package com.kodilla.crud.tasks.controller;

import com.kodilla.crud.tasks.domain.TrelloBoardDTO;
import com.kodilla.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/v1/trello")
public class TrelloController
{

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public void getTrelloBoards() {

        List<TrelloBoardDTO> trelloBoards = trelloClient.getTrelloBoards();

        trelloBoards.forEach(trelloBoardDTO -> System.out.println(trelloBoardDTO.getId() + " " + trelloBoardDTO.getName()));

    }
}

