package com.kodilla.crud.tasks.scheduler;

import com.kodilla.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTest
{
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private EmailScheduler emailScheduler;

    @Test
    public void useMessagePatternNoTasks()
    {
        //Given
        long taskCount = 0;
        Mockito.when(taskRepository.count()).thenReturn(taskCount);
        //When
        String messageText = emailScheduler.useMessagePattern();
        //Then
        Assert.assertEquals("Currently in database you got: no tasks. Take a break!", messageText);
    }

    @Test
    public void useMessagePatternOneTask()
    {
        //Given
        long taskCount = 1;
        Mockito.when(taskRepository.count()).thenReturn(taskCount);
        //When
        String messageText = emailScheduler.useMessagePattern();
        //Then
        Assert.assertEquals("Currently in database you got: 1 task. Back to work!", messageText);
    }

    @Test
    public void useMessagePatternManyTasks()
    {
        //Given
        long taskCount = 5;
        Mockito.when(taskRepository.count()).thenReturn(taskCount);
        //When
        String messageText = emailScheduler.useMessagePattern();
        //Then
        Assert.assertEquals("Currently in database you got: 5 tasks. All hands on deck!", messageText);
    }
}