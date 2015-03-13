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
 * Package  : iqq.im.core
 * File     : QQEventDispatcher.java
 * Author   : solosky < solosky772@qq.com >
 * Created  : 2012-8-2
 * License  : Apache License 2.0 
 */
package iqq.im.actor;

import iqq.im.QQActionListener;
import iqq.im.QQException;
import iqq.im.WebQQClient;
import iqq.im.bean.QQGroup;
import iqq.im.bean.QQMsg;
import iqq.im.bean.content.ContentItem;
import iqq.im.bean.content.FontItem;
import iqq.im.bean.content.TextItem;
import iqq.im.bean.content.ContentItem.Type;
import iqq.im.core.QQContext;
import iqq.im.core.QQService;
import iqq.im.event.QQActionEvent;
import iqq.im.event.QQActionFuture;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 单线程的QQ内部分发器，可以同时使用多个QQ实例里
 *
 * @author solosky
 */
public class ThreadMsgDispatcher_01 implements Runnable {
	private static final Logger LOG = LoggerFactory.getLogger(ThreadMsgDispatcher_01.class);
	private BlockingDeque<QQMsg> msgDeque;
	private WebQQClient webQQClient;
	private Thread msgThread;

	/**
	 * 默认构造函数，不会自动启动action循环
	 */
	public ThreadMsgDispatcher_01() {
		this.msgDeque = new LinkedBlockingDeque<QQMsg>();
		this.msgMap = new HashMap<Long, LinkedBlockingDeque<QQMsg>>();
	}

	private HashMap<Long, LinkedBlockingDeque<QQMsg>> msgMap;
	public void pushActor(QQMsg msg) {
		
		QQGroup group = msg.getGroup();
		Long gin = group.getGin();
		LinkedBlockingDeque<QQMsg> deque = msgMap.get(gin);
		if(deque== null){
			deque = new LinkedBlockingDeque<QQMsg>();
			msgMap.put(gin, deque);
		}
		deque.offer(msg);
		msgDeque.offer(msg);
	}

	/**
	 * 执行一个QQActor，返回是否继续下一个actor
	 */
	private boolean dispatchMsg(QQMsg msg) {
//		Set<Entry<Long, LinkedBlockingDeque<QQMsg>>> entries = msgMap.entrySet();
//		for (Entry<Long, LinkedBlockingDeque<QQMsg>> entry : entries) {
//			Long gin = entry.getKey();
//			LinkedBlockingDeque<QQMsg> deque = entry.getValue();
//		}
		try {
//			final QQMsg reqMsg = msg;
//			if(msgDeque.size()>=4){
//				msg = msgDeque.takeLast();
//				msgDeque.clear();
//			}
		final QQMsg reqMsg = msg;
			QQActionFuture future = webQQClient.sendWbMsg(reqMsg.getText(), "5175429989", null);
			QQActionEvent event = future.waitFinalEvent();
			if (event.getType() == QQActionEvent.Type.EVT_OK) {
				String chatContent = (String) event.getTarget();
				chatContent = chatContent.replace("小冰", "诗月");
				chatContent = chatContent.replace("微软", "诗月");
				List<ContentItem> contentList = reqMsg.getContentList();
				for (ContentItem contentItem : contentList) {
					if(contentItem.getType() == Type.TEXT){
						((TextItem)contentItem).setContent(chatContent);
					}
				}
//				reqMsg.addContentItem(new TextItem); // 添加文本内容
				webQQClient.sendMsg(reqMsg, null); // 调用接口发送消息
				
			}else if(event.getType() == QQActionEvent.Type.EVT_ERROR){
				LOG.debug("微博消息发送失败！", event.getTarget());
			}
		} catch (Throwable e) {
			LOG.warn("QQActor dispatchAction Error!", e);
		}
		return !(msg.getType() == QQMsg.Type.EXIT_MSG);
	}

	/** {@inheritDoc} */
	@Override
	public void run() {
		try {
			LOG.debug("QQActorDispatcher enter action loop...");
			while (dispatchMsg(this.msgDeque.take())) {
			}
			LOG.debug("QQActorDispatcher leave action loop...");
		} catch (InterruptedException e) {
			LOG.debug("QQActorDispatcher interrupted.");
		}
		
	}

	public void init(WebQQClient webQQClient) throws QQException {
		this.webQQClient = webQQClient;
		msgDeque.clear();
		msgThread = new Thread(this);
		msgThread.setName("QQMsgDispatcher");
		msgThread.start();
	}

	public void destroy() throws QQException {
		QQMsg msg = new QQMsg();
		msg.setType(QQMsg.Type.EXIT_MSG);
		pushActor(msg);
		try {
			if (Thread.currentThread() != msgThread) {
				msgThread.join();
			}
		} catch (InterruptedException e) {
			throw new QQException(QQException.QQErrorCode.UNKNOWN_ERROR, e);
		}
	}

}
