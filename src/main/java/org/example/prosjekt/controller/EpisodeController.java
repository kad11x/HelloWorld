package org.example.prosjekt.controller;

import io.javalin.http.Context;
import org.example.prosjekt.model.Episode;
import org.example.prosjekt.model.Produksjon;
import org.example.prosjekt.data.TvSerieRepository;

import java.util.ArrayList;
import java.util.Comparator;

public class EpisodeController {

    private TvSerieRepository tvSerieRepository;

    public EpisodeController(TvSerieRepository tvSerieRepository) {
        this.tvSerieRepository = tvSerieRepository;
    }

    public void enEpisode(Context context) {

        String tvSerienavn = context.pathParam("{tvserie-id}");
        int sesong = Integer.parseInt(context.pathParam("{sesong-nr}"));
        int episode = Integer.parseInt(context.pathParam("{episode-nr}"));


        context.json(tvSerieRepository.getEnEpisodeISesong(tvSerienavn,sesong,episode));

    }

    public void alleEpisoder(Context ctx) {

        String tvSerienavn = ctx.pathParam("{tvserie-id}");
        int sesong = Integer.parseInt(ctx.pathParam("{sesong-nr}"));
        String sortering = ctx.queryParam("sortering");

        //context.json(tvSerieRepository.getEpisoderISesong(tvSerienavn,sesong));

        ArrayList<Episode> episoder = tvSerieRepository.getEpisoderISesong(tvSerienavn,sesong);
        switch (sortering) {
            case "episodenr" -> {
                episoder.sort(Comparator.comparing(Episode::getEpisodeNummer));
                ctx.json(episoder);
            }
            case "tittel" -> {
                episoder.sort(Comparator.comparing(Produksjon::getTittel));
                ctx.json(episoder);
            }
            case "spilletid" -> {
                episoder.sort(Comparator.comparing(Produksjon::getSpilletid).reversed());
                ctx.json(episoder);
            }
            default -> {
                ctx.json(episoder);
            }
        }



    }
}
