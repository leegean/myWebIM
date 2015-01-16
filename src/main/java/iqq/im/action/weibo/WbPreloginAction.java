package iqq.im.action.weibo;

import iqq.im.QQActionListener;
import iqq.im.QQException;
import iqq.im.QQException.QQErrorCode;
import iqq.im.action.AbstractHttpAction;
import iqq.im.core.QQConstants;
import iqq.im.core.QQContext;
import iqq.im.core.QQSession;
import iqq.im.event.QQActionEvent;
import iqq.im.http.QQHttpRequest;
import iqq.im.http.QQHttpResponse;
import iqq.im.util.QQEncryptor;
import iqq.im.util.StringHelper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.json.JSONException;
import org.json.JSONObject;

public class WbPreloginAction extends AbstractHttpAction{

	private QQSession session;
	/**
	 * <p>Constructor for GetCaptchaImageAction.</p>
	 *
	 * @param context a {@link iqq.im.core.QQContext} object.
	 * @param listener a {@link iqq.im.QQActionListener} object.
	 * @param uin a long.
	 */
	public WbPreloginAction(QQContext context, QQActionListener listener) {
		super(context, listener);
		this.session = context.getSession();
	}
	/** {@inheritDoc} */
	@Override
	protected void onHttpStatusOK(QQHttpResponse response) throws QQException,
			JSONException {
//		sinaSSOController.preloginCallBack({"retcode":0,"servertime":1421126570,"pcid":"gz-14863373e0c3615ab981f0a29795032e72c7","nonce":"PHZ8J6","pubkey":"EB2A38568661887FA180BDDB5CABD5F21C7BFD59C090CB2D245A87AC253062882729293E5506350508E7F9AA3BB77F4333231490F915F6D63C55FE2F08A49B353F444AD3993CACC02DB784ABBB8E42A9B1BBFFFB38BE18D78E87A0E41B9B8F73A928EE0CCEE1F6739884B9777E4FE9E88A1BBE495927AC4A799B3181D6442443","rsakv":"1330428213","exectime":3})
		String respStr = response.getResponseString();
		Pattern p = Pattern.compile("sinaSSOController.preloginCallBack\\((\\{[^\\{]+\\})\\)");
		Matcher m = p.matcher(respStr);
		if(m.matches()){
			JSONObject json = new JSONObject(m.group(1));
			int retcode = json.getInt("retcode");
			if(retcode==0){
				QQSession session = getContext().getSession();
				long servertime = json.getLong("servertime");
				String nonce = json.getString("nonce");
				String pubkey = json.getString("pubkey");
				long rsakv = json.getLong("rsakv");
				int exectime =  json.getInt("exectime");
				session.setExectime(exectime);
				
				session.setServertime(servertime);
				session.setNonce(nonce);
				session.setPubkey(pubkey);
				session.setRsakv(rsakv);
				 notifyActionEvent(QQActionEvent.Type.EVT_OK, null);
			}else{
				notifyActionEvent(QQActionEvent.Type.EVT_ERROR, respStr);
			}
		}
	}
	/** {@inheritDoc} */
	@Override
	protected QQHttpRequest onBuildRequest() throws QQException, JSONException {
		String su = QQEncryptor.getWbSu(getContext().getAccount().getWbUsername());
		getContext().getSession().setSu(su);
		long startTime = new Date().getTime();
		session.setStarttime(startTime);
		String url = StringHelper.format(QQConstants.URL_WB_PRELOGIN, su,startTime);
		return createHttpRequest("GET", url);
	}

	


}
