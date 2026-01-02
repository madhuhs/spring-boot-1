package com.jspiders.taskapi.services;

import com.jspiders.taskapi.data.CreateTaskRequest;
import com.jspiders.taskapi.data.Task;
import com.jspiders.taskapi.data.UpdateTaskRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {
    ResponseEntity<String> createTask(CreateTaskRequest createTaskRequest);
    ResponseEntity<List<Task>> getAllTasks();
    ResponseEntity<Task> getTaskByID(Long taskId);
    ResponseEntity<String> updateTask(UpdateTaskRequest updateTaskRequest);
    ResponseEntity<String> deleteTaskByID(Long taskId);
}
