package com.unibell.service;

import com.unibell.domain.dto.filter.ContactFilter;
import com.unibell.domain.dto.request.CreateContactRequest;
import com.unibell.domain.dto.response.GetContactShortResponse;
import com.unibell.domain.entity.Client;
import com.unibell.domain.entity.Contact;
import com.unibell.domain.exception.EntityNotFoundException;
import com.unibell.mapper.ContactMapper;
import com.unibell.repository.ClientRepository;
import com.unibell.repository.ContactRepository;
import com.unibell.repository.spec.ContactSpecBuilder;
import com.unibell.validator.ContactValidator;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final ClientRepository clientRepository;
    private final ContactValidator contactValidator;
    private final ContactSpecBuilder contactSpecBuilder;
    private final ContactMapper contactMapper;

    /**
     * create a new contact.
     * validation is without annotation because it is customized
     * @return id of the created contact
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Long create(CreateContactRequest createContactRequest) {
        contactValidator.validate(createContactRequest);
        if (!clientRepository.existsById(createContactRequest.getClientId())) {
            throw new EntityNotFoundException("client", createContactRequest.getClientId());
        }
        Contact contact = contactMapper.toContact(createContactRequest);
        Client client = new Client();
        client.setId(createContactRequest.getClientId());
        contact.setClient(client);
        return contactRepository.save(contact).getId();
    }

    public List<GetContactShortResponse> getAllByFilter(@NotNull ContactFilter filter) {
        return contactRepository.findAll(contactSpecBuilder.build(filter)).stream()
                .map(contactMapper::toGetContactShortResponse)
                .toList();
    }

}
