package iqq.im;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class T_05 {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		new T_05().init();
	}
	private int maxIndex=22;
	private void init() throws FileNotFoundException {
		Scanner s  = new Scanner(new File("t00.txt"));
		while(s.hasNextLine()){
			String line = s.nextLine();
			int index = line.indexOf("@");
			if(index>maxIndex){
				maxIndex = index;
			}
			else{
				String padding = "";
				for (int i = 1; i <= maxIndex - index; i++) {
					padding+=" ";
				}
				line = line.replace("@", padding+"@");
				System.out.println(line);
			}
			
		}
		System.out.println(maxIndex);
	}
}
