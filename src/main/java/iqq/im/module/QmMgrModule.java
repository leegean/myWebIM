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
 * Project  : WebQQCore
 * Package  : iqq.im.module
 * File     : CoreModule.java
 * Author   : solosky < solosky772@qq.com >
 * Created  : 2012-7-31
 * License  : Apache License 2.0 
 */
package iqq.im.module;

import java.util.ArrayList;

import iqq.im.QQActionListener;
import iqq.im.action.ChannelLoginAction;
import iqq.im.action.CheckLoginSigAction;
import iqq.im.action.CheckVerifyAction;
import iqq.im.action.GetCaptchaImageAction;
import iqq.im.action.GetLoginSigAction;
import iqq.im.action.PollMsgAction;
import iqq.im.action.WebLoginAction;
import iqq.im.action.WebLogoutAction;
import iqq.im.action.qun.QmCheckVerifyAction;
import iqq.im.action.qun.QmDeleteGroupMemberAction;
import iqq.im.action.qun.QmGetCaptchaImageAction;
import iqq.im.action.qun.QmGetLoginSigAction;
import iqq.im.action.qun.QmSearchGroupMembersAction;
import iqq.im.action.qun.QmSetGroupCardAction;
import iqq.im.action.qun.QmWebLoginAction;
import iqq.im.bean.QQStatus;
import iqq.im.bean.QmMemSearchCondition;
import iqq.im.core.QQContext;
import iqq.im.core.QQModule;
import iqq.im.event.QQActionEvent;
import iqq.im.event.QQActionFuture;

/**
 *
 * 登录模块，处理登录和退出
 *
 * @author solosky
 */
public class QmMgrModule extends AbstractModule {
	
	public QQActionFuture deleteGroupMember(String group, ArrayList<String> memsDeleted,boolean flag,   QQActionListener listener){
		return pushHttpAction(new QmDeleteGroupMemberAction(group, memsDeleted, flag, getContext(), listener));
	}
	
	public QQActionFuture searchGroupMember(QmMemSearchCondition condition,  QQActionListener listener){
		return pushHttpAction(new QmSearchGroupMembersAction(condition, getContext(), listener));
	}
	
	public QQActionFuture setGroupCard(String group, String uin,String card, QQActionListener listener){
		return pushHttpAction(new QmSetGroupCardAction(group, uin, card, getContext(), listener));
	}
	
	
}
