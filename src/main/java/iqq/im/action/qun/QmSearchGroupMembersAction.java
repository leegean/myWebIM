package iqq.im.action.qun;

import java.util.HashMap;
import java.util.Iterator;

import iqq.im.QQActionListener;
import iqq.im.QQException;
import iqq.im.action.AbstractHttpAction;
import iqq.im.bean.QQAccount;
import iqq.im.bean.QQGroup;
import iqq.im.bean.QmGroupMembers;
import iqq.im.bean.QmMemSearchCondition;
import iqq.im.core.*;
import iqq.im.event.QQActionEvent;
import iqq.im.http.QQHttpCookie;
import iqq.im.http.QQHttpRequest;
import iqq.im.http.QQHttpResponse;

import iqq.im.service.HttpService;
import iqq.im.util.QQEncryptor;
import org.slf4j.Logger;
import org.apache.http.cookie.Cookie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

/**
 * 获取群列表名称
 * 
 * @author ChenZhiHui
 * @since 2013-2-21
 */
public class QmSearchGroupMembersAction extends AbstractHttpAction {
	private static final Logger LOG = LoggerFactory.getLogger(QmSearchGroupMembersAction.class);
	private QmMemSearchCondition condition;

	/**
	 * <p>
	 * Constructor for GetGroupListAction.
	 * </p>
	 * 
	 * @param context
	 *            a {@link iqq.im.core.QQContext} object.
	 * @param listener
	 *            a {@link iqq.im.QQActionListener} object.
	 */
	public QmSearchGroupMembersAction(QmMemSearchCondition condition, QQContext context, QQActionListener listener) {
		super(context, listener);
		this.condition = condition;
	}

	/** {@inheritDoc} */
	@Override
	public QQHttpRequest onBuildRequest() throws QQException, JSONException {
		// credit:1
		// g:0
		// qage:3|5
		// join_time:7776000|15552000
		// lv:2
		// last_speak_time:7776000|15552000
		HttpService httpService = (HttpService) getContext().getSerivce(QQService.Type.HTTP);
		Cookie skey = httpService.getCookie("skey", QQConstants.URL_QM_SEATCH_GROUP_MEMBERS);

		QQHttpRequest req = createHttpRequest("POST", QQConstants.URL_QM_SEATCH_GROUP_MEMBERS);
		req.addPostValue("gc", condition.getGc());
		req.addPostValue("st", condition.getSt() + "");
		req.addPostValue("end", condition.getEnd() + "");
		req.addPostValue("sort", condition.getSort() + "");
		req.addPostValue("bkn", QQEncryptor.getBkn(skey.getValue()) + "");

		if (condition.getCreadit() != -1)
			req.addPostValue("credit", condition.getCreadit() + "");
		if (condition.getG() != -1)
			req.addPostValue("g", condition.getG() + "");
		if (condition.getQage() != null)
			req.addPostValue("qage", condition.getQage());
		if (condition.getJoinTime() != null)
			req.addPostValue("join_time", condition.getJoinTime());
		if (condition.getLv() != -1)
			req.addPostValue("lv", condition.getLv() + "");
		if (condition.getLastSpeakTime() != null)
			req.addPostValue("last_speak_time", condition.getLastSpeakTime());
		if (condition.getKey() != null)
			req.addPostValue("key", condition.getKey());

//		req.addHeader("Accept","application/json, text/javascript, */*; q=0.01");
//		req.addHeader("Accept-Encoding:","gzip, deflate");
//		req.addHeader("Accept-Language","zh-CN,zh;q=0.8");
//		req.addHeader("Connection","keep-alive");
//		req.addHeader("Host", "qun.qq.com");
//		req.addHeader("X-Requested-With", "XMLHttpRequest");
//		req.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		req.addHeader("Referer", "http://qun.qq.com/member.html");
//req.addHeader("Origin", "http://qun.qq.com");
//req.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.93 Safari/537.36");
		return req;
	}

