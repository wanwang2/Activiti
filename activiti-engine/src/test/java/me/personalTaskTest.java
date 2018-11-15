package me;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class personalTaskTest {
ProcessEngine processEngine   = ProcessEngines.getDefaultProcessEngine();
	
	@Test
	public void deploymentProcessDefinition_inputStream() {
		InputStream inputStreamBpmn = this.getClass().getResourceAsStream("personalTask.bpmn");
		InputStream inputStreamPng = this.getClass().getResourceAsStream("personalTask.png");
		
		DeploymentBuilder deploymentBuilder = processEngine.getRepositoryService()//与流程定义相关的service
		.createDeployment()//创建一个部署对象
		.name("个人任务")//取个名字
		.addInputStream("personalTask.bpmn", inputStreamBpmn)
		.addInputStream("personalTask.png", inputStreamPng);
		 Deployment deployment =deploymentBuilder.deploy();//部署完毕
		
		//输出信息
        System.out.println("部署Id:"+deployment.getId());  
        System.out.println("部署名称:"+deployment.getName());
	}
	
	/**启动流程实例---带参的**/
	@Test
	public void startProcessInstance() {
		String processDefinitionkey="personalTask";
		/**启动流程实例的同时，设置流程变量，使用流程变量来指定任务的办理人,对应task.bpmn文件中#｛userId｝*/
		Map<String,Object> variables = new HashMap<>();
		variables.put("userId", "旭旭宝宝");
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(processDefinitionkey,variables);
		System.out.println("流程实例ID："+processInstance.getId());
        System.out.println("流程定义ID："+processInstance.getProcessDefinitionId());
	}
	
	/**查看当前的个人任务*/
    @Test  
    public void findMyPersonalTask(){  
        String assignee="旭旭宝宝";  
        TaskService taskService=processEngine.getTaskService();  
        List<Task> taskList=taskService.createTaskQuery()
                   .taskAssignee(assignee)
                   .list();
          
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
        String taskId="2505";
        TaskService taskService=processEngine.getTaskService();  
        taskService.complete(taskId);
        System.out.println("完成任务id:"+taskId);  
          
    }  
	
    /**删除流程实例*/  
    @Test  
    public void deleteDeployment(){  
        //流程部署id
        String deploymentId="2101";  
        RepositoryService repositoryService=processEngine.getRepositoryService();  
        //repositoryService.deleteDeployment(deploymentId); true 级联删除 
        repositoryService.deleteDeployment(deploymentId, true);  
          
    }  
}	
