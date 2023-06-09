package org.example;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.vue.VueComponent;
import org.example.prosjekt.controller.EpisodeController;
import org.example.prosjekt.controller.TvSerieController;
import org.example.prosjekt.data.TvSerieCSVRepository;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;




public class Application {



    public static void main(String[] args) throws IOException {


        Javalin app = Javalin.create(config -> {
            config.staticFiles.enableWebjars();
            config.vue.vueAppName = "app";
        }).start(4567);


        app.get("/", new Handler() {
                    @Override
                    public void handle(@NotNull Context context) throws Exception {
                        context.redirect("/tvserie");
                    }
                });

                app.get("/tvserie/{tvserie-id}/sesong/{sesong-nr}", new VueComponent("tvserie-detail"));

        app.get("/tvserie", new VueComponent("tvserie-overview"));

        app.get("/tvserie/{tvserie-id}/sesong/{sesong-nr}/episode/{episode-nr}", new VueComponent("episode-detail"));

        app.get("/tvserie/{tvserie-id}/sesong/{sesong-nr}/episode/{episode-nr}/updateepisode",new VueComponent("episode-update"));

        app.get("/tvserie/{tvserie-id}/createepisode", new VueComponent("episode-create"));


        /*TvSerieDataRepository tvserieRepository = new TvSerieDataRepository();
        TvSerieController tvserieController = new TvSerieController(tvserieRepository);
        EpisodeController episodeController = new EpisodeController(tvserieRepository);*/

        //tvseriedata:
        //TvSerieDataRepository tvserieRepository = new TvSerieDataRepository();

        //tvserieJSON:
        //TvSerieJSONRepository tvserieRepository = new TvSerieJSONRepository("tvshows_10_with_roles.json");

        //tvserieCSV:
        TvSerieCSVRepository tvserieRepository = new TvSerieCSVRepository("test.csv",";");
        //test filen:
        //TvSerieCSVRepository tvserieRepository = new TvSerieCSVRepository("test.csv",";");

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


        // oblig 5


        app.get("/api/tvserie/{tvserie-id}/sesong/{sesong-nr}/episode/{episode-nr}/deleteepisode", new Handler() {
            @Override
            public void handle( Context context) throws Exception {

                episodeController.deleteEpisodeController(context);


            }
        });



        app.post("/api/tvserie/{tvserie-id}/createepisode", new Handler() {
            @Override
            public void handle( Context context) throws Exception {
                episodeController.createEpisodeController(context);
            }
        });


        app.post("/api/tvserie/{tvserie-id}/sesong/{sesong-nr}/episode/{episode-nr}/updateepisode", new Handler() {
            @Override
            public void handle( Context context) throws Exception {
                episodeController.updateEpisodeController(context);
                //System.out.println(Thread.currentThread().getName());
            }
        });











    }



}
