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

public class WbGetVerifyImageAction extends AbstractHttpAction{

	private QQSession session;
	private WbVerifyImage image;
	/**
	 * <p>Constructor for GetCaptchaImageAction.</p>
	 *
	 * @param context a {@link iqq.im.core.QQContext} object.
	 * @param listener a {@link iqq.im.QQActionListener} object.
	 * @param uin a long.
	 */
	public WbGetVerifyImageAction(QQContext context, QQActionListener listener, WbVerifyImage image) {
		super(context, listener);
		this.session = context.getSession();
		this.image = image;
	}
	/** {@inheritDoc} */
	@Override
	protected void onHttpStatusOK(QQHttpResponse response) throws QQException,
			JSONException {
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(response.getResponseData());
			image.setImage(ImageIO.read(in));
			notifyActionEvent(QQActionEvent.Type.EVT_OK,image );
		} catch (IOException e) {
			notifyActionEvent(QQActionEvent.Type.EVT_ERROR, new QQException(QQErrorCode.UNKNOWN_ERROR, e));
		}
			
	}
	/** {@inheritDoc} */
	@Override
	protected QQHttpRequest onBuildRequest() throws QQException, JSONException {
		String url = StringHelper.format(QQConstants.WB_VERIFY_IMAGE);
		return createHttpRequest("GET", url);
	}

	


}
