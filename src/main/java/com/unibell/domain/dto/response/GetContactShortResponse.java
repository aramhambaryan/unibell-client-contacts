package com.unibell.domain.dto.response;

import com.unibell.domain.enums.ContactType;
import lombok.Data;

@Data
public class GetContactShortResponse {

    private Long id;
    private String name;
    private ContactType type;
    private String email;
    private String phone;
}
