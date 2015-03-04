
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
public class QmDeleteGroupMemberAction extends AbstractHttpAction {
	private static final Logger LOG = LoggerFactory.getLogger(QmDeleteGroupMemberAction.class);
	private String group;
	private ArrayList<String> memsDeleted;
	private boolean flag;

	/**
	 * <p>Constructor for GetGroupListAction.</p>
	 *
	 * @param context a {@link iqq.im.core.QQContext} object.
	 * @param listener a {@link iqq.im.QQActionListener} object.
	 */
	public QmDeleteGroupMemberAction(String group, ArrayList<String> memsDeleted,boolean flag,  QQContext context, QQActionListener listener) {
		super(context, listener);
		this.group = group;
		this.memsDeleted = memsDeleted;
		this.flag = flag;//1:不再接受加群申请；否则为0
	}

	/** {@inheritDoc} */
	@Override
	public QQHttpRequest onBuildRequest() throws QQException, JSONException {
//		gc:126658727
//		ul:1668008346|1127198273
//		flag:0
//		bkn:605178519
		

		HttpService httpService = (HttpService) getContext().getSerivce(QQService.Type.HTTP);
		Cookie skey = httpService.getCookie("skey", QQConstants.URL_QM_SEATCH_GROUP_MEMBERS);

		QQHttpRequest req = createHttpRequest("POST",
				QQConstants.URL_QM_DELETE_GROUP_MEMBER);
		req.addPostValue("gc", group);
		
		String ul="";
		if(memsDeleted!=null&&memsDeleted.size()>0){
			for (String  mem : memsDeleted) {
				ul+="|"+mem;
			}
		}
		req.addPostValue("ul", ul.substring(1));
		req.addPostValue("flag", flag?"1":"0");
		req.addPostValue("bkn", QQEncryptor.getBkn(skey.getValue()) + "");
		

		req.addHeader("Referer", "http://qun.qq.com/member.html");

		return req;
	}

	/** {@inheritDoc} */
	@Override
	protected void onHttpStatusOK(QQHttpResponse response) throws QQException,
			JSONException {
//		{"ec":0,"ul":[2414081615]}
		QQStore store = getContext().getStore();
		JSONObject json = new JSONObject(response.getResponseString());

		int returnCode = json.getInt("ec");
		if(returnCode==0){
			notifyActionEvent(QQActionEvent.Type.EVT_OK, null); 	
		}else{
			notifyActionEvent(QQActionEvent.Type.EVT_ERROR, response.getResponseString()); 	
		}
	}

}
