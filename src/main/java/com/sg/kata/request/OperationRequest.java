package com.sg.kata.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OperationRequest implements Serializable {

    private static final long serialVersionUID = -1245837755215168372L;

    private Double amount;
    private Integer accountId;
    
}
