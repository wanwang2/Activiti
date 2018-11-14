package me;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.junit.Test;

public class InstanceTest {

	@Test
	public void testInstance() {
		ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
		System.out.println("pe:" + pe);
		TaskService taskService = pe.getTaskService();
		System.out.println("taskService:" + taskService);
	}
}
