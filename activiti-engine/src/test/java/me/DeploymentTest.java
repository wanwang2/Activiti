package me;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class DeploymentTest {

	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	TaskService taskService = processEngine.getTaskService(); 
	RepositoryService repositoryService = processEngine.getRepositoryService();
	RuntimeService runtimeService = processEngine.getRuntimeService(); 
	
	@Test
	public void testDeploy() {
		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
		deploymentBuilder
			.name("用户信息")
			.addClasspathResource("UserInfoAudit.bpmn")
			.addClasspathResource("UserInfoAudit.png");
		Deployment deployment = deploymentBuilder.deploy();
		
		//输出信息
        System.out.println("部署Id:"+deployment.getId());  
        System.out.println("部署名称:"+deployment.getName());
	}
	
	@Test
	public void testStart() {
		String processDefinitionkey = "UserInfoAuditId";//userInfoAuditId
		Map<String,Object> variables = new HashMap<>();
		variables.put("userId", "旭旭宝宝2");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionkey,variables);
		System.out.println("流程实例ID："+processInstance.getId());
        System.out.println("流程定义ID："+processInstance.getProcessDefinitionId());
	}
	
	/**查看当前的个人任务*/
    @Test  
    public void findMyPersonalTask(){  
		String assignee = "旭旭宝宝2";
		List<Task> taskList = taskService.createTaskQuery().taskAssignee(assignee).list();

        if(taskList!=null&&taskList.size()>0){  
            for(Task task:taskList){  
                System.out.println("任务id:"+task.getId());  
                System.out.println("任务名称:"+task.getName());  
                System.out.println("任务创建时间:"+task.getCreateTime());  
                System.out.println("任务代理人:"+task.getAssignee());  
                System.out.println("流程实例id:"+task.getProcessInstanceId());  
                System.out.println("执行对象id:"+task.getExecutionId());  
                System.out.println("流程定义id:"+task.getProcessDefinitionId());  
                System.out.println("#############################################");  
            }  
        }  
    }
    
    /**完成任务*/  
    @Test  
    public void completeMyPersonalTask(){  
        String taskId="75007";
        taskService.complete(taskId);
        System.out.println("完成任务id:"+taskId);
    } 

    /**删除流程实例*/  
    @Test  
    public void deleteDeployment(){  
        //流程部署id
        String deploymentId="82501";  
        //repositoryService.deleteDeployment(deploymentId); true 级联删除 
        repositoryService.deleteDeployment(deploymentId, true);  
    }  
}
