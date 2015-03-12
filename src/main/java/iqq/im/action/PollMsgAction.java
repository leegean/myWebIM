/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Project  : WebQQCoreAsync
 * Package  : iqq.im
 * File     : WebQQClientTest.java
 * Author   : solosky < solosky772@qq.com >
 * Created  : 2013-2-17
 * License  : Apache License 2.0 
 */
package iqq.im.action;

import iqq.im.QQActionListener;
import iqq.im.QQException;
import iqq.im.bean.QQBuddy;
import iqq.im.bean.QQClientType;
import iqq.im.bean.QQDiscuz;
import iqq.im.bean.QQDiscuzMember;
import iqq.im.bean.QQGroup;
import iqq.im.bean.QQGroupMember;
import iqq.im.bean.QQHalfStranger;
import iqq.im.bean.QQMsg;
import iqq.im.bean.QQStatus;
import iqq.im.bean.QQStranger;
import iqq.im.bean.QQUser;
import iqq.im.core.QQConstants;
import iqq.im.core.QQContext;
import iqq.im.core.QQSession;
import iqq.im.core.QQStore;
import iqq.im.event.QQActionEvent;
import iqq.im.event.QQNotifyEvent;
import iqq.im.http.QQHttpRequest;
import iqq.im.http.QQHttpResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

/**
 * 
 * 轮询Poll消息
 * 
 * @author solosky
 */
public class PollMsgAction extends AbstractHttpAction {

	private static final Logger LOG = LoggerFactory.getLogger(PollMsgAction.class);

	/**
	 * <p>
	 * Constructor for PollMsgAction.
	 * </p>
	 * 
	 * @param context
	 *            a {@link iqq.im.core.QQContext} object.
	 * @param listener
	 *            a {@link iqq.im.QQActionListener} object.
	 */
	public PollMsgAction(QQContext context, QQActionListener listener) {
		super(context, listener);
	}

	/** {@inheritDoc} */
	@Override
	protected QQHttpRequest onBuildRequest() throws QQException, JSONException {
		QQSession session = getContext().getSession();
		JSONObject json = new JSONObject();
		json.put("clientid", session.getClientId());
		json.put("psessionid", session.getSessionId());
		json.put("key", 0); // 暂时不知道什么用的
		json.put("ids", new JSONArray()); // 同上

		QQHttpRequest req = createHttpRequest("POST", QQConstants.URL_POLL_MSG);
		req.addPostValue("r", json.toString());
		req.addPostValue("clientid", session.getClientId() + "");
		req.addPostValue("psessionid", session.getSessionId());
		req.setReadTimeout(70 * 1000);
		req.setConnectTimeout(10 * 1000);
		req.addHeader("Referer", QQConstants.REFFER);

		return req;
	}

	/** {@inheritDoc} */
	@Override
	public void onHttpFinish(QQHttpResponse response) {
		// 如果返回的内容为空，认为这次pollMsg仍然成功
		if (response.getContentLength() == 0) {
			LOG.debug("PollMsgAction: empty response!!!!");
			notifyActionEvent(QQActionEvent.Type.EVT_OK, new ArrayList<QQNotifyEvent>());
		} else {
			super.onHttpFinish(response);
		}
	}

