package com.sg.kata.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    @SequenceGenerator(name = "seq_account_gen", sequenceName = "seq_account", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_account_gen")
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "owner", nullable = false)
    private String owner;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

}
