package com.chonfoungo.banking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="_user")
public class User extends  AbstractEntity {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private boolean active;

    @OneToOne
    private Address address;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transaction;

    @OneToMany(mappedBy = "user")
    private List<Contact> contacts;

    @OneToOne
    private Account account;

    @OneToOne
    private Role role;


}
