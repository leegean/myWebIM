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
 * Created  : 2012-9-6
 * License  : Apache License 2.0 
 */
package iqq.im;

import iqq.im.actor.ThreadActorDispatcher;
import iqq.im.bean.QQAccount;
import iqq.im.bean.QQBuddy;
import iqq.im.bean.QQCategory;
import iqq.im.bean.QQDiscuz;
import iqq.im.bean.QQEmail;
import iqq.im.bean.QQGroup;
import iqq.im.bean.QQGroupSearchList;
import iqq.im.bean.QQMsg;
import iqq.im.bean.QQStatus;
import iqq.im.bean.content.*;
import iqq.im.core.QQConstants;
import iqq.im.event.QQActionEvent;
import iqq.im.event.QQActionEvent.Type;
import iqq.im.event.QQActionFuture;
import iqq.im.event.QQNotifyEvent;
import iqq.im.event.QQNotifyEventArgs;
import iqq.im.event.QQNotifyHandler;
import iqq.im.event.QQNotifyHandlerProxy;
import iqq.im.module.WbProcModule;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.core.net.server.Client;
import static iqq.im.event.QQActionEvent.Type.EVT_OK;

/**
 * Client测试类
 * 
 * @author solosky
 * 
 */
public class WebQQClientUiTest extends JFrame implements WindowListener {
	private static final Logger LOG = LoggerFactory.getLogger(WebQQClientUiTest.class);
	WebQQClient client;
	private JTextArea jta;
	private Timer sendTimer;

	public WebQQClientUiTest(String user, String pwd) {
		setLayout(new FlowLayout());
		sendTimer = new Timer();
		QQAccount account = new QQAccount();
		account.setWbUsername("569398403@qq.com");
		account.setWbPassword("leegean19861001");
		client = new WebQQClient(account, new QQNotifyHandlerProxy(this), new ThreadActorDispatcher());
		add(new JButton(new AbstractAction("login") {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				loginWb();
			}
		}));
		jta = new JTextArea(4, 20);
		add(jta);
		JButton sendBtn = new JButton(new AbstractAction("send") {

			int index = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				StringBuilder sb = null;
				try {
					Scanner scanner = new Scanner(new  File("js_wb/msg.txt"));
					sb = new StringBuilder();
					while (scanner.hasNextLine()) {
						String line = scanner.nextLine();
						sb.append(line);
					}
					scanner.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				final String[] sendMsg = sb.toString().split("[，。？；]");
				
				if (sendMsg.length > 0) {
							send(sendMsg);
				}
			}
			private void send(final String[] sendMsg) {
				final String msg = sendMsg[index];
				LOG.debug("send:   "+msg);
				client.sendWbMsg(msg, "5175429989", new QQActionListener() {

					@Override
					public void onActionEvent(QQActionEvent event) {
						// TODO Auto-generated method stub
						if (event.getType() == EVT_OK) {
							LOG.debug("发送成功");
							final Timer pollTimer = new Timer();

							pollTimer.schedule(new TimerTask() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									QQActionFuture future = client.pollWbMsg("5175429989", null);
									try {
										QQActionEvent event = future.waitFinalEvent();

										// TODO Auto-generated method stub
										if (event.getType() == EVT_OK) {
											JSONObject json = (JSONObject)event.getTarget();
//											System.out.println("pllmsg：   "+json);
											LOG.debug(json.toString());
											JSONArray data = json.optJSONArray("data");
											if(data!=null){
												JSONObject firstItem = data.optJSONObject(0);
												String firstText = firstItem.optString("text");
												boolean flag = false;
												for (int i = 0; i < data.length(); i++) {
													
													JSONObject item = data.optJSONObject(i);
													String senderId = item.optString("sender_id");
													if(senderId.equals("2645052603")){
														break;
													}
													String text = item.optString("text");
													if(i>0&&text.equals(msg)){
														flag = true;
														break;
													}
												}
												if(flag){
													LOG.debug("       pllmsg：   "+firstText);
													pollTimer.cancel();
													send(sendMsg);
												}
												
													
											}
											
										}
									
									} catch (QQException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}, 1000, 1000);

						}
					}
				});
				index++;
				if(index==sendMsg.length)index=0;
			}
		});
		
		
		
