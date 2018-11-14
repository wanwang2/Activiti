package me;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.Test;

public class DeploymentTest {

	@Test
	public void testDeploy() {
		ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
		DeploymentBuilder deploymentBuilder = pe.getRepositoryService().createDeployment();
		deploymentBuilder.addClasspathResource("UserInfoAudit.bpmn");
		Deployment deployment = deploymentBuilder.deploy();
		System.out.println("deployment:" + deployment.getId());
	}
}
