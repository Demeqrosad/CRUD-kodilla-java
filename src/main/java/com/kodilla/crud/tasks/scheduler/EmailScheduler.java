package com.kodilla.crud.tasks.scheduler;

import com.kodilla.crud.tasks.config.AdminConfig;
import com.kodilla.crud.tasks.domain.Mail;
import com.kodilla.crud.tasks.repository.TaskRepository;
import com.kodilla.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ChoiceFormat;
import java.text.MessageFormat;

@Component
public class EmailScheduler
{
    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "Tasks: Once a day email";

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail()
    {
        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                "",
                SUBJECT,
                useMessagePattern())
        );
    }

    public String useMessagePattern()
    {
        MessageFormat infoPattern = new MessageFormat("Currently in database you got: {0}. {1}");

        double[] taskLimits = {0, 1, 2};
        String[] taskFormats = {"no tasks", "1 task", "{0, number} tasks"};
        ChoiceFormat taskFormat = new ChoiceFormat(taskLimits, taskFormats);
        infoPattern.setFormatByArgumentIndex(0, taskFormat);

        double[] postscriptLimits = {0, 1, 2};
        String[] postscriptFormats = {"Take a break!", "Back to work!", "All hands on deck!"};
        ChoiceFormat postscriptFormat = new ChoiceFormat(postscriptLimits, postscriptFormats);
        infoPattern.setFormatByArgumentIndex(1, postscriptFormat);

        long size = taskRepository.count();
        Object[] formatArgs = {new Long(size), new Long(size)};

        return infoPattern.format(formatArgs);
    }
}
