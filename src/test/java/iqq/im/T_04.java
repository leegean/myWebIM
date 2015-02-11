package iqq.im;

import iqq.im.core.QQConstants;
import iqq.im.util.QQEncryptor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.text.DateFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class T_04 {

	public static void main(String[] args) throws ScriptException, FileNotFoundException, JSONException, MalformedURLException {
		// TODO Auto-generated method stub
		ScriptEngineManager sem = new ScriptEngineManager();    /*script引擎管理*/  
        ScriptEngine se = sem.getEngineByName("javascript");           /*script引擎*/  
              
        se.eval(" var window = new Object(); var document = new Object();navigator=new Object();navigator.userAgent=''") ;                     /* 执行一段script */
        se.eval(new FileReader("ext.js"))  ;
        String ts = "0";
        String tc = "0";
        String p = "0";
//        se.eval("i.incoming("+ts+","+tc+","+p+")");
//        GregorianCalendar.getInstance().toString()
            Object result = se.eval("(new Date).toLocaleString();")  ;
            System.out.println(result);
//            System.out.println(String.format("%.0f", result)+"timestamp = ");
//            Bindings bindings = se.getContext().getBindings(ScriptContext.ENGINE_SCOPE);
//    		Set<Entry<String, Object>> entrySet = bindings.entrySet();
//    		for (Entry<String, Object> entry : entrySet) {
//    			System.out.println(entry.getKey()+"    "+entry.getValue());
//    		}
//            se.eval("my().show()");
            
//            JSONArray jsArray = new JSONArray("[{\"version\":\"1.0\",\"minimumVersion\":\"0.9\",\"channel\":\"/meta/handshake\",\"supportedConnectionTypes\":[\"callback-polling\"],\"advice\":{\"timeout\":60000,\"interval\":0},\"id\":\"1\",\"ext\":{\"ack\":true,\"timesync\":{\"tc\":1423104001494,\"l\":0,\"o\":0}},\"timestamp\":\"Thu, 05 Feb 2015 02:40:01 GMT\"}]");
//            jsArray.getJSONObject(0).put("version", "2.0");
//    		System.out.println(jsArray.toString());
            JSONArray jsArray = new JSONArray("[{\"version\":\"1.0\",\"minimumVersion\":\"0.9\",\"channel\":\"/meta/handshake\"," +
    				"\"supportedConnectionTypes\":[\"callback-polling\"],\"advice\":{\"timeout\":60000,\"interval\":0},\"id\":\"1\"," +
    				"\"ext\":{\"ack\":true,\"timesync\":{\"tc\":1423104001494,\"l\":0,\"o\":87593}},\"timestamp\":\"Thu, 05 Feb 2015 02:40:01 GMT\"}]");
    		JSONObject jsObject = jsArray.getJSONObject(0);
    		jsObject.put("id", "1");
    		 JSONObject ext = jsObject.getJSONObject("ext");
    		JSONObject timesync =ext.getJSONObject("timesync");
    		timesync.put("tc", QQEncryptor.getWbTimesyncTc());
    		timesync.put("l", QQEncryptor.getWbTimesyncL());
    		timesync.put("o", QQEncryptor.getWbTimesyncO());
    		jsObject.put("timestamp", QQEncryptor.getWbTimestamp());
    		System.out.println(jsArray.toString());
    		
    		Pattern pattern = Pattern.compile(QQConstants.REGXP_WEBIMCB);
    		Matcher m = pattern.matcher("try{parent.webimCB._callback0([{\"id\":\"7\",\"subscription\":\"/im/qeitlb_2645052603\",\"successful\":true,\"channel\":\"/meta/subscribe\",\"ext\":{\"timesync\":{\"ts\":1423197700753,\"tc\":1423197652679,\"p\":36,\"a\":1076}}}])}catch(e){}");
    		if(m.find()){
    			System.out.println("----------------");
    			System.out.println(m.group(1));
    		}
    		
    		
    		System.out.println(se.eval("encodeURIComponent('++)')"));
    		String s1 = "http://14.76.web1.im.weibo.com/im/handshake?jsonp=parent.webimCB._callback0&message=%5B%7B%22ext%22%3A%7B%22timesync%22%3A%7B%22l%22%3A0%2C%22tc%22%3A%221423465143507%22%2C%22o%22%3A0%7D%2C%22ack%22%3Atrue%7D%2C%22minimumVersion%22%3A%220.9%22%2C%22supportedConnectionTypes%22%3A%5B%22callback-polling%22%5D%2C%22advice%22%3A%7B%22interval%22%3A0%2C%22timeout%22%3A60000%7D%2C%22channel%22%3A%22%2Fmeta%2Fhandshake%22%2C%22id%22%3A%221%22%2C%22version%22%3A%221.0%22%2C%22timestamp%22%3A%22Mon%2C%2009%20Feb%202015%2006%3A59%3A03%20GMT%22%7D%5D&Mon Feb 09 2015 14:59:03 GMT+0800 (CST)";
    		System.out.println(new URL("http://13.76.web1.im.weibo.com/").getHost());
//    		System.out.println((BigDecimal)se.eval("1234444444444455"));
    		
    		
    		
    		String respStr = "try{parent.webimCB._callback0([{\"id\":\"1\",\"minimumVersion\":\"1.0\",\"supportedConnectionTypes\":[\"callback-polling\",\"long-polling\"],\"successful\":true,\"channel\":\"/meta/handshake\",\"ext\":{\"timesync\":{\"ts\":1423530847528,\"tc\":1423530787332,\"p\":0,\"a\":-60196},\"ack\":true},\"clientId\":\"3gtqu1qrzyjwzj2i6xtzinl2w2tsiv\",\"version\":\"1.0\"}])}catch(e){}";
    		Pattern pattern1 = Pattern.compile(QQConstants.REGXP_WEBIMCB);
    		Matcher m1 = pattern1.matcher(respStr);
    		if(m1.matches()){
    			JSONArray json = new JSONArray(m1.group(1));
    			JSONObject jsonO = json.getJSONObject(0);
    			JSONObject ext1 = jsonO.getJSONObject("ext");
    			JSONObject timesync1 = ext1.getJSONObject("timesync");
    			String ts1 = timesync1.getString("ts");
    			String tc1 = timesync1.getString("tc");
    			String p1 = timesync1.getString("p");
    			QQEncryptor.updateWbTimesync(ts1, tc1, p1);
    			System.out.println( QQEncryptor.getWbTimesyncL()+ "           "+QQEncryptor.getWbTimesyncO());
	}
    		
    		System.out.println(se.eval("(new Date).getTime()+'';"));
	}

}
