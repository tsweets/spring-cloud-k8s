package org.beer30.springcloud.cardholder.domain;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class Card {


    private Long id;

    private String embossedName;
    private String cardnumberLast4;
    private String status;
    private ZonedDateTime issueDate;
    private Long cardholderId;


}
