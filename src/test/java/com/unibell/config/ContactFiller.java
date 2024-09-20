package com.unibell.config;

import com.unibell.domain.entity.Client;
import com.unibell.domain.entity.Contact;
import com.unibell.domain.enums.ContactType;
import com.unibell.repository.ClientRepository;
import com.unibell.repository.ContactRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.unibell.domain.enums.ContactType.EMAIL;
import static com.unibell.domain.enums.ContactType.PHONE;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

public interface ContactFiller {

    ContactRepository getContactRepository();
    ClientRepository getClientRepository();

    @AfterEach
    @Order(Orders.CONTACT_DELETE_ORDER)
    default void deleteContacts() {
        getContactRepository().deleteAll();
    }

    @BeforeEach
    @Order(Orders.CONTACT_SAVE_ORDER)
    default void saveContacts() {
        getContactRepository().saveAll(getRandomContacts());
    }

    private List<Contact> getRandomContacts() {
        List<Long> clientIds = getClientRepository().findAll().stream().map(Client::getId).toList();
        var contactsList = new LinkedList<Contact>();
        for (int i = 0; i < 30; i++) {
            contactsList.add(getRandomContact(clientIds, EMAIL));
            contactsList.add(getRandomContact(clientIds, PHONE));

        }
        return contactsList;
    }

    private Contact getRandomContact(List<Long> clientIds, ContactType contactType) {
        Contact contact = new Contact();
        Client client = new Client();
        client.setId(clientIds.get(ThreadLocalRandom.current().nextInt(clientIds.size())));
        contact.setClient(client);
        contact.setName(randomAlphabetic(10));
        contact.setType(contactType);
        if (contactType == EMAIL) {
            contact.setEmail(randomAlphabetic(20));
        } else if (contactType == PHONE) {
            contact.setPhone(randomNumeric(10));
        } else {
            throw new UnsupportedOperationException(contactType + " contact type not supported yet");
        }
        return contact;
    }
}
