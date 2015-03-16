/**
 * 
 */
package iqq.im;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator 2015-1-7
 */
public class T_02 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
		System.out.println(str.length);
		
		String str1 = "<ul node-type=\"_phizListNode\"><li action-type=\"webim_phiz_face\" action-data=\"text=[呵呵]\" title=\"[呵呵]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/ac/smilea_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[嘻嘻]\" title=\"[嘻嘻]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/0b/tootha_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[哈哈]\" title=\"[哈哈]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6a/laugh.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[可爱]\" title=\"[可爱]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/14/tza_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[可怜]\" title=\"[可怜]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/af/kl_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[挖鼻屎]\" title=\"[挖鼻屎]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/a0/kbsa_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[吃惊]\" title=\"[吃惊]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/f4/cj_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[害羞]\" title=\"[害羞]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6e/shamea_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[挤眼]\" title=\"[挤眼]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/c3/zy_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[闭嘴]\" title=\"[闭嘴]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/29/bz_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[鄙视]\" title=\"[鄙视]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/71/bs2_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[爱你]\" title=\"[爱你]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6d/lovea_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[泪]\" title=\"[泪]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/9d/sada_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[偷笑]\" title=\"[偷笑]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/19/heia_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[亲亲]\" title=\"[亲亲]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/8f/qq_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[生病]\" title=\"[生病]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/b6/sb_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[太开心]\" title=\"[太开心]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/58/mb_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[懒得理你]\" title=\"[懒得理你]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/17/ldln_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[右哼哼]\" title=\"[右哼哼]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/98/yhh_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[左哼哼]\" title=\"[左哼哼]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6d/zhh_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[嘘]\" title=\"[嘘]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/a6/x_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[衰]\" title=\"[衰]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/af/cry.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[委屈]\" title=\"[委屈]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/73/wq_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[吐]\" title=\"[吐]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/9e/t_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[打哈气]\" title=\"[打哈气]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/f3/k_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[抱抱]\" title=\"[抱抱]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/27/bba_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[怒]\" title=\"[怒]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/7c/angrya_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[疑问]\" title=\"[疑问]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/5c/yw_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[馋嘴]\" title=\"[馋嘴]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/a5/cza_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[拜拜]\" title=\"[拜拜]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/70/88_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[思考]\" title=\"[思考]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/e9/sk_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[汗]\" title=\"[汗]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/24/sweata_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[困]\" title=\"[困]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/7f/sleepya_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[睡觉]\" title=\"[睡觉]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6b/sleepa_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[钱]\" title=\"[钱]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/90/money_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[失望]\" title=\"[失望]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/0c/sw_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[酷]\" title=\"[酷]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/40/cool_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[花心]\" title=\"[花心]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/8c/hsa_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[哼]\" title=\"[哼]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/49/hatea_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[鼓掌]\" title=\"[鼓掌]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/36/gza_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[晕]\" title=\"[晕]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/d9/dizzya_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[悲伤]\" title=\"[悲伤]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/1a/bs_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[抓狂]\" title=\"[抓狂]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/62/crazya_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[黑线]\" title=\"[黑线]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/91/h_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[阴险]\" title=\"[阴险]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6d/yx_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[怒骂]\" title=\"[怒骂]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/89/nm_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[草泥马]\" title=\"[草泥马]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/7a/shenshou_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[神马]\" title=\"[神马]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/60/horse2_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[浮云]\" title=\"[浮云]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/bc/fuyun_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[给力]\" title=\"[给力]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/c9/geili_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[围观]\" title=\"[围观]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/f2/wg_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[威武]\" title=\"[威武]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/70/vw_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[熊猫]\" title=\"[熊猫]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6e/panda_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[兔子]\" title=\"[兔子]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/81/rabbit_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[奥特曼]\" title=\"[奥特曼]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/bc/otm_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[囧]\" title=\"[囧]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/15/j_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[互粉]\" title=\"[互粉]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/89/hufen_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[心]\" title=\"[心]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/40/hearta_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[伤心]\" title=\"[伤心]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/ea/unheart.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[猪头]\" title=\"[猪头]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/58/pig.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[ok]\" title=\"[ok]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/d6/ok_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[耶]\" title=\"[耶]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/d9/ye_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[good]\" title=\"[good]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/d8/good_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[不要]\" title=\"[不要]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/c7/no_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[赞]\" title=\"[赞]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/d0/z2_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[来]\" title=\"[来]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/40/come_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[弱]\" title=\"[弱]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/d8/sad_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[蜡烛]\" title=\"[蜡烛]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/91/lazu_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[礼物]\" title=\"[礼物]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/c4/liwu_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[钟]\" title=\"[钟]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/d3/clock_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[话筒]\" title=\"[话筒]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/1b/m_org.gif\"></li><li action-type=\"webim_phiz_face\" action-data=\"text=[蛋糕]\" title=\"[蛋糕]\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6a/cake.gif\"></li></ul>";
		Pattern p = Pattern.compile("title=\"\\[([\\w\u4e00-\u9fa5]*)\\]\"");
		Matcher m = p.matcher(str1);
		int count=0;
		while(m.find()){
			count++;
			System.out.println("faceMap.put(\""+m.group(1)+"\",\""+"\");");
		}
		System.out.println(count);
		String[] facePair = new String[2];
		HashMap<String, String> faceCodeMap = new HashMap<String, String>();
		for (int i = 0; i < str.length; i++) {
			faceCodeMap.put(str[i], code[i]+"");
		}
		
		HashMap<String, String> faceMap = new HashMap<String, String>();

		faceMap.put("呵呵","微笑");
		faceMap.put("嘻嘻","惊讶");
		faceMap.put("哈哈","憨笑");
		faceMap.put("可爱","可爱");
		faceMap.put("可怜","可怜");
		faceMap.put("挖鼻屎","抠鼻");
		faceMap.put("吃惊","惊讶");
		faceMap.put("害羞","害羞");
		faceMap.put("挤眼","调皮");
		faceMap.put("闭嘴","闭嘴");
		faceMap.put("鄙视","鄙视");
		faceMap.put("爱你","爱心");
		faceMap.put("泪","流泪");
		faceMap.put("偷笑","偷笑");
		faceMap.put("亲亲","爱心");
		faceMap.put("生病","药");
		faceMap.put("太开心","龇牙");
		faceMap.put("懒得理你","傲慢");
		faceMap.put("右哼哼","左哼哼");
		faceMap.put("左哼哼","右哼哼");
		faceMap.put("嘘","嘘");
		faceMap.put("衰","衰");
		faceMap.put("委屈","委屈");
		faceMap.put("吐","吐");
		faceMap.put("打哈气","哈欠");
		faceMap.put("抱抱","拥抱");
		faceMap.put("怒","发怒");
		faceMap.put("疑问","疑问");
		faceMap.put("馋嘴","色");
		faceMap.put("拜拜","再见");
		faceMap.put("思考","大兵");
		faceMap.put("汗","流汗");
		faceMap.put("困","困");
		faceMap.put("睡觉","睡");
		faceMap.put("钱","色");
		faceMap.put("失望","难过");
		faceMap.put("酷","酷");
		faceMap.put("花心","色");
		faceMap.put("哼","傲慢");
		faceMap.put("鼓掌","鼓掌");
		faceMap.put("晕","晕");
		faceMap.put("悲伤","大哭");
		faceMap.put("抓狂","折磨");
		faceMap.put("黑线","擦汗");
		faceMap.put("阴险","阴险");
		faceMap.put("怒骂","咒骂");
		faceMap.put("草泥马","咒骂");
		faceMap.put("神马","疑问");
		faceMap.put("浮云","多云");
		faceMap.put("给力","拳头");
		faceMap.put("围观","");
		faceMap.put("威武","");
		faceMap.put("熊猫","熊猫");
		faceMap.put("兔子","");
		faceMap.put("奥特曼","");
		faceMap.put("囧","流汗");
		faceMap.put("互粉","");
		faceMap.put("心","爱心");
		faceMap.put("伤心","心碎");
		faceMap.put("猪头","猪头");
		faceMap.put("ok","OK");
		faceMap.put("耶","胜利");
		faceMap.put("good","强");
		faceMap.put("不要","NO");
		faceMap.put("赞","强");
		faceMap.put("来","勾引");
		faceMap.put("弱","弱");
		faceMap.put("蜡烛","祈祷");
		faceMap.put("礼物","礼物");
		faceMap.put("钟","闹钟");
		faceMap.put("话筒","K歌");
		faceMap.put("蛋糕","蛋糕");
	}
}
