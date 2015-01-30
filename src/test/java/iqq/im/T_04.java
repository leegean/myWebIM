package iqq.im;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class T_04 {

	public static void main(String[] args) throws ScriptException, FileNotFoundException {
		// TODO Auto-generated method stub
		ScriptEngineManager sem = new ScriptEngineManager();    /*script引擎管理*/  
        ScriptEngine se = sem.getEngineByName("javascript");           /*script引擎*/  
              
        se.eval(" var window = new Object(); var document = new Object();navigator=new Object();navigator.userAgent=''") ;                     /* 执行一段script */  
        
            se.eval(new FileReader("t4.txt"))  ;
              
            
	}

}
