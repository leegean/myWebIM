package iqq.im;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class T_03 {

	public static void main(String[] args) throws ScriptException, NoSuchMethodException, FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated method stub

		ScriptEngineManager sem = new ScriptEngineManager(); /* script引擎管理 */
		ScriptEngine engine = sem.getEngineByName("javascript"); /* script引擎 */
		engine.getContext().setErrorWriter(new PrintWriter(System.out));
		for (ScriptEngineFactory available : sem.getEngineFactories()) {
		    System.out.println(available.getEngineName());
		}
		
		 engine.eval("var window = new Object();var navigator = new Object();navigator.userAgent = 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36';"); /* 执行一段script */
		Compilable compilable = (Compilable) engine;
	    CompiledScript compiled = compilable.compile(new FileReader("js1.txt"));
	    Object sinaSSOEncoder = compiled.eval();
	    Invocable invocableEngine = (Invocable) engine;
	    
	    Bindings bindings = engine.getContext().getBindings(ScriptContext.ENGINE_SCOPE);
		Set<Entry<String, Object>> entrySet = bindings.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			System.out.println(entry.getKey()+"    "+entry.getValue());
		}
		
//		String callbackvalue  = (String)engine.eval("sinaSSOEncoder.makeRequest('aaaaaaa');");
//		String callbackvalue  = (String)engine.eval("sinaSSOEncoder.base64.makeRequest('aaaaaaa');");
		String callbackvalue  = (String)engine.eval("sinaSSOEncoder.base64.encode('"+URLEncoder.encode("569398403@qq.com", "utf-8")+"');");
//		String callbackvalue = (String) invocableEngine.invokeMethod(sinaSSOEncoder, "makeRequest", "aaaaaaa");
//		String callbackvalue = (String) invocableEngine.invokeMethod(sinaSSOEncoder, "base64.encode", "569398403");
		System.out.println(callbackvalue);
		
		System.out.println(URLEncoder.encode("569398403@qq.com", "utf-8"));
		String str = "{\"retcode\":\"101\",\"reason\":\"\u62b1\u6b49\uff01\u767b\u5f55\u5931\u8d25\uff0c\u8bf7\u7a0d\u5019\u518d\u8bd5\"}";
		System.out.println("" +str+
				"e68bee9f683bb008ac7792d035f5236fe1f2ec689133caebd97e4f5289931284c8ef083ad06dcd878da6e60811c68e51d6e8a60b440b79c4fe385deb4293bf6b88ecb3b996cbad8ef79d4c36da6bd2b93ced2143ed01fe3f106faf1efba38825ac60c5876bdb388ab5e971cbdc4f61b3066894bec4238d3e4670a887de885156".length());
		
		String s1 = "a0d417d72b73c749ed7525c3e0c794107a9e1706b4717d1d1952c0e7e3d9ec688d37cd462e37d39242b1de8cb0e9d90a11ce69f30419b76dbf662bffb0314d0227068e26204215b53183c63ce8330ebb0573ae06931d7e2d1272b810806993d3ecd2d823b152e1f222d8eab75662b5ce48b47094e383ef0e5145879cf717ecdb";
		String s2 = "a65db48facc49b62438a2aa82ff09a2064fa3d8333c4d9d130a07607954a655503fffd3abaf1d21e666018b3aaba292bcb058e61475b148e4f27c80a6f2c39acf23a322fe1c2ccf8298300cb17f2eb38bc987eacad7a3e9df6417aac810c9df7cb4ecfa896b9e4d43b735b9b7435cfc6e1d2ede0af912c8da84b735599480055";
		String s3 = "e68bee9f683bb008ac7792d035f5236fe1f2ec689133caebd97e4f5289931284c8ef083ad06dcd878da6e60811c68e51d6e8a60b440b79c4fe385deb4293bf6b88ecb3b996cbad8ef79d4c36da6bd2b93ced2143ed01fe3f106faf1efba38825ac60c5876bdb388ab5e971cbdc4f61b3066894bec4238d3e4670a887de885156";
		String s4 = "1de8b666bac9d90f75fef3bacf504ad99c44c4203d5aceeb6664772cbf3f241e5b0a56ec5f4ec1f6af12430f4e90566a173246ab9a23830c5eb3b54d35903e23afbee09ca81558d52db5efb599bbe4146ad69fb15f472f2d046097f6136ab9638c61448b3b4035eaa04853a065f12bbe7c8b28c90fa4b0c779b26d8ef5586326";
		String s5 = "89234b5a1c10c0592b4c00b80a8f8af17e5c02cec10c90b2c171e95ffc2e547ba7df082a739595254ffbb323ca350640708c5908a7219664d47be0ee8706a503b0151119e0e028bcddf3f666aa5408aaf1eb29b58ac22d43165b5fd86d643e6652e82bf45e2fed48857cdf958f739b3eb67d5c712fbf6f4c69e3f4a462b715ac";
		String s6 = "d99a7f77b541ce4880e543acb463de9687ea0a12a65e95a44881f91ee69ff5adbd7a8d92e3d9012c8ecfb6ade5065cef0b49f91d0df3dd6ca28952458262254631f6c601583a4895e6785ef63eed836cec1c50020db5c4fa5f013ac065ccea91d776dd4a75e68c734b1b16c897262e3534dd6fbba21969edfff59476ab950bdc";
		
		String s7 = "690512c6313172adf26375c7b77bf6c459f2893e32a74ab1cb6b61adfebe2787994698a38ccf35a2e03d689457507825aecc8d12f73e9aae912e807f3062dc6af94d7ad17dd1248986a11df310ae8f997b593ee02fffe72589d410b81dd4d4c47bb4d842ac42bc61010754949a99eae2166cdb852f12a6d30f90d16447ceb4bc";
		
		System.out.println(s1.length()+"  "+s1.length()+"  "+s2.length()+"  "+s3.length()+"  "+s4.length()+"  "+s5.length()+"  "+s6.length()+"  "+s7.length());
