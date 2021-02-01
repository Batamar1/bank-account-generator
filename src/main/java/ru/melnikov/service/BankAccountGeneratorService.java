package ru.melnikov.service;

import ru.melnikov.model.BankAccount;

import java.util.List;

public interface BankAccountGeneratorService {
    List<BankAccount> generate(int count);
}
