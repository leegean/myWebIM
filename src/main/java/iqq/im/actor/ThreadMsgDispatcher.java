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
import iqq.im.bean.content.FaceItem;
import iqq.im.bean.content.FontItem;
import iqq.im.bean.content.TextItem;
import iqq.im.bean.content.ContentItem.Type;
import iqq.im.core.QQContext;
import iqq.im.core.QQService;
import iqq.im.event.QQActionEvent;
import iqq.im.event.QQActionFuture;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 单线程的QQ内部分发器，可以同时使用多个QQ实例里
 *
 * @author solosky
 */
public class ThreadMsgDispatcher implements Runnable {
	private static final Logger LOG = LoggerFactory.getLogger(ThreadMsgDispatcher.class);
	private BlockingDeque<QQMsg> msgDeque;
	private WebQQClient webQQClient;
	private Thread msgThread;
	private HashMap<Long, LinkedBlockingDeque<QQMsg>> groupMsgMap;
	private HashMap<String, String> sina2qqFaceMap;
	private HashMap<String, String> faceCodeMap;
	private Pattern pattern;

	/**
	 * 默认构造函数，不会自动启动action循环
	 */
	public ThreadMsgDispatcher() {
		groupMsgMap = new HashMap<Long, LinkedBlockingDeque<QQMsg>>();
		this.msgDeque = new LinkedBlockingDeque<QQMsg>();
//		没人我不发...<i class="face face_2 icon_15">[失望]</i>
		pattern = Pattern.compile("<i\\s*class[^\\[]*\\[([\\w\u4e00-\u9fa5]*)\\]</i>");
		initFace();
	}

	public void pushActor(QQMsg msg) {
		QQGroup group = msg.getGroup();
		Long gin = group.getGin();
		LinkedBlockingDeque<QQMsg> groupMsgDeque = groupMsgMap.get(gin);
		if(groupMsgDeque==null){
			groupMsgDeque = new LinkedBlockingDeque<QQMsg>(5);
			groupMsgMap.put(gin, groupMsgDeque);
		}
		if(!groupMsgDeque.offer(msg)){
			QQMsg lastMsg = groupMsgDeque.peekLast();
			groupMsgDeque.clear();
			groupMsgDeque.offer(lastMsg);
		}
		msgDeque.offer(msg);
	}

