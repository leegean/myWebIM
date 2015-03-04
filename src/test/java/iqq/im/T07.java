package iqq.im;

import java.net.URI;

import org.apache.http.HttpHost;
import org.apache.http.client.utils.URIUtils;

public class T07 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		URI uri = URI.create("http://www.baidu.com/index.html");
		HttpHost httphost = URIUtils.extractHost(uri);
		System.out.println(uri.getHost()+"      "+httphost.toURI());

		System.out.println("\u0000\n\u0000\u0000\u0000\u0000\u0002\u5B8B\u4F53\r");
	}

}
