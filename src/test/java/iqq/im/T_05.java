package iqq.im;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class T_05 {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		T_05 t = new T_05();
		TreeSet<Integer> lineNums = t.cal();
		TreeMap<Integer, String> rangeNums = t.range();
		
		HashMap<Integer, Integer> funcs = new HashMap<Integer, Integer>();
		for (Integer integer : lineNums) {
			SortedMap<Integer, String> subMap = rangeNums.headMap(integer);
			SortedMap<Integer, String> tailMap = rangeNums.tailMap(integer);
			if (!subMap.isEmpty()){
				System.out.println(integer + "       " + subMap.lastKey() + "            " +tailMap.firstKey()+"       " + subMap.get(subMap.lastKey()));
				funcs.put(subMap.lastKey(), tailMap.firstKey());
			}
		}
		int lineNum = 0;
		PrintWriter pw = new PrintWriter("t6.js");
		Scanner s  = new Scanner(new File("t5.js"));
		while(s.hasNextLine()){
			lineNum++;
			String line = s.nextLine();
		
			Set<Entry<Integer, Integer>> entries = funcs.entrySet();
			for (Entry<Integer, Integer> entry : entries) {
				Integer start = entry.getKey();
				Integer end = entry.getValue();
				if(lineNum>=start&&lineNum<end){
					line = line.replace("//", "");
				}
			}
			pw.println(line);
		}
		pw.close();
	}
	HashMap<Integer, String>contentMap  = new HashMap<Integer, String>();
	private int maxIndex=0;
	
	public TreeSet<Integer> cal() throws FileNotFoundException{
		  TreeSet<Integer> nums = new TreeSet<Integer>();
		File dir = new File("txt");
		File[] files = dir.listFiles();
		for (File file : files) {
			Scanner s  = new Scanner(file);
			while(s.hasNextLine()){
				String line = s.nextLine();
				int index = line.lastIndexOf(":");
				if(index>-1){
					Integer lineNum = Integer.parseInt(line.substring(index+1));
					nums.add(lineNum);
				}
				
			}
			s.close();
		}
//		for (Integer integer : nums) {
//		System.out.println(integer);
//	}
		return nums;
	}
	public TreeMap<Integer, String> range() throws FileNotFoundException{
		TreeMap<Integer, String> rangeMap = new TreeMap<Integer, String>();
		Pattern p = Pattern.compile("f\\(\"([^\"]+)\", function");
		File jsFile = new File("t5.js");
		Scanner s = new Scanner(jsFile);
		while(s.hasNextLine()){
			lineNum++;
			
			String line = s.nextLine();
			contentMap.put(lineNum, line);
			Matcher m = p.matcher(line);
			if(m.find()){
				String func = m.group(1);
				rangeMap.put(lineNum, func);
			}
		}
		s.close();
		Set<Entry<Integer, String>> entries = rangeMap.entrySet();
//		for (Entry<Integer, String> entry : entries) {
//			System.out.println(entry.getKey()+"           "+entry.getValue());
//		}

		return rangeMap;
	}
	private Integer lineNum = 0;
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
