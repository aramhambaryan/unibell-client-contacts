package com.unibell.domain.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class GetClientFullResponse {

    private Long id;
    private String name;
    private List<GetContactShortResponse> contacts;
}
