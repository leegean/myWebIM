package iqq.im;

import java.net.URI;
import java.util.Date;

import org.apache.http.HttpHost;
import org.apache.http.client.utils.URIUtils;
import org.omg.CORBA.DATA_CONVERSION;

public class T07 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		URI uri = URI.create("http://www.baidu.com/index.html");
		HttpHost httphost = URIUtils.extractHost(uri);
		System.out.println(uri.getHost()+"      "+httphost.toURI());

		System.out.println("\u0000\n\u0000\u0000\u0000\u0000\u0002\u5B8B\u4F53\r");
		System.out.println(new Date().getTime()+"        "+System.currentTimeMillis());
//		MJhvSq759nZ+U0MDME9kkF5cXdSLR7xPfbKFgqKCU4lew+X6BT4fG4E893/Cd0V3ZKMuTvN2q06aKEN9rUUcv6HaSQyLH3oEP6gb3wjBd+T3I1IVs8JxkQV+YC+hjOxbkVJ/LdibSa25WgSioOTiJYnkP3QMT+F5+Xe8PjZTEF/daRKfNgW+nvYlfUX1c2cohF4EUHXw7W+OCmn4LUnPBQ
	}

}
