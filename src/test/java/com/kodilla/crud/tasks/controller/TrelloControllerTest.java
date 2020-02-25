package com.kodilla.crud.tasks.controller;

import com.google.gson.Gson;
import com.kodilla.crud.tasks.domain.CreatedTrelloCardDTO;
import com.kodilla.crud.tasks.domain.TrelloBoardDTO;
import com.kodilla.crud.tasks.domain.TrelloCardDTO;
import com.kodilla.crud.tasks.domain.TrelloListDTO;
import com.kodilla.crud.tasks.trello.facade.TrelloFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(TrelloController.class)
public class TrelloControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrelloFacade trelloFacade;

    @Test
    public void shouldFetchEmptyTrelloBoards() throws Exception
    {
        //Given
        List<TrelloBoardDTO> trelloBoardDTOs = new ArrayList<>();
        Mockito.when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoardDTOs);
        //When & Then
        mockMvc.perform(get("/v1/trello/boards").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))// or isOk()
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void shouldFetchTrelloBoards() throws Exception
    {
        //Given
        List<TrelloListDTO> trelloListDTOs = new ArrayList<>();
        trelloListDTOs.add(new TrelloListDTO("Test List", "1", false));

        List<TrelloBoardDTO> trelloBoardDTOs = new ArrayList<>();
        trelloBoardDTOs.add(new TrelloBoardDTO("Test Task", "1", trelloListDTOs));

        Mockito.when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoardDTOs);
        //When & Then
        mockMvc.perform(get("/v1/trello/boards").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //Trello board fields
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is("1")))
                .andExpect(jsonPath("$[0].name", is("Test Task")))
                //Trello list fields
                .andExpect(jsonPath("$[0].lists", hasSize(1)))
                .andExpect(jsonPath("$[0].lists[0].id", is("1")))
                .andExpect(jsonPath("$[0].lists[0].name", is("Test List")))
                .andExpect(jsonPath("$[0].lists[0].closed", is(false)));
    }

    @Test
    public void shouldCreateTrelloCard() throws Exception
    {
        //Given
        TrelloCardDTO trelloCardDTO = new TrelloCardDTO(
                "Test",
                "Test description",
                "top",
                "1");

        CreatedTrelloCardDTO createdTrelloCardDTO = new CreatedTrelloCardDTO(
                "Test",
                "323",
                "http://test.com");

        Mockito.when(trelloFacade.createCard(ArgumentMatchers.any(TrelloCardDTO.class))).thenReturn(createdTrelloCardDTO);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(trelloCardDTO);

        //When & Then
        mockMvc.perform(post("/v1/trello/cards")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is("323")))
                .andExpect(jsonPath("$.name", is("Test")))
                .andExpect(jsonPath("$.shortUrl", is("http://test.com")));

    }
}