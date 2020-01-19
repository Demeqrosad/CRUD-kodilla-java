package com.kodilla.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Mail
{
    private String mailTo;
    private String subject;
    private String message;
}
