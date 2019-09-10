package main;


import org.junit.runners.Parameterized;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.*;  
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;  
import java.lang.reflect.Proxy;  
import java.util.Calendar;  
import java.text.SimpleDateFormat;  
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BasicIdent2Test {
	String msg1="";
	String msg2="";
	String msg3="";
	String msg4="";
	
	BasicIdent2 bi=new BasicIdent2();
	WindowsInfoUtil usage=new WindowsInfoUtil();
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testMessage1() {
		msg1="message1wwwww";
		
		bi.buildSystem();
		bi.message1();
		
		//System.out.println(msg1);
		System.out.println("T1="+bi.T1);
		System.out.println("T2="+bi.T2);
	        assertTrue(bi.T1.isEqual(bi.T2));
	        System.out.println("Message Intergration code is verified");
	        System.out.println("cpu utilization ratio=" + usage.getCpuRatioForWindows());
	        System.out.println("Available Memory ratio=" + WindowsInfoUtil.getMemery()); 
	}

	@Test
	public void testMessage2() {
		msg2="message2wwwww";
		
		bi.buildSystem();
		bi.message2();
		
		//System.out.println(msg2);
		System.out.println("T3="+bi.T3);
		System.out.println("T4="+bi.T4);
	        assertTrue(bi.T3.isEqual(bi.T4));
	        System.out.println("Message Intergration code is verified");
	        System.out.println("cpu utilization ratio=" + usage.getCpuRatioForWindows());
	        System.out.println("Available Memory ratio=" + WindowsInfoUtil.getMemery()); 
	}

	@Test
	public void testMessage3() {
		msg3="message3";
		
		bi.buildSystem();
		bi.message3();
		
		//System.out.println(msg3);
		System.out.println("T5="+bi.T5);
		System.out.println("T6="+bi.T6);
	        assertTrue(bi.T5.isEqual(bi.T6));
	        System.out.println("Message Intergration code is verified");
	        System.out.println("cpu utilization ratio=" + usage.getCpuRatioForWindows());
	        System.out.println("Available Memory ratio=" + WindowsInfoUtil.getMemery()); 
	}

	@Test
	public void testMessage4() {
		msg4="message4wwwww";
		
		bi.buildSystem();
		bi.message4();
		
		//System.out.println(msg4);
		System.out.println("T7="+bi.T7);
		System.out.println("T8="+bi.T8);
	        assertTrue(bi.T7.isEqual(bi.T8));
	        System.out.println("Message Intergration code is verified");
	        System.out.println("cpu utilization ratio=" + usage.getCpuRatioForWindows());
	        System.out.println("Available Memory ratio=" + WindowsInfoUtil.getMemery()); 
	}
	

	@Test
	public void testVerifyEquiity() {
		bi.buildSystem();
		bi.computePTK1();
		bi.computePTK2();
		
		System.out.println("Ka="+bi.Ka);
		System.out.println("Kb="+bi.Kb);
	        assertTrue(bi.Ka.isEqual(bi.Kb));
	        System.out.println("PMK is confirmed Same bwtween Supplicent and Authenticator, And both install PTK ");
	
	}

}
