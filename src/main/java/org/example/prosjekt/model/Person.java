package org.example.prosjekt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public class Person {

    private String fornavn;
    private String etternavn;
    private LocalDate fodselsdato;


    public Person(String fornavn, String etternavn, LocalDate fodselsdato) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.fodselsdato = fodselsdato;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public LocalDate getFodselsdato() {
        return fodselsdato;
    }

    public void setFodselsdato(LocalDate fodselsdato) {
        this.fodselsdato = fodselsdato;
    }
    @JsonIgnore
    public String getFullNavn (){
        return this.fornavn +" "+ this.etternavn;
    }

    @Override
    public String toString(){
        return "Person fullnavn: "+this.getFullNavn();
    }
}
