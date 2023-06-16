package com.example.csharing.form;

public interface FormConverter <S,T>{
    T convertTo(S s);

}
