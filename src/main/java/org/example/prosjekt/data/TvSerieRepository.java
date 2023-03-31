package org.example.prosjekt.data;

import org.example.prosjekt.model.Episode;
import org.example.prosjekt.model.TvSerie;

import java.time.LocalDate;
import java.util.ArrayList;

public interface TvSerieRepository {





    ArrayList<TvSerie> getTvSerier();



    TvSerie hentTvSerie(String tittel);

    Episode getEnEpisodeISesong(String tittel, int sesong, int episode);

    ArrayList<Episode> getEpisoderISesong(String tittel, int sesong);


    void createEpisode(String tvserie, String title, int sesonNr, int episodeNr, String beskrivelse, double spilletid, LocalDate utgivelsesdato, String bildeurl);
    void updateEpisode(String tvserie, int sesongNr, int episodeNr, String title, int sesongNummer, int episodeNummer, String beskrivelse, double spilletid, LocalDate utgivelsesdato, String bildeurl);
    void deleteEpisode(String tvserie, int sesongNr, int episodeNr);



}
