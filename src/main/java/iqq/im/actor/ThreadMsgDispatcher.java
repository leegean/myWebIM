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


import iqq.im.QQException;
import iqq.im.bean.QQMsg;
import iqq.im.core.QQContext;
import iqq.im.core.QQService;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 单线程的QQ内部分发器，可以同时使用多个QQ实例里
 *
 * @author solosky
 */
public class ThreadMsgDispatcher implements  Runnable {
	private static final Logger LOG = LoggerFactory.getLogger(ThreadMsgDispatcher.class);
	private BlockingQueue<QQMsg> msgQueue;
	private Thread msgThread;

	/**
	 * 默认构造函数，不会自动启动action循环
	 */
	public ThreadMsgDispatcher(){
		this.msgQueue = new LinkedBlockingQueue<QQMsg>();
	}
	
	public void pushActor(QQMsg msg){
		this.msgQueue.add(msg);
	}
	
	/**
	 * 执行一个QQActor，返回是否继续下一个actor
	 */
	private boolean dispatchMsg(QQMsg msg){
		try {

		
		} catch (Throwable e) {
			LOG.warn("QQActor dispatchAction Error!", e);
		}
		return !(msg.getType()==QQMsg.Type.EXIT_MSG);
	}
	
	/** {@inheritDoc} */
	@Override
	public void run(){
		try {
			LOG.debug("QQActorDispatcher enter action loop...");
			while(dispatchMsg(this.msgQueue.take())){}
			LOG.debug("QQActorDispatcher leave action loop...");
		} catch (InterruptedException e) {
			LOG.debug("QQActorDispatcher interrupted.");
		}
	}

	public void init() throws QQException {
		
		msgQueue.clear();
		msgThread = new Thread(this);
		msgThread.setName("QQMsgDispatcher");
		msgThread.start();
	}

	public void destroy() throws QQException {
		QQMsg msg = new QQMsg();
		msg.setType(QQMsg.Type.EXIT_MSG);
		pushActor(msg);
		try {
			if(Thread.currentThread() != msgThread){
				msgThread.join();
			}
		} catch (InterruptedException e) {
			throw new QQException(QQException.QQErrorCode.UNKNOWN_ERROR, e);
		}
	}
	
	
	
}
