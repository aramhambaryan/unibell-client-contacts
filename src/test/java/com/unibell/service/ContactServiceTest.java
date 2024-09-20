package com.unibell.service;

import com.unibell.config.AbstractIntegrationTest;
import com.unibell.domain.dto.request.CreateContactRequest;
import com.unibell.domain.entity.Contact;
import com.unibell.domain.enums.ContactType;
import com.unibell.repository.ClientRepository;
import com.unibell.repository.ContactRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

class ContactServiceTest extends AbstractIntegrationTest {

    @Autowired
    private ContactService contactService;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ContactRepository contactRepository;

    @Test
    void testTypeValidation() {
        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setName(randomAlphabetic(10));
        createContactRequest.setClientId(clientRepository.findAll().get(0).getId());

        createContactRequest.setType(ContactType.EMAIL);
        Assertions.assertThrows(ConstraintViolationException.class, () -> contactService.create(createContactRequest));

        createContactRequest.setEmail(randomAlphabetic(10));
        Assertions.assertDoesNotThrow(() -> contactService.create(createContactRequest));

        createContactRequest.setPhone(randomAlphabetic(10));
        Assertions.assertThrows(ConstraintViolationException.class, () -> contactService.create(createContactRequest));
    }

    @Test
    void whenSave_thenSavedExists() {
        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setName(randomAlphabetic(10));
        createContactRequest.setType(ContactType.EMAIL);
        createContactRequest.setEmail(randomAlphabetic(10));
        createContactRequest.setClientId(clientRepository.findAll().get(0).getId());
        Long id = contactService.create(createContactRequest);
        Optional<Contact> optionalContact = contactRepository.findById(id);

        assertTrue(optionalContact.isPresent());
        Contact contact = optionalContact.get();
        assertEquals(createContactRequest.getName(), contact.getName());
        assertEquals(createContactRequest.getType(), contact.getType());
        assertEquals(createContactRequest.getEmail(), contact.getEmail());
        assertEquals(createContactRequest.getPhone(), contact.getPhone());
    }
}