	/** {@inheritDoc} */
	@Override
	protected void onHttpStatusOK(QQHttpResponse response) throws QQException, JSONException {
		QQStore store = getContext().getStore();
		List<QQNotifyEvent> notifyEvents = new ArrayList<QQNotifyEvent>();
		JSONObject json = new JSONObject(response.getResponseString());
		int retcode = json.getInt("retcode");
		if (retcode == 0) {
			// 有可能为 {"retcode":0,"result":"ok"}
			if (!json.isNull("result") && json.get("result") instanceof JSONArray) {
				JSONArray results = json.getJSONArray("result");
				// 消息下载来的列表中是倒过来的，那我直接倒过来取，编位回来
				for (int i = results.length() - 1; i >= 0; i--) {
					JSONObject poll = results.getJSONObject(i);
					String pollType = poll.getString("poll_type");
					JSONObject pollData = poll.getJSONObject("value");
					if (pollType.equals("input_notify")) {
						long fromUin = pollData.getLong("from_uin");
						QQBuddy buddy = store.getBuddyByUin(fromUin);
						notifyEvents.add(new QQNotifyEvent(QQNotifyEvent.Type.BUDDY_INPUT, buddy));
					} else if (pollType.equals("message")) {
						// 好友消息
						notifyEvents.add(processBuddyMsg(pollData));
					} else if (pollType.equals("group_message")) {
						// 群消息
						// 被管理员禁言10分钟{"retcode":0,"result":[{"poll_type":"group_message","value":{"msg_id":20986,"from_uin":848492696,"to_uin":1002053815,"msg_id2":128032,"msg_type":43,"reply_ip":176756755,"group_code":2227416282,"send_uin":1900986400,"seq":221,"time":1419225537,"info_seq":126658727,"content":[["font",{"size":10,"color":"000000","style":[0,0,0],"name":"\u5B8B\u4F53"}],"\u674E\u519B(1002053815) \u88AB\u7BA1\u7406\u5458\u7981\u8A0010\u5206\u949F "]}}]}
						// 被管理员禁言1小时
						// {"retcode":0,"result":[{"poll_type":"group_message","value":{"msg_id":49336,"from_uin":848492696,"to_uin":1002053815,"msg_id2":198014,"msg_type":43,"reply_ip":176756884,"group_code":2227416282,"send_uin":1900986400,"seq":224,"time":1419225732,"info_seq":126658727,"content":[["font",{"size":10,"color":"000000","style":[0,0,0],"name":"\u5B8B\u4F53"}],"\u674E\u519B(1002053815) \u88AB\u7BA1\u7406\u5458\u7981\u8A001\u5C0F\u65F6 "]}}]}
						// 被管理员禁言解除
						// {"retcode":0,"result":[{"poll_type":"group_message","value":{"msg_id":60841,"from_uin":848492696,"to_uin":1002053815,"msg_id2":340248,"msg_type":43,"reply_ip":176498277,"group_code":2227416282,"send_uin":1900986400,"seq":223,"time":1419225681,"info_seq":126658727,"content":[["font",{"size":10,"color":"000000","style":[0,0,0],"name":"\u5B8B\u4F53"}],"\u674E\u519B(1002053815) \u88AB\u7BA1\u7406\u5458\u89E3\u9664\u7981\u8A00 "]}}]}
						// 群单纯图片消息
						// {"retcode":0,"result":[{"poll_type":"group_message","value":{"msg_id":14154,"from_uin":848492696,"to_uin":1002053815,"msg_id2":483355,"msg_type":43,"reply_ip":176489607,"group_code":2227416282,"send_uin":2388435354,"seq":226,"time":1419225894,"info_seq":126658727,"content":[["font",{"size":10,"color":"000000","style":[0,0,0],"name":"\u5FAE\u8F6F\u96C5\u9ED1"}],["cface",{"name":"{99820253-D7C7-8EDC-3F1E-53B08AE5C390}.jpg","file_id":2610173877,"key":"                ","server":"183.60.50.34:80"}]," "]}}]}
						// 群文字与表情消息
						// {"retcode":0,"result":[{"poll_type":"group_message","value":{"msg_id":14155,"from_uin":848492696,"to_uin":1002053815,"msg_id2":279741,"msg_type":43,"reply_ip":176886363,"group_code":2227416282,"send_uin":2388435354,"seq":227,"time":1419226485,"info_seq":126658727,"content":[["font",{"size":10,"color":"000000","style":[0,0,0],"name":"\u5FAE\u8F6F\u96C5\u9ED1"}],"aa",["face",14]," "]}}]}
						// 超级表情
						// {"retcode":0,"result":[{"poll_type":"group_message","value":{"msg_id":57841,"from_uin":2901943685,"to_uin":1002053815,"msg_id2":683745,"msg_type":32,"reply_ip":179898740,"group_code":1226655265,"t_gcode":260334785,"send_uin":569398403,"seq":19,"time":1425015233,"info_seq":37,"content":[""]}}]}

						notifyEvents.add(processGroupMsg(pollData));
					} else if (pollType.equals("discu_message")) {
						// 讨论组消息
						notifyEvents.add(processDiscuzMsg(pollData));
					} else if (pollType.equals("sess_message")) {
						// 临时会话消息
						notifyEvents.add(processSessionMsg(pollData));
					} else if (pollType.equals("shake_message")) {
						// 窗口震动
						long fromUin = pollData.getLong("from_uin");
						QQUser user = getContext().getStore().getBuddyByUin(fromUin);
						notifyEvents.add(new QQNotifyEvent(QQNotifyEvent.Type.SHAKE_WINDOW, user));
					} else if (pollType.equals("kick_message")) {
						// 被踢下线
						getContext().getAccount().setStatus(QQStatus.OFFLINE);
						getContext().getSession().setState(QQSession.State.KICKED);
						notifyEvents.add(new QQNotifyEvent(QQNotifyEvent.Type.KICK_OFFLINE, pollData.getString("reason")));
					} else if (pollType.equals("buddies_status_change")) {
						notifyEvents.add(processBuddyStatusChange(pollData));
					} else if (pollType.equals("group_web_message")) {
						// 点歌
						// {"retcode":0,"result":[{"poll_type":"group_web_message","value":{"msg_id":65528,"from_uin":2901943685,"to_uin":1002053815,"msg_id2":751439,"msg_type":45,"reply_ip":176886319,"group_code":1226655265,"group_type":1,"ver":1,"send_uin":3706930015,"xml":"\u0001\u0000\u0001 \u0000\t\u00D4 \u0000\u000F\u00C9\u00CB\u00D0\u00C4\u032B\u01BD\u00D1\u00F3-\u00C0\u00D7\u00E6\u00C3\u0000\u00EDtencent://miniplayer/?cmd=1\u0026fuin=569398403\u0026frienduin=569398403\u0026groupid=4150334785\u0026groupcode=260334785\u0026action=\u0027accept\u0027\u0026mdlurl=\u0027http://scenecgi.chatshow.qq.com/fcgi-bin/gm_qry_music_info.fcg?songcount=1\u0026songidlist=644128\u0026version=207\u0026cmd=1\u0027\u0000\u00EDtencent://miniplayer/?cmd=1\u0026fuin=569398403\u0026frienduin=569398403\u0026groupid=4150334785\u0026groupcode=260334785\u0026action=\u0027refuse\u0027\u0026mdlurl=\u0027http://scenecgi.chatshow.qq.com/fcgi-bin/gm_qry_music_info.fcg?songcount=1\u0026songidlist=644128\u0026version=207\u0026cmd=1\u0027"}}]}
						
					} else if (pollType.equals("system_message")) {
						
						// 其他人 添加你为好友
						// {"retcode":0,"result":[{"poll_type":"system_message","value":{"seq":49219,"type":"added_buddy_sig","uiuin":"","from_uin":2388435354,"account":569398403,"sig":"Ub\u008A\u00B8\u00D4\u0001\u008B\u0092Q\u001D\"*W\u00F6b\u00EA\u001E\u00F0\u009A\u0016gqb\u00C7","stat":20}}]}
					} else if (pollType.equals("sys_g_msg")) {
						// 设置你为群管理员
						// {"retcode":0,"result":[{"poll_type":"sys_g_msg","value":{"msg_id":58825,"from_uin":848492696,"to_uin":1002053815,"msg_id2":584526,"msg_type":44,"reply_ip":180061855,"type":"group_admin_op","gcode":2227416282,"t_gcode":126658727,"op_type":1,"uin":1002053815,"t_uin":1002053815,"uin_flag":1}}]}
						// 取消你为群管理员
						// {"retcode":0,"result":[{"poll_type":"sys_g_msg","value":{"msg_id":9211,"from_uin":848492696,"to_uin":1002053815,"msg_id2":265670,"msg_type":44,"reply_ip":176757008,"type":"group_admin_op","gcode":2227416282,"t_gcode":126658727,"op_type":0,"uin":1002053815,"t_uin":1002053815,"uin_flag":0}}]}
						// 设置群成员为群管理员
						// {"retcode":0,"result":[{"poll_type":"sys_g_msg","value":{"msg_id":40322,"from_uin":848492696,"to_uin":1002053815,"msg_id2":183897,"msg_type":44,"reply_ip":179406747,"type":"group_admin_op","gcode":2227416282,"t_gcode":126658727,"op_type":1,"uin":3904214993,"t_uin":2564781987,"uin_flag":1}}]}
						// 取消群成员为群管理员
//						{"retcode":0,"result":[{"poll_type":"sys_g_msg","value":{"msg_id":13045,"from_uin":848492696,"to_uin":1002053815,"msg_id2":15288,"msg_type":44,"reply_ip":176756443,"type":"group_admin_op","gcode":2227416282,"t_gcode":126658727,"op_type":0,"uin":3904214993,"t_uin":2564781987,"uin_flag":0}}]}

						// 你已经被移除群
						// {"retcode":0,"result":[{"poll_type":"sys_g_msg","value":{"msg_id":5515,"from_uin":848492696,"to_uin":1002053815,"msg_id2":634857,"msg_type":34,"reply_ip":176488602,"type":"group_leave","gcode":2227416282,"t_gcode":126658727,"op_type":3,"old_member":1002053815,"t_old_member":"","admin_uin":2388435354,"t_admin_uin":"","admin_nick":"\u521B\u5EFA\u8005"}}]}
						// 某人申请加入群
						// {"retcode":0,"result":[{"poll_type":"sys_g_msg","value":{"msg_id":9564,"from_uin":848492696,"to_uin":1002053815,"msg_id2":366710,"msg_type":35,"reply_ip":176884836,"type":"group_request_join","gcode":2227416282,"t_gcode":126658727,"request_uin":3904214993,"t_request_uin":"","msg":"gggggg"}}]}
					} else {
						LOG.warn("unknown pollType: " + pollType);
					}
				}
			}
			// end recode == 0
		} else if (retcode == 102) {
			// 接连正常，没有消息到达 {"retcode":102,"errmsg":""}
			// 继续进行下一个消息请求

		} else if (retcode == 110 || retcode == 109) { // 客户端主动退出
			getContext().getSession().setState(QQSession.State.OFFLINE);
		} else if (retcode == 116) {
			// 需要更新Ptwebqq值，暂时不知道干嘛用的
			// {"retcode":116,"p":"2c0d8375e6c09f2af3ce60c6e081bdf4db271a14d0d85060"}
			// if (a.retcode === 116) alloy.portal.setPtwebqq(a.p)
			getContext().getSession().setPtwebqq(json.getString("p"));
		} else if (retcode == 121 || retcode == 120 || retcode == 100) { // 121,120
																			// :
																			// ReLinkFailure
																			// 100
																			// :
																			// NotReLogin
			// 服务器需求重新认证
			// {"retcode":121,"t":"0"}
			LOG.info("**** NEED_REAUTH retcode: " + retcode + " ****");
			getContext().getSession().setState(QQSession.State.OFFLINE);
			QQException ex = new QQException(QQException.QQErrorCode.INVALID_LOGIN_AUTH);
			notifyActionEvent(QQActionEvent.Type.EVT_ERROR, ex);
			return;
			// notifyEvents.add(new
			// QQNotifyEvent(QQNotifyEvent.Type.NEED_REAUTH, null));
		} else {

			LOG.error("**Reply retcode to author**");
			LOG.error("***************************");
			LOG.error("Unknown retcode: " + retcode);
			LOG.error("***************************");
			// 返回错误，核心遇到未知recode
			// getContext().getSession().setState(QQSession.State.ERROR);
			notifyEvents.add(new QQNotifyEvent(QQNotifyEvent.Type.UNKNOWN_ERROR, json));
		}
		notifyActionEvent(QQActionEvent.Type.EVT_OK, notifyEvents);
	}

