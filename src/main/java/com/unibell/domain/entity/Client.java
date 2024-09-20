package com.unibell.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Client extends AbstractEntity {

    private String name;
    @OneToMany(mappedBy = "client")
    private List<Contact> contacts;

}
