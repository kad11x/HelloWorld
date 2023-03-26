package org.example;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.vue.VueComponent;
import org.example.prosjekt.controller.EpisodeController;
import org.example.prosjekt.controller.TvSerieController;
import org.example.prosjekt.data.TvSerieJSONRepository;

import java.io.IOException;




public class Application {



    public static void main(String[] args) throws IOException {


        Javalin app = Javalin.create(config -> {
            config.staticFiles.enableWebjars();
            config.vue.vueAppName = "app";
        }).start(3456);


        //app.get("/", new VueComponent("hello-world"));

        app.get("/tvserie/{tvserie-id}/sesong/{sesong-nr}", new VueComponent("tvserie-detail"));

        app.get("/tvserie", new VueComponent("tvserie-overview"));

        app.get("/tvserie/{tvserie-id}/sesong/{sesong-nr}/episode/{episode-nr}", new VueComponent("episode-detail"));


        /*TvSerieDataRepository tvserieRepository = new TvSerieDataRepository();
        TvSerieController tvserieController = new TvSerieController(tvserieRepository);
        EpisodeController episodeController = new EpisodeController(tvserieRepository);*/

        //tvseriedata:
        //TvSerieDataRepository tvserieRepository = new TvSerieDataRepository();

        //tvserieJSON:
        TvSerieJSONRepository tvserieRepository = new TvSerieJSONRepository("tvshows_10.json");

        //tvserieCSV:
        //TvSerieCSVRepository tvserieRepository = new TvSerieCSVRepository("tvshows_10.csv",";");

        TvSerieController tvserieController = new TvSerieController(tvserieRepository);
        EpisodeController episodeController = new EpisodeController(tvserieRepository);




        // Denne gir en liste med tvserie
        app.get("/api/tvserie/", new Handler() {
            @Override
            public void handle(Context context) {
                tvserieController.getTvSerie(context);
            }
        });

        app.get("/api/tvserie/{tvserie-id}", new Handler() {
            @Override
            public void handle(Context context) {
                tvserieController.enTvSerie(context);
            }
        });

        app.get("/api/tvserie/{tvserie-id}/sesong/{sesong-nr}", new Handler() {
            @Override
            public void handle(Context context) {
                episodeController.alleEpisoder(context);
            }
        });

        app.get("/api/tvserie/{tvserie-id}/sesong/{sesong-nr}/episode/{episode-nr}", new Handler() {
            @Override
            public void handle(Context context) {
                episodeController.enEpisode(context);
            }
        });


        //TvSerieCSVRepository hei = new TvSerieCSVRepository("tvshows_10.cvs");
        //System.out.println(hei);












    }



}
