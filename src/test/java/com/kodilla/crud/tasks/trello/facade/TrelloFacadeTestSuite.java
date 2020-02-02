package com.kodilla.crud.tasks.trello.facade;

import com.kodilla.crud.tasks.domain.TrelloBoard;
import com.kodilla.crud.tasks.domain.TrelloBoardDTO;
import com.kodilla.crud.tasks.domain.TrelloList;
import com.kodilla.crud.tasks.domain.TrelloListDTO;
import com.kodilla.crud.tasks.mapper.TrelloMapper;
import com.kodilla.crud.tasks.service.TrelloService;
import com.kodilla.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTestSuite
{
    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;

    @Test
    public void shouldFetchEmptyList()
    {
        //Given
        List<TrelloListDTO> trelloListDTOs = new ArrayList<>();
        trelloListDTOs.add(new TrelloListDTO("test_list", "1", false));

        List<TrelloBoardDTO> trelloBoardDTOs = new ArrayList<>();
        trelloBoardDTOs.add(new TrelloBoardDTO("test", "1", trelloListDTOs));

        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "test_list", false));

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "test", trelloLists));

        Mockito.when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDTOs);
        Mockito.when(trelloMapper.mapToBoards(trelloBoardDTOs)).thenReturn(trelloBoards);
        Mockito.when(trelloMapper.mapToBoardsDTO(Mockito.anyList())).thenReturn(new ArrayList<>());
        Mockito.when(trelloValidator.validateTrelloBoards(trelloBoards)).thenReturn(new ArrayList<>());
        //When
        List<TrelloBoardDTO> trelloBoardDTOList = trelloFacade.fetchTrelloBoards();
        //Then
        Assert.assertNotNull(trelloBoardDTOList);
        Assert.assertEquals(0, trelloBoardDTOList.size());
    }

    @Test
    public void shouldFetchTrelloBoards()
    {
        //Given
        List<TrelloListDTO> trelloListDTOs = new ArrayList<>();
        trelloListDTOs.add(new TrelloListDTO("my_list", "1", false));

        List<TrelloBoardDTO> trelloBoardDTOs = new ArrayList<>();
        trelloBoardDTOs.add(new TrelloBoardDTO("my_task", "1", trelloListDTOs));

        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "my_list", false));

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "my_task", trelloLists));

        Mockito.when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDTOs);
        Mockito.when(trelloMapper.mapToBoards(trelloBoardDTOs)).thenReturn(trelloBoards);
        Mockito.when(trelloMapper.mapToBoardsDTO(Mockito.anyList())).thenReturn(trelloBoardDTOs);
        Mockito.when(trelloValidator.validateTrelloBoards(trelloBoards)).thenReturn(trelloBoards);
        //When
        List<TrelloBoardDTO> trelloBoardDTOList = trelloFacade.fetchTrelloBoards();
        //Then
        Assert.assertNotNull(trelloBoardDTOList);
        Assert.assertEquals(1, trelloBoardDTOList.size());

        trelloBoardDTOs.forEach(trelloBoardDTO -> {
            Assert.assertEquals("1", trelloBoardDTO.getId());
            Assert.assertEquals("my_task", trelloBoardDTO.getName());

            trelloBoardDTO.getLists().forEach(trelloListDTO -> {
                Assert.assertEquals("1", trelloListDTO.getId());
                Assert.assertEquals("my_list", trelloListDTO.getName());
                Assert.assertEquals(false, trelloListDTO.isClosed());
            });
        });
    }
}
