package iqq.im;
import java.io.ObjectInputStream.GetField;
import java.util.Scanner;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;


public class T_01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScriptEngineManager sem = new ScriptEngineManager();    /*script引擎管理*/  
        ScriptEngine se = sem.getEngineByName("javascript");           /*script引擎*/  
        try {  
              
            se.eval(" var $ = new Object(); var navigator = new Object();navigator.appName == 'Microsoft Internet Explorer';") ;                     /* 执行一段script */  
              
            
            Scanner s = new Scanner(T_01.class.getClassLoader().getResourceAsStream("js.txt"));
            StringBuilder sb = new StringBuilder();
            while(s.hasNextLine()){
            	sb.append(s.nextLine());
            }
            s.close();
            Object Encryption = se.eval(sb.toString()) ;      
//            ";º ·"
            String salt = String.valueOf(new char[]{(char)0,(char)0,(char)0,(char)0,(char)59,(char)186,(char)32,(char)183});
            Invocable invocableEngine = (Invocable) se ;   
            String callbackvalue=(String)invocableEngine.invokeMethod(Encryption, "getEncryption","lj198610fdf01", salt,"!WEE");
//            String callbackvalue=(String)invocableEngine.invokeMethod(Encryption, "show");
//            String callbackvalue=(String) invocableEngine.invokeFunction("show" );   /*调用方法中的函数*/  
            System.out.println( callbackvalue ) ;              /** 打印返回值*/  
            String str = "Fi5ElT8TMOq87YUFpA-LK4apjf118cfdXpXAdWWw9Xn627*e*ypbaCV-sHXKzjjJiKKeftGu1P793TRIfVGg1h-PUK84iCUzSRdNG*MX01*eja7vzxgCyljML4O3ge7GdO3wFGmgEzjYA5iZr9CbYxmE53*MvHOANvBOAfDa2kPNKhhrnrUSyElD6MRMUcdXUYWaMgNdauS3m*c5ej8SKQ__";
            System.out.println(str.length());
            int iValue =59;
            char tempC = (char)iValue;//转换为一个字符
            System.out.println("ASCII码为："+iValue+"的字符为："+tempC);
//            w0UBLYYt27svd5Ci4mpDQMFecJX6wVA-ve9nxa2lRIiqSYRAYgLiQsoUCLGzJ2QJjFw8azBe7vBa*DUwPWeWaQYgUpNqRzkimzZCDyjuKLNSu4z4bOGe1p5BzDpVBt9cuxY9Z5S5iBGClqeKb37eyAmtyWfltpYbN4xImMoe*DKpRAQgqGOrWZlo8CEqfYacvvTms2*Nk-vTHIuVA*ScMw__
//            J8PFUXyFoHjRe6SFkILd8Csn6XO-mUyJaDThylZHSnyuWbAevTTtOGdHbv4NyJhIfSu*BrEoYUcHzp7*XKx86UKCVIUvWT*paQuuYh6lt2O0XQ7VAxyzp6n7cMxzpNYj45qk2Pueo0m5O96rTx-lKJdFN5KdUfNHMya9SYHjqtqoWYUA-EFYEemxfKh3-7-XqoPzv2Z4B88_
//            cfYmnJEFUUExlCUXLiL-lr1ms5NSJClYIHOasBioOzviW7PbsvREk2LzXC8WCmBNhOBXqFlBU04y8ofqHmLzvIOBy30rEmvALcXRXSfbC-4GKWh8Ot2QR2mBIbkfSvrxox0S*liG06HEgOi3bc*VpJWQp6*t1BeRhEd4r08BLqn8NB-V-OmCMJg8s95ujIM88758vSwgPBKnSwf7BFOiMg__
//            WNhfUjJgg46nWfgpxbAri5PPwZWVxGdbOLX-IZwJhFvtwxPJQMYHCmIPPon4vDQfFL9IiAKteFXbunPbFOk8amnJTS4wRYHJDnPr6lObr4uSLB*SSijRgDuN5SqTtnS6J1Z-TIpkDqqE7MsTrpKstTXHq1o0192QJbEejIoZOwoLNIjpE9Ug4s729Azw-tzyI4rpqwrs0snXpdx69nemFQ__
//            vyBIJzdmb5k-JIU0Q4nXiiPgP*jm*TT8o*MFks1AjI4QjPqA6k1hmMo*r2*p8B69yMm8Kx7Kh-Wb9Sk7kDcPJFc9Zqkr1*MmqCuZhURABcn4ajgQ3Hy8XtHz*bd3X5OOkV6A3gqfULWqGnq8EyYHLWbtyEVu6Mmhvtj95ayv4EqW*TA8lUGQiYnIixYU*70nMAIy*gTWWJI_
//            5fJ98oCICxnfUd78wkdGQXaHVI6oyssfB8DV0ohbAniAVXr4sBfKE2DwNo5M9MFL093M8wHT*lQMnKSzZcuiWr78CJEvj*Zt5eq3LJ6FETQVSGB7bVx1ELLumJ-pNNAbmHWinJh1DokG-h-j9VBHel2OXDyU8GamMKXUJrkOpAGzqFPloXm-Lsov2YmLiYNjO4v-3VrRNsw_
//            j2zU9z5t-gwb23qxhAlYxj2ciLLj-MUmXti2ObjjJ1VWUW7zNkIDfIbKAx0Y9BnWteXrNbeovA2-7oi0W8-uNPeEh6NexeF20rpllUIyzt7i9N4AwA0PuwcdyFwn2DJRlhP2EcJRCZbVa*hRqZtBxO89lBHgCt2UotLKkZ*wqGFRQN4TdtMAWk2DfcDMSp8cFElIn0lBsscISQeY1p3ruQ__
//            fQMeFQtz6VOHuHwGBlYu-q0J6jiFNZI5XJrAWiC-D9IzM*-weP4E-UIG9tQCySks1c4lD7XfpuOe0LgeacTi6T6wkqM037MNUcJ7hc6rDgCl1EEQwxLi*uErCfamWeRaX5DtEsIGKLatgu46siipPBIO7q0pwlO8vqCS33yxoahmiE8yU7gPEXOD7bAOYlyYWg*JLwFapII_
//            jBQLt8BZx29KXB*ufCFpavLvjnS5y2gxdQ8y0JjTGyIXZXkdIBYt9BfSoDh2yqHnwUaxXulH09d7XmX7uPNExWZ8OpJbyiURRdXg7Dujs0jTl8snrrEcg9QQKd4k8jvd2SGCsAORGJUcDvo6OTwR860d9mejhmM*dNO56tLtn4HQBevR-B*s9Mjyc*P2YbkcgvSkCVMnNyk_
//            0ejLnJ6eqC0YM6433orhKcBvAGgYCZ6I5X1YjWHWv2jxs3P5tutw1TUT6VkQb8Cmb*fqub-CBYRYjro*9EZdigPHiWz89DdrNnhAL5qm0canS4LLdq2KlbZmAQSCocHn*ilyjYn-CAoFLazewdbKBW4ROXE6T0soI5tET-wsjowVjfu57QP8bKV-1bSEk-Pq92PJIlmrPxg_
//            EldEQAPdVIf3KRW9D8x9cutKLafQDi6rzkCjEygqoJeF8P3DtFkaKfaNt-vYsrKPYh-O6eDULEd80kKiOJlPoSf*d6Oy23AATVP4NEAK8YCb8fOEUBcVaTe7jBV3l2YQZWp1Pk3ivu7GMtj4cOxQNRNktJ*7d7-8oyaFISrHf5fQvzQsT1W1pe8TN6*nhlacFJeG-N0tRLY_
//            zi4v01yotUzDyYcm5XwSr466q*JAVxCvbMfkNc6DHpTANyDhjXPNh4PxORYTF*yJOKeTGIJuUoTPmSjBWol5b6icEkqPNg0q9KzIg*VSdN6yzPIjMCFZsw4idcPoNRlXATmbqCMYQcfwRbVKO8UvVZlA8yzpu1ylrXgT*gnDfzPquoQPfhPICeaiA2OYuyOS0-lz5EsNGAaK6fcIDsb8fg__
//            nAJQRRFT6eRqv5KedBspuv1*GwkA4Ydr2qzM*3gB01DiSjE0OEMdqIt9886WtIGjiTkfSajaMulLURBjekd0qVimmTUi-o9zZYCO-un5uMEZ5oyTzzZ9FDkNSLlypltx3gWdJpPDzvqA0jwXSRSKRN9XvQu5JfKbLgPc4MeFePe*BpP28TdHCz*gyY6m1wxI7weeG0jQflc_
//            PokP5i1kzYhtjFOqCtzGu3cbyfagfV3NrMTyAGgssgszC34F15D1WJZcgNT3nLMd*N4mRxcm80an0J7hQtt4E1JvF3HMl9rhYmdymWQ32mCmaP3UzR76R4Qo7iM3O-dEdNnBD5r0qhx8W7OB5eRTOM-D*WpC5E1cXABwfZVXawaM4GdCXkYTN6xjRYXJ7AueRMDKrQGUTtY_
//            DKY7FUnsbszpbD4LCqthGN5ctMjeWttKBqrrofVYTmPe67Fv*iHT8jTjbFoGSKHcOrhuptCBHjhygOE9mY0Sbe-HfH975ZuDDsx9NJw36ELt3DIrXfSl84*Gxr7toijZs-GLXb9BFiSPU7aEwG3aRRMqmKlRrFpq7ilUC7yt3uP90KM6b5IPfF3S10EIMvlbhsedhiX2mwI_
        } catch (Exception e) {   
            e.printStackTrace();  
        }
	}

}
