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
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.json.JSONException;
import org.json.JSONObject;

public class WbGetVerifyImageUrlAction extends AbstractHttpAction {

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
	public WbGetVerifyImageUrlAction(QQContext context, QQActionListener listener) {
		super(context, listener);
		this.session = context.getSession();
	}

	/** {@inheritDoc} */
	@Override
	protected void onHttpStatusOK(QQHttpResponse response) throws QQException, JSONException {
		String respStr = response.getResponseString();
		JSONObject json = new JSONObject(respStr);
		int retcode = json.optInt("retcode", -1);
		
		if (retcode == 20000000) {
			JSONObject data = json.optJSONObject("data");
			String src = data.optString("image");
			String pcid = data.optString("pcid");
			// that.verifyImage.src = result.data.image;
			// that.verifyImage.setAttribute('data-pcid', result.data.pcid);
			WbVerifyImage verifyImage = new WbVerifyImage();
			verifyImage.setSrc(src);
			verifyImage.setDataPcid(pcid);
			notifyActionEvent(QQActionEvent.Type.EVT_OK, verifyImage);
		} else {
			notifyActionEvent(QQActionEvent.Type.EVT_ERROR, respStr);
		}
	}

	/** {@inheritDoc} */
	@Override
	protected QQHttpRequest onBuildRequest() throws QQException, JSONException {
		String url = StringHelper.format(QQConstants.WB_VERIFY_IMAGE);
		return createHttpRequest("GET", url);
	}

}
