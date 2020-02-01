package com.kodilla.crud.tasks.trello.client;

import com.kodilla.crud.tasks.domain.CreatedTrelloCardDTO;
import com.kodilla.crud.tasks.domain.TrelloBoardDTO;
import com.kodilla.crud.tasks.domain.TrelloCardDTO;
import com.kodilla.crud.tasks.trello.config.TrelloConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

@Component
public class TrelloClient
{
    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);

    @Autowired
    private TrelloConfig trelloConfig;

    @Autowired
    private RestTemplate restTemplate;

    public List<TrelloBoardDTO> getTrelloBoards()
    {
        try
        {
            URI uri = this.uriTrelloBoards();
            TrelloBoardDTO[] boardsResponse = restTemplate.getForObject(
                    uri, TrelloBoardDTO[].class);
            return Arrays.asList(ofNullable(boardsResponse).orElse(new TrelloBoardDTO[0]));
        }
        catch (RestClientException | URISyntaxException e)
        {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public CreatedTrelloCardDTO createNewCard(TrelloCardDTO trelloCardDTO)
    {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("name", trelloCardDTO.getName())
                .queryParam("desc", trelloCardDTO.getDescription())
                .queryParam("pos", trelloCardDTO.getPos())
                .queryParam("idList", trelloCardDTO.getListId()).build().encode().toUri();

        return restTemplate.postForObject(url, null, CreatedTrelloCardDTO.class);
    }

    private URI uriTrelloBoards() throws URISyntaxException
    {
        return UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/members/" + trelloConfig.getTrelloUser() + "/boards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("fields", "name,id")
                .queryParam("lists", "all").build().encode().toUri();
    }
}