	/**
	 * <p>
	 * processBuddyStatusChange.
	 * </p>
	 * 
	 * @param pollData
	 *            a {@link org.json.JSONObject} object.
	 * @throws org.json.JSONException
	 *             if any.
	 * @return a {@link iqq.im.event.QQNotifyEvent} object.
	 */
	public QQNotifyEvent processBuddyStatusChange(JSONObject pollData) throws JSONException {
		long uin = pollData.getLong("uin");
		QQBuddy buddy = getContext().getStore().getBuddyByUin(uin);
		String status = pollData.getString("status");
		int clientType = pollData.getInt("client_type");
		buddy.setStatus(QQStatus.valueOfRaw(status));
		buddy.setClientType(QQClientType.valueOfRaw(clientType));
		return new QQNotifyEvent(QQNotifyEvent.Type.BUDDY_STATUS_CHANGE, buddy);
	}

	/**
	 * <p>
	 * processBuddyMsg.
	 * </p>
	 * 
	 * @param pollData
	 *            a {@link org.json.JSONObject} object.
	 * @throws org.json.JSONException
	 *             if any.
	 * @throws iqq.im.QQException
	 *             if any.
	 * @return a {@link iqq.im.event.QQNotifyEvent} object.
	 */
	public QQNotifyEvent processBuddyMsg(JSONObject pollData) throws JSONException, QQException {
		QQStore store = getContext().getStore();

		long fromUin = pollData.getLong("from_uin");
		QQMsg msg = new QQMsg();
		msg.setId(pollData.getLong("msg_id"));
		msg.setId2(pollData.getLong("msg_id2"));
		msg.parseContentList(pollData.getJSONArray("content").toString());
		msg.setType(QQMsg.Type.BUDDY_MSG);
		msg.setTo(getContext().getAccount());
		msg.setFrom(store.getBuddyByUin(fromUin));
		msg.setDate(new Date(pollData.getLong("time") * 1000));
		if (msg.getFrom() == null) {
			QQUser member = store.getStrangerByUin(fromUin); // 搜索陌生人列表
			if (member == null) {
				member = new QQHalfStranger();
				member.setUin(fromUin);
				store.addStranger((QQStranger) member);
			}
			msg.setFrom(member);
		}

		return new QQNotifyEvent(QQNotifyEvent.Type.CHAT_MSG, msg);
	}

