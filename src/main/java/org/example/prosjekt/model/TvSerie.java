package org.example.prosjekt.model;

import java.time.LocalDate;
import java.util.ArrayList;



public class TvSerie implements Comparable<TvSerie>{
    private String tittel;
    private String beskrivelse;
    private LocalDate utgivelsesdato;
    private ArrayList<Episode> episoder = new ArrayList<Episode>();
    private double gjennomsnittligSpilletid;
    //Oppgave2.7
    private int antallSesonger;

    private String bildeUrl;

    public TvSerie() {
    }

    public String getBildeUrl() {
        return bildeUrl;
    }

    public String getTittel() {
        return tittel;
    }
    public void setTittel(String tittel){
        this.tittel = tittel;
    }
    public  String getBeskrivelse() {
        return beskrivelse;
    }
    public void setBeskrivelse(String beskrivelse){
        this.beskrivelse = beskrivelse;
    }
    public LocalDate getUtgivelsesdato() {
        return utgivelsesdato;
    }

    public void setUtgivelsesdato(LocalDate utgivelsesdato) {
        this.utgivelsesdato = utgivelsesdato;
    }
    public ArrayList<Episode> getEpisoder() {
        return episoder;
    }
    public void setEpisoder(ArrayList episoder){
        this.episoder = episoder;
    }

    public double getGjennomsnittligSpilletid()
    {
        return gjennomsnittligSpilletid;
    }

    public int getAntallSesonger() {
        return antallSesonger;
    }


    public TvSerie(String tittel, String beskrivelse, LocalDate utgivelsesdato, String bildeUrl) {
        this.tittel = tittel;
        this.beskrivelse = beskrivelse;
        this.utgivelsesdato = utgivelsesdato;
        this.bildeUrl = bildeUrl;

    }



    @Override
    public String toString() {
        return "Beskrivelse av serien: " +
                tittel + " er " +
                beskrivelse + " og ble utgitt i " +
                utgivelsesdato + " finnes " +
                episoder + " episoder.";
    }


    public void addEpisode(Episode episode) {
        int sesong = episode.getSesongNummer();

        if (sesong > antallSesonger + 1) {
            System.out.println("Feil, du kan ikke legge til episode: " + episode.getTittel()+ "!!!!" );
        } else if (sesong == antallSesonger + 1) {
            antallSesonger = sesong;
            episoder.add(episode);

        }
        else {
            episoder.add(episode);
        }
        oppdaterGjennomsnittligSpilletid();
    }

    public ArrayList<Episode> hentEpisoderISesong(int sesong) {
        ArrayList<Episode> result = new ArrayList<Episode>();
        for (Episode episode : episoder) {
            if (episode.getSesongNummer() == sesong) {
                result.add(episode);
            }
        }
        return result;
    }

    private void oppdaterGjennomsnittligSpilletid() {
        double tot = 0;

        for (Episode episode : episoder) {
            tot += episode.getSpilletid();
        }

        double res = tot / episoder.size();

        gjennomsnittligSpilletid = res;

    }

    public ArrayList<Roller> hentRollebesetning() {
        ArrayList kerr = new ArrayList<>();
        for (Episode episode : this.episoder ) {
            kerr.add(episode.getRoller());

        }

        return kerr;


    }

    //ikke ferdig enda
    @Override
    public int compareTo( TvSerie o) {
        if (this.tittel == o.tittel) {
            return this.tittel.compareTo(o.getTittel());
        }

        return 0;
    }
}
