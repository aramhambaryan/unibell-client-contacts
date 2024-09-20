package com.unibell.service;

import com.unibell.domain.dto.request.CreateClientRequest;
import com.unibell.domain.dto.response.GetClientFullResponse;
import com.unibell.domain.dto.response.GetClientShortResponse;
import com.unibell.domain.exception.EntityNotFoundException;
import com.unibell.mapper.ClientMapper;
import com.unibell.repository.ClientRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;


    /**
     * create a new client
     * @return id of the created client
     */
    public Long create(@Valid CreateClientRequest createClientRequest) {
        return clientRepository.save(clientMapper.toClient(createClientRequest))
                .getId();
    }


    public List<GetClientShortResponse> getAll() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toGetClientShortResponse)
                .toList();
    }

    public GetClientFullResponse getById(@NotNull Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::toGetClientFullResponse)
                .orElseThrow(() -> new EntityNotFoundException("Client", id));
    }


}
