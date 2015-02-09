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
import java.net.MalformedURLException;
import java.net.URL;
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

public class WbHandshakeAction extends AbstractHttpAction{

	private QQSession session;
	/**
	 * <p>Constructor for GetCaptchaImageAction.</p>
	 *
	 * @param context a {@link iqq.im.core.QQContext} object.
	 * @param listener a {@link iqq.im.QQActionListener} object.
	 * @param uin a long.
	 */
	public WbHandshakeAction(QQContext context, QQActionListener listener) {
		super(context, listener);
		this.session = context.getSession();
	}
	/** {@inheritDoc} */
	@Override
	protected void onHttpStatusOK(QQHttpResponse response) throws QQException,
			JSONException {
//		try{parent.webimCB._callback5([{"id":"6","minimumVersion":"1.0","supportedConnectionTypes":["callback-polling","long-polling"],"successful":true,
//		"channel":"/meta/handshake","ext":{"timesync":{"ts":1423197700664,"tc":1423197651515,"p":0,"a":-49149},"ack":true},"clientId":"1o4zsqkw1g7e3iy171kuubb5awt03k","version":"1.0"}])}catch(e){}
		String respStr = response.getResponseString();
		Pattern pattern = Pattern.compile(QQConstants.REGXP_WEBIMCB);
		Matcher m = pattern.matcher(respStr);
		if(m.find()){
			JSONArray json = new JSONArray(m.group(1));
			JSONObject jsonO = json.getJSONObject(0);
			session.setWbClientId(jsonO.getString("clientId"));
			JSONObject ext = jsonO.getJSONObject("ext");
			JSONObject timesync = ext.getJSONObject("timesync");
			String ts = timesync.getString("ts");
			String tc = timesync.getString("tc");
			String p = timesync.getString("p");
			QQEncryptor.updateWbTimesync(ts, tc, p);
				 notifyActionEvent(QQActionEvent.Type.EVT_OK, respStr);
		}else{
			notifyActionEvent(QQActionEvent.Type.EVT_ERROR, respStr);
		}
	}
	/** {@inheritDoc} */
	@Override
	protected QQHttpRequest onBuildRequest() throws QQException, JSONException {
		String jsonp = "parent.webimCB._callback"+session.getId();
		session.setId(session.getId()+1);
		JSONArray jsArray = new JSONArray("[{\"version\":\"1.0\",\"minimumVersion\":\"0.9\",\"channel\":\"/meta/handshake\"," +
				"\"supportedConnectionTypes\":[\"callback-polling\"],\"advice\":{\"timeout\":60000,\"interval\":0},\"id\":\"1\"," +
				"\"ext\":{\"ack\":true,\"timesync\":{\"tc\":1423104001494,\"l\":0,\"o\":0}},\"timestamp\":\"Thu, 05 Feb 2015 02:40:01 GMT\"}]");
		JSONObject jsObject = jsArray.getJSONObject(0);
		jsObject.put("id", session.getId()+"");
		 JSONObject ext = QQEncryptor.updateExt(true);
		 jsObject.put("ext", ext);
		jsObject.put("timestamp", QQEncryptor.getWbTimestamp());
		
		String url = "";
		try {
			String message = URLEncoder.encode(jsArray.toString(), "UTF-8");
//			message = QQEncryptor.urlEncode(jsArray.toString());
			String localeDate = URLEncoder.encode(QQEncryptor.getWbLocaleDate(), "UTF-8");
//			localeDate = QQEncryptor.urlEncode(QQEncryptor.getWbLocaleDate());
//			localeDate = "Mon%20Feb%2009%202015%2013:00:08%20GMT+0800%20(%E4%B8%AD%E5%9B%BD%E6%A0%87%E5%87%86%E6%97%B6%E9%97%B4)";
			url = session.getWbImUrl()+"im/handshake?jsonp="+jsonp+"&message="+message+"&"+localeDate;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		QQHttpRequest req = createHttpRequest("GET", url);
		
//		try {
//			req.addHeader("Host", new URL(session.getWbImUrl()).getHost());
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return req;
	}
	
}
