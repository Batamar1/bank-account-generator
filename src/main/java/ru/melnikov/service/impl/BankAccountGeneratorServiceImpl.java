package ru.melnikov.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.melnikov.model.BankAccount;
import ru.melnikov.service.BankAccountGeneratorService;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
public class BankAccountGeneratorServiceImpl implements BankAccountGeneratorService {
    private final List<String> manNames;
    private final List<String> manPatronymics;
    private final List<String> manSurnames;
    private final List<String> womanNames;
    private final List<String> womanPatronymics;
    private final List<String> womanSurnames;

    private final Random random= new Random();

    @Value("${spring.main.n}")
    private int N = 1;

    private List<Long> accountNumbers;

    @Autowired
    public BankAccountGeneratorServiceImpl(List<String> manNames, List<String> manPatronymics, List<String> manSurnames, List<String> womanNames, List<String> womanPatronymics, List<String> womanSurnames) {
        this.manNames = manNames;
        this.manPatronymics = manPatronymics;
        this.manSurnames = manSurnames;
        this.womanNames = womanNames;
        this.womanPatronymics = womanPatronymics;
        this.womanSurnames = womanSurnames;
    }

    @Override
    public List<BankAccount> generate(int count) {
        initSetAccountNumbers();
        List<BankAccount> bankAccounts = new ArrayList<>();
        if(count > N) throw new IllegalArgumentException();
        for(int i = 0; i < count; i++){
            bankAccounts.add(random.nextInt(2) == 0? generateManBankAccount() : generateWomanBankAccount());
        }
        log.info("BankAccountGeneratorService.generate count = {}, return bankAccounts = {}", count, bankAccounts);
        return bankAccounts;
    }

    private BankAccount generateManBankAccount(){
        BankAccount bankAccount = new BankAccount();
        bankAccount.setLastName(manSurnames.get(random.nextInt(manSurnames.size())));
        bankAccount.setFirstName(manNames.get(random.nextInt(manNames.size())));
        bankAccount.setPatronymic(manPatronymics.get(random.nextInt(manPatronymics.size())));
        bankAccount.setAccountNumber(generateAccountNumber());
        return bankAccount;
    }

    private BankAccount generateWomanBankAccount(){
        BankAccount bankAccount = new BankAccount();
        bankAccount.setLastName(womanSurnames.get(random.nextInt(womanSurnames.size())));
        bankAccount.setFirstName(womanNames.get(random.nextInt(womanNames.size())));
        bankAccount.setPatronymic(womanPatronymics.get(random.nextInt(womanPatronymics.size())));
        bankAccount.setAccountNumber(generateAccountNumber());
        return bankAccount;
    }

    private void initSetAccountNumbers(){
        accountNumbers = new ArrayList<>();
        for(long i = 1; i <= N; i++){
            accountNumbers.add(i);
        }
        Collections.shuffle(accountNumbers);
    }
    private Long generateAccountNumber(){
        var value = accountNumbers.get(0);
        accountNumbers.remove(0);
        return random.nextInt(2) == 0? -value : value;
    }

}
