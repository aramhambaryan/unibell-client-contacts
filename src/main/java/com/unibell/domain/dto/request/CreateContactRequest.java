package com.unibell.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unibell.domain.enums.ContactType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CreateContactRequest {

    @NotBlank
    private String name;
    @JsonIgnore
    @NotNull
    private Long clientId;
    @NotNull
    private ContactType type;
    private String phone;
    private String email;


}
