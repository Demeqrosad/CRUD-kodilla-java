package com.kodilla.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor()
public class TaskDTO
{
    private Long taskID;
    private String taskTitle;
    private String taskContent;
}
