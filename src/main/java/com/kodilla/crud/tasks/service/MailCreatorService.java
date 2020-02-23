package com.kodilla.crud.tasks.service;

import com.kodilla.crud.tasks.config.AdminConfig;
import com.kodilla.crud.tasks.domain.TaskDTO;
import com.kodilla.crud.tasks.mapper.TaskMapper;
import com.kodilla.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MailCreatorService
{
    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Value("${info.app.name}")
    private String appName;

    @Value("${info.company.name}")
    private String companyName;


    public String buildDailyInfoEmail(String message)
    {
        List<String> tasks = new ArrayList<>();
        List<TaskDTO> taskDTOList = taskMapper.mapToTaskDTOList(taskRepository.findAll());
        tasks = taskDTOList.stream()
                .map(t -> t.getTaskTitle())
                .collect(Collectors.toList());
        boolean isToBeShown = false;
        if(taskRepository.findAll().size() > 0)
        {
            isToBeShown = true;
        }

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("button", "View my tasks");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("appName", appName);
        context.setVariable("companyName", companyName);
        context.setVariable("preview_message", "Daily list of your tasks");
        context.setVariable("show_button", isToBeShown);
        context.setVariable("is_friend", true);
        context.setVariable("tasks_list", tasks);
        return templateEngine.process("mail/daily-tasks-info-mail", context);
    }

    public String buildTrelloCardEmail(String message)
    {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("appName", appName);
        context.setVariable("companyName", companyName);
        context.setVariable("preview_message", "New Trello card was created");
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
