package com.unibell.repository.spec;

import com.unibell.domain.dto.filter.ContactFilter;
import com.unibell.domain.entity.Client_;
import com.unibell.domain.entity.Contact;
import com.unibell.domain.entity.Contact_;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ContactSpecBuilder implements SpecBuilder<Contact, ContactFilter> {

    @Override
    public Specification<Contact> build(ContactFilter filter) {
        return (root, query, criteriaBuilder) -> {
            var predicatesList = new ArrayList<Predicate>();

            if (filter.getType() != null) {
                Predicate predicate = criteriaBuilder.equal(root.get(Contact_.TYPE), filter.getType());
                predicatesList.add(predicate);
            }

            if (filter.getClientId() != null) {
                Predicate predicate = criteriaBuilder.equal(root.get(Contact_.CLIENT).get(Client_.ID), filter.getClientId());
                predicatesList.add(predicate);
            }

            return criteriaBuilder.and(predicatesList.toArray(new Predicate[]{}));
        };
    }
}
