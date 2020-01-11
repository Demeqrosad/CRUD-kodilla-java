package com.kodilla.crud.tasks.trello.client;

import com.kodilla.crud.tasks.domain.TrelloBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient
{
    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.app.user}")
    private String trelloUser;

    @Autowired
    private RestTemplate restTemplate;

    public List<TrelloBoardDTO> getTrelloBoards()
    {
        return Optional.ofNullable(restTemplate.getForObject(
                this.uriTrelloBoards(), TrelloBoardDTO[].class))
                .map(Arrays::asList).orElseGet(ArrayList::new);
    }

    private URI uriTrelloBoards()
    {
        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + trelloUser +
                "/boards").queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id").build().encode().toUri();
    }
}
