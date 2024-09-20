package com.unibell.mapper;

import com.unibell.config.MapperConfiguration;
import com.unibell.domain.dto.request.CreateContactRequest;
import com.unibell.domain.dto.response.GetContactShortResponse;
import com.unibell.domain.entity.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfiguration.class)
public interface ContactMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    Contact toContact(CreateContactRequest createContactRequest);
    GetContactShortResponse toGetContactShortResponse(Contact contact);
}
