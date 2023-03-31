package org.example.prosjekt.controller;

import io.javalin.http.Context;
import org.example.prosjekt.data.TvSerieRepository;
import org.example.prosjekt.model.Episode;
import org.example.prosjekt.model.Produksjon;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

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

    public void updateEpisodeController(Context context){
        //lager pathparm
        String serie = context.pathParam("tvserie-id");
        int sesong = Integer.parseInt(context.pathParam("sesong-nr"));
        int episodeNr = Integer.parseInt(context.pathParam("episode-nr"));

        String title = context.formParam("tittel");
        int sesongNummer = Integer.parseInt(context.formParam("sesongNummer"));
        int episodeNummer = Integer.parseInt(context.formParam("episodeNummer"));
        String beskrivelse = context.formParam("beskrivelse");
        double spilletid = Double.parseDouble(Objects.requireNonNull(context.formParam("spilletid")));
        LocalDate utgivelsesdato = LocalDate.parse(context.formParam("utgivelsesdato"));
        String bildeUrl = context.formParam("bildeUrl");

        tvSerieRepository.updateEpisode(serie,sesong,episodeNr,title,sesongNummer,episodeNummer,beskrivelse,spilletid,utgivelsesdato,bildeUrl);

        context.redirect("/tvserie/" + serie + "/sesong/" + sesongNummer+"/episode/"+episodeNr);

    }

    public void createEpisodeController(Context context){
        //params for å koble med vue component
        String title = context.formParam("tittel");
        int sesongNr = Integer.parseInt(context.formParam("sesongNummer"));
        int episodeNr = Integer.parseInt(context.formParam("episodeNummer"));
        String beskrivelse = context.formParam("beskrivelse");
        double spilletid = Double.parseDouble(Objects.requireNonNull(context.formParam("spilletid")));
        LocalDate  utgivelsesdato = LocalDate.parse(context.formParam("utgivelsesdato"));
        String  bildeUrl = context.formParam("bildeUrl");

        //finne riktig tvserie å legge episoden til
        String tvserie = context.pathParam("tvserie-id");

        //lager episode i jason
        tvSerieRepository.createEpisode(tvserie,title,sesongNr,episodeNr,beskrivelse,spilletid,utgivelsesdato,bildeUrl);


        context.redirect("/tvserie/" + tvserie + "/sesong/" + sesongNr);
        //context.redirect("/tvserie/" + tvserie + "/sesong/" + sesongNr + "/episodeNummer/" + episodeNr);

    }

    public void deleteEpisodeController(Context context){
        String serie = context.pathParam("tvserie-id");
        int sesong = Integer.parseInt(context.pathParam("sesong-nr"));
        int episode = Integer.parseInt(context.pathParam("episode-nr"));

        tvSerieRepository.deleteEpisode(serie,sesong,episode);
        context.redirect("/tvserie/" + serie + "/sesong/" + sesong);
    }



}
