package ru.melnikov.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResponseData <T> {
    private T data;

    private List<Error> errors;

    public ResponseData(T data){
        this.data = data;
    }

    public ResponseData(T data, List<Error> errors){
        this.data = data;
        this.errors = errors;
    }

    public ResponseData(){
    }
    public void addError(Error error){
        if(errors == null){
            errors = new ArrayList<>();
        }
        errors.add(error);
    }

}