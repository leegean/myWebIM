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
import iqq.im.action.qun.QmGetCaptchaImageAction;
import iqq.im.action.qun.QmGetLoginSigAction;
import iqq.im.action.qun.QmWebLoginAction;
import iqq.im.action.weibo.WbGetVerifyImageAction;
import iqq.im.action.weibo.WbGetVerifyImageUrlAction;
import iqq.im.action.weibo.WbLoginAction;
import iqq.im.action.weibo.WbCheckVerifyAction;
import iqq.im.action.weibo.WbPollMsgAction;
import iqq.im.action.weibo.WbSendMsgAction;
import iqq.im.bean.QQStatus;
import iqq.im.bean.content.WbVerifyImage;
import iqq.im.core.QQModule;
import iqq.im.event.QQActionEvent;
import iqq.im.event.QQActionFuture;

/**
 *
 * 登录模块，处理登录和退出
 *
 * @author solosky
 */
public class WbLoginModule extends AbstractModule {
	
	public QQActionFuture login(QQActionListener listener, WbVerifyImage verifyImage){
		return pushHttpAction(new WbLoginAction(getContext(), listener, verifyImage));
	}
	
	
	public QQActionFuture checkVerify(QQActionListener listener){
		return pushHttpAction(new WbCheckVerifyAction(getContext(), listener));
	}
	public QQActionFuture getVerifyImageUrl(QQActionListener listener){
		return pushHttpAction(new WbGetVerifyImageUrlAction(getContext(), listener));
	}
	public QQActionFuture getVerifyImage(QQActionListener listener, WbVerifyImage verifyImage){
		return pushHttpAction(new WbGetVerifyImageAction(getContext(), listener, verifyImage));
	}
	public QQActionFuture sendMsg(QQActionListener listener, String msg, String acceptor){
		return pushHttpAction(new WbSendMsgAction(getContext(), listener, msg, acceptor));
	}
	public QQActionFuture pollMsg(QQActionListener listener, String acceptor){
		return pushHttpAction(new WbPollMsgAction(getContext(), listener, acceptor));
	}
}
