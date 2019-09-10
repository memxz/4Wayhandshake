package main;

import java.lang.reflect.Method;
import java.io.*;


	public class CPUUsageThread {

	public CPUUsageThread() {
	    String s;
	    try {
	        Process ps = Runtime.getRuntime().exec("Pc.bat");
	        BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
	        while((s = br.readLine()) != null) {
	            System.out.println(s);
	        }
	    }
	    catch( Exception ex ) {
	        System.out.println(ex.toString());
	    }
	}
}
