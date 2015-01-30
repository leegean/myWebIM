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

import javax.imageio.ImageIO;

import org.json.JSONException;
import org.json.JSONObject;

public class WbLoginAction extends AbstractHttpAction{

	private QQSession session;
	private long prelt;
	/**
	 * <p>Constructor for GetCaptchaImageAction.</p>
	 *
	 * @param context a {@link iqq.im.core.QQContext} object.
	 * @param listener a {@link iqq.im.QQActionListener} object.
	 * @param uin a long.
	 */
	public WbLoginAction(QQContext context, QQActionListener listener, long prelt) {
		super(context, listener);
		this.prelt = prelt;
		session = context.getSession();
	}
	@Override
	protected void onHttpStatusOK(QQHttpResponse response) throws QQException,
			JSONException {
//		{"retcode":"101","reason":"\u767b\u5f55\u540d\u6216\u5bc6\u7801\u9519\u8bef"}
		JSONObject json = new JSONObject(response.getResponseString());
		int retcode = json.getInt("retcode");
		switch (retcode) {
		case 0:
//		{"retcode":"0","ticket":"ST-MjY0NTA1MjYwMw==-1421823968-gz-A4B8DD8D1824CF382EB98D4084DC0F78","uid":"2645052603","nick":"\u4e00\u53e5\u8bdd_\u7ecf\u5178","crossDomainUrlList":["http:\/\/crosdom.weicaifu.com\/sso\/crosdom?action=login&savestate=1453359968","http:\/\/passport.97973.com\/sso\/crossdomain?action=login&savestate=1453359968"]}
			notifyActionEvent(QQActionEvent.Type.EVT_OK, null);
			break;
		case 101:
			notifyActionEvent(QQActionEvent.Type.EVT_ERROR, "登录名或密码错误");
			break;
		case 80:
			notifyActionEvent(QQActionEvent.Type.EVT_ERROR, "请输入正确的密码");
			break;
		case 2092:
			notifyActionEvent(QQActionEvent.Type.EVT_ERROR, "抱歉！登录失败，请稍候再试");
			break;
//			{"retcode":"4049","reason":"\u4e3a\u4e86\u60a8\u7684\u5e10\u53f7\u5b89\u5168\uff0c\u8bf7\u8f93\u5165\u9a8c\u8bc1\u7801"}
//			为了您的帐号安全，请输入验证码
//			{"retcode":"2070","reason":"\u8f93\u5165\u7684\u9a8c\u8bc1\u7801\u4e0d\u6b63\u786e"}
//			输入的验证码不正确
//			{"retcode":"2093","reason":"\u62b1\u6b49\uff01\u767b\u5f55\u5931\u8d25\uff0c\u8bf7\u7a0d\u5019\u518d\u8bd5"}
//			{"retcode":"4401","reason":"\u62b1\u6b49\uff01\u767b\u5f55\u5931\u8d25\uff0c\u8bf7\u7a0d\u5019\u518d\u8bd5"}
//			抱歉！登录失败，请稍候再试
//			{"retcode":"20","reason":"\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u767b\u5f55\u540d"}
//			请输入正确的登录名
		default:
			notifyActionEvent(QQActionEvent.Type.EVT_ERROR, response.getResponseString());
			break;
		}
		
	}
	@Override
	protected QQHttpRequest onBuildRequest() throws QQException, JSONException {
		String url = StringHelper.format(QQConstants.URL_WB_LOGIN, new Date().getTime());
		
		QQHttpRequest req = createHttpRequest("POST", url);
		/*entry:
		gateway:1
		from:
		savestate:7
		useticket:1
		pagerefer:http://www.hao123.com/
		wsseretry:servertime_error
		vsnf:1
		su:NTY5Mzk4NDAzJTQwcXEuY29t
		service:miniblog
		servertime:1421126570
		nonce:PHZ8J6
		pwencode:rsa2
		rsakv:1330428213
		sp:
		a0d417d72b73c749ed7525c3e0c794107a9e1706b4717d1d1952c0e7e3d9ec688d37cd462e37d39242b1de8cb0e9d90a11ce69f30419b76dbf662bffb0314d0227068e26204215b53183c63ce8330ebb0573ae06931d7e2d1272b810806993d3ecd2d823b152e1f222d8eab75662b5ce48b47094e383ef0e5145879cf717ecdb
		a65db48facc49b62438a2aa82ff09a2064fa3d8333c4d9d130a07607954a655503fffd3abaf1d21e666018b3aaba292bcb058e61475b148e4f27c80a6f2c39acf23a322fe1c2ccf8298300cb17f2eb38bc987eacad7a3e9df6417aac810c9df7cb4ecfa896b9e4d43b735b9b7435cfc6e1d2ede0af912c8da84b735599480055
		e68bee9f683bb008ac7792d035f5236fe1f2ec689133caebd97e4f5289931284c8ef083ad06dcd878da6e60811c68e51d6e8a60b440b79c4fe385deb4293bf6b88ecb3b996cbad8ef79d4c36da6bd2b93ced2143ed01fe3f106faf1efba38825ac60c5876bdb388ab5e971cbdc4f61b3066894bec4238d3e4670a887de885156
		sr:1440*900
		encoding:UTF-8
		cdult:2
		domain:weibo.com
		prelt:55877
		returntype:TEXT*/
		req.addPostValue("entry","weibo");
		req.addPostValue("gateway","1");
		req.addPostValue("from","");
		req.addPostValue("savestate","7");
		req.addPostValue("useticket","1");
		req.addPostValue("pagerefer","http://passport.weibo.com/visitor/visitor?entry=miniblog&a=enter&url=http%3A%2F%2Fweibo.com%2F&domain=.weibo.com&ua=php-sso_sdk_client-0.6.9&_rand=1421390373.5412");
//		req.addPostValue("wsseretry","servertime_error");
		req.addPostValue("vsnf","1");
		req.addPostValue("su",""+session.getSu());
		req.addPostValue("service","miniblog");
		
		long servertime = session.getServertime();
		String nonce = session.getNonce();
		long rsakv = session.getRsakv();
		String pubkey = session.getPubkey();
		
		req.addPostValue("servertime",""+servertime);
		req.addPostValue("nonce",""+nonce);
		req.addPostValue("pwencode","rsa2");
		req.addPostValue("rsakv",""+rsakv);
		String sp = QQEncryptor.getWbSp(getContext().getAccount().getWbPassword(), pubkey, servertime, nonce);
		session.setSp(sp);
		System.out.println(servertime+"   "+nonce+"   "+rsakv+"   "+ pubkey);
		System.out.println(sp);
		req.addPostValue("sp",""+sp);
		req.addPostValue("sr","1440*900");
		req.addPostValue("encoding","UTF-8");
		req.addPostValue("cdult","2");
		req.addPostValue("domain","weibo.com");
		req.addPostValue("prelt",prelt+"");
		req.addPostValue("returntype","TEXT");
		
		req.addHeader("Referer", "http://d.weibo.com/");
		return req;
	}

	


}
