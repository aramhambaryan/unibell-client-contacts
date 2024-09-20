package com.unibell.mapper;

import com.unibell.config.MapperConfiguration;
import com.unibell.domain.dto.request.CreateClientRequest;
import com.unibell.domain.dto.response.GetClientFullResponse;
import com.unibell.domain.dto.response.GetClientShortResponse;
import com.unibell.domain.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfiguration.class)
public interface ClientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contacts", ignore = true)
    Client toClient(CreateClientRequest createClientRequest);
    GetClientShortResponse toGetClientShortResponse(Client client);
    GetClientFullResponse toGetClientFullResponse(Client client);
}
