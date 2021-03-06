
 /**
 * Project  : WebQQCore
 * Package  : iqq.im.action
 * File     : CheckSigAction.java
 * Author   : solosky < solosky772@qq.com >
 * Created  : Aug 4, 2013
 * License  : Apache License 2.0 
 */
package iqq.im.action.qun;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import iqq.im.QQActionListener;
import iqq.im.QQException;
import iqq.im.QQException.QQErrorCode;
import iqq.im.action.AbstractHttpAction;
import iqq.im.core.QQConstants;
import iqq.im.core.QQContext;
import iqq.im.event.QQActionEvent;
import iqq.im.event.QQActionEventArgs;
import iqq.im.http.QQHttpRequest;
import iqq.im.http.QQHttpResponse;
import iqq.im.util.QQEncryptor;

import org.slf4j.Logger;
import org.json.JSONException;
import org.slf4j.LoggerFactory;

/**
 * <p>CheckLoginSigAction class.</p>
 *
 * @author solosky
 */
public class QmCheckVerifyAction extends AbstractHttpAction{
	private static final Logger LOG = LoggerFactory.getLogger(QmCheckVerifyAction.class);
	/**
	 * <p>Constructor for CheckLoginSigAction.</p>
	 *
	 * @param context a {@link iqq.im.core.QQContext} object.
	 * @param listener a {@link iqq.im.QQActionListener} object.
	 * @param checkSigUrl a {@link java.lang.String} object.
	 */
	public QmCheckVerifyAction(QQContext context, QQActionListener listener) {
		super(context, listener);
	}
	/** {@inheritDoc} */
	@Override
	protected void onHttpStatusOK(QQHttpResponse response) throws QQException, JSONException {
		Pattern p = Pattern.compile(QQConstants.REGXP_QM_CHECK_VERIFY);
        Matcher m = p.matcher(response.getResponseString());
//        ptui_checkVC('0','!GUL','\x00\x00\x00\x00\x21\xf0\x54\x83','7de0b6e5222063877d6a9b7f8729d5729eaac2bcfc0b085d99869416137b9d71beda7b022ec4b11c25cba67671a06ac5','0');
//        ptui_checkVC('1','_-KqLqI9-mm3l6jLsLauBlIzu10zEbbu','\x00\x00\x00\x00\x53\x63\x4d\x24','','0');
        if(m.find()){
        	String qqHex = m.group(3);
			qqHex = qqHex.replaceAll("\\\\x", "");
        	QQActionEventArgs.CheckVerifyArgs args = new QQActionEventArgs.CheckVerifyArgs();
        	args.result = Integer.parseInt(m.group(1));
        	args.code   = m.group(2);
        	args.uin    = Long.parseLong(qqHex, 16);
        	notifyActionEvent(QQActionEvent.Type.EVT_OK, args);
        }else{
        	notifyActionEvent(QQActionEvent.Type.EVT_ERROR, QQErrorCode.UNEXPECTED_RESPONSE);
        }
	}
	/** {@inheritDoc} */
	@Override
	protected QQHttpRequest onBuildRequest() throws QQException, JSONException {
//		http://check.ptlogin2.qq.com/check?regmaster=&pt_tea=1&uin=1002053815&appid=715030901&js_ver=10106&js_type=1&login_sig=CNipH*l4Kw29K1NVWgFyHTpmPkJVxqZJi6gfj2tukZPiCW8Urnbf9RXw1dQieIte&u1=http%3A%2F%2Fqun.qq.com%2F&r=0.6489963890053332
		
		QQHttpRequest req = createHttpRequest("GET", QQConstants.URL_QM_CHECK_VERIFY);
		req.addGetValue("regmaster", "");
		req.addGetValue("pt_tea", "1");
		req.addGetValue("uin", getContext().getAccount().getUsername()+"");
		req.addGetValue("appid", QQConstants.QM_APPID);
		req.addGetValue("js_ver",QQConstants.QM_JS_VER);
		req.addGetValue("js_type", "1");
		req.addGetValue("login_sig", getContext().getSession().getQmLoginSig());
		req.addGetValue("u1", "http%3A%2F%2Fqun.qq.com%2F");
		req.addGetValue("r", Math.random()+"");
		req.addHeader("Referer", QQConstants.URL_QM_LOGIN_SIG);
		return req;
	}

}
