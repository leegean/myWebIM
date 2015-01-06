
 /**
 * Project  : WebQQCore
 * Package  : iqq.im.action
 * File     : GetLoginSigAction.java
 * Author   : solosky < solosky772@qq.com >
 * Created  : Aug 4, 2013
 * License  : Apache License 2.0 
 */
package iqq.im.action.qun.login;

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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.json.JSONException;
import org.slf4j.LoggerFactory;

public class QmGetLoginSigAction extends AbstractHttpAction {
	private static Logger LOG = LoggerFactory.getLogger(QmGetLoginSigAction.class);

	/**
	 * <p>Constructor for GetLoginSigAction.</p>
	 *
	 * @param context a {@link iqq.im.core.QQContext} object.
	 * @param listener a {@link iqq.im.QQActionListener} object.
	 */
	public QmGetLoginSigAction(QQContext context, QQActionListener listener) {
		super(context, listener);
	}

	/** {@inheritDoc} */
	@Override
	protected void onHttpStatusOK(QQHttpResponse response) throws QQException,
			JSONException {
		Pattern pt = Pattern.compile(QQConstants.REGXP_QM_LOGIN_SIG);
		Matcher mc = pt.matcher(response.getResponseString());
		if(mc.find()){
			QQSession session = getContext().getSession();
			session.setQmLoginSig(mc.group(1));
			notifyActionEvent(QQActionEvent.Type.EVT_OK, session.getQmLoginSig());
		}else{
			notifyActionEvent(QQActionEvent.Type.EVT_ERROR, new QQException(QQErrorCode.INVALID_RESPONSE, "Qm Login Sig Not Found!!"));
		}
	}

	/** {@inheritDoc} */
	@Override
	protected QQHttpRequest onBuildRequest() throws QQException, JSONException {
		return createHttpRequest("GET", QQConstants.URL_QM_LOGIN_SIG);
	}
}
