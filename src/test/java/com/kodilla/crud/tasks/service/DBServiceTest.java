package com.kodilla.crud.tasks.service;

import com.kodilla.crud.tasks.domain.Task;
import com.kodilla.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DBServiceTest
{
    @Autowired
    private TaskRepository repository;

    @Autowired
    private DBService dbService;

    @Test
    public void doSaveGetDeleteTask()
    {
        //Given
        List<Long> taskIDs = new ArrayList<>();
        List<Task> tasks = repository.findAll();
        int numOfTasks = tasks.size();
        for (Task task : tasks)
        {
            taskIDs.add(task.getTaskID());
        }
        Collections.sort(taskIDs);
        Long lastTaskID = taskIDs.get(numOfTasks - 1);
        Task task1 = new Task(lastTaskID + 1L, "Task1", "Task to test");
        Task task2 = new Task(lastTaskID + 2L, "Task2", "Task to test");
        //When
        dbService.saveTask(task1);
        dbService.saveTask(task2);
        int expNumOfTasks = repository.findAll().size();
        int actNumOfTasks = dbService.getAllTasks().size();
        Task actLastTask = dbService.getTaskByTaskID(lastTaskID + 2L);
        Long actLastTaskID = actLastTask.getTaskID();
        dbService.deleteTask(lastTaskID + 1L);
        dbService.deleteTask(lastTaskID + 2L);
        //Then
        Assert.assertEquals(expNumOfTasks, actNumOfTasks);
        Assert.assertTrue(lastTaskID + 2L == actLastTaskID);
        Assert.assertEquals(numOfTasks, repository.findAll().size());
    }

    @Test
    public void fetchGettingNullTask()
    {
        //Given
        List<Long> taskIDs = new ArrayList<>();
        List<Task> tasks = repository.findAll();
        int numOfTasks = tasks.size();
        for (Task task : tasks)
        {
            taskIDs.add(task.getTaskID());
        }
        Collections.sort(taskIDs);
        Long lastTaskID = taskIDs.get(numOfTasks - 1);
        //When
        boolean isNotNullTask = dbService.getTask(lastTaskID + 1L).isPresent();
        //Then
        System.out.println(isNotNullTask);
        Assert.assertFalse(isNotNullTask);
    }
}