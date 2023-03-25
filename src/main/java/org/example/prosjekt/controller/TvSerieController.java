package org.example.prosjekt.controller;

import io.javalin.http.Context;
import org.example.prosjekt.model.TvSerie;
import org.example.prosjekt.data.TvSerieRepository;

import java.util.ArrayList;

public class TvSerieController {

    private TvSerieRepository tvSerieRepository;



    public TvSerieController(TvSerieRepository tvSerieRepository) {
        this.tvSerieRepository = tvSerieRepository;


    }




    //henter ut tvseriinfo som sendes videre til applicationen
    public void getTvSerie(Context context) {


        ArrayList<TvSerie> tvserier = tvSerieRepository.getTvSerier();

        context.json(tvserier);

    }

    public void enTvSerie(Context context) {

        String tvSerienavn = context.pathParam("{tvserie-id}");


        context.json(tvSerieRepository.hentTvSerie(tvSerienavn));



    }














}
