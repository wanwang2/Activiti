package me;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;

public class InstanceTest {

	public static void main(String[] args) {
		ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
		System.out.println("pe:" + pe);
	}
}