	/**
	 * <p>
	 * processGroupMsg.
	 * </p>
	 * 
	 * @param pollData
	 *            a {@link org.json.JSONObject} object.
	 * @throws org.json.JSONException
	 *             if any.
	 * @throws iqq.im.QQException
	 *             if any.
	 * @return a {@link iqq.im.event.QQNotifyEvent} object.
	 */
	public QQNotifyEvent processGroupMsg(JSONObject pollData) throws JSONException, QQException {
		// {"retcode":0,"result":[{"poll_type":"group_message",
		// "value":{"msg_id":6175,"from_uin":3924684389,"to_uin":1070772010,"msg_id2":992858,"msg_type":43,"reply_ip":176621921,
		// "group_code":3439321257,"send_uin":1843694270,"seq":875,"time":1365934781,"info_seq":170125666,"content":[["font",{"size":10,"color":"3b3b3b","style":[0,0,0],"name":"\u5FAE\u8F6F\u96C5\u9ED1"}],"eeeeeeeee "]}}]}

		QQStore store = getContext().getStore();
		QQMsg msg = new QQMsg();
		msg.setId(pollData.getLong("msg_id"));
		msg.setId2(pollData.getLong("msg_id2"));
		int msgType = pollData.getInt("msg_type");
		long gin = pollData.getLong("from_uin");
		long fromUin = pollData.getLong("send_uin");
		long groupCode = pollData.getLong("group_code");
		QQGroup group = store.getGroupByCode(groupCode);
		long groupID= -1;
		if(group==null){
			group = new QQGroup();
			group.setGin(gin);
            group.setCode(groupCode);
            // put to store
            store.addGroup(group);
		}
		switch (msgType) {
		case 32:{
			// 魔法、超级、涂鸦表情
			groupID = pollData.getLong("t_gcode"); // 真实群号码
			break;
		}
		case 43:
			// 一般消息
			groupID = pollData.getLong("info_seq"); // 真实群号码

			
			msg.parseContentList(pollData.getJSONArray("content").toString());
			msg.setType(QQMsg.Type.GROUP_MSG);
			msg.setDate(new Date(pollData.getLong("time") * 1000));
			break;
		default:
			break;
		}
		if (group.getGid() <= 0) {
			group.setGid(groupID);
		}
		msg.setGroup(group);
		if (group != null) {
			msg.setFrom(group.getMemberByUin(fromUin));
		}
		msg.setTo(getContext().getAccount());
		if (msg.getFrom() == null) {
			QQGroupMember member = new QQGroupMember();
			member.setUin(fromUin);
			msg.setFrom(member);
			if (group != null) {
				group.getMembers().add(member);
			}
		}

		return new QQNotifyEvent(QQNotifyEvent.Type.CHAT_MSG, msg);
	}

	/**
	 * <p>
	 * processDiscuzMsg.
	 * </p>
	 * 
	 * @param pollData
	 *            a {@link org.json.JSONObject} object.
	 * @throws org.json.JSONException
	 *             if any.
	 * @throws iqq.im.QQException
	 *             if any.
	 * @return a {@link iqq.im.event.QQNotifyEvent} object.
	 */
	public QQNotifyEvent processDiscuzMsg(JSONObject pollData) throws JSONException, QQException {
		QQStore store = getContext().getStore();

		QQMsg msg = new QQMsg();
		long fromUin = pollData.getLong("send_uin");
		long did = pollData.getLong("did");

		msg.parseContentList(pollData.getJSONArray("content").toString());
		msg.setType(QQMsg.Type.DISCUZ_MSG);
		msg.setDiscuz(store.getDiscuzByDid(did));
		msg.setTo(getContext().getAccount());
		msg.setDate(new Date(pollData.getLong("time") * 1000));

		if (msg.getDiscuz() != null) {
			msg.setFrom(msg.getDiscuz().getMemberByUin(fromUin));
		}

		if (msg.getFrom() == null) {
			QQDiscuzMember member = new QQDiscuzMember();
			member.setUin(fromUin);
			msg.setFrom(member);
			if (msg.getDiscuz() != null) {
				msg.getDiscuz().getMembers().add(member);
			}
		}
		return new QQNotifyEvent(QQNotifyEvent.Type.CHAT_MSG, msg);
	}