//		http://login.sina.com.cn/sso/prelogin.php?entry=weibo&callback=sinaSSOController.preloginCallBack&su=NTY5Mzk4NDAzJTQwcXEuY29t&rsakt=mod&client=ssologin.js(v1.4.18)&_=1421126543540
//		sinaSSOController.preloginCallBack({"retcode":0,"servertime":1421126570,"pcid":"gz-14863373e0c3615ab981f0a29795032e72c7","nonce":"PHZ8J6","pubkey":"EB2A38568661887FA180BDDB5CABD5F21C7BFD59C090CB2D245A87AC253062882729293E5506350508E7F9AA3BB77F4333231490F915F6D63C55FE2F08A49B353F444AD3993CACC02DB784ABBB8E42A9B1BBFFFB38BE18D78E87A0E41B9B8F73A928EE0CCEE1F6739884B9777E4FE9E88A1BBE495927AC4A799B3181D6442443","rsakv":"1330428213","exectime":3})
		
//		http://login.sina.com.cn/sso/prelogin.php?entry=weibo&callback=sinaSSOController.preloginCallBack&su=NTY5Mzk4NDAzJTQwcXEuY29t&rsakt=mod&client=ssologin.js(v1.4.18)&_=1421129451518
//		sinaSSOController.preloginCallBack({"retcode":0,"servertime":1421129478,"pcid":"gz-c0c48e9258036488ecb79963c2ce6b99d717","nonce":"3C7N15","pubkey":"EB2A38568661887FA180BDDB5CABD5F21C7BFD59C090CB2D245A87AC253062882729293E5506350508E7F9AA3BB77F4333231490F915F6D63C55FE2F08A49B353F444AD3993CACC02DB784ABBB8E42A9B1BBFFFB38BE18D78E87A0E41B9B8F73A928EE0CCEE1F6739884B9777E4FE9E88A1BBE495927AC4A799B3181D6442443","rsakv":"1330428213","exectime":2})
		
