package ru.melnikov.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.melnikov.model.BankAccount;
import ru.melnikov.response.ResponseData;
import ru.melnikov.service.BankAccountGeneratorService;

import java.util.List;

@RestController
@RequestMapping
@Slf4j
public class BankAccountGeneratorController {

    @Autowired
    BankAccountGeneratorService service;

    @GetMapping(value = "/bank-accounts-batch/{count}")
    public @ResponseBody ResponseData<List<BankAccount>> generate(@PathVariable int count){
        log.info("BankAccountGeneratorController.generate, count = {}",count);
        return new ResponseData<>(service.generate(count));
    }
}
