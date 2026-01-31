package com.jspiders.taskapi.data.users;

import com.jspiders.taskapi.data.tasks.Task;
import com.jspiders.taskapi.data.tasks.TaskDTO;
import lombok.Data;

import java.util.List;

@Data
public class AppUserDTO {
    private Long userId;
    private String name;
    private String email;
    private String mobile;
    private boolean isActive;
    private List<TaskDTO> taskList;
}
