/**
 * 
 */
package iqq.im;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator 2015-1-7
 */
public class T_02 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pStr = "ptuiCB\\('([^']*)','([^']*)','([^']*)','([^']*)','([^']*)', '([^']*)'";
		String str = "ptuiCB('0','0','','1','登录成功！', '李军')";
		
		pStr="[a-z[A-Z]]";
		str="b";
		
		Pattern p = Pattern.compile(pStr);
		Matcher m = p.matcher(str);
		if(m.matches()){
			
			int count = m.groupCount();
			System.out.println("=========count: "+count);
			for (int i = 1; i <= count; i++) {
				System.out.println("========="+m.group(i));
			}
		}
		System.out.println("========="+3*30*24*3600);
		
		System.out.println(Math.nextAfter(0, 100000));
		
	}

}
