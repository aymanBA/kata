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
@Table(name = "operation")
public class Operation {

    @Id
    @SequenceGenerator(name = "seq_operation_gen", sequenceName = "seq_operation", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_operation_gen")
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "old_balance", nullable = false)
    private Double oldBalance;

    @Column(name = "new_balance", nullable = false)
    private Double newBalance;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private OperationType type;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
