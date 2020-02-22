package com.kodilla.crud.tasks.service;

import com.kodilla.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService
{
    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Value("${info.app.name}")
    private String appName;

    @Value("${info.company.name}")
    private String companyName;


    public String buildTrelloCardEmail(String message)
    {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("appName", appName);
        context.setVariable("companyName", companyName);
        context.setVariable("preview_message", "New Trello card was created");
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
