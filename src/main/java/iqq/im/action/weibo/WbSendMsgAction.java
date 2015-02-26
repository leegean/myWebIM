package iqq.im.action.weibo;

import iqq.im.QQActionListener;
import iqq.im.QQException;
import iqq.im.QQException.QQErrorCode;
import iqq.im.action.AbstractHttpAction;
import iqq.im.bean.content.WbVerifyImage;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;

import org.json.JSONException;
import org.json.JSONObject;

public class WbSendMsgAction extends AbstractHttpAction {

	private QQSession session;
	private String msg;
	private String acceptor;

	/**
	 * <p>
	 * Constructor for GetCaptchaImageAction.
	 * </p>
	 * 
	 * @param context
	 *            a {@link iqq.im.core.QQContext} object.
	 * @param listener
	 *            a {@link iqq.im.QQActionListener} object.
	 * @param uin
	 *            a long.
	 */
	public WbSendMsgAction(QQContext context, QQActionListener listener, String msg, String acceptor) {
		super(context, listener);
		session = context.getSession();
		this.msg = msg;
		this.acceptor = acceptor;
	}

	@Override
	protected void onHttpStatusOK(QQHttpResponse response) throws QQException, JSONException {
//		 {"ok":1,"msg":"\u79c1\u4fe1\u53d1\u9001\u6210\u529f"}
		String respStr = response.getResponseString();
		JSONObject json = new JSONObject(respStr);
		int retcode = json.optInt("ok", -1);
		if (retcode == 1) {

			notifyActionEvent(QQActionEvent.Type.EVT_OK, respStr);
		} else {
			notifyActionEvent(QQActionEvent.Type.EVT_ERROR, respStr);
		}
	}

	@Override
	protected QQHttpRequest onBuildRequest() throws QQException, JSONException {
		String url = StringHelper.format(QQConstants.WB_SEND_MSG);

		QQHttpRequest req = createHttpRequest("POST", url);
		req.addHeader("Content-Type", "application/x-www-form-urlencoded");

		req.addPostValue("fileId", "null");
		req.addPostValue("uid", acceptor);
		try {
			req.addPostValue("content", URLEncoder.encode(msg, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		req.addHeader("Referer", "http://m.weibo.cn/msg/chat?uid=5175429989");
		req.addHeader("User-Agent", "Mozilla/5.0 (Linux; U; Android 4.3; en-us; SM-N900T Build/JSS15J) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");
		return req;
	}

}
