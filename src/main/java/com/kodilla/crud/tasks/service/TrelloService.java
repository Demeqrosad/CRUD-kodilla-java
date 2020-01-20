package com.kodilla.crud.tasks.service;

import com.kodilla.crud.tasks.config.AdminConfig;
import com.kodilla.crud.tasks.domain.CreatedTrelloCard;
import com.kodilla.crud.tasks.domain.Mail;
import com.kodilla.crud.tasks.domain.TrelloBoardDTO;
import com.kodilla.crud.tasks.domain.TrelloCardDTO;
import com.kodilla.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrelloService
{
    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TrelloClient trelloClient;

    @Autowired
    private SimpleEmailService emailService;

    private static final String SUBJECT = "Tasks: New Trello card";

    public List<TrelloBoardDTO> fetchTrelloBoards()
    {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCard createTrelloCard(final TrelloCardDTO trelloCardDTO)
    {
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDTO);
        Optional.ofNullable(newCard).ifPresent(card -> emailService.send(new Mail(
            adminConfig.getAdminMail(),
            "",
            SUBJECT,
            "New card: " + trelloCardDTO.getName() + " has been created on your Trello account")));
        return newCard;
    }
}