	/**
	 * <p>
	 * processSessionMsg.
	 * </p>
	 * 
	 * @param pollData
	 *            a {@link org.json.JSONObject} object.
	 * @throws org.json.JSONException
	 *             if any.
	 * @throws iqq.im.QQException
	 *             if any.
	 * @return a {@link iqq.im.event.QQNotifyEvent} object.
	 */
	public QQNotifyEvent processSessionMsg(JSONObject pollData) throws JSONException, QQException {
		// {"retcode":0,"result":[{"poll_type":"sess_message",
		// "value":{"msg_id":25144,"from_uin":167017143,"to_uin":1070772010,"msg_id2":139233,"msg_type":140,"reply_ip":176752037,"time":1365931836,"id":2581801127,"ruin":444674479,"service_type":1,
		// "flags":{"text":1,"pic":1,"file":1,"audio":1,"video":1},"content":[["font",{"size":9,"color":"000000","style":[0,0,0],"name":"Tahoma"}],"2\u8F7D3 ",["face",1]," "]}}]}
		QQStore store = getContext().getStore();

		QQMsg msg = new QQMsg();
		long fromUin = pollData.getLong("from_uin");
		long fromQQ = pollData.getLong("ruin"); // 真实QQ
		int serviceType = pollData.getInt("service_type"); // Group:0,Discuss:1
		long typeId = pollData.getLong("id"); // Group ID or Discuss ID

		msg.parseContentList(pollData.getJSONArray("content").toString());
		msg.setType(QQMsg.Type.SESSION_MSG);
		msg.setTo(getContext().getAccount());
		msg.setDate(new Date(pollData.getLong("time") * 1000));

		QQUser user = store.getBuddyByUin(fromUin); // 首先看看是不是自己的好友
		if (user != null) {
			msg.setType(QQMsg.Type.BUDDY_MSG); // 是自己的好友
		} else {
			if (serviceType == 0) { // 是群成员
				QQGroup group = store.getGroupByCode(typeId);
				for (QQUser u : group.getMembers()) {
					if (u.getUin() == fromUin) {
						user = u;
						break;
					}
				}
			} else if (serviceType == 1) { // 是讨论组成员
				QQDiscuz discuz = store.getDiscuzByDid(typeId);
				for (QQUser u : discuz.getMembers()) {
					if (u.getUin() == fromUin) {
						user = u;
						break;
					}
				}
			} else {
				user = store.getStrangerByUin(fromUin); // 看看陌生人列表中有木有
			}
			if (user == null) { // 还没有就新建一个陌生人，原理来说不应该这样。后面我就不知道怎么回复这消息了，但是消息是不能丢失的
				user = new QQStranger();
				user.setQQ(pollData.getLong("ruin"));
				user.setUin(fromUin);
				user.setNickname(pollData.getLong("ruin") + "");
				store.addStranger((QQStranger) user);
			}
		}
		user.setQQ(fromQQ); // 带上QQ号码
		msg.setFrom(user);
		return new QQNotifyEvent(QQNotifyEvent.Type.CHAT_MSG, msg);
	}
}
