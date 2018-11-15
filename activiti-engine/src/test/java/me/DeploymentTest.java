package me;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

public class DeploymentTest {
	
	//ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
	
	@Test
	public void testDeploy() {
		ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
		DeploymentBuilder deploymentBuilder = pe.getRepositoryService().createDeployment();
		deploymentBuilder
			.name("UserInfoAudit2")
			.addClasspathResource("UserInfoAudit2.bpmn")
			.addClasspathResource("UserInfoAudit2.png");
		Deployment deployment = deploymentBuilder.deploy();
		System.out.println("deployment:" + deployment.getId());
	}
	
	@Test
	public void testStart() {
		ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
		String key = "UserInfoAudit2Id";//userInfoAuditId

		ProcessInstance processInstance = pe.getRuntimeService().startProcessInstanceByKey(key);
		System.out.println("processInstance.id:" + processInstance.getId());
		System.out.println("processInstance.activityId:" + processInstance.getActivityId());
	}
}
