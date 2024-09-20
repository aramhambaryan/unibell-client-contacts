package com.unibell.domain.dto.filter;

import com.unibell.domain.enums.ContactType;
import lombok.Data;

@Data
public class ContactFilter {

    private Long clientId;
    private ContactType type;
}
