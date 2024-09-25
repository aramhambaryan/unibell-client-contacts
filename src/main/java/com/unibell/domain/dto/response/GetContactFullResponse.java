package com.unibell.domain.dto.response;

import com.unibell.domain.enums.ContactType;
import lombok.Data;

@Data
public class GetContactFullResponse {

    private Long id;
    private String name;
    private GetClientShortResponse client;
    private ContactType type;
    private String email;
    private String phone;
}
