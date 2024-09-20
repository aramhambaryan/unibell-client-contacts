package com.unibell.domain.entity;

import com.unibell.domain.enums.ContactType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Contact extends AbstractEntity {

    private String name;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @Enumerated(EnumType.STRING)
    private ContactType type;
    private String email;
    private String phone;
}
