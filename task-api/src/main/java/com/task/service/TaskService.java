package com.task.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kie.server.api.model.instance.TaskSummary;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.UserTaskServicesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbpm.task.Employee;

@Service
public class TaskService {

	@Autowired
	private KieServerConfiguration kieConfiguration;

	public String completeTaskUsingProcessIdAndTaskName(Long processInstanceId, List<String> status, String taskName) {
		KieServicesClient kieServicesClient = kieConfiguration.getKieClient();
		UserTaskServicesClient userTaskClient = kieServicesClient.getServicesClient(UserTaskServicesClient.class);
		List<TaskSummary> taskSummaryList = userTaskClient.findTasksByStatusByProcessInstanceId(processInstanceId,
				status, 0, 100);

		if (taskSummaryList == null || taskSummaryList.isEmpty()) {
			return "No Task found for the process Instance Id" + processInstanceId;
		}

		for (TaskSummary taskSummary : taskSummaryList) {
			if (taskSummary.getName().equals(taskName)) {
				// Here we are considering single Actor if its group then
				// claim-->start-->complete
				if (taskSummary.getStatus().equals("Reserved")) {
					System.out.println("task is reserved state!!!!!!!!!!!&&&&");
					Map<String, Object> map = new HashMap<String, Object>();
					Employee emp = new Employee();
					emp.setId(1);
					emp.setName("Anil Kumar Bejjanki");
					map.put("emp_out", emp);
					userTaskClient.startTask(taskSummary.getContainerId(), taskSummary.getId(),
							taskSummary.getActualOwner());
					userTaskClient.completeTask(taskSummary.getContainerId(), taskSummary.getId(),
							taskSummary.getActualOwner(), map);
					System.out.println("task started and completed$$$$$$$$$$$$$$$$$$$");
				}

			} else {
				return "No Task With the name :::" + taskName;
			}
		}

		return "Success";
	}
}
