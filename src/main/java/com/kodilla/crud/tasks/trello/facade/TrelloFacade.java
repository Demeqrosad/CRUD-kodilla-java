package com.kodilla.crud.tasks.trello.facade;

import com.kodilla.crud.tasks.domain.CreatedTrelloCardDTO;
import com.kodilla.crud.tasks.domain.TrelloBoard;
import com.kodilla.crud.tasks.domain.TrelloBoardDTO;
import com.kodilla.crud.tasks.domain.TrelloCard;
import com.kodilla.crud.tasks.domain.TrelloCardDTO;
import com.kodilla.crud.tasks.mapper.TrelloMapper;
import com.kodilla.crud.tasks.service.TrelloService;
import com.kodilla.crud.tasks.trello.validator.TrelloValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrelloFacade
{
    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloFacade.class);

    @Autowired
    private TrelloService trelloService;

    @Autowired
    private TrelloMapper trelloMapper;

    @Autowired
    private TrelloValidator trelloValidator;

    public List<TrelloBoardDTO> fetchTrelloBoards()
    {
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloService.fetchTrelloBoards());
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        return trelloMapper.mapToBoardsDTO(filteredBoards);
    }

    public CreatedTrelloCardDTO createCard(final TrelloCardDTO trelloCardDTO)
    {
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDTO);
        trelloValidator.validateCard(trelloCard);
        return trelloService.createTrelloCard(trelloMapper.mapToCardDTO(trelloCard));
    }
}
