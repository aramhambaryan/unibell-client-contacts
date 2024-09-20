package com.unibell.repository.spec;

import com.unibell.domain.dto.filter.ClientFilter;
import com.unibell.domain.entity.Client;
import org.springframework.data.jpa.domain.Specification;

public interface SpecBuilder<ENTITY, FILTER> {

    Specification<ENTITY> build(FILTER filter);
}
