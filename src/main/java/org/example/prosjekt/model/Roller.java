package org.example.prosjekt.model;

public class Roller {

    private String rolleFornavn;
    private String rolleEtternavn;
    private Person skuespiller;

    public Roller(String rolleFornavn, String rolleEtternavn, Person skuespiller) {
        this.rolleFornavn = rolleFornavn;
        this.rolleEtternavn = rolleEtternavn;
        this.skuespiller = skuespiller;
    }

    public Roller() {
    }

    public String getRolleFornavn() {
        return rolleFornavn;
    }

    public String getRolleEtternavn() {
        return rolleEtternavn;
    }

    public Person getSkuespiller() {
        return skuespiller;
    }

    public void setRolleFornavn(String rolleFornavn) {
        this.rolleFornavn = rolleFornavn;
    }

    public void setRolleEtternavn(String rolleEtternavn) {
        this.rolleEtternavn = rolleEtternavn;
    }

    public void setSkuespiller(Person skuespiller) {
        this.skuespiller = skuespiller;
    }

    @Override
    public String toString() {
        return "skuespilleren som er med heter : " + skuespiller +"," +
                " Har navnet " +rolleFornavn+" "+rolleEtternavn + "!!\t";

    }
}
