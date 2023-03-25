package org.example.prosjekt.model;
import java.time.LocalDate;

public class Film extends Produksjon{



    public Film(String tittel, int spilleTid, LocalDate utgittdato, String beskrivelse) {
        super(tittel, spilleTid,utgittdato, beskrivelse);

    }

    public Film(String tittel, int spilleTid) {
        super(tittel, spilleTid);
    }

    public Film(String tittel, int spilleTid, LocalDate utgittdato, String beskrivelse, Person regissor) {
        super(tittel, spilleTid,utgittdato, beskrivelse, regissor);

    }

    public Film() {
    }
}
