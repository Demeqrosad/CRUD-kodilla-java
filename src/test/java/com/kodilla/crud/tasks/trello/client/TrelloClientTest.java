package com.kodilla.crud.tasks.trello.client;

import com.kodilla.crud.tasks.domain.TrelloBoardDTO;
import com.kodilla.crud.tasks.trello.config.TrelloConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloClientTest
{
    @InjectMocks
    private TrelloClient trelloClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TrelloConfig trelloConfig;

    @Test
    public void shouldFetchTrelloBoards() throws URISyntaxException
    {
        //Given
        //Mockito.when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        //Mockito.when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        //Mockito.when(trelloConfig.getTrelloToken()).thenReturn("test");

        TrelloBoardDTO[] trelloBoards = new TrelloBoardDTO[1];
        trelloBoards[0] = new TrelloBoardDTO("test_board", "test_id", new ArrayList<>());

        URI uri = new URI("http://test.com/members/demetriusz@kurosad.pl/boards?key=test&token=test&fields=name,id&lists=all");

        Mockito.when(restTemplate.getForObject(uri, TrelloBoardDTO[].class)).thenReturn(trelloBoards);
        //When
        List<TrelloBoardDTO> fetchedTrelloBoards = trelloClient.getTrelloBoards();
        //Then
        Assert.assertEquals(1, fetchedTrelloBoards.size());
        Assert.assertEquals("test_id", fetchedTrelloBoards.get(0).getId());
        Assert.assertEquals("test_board", fetchedTrelloBoards.get(0).getName());
        Assert.assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());

    }

    @Test
    public void shouldReturnEmptyList() throws URISyntaxException
    {
        //Given
        URI uri = new URI("http://test.com/members/demetriusz@kurosad.pl/boards?key=test&token=test&fields=name,id&lists=all");

        Mockito.when(restTemplate.getForObject(uri, TrelloBoardDTO[].class)).thenReturn(null);
        //When
        List<TrelloBoardDTO> nullTrelloBoards = trelloClient.getTrelloBoards();
        //Then
        Assert.assertEquals(0, nullTrelloBoards.size());
    }
}