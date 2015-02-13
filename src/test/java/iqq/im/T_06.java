package iqq.im;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.json.JSONException;
import org.json.JSONObject;

public class T_06 {

	public static void main(String[] args) throws ScriptException, FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		ScriptEngineManager sem = new ScriptEngineManager();    /*script引擎管理*/  
        ScriptEngine se = sem.getEngineByName("javascript");           /*script引擎*/  
              
//        se.eval(" var window = new Object(); var document = new Object();navigator=new Object();navigator.userAgent=''") ;                     /* 执行一段script */  
//        
//            se.eval(new FileReader("js_wb/req_01.js"))  ;
        System.out.println("\u7528\u6237\u540d\u6216\u5bc6\u7801\u9519\u8bef");
//        NTY5Mzk4NDAzJTQwcXEuY29t
        System.out.println(Base64.getEncoder().encode("569398403@qq.com".getBytes()));
        String asB64 = Base64.getEncoder().encodeToString(URLEncoder.encode("569398403@qq.com", "utf-8").getBytes("utf-8"));  
        System.out.println(asB64); // 输出为: c29tZSBzdHJpbmc=  
        
        try {
			JSONObject json = new JSONObject("{\"weibo.cn\":\"//passport.sina.cn/sso/crossdomain?entry=mweibo&action=login&ticket=ST-MjY0NTA1MjYwMw%3D%3D-1423811713-gz-14EA1C1C92F7848A394DE81780391578\",\"weibo.com\":\"//passport.weibo.com/sso/crossdomain?entry=mweibo&action=login&proj=1&ticket=ST-MjY0NTA1MjYwMw%3D%3D-1423811713-gz-3E6687BE49FBDEA2D3FF3EC6D6286D76\",\"sina.com.cn\":\"//login.sina.com.cn/sso/crossdomain?entry=mweibo&action=login&proj=1&ticket=ST-MjY0NTA1MjYwMw%3D%3D-1423811713-gz-8A5FE7AA8D40250D55422B1FFDE1BAA4\"}");
			json.getString("weibo.cn");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       /* {"ok":1,"msg":"\u79c1\u4fe1\u53d1\u9001\u6210\u529f"}
        ======prelogin出错=======
        #
        {"ok":-2,"msg":"\u4e0d\u5b58\u5728\u6b64\u7528\u6237"}*/
//        %E5%A5%BD%E5%95%8A%0A
        System.out.println(URLEncoder.encode("好啊", "utf-8")+"#"+URLDecoder.decode("%E5%A5%BD%E5%95%8A%0A", "utf-8").length()+"        "+"好啊".length());;
	}

	private static int getA() {
		// TODO Auto-generated method stub
		throw new RuntimeException();
	}

}
