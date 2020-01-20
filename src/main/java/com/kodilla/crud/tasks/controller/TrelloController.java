package com.kodilla.crud.tasks.controller;

import com.kodilla.crud.tasks.domain.CreatedTrelloCard;
import com.kodilla.crud.tasks.domain.TrelloBoardDTO;
import com.kodilla.crud.tasks.domain.TrelloCardDTO;
import com.kodilla.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController
{

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDTO> getTrelloBoards()
    {
        return trelloClient.getTrelloBoards();
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDTO trelloCardDTO)
    {
        return trelloClient.createNewCard(trelloCardDTO);
    }
}

