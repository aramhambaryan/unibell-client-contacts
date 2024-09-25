package com.unibell.controller;

import com.unibell.domain.dto.filter.ContactFilter;
import com.unibell.domain.dto.request.CreateContactRequest;
import com.unibell.domain.dto.response.GetContactFullResponse;
import com.unibell.domain.dto.response.GetContactShortResponse;
import com.unibell.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public Long create(@RequestBody CreateContactRequest createContactRequest) {
        return contactService.create(createContactRequest);
    }

    @GetMapping
    public List<GetContactShortResponse> getAllByFilter(ContactFilter contactFilter) {
        return contactService.getAllByFilter(contactFilter);
    }

    @GetMapping("/{id}")
    public GetContactFullResponse getOneById(@PathVariable Long id) {
        return contactService.getOneById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        contactService.deleteById(id);
    }

}
