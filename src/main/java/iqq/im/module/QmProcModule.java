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
 * Package  : iqq.im.module
 * File     : ProcModule.java
 * Author   : solosky < solosky772@qq.com >
 * Created  : 2012-9-2
 * License  : Apache License 2.0 
 */
package iqq.im.module;

import iqq.im.QQActionListener;
import iqq.im.QQException;
import iqq.im.QQException.QQErrorCode;
import iqq.im.bean.QQAccount;
import iqq.im.bean.QQStatus;
import iqq.im.core.QQModule;
import iqq.im.core.QQSession;
import iqq.im.event.QQActionEvent;
import iqq.im.event.QQActionEventArgs;
import iqq.im.event.QQActionFuture;
import iqq.im.event.QQNotifyEvent;
import iqq.im.event.QQNotifyEventArgs;
import iqq.im.event.future.ProcActionFuture;

import java.awt.image.BufferedImage;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 处理整体登陆逻辑
 *
 * @author solosky
 */
public class QmProcModule extends AbstractModule {
	private static final Logger LOG = LoggerFactory.getLogger(QmProcModule.class);
	/**
	 * <p>login.</p>
	 *
	 * @param listener a {@link iqq.im.QQActionListener} object.
	 * @return a {@link iqq.im.event.QQActionFuture} object.
	 */
	public QQActionFuture login(QQActionListener listener) {
		ProcActionFuture future = new ProcActionFuture(listener, true);
		doGetLoginSig(future);
		return future;
	}
	
	/**
	 * <p>loginWithVerify.</p>
	 *
	 * @param verifyCode a {@link java.lang.String} object.
	 * @param future a {@link iqq.im.event.future.ProcActionFuture} object.
	 * @return a {@link iqq.im.event.QQActionFuture} object.
	 */
	public QQActionFuture loginWithVerify(String verifyCode, ProcActionFuture future) {
		doWebLogin(verifyCode, future);
		return future;
	}
	
	
	private void doGetLoginSig(final ProcActionFuture future){
		QmLoginModule login = (QmLoginModule) getContext().getModule(QQModule.Type.QM_LOGIN);
		login.getLoginSig(new QQActionListener() {
			@Override
			public void onActionEvent(QQActionEvent event) {
				if(event.getType()==QQActionEvent.Type.EVT_OK){
					doCheckVerify(future);
				}else if(event.getType()==QQActionEvent.Type.EVT_ERROR){
					future.notifyActionEvent(
							QQActionEvent.Type.EVT_ERROR,
							(QQException) event.getTarget());
				}
			}
		});
	}
	
	private void doGetVerify(final String reason, final ProcActionFuture future){
		if (future.isCanceled()) {
			return;
		}
		QQAccount account = getContext().getAccount();
		QmLoginModule login = (QmLoginModule) getContext().getModule(QQModule.Type.QM_LOGIN);
		login.getCaptcha(new QQActionListener() {
			public void onActionEvent(QQActionEvent event) {
				if(event.getType()==QQActionEvent.Type.EVT_OK){
					QQNotifyEventArgs.ImageVerify verify = new QQNotifyEventArgs.ImageVerify();
					
					verify.type = QQNotifyEventArgs.ImageVerify.VerifyType.LOGIN;
					verify.image = (BufferedImage) event.getTarget();
					verify.reason = reason;
					verify.future = future;
					
					getContext().fireNotify(new QQNotifyEvent(QQNotifyEvent.Type.CAPACHA_VERIFY, verify));
				}else if(event.getType()==QQActionEvent.Type.EVT_ERROR){
					future.notifyActionEvent(
							QQActionEvent.Type.EVT_ERROR,
							(QQException) event.getTarget());
				}
			}
		});
		
		
	}

	private void doCheckVerify(final ProcActionFuture future) {
		if (future.isCanceled()) {
			return;
		}

		QmLoginModule login = getContext().getModule(QQModule.Type.QM_LOGIN);
		final QQAccount account = getContext().getAccount();
		login.checkVerify(new QQActionListener() {
			public void onActionEvent(QQActionEvent event) {
				if (event.getType() == QQActionEvent.Type.EVT_OK) {
					QQActionEventArgs.CheckVerifyArgs args = 
						(QQActionEventArgs.CheckVerifyArgs) (event.getTarget());
					account.setUin(args.uin);
					if (args.result == 0) {
						doWebLogin(args.code, future);
					} else {
						doGetVerify("为了保证您账号的安全，请输入验证码中字符继续登录。", future);
					}
				}else if(event.getType() == QQActionEvent.Type.EVT_ERROR){
					future.notifyActionEvent(
							QQActionEvent.Type.EVT_ERROR,
							(QQException) event.getTarget());
				}

			}
		});
	}

	private void doWebLogin(String verifyCode, final ProcActionFuture future) {
		QmLoginModule login =  getContext().getModule(QQModule.Type.QM_LOGIN);
		QQAccount account = getContext().getAccount();
		login.webLogin(account.getUsername(), account.getPassword(),
				account.getUin(), verifyCode, new QQActionListener() {
					public void onActionEvent(QQActionEvent event) {
						if (event.getType() == QQActionEvent.Type.EVT_OK) {
							future.notifyActionEvent(QQActionEvent.Type.EVT_OK, null);
						} else if (event.getType() == QQActionEvent.Type.EVT_ERROR) {
							QQException ex = (QQException) (event.getTarget());
							if(ex.getError()==QQErrorCode.WRONG_CAPTCHA){
								doGetVerify(ex.getMessage(), future);
							}else{
								future.notifyActionEvent(
										QQActionEvent.Type.EVT_ERROR,
										(QQException) event.getTarget());
							}
						}
					}
				});
	}
	


}
