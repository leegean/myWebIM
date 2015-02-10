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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WbSubscribeAction extends AbstractHttpAction{

	private QQSession session;
	/**
	 * <p>Constructor for GetCaptchaImageAction.</p>
	 *
	 * @param context a {@link iqq.im.core.QQContext} object.
	 * @param listener a {@link iqq.im.QQActionListener} object.
	 * @param uin a long.
	 */
	public WbSubscribeAction(QQContext context, QQActionListener listener) {
		super(context, listener);
		this.session = context.getSession();
	}
	/** {@inheritDoc} */
	@Override
	protected void onHttpStatusOK(QQHttpResponse response) throws QQException,
			JSONException {
//		try{parent.webimCB._callback6([{"id":"7","subscription":"/im/qeitlb_2645052603","successful":true,"channel":"/meta/subscribe","ext":{"timesync":{"ts":1423197700753,"
//		tc":1423197652679,"p":36,"a":1076}}}])}catch(e){}
		String respStr = response.getResponseString();
		Pattern pattern = Pattern.compile(QQConstants.REGXP_WEBIMCB);
		Matcher m = pattern.matcher(respStr);
		if(m.matches()){
			JSONArray json = new JSONArray(m.group(1));
			JSONObject jsonO = json.getJSONObject(0);
			JSONObject ext = jsonO.getJSONObject("ext");
			JSONObject timesync = ext.getJSONObject("timesync");
			String ts = timesync.getString("ts");
			String tc = timesync.getString("tc");
			String p = timesync.getString("p");
			QQEncryptor.updateWbTimesync(ts, tc, p);
				 notifyActionEvent(QQActionEvent.Type.EVT_OK, null);
		}else{
			notifyActionEvent(QQActionEvent.Type.EVT_ERROR, respStr);
		}
	}
	/** {@inheritDoc} */
	@Override
	protected QQHttpRequest onBuildRequest() throws QQException, JSONException {
		String jsonp = "parent.webimCB._callback"+session.getId();
		session.setId(session.getId()+1);
		JSONArray jsArray = new JSONArray("[{\"channel\":\"/meta/subscribe\",\"subscription\":\"/im/qeitlb_2645052603\",\"id\":\"7\"," +
				"\"clientId\":\"1o4zsqkw1g7e3iy171kuubb5awt03k\",\"timestamp\":\"Fri, 06 Feb 2015 04:40:52 GMT\"," +
				"\"ext\":{\"timesync\":{\"tc\":1423197652679,\"l\":582,\"o\":48568}}}]");
		JSONObject jsObject = jsArray.getJSONObject(0);
		jsObject.put("subscription", session.getWbUidChannel());
		jsObject.put("id", session.getId()+"");
		jsObject.put("clientId", session.getWbClientId());
		
		 JSONObject ext = QQEncryptor.updateExt(null);
		 jsObject.put("ext", ext);
		jsObject.put("timestamp", QQEncryptor.getWbTimestamp());
		
		String url = "";
		try {
			String message = URLEncoder.encode(jsArray.toString(), "UTF-8");
			String localeDate = URLEncoder.encode(QQEncryptor.getWbLocaleDate(), "UTF-8");
			url = session.getWbImUrl()+"im/?jsonp="+jsonp+"&message="+message+"&"+localeDate;
		} catch (UnsupportedEncodingException e) {
		}
		
		 
		return createHttpRequest("GET", url);
	}
	
}