//		http://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.4.18)&_=1421126601581
		/*entry:weibo
		gateway:1
		from:
		savestate:7
		useticket:1
		pagerefer:http://www.hao123.com/
		wsseretry:servertime_error
		vsnf:1
		su:NTY5Mzk4NDAzJTQwcXEuY29t
		service:miniblog
		servertime:1421126570
		nonce:PHZ8J6
		pwencode:rsa2
		rsakv:1330428213
		sp:
		a0d417d72b73c749ed7525c3e0c794107a9e1706b4717d1d1952c0e7e3d9ec688d37cd462e37d39242b1de8cb0e9d90a11ce69f30419b76dbf662bffb0314d0227068e26204215b53183c63ce8330ebb0573ae06931d7e2d1272b810806993d3ecd2d823b152e1f222d8eab75662b5ce48b47094e383ef0e5145879cf717ecdb
		a65db48facc49b62438a2aa82ff09a2064fa3d8333c4d9d130a07607954a655503fffd3abaf1d21e666018b3aaba292bcb058e61475b148e4f27c80a6f2c39acf23a322fe1c2ccf8298300cb17f2eb38bc987eacad7a3e9df6417aac810c9df7cb4ecfa896b9e4d43b735b9b7435cfc6e1d2ede0af912c8da84b735599480055
		e68bee9f683bb008ac7792d035f5236fe1f2ec689133caebd97e4f5289931284c8ef083ad06dcd878da6e60811c68e51d6e8a60b440b79c4fe385deb4293bf6b88ecb3b996cbad8ef79d4c36da6bd2b93ced2143ed01fe3f106faf1efba38825ac60c5876bdb388ab5e971cbdc4f61b3066894bec4238d3e4670a887de885156
		1de8b666bac9d90f75fef3bacf504ad99c44c4203d5aceeb6664772cbf3f241e5b0a56ec5f4ec1f6af12430f4e90566a173246ab9a23830c5eb3b54d35903e23afbee09ca81558d52db5efb599bbe4146ad69fb15f472f2d046097f6136ab9638c61448b3b4035eaa04853a065f12bbe7c8b28c90fa4b0c779b26d8ef5586326
		89234b5a1c10c0592b4c00b80a8f8af17e5c02cec10c90b2c171e95ffc2e547ba7df082a739595254ffbb323ca350640708c5908a7219664d47be0ee8706a503b0151119e0e028bcddf3f666aa5408aaf1eb29b58ac22d43165b5fd86d643e6652e82bf45e2fed48857cdf958f739b3eb67d5c712fbf6f4c69e3f4a462b715ac
		d99a7f77b541ce4880e543acb463de9687ea0a12a65e95a44881f91ee69ff5adbd7a8d92e3d9012c8ecfb6ade5065cef0b49f91d0df3dd6ca28952458262254631f6c601583a4895e6785ef63eed836cec1c50020db5c4fa5f013ac065ccea91d776dd4a75e68c734b1b16c897262e3534dd6fbba21969edfff59476ab950bdc
		690512c6313172adf26375c7b77bf6c459f2893e32a74ab1cb6b61adfebe2787994698a38ccf35a2e03d689457507825aecc8d12f73e9aae912e807f3062dc6af94d7ad17dd1248986a11df310ae8f997b593ee02fffe72589d410b81dd4d4c47bb4d842ac42bc61010754949a99eae2166cdb852f12a6d30f90d16447ceb4bc
		sr:1440*900
		encoding:UTF-8
		cdult:2
		domain:weibo.com
		prelt:55877
		returntype:TEXT*/
		
		/*entry:weibo
		gateway:1
		from:
		savestate:7
		useticket:1
		pagerefer:http://www.hao123.com/
		wsseretry:servertime_error
		vsnf:1
		su:NTY5Mzk4NDAzJTQwcXEuY29t
		service:miniblog
		servertime:1421129478
		nonce:3C7N15
		pwencode:rsa2
		rsakv:1330428213
		sp:e68bee9f683bb008ac7792d035f5236fe1f2ec689133caebd97e4f5289931284c8ef083ad06dcd878da6e60811c68e51d6e8a60b440b79c4fe385deb4293bf6b88ecb3b996cbad8ef79d4c36da6bd2b93ced2143ed01fe3f106faf1efba38825ac60c5876bdb388ab5e971cbdc4f61b3066894bec4238d3e4670a887de885156
		sr:1440*900
		encoding:UTF-8
		cdult:2
		domain:weibo.com
		prelt:2085
		returntype:TEXT*/
		
		
//		{"retcode":"101","reason":"\u767b\u5f55\u540d\u6216\u5bc6\u7801\u9519\u8bef"}
	}

}