	/**
	 * 执行一个QQActor，返回是否继续下一个actor
	 */
	private boolean dispatchMsg(QQMsg msg) {
		
		try {
			Collection<LinkedBlockingDeque<QQMsg>> deques = groupMsgMap.values();
			QQMsg oldMsg = null;
			LinkedBlockingDeque<QQMsg> oldDeque= null;
			for (LinkedBlockingDeque<QQMsg> linkedBlockingDeque : deques) {
				QQMsg tempOldMsg = linkedBlockingDeque.peek();
//				if(linkedBlockingDeque.size()>=5){
//					tempOldMsg = linkedBlockingDeque.peekLast();
//					linkedBlockingDeque.clear();
//				}else{
//					tempOldMsg = linkedBlockingDeque.peek();	
//				}
				
				if(oldMsg==null){
					oldMsg = tempOldMsg;
					oldDeque = linkedBlockingDeque;
				}else{
					if(tempOldMsg!=null){
						long oldTime = oldMsg.getDate().getTime();
						long tempTime = tempOldMsg.getDate().getTime();
						if(oldTime>tempTime){
							oldMsg = tempOldMsg;
							oldDeque = linkedBlockingDeque;
						}
					}
					
				}
				
			}
			final QQMsg reqMsg = oldDeque.poll();
			if(reqMsg == null)return true;
			LOG.debug(reqMsg.getText());
			QQActionFuture future = webQQClient.sendWbMsg(reqMsg.getText(), "5175429989", null);
			
			QQActionEvent event = future.waitFinalEvent();
			if (event.getType() == QQActionEvent.Type.EVT_OK) {
				String chatContent = (String) event.getTarget();
				chatContent = chatContent.replace("小冰", "诗月");
				chatContent = chatContent.replace("微软", "诗月");
				
				Matcher matcher = pattern.matcher(chatContent);
				while(matcher.find()){
					String matcherStr = matcher.group();
					chatContent = chatContent.replace(matcherStr, "");
					String sinaFace = matcher.group(1);
					String qqFace = sina2qqFaceMap.get(sinaFace);
					if(qqFace!=null&&qqFace.length()>0){
						String code = faceCodeMap.get(qqFace);
						reqMsg.addContentItem(new FaceItem(Integer.parseInt(code)));
					}
					
				}
				
				List<ContentItem> contentList = reqMsg.getContentList();
				for (ContentItem contentItem : contentList) {
					if(contentItem.getType() == Type.TEXT){
						((TextItem)contentItem).setContent(chatContent);
					}
				}
				reqMsg.addContentItem(new TextItem()); // 添加文本内容
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
	public  void initFace() {
		// TODO Auto-generated method stub
		String[] str = new String[] { "微笑", "撇嘴", "色", "发呆", "得意", "流泪", "害羞", "闭嘴", "睡", "大哭", "尴尬", "发怒", "调皮", "呲牙", "惊讶", "难过", "酷",
				"冷汗", "抓狂", "吐", "偷笑", "可爱", "白眼", "傲慢", "饥饿", "困", "惊恐", "流汗", "憨笑", "大兵", "奋斗", "咒骂", "疑问", "嘘", "晕", "折磨", "衰", "骷髅",
				"敲打", "再见", "擦汗", "抠鼻", "鼓掌", "糗大了", "坏笑", "左哼哼", "右哼哼", "哈欠", "鄙视", "委屈", "快哭了", "阴险", "亲亲", "吓", "可怜", "菜刀", "西瓜",
				"啤酒", "篮球", "乒乓", "咖啡", "饭", "猪头", "玫瑰", "凋谢", "示爱", "爱心", "心碎", "蛋糕", "闪电", "炸弹", "刀", "足球", "瓢虫", "便便", "月亮", "太阳", "礼物", 
				"拥抱", "强", "弱", "握手", "胜利", "抱拳", "勾引", "拳头", "差劲", "爱你", "NO", "OK", "爱情", "飞吻", "跳跳", "发抖", "怄火", "转圈", "磕头", "回头", "跳绳", "挥手",
				"激动", "街舞", "献吻", "左太极", "右太极", "双喜", "鞭炮", "灯笼", "发财", "K歌", "购物", "邮件", "帅", "喝彩", "祈祷", "爆筋", "棒棒糖", "喝奶", "下面", "香蕉", "飞机",
				"开车", "左车头", "车厢", "右车头", "多云", "下雨","钞票", "熊猫", "灯泡", "风车", "闹钟", "打伞", "彩球", "钻戒", "沙发", "纸巾", "药", "手枪", "青蛙" };
		int[] code = new int[]{14, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 0, 50, 51, 96, 53, 54, 73, 74, 75, 76, 77, 78, 55, 56, 57, 58, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 32, 113, 114, 115, 63, 64, 59, 33, 34, 116, 36, 37, 38, 91, 92, 93, 29, 117, 72, 45, 42, 39, 62, 46, 47, 71, 95, 118, 119, 120, 121, 122, 123, 124, 27, 21, 23, 25, 26, 125, 126, 127, 
	            128, 129, 130, 131, 132, 133, 134, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170};
		
//		String str1 = "<ul node-type=\"_phizListNode\"><li action-type=\"webim_phiz_face\" action-data=\"text=[呵呵]\" title=\"[呵呵]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/ac/smilea_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[嘻嘻]\" title=\"[嘻嘻]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/0b/tootha_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[哈哈]\" title=\"[哈哈]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6a/laugh.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[可爱]\" title=\"[可爱]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/14/tza_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[可怜]\" title=\"[可怜]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/af/kl_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[挖鼻屎]\" title=\"[挖鼻屎]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/a0/kbsa_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[吃惊]\" title=\"[吃惊]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/f4/cj_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[害羞]\" title=\"[害羞]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6e/shamea_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[挤眼]\" title=\"[挤眼]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/c3/zy_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[闭嘴]\" title=\"[闭嘴]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/29/bz_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[鄙视]\" title=\"[鄙视]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/71/bs2_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[爱你]\" title=\"[爱你]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6d/lovea_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[泪]\" title=\"[泪]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/9d/sada_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[偷笑]\" title=\"[偷笑]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/19/heia_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[亲亲]\" title=\"[亲亲]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/8f/qq_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[生病]\" title=\"[生病]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/b6/sb_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[太开心]\" title=\"[太开心]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/58/mb_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[懒得理你]\" title=\"[懒得理你]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/17/ldln_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[右哼哼]\" title=\"[右哼哼]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/98/yhh_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[左哼哼]\" title=\"[左哼哼]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6d/zhh_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[嘘]\" title=\"[嘘]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/a6/x_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[衰]\" title=\"[衰]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/af/cry.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[委屈]\" title=\"[委屈]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/73/wq_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[吐]\" title=\"[吐]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/9e/t_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[打哈气]\" title=\"[打哈气]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/f3/k_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[抱抱]\" title=\"[抱抱]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/27/bba_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[怒]\" title=\"[怒]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/7c/angrya_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[疑问]\" title=\"[疑问]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/5c/yw_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[馋嘴]\" title=\"[馋嘴]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/a5/cza_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[拜拜]\" title=\"[拜拜]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/70/88_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[思考]\" title=\"[思考]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/e9/sk_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[汗]\" title=\"[汗]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/24/sweata_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[困]\" title=\"[困]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/7f/sleepya_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[睡觉]\" title=\"[睡觉]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6b/sleepa_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[钱]\" title=\"[钱]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/90/money_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[失望]\" title=\"[失望]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/0c/sw_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[酷]\" title=\"[酷]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/40/cool_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[花心]\" title=\"[花心]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/8c/hsa_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[哼]\" title=\"[哼]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/49/hatea_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[鼓掌]\" title=\"[鼓掌]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/36/gza_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[晕]\" title=\"[晕]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/d9/dizzya_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[悲伤]\" title=\"[悲伤]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/1a/bs_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[抓狂]\" title=\"[抓狂]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/62/crazya_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[黑线]\" title=\"[黑线]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/91/h_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[阴险]\" title=\"[阴险]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6d/yx_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[怒骂]\" title=\"[怒骂]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/89/nm_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[草泥马]\" title=\"[草泥马]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/7a/shenshou_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[神马]\" title=\"[神马]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/60/horse2_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[浮云]\" title=\"[浮云]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/bc/fuyun_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[给力]\" title=\"[给力]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/c9/geili_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[围观]\" title=\"[围观]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/f2/wg_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[威武]\" title=\"[威武]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/70/vw_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[熊猫]\" title=\"[熊猫]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6e/panda_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[兔子]\" title=\"[兔子]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/81/rabbit_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[奥特曼]\" title=\"[奥特曼]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/bc/otm_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[囧]\" title=\"[囧]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/15/j_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[互粉]\" title=\"[互粉]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/89/hufen_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[心]\" title=\"[心]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/40/hearta_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[伤心]\" title=\"[伤心]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/ea/unheart.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[猪头]\" title=\"[猪头]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/58/pig.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[ok]\" title=\"[ok]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/d6/ok_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[耶]\" title=\"[耶]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/d9/ye_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[good]\" title=\"[good]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/d8/good_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[不要]\" title=\"[不要]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/c7/no_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[赞]\" title=\"[赞]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/d0/z2_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[来]\" title=\"[来]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/40/come_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[弱]\" title=\"[弱]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/d8/sad_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[蜡烛]\" title=\"[蜡烛]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/91/lazu_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[礼物]\" title=\"[礼物]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/c4/liwu_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[钟]\" title=\"[钟]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/d3/clock_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[话筒]\" title=\"[话筒]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/1b/m_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[蛋糕]\" title=\"[蛋糕]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6a/cake.gif\"></li></ul>";
//		Pattern p = Pattern.compile("title=\"\\[([\\w\u4e00-\u9fa5]*)\\]\"");
//		Matcher m = p.matcher(str1);
//		int count=0;
//		while(m.find()){
//			count++;
//			System.out.println("faceMap.put(\""+m.group(1)+"\",\""+"\");");
//		}
//		System.out.println(count);
//		String[] facePair = new String[2];
		faceCodeMap = new HashMap<String, String>();
		for (int i = 0; i < str.length; i++) {
			faceCodeMap.put(str[i], code[i]+"");
		}
		
		sina2qqFaceMap = new HashMap<String, String>();

		sina2qqFaceMap.put("呵呵","微笑");
		sina2qqFaceMap.put("嘻嘻","惊讶");
		sina2qqFaceMap.put("哈哈","憨笑");
		sina2qqFaceMap.put("可爱","可爱");
		sina2qqFaceMap.put("可怜","可怜");
		sina2qqFaceMap.put("挖鼻屎","抠鼻");
		sina2qqFaceMap.put("吃惊","惊讶");
		sina2qqFaceMap.put("害羞","害羞");
		sina2qqFaceMap.put("挤眼","调皮");
		sina2qqFaceMap.put("闭嘴","闭嘴");
		sina2qqFaceMap.put("鄙视","鄙视");
		sina2qqFaceMap.put("爱你","爱心");
		sina2qqFaceMap.put("泪","流泪");
		sina2qqFaceMap.put("偷笑","偷笑");
		sina2qqFaceMap.put("亲亲","爱心");
		sina2qqFaceMap.put("生病","药");
		sina2qqFaceMap.put("太开心","龇牙");
		sina2qqFaceMap.put("懒得理你","傲慢");
		sina2qqFaceMap.put("右哼哼","左哼哼");
		sina2qqFaceMap.put("左哼哼","右哼哼");
		sina2qqFaceMap.put("嘘","嘘");
		sina2qqFaceMap.put("衰","衰");
		sina2qqFaceMap.put("委屈","委屈");
		sina2qqFaceMap.put("吐","吐");
		sina2qqFaceMap.put("打哈气","哈欠");
		sina2qqFaceMap.put("抱抱","拥抱");
		sina2qqFaceMap.put("怒","发怒");
		sina2qqFaceMap.put("疑问","疑问");
		sina2qqFaceMap.put("馋嘴","色");
		sina2qqFaceMap.put("拜拜","再见");
		sina2qqFaceMap.put("思考","大兵");
		sina2qqFaceMap.put("汗","流汗");
		sina2qqFaceMap.put("困","困");
		sina2qqFaceMap.put("睡觉","睡");
		sina2qqFaceMap.put("钱","色");
		sina2qqFaceMap.put("失望","难过");
		sina2qqFaceMap.put("酷","酷");
		sina2qqFaceMap.put("花心","色");
		sina2qqFaceMap.put("哼","傲慢");
		sina2qqFaceMap.put("鼓掌","鼓掌");
		sina2qqFaceMap.put("晕","晕");
		sina2qqFaceMap.put("悲伤","大哭");
		sina2qqFaceMap.put("抓狂","折磨");
		sina2qqFaceMap.put("黑线","擦汗");
		sina2qqFaceMap.put("阴险","阴险");
		sina2qqFaceMap.put("怒骂","咒骂");
		sina2qqFaceMap.put("草泥马","咒骂");
		sina2qqFaceMap.put("神马","疑问");
		sina2qqFaceMap.put("浮云","多云");
		sina2qqFaceMap.put("给力","拳头");
		sina2qqFaceMap.put("围观","");
		sina2qqFaceMap.put("威武","");
		sina2qqFaceMap.put("熊猫","熊猫");
		sina2qqFaceMap.put("兔子","");
		sina2qqFaceMap.put("奥特曼","");
		sina2qqFaceMap.put("囧","流汗");
		sina2qqFaceMap.put("互粉","");
		sina2qqFaceMap.put("心","爱心");
		sina2qqFaceMap.put("伤心","心碎");
		sina2qqFaceMap.put("猪头","猪头");
		sina2qqFaceMap.put("ok","OK");
		sina2qqFaceMap.put("耶","胜利");
		sina2qqFaceMap.put("good","强");
		sina2qqFaceMap.put("不要","NO");
		sina2qqFaceMap.put("赞","强");
		sina2qqFaceMap.put("来","勾引");
		sina2qqFaceMap.put("弱","弱");
		sina2qqFaceMap.put("蜡烛","祈祷");
		sina2qqFaceMap.put("礼物","礼物");
		sina2qqFaceMap.put("钟","闹钟");
		sina2qqFaceMap.put("话筒","K歌");
		sina2qqFaceMap.put("蛋糕","蛋糕");
	}
}
