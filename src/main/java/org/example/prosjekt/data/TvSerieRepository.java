package org.example.prosjekt.data;

import org.example.prosjekt.model.Episode;
import org.example.prosjekt.model.TvSerie;

import java.util.ArrayList;

public interface TvSerieRepository {





    ArrayList<TvSerie> getTvSerier();



    TvSerie hentTvSerie(String tittel);

    public Episode getEnEpisodeISesong(String tittel, int sesong, int episode);

    public ArrayList<Episode> getEpisoderISesong(String tittel, int sesong);



}
