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
import iqq.im.bean.QmGroupMembers;
import iqq.im.bean.QmMemSearchCondition;
import iqq.im.bean.content.*;
import iqq.im.core.QQConstants;
import iqq.im.event.QQActionEvent;
import iqq.im.event.QQActionEvent.Type;
import iqq.im.event.QQActionFuture;
import iqq.im.event.QQNotifyEvent;
import iqq.im.event.QQNotifyEventArgs;
import iqq.im.event.QQNotifyHandler;
import iqq.im.event.QQNotifyHandlerProxy;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static iqq.im.event.QQActionEvent.Type.EVT_OK;

public class WebQQClientUiTest extends JFrame implements WindowListener {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(WebQQClientUiTest.class);
	WebQQClient client;
	private JTextArea jta;
	private JTextField jtf;
	private JTextField jtfGc;
	private JTextField jtfUin;
	private JTextField jtfCard;
	private JRadioButton jrb;

	@SuppressWarnings("serial")
	public WebQQClientUiTest(String user, String pwd, String wbUser, String wbPwd) {
		setLayout(new FlowLayout());
		QQAccount account = new QQAccount();
		account.setUsername(user);
		account.setPassword(pwd);
		account.setWbUsername(wbUser);
		account.setWbPassword(wbPwd);
		client = new WebQQClient(account, new QQNotifyHandlerProxy(this), new ThreadActorDispatcher());
		add(new JButton(new AbstractAction("login") {

			@Override
			public void actionPerformed(ActionEvent e) {

				login();
			}
		}));
		add(new JButton(new AbstractAction("loginQm") {

			@Override
			public void actionPerformed(ActionEvent e) {

				loginQm();
			}
		}));
		jtfGc = new JTextField(15);
		add(jtfGc);
		jtf = new JTextField(15);
		add(jtf);
		add(new JButton(new AbstractAction("searchMem") {

			@Override
			public void actionPerformed(ActionEvent e) {
				final String group = jtfGc.getText().trim();
				final String lastSpeak = jtf.getText().trim();
				if (group.length()>0) {
					QmMemSearchCondition condition = new QmMemSearchCondition();
					condition.setGc(group);
					if(lastSpeak.length() > 0)
					condition.setLastSpeakTime("1|"+Integer.parseInt(lastSpeak)*24*3600);
					client.searchQmGroupMember(condition, new QQActionListener(){

						@Override
						public void onActionEvent(QQActionEvent event) {
							
							if (event.getType() == EVT_OK) {
								LOG.debug("搜索群成员成功");

								QmGroupMembers members = (QmGroupMembers)event.getTarget();
								LOG.debug("members:     "+members.getCount()+"             admNumb:   "+ members.getAdmNum());
							}
						}
						
					});
				}

			}

		}));
		jtfUin = new JTextField(15);
		add(jtfUin);
		jtfCard = new JTextField(15);
		add(jtfCard);
		add(new JButton(new AbstractAction("updateCard") {

			@Override
			public void actionPerformed(ActionEvent e) {
				final String group = jtfGc.getText().trim();
				final String uin = jtfUin.getText().trim();
				final String card = jtfCard.getText().trim();
				if (group.length()>0&&uin.length()>0) {
					client.setQmGroupCard(group, uin, card, new QQActionListener(){

						@Override
						public void onActionEvent(QQActionEvent event) {
							
							if (event.getType() == EVT_OK) {
								LOG.debug("修改群名片成功");
							}
						}
						
					});
				}

			}

		}));
		jrb = new JRadioButton("不再接受加群申请");
		add(jrb);
		add(new JButton(new AbstractAction("deleteMem") {

			@Override
			public void actionPerformed(ActionEvent e) {
				final String group = jtfGc.getText().trim();
				final String uin = jtfUin.getText().trim();
				ArrayList<String> deleteList = new ArrayList<String>();
				deleteList.add(uin);
				if (group.length()>0&&uin.length()>0) {
					client.deleteQmGroupMember(group, deleteList, jrb.isSelected(), new QQActionListener(){

						@Override
						public void onActionEvent(QQActionEvent event) {
							
							if (event.getType() == EVT_OK) {
								LOG.debug("删除群成员成功");
							}
						}
						
					});
				}

			}

		}));
		add(new JButton(new AbstractAction("loginWb") {

			@Override
			public void actionPerformed(ActionEvent e) {

				loginWb();
			}
		}));
		jta = new JTextArea(4, 20);
		add(jta);

		add(new JButton(new AbstractAction("sendOne") {

			@Override
			public void actionPerformed(ActionEvent e) {

				final String msg = jta.getText().trim();
				if (msg.length() > 0)
					client.sendWbMsg(msg, "5175429989", new QQActionListener() {

						@Override
						public void onActionEvent(QQActionEvent event) {

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

									QQActionFuture future = client.pollWbMsg("5175429989", null);
									try {
										QQActionEvent event1 = future.waitFinalEvent();

										if (event1.getType() == EVT_OK) {
											JSONObject json = (JSONObject) event1.getTarget();
											// System.out.println("pllmsg：   "+json);
											LOG.debug(json.toString());
											JSONArray data = json.optJSONArray("data");
											JSONArray attachment = json.optJSONArray("attachment");
											if (attachment != null) {
												for (int i = 0; i < attachment.length(); i++) {
													JSONObject item = attachment.optJSONObject(i);
													String thumbnail600 = item.optString("thumbnail_600");
												}
											}
											if (data != null) {
												JSONObject firstItem = data.optJSONObject(0);
												String senderId = firstItem.optString("sender_id");
												if (senderId.equals("2645052603")) {
													return;
												}
												String firstText = firstItem.optString("text");
												boolean flag = false;
												for (int i = 0; i < data.length(); i++) {

													JSONObject item = data.optJSONObject(i);

													String text = item.optString("text");
													if (i > 0 && text.equals(msg)) {
														flag = true;
														break;
													}

												}
												if (flag) {
													pollTimer.cancel();
													pollTimer.purge();
													LOG.debug("       pllmsg：   " + firstText);
												}
											}

										}

									} catch (QQException e) {
										e.printStackTrace();
									}
								}
							}, 1000, 500);

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
		WebQQClientUiTest test = new WebQQClientUiTest("1002053815", "lj19861001", "569398403@qq.com", "leegean19861001");
	}

	public void loginWb() {
		client.preloginWb(new QQActionListener() {

			@Override
			public void onActionEvent(QQActionEvent event) {

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
				String chatContent = ((TextItem) item).getContent();
				System.out.print(" Text:" + chatContent);
				if (chatContent.trim().length() > 0) {
					// 组装QQ消息发送回去
					QQMsg sendMsg = new QQMsg();
					sendMsg.setTo(msg.getFrom()); // QQ好友UIN
					iqq.im.bean.QQMsg.Type msgType = msg.getType();
					switch (msgType) {
					case BUDDY_MSG:
						sendMsg.setType(QQMsg.Type.BUDDY_MSG); // 发送类型为好友
						break;
					case GROUP_MSG:
						sendMsg.setType(QQMsg.Type.GROUP_MSG); // 发送类型为好友
						sendMsg.setGroup(msg.getGroup());
						break;
					default:
						break;
					}
					// QQ内容
					sendMsg.addContentItem(new TextItem("hello")); // 添加文本内容
					sendMsg.addContentItem(new FaceItem(0)); // QQ id为0的表情
					sendMsg.addContentItem(new FontItem()); // 使用默认字体
					client.sendMsg(sendMsg, null); // 调用接口发送消息
				}
			} else if (item.getType() == ContentItem.Type.CFACE) {
				// 截图
				// {"retcode":0,"result":[{"poll_type":"group_message","value":{"msg_id":20290,"from_uin":2901943685,"to_uin":1002053815,"msg_id2":855429,"msg_type":43,"reply_ip":176886380,"group_code":1226655265,"send_uin":3706930015,"seq":26,"time":1425016953,"info_seq":260334785,"content":[["font",{"size":10,"color":"000000","style":[0,0,0],"name":"\u5FAE\u8F6F\u96C5\u9ED1"}],["cface",{"name":"{88F3B4B5-0447-77CC-63FB-0E9993B365B0}.jpg","file_id":3080570299,"key":"                ","server":"183.60.51.182:80"}]," "]}}]}
				// System.out.print(" Text:" + ((CFaceItem) item).());
			}
		}

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
		verify.setCode(code);
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

					// 启动轮询时，需要获取所有好友、群成员、讨论组成员
					// 所有的逻辑完了后，启动消息轮询
					client.beginPollMsg();
				}
			}
		};

		client.login(QQStatus.ONLINE, listener);
	}

	public void loginQm() {

		final QQActionListener listener = new QQActionListener() {
			public void onActionEvent(QQActionEvent event) {
				System.out.println("LOGIN_STATUS:" + event.getType() + ":" + event.getTarget());
				if (event.getType() == EVT_OK) {
					// 到这里就算是登录成功了
					System.out.println("就算是登录成功了");

				}
			}
		};

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
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("windowClosing");
		client.destroy();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("windowClosed");
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

}