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

public class WbCheckVerifyAction extends AbstractHttpAction {

	private QQSession session;

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
	public WbCheckVerifyAction(QQContext context, QQActionListener listener) {
		super(context, listener);
		this.session = context.getSession();
	}

	/** {@inheritDoc} */
	@Override
	protected void onHttpStatusOK(QQHttpResponse response) throws QQException, JSONException {
		// jsonpcallback1423798304929({"retcode":0,"servertime":1423798300,"pcid":"gz-109d698e0a916c8275a5658f4be29d19c0b2","nonce":"5K32RF","pubkey":"-----BEGIN
		// PUBLIC
		// KEY-----\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDrKjhWhmGIf6GAvdtcq9XyHHv9\nWcCQyy0kWoesJTBiiCcpKT5VBjUFCOf5qju3f0MzIxSQ+RX21jxV\/i8IpJs1P0RK\n05k8rMAtt4Sru45CqbG7\/\/s4vhjXjoeg5Bubj3OpKO4MzuH2c5iEuXd+T+noihu+\nSVknrEp5mzGB1kQkQwIDAQAB\n-----END
		// PUBLIC KEY-----","rsakv":"1330428213","showpin":0,"exectime":9})
		String respStr = response.getResponseString();
		Pattern p = Pattern.compile("jsonpcallback\\d+\\(([^\\)]+)\\)");
		Matcher m = p.matcher(respStr);
		if (m.matches()) {
			JSONObject json = new JSONObject(m.group(1));
			Integer retcode = json.optInt("retcode", -1);
			Integer nopwd = json.optInt("nopwd", -1);
			Integer lm = json.optInt("lm", -1);
			Integer showpin = json.optInt("showpin", -1);
			if (retcode == 0) {
				if (nopwd == 1 && lm == 1) {
					notifyActionEvent(QQActionEvent.Type.EVT_OK, "您的账号是通过快速注册方式获得的，需通过验证码登陆方式登陆微博");
				} else {
					switch (showpin) {// 此处不要忘记隐藏需要手机验证码登陆时要隐藏的东西
					case 1:
						notifyActionEvent(QQActionEvent.Type.EVT_OK, true);
						break;
					default:
						notifyActionEvent(QQActionEvent.Type.EVT_OK, false);
						break;
					}
				}

			} else {
				notifyActionEvent(QQActionEvent.Type.EVT_ERROR, respStr);
			}

		} else {
			notifyActionEvent(QQActionEvent.Type.EVT_ERROR, respStr);
		}
	}

	/** {@inheritDoc} */
	@Override
	protected QQHttpRequest onBuildRequest() throws QQException, JSONException {
		String jsonpName = "callback";
		String jsonpCallback = QQEncryptor._siteId("jsonpcallback");
		String jsonpParams = jsonpName + '=' + jsonpCallback;
		String su = QQEncryptor.utf8_to_b64(getContext().getAccount().getWbUsername());
		getContext().getSession().setSu(su);
		String url = StringHelper.format(QQConstants.WB_PRELOGIN) + "&su=" + su;
		String andTag = url.indexOf("?") > 0 ? "&" : "?";
		url = url + andTag + jsonpParams;
		return createHttpRequest("GET", url);
	}

}
