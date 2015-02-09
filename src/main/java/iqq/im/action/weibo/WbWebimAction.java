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

public class WbWebimAction extends AbstractHttpAction{

	private QQSession session;
	/**
	 * <p>Constructor for GetCaptchaImageAction.</p>
	 *
	 * @param context a {@link iqq.im.core.QQContext} object.
	 * @param listener a {@link iqq.im.QQActionListener} object.
	 * @param uin a long.
	 */
	public WbWebimAction(QQContext context, QQActionListener listener) {
		super(context, listener);
		this.session = context.getSession();
	}
	/** {@inheritDoc} */
	@Override
	protected void onHttpStatusOK(QQHttpResponse response) throws QQException,
			JSONException {

//		 try{IM_14234451220292({"channel":"/im/he47ot_2645052603","server":"http://12.76.web1.im.weibo.com/"});}catch(e){}
		 String respStr = response.getResponseString();
		Pattern p = Pattern.compile("try\\{IM_\\d+\\(([^\\)]+)\\);\\}catch\\(e\\)\\{\\}");
		Matcher m = p.matcher(respStr);
		if(m.find()){
			JSONObject json = new JSONObject(m.group(1));
			String channel = json.getString("channel");
			String server = json.getString("server");
			session.setWbImUrl(server);
			session.setWbUidChannel(channel);
			notifyActionEvent(QQActionEvent.Type.EVT_OK, respStr);
		}else{
			notifyActionEvent(QQActionEvent.Type.EVT_ERROR, respStr);
		}
	}
	/** {@inheritDoc} */
	@Override
	protected QQHttpRequest onBuildRequest() throws QQException, JSONException {
//		http://nas.im.api.weibo.com/im/webim.jsp?uid=2645052603&returntype=json&v=1.1
//		&callback=IM_14234434115082&__rnd=1423443411508
		String url = StringHelper.format(QQConstants.URL_WB_webim, session.getWbUid(), QQEncryptor.getImCallback(),QQEncryptor.getRnd());
		QQHttpRequest req = createHttpRequest("GET", url);
		req.addHeader("Referer", "http://weibo.com/leegean/home?wvr=5");
		return req;
	}

	


}
