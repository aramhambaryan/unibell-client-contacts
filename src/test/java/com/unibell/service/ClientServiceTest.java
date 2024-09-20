package com.unibell.service;

import com.unibell.config.AbstractIntegrationTest;
import com.unibell.domain.dto.request.CreateClientRequest;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;


public class ClientServiceTest extends AbstractIntegrationTest {

    @Autowired
    private ClientService clientService;

    @Test
    void whenCreate_returnIdNotNull() {
        CreateClientRequest createClientRequest = new CreateClientRequest();
        createClientRequest.setName(RandomStringUtils.randomAlphabetic(10));
        Long id = clientService.create(createClientRequest);
        Assertions.assertNotNull(id);
    }

    @Test
    void whenNullOrEmptyName_throw() {
        CreateClientRequest createClientRequest = new CreateClientRequest();
        Assertions.assertThrows(ConstraintViolationException.class, () -> clientService.create(createClientRequest));
        createClientRequest.setName("");
        Assertions.assertThrows(ConstraintViolationException.class, () -> clientService.create(createClientRequest));
    }

}