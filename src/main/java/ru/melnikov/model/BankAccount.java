package ru.melnikov.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
    private String firstName;
    private String lastName;
    private String patronymic;
    private long accountNumber;
}
