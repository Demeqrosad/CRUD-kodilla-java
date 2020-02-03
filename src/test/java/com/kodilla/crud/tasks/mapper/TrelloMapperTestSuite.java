package com.kodilla.crud.tasks.mapper;

import com.kodilla.crud.tasks.domain.TrelloBoard;
import com.kodilla.crud.tasks.domain.TrelloBoardDTO;
import com.kodilla.crud.tasks.domain.TrelloCard;
import com.kodilla.crud.tasks.domain.TrelloCardDTO;
import com.kodilla.crud.tasks.domain.TrelloList;
import com.kodilla.crud.tasks.domain.TrelloListDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite
{
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testCardToDTO()
    {
        //Given
        TrelloCard trelloCard = new TrelloCard("Card", "Card to mapping","1", "2");
        //When
        TrelloCardDTO trelloCardDTO = trelloMapper.mapToCardDTO(trelloCard);
        //Then
        Assert.assertTrue(trelloCardDTO != null);
        Assert.assertEquals("Card", trelloCardDTO.getName());
        Assert.assertEquals("Card to mapping", trelloCardDTO.getDescription());
        Assert.assertEquals("1", trelloCardDTO.getPos());
        Assert.assertEquals("2", trelloCardDTO.getListId());
    }

    @Test
    public void testDTOToCard()
    {
        //Given
        TrelloCardDTO trelloCardDTO = new TrelloCardDTO("CardDTO", "CardDTO to mapping","2", "1");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDTO);
        //Then
        Assert.assertTrue(trelloCard != null);
        Assert.assertEquals("CardDTO", trelloCard.getName());
        Assert.assertEquals("CardDTO to mapping", trelloCard.getDescription());
        Assert.assertEquals("2", trelloCard.getPos());
        Assert.assertEquals("1", trelloCard.getListId());

    }

    @Test
    public void testBoardToDTO()
    {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "List1", false));
        trelloLists.add(new TrelloList("2", "List2", false));
        trelloLists.add(new TrelloList("3", "List3", false));
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("4", "Board1", trelloLists));
        //When
        List<TrelloBoardDTO> trelloBoardDTOs = trelloMapper.mapToBoardsDTO(trelloBoards);
        //Then
        Assert.assertTrue(trelloBoardDTOs.get(0) != null);
        Assert.assertEquals("4", trelloBoardDTOs.get(0).getId());
        Assert.assertEquals("Board1", trelloBoardDTOs.get(0).getName());
        Assert.assertEquals("List2", trelloBoardDTOs.get(0).getLists().get(1).getName());
        Assert.assertEquals("2", trelloBoardDTOs.get(0).getLists().get(1).getId());
        Assert.assertEquals(3, trelloBoardDTOs.get(0).getLists().size());
    }

    @Test
    public void testDTOToBoard()
    {
        //Given
        List<TrelloListDTO> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDTO("List1", "1", false));
        trelloLists.add(new TrelloListDTO("List2", "2", false));
        trelloLists.add(new TrelloListDTO("List3", "3", false));
        List<TrelloBoardDTO> trelloBoardDTOs = new ArrayList<>();
        trelloBoardDTOs.add(new TrelloBoardDTO("Board1", "4", trelloLists));
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDTOs);
        //Then
        Assert.assertTrue(trelloBoards.get(0) != null);
        Assert.assertEquals("4", trelloBoards.get(0).getId());
        Assert.assertEquals("Board1", trelloBoards.get(0).getName());
        Assert.assertEquals("List2", trelloBoards.get(0).getLists().get(1).getName());
        Assert.assertEquals("2", trelloBoards.get(0).getLists().get(1).getId());
        Assert.assertEquals(3, trelloBoards.get(0).getLists().size());
    }
}
