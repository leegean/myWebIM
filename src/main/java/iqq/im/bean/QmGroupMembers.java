/**
 * 
 */
package iqq.im.bean;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Administrator
 *2015-1-7
 */
public class QmGroupMembers {

	private int admMax;
	private int admNum;
	private int count;
	private int ec;
	private HashMap<String, String> levelName = new HashMap<String, String>();
	private int maxCount;
	private ArrayList<QmGroupMember> mems = new ArrayList<QmGroupMembers.QmGroupMember>();
	public static class QmGroupMember{
		private String card;//群名片
		private int flag;
		private int g;//性别   0:男；1：女；255：未知
		private long joinTime;//0:2012年5月以前
		private long lastSpeakTime;
		private int lvLevel;//等级
		private int lvPoint;//积分
		private String nick;
		private int qage;//q龄
		private int role;//0:群主；1：群管理员；2：群成员
		private String tags;
		private long uin;
		public String getCard() {
			return card;
		}
		public void setCard(String card) {
			this.card = card;
		}
		public int getFlag() {
			return flag;
		}
		public void setFlag(int flag) {
			this.flag = flag;
		}
		public int getG() {
			return g;
		}
		public void setG(int g) {
			this.g = g;
		}
		public long getJoinTime() {
			return joinTime;
		}
		public void setJoinTime(long joinTime) {
			this.joinTime = joinTime;
		}
		public long getLastSpeakTime() {
			return lastSpeakTime;
		}
		public void setLastSpeakTime(long lastSpeakTime) {
			this.lastSpeakTime = lastSpeakTime;
		}
		public int getLvLevel() {
			return lvLevel;
		}
		public void setLvLevel(int lvLevel) {
			this.lvLevel = lvLevel;
		}
		public int getLvPoint() {
			return lvPoint;
		}
		public void setLvPoint(int lvPoint) {
			this.lvPoint = lvPoint;
		}
		public String getNick() {
			return nick;
		}
		public void setNick(String nick) {
			this.nick = nick;
		}
		public int getQage() {
			return qage;
		}
		public void setQage(int qage) {
			this.qage = qage;
		}
		public int getRole() {
			return role;
		}
		public void setRole(int role) {
			this.role = role;
		}
		public String getTags() {
			return tags;
		}
		public void setTags(String tags) {
			this.tags = tags;
		}
		public long getUin() {
			return uin;
		}
		public void setUin(long uin) {
			this.uin = uin;
		}
	}
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
	private int searchCount;
	private long svrTime;
	private int vecsize;
	public int getAdmMax() {
		return admMax;
	}
	public void setAdmMax(int admMax) {
		this.admMax = admMax;
	}
	public int getAdmNum() {
		return admNum;
	}
	public void setAdmNum(int admNum) {
		this.admNum = admNum;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getEc() {
		return ec;
	}
	public void setEc(int ec) {
		this.ec = ec;
	}
	public HashMap<String, String> getLevelName() {
		return levelName;
	}
	public void setLevelName(HashMap<String, String> levelName) {
		this.levelName = levelName;
	}
	public int getMaxCount() {
		return maxCount;
	}
	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}
	public ArrayList<QmGroupMember> getMems() {
		return mems;
	}
	public void setMems(ArrayList<QmGroupMember> mems) {
		this.mems = mems;
	}
	public int getSearchCount() {
		return searchCount;
	}
	public void setSearchCount(int searchCount) {
		this.searchCount = searchCount;
	}
	public long getSvrTime() {
		return svrTime;
	}
	public void setSvrTime(long svrTime) {
		this.svrTime = svrTime;
	}
	public int getVecsize() {
		return vecsize;
	}
	public void setVecsize(int vecsize) {
		this.vecsize = vecsize;
	}

}
