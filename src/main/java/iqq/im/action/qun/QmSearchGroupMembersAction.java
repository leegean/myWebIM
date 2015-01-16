
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
	 * <p>Constructor for GetGroupListAction.</p>
	 *
	 * @param context a {@link iqq.im.core.QQContext} object.
	 * @param listener a {@link iqq.im.QQActionListener} object.
	 */
	public QmSearchGroupMembersAction(QmMemSearchCondition condition,QQContext context, QQActionListener listener) {
		super(context, listener);
		this.condition = condition;
	}

	/** {@inheritDoc} */
	@Override
	public QQHttpRequest onBuildRequest() throws QQException, JSONException {
//		credit:1
//		g:0
//		qage:3|5
//		join_time:7776000|15552000
//		lv:2
//		last_speak_time:7776000|15552000
		

		QQHttpRequest req = createHttpRequest("POST",
				QQConstants.URL_QM_SEATCH_GROUP_MEMBERS);
		req.addPostValue("gc", condition.getGc());
		req.addPostValue("st", condition.getSt()+"");
		req.addPostValue("end", condition.getEnd()+"");
		req.addPostValue("sort", condition.getSort()+"");
		req.addPostValue("bkn", "");
		
		if(condition.getCreadit()!=-1)req.addPostValue("credit", condition.getCreadit()+"");
		if(condition.getG()!=-1)req.addPostValue("g", condition.getG()+"");
		if(condition.getQage()!=null)req.addPostValue("qage", condition.getQage());
		if(condition.getJoinTime()!=null)req.addPostValue("join_time", condition.getJoinTime());
		if(condition.getLv()!=-1)req.addPostValue("lv", condition.getLv()+"");
		if(condition.getLastSpeakTime()!=null)req.addPostValue("last_speak_time", condition.getLastSpeakTime());
		if(condition.getKey()!=null)req.addPostValue("key", condition.getKey());

		req.addHeader("Referer", "http://qun.qq.com/member.html");

		return req;
	}

	/** {@inheritDoc} */
	@Override
	protected void onHttpStatusOK(QQHttpResponse response) throws QQException,
			JSONException {
//		{"adm_max":10,"adm_num":0,"count":5,"ec":0,"max_count":500,"mems":[{"card":"","flag":0,"g":0,"join_time":1346172585,"last_speak_time":0,"lv":{"level":1,"point":0},"nick":"我是識月","qage":8,"role":0,"tags":"-1","uin":569398403},{"card":"","flag":0,"g":1,"join_time":1346211778,"last_speak_time":0,"lv":{"level":1,"point":0},"nick":"月颖","qage":2,"role":2,"tags":"-1","uin":2564781987},{"card":"","flag":0,"g":0,"join_time":1364884811,"last_speak_time":0,"lv":{"level":1,"point":0},"nick":"李军","qage":4,"role":2,"tags":"-1","uin":1002053815},{"card":"","flag":0,"g":1,"join_time":1364884897,"last_speak_time":0,"lv":{"level":1,"point":0},"nick":"name","qage":2,"role":2,"tags":"-1","uin":2280410025},{"card":"","flag":0,"g":0,"join_time":1364884984,"last_speak_time":0,"lv":{"level":1,"point":0},"nick":"tracy","qage":2,"role":2,"tags":"-1","uin":1401982225}],"search_count":5,"svr_time":1420608628,"vecsize":1}
//		{"adm_max":10,"adm_num":2,"count":8,"ec":0,"levelname":{"1":"路人","2":"师兄","3":"少侠","4":"大侠","5":"掌门","6":"宗师"},"max_count":500,"mems":[{"card":"yue","flag":0,"g":0,"join_time":0,"last_speak_time":1419238990,"lv":{"level":2,"point":22},"nick":"我是識月","qage":8,"role":0,"tags":"-1","uin":569398403},{"card":"lijun","flag":0,"g":0,"join_time":1419232468,"last_speak_time":1419238024,"lv":{"level":2,"point":13},"nick":"李军","qage":4,"role":1,"tags":"-1","uin":1002053815},{"card":"","flag":0,"g":1,"join_time":1418993822,"last_speak_time":1418996225,"lv":{"level":2,"point":5},"nick":"name","qage":2,"role":1,"tags":"-1","uin":2280410025},{"card":"","flag":0,"g":0,"join_time":1346170317,"last_speak_time":0,"lv":{"level":1,"point":0},"nick":"tracy","qage":2,"role":2,"tags":"-1","uin":1401982225},{"card":"","flag":0,"g":1,"join_time":1364906975,"last_speak_time":0,"lv":{"level":1,"point":0},"nick":"aa","qage":2,"role":2,"tags":"-1","uin":2414081615},{"card":"","flag":0,"g":1,"join_time":1381914943,"last_speak_time":0,"lv":{"level":1,"point":0},"nick":"yue0009","qage":2,"role":2,"tags":"-1","uin":1668008346},{"card":"","flag":0,"g":1,"join_time":1383301096,"last_speak_time":0,"lv":{"level":1,"point":0},"nick":"yue0022","qage":2,"role":2,"tags":"-1","uin":1127198273},{"card":"","flag":0,"g":1,"join_time":1418997115,"last_speak_time":0,"lv":{"level":1,"point":0},"nick":"风雨梦","qage":2,"role":2,"tags":"-1","uin":154719534}],"search_count":8,"svr_time":1420609613,"vecsize":1}
		/*{
		    "adm_max": 10,
		    "adm_num": 2,
		    "count": 8,
		    "ec": 0,
		    "levelname": {
		        "1": "路人",
		        "2": "师兄",
		        "3": "少侠",
		        "4": "大侠",
		        "5": "掌门",
		        "6": "宗师"
		    },
		    "max_count": 500,
		    "mems": [
		        {
		            "card": "yue",
		            "flag": 0,
		            "g": 0,
		            "join_time": 0,
		            "last_speak_time": 1419238990,
		            "lv": {
		                "level": 2,
		                "point": 22
		            },
		            "nick": "我是識月",
		            "qage": 8,
		            "role": 0,
		            "tags": "-1",
		            "uin": 569398403
		        },
		        {
		            "card": "lijun",
		            "flag": 0,
		            "g": 0,
		            "join_time": 1419232468,
		            "last_speak_time": 1419238024,
		            "lv": {
		                "level": 2,
		                "point": 13
		            },
		            "nick": "李军",
		            "qage": 4,
		            "role": 1,
		            "tags": "-1",
		            "uin": 1002053815
		        },
		        {
		            "card": "",
		            "flag": 0,
		            "g": 1,
		            "join_time": 1418993822,
		            "last_speak_time": 1418996225,
		            "lv": {
		                "level": 2,
		                "point": 5
		            },
		            "nick": "name",
		            "qage": 2,
		            "role": 1,
		            "tags": "-1",
		            "uin": 2280410025
		        },
		        {
		            "card": "",
		            "flag": 0,
		            "g": 0,
		            "join_time": 1346170317,
		            "last_speak_time": 0,
		            "lv": {
		                "level": 1,
		                "point": 0
		            },
		            "nick": "tracy",
		            "qage": 2,
		            "role": 2,
		            "tags": "-1",
		            "uin": 1401982225
		        },
		        {
		            "card": "",
		            "flag": 0,
		            "g": 1,
		            "join_time": 1364906975,
		            "last_speak_time": 0,
		            "lv": {
		                "level": 1,
		                "point": 0
		            },
		            "nick": "aa",
		            "qage": 2,
		            "role": 2,
		            "tags": "-1",
		            "uin": 2414081615
		        },
		        {
		            "card": "",
		            "flag": 0,
		            "g": 1,
		            "join_time": 1381914943,
		            "last_speak_time": 0,
		            "lv": {
		                "level": 1,
		                "point": 0
		            },
		            "nick": "yue0009",
		            "qage": 2,
		            "role": 2,
		            "tags": "-1",
		            "uin": 1668008346
		        },
		        {
		            "card": "",
		            "flag": 0,
		            "g": 1,
		            "join_time": 1383301096,
		            "last_speak_time": 0,
		            "lv": {
		                "level": 1,
		                "point": 0
		            },
		            "nick": "yue0022",
		            "qage": 2,
		            "role": 2,
		            "tags": "-1",
		            "uin": 1127198273
		        },
		        {
		            "card": "",
		            "flag": 0,
		            "g": 1,
		            "join_time": 1418997115,
		            "last_speak_time": 0,
		            "lv": {
		                "level": 1,
		                "point": 0
		            },
		            "nick": "风雨梦",
		            "qage": 2,
		            "role": 2,
		            "tags": "-1",
		            "uin": 154719534
		        }
		    ],
		    "search_count": 8,
		    "svr_time": 1420609613,
		    "vecsize": 1
		}*/
		QQStore store = getContext().getStore();
		JSONObject json = new JSONObject(response.getResponseString());
		QmGroupMembers qmGroupMembers = new QmGroupMembers();
		qmGroupMembers.setAdmMax(json.getInt("adm_max"));
		qmGroupMembers.setAdmNum(json.getInt("adm_num"));
		qmGroupMembers.setCount(json.getInt("count"));
		qmGroupMembers.setEc(json.getInt("ec"));
		qmGroupMembers.setMaxCount(json.getInt("max_count"));
		qmGroupMembers.setSearchCount(json.getInt("search_count"));
		qmGroupMembers.setSvrTime(json.getInt("svr_time"));
		qmGroupMembers.setVecsize(json.getInt("vecsize"));
		
		JSONObject jsonLn = json.getJSONObject("levelname");
		Iterator keys = jsonLn.keys();
		HashMap<String, String> levelName = qmGroupMembers.getLevelName();
		while(keys.hasNext()){
			String key = (String)keys.next();
			String name = jsonLn.getString(key);
			levelName.put(key, name);
		}
		
		JSONArray jsonMems = json.getJSONArray("mems");
		int jmemsLen = jsonMems.length();
		for (int i = 0; i < jmemsLen; i++) {
			JSONObject jMem = jsonMems.getJSONObject(i);
			QmGroupMembers.QmGroupMember qmMem = new QmGroupMembers.QmGroupMember();
			qmMem.setCard(jMem.getString("card"));
			qmMem.setFlag(jMem.getInt("flag"));
			qmMem.setG(jMem.getInt("g"));
			qmMem.setJoinTime(jMem.getInt("join_time"));
			qmMem.setLastSpeakTime(jMem.getInt("last_speak_time"));
			JSONObject jLv = jMem.getJSONObject("lv");
			qmMem.setLvLevel(jLv.getInt("level"));
			qmMem.setLvPoint(jLv.getInt("point"));
			
			qmMem.setNick(jMem.getString("nick"));
			qmMem.setQage(jMem.getInt("qage"));
			qmMem.setRole(jMem.getInt("role"));
			qmMem.setTags(jMem.getString("tags"));
			qmMem.setUin(jMem.getInt("uin"));
		}

		
		//添加到Store
//		store.addGroup(group);
	}

}
