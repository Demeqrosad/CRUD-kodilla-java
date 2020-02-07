package com.kodilla.crud.tasks.domain;

import org.junit.Assert;
import org.junit.Test;

public class NoArgsConstuctorTest
{
    @Test
    public void nullTrelloBoardDTO()
    {
        //Given

        //When
        TrelloBoardDTO trelloBoardDTO = new TrelloBoardDTO();
        //Then
        Assert.assertEquals(null, trelloBoardDTO.getId());
        Assert.assertEquals(null, trelloBoardDTO.getName());
        Assert.assertEquals(null, trelloBoardDTO.getLists());
    }

    @Test
    public void nullTrelloCardDTO()
    {
        //Given

        //When
        TrelloCardDTO trelloCardDTO = new TrelloCardDTO();
        //Then
        Assert.assertEquals(null, trelloCardDTO.getName());
        Assert.assertEquals(null, trelloCardDTO.getDescription());
        Assert.assertEquals(null, trelloCardDTO.getListId());
        Assert.assertEquals(null, trelloCardDTO.getPos());
    }

    @Test
    public void nullTrelloListDTO()
    {
        //Given

        //When
        TrelloListDTO trelloListDTO = new TrelloListDTO();
        //Then
        Assert.assertEquals(null, trelloListDTO.getName());
        Assert.assertEquals(null, trelloListDTO.getId());
    }

    @Test
    public void nullTrelloAttachmentByTypeDTO()
    {
        //Given

        //When
        TrelloAttachmentByTypeDTO trelloAttachmentByTypeDTO = new TrelloAttachmentByTypeDTO();
        //Then
        Assert.assertEquals(null, trelloAttachmentByTypeDTO.getTrello());
        Assert.assertEquals(0, trelloAttachmentByTypeDTO.getVotes());
    }

    @Test
    public void nullTrelloBadgeDTO()
    {
        //Given

        //When
        TrelloBadgeDTO trelloBadgeDTO = new TrelloBadgeDTO();
        //Then
        Assert.assertEquals(null, trelloBadgeDTO.getAttachments());
        Assert.assertEquals(0, trelloBadgeDTO.getVotes());
    }

    @Test
    public void nullTrelloTrelloDTO()
    {
        //Given

        //When
        TrelloTrelloDTO trelloTrelloDTO = new TrelloTrelloDTO();
        //Then
        Assert.assertEquals(0, trelloTrelloDTO.getBoard());
        Assert.assertEquals(0, trelloTrelloDTO.getCard());
    }
}
