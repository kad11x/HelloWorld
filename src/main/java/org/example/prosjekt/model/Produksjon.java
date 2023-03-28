package org.example.prosjekt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Produksjon {

    private String tittel;
    private double spilletid;
    private LocalDate utgivelsesdato;
    private String beskrivelse;
    private Person regissor;
    private ArrayList<Roller> roller = new ArrayList<>();

    private String bildeUrl;




    public String getBildeUrl() {
        return bildeUrl;
    }

    public String getTittel() {
        return tittel;
    }
    public void setTittel(String tittel) {
        this.tittel = tittel;
    }
    public double getSpilletid() {
        return spilletid;
    }
    public LocalDate getutgivelsesdato() {
        return utgivelsesdato;
    }
    public void setutgivelsesdato(LocalDate utgivelsesdato) {
        this.utgivelsesdato = utgivelsesdato;
    }
    public void setSpilletid(double spilletid) {
        this.spilletid = spilletid;
    }
    public String getBeskrivelse() {
        return beskrivelse;
    }
    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public Person getRegissor() {
        return regissor;
    }

    public void setRegissor(Person regissor) {
        this.regissor = regissor;
    }

    @JsonIgnore
    public ArrayList<Roller> getRoller() {
        return roller;
    }

    public void leggTilEnRolle(Roller enRolle) {
        roller.add(enRolle);
    }

    public void leggTilMangeRoller(ArrayList<Roller> flereRoller) {
        for (Roller skuespiller : flereRoller ) {
            roller.add(skuespiller);
        };
    }

    public Produksjon() {
    }

    public Produksjon(String tittel, double spilleTid, LocalDate utgittdato, String beskrivelse, Person regissor) {
        this.spilletid = spilleTid;
        this.tittel = tittel;
        this.utgivelsesdato = utgittdato;
        this.beskrivelse = beskrivelse;
        this.regissor = regissor;

    }
    public Produksjon(String tittel, double spilleTid, LocalDate utgittdato, String beskrivelse) {
        this.spilletid = spilleTid;
        this.tittel = tittel;
        this.utgivelsesdato = utgittdato;
        this.beskrivelse = beskrivelse;


    }
        public Produksjon(String tittel, double spilleTid) {
        this.tittel = tittel;
        this.spilletid = spilleTid;
    }

    public Produksjon(String tittel) {
        this.tittel = tittel;
    }
    //til bildeUrl for episode
    public Produksjon(String tittel, double spilleTid, LocalDate utgittdato, String beskrivelse, Person regissor, String bildeUrl) {
        this.spilletid = spilleTid;
        this.tittel = tittel;
        this.utgivelsesdato = utgittdato;
        this.beskrivelse = beskrivelse;
        this.regissor = regissor;
        this.bildeUrl = bildeUrl;

    }

    public Produksjon(String tittel, double spilleTid, LocalDate utgittdato, String beskrivelse, String bildeUrl) {
        this.spilletid = spilleTid;
        this.tittel = tittel;
        this.utgivelsesdato = utgittdato;
        this.beskrivelse = beskrivelse;
        this.bildeUrl = bildeUrl;

    }
}
