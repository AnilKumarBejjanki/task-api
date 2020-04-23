package com.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@PostMapping
	public String completeTask(@RequestParam Long processInstanceId, @RequestParam List<String> status,
			String taskName) {
		return taskService.completeTaskUsingProcessIdAndTaskName(processInstanceId, status, taskName);
	}

}
