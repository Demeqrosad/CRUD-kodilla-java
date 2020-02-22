package com.kodilla.crud.tasks.controller;

import com.google.gson.Gson;
import com.kodilla.crud.tasks.domain.Task;
import com.kodilla.crud.tasks.domain.TaskDTO;
import com.kodilla.crud.tasks.mapper.TaskMapper;
import com.kodilla.crud.tasks.service.DBService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
@Import(TaskMapper.class)
public class TaskControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskMapper taskMapper;

    @MockBean
    private DBService service;

    @Test
    public void shouldFetchAllTasks() throws Exception
    {
        //Given
        List<TaskDTO> taskDTOList = new ArrayList<>();
        taskDTOList.add(new TaskDTO(1L, "Task1", "Task number 1"));
        taskDTOList.add(new TaskDTO(2L, "Task2", "Task number 2"));
        List<Task> taskList = new ArrayList<>();
        for (TaskDTO taskDTO : taskDTOList)
        {
            taskList.add(taskMapper.mapToTask(taskDTO));
        }
        Mockito.when(service.getAllTasks()).thenReturn(taskList);
        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].taskID", is(2)))
                .andExpect(jsonPath("$[0].taskTitle", is("Task1")))
                .andExpect(jsonPath("$[1].taskContent", is("Task number 2")));
    }

    @Test
    public void shouldFetchOneTasks() throws Exception
    {
        //Given
        TaskDTO taskDTO = new TaskDTO(1L, "Task1", "Task number 1");
        Mockito.when(service.getTask(Mockito.any(Long.class))).thenReturn(Optional.ofNullable(taskMapper.mapToTask(taskDTO)));
        //When & Then
        mockMvc.perform(get("/v1/task/getTask")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("taskID", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taskID", is(1)))
                .andExpect(jsonPath("$.taskTitle", is("Task1")))
                .andExpect(jsonPath("$.taskContent", is("Task number 1")));
    }

    @Test
    public void shouldDeleteOneTasks() throws Exception
    {
        //Given

        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("taskID", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateOneTasks() throws Exception
    {
        //Given
        TaskDTO taskDTO = new TaskDTO(1L, "Task1", "Task number 1");
        Mockito.when(service.saveTask(Mockito.any(Task.class))).thenReturn(taskMapper.mapToTask(taskDTO));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDTO);

        //When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taskID", is(1)))
                .andExpect(jsonPath("$.taskTitle", is("Task1")))
                .andExpect(jsonPath("$.taskContent", is("Task number 1")));
    }

    @Test
    public void shouldCreateNewTasks() throws Exception
    {
        //Given
        TaskDTO taskDTO = new TaskDTO(1L, "Task1", "Task number 1");
        Mockito.when(service.saveTask(Mockito.any(Task.class))).thenReturn(taskMapper.mapToTask(taskDTO));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDTO);

        //When & Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}