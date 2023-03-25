package org.example.prosjekt.model;

public class Person {
    private String fulltNavn;


    private int alder;


    public String getfulltNavn() {
        return fulltNavn;
    }

    public void setfulltNavn(String fulltnavn) {
        this.fulltNavn = fulltnavn;
    }





    public int getAlder() {
        return alder;
    }

    public void setAlder(int alder) {
        this.alder = alder;
    }

    public Person(String navn) {
        this.fulltNavn = navn;
    }
    public Person(String navn, int alder) {
        this.fulltNavn = navn;

        this.alder = alder;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return fulltNavn;
    }
}
