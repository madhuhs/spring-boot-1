package com.jspiders.taskapi.data;

import lombok.Data;

@Data
public class UpdateTaskRequest {
    private Long taskId;
    private String title;
    private String description;
    private String status;
}
