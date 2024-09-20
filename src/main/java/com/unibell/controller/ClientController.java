package com.unibell.controller;

import com.unibell.domain.dto.request.CreateClientRequest;
import com.unibell.domain.dto.response.GetClientFullResponse;
import com.unibell.domain.dto.response.GetClientShortResponse;
import com.unibell.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * For an endpoint description please refer to the description of the method it calls
 */

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public Long create(@RequestBody CreateClientRequest createClientRequest) {
        return clientService.create(createClientRequest);
    }

    @GetMapping
    public List<GetClientShortResponse> getAll() {
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    public GetClientFullResponse getById(@PathVariable Long id) {
        return clientService.getById(id);
    }


}
