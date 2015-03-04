
package iqq.im.action.qun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import iqq.im.QQActionListener;
import iqq.im.QQException;
import iqq.im.action.AbstractHttpAction;
import iqq.im.bean.QQAccount;
import iqq.im.bean.QQGroup;
import iqq.im.bean.QmGroupMembers;
import iqq.im.bean.QmMemSearchCondition;
import iqq.im.core.*;
import iqq.im.event.QQActionEvent;
import iqq.im.http.QQHttpCookie;
import iqq.im.http.QQHttpRequest;
import iqq.im.http.QQHttpResponse;

import iqq.im.service.HttpService;
import iqq.im.util.QQEncryptor;
import org.slf4j.Logger;
import org.apache.http.cookie.Cookie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

/**
 * 获取群列表名称
 *
 * @author ChenZhiHui
 * @since 2013-2-21
 */
public class QmSetGroupCardAction extends AbstractHttpAction {
	private static final Logger LOG = LoggerFactory.getLogger(QmSetGroupCardAction.class);
	private String group;
	private String uin;
	private String card;

	/**
	 * <p>Constructor for GetGroupListAction.</p>
	 *
	 * @param context a {@link iqq.im.core.QQContext} object.
	 * @param listener a {@link iqq.im.QQActionListener} object.
	 */
	public QmSetGroupCardAction(String group, String uin,String card,  QQContext context, QQActionListener listener) {
		super(context, listener);
		this.group = group;
		this.uin = uin;
		this.card = card;
		
	}

	/** {@inheritDoc} */
	@Override
	public QQHttpRequest onBuildRequest() throws QQException, JSONException {
//		gc:126658727
//		u:2280410025
//		name:name
//		bkn:605178519
		
		HttpService httpService = (HttpService) getContext().getSerivce(QQService.Type.HTTP);
		Cookie skey = httpService.getCookie("skey", QQConstants.URL_QM_SEATCH_GROUP_MEMBERS);

		QQHttpRequest req = createHttpRequest("POST",
				QQConstants.URL_QM_SET_GROUP_CARD);
		req.addPostValue("gc", group);
		req.addPostValue("u", uin);
		req.addPostValue("name", card);
		req.addPostValue("bkn", QQEncryptor.getBkn(skey.getValue()) + "");
		

		req.addHeader("Referer", "http://qun.qq.com/member.html");

		return req;
	}

	/** {@inheritDoc} */
	@Override
	protected void onHttpStatusOK(QQHttpResponse response) throws QQException,
			JSONException {
//		{"ec":0}
//		 {"ec":15,"em":""}
		QQStore store = getContext().getStore();
		JSONObject json = new JSONObject(response.getResponseString());

		int returnCode = json.optInt("ec", -1);
		if(returnCode==0){
			notifyActionEvent(QQActionEvent.Type.EVT_OK, null); 	
		}else{
			notifyActionEvent(QQActionEvent.Type.EVT_ERROR, response.getResponseString()); 	
		}
	}

}
