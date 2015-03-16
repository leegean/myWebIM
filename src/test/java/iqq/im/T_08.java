package iqq.im;

import static iqq.im.event.QQActionEvent.Type.EVT_OK;
import iqq.im.actor.ThreadActorDispatcher;
import iqq.im.bean.QQAccount;
import iqq.im.bean.QQBuddy;
import iqq.im.bean.QQCategory;
import iqq.im.bean.QQGroup;
import iqq.im.bean.QQMsg;
import iqq.im.bean.QQStatus;
import iqq.im.bean.QQUser;
import iqq.im.bean.content.ContentItem;
import iqq.im.bean.content.FaceItem;
import iqq.im.bean.content.FontItem;
import iqq.im.bean.content.OffPicItem;
import iqq.im.bean.content.TextItem;
import iqq.im.bean.content.WbVerifyImage;
import iqq.im.event.QQActionEvent;
import iqq.im.event.QQNotifyEvent;
import iqq.im.event.QQNotifyEventArgs;
import iqq.im.event.QQNotifyHandler;
import iqq.im.event.QQNotifyHandlerProxy;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.JTree.DynamicUtilTreeNode;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class T_08 extends JFrame implements WindowListener{

	public static void main(String[] args) {
		T_08 win = new T_08();
		LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
		for (LookAndFeelInfo lookAndFeelInfo : looks) {
			System.out.println(lookAndFeelInfo.getClassName() + "       " + lookAndFeelInfo.getName());
		}
		System.out.println(UIManager.getCrossPlatformLookAndFeelClassName());
		win.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

			}
		});
		win.setSize(500, 700);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setVisible(true);

	}

	private JTree contactTree;
	private DefaultTreeModel contactTreeModel;
	private DefaultMutableTreeNode contactRootNode;
	protected DynamicUtilTreeNode node;
	private ImageIcon openIcon;
	private ImageIcon closeIcon;
	private JTabbedPane tabPane;
	private JTree groupTree;
	private DefaultTreeModel groupTreeModel;
	private DefaultMutableTreeNode groupRootNode;
	private JLabel nickName;
	private JTextField nameField;
	private JTextField pwdField;
	private JTextField wbNameField;
	private JTextField wbPwdField;
	protected WebQQClient client;
	private JLabel verifyLabel;
	private JTextField verifyField;
	private QQNotifyEvent verifyEvt;

	private HashSet<Long> msgCache = new HashSet<Long>();
	protected boolean isLoginWb;
	protected long loginWbTime;
	private CardLayout cardLayout;
	private Container rootPanel;
	private JTree tree;
	public T_08() {
		try {
			openIcon = new ImageIcon(ImageIO.read(new File("a.png")));
			closeIcon = new ImageIcon(ImageIO.read(new File("b.png")));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		setLayout(new FlowLayout());

		tree = new JTree();
		BasicTreeUI treeUI = ((BasicTreeUI) tree.getUI());
		treeUI.setLeftChildIndent(0);
		treeUI.setRightChildIndent(0);
		tree.setRootVisible(false);
		tree.setOpaque(false);
		tree.setCellRenderer(new UserRenderer());
		tree.putClientProperty("JTree.lineStyle", "None");
		tree.setExpandsSelectedPaths(true);
		tree.setToggleClickCount(1);

		cardLayout = new CardLayout();
		rootPanel = getContentPane();
		rootPanel.setLayout(cardLayout);
		JPanel contentPanel = new JPanel(new BorderLayout());
		
		GridBagLayout gbl = new GridBagLayout();
		JPanel loginPanel = new JPanel(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		
		loginPanel.add(new JLabel("用户名："), gbc);
		
		gbc.gridx = 1;
		nameField = new JTextField(10);
		nameField.setText("1002053815");
		loginPanel.add(nameField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		loginPanel.add(new JLabel("密    码："), gbc);
		gbc.gridx = 1;
		
		pwdField = new JTextField(10);
		pwdField.setText("lj19861001");
		
		loginPanel.add(pwdField, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
loginPanel.add(new JLabel("账号："), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		wbNameField = new JTextField(10);
		loginPanel.add(wbNameField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		loginPanel.add(new JLabel("密码："), gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		wbPwdField = new JTextField(10);
		
		loginPanel.add(wbPwdField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		
		loginPanel.add(new JButton(new AbstractAction("登录") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String name = nameField.getText().trim();
				String pwd = pwdField.getText().trim();
				if(name.length()<5)return;
				String wbName = wbNameField.getText().trim();
				String wbPwd = wbPwdField.getText().trim();
				
				QQAccount account = new QQAccount();
				account.setUsername(name);
				account.setPassword(pwd);
				account.setWbUsername(wbName);
				account.setWbPassword(wbPwd);
				client.setAccount(account);
				
				if(verifyLabel.getIcon()!=null){
					verifyLabel.setIcon(null);
					client.submitVerify(verifyField.getText(), verifyEvt);
				}else{
					login();
				}
			}
		}), gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		verifyLabel = new JLabel();
		loginPanel.add(verifyLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		verifyField = new JTextField(10);
		verifyField.setVisible(false);
		loginPanel.add(verifyField, gbc);
		rootPanel.add(loginPanel, BorderLayout.CENTER);
		tabPane = new JTabbedPane(JTabbedPane.TOP);

		JPanel contactPanel = createContactPanel();
		JPanel groupPanel = createGroupPanel();
		
		JPanel setPanel = new JPanel();
		tabPane.add("联系人",contactPanel);
		tabPane.add("群组",groupPanel);
		tabPane.add("设置",setPanel);
		contentPanel.add(tabPane, BorderLayout.CENTER);
		
		JPanel userPanel = new JPanel();
		nickName = new JLabel("nick");
		userPanel.add(nickName);
		contentPanel.add(userPanel, BorderLayout.NORTH);
		
		contentPanel.add(new JButton(new AbstractAction("insert") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Hashtable<String, Integer[]> map = new Hashtable<String, Integer[]>();
				map.put("group_1", new Integer[] { 51, 52, 53 });
				String[] data = new String[] { "aa", "bb", "cc" };
				DynamicUtilTreeNode.createChildren(contactRootNode, map);

				contactTreeModel.reload(contactRootNode);
				expandAllNode(contactTree, new TreePath(contactRootNode), true);
				
				Hashtable<String, Integer[]> map1 = new Hashtable<String, Integer[]>();
				map1.put("group_1", new Integer[] { 51, 52, 53 });
				String[] data1 = new String[] { "aa", "bb", "cc" };
				DynamicUtilTreeNode.createChildren(groupRootNode, map);

				groupTreeModel.reload(groupRootNode);
				expandAllNode(groupTree, new TreePath(groupRootNode), true);
			}
		}), BorderLayout.SOUTH);
		rootPanel.add(contentPanel);
		
		client = new WebQQClient(new QQNotifyHandlerProxy(this), new ThreadActorDispatcher());
	}

	private JPanel createContactPanel() {
		contactTree = new JTree();
		BasicTreeUI treeUI = ((BasicTreeUI) contactTree.getUI());
		treeUI.setLeftChildIndent(0);
		treeUI.setRightChildIndent(0);
		contactTree.setRootVisible(false);
		UserRenderer renderer = new UserRenderer();
		contactTree.setCellRenderer(renderer);
		contactTree.addMouseMotionListener(renderer);
		contactTree.putClientProperty("JTree.lineStyle", "None");
		contactTree.setExpandsSelectedPaths(true);
		contactTree.setToggleClickCount(1);
		contactRootNode = new DefaultMutableTreeNode("root");
		contactTreeModel = new DefaultTreeModel(contactRootNode);
		contactTree.setModel(contactTreeModel);

		JScrollPane jsp = new JScrollPane(contactTree);
		jsp.setOpaque(false);
		
		jsp.setBorder(null);
		MyPanelUI pui = new MyPanelUI();
		JPanel contactPanel = new JPanel(new BorderLayout());
		contactPanel.setUI(pui);
		contactPanel.add(jsp);
		return contactPanel;
	}
	private JPanel createGroupPanel() {
		groupTree = new JTree();
		BasicTreeUI treeUI = ((BasicTreeUI) groupTree.getUI());
		treeUI.setLeftChildIndent(0);
		treeUI.setRightChildIndent(0);
		groupTree.setRootVisible(false);
		UserRenderer renderer = new UserRenderer();
		groupTree.setCellRenderer(renderer);
		groupTree.addMouseMotionListener(renderer);
		groupTree.putClientProperty("JTree.lineStyle", "None");
		groupTree.setExpandsSelectedPaths(true);
		groupTree.setToggleClickCount(1);
		groupRootNode = new DefaultMutableTreeNode("root");
		groupTreeModel = new DefaultTreeModel(groupRootNode);
		groupTree.setModel(groupTreeModel);
		

		JScrollPane jsp = new JScrollPane(groupTree);
		jsp.setOpaque(false);
		jsp.setBorder(null);
		MyPanelUI pui = new MyPanelUI();
		JPanel groupPanel = new JPanel(new BorderLayout());
		groupPanel.setUI(pui);
		groupPanel.add(jsp);
		return groupPanel;
	}
	private void expandAllNode(JTree tree, TreePath parent, boolean expand) {
		// Traverse children
		TreeNode node = (TreeNode) parent.getLastPathComponent();
		if (node.getChildCount() >= 0) {
			for (Enumeration<?> e = node.children(); e.hasMoreElements();) {
				TreeNode n = (TreeNode) e.nextElement();
				TreePath path = parent.pathByAddingChild(n);
				expandAllNode(tree, path, expand);
			}
		}

		if (expand) {
			tree.expandPath(parent);
		} else {
			tree.collapsePath(parent);
		}
	}
	@QQNotifyHandler(QQNotifyEvent.Type.CHAT_MSG)
	public void processBuddyMsg(QQNotifyEvent event) throws QQException {
		QQMsg msg = (QQMsg) event.getTarget();
		long msgId = msg.getId();
		if(msgCache.contains(msgId)){
			return;
		}else{
			msgCache.add(msgId);
		}
		
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
				System.out.print(chatContent);
				System.out.println();
				int chatLen = chatContent.trim().length();
				if (chatLen > 0 && chatLen < 100) {
					// 组装QQ消息发送回去
					final QQMsg sendMsg = new QQMsg();
				sendMsg.setFrom(msg.getTo());
					sendMsg.setTo(msg.getFrom()); // QQ好友UIN
					sendMsg.setDate(msg.getDate());
					iqq.im.bean.QQMsg.Type msgType = msg.getType();
					switch (msgType) {
					case BUDDY_MSG:
						sendMsg.setType(QQMsg.Type.BUDDY_MSG); 
						break;
					case GROUP_MSG:
						sendMsg.setType(QQMsg.Type.GROUP_MSG); 
						sendMsg.setGroup(msg.getGroup());
						sendMsg.addContentItem(new TextItem(chatContent.replace("@", "").replace("诗月", ""))); // 添加文本内容
						sendMsg.addContentItem(new FontItem()); // 使用默认字体
						break;
					default:
						break;
					}
					// QQ内容
					if(msgType == QQMsg.Type.GROUP_MSG){
						if(isLoginWb&&msg.getDate().getTime()>loginWbTime){
							client.getMsgDispatcher().pushActor(sendMsg);
						}
					}
					
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
//		ImageIO.write(verify.image, "png", new File("verify.png"));
//		System.out.println(verify.reason);
//		System.out.print("请输入在项目根目录下verify.png图片里面的验证码:");
//		String code = new BufferedReader(new InputStreamReader(System.in)).readLine();
		verifyEvt = event;
		verifyLabel.setIcon(new ImageIcon(verify.image));
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
				if (event.getType() == EVT_OK) {
					// 到这里就算是登录成功了

					cardLayout.last(rootPanel);
					
					// 获取下用户信息
					client.getUserInfo(client.getAccount(), new QQActionListener() {
						public void onActionEvent(QQActionEvent event) {
							if(event.getType() == EVT_OK){
								final QQUser user = (QQUser)event.getTarget();
										nickName.setText(user.getNickname()+"("+user.getUin()+")");
								
							}
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
									Hashtable<String, Object[]> data = new Hashtable<String, Object[]>();
									
									List<QQBuddy> buddyList = c.getBuddyList();
									data.put(c.getName(), buddyList.toArray());
									DynamicUtilTreeNode.createChildren(contactRootNode, data);
									for (QQBuddy b : buddyList) {
										System.out.println("---- QQ nick:" + b.getNickname() + " markname:" + b.getMarkname() + " uin:" + b.getUin() + " isVip:" + b.isVip() + " vip_level:" + b.getVipLevel());
									}
								}
								contactTreeModel.reload(contactRootNode);
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
//								for (QQGroup g : client.getGroupList()) {
//									client.getGroupInfo(g, null);
//									System.out.println("Group: " + g.getName());
//								}
								List<QQGroup> groupList = (List<QQGroup>)event.getTarget();
								DynamicUtilTreeNode.createChildren(groupRootNode, groupList.toArray());
								groupTreeModel.reload(groupRootNode);
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
	public void loginWb() {
		client.preloginWb(new QQActionListener() {

			@Override
			public void onActionEvent(QQActionEvent event) {

				if (event.getType() == EVT_OK) {
					isLoginWb = true;
					loginWbTime = System.currentTimeMillis();
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

	@Override
	public void windowOpened(WindowEvent e) {
	}
	@Override
	public void windowClosing(WindowEvent e) {
		
		client.destroy();
	}
	@Override
	public void windowClosed(WindowEvent e) {
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
