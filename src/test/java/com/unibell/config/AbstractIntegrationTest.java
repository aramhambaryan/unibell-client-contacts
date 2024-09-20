package com.unibell.config;

import com.unibell.repository.ClientRepository;
import com.unibell.repository.ContactRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * parent class that combines configuration classes for integration tests
 */
@SpringBootTest
public class AbstractIntegrationTest implements DbConfig, ClientFiller, ContactFiller {

    @Autowired
    @Getter
    private ClientRepository clientRepository;

    @Autowired
    @Getter
    private ContactRepository contactRepository;
}
