package com.kodilla.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO
{
    private Long taskID;
    private String taskTitle;
    private String taskContent;
}
