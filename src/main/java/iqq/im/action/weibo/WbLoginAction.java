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
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;

import org.json.JSONException;
import org.json.JSONObject;

public class WbLoginAction extends AbstractHttpAction {

	private QQSession session;
	private WbVerifyImage verifyImage;

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
	public WbLoginAction(QQContext context, QQActionListener listener, WbVerifyImage verifyImage) {
		super(context, listener);
		session = context.getSession();
		this.verifyImage = verifyImage;
	}

	@Override
	protected void onHttpStatusOK(QQHttpResponse response) throws QQException, JSONException {
		// {"retcode":20000000,"msg":"","data":{"crossdomainlist":{"weibo.com":"\/\/passport.weibo.com\/sso\/crossdomain?entry=mweibo&action=login&proj=1&ticket=ST-MjY0NTA1MjYwMw%3D%3D-1423804052-gz-440F8100AC487A47A0394C7117AD73AC","sina.com.cn":"\/\/login.sina.com.cn\/sso\/crossdomain?entry=mweibo&action=login&proj=1&ticket=ST-MjY0NTA1MjYwMw%3D%3D-1423804052-gz-B464F164650727793AD34C693C0C166D","weibo.cn":"\/\/passport.sina.cn\/sso\/crossdomain?entry=mweibo&action=login&ticket=ST-MjY0NTA1MjYwMw%3D%3D-1423804052-gz-50D268018D2BD2C6E5FDCA440F37EDD0"},"loginresulturl":"","uid":"2645052603"}}
		String respStr = response.getResponseString();
		JSONObject json = new JSONObject(respStr);
		int retcode = json.optInt("retcode", -1);
		if (retcode == 20000000) {
			JSONObject data = json.optJSONObject("data");
			JSONObject crossdomains = data.optJSONObject("crossdomainlist");
			Iterator<String> keys = crossdomains.keys();
			ArrayList<String> crossdomainlist = new ArrayList<String>();
			while (keys.hasNext()) {
				String key = keys.next();
				crossdomainlist.add(crossdomains.getString(key));
			}

			notifyActionEvent(QQActionEvent.Type.EVT_OK, crossdomainlist);
		} else {
			notifyActionEvent(QQActionEvent.Type.EVT_ERROR, respStr);
		}
	}

	@Override
	protected QQHttpRequest onBuildRequest() throws QQException, JSONException {
		String url = StringHelper.format(QQConstants.WB_LOGIN);

		QQHttpRequest req = createHttpRequest("POST", url);
		req.addHeader("Content-Type", "application/x-www-form-urlencoded");

		req.addPostValue("username", getContext().getAccount().getWbUsername());
		req.addPostValue("password", getContext().getAccount().getWbPassword());
		req.addPostValue("savestate", "1");
		req.addPostValue("ec", "0");
		if (verifyImage != null) {
			req.addPostValue("pincode", verifyImage.getCode());
			req.addPostValue("pcid", verifyImage.getDataPcid());
		}

		req.addPostValue("pagerefer", "");
		req.addPostValue("entry", "mweibo");
		// req.addPostValue("rf", "");
		// req.addPostValue("rfcal", "");
		req.addPostValue("loginfrom", "");
		req.addPostValue("client_id", "");
		req.addPostValue("code", "");

		req.addPostValue("hff", "");
		req.addPostValue("hfp", "");

		req.addHeader("Referer", "https://passport.weibo.cn/signin/login?entry=mweibo&res=wel&wm=3349&r=http%3A%2F%2Fm.weibo.cn%2F%3Fjumpfrom%3Dweibocom");
		return req;
	}

}
