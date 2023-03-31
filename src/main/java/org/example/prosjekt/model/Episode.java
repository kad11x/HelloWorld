package org.example.prosjekt.model;

import java.time.LocalDate;

public class Episode extends Produksjon implements Comparable<Episode>{
    //private String tittel;
    private int episodeNummer;
    private int sesongNummer;
    //private double spilleTid;

    //public String getTittel() {
        //return tittel;
    //}

    //public void setTittel(String tittel) {
        //this.tittel = tittel;
    //}

    public int getEpisodeNummer() {
        return episodeNummer;
    }

    public void setEpisodeNummer(int episodeNummer) {
        this.episodeNummer = episodeNummer;
    }

    public int getSesongNummer() {
        return sesongNummer;
    }

    public void setSesongNummer(int sesongNummer) {
        this.sesongNummer = sesongNummer;
    }

    //public double getSpilleTid() {
        //return spilleTid;
    //}
    //public void setSpilleTid(double spilleTid) {
        //this.spilleTid = spilleTid;
    //}


    public Episode() {

    }

    public Episode(String tittel, int episodeNr, int sesongNr, double spilleTid, LocalDate utgitt, String beskrivelse) {
        super(tittel,spilleTid,utgitt,beskrivelse);
        this.episodeNummer = episodeNr;
        this.sesongNummer = sesongNr;


    }

    public Episode(String tittel, int episodeNr, int sesongNr, double spilleTid, LocalDate utgitt, String beskrivelse, String bildeUrl) {
        super(tittel,spilleTid,utgitt,beskrivelse, bildeUrl);
        this.episodeNummer = episodeNr;
        this.sesongNummer = sesongNr;


    }



    public Episode(String tittel, int episodeNr, int sesongNr,double spilletid) {
        super(tittel,spilletid);
        this.episodeNummer = episodeNr;
        this.sesongNummer = sesongNr;

    }

    public Episode(String tittel, int episodeNr, int sesongNr, double spilleTid, LocalDate utgitt, String beskrivelse,Person regissor, String bildeUrl) {
        super(tittel,spilleTid,utgitt,beskrivelse,regissor, bildeUrl);
        this.episodeNummer = episodeNr;
        this.sesongNummer = sesongNr;


    }

    @Override
    public String toString() {
        return "Episoden heter: " + getTittel() + "episode NR: " + episodeNummer +
                " og sesong NR: " + sesongNummer + '\n';
    }

    @Override
    public int compareTo(Episode o) {
        return this.episodeNummer - o.getEpisodeNummer();
    }
}
