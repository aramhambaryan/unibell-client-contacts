package com.unibell.config;

import com.unibell.domain.entity.Client;
import com.unibell.repository.ClientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.stream.IntStream;

public interface ClientFiller {

    ClientRepository getClientRepository();

    @AfterEach
    @Order(Orders.CLIENT_DELETE_ORDER)
    default void deleteClients() {
        getClientRepository().deleteAll();
    }

    @BeforeEach
    @Order(Orders.CLIENT_SAVE_ORDER)
    default void saveClients() {
        getClientRepository().saveAll(getRandomClients());
    }

    private List<Client> getRandomClients() {
        return IntStream.of(10)
                .mapToObj(x -> getRandomClient())
                .toList();
    }

    private Client getRandomClient() {
        Client client = new Client();
        client.setName(RandomStringUtils.randomAlphabetic(10));
        return client;
    }


}
