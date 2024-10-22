package com.chonfoungo.banking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Contact extends AbstractEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String iban;


    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
