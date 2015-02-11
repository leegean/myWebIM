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
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 处理整体登陆逻辑
 * 
 * @author solosky
 */
public class WbProcModule extends AbstractModule {
	private static final Logger LOG = LoggerFactory.getLogger(WbProcModule.class);

	/**
	 * <p>
	 * login.
	 * </p>
	 * 
	 * @param listener
	 *            a {@link iqq.im.QQActionListener} object.
	 * @return a {@link iqq.im.event.QQActionFuture} object.
	 */
	public QQActionFuture loginWb(QQActionListener listener) {
		final ProcActionFuture future = new ProcActionFuture(listener, true);
		final WbLoginModule loginModule = (WbLoginModule) getContext().getModule(QQModule.Type.WB_LOGIN);
		loginModule.prelogin(new QQActionListener() {

			@Override
			public void onActionEvent(QQActionEvent event) {
				// TODO Auto-generated method stub
				if (event.getType() == QQActionEvent.Type.EVT_OK) {
					login(future);
				} else if (event.getType() == QQActionEvent.Type.EVT_ERROR) {
					future.notifyActionEvent(QQActionEvent.Type.EVT_ERROR, (QQException) event.getTarget());
				}

			}
		});
		return future;
	}
	public QQActionFuture login(final ProcActionFuture future) {
		final WbLoginModule loginModule = (WbLoginModule) getContext().getModule(QQModule.Type.WB_LOGIN);
		final long prelt = new Date().getTime() - getContext().getSession().getStarttime() - (getContext().getSession().getExectime() | 0);
		loginModule.login(new QQActionListener() {

			@Override
			public void onActionEvent(QQActionEvent event) {
				// TODO Auto-generated method stub
				if (event.getType() == QQActionEvent.Type.EVT_OK) {
					loginCallback(future);
				} else if (event.getType() == QQActionEvent.Type.EVT_ERROR) {
					future.notifyActionEvent(QQActionEvent.Type.EVT_ERROR, (QQException) event.getTarget());
				}

			}
		},prelt);
		return future;
	}
	public QQActionFuture loginCallback(final ProcActionFuture future) {
		final WbLoginModule loginModule = (WbLoginModule) getContext().getModule(QQModule.Type.WB_LOGIN);
		loginModule.loginCallback(new QQActionListener() {

			@Override
			public void onActionEvent(QQActionEvent event) {
				// TODO Auto-generated method stub
				if (event.getType() == QQActionEvent.Type.EVT_OK) {
					
					loginModule.webim(future);
				} else if (event.getType() == QQActionEvent.Type.EVT_ERROR) {
					future.notifyActionEvent(QQActionEvent.Type.EVT_ERROR, (QQException) event.getTarget());
				}

			}
		});
		return future;
	}
	public void doPollMsg() {
		final WbLoginModule login = getContext().getModule(QQModule.Type.WB_LOGIN);

		login.handshake(new QQActionListener() {
			public void onActionEvent(QQActionEvent event) {
				// 回调通知事件函数
				if (event.getType() == QQActionEvent.Type.EVT_OK) {

					System.out.println(event.getTarget());
					login.subscribe(new QQActionListener() {
						public void onActionEvent(QQActionEvent event) {
							// 回调通知事件函数
							if (event.getType() == QQActionEvent.Type.EVT_OK) {
								login.connectAdvice(new QQActionListener() {
									
									@Override
									public void onActionEvent(QQActionEvent event) {
										// TODO Auto-generated method stub
										if (event.getType() == QQActionEvent.Type.EVT_OK) {
											pollMsg();
										}
									}
								});
							}

						}
					});
				}else{
					System.out.println(event.getTarget());
				}

			}
		});

	}
	public void pollMsg(){
		final WbLoginModule login = getContext().getModule(QQModule.Type.WB_LOGIN);
		login.connect(new QQActionListener() {
			public void onActionEvent(QQActionEvent event) {
				// 回调通知事件函数
				if (event.getType() == QQActionEvent.Type.EVT_OK) {
						getContext().fireNotify(new QQNotifyEvent(QQNotifyEvent.Type.WeboChat, event.getTarget()));
					pollMsg();
				}

			}
		});
	}
	
	public QQActionFuture doSendMsg(QQActionListener listener, String msg) {
		final WbLoginModule login = getContext().getModule(QQModule.Type.WB_LOGIN);
		QQActionFuture future = login.sendMsg(listener, msg);
		return future;
		
	}
}
