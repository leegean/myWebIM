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
import iqq.im.bean.content.WbVerifyImage;
import iqq.im.core.QQModule;
import iqq.im.core.QQSession;
import iqq.im.event.QQActionEvent;
import iqq.im.event.QQActionEventArgs;
import iqq.im.event.QQActionFuture;
import iqq.im.event.QQNotifyEvent;
import iqq.im.event.QQNotifyEventArgs;
import iqq.im.event.future.ProcActionFuture;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
	public QQActionFuture prelogin(QQActionListener listener) {
		final ProcActionFuture future = new ProcActionFuture(listener, true);
		final WbLoginModule loginModule = (WbLoginModule) getContext().getModule(QQModule.Type.WB_LOGIN);
		loginModule.checkVerify(new QQActionListener() {

			@Override
			public void onActionEvent(QQActionEvent event) {
				// TODO Auto-generated method stub
				if (event.getType() == QQActionEvent.Type.EVT_OK) {
					boolean needVerify = (Boolean) event.getTarget();
					if (needVerify) {
						getVerifyImageUrl(future);
					} else {
						login(future, null);
					}
				} else if (event.getType() == QQActionEvent.Type.EVT_ERROR) {
					System.out.println("======prelogin出错=======");
					future.notifyActionEvent(QQActionEvent.Type.EVT_ERROR,  event.getTarget());
				}

			}
		});
		return future;
	}

	public QQActionFuture login(final ProcActionFuture future,WbVerifyImage verifyImage) {
		// TODO Auto-generated method stub
		final WbLoginModule loginModule = (WbLoginModule) getContext().getModule(QQModule.Type.WB_LOGIN);
		return loginModule.login(new QQActionListener() {

			@Override
			public void onActionEvent(QQActionEvent event) {
				// TODO Auto-generated method stub
				if (event.getType() == QQActionEvent.Type.EVT_OK) {
					ArrayList<String> crossdomainlist = (ArrayList<String>)event.getTarget();
					future.notifyActionEvent(QQActionEvent.Type.EVT_OK, crossdomainlist);
				} else if (event.getType() == QQActionEvent.Type.EVT_ERROR) {
					future.notifyActionEvent(QQActionEvent.Type.EVT_ERROR, event.getTarget());
				}
			}
		},verifyImage);
	}

	protected void getVerifyImageUrl(final ProcActionFuture future) {
		// TODO Auto-generated method stub
		final WbLoginModule loginModule = (WbLoginModule) getContext().getModule(QQModule.Type.WB_LOGIN);
		loginModule.getVerifyImageUrl(new QQActionListener() {

			@Override
			public void onActionEvent(QQActionEvent event) {
				// TODO Auto-generated method stub
				if (event.getType() == QQActionEvent.Type.EVT_OK) {
					WbVerifyImage verifyImage = (WbVerifyImage) event.getTarget();
					getVerifyImage(future, verifyImage);
				} else if (event.getType() == QQActionEvent.Type.EVT_ERROR) {
					future.notifyActionEvent(QQActionEvent.Type.EVT_ERROR, event.getTarget());
				}
			}
		});
	}

	protected void getVerifyImage(final ProcActionFuture future, WbVerifyImage verifyImage) {
		// TODO Auto-generated method stub
		final WbLoginModule loginModule = (WbLoginModule) getContext().getModule(QQModule.Type.WB_LOGIN);
		loginModule.getVerifyImage(new QQActionListener() {

			@Override
			public void onActionEvent(QQActionEvent event) {
				// TODO Auto-generated method stub
				if (event.getType() == QQActionEvent.Type.EVT_OK) {
					BufferedImage image = (BufferedImage) event.getTarget();
					getContext().fireNotify(new QQNotifyEvent(QQNotifyEvent.Type.WB_CAPACHA_VERIFY, image));
				} else if (event.getType() == QQActionEvent.Type.EVT_ERROR) {
					future.notifyActionEvent(QQActionEvent.Type.EVT_ERROR, event.getTarget());
				}
			}
		},verifyImage);
	}

	public QQActionFuture sendMsg(QQActionListener listener, String msg, String acceptor) {
		final ProcActionFuture future = new ProcActionFuture(listener, true);
		final WbLoginModule loginModule = (WbLoginModule) getContext().getModule(QQModule.Type.WB_LOGIN);
		loginModule.sendMsg(new QQActionListener() {

			@Override
			public void onActionEvent(QQActionEvent event) {
				// TODO Auto-generated method stub
				if (event.getType() == QQActionEvent.Type.EVT_OK) {
					future.notifyActionEvent(QQActionEvent.Type.EVT_OK, event.getTarget());
				} else if (event.getType() == QQActionEvent.Type.EVT_ERROR) {
					System.out.println("======prelogin出错=======");
					future.notifyActionEvent(QQActionEvent.Type.EVT_ERROR,  event.getTarget());
				}
			}
		},msg, acceptor);
		return future;
	}
	public QQActionFuture pollMsg(QQActionListener listener, String acceptor) {
		final ProcActionFuture future = new ProcActionFuture(listener, true);
		final WbLoginModule loginModule = (WbLoginModule) getContext().getModule(QQModule.Type.WB_LOGIN);
		loginModule.pollMsg(new QQActionListener() {

			@Override
			public void onActionEvent(QQActionEvent event) {
				// TODO Auto-generated method stub
				if (event.getType() == QQActionEvent.Type.EVT_OK) {
					future.notifyActionEvent(QQActionEvent.Type.EVT_OK, event.getTarget());
				} else if (event.getType() == QQActionEvent.Type.EVT_ERROR) {
					System.out.println("======prelogin出错=======");
					future.notifyActionEvent(QQActionEvent.Type.EVT_ERROR,  event.getTarget());
				}
			}
		}, acceptor);
		return future;
	}
}