	/** {@inheritDoc} */
	@Override
	protected void onHttpStatusOK(QQHttpResponse response) throws QQException, JSONException {
		// {"adm_max":10,"adm_num":0,"count":5,"ec":0,"max_count":500,"mems":[{"card":"","flag":0,"g":0,"join_time":1346172585,"last_speak_time":0,"lv":{"level":1,"point":0},"nick":"我是識月","qage":8,"role":0,"tags":"-1","uin":569398403},{"card":"","flag":0,"g":1,"join_time":1346211778,"last_speak_time":0,"lv":{"level":1,"point":0},"nick":"月颖","qage":2,"role":2,"tags":"-1","uin":2564781987},{"card":"","flag":0,"g":0,"join_time":1364884811,"last_speak_time":0,"lv":{"level":1,"point":0},"nick":"李军","qage":4,"role":2,"tags":"-1","uin":1002053815},{"card":"","flag":0,"g":1,"join_time":1364884897,"last_speak_time":0,"lv":{"level":1,"point":0},"nick":"name","qage":2,"role":2,"tags":"-1","uin":2280410025},{"card":"","flag":0,"g":0,"join_time":1364884984,"last_speak_time":0,"lv":{"level":1,"point":0},"nick":"tracy","qage":2,"role":2,"tags":"-1","uin":1401982225}],"search_count":5,"svr_time":1420608628,"vecsize":1}
		// {"adm_max":10,"adm_num":2,"count":8,"ec":0,"levelname":{"1":"路人","2":"师兄","3":"少侠","4":"大侠","5":"掌门","6":"宗师"},"max_count":500,"mems":[{"card":"yue","flag":0,"g":0,"join_time":0,"last_speak_time":1419238990,"lv":{"level":2,"point":22},"nick":"我是識月","qage":8,"role":0,"tags":"-1","uin":569398403},{"card":"lijun","flag":0,"g":0,"join_time":1419232468,"last_speak_time":1419238024,"lv":{"level":2,"point":13},"nick":"李军","qage":4,"role":1,"tags":"-1","uin":1002053815},{"card":"","flag":0,"g":1,"join_time":1418993822,"last_speak_time":1418996225,"lv":{"level":2,"point":5},"nick":"name","qage":2,"role":1,"tags":"-1","uin":2280410025},{"card":"","flag":0,"g":0,"join_time":1346170317,"last_speak_time":0,"lv":{"level":1,"point":0},"nick":"tracy","qage":2,"role":2,"tags":"-1","uin":1401982225},{"card":"","flag":0,"g":1,"join_time":1364906975,"last_speak_time":0,"lv":{"level":1,"point":0},"nick":"aa","qage":2,"role":2,"tags":"-1","uin":2414081615},{"card":"","flag":0,"g":1,"join_time":1381914943,"last_speak_time":0,"lv":{"level":1,"point":0},"nick":"yue0009","qage":2,"role":2,"tags":"-1","uin":1668008346},{"card":"","flag":0,"g":1,"join_time":1383301096,"last_speak_time":0,"lv":{"level":1,"point":0},"nick":"yue0022","qage":2,"role":2,"tags":"-1","uin":1127198273},{"card":"","flag":0,"g":1,"join_time":1418997115,"last_speak_time":0,"lv":{"level":1,"point":0},"nick":"风雨梦","qage":2,"role":2,"tags":"-1","uin":154719534}],"search_count":8,"svr_time":1420609613,"vecsize":1}
		/*
		 * { "adm_max": 10, "adm_num": 2, "count": 8, "ec": 0, "levelname": {
		 * "1": "路人", "2": "师兄", "3": "少侠", "4": "大侠", "5": "掌门", "6": "宗师" },
		 * "max_count": 500, "mems": [ { "card": "yue", "flag": 0, "g": 0,
		 * "join_time": 0, "last_speak_time": 1419238990, "lv": { "level": 2,
		 * "point": 22 }, "nick": "我是識月", "qage": 8, "role": 0, "tags": "-1",
		 * "uin": 569398403 }, { "card": "lijun", "flag": 0, "g": 0,
		 * "join_time": 1419232468, "last_speak_time": 1419238024, "lv": {
		 * "level": 2, "point": 13 }, "nick": "李军", "qage": 4, "role": 1,
		 * "tags": "-1", "uin": 1002053815 }, { "card": "", "flag": 0, "g": 1,
		 * "join_time": 1418993822, "last_speak_time": 1418996225, "lv": {
		 * "level": 2, "point": 5 }, "nick": "name", "qage": 2, "role": 1,
		 * "tags": "-1", "uin": 2280410025 }, { "card": "", "flag": 0, "g": 0,
		 * "join_time": 1346170317, "last_speak_time": 0, "lv": { "level": 1,
		 * "point": 0 }, "nick": "tracy", "qage": 2, "role": 2, "tags": "-1",
		 * "uin": 1401982225 }, { "card": "", "flag": 0, "g": 1, "join_time":
		 * 1364906975, "last_speak_time": 0, "lv": { "level": 1, "point": 0 },
		 * "nick": "aa", "qage": 2, "role": 2, "tags": "-1", "uin": 2414081615
		 * }, { "card": "", "flag": 0, "g": 1, "join_time": 1381914943,
		 * "last_speak_time": 0, "lv": { "level": 1, "point": 0 }, "nick":
		 * "yue0009", "qage": 2, "role": 2, "tags": "-1", "uin": 1668008346 }, {
		 * "card": "", "flag": 0, "g": 1, "join_time": 1383301096,
		 * "last_speak_time": 0, "lv": { "level": 1, "point": 0 }, "nick":
		 * "yue0022", "qage": 2, "role": 2, "tags": "-1", "uin": 1127198273 }, {
		 * "card": "", "flag": 0, "g": 1, "join_time": 1418997115,
		 * "last_speak_time": 0, "lv": { "level": 1, "point": 0 }, "nick":
		 * "风雨梦", "qage": 2, "role": 2, "tags": "-1", "uin": 154719534 } ],
		 * "search_count": 8, "svr_time": 1420609613, "vecsize": 1 }
		 */
		
		/*function groupMemberLoad(d){
				
				loading = 0;
				reseting = 0;
				if(d.ec == 0){
					nowGroup = {
						mems : d.mems,
						lv : d.levelname,
						tag : d.tag_info,
						adnum : d.adm_num,
						admax : d.adm_max,
						num : d.count,
						max : d.max_count
					}
			
					if(!st && gidChange){
						renderGroupTh({
							show : checkAuth(nowGid),
							desc : nowDesc,
							lv : nowGroup.lv,
							tag : nowGroup.tag
						});
					}

					if((!$.isEmptyObject(nowFilter) || nowKey != '') && st == 0){
						renderFilter(d.search_count);
					};
					if(nowGroup.lv){
						$('.th-lv').show();
						$('#groupLevel').show();
					}else{
						$('.th-lv').hide();
						$('#groupLevel').hide();
					}
					if(nowGroup.tag){
						$('.th-mark').show();
					}else{
						$('.th-mark').hide();
					}			
					//设置下一样的开始id和end id
					count = d.count;

					var type = checkGroup(nowGid);
					renderLevel(nowGroup.lv);
					getMem2Uin(nowGroup.mems);
					if(d.search_count){
						renderMem({
							list: nowGroup.mems,
							my : myQQ,
							st : st,
							lv : nowGroup.lv,
							tag : nowGroup.tag,
							type : type,
							ie6 : isIe6
						});
						$('#groupMember').show();
						$('#searchEmpty').hide();
					}else{
						$('#groupMember').hide();
						var h = $('#groupMemberEmptyTmp').html();
						$('#searchEmpty').html(h).show();
					}
					renderMemTit({
						type : type,
						num : nowGroup.num,
						all : nowGroup.max || 0
					});
					var ct = d.search_count || d.count;
					var ot = st;
					if(end >= ct){
						hasNext = 0;
					}else{
						hasNext = 1;
					}
					if(st < ct){
						st = end + 1;
						end = st + defNum;
						if(end >= ct){
							end = ct;
						}
					}
					checkAuth(nowGid);	
					// if(nowGroup.adnum == nowGroup.count-1){
					// 	$('#selectAll').hide();
					// }else{
					// 	$('#selectAll').show();
					// }
				}else{
					Dialog.alert('拉取成员列表出错，请稍后重试!');
				}
			}*/
		JSONObject json = new JSONObject(response.getResponseString("utf-8"));
		int retCode = json.optInt("ec", -1);
		if(retCode==0){
			QmGroupMembers qmGroupMembers = new QmGroupMembers();
			qmGroupMembers.setAdmMax(json.optInt("adm_max"));
			qmGroupMembers.setAdmNum(json.optInt("adm_num"));
			qmGroupMembers.setCount(json.optInt("count"));
			qmGroupMembers.setMaxCount(json.optInt("max_count"));
			qmGroupMembers.setSearchCount(json.optInt("search_count"));
			qmGroupMembers.setSvrTime(json.optLong("svr_time"));
			qmGroupMembers.setVecsize(json.optInt("vecsize"));

			JSONObject jsonLn = json.optJSONObject("levelname");
			if(jsonLn!=null){
				Iterator keys = jsonLn.keys();
				HashMap<String, String> levelName = qmGroupMembers.getLevelName();
				while (keys.hasNext()) {
					String key = (String) keys.next();
					String name = jsonLn.optString(key);
					levelName.put(key, name);
				}
			}
			

			JSONArray jsonMems = json.optJSONArray("mems");
			if(jsonMems!=null){
				int jmemsLen = jsonMems.length();
				for (int i = 0; i < jmemsLen; i++) {
					JSONObject jMem = jsonMems.optJSONObject(i);
					
					QmGroupMembers.QmGroupMember qmMem = new QmGroupMembers.QmGroupMember();
					qmMem.setCard(jMem.optString("card"));
					qmMem.setFlag(jMem.optInt("flag"));
					qmMem.setG(jMem.optInt("g"));
					qmMem.setJoinTime(jMem.optInt("join_time"));
					qmMem.setLastSpeakTime(jMem.optInt("last_speak_time"));
					JSONObject jLv = jMem.optJSONObject("lv");
					qmMem.setLvLevel(jLv.optInt("level"));
					qmMem.setLvPoint(jLv.optInt("point"));

					qmMem.setNick(jMem.optString("nick"));
					qmMem.setQage(jMem.optInt("qage"));
					qmMem.setRole(jMem.optInt("role"));
					qmMem.setTags(jMem.optString("tags"));
					qmMem.setUin(jMem.optInt("uin"));
					
					qmGroupMembers.getMems().add(qmMem);
				}
			}
			
			notifyActionEvent(QQActionEvent.Type.EVT_OK, qmGroupMembers);
		}else{
			notifyActionEvent(QQActionEvent.Type.EVT_ERROR, response.getResponseString());
		}
	}

}