		add(sendBtn);

		add(new JButton(new AbstractAction("sendOne") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				final String msg = jta.getText().trim();
				if(msg.length()>0)
				client.sendWbMsg(msg, "5175429989", new QQActionListener() {

					@Override
					public void onActionEvent(QQActionEvent event) {
						// TODO Auto-generated method stub
						if (event.getType() == EVT_OK) {
							LOG.debug("发送成功");
							pollMsg(msg);

						}
					}

					private void pollMsg(final String msg) {
						final Timer pollTimer = new Timer();
						pollTimer.schedule(new TimerTask() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								QQActionFuture future = client.pollWbMsg("5175429989", null);
								try {
									QQActionEvent event1 = future.waitFinalEvent();

									// TODO Auto-generated method stub
									if (event1.getType() == EVT_OK) {
										JSONObject json = (JSONObject)event1.getTarget();
//											System.out.println("pllmsg：   "+json);
										LOG.debug(json.toString());
										JSONArray data = json.optJSONArray("data");
										JSONArray attachment = json.optJSONArray("attachment");
										if(attachment!=null){
											for (int i = 0; i < attachment.length(); i++) {
												JSONObject item = attachment.optJSONObject(i);
												String thumbnail600 = item.optString("thumbnail_600");
											}
										}
										if(data!=null){
											JSONObject firstItem = data.optJSONObject(0);
											String senderId = firstItem.optString("sender_id");
											if(senderId.equals("2645052603")){
												return;
											}
											String firstText = firstItem.optString("text");
											boolean flag = false;
											for (int i = 0; i < data.length(); i++) {
												
												JSONObject item = data.optJSONObject(i);
												
												String text = item.optString("text");
												if(i>0&&text.equals(msg)){
													flag = true;
													break;
												}
												
											}
											if(flag){
												pollTimer.cancel();
												pollTimer.purge();
												LOG.debug("       pllmsg：   "+firstText);
											}
										}
										
									}
								
								} catch (QQException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} 
							}
						}, 1000, 1000);
								
					}
				});
			}
		}));
		add(new JButton(new AbstractAction("getmsg") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
						client.pollWbMsg("5175429989", new QQActionListener() {
							
							@Override
							public void onActionEvent(QQActionEvent event) {
								// TODO Auto-generated method stub
								if (event.getType() == EVT_OK) {
									System.out.println("pllmsg：   "+event.getTarget());
								}
							}
						});
			}
		}));
		addWindowListener(this);
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * 程序入口
	 * 
	 */
	public static void main(String[] args) {
		WebQQClientUiTest test = new WebQQClientUiTest("569398403@qq.com", "leegean19861001");
		// test.loginWb();
	}

	public void loginWb() {
		client.preloginWb(new QQActionListener() {

			@Override
			public void onActionEvent(QQActionEvent event) {
				// TODO Auto-generated method stub
				if (event.getType() == EVT_OK) {
					// 到这里就算是登录成功了
					ArrayList<String> list = (ArrayList<String>) event.getTarget();
					for (String string : list) {
						System.out.println(string);
					}
					System.out.println("就算是登录成功微博了");

				} else {
					System.out.println(event.getTarget());
				}
			}
		});
	}

	/**
	 * 聊天消息通知，使用这个注解可以收到QQ消息
	 * 
	 * 接收到消息然后组装消息发送回去
	 * 
	 * @throws QQException
	 */
	@QQNotifyHandler(QQNotifyEvent.Type.CHAT_MSG)
	public void processBuddyMsg(QQNotifyEvent event) throws QQException {
		QQMsg msg = (QQMsg) event.getTarget();

		System.out.println("[消息] " + msg.getFrom().getNickname() + "说:" + msg.packContentList());
		System.out.print("消息内容: ");
		List<ContentItem> items = msg.getContentList();
		for (ContentItem item : items) {
			if (item.getType() == ContentItem.Type.FACE) {
				System.out.print(" Face:" + ((FaceItem) item).getId());
			} else if (item.getType() == ContentItem.Type.OFFPIC) {
				System.out.print(" Picture:" + ((OffPicItem) item).getFilePath());
			} else if (item.getType() == ContentItem.Type.TEXT) {
				System.out.print(" Text:" + ((TextItem) item).getContent());
			}
		}
		System.out.println();

		// 组装QQ消息发送回去
		QQMsg sendMsg = new QQMsg();
		sendMsg.setTo(msg.getFrom()); // QQ好友UIN
		sendMsg.setType(QQMsg.Type.BUDDY_MSG); // 发送类型为好友
		// QQ内容
		sendMsg.addContentItem(new TextItem("hello")); // 添加文本内容
		sendMsg.addContentItem(new FaceItem(0)); // QQ id为0的表情
		sendMsg.addContentItem(new FontItem()); // 使用默认字体
		client.sendMsg(sendMsg, null); // 调用接口发送消息
	}

	/**
	 * 被踢下线通知
	 * 
	 */
	@QQNotifyHandler(QQNotifyEvent.Type.KICK_OFFLINE)
	protected void processKickOff(QQNotifyEvent event) {
		System.out.println("被踢下线: " + (String) event.getTarget());
	}

	/**
	 * 需要验证码通知
	 * 
	 * @throws IOException
	 */
	@QQNotifyHandler(QQNotifyEvent.Type.CAPACHA_VERIFY)
	protected void processVerify(QQNotifyEvent event) throws IOException {
		QQNotifyEventArgs.ImageVerify verify = (QQNotifyEventArgs.ImageVerify) event.getTarget();
		ImageIO.write(verify.image, "png", new File("verify.png"));
		System.out.println(verify.reason);
		System.out.print("请输入在项目根目录下verify.png图片里面的验证码:");
		String code = new BufferedReader(new InputStreamReader(System.in)).readLine();
		client.submitVerify(code, event);
	}

	@QQNotifyHandler(QQNotifyEvent.Type.WB_CAPACHA_VERIFY)
	protected void processWbVerify(QQNotifyEvent event) throws IOException {
		WbVerifyImage verify = (WbVerifyImage) event.getTarget();
		ImageIO.write(verify.getImage(), "png", new File("verify.png"));
		System.out.print("请输入在项目根目录下verify.png图片里面的验证码:");
		String code = new BufferedReader(new InputStreamReader(System.in)).readLine();
		client.loginWb(verify.getFuture(), verify);
	}

	/**
	 * 登录
	 */
	public void login() {
		final QQActionListener listener = new QQActionListener() {
			public void onActionEvent(QQActionEvent event) {
				System.out.println("LOGIN_STATUS:" + event.getType() + ":" + event.getTarget());
				if (event.getType() == EVT_OK) {
					// 到这里就算是登录成功了

					// 获取下用户信息
					client.getUserInfo(client.getAccount(), new QQActionListener() {
						public void onActionEvent(QQActionEvent event) {
							System.out.println("LOGIN_STATUS:" + event.getType() + ":" + event.getTarget());
						}
					});

					// 获取好友列表..TODO.
					// 不一定调用，可能会有本地缓存
					client.getBuddyList(new QQActionListener() {

						@Override
						public void onActionEvent(QQActionEvent event) {
							// TODO Auto-generated method stub
							System.out.println("******** " + event.getType() + " ********");
							if (event.getType() == QQActionEvent.Type.EVT_OK) {
								System.out.println("******** 好友列表  ********");
								List<QQCategory> qqCategoryList = (List<QQCategory>) event.getTarget();

								for (QQCategory c : qqCategoryList) {
									System.out.println("分组名称:" + c.getName());
									List<QQBuddy> buddyList = c.getBuddyList();
									for (QQBuddy b : buddyList) {
										System.out.println("---- QQ nick:" + b.getNickname() + " markname:" + b.getMarkname() + " uin:" + b.getUin() + " isVip:" + b.isVip() + " vip_level:" + b.getVipLevel());
									}

								}
							} else if (event.getType() == QQActionEvent.Type.EVT_ERROR) {
								System.out.println("** 好友列表获取失败，处理重新获取");
							}
						}
					});
					// 获取群列表
					client.getGroupList(new QQActionListener() {

						@Override
						public void onActionEvent(QQActionEvent event) {
							if (event.getType() == EVT_OK) {
								for (QQGroup g : client.getGroupList()) {
									client.getGroupInfo(g, null);
									System.out.println("Group: " + g.getName());
								}
							} else if (event.getType() == QQActionEvent.Type.EVT_ERROR) {
								System.out.println("** 群列表获取失败，处理重新获取");
							}
						}
					});
					// 获取讨论组列表
					client.getDiscuzList(new QQActionListener() {

						@Override
						public void onActionEvent(QQActionEvent event) {
							if (event.getType() == EVT_OK) {
								for (QQDiscuz d : client.getDiscuzList()) {
									client.getDiscuzInfo(d, null);
									System.out.println("Discuz: " + d.getName());
								}
							}
						}
					});

					// 查群测试
					QQGroupSearchList list = new QQGroupSearchList();
					list.setKeyStr("QQ");
					client.searchGroupGetList(list, new QQActionListener() {
						@Override
						public void onActionEvent(QQActionEvent event) {
							if (event.getType() == EVT_OK) {

							}
						}
					});

					// 启动轮询时，需要获取所有好友、群成员、讨论组成员
					// 所有的逻辑完了后，启动消息轮询
					client.beginPollMsg();
				}
			}
		};

		String ua = "Mozilla/5.0 (@os.name; @os.version; @os.arch) AppleWebKit/537.36 (KHTML, like Gecko) @appName Safari/537.36";
		ua = ua.replaceAll("@appName", QQConstants.USER_AGENT);
		ua = ua.replaceAll("@os.name", System.getProperty("os.name"));
		ua = ua.replaceAll("@os.version", System.getProperty("os.version"));
		ua = ua.replaceAll("@os.arch", System.getProperty("os.arch"));
		client.setHttpUserAgent(ua);
		client.login(QQStatus.ONLINE, listener);
	}

	public void LoginQm() {

		final QQActionListener listener = new QQActionListener() {
			public void onActionEvent(QQActionEvent event) {
				System.out.println("LOGIN_STATUS:" + event.getType() + ":" + event.getTarget());
				if (event.getType() == EVT_OK) {
					// 到这里就算是登录成功了
					System.out.println("就算是登录成功了");

				}
			}
		};

		String ua = "Mozilla/5.0 (@os.name; @os.version; @os.arch) AppleWebKit/537.36 (KHTML, like Gecko) @appName Safari/537.36";
		ua = ua.replaceAll("@appName", QQConstants.USER_AGENT);
		ua = ua.replaceAll("@os.name", System.getProperty("os.name"));
		ua = ua.replaceAll("@os.version", System.getProperty("os.version"));
		ua = ua.replaceAll("@os.arch", System.getProperty("os.arch"));
		client.setHttpUserAgent(ua);
		client.loginQm(listener);
	}

	/**
	 * 新邮件通知
	 * 
	 * 这个暂时没有启用
	 * 
	 * @throws QQException
	 */
	@QQNotifyHandler(QQNotifyEvent.Type.EMAIL_NOTIFY)
	public void processEmailMsg(QQNotifyEvent event) throws QQException {
		List<QQEmail> list = (List<QQEmail>) event.getTarget();
		for (QQEmail mail : list) {
			System.out.println("邮件: " + mail.getSubject());
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("windowClosing");
		client.destroy();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("windowClosed");

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
