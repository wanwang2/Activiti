package me;

import java.io.InputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

public class startTest {
	
	ProcessEngine processEngine   = ProcessEngines.getDefaultProcessEngine();
	
	@Test
	public void deploymentProcessDefinition_inputStream() {
		InputStream inputStreamBpmn = this.getClass().getResourceAsStream("start.bpmn");
		InputStream inputStreamPng = this.getClass().getResourceAsStream("start.png");
		
		DeploymentBuilder deploymentBuilder = processEngine.getRepositoryService()//与流程定义相关的service
		.createDeployment()//创建一个部署对象
		.name("开始活动")//取个名字
		.addInputStream("start.bpmn", inputStreamBpmn)
		.addInputStream("start.png", inputStreamPng);
		 Deployment deployment =deploymentBuilder.deploy();//部署完毕
		
		//输出信息
        System.out.println("部署Id:"+deployment.getId());  
        System.out.println("部署名称:"+deployment.getName());
	}
	
	/**启动流程实例**/
	@Test
	public void startProcessInstance() {
		String processDefinitionkey="start";
		ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey(processDefinitionkey);
		System.out.println("流程实例ID："+pi.getId());
        System.out.println("流程定义ID："+pi.getProcessDefinitionId());
        /**判断流程是否结束,查询正在执行的执行对象表**/
        ProcessInstance rpi =  processEngine.getRuntimeService().createProcessInstanceQuery()
        .processInstanceId(pi.getId()).singleResult();
        //说明流程实例结束了
        if(rpi == null) {//正在执行的任务为空 从历史表查询
        HistoricProcessInstance hpi =	processEngine.getHistoryService().createHistoricProcessInstanceQuery()
        	.processInstanceId(pi.getId())
        	.singleResult();
        	System.out.println(hpi.getId()+"  "+hpi.getStartTime()+" "+hpi.getEndTime()
        	+"  "+hpi.getDurationInMillis());
        }
	}
	
}	
