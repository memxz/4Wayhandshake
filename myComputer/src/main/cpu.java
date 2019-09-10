package main;
import java.lang.management.ManagementFactory;

import com.sun.management.OperatingSystemMXBean;

public class cpu {
	public static void main(String[]args){
	OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
	// What % CPU load this current JVM is taking, from 0.0-1.0
	System.out.println(osBean.getProcessCpuLoad());//ָCPU�ĸ������
		
	// What % load the overall system is at, from 0.0-1.0
	System.out.println(osBean.getSystemCpuLoad());
}
}