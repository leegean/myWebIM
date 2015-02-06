package iqq.im;

import iqq.im.core.QQConstants;
import iqq.im.util.QQEncryptor;

import java.io.FileNotFoundException;
import java.io.FileReader;
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

	public static void main(String[] args) throws ScriptException, FileNotFoundException, JSONException {
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
    				"\"ext\":{\"ack\":true,\"timesync\":{\"tc\":1423104001494,\"l\":0,\"o\":0}},\"timestamp\":\"Thu, 05 Feb 2015 02:40:01 GMT\"}]");
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
	}

}
