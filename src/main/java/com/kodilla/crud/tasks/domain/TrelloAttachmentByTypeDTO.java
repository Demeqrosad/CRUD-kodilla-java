package com.kodilla.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloAttachmentByTypeDTO
{
    @JsonProperty("votes")
    private int votes;

    @JsonProperty("trello")
    private TrelloTrelloDTO trello;
}
