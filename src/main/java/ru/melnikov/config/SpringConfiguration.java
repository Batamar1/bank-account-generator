package ru.melnikov.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class SpringConfiguration {

    @Bean(name = "manNames")
    List<String> getManNames(){
        return readFile("MansNames.txt");
    }

    @Bean(name = "manPatronymics")
    List<String> getManPatronymics(){
        return readFile("MansPatronymics.txt");
    }

    @Bean(name = "manSurnames")
    List<String> getManSurnames(){
        return readFile("MansSurnames.txt");
    }

    @Bean(name = "womanNames")
    List<String> getWomanNames(){
        return readFile("WomansNames.txt");
    }

    @Bean(name = "womanPatronymics")
    List<String> getWomanPatronymics(){
        return readFile("WomansPatronymics.txt");
    }

    @Bean(name = "womanSurnames")
    List<String> getWomanSurnames(){
        return readFile("WomansSurnames.txt");
    }



    private List<String> readFile(String uri){
        log.info("SpringConfiguration.readFile uri = {}", uri);
        Path path = Paths.get("src/main/resources/NamesForBankAccounts/" + uri);
        List<String> read = new ArrayList<>();
        try{
            read = Files.readAllLines(path);
        }catch (IOException e){
            e.printStackTrace();
        }
        return read;
    }
}
