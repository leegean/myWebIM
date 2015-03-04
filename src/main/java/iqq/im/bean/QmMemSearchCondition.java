package iqq.im.bean;

public class QmMemSearchCondition {
	// 默认
//  gc:260334785
//  st:0
//  end:20
//  sort:0
//  bkn:270778146
	
//条件
//	gc:19274031
//	st:0
//	end:20
//	sort:0
//	credit:1
//	g:0
//	qage:3|5
//	join_time:7776000|15552000
//	lv:2
//	last_speak_time:7776000|15552000
//	bkn:605178519
	
	private String gc;
	private int st;
	private int end = 20;
	private int sort;
	private int creadit = -1;//1：有不良记录者
	private int g = -1;
	private String qage;
	private String joinTime;
	private int lv = -1;
	private String lastSpeakTime;
	private String key;
	public QmMemSearchCondition() {
		// TODO Auto-generated constructor stub
//		var searchResult = {
//				'不限' : -1,
//				'不良记录成员' : 1,
//				'男' : '0',
//				'女' : '1',
//				// '半年内': '1|15552000',
//				// '半年-1年' : '15552000|31104000',
//				// '1年-3年' : '31104000|93312000',
//				// '3-5年' : '93312000|155520000',
//				// '5年以上' : '155520000|0',
//				'1年内': '0|1',
//				'1年-3年' : '1|3',
//				'3年-5年' : '3|5',
//				'5-7年' : '5|7',
//				'7年以上' : '7|0',		
//				'1个月内' : '1|2592000',
//				'1-3个月' : '2592000|7776000',
//				'3-6个月' : '7776000|15552000',
//				'6-12个月' : '15552000|31536000',
//				'12个月以上' : '31536000|0'
//			}	
	}
	public String getGc() {
		return gc;
	}
	public void setGc(String gc) {
		this.gc = gc;
	}
	public int getSt() {
		return st;
	}
	public void setSt(int st) {
		this.st = st;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getCreadit() {
		return creadit;
	}
	public void setCreadit(int creadit) {
		this.creadit = creadit;
	}
	public int getG() {
		return g;
	}
	public void setG(int g) {
		this.g = g;
	}
	public String getQage() {
		return qage;
	}
	public void setQage(String qage) {
		this.qage = qage;
	}
	public String getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(String joinTime) {
		this.joinTime = joinTime;
	}
	public int getLv() {
		return lv;
	}
	public void setLv(int lv) {
		this.lv = lv;
	}
	public String getLastSpeakTime() {
		return lastSpeakTime;
	}
	public void setLastSpeakTime(String lastSpeakTime) {
		this.lastSpeakTime = lastSpeakTime;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	

}
