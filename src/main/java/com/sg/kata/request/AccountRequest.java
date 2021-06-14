package com.sg.kata.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest implements Serializable {

    private static final long serialVersionUID = 5745608566921435425L;

    private String owner;
    private Double balance;
}
