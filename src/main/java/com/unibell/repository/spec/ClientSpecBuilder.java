package com.unibell.repository.spec;

import com.unibell.domain.dto.filter.ClientFilter;
import com.unibell.domain.entity.Client;
import com.unibell.domain.entity.Client_;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientSpecBuilder implements SpecBuilder<Client, ClientFilter> {


    @Override
    public Specification<Client> build(ClientFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.getId() != null) {
                Predicate predicate = criteriaBuilder.equal(root.get(Client_.ID), filter.getId());
                predicates.add(predicate);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        };
    }
}
