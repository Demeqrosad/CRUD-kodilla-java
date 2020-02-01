package com.kodilla.crud.tasks.mapper;

import com.kodilla.crud.tasks.domain.TrelloBoard;
import com.kodilla.crud.tasks.domain.TrelloBoardDTO;
import com.kodilla.crud.tasks.domain.TrelloCard;
import com.kodilla.crud.tasks.domain.TrelloCardDTO;
import com.kodilla.crud.tasks.domain.TrelloList;
import com.kodilla.crud.tasks.domain.TrelloListDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrelloMapper
{
    public List<TrelloBoard> mapToBoards(final List<TrelloBoardDTO> trelloBoardDTO)
    {
        return trelloBoardDTO.stream()
                .map(trelloBoard ->
                        new TrelloBoard(trelloBoard.getId(), trelloBoard.getName(), mapToList(trelloBoard.getLists()))
                ).collect(Collectors.toList());
    }

    public List<TrelloBoardDTO> mapToBoardsDTO(final List<TrelloBoard> trelloBoards)
    {
        return trelloBoards.stream()
                .map(trelloBoard ->
                        new TrelloBoardDTO(trelloBoard.getName(), trelloBoard.getId(), mapToListDTO(trelloBoard.getLists()))
                ).collect(Collectors.toList());
    }

    public List<TrelloList> mapToList(final List<TrelloListDTO> trelloListDTO)
    {
        return trelloListDTO.stream()
                .map(trelloList -> new TrelloList(trelloList.getId(), trelloList.getName(), trelloList.isClosed()))
                .collect(Collectors.toList());
    }

    public List<TrelloListDTO> mapToListDTO(final List<TrelloList> trelloLists)
    {
        return trelloLists.stream()
                .map(trelloList -> new TrelloListDTO(trelloList.getName(), trelloList.getId(), trelloList.isClosed()))
                .collect(Collectors.toList());
    }

    public TrelloCardDTO mapToCardDTO(final TrelloCard trelloCard)
    {
        return new TrelloCardDTO(trelloCard.getName(), trelloCard.getDescription(),
                trelloCard.getPos(), trelloCard.getListId());
    }

    public TrelloCard mapToCard(final TrelloCardDTO trelloCardDTO)
    {
        return new TrelloCard(trelloCardDTO.getName(), trelloCardDTO.getDescription(),
                trelloCardDTO.getPos(), trelloCardDTO.getListId());
    }
}
