package org.example.prosjekt.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.prosjekt.model.Episode;
import org.example.prosjekt.model.TvSerie;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class TvSerieJSONRepository implements TvSerieRepository {

    private ArrayList<TvSerie> jsonListeTvserie;



    //Lager en konstruktør som tar i mot en fil og leser det til arraylist.
    public TvSerieJSONRepository(String file) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        File fil = new File(file);
        try {
            TvSerie[] seriesFromJSON = mapper.readValue(fil, TvSerie[].class);


        jsonListeTvserie = new ArrayList<>(Arrays.asList(seriesFromJSON));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //jsonListeTvserie = mapper.readValue(new File(file), new TypeReference<ArrayList<TvSerie>>() {});
    }

    //skulle også lage en egen fil metode som leser informasjonen fra JSON fila.
    public ArrayList<TvSerie> readFromJson(String filepath) {
        ObjectMapper mapper = new ObjectMapper();

        //filePath
        File fil = new File(filepath);

        //module to be able to read LocalDate object correctly. This is needed in order to be able get LocalDate
        mapper.registerModule(new JavaTimeModule());

        //read tvseries from json filej
        try {
            TvSerie[] seriesFromJson = mapper.readValue(fil,TvSerie[].class);

            //  ArrayList<TVSerie> serieArrayList = new ArrayList<>(Arrays.asList(seriesFromJson));
            return new ArrayList<>(Arrays.asList(seriesFromJson));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    //Når du har implementert metodene i TvSerieJSONRepository, kan du bruke dette repositoriet i stedet for TvSerieDataRepository i
    // Application-klassen. Siden begge repositoriene implementerer samme grensesnitt, kan Controller-klassen
    // fortsatt bruke grensesnittet uten å vite om den konkrete implementasjonen.
    //Så lenge metodene i den nye implementasjonen av grensesnittet oppfyller kravene til Controller-klassen, vil det fungere fint sammen med Controlleren.



    @Override
    public ArrayList<TvSerie> getTvSerier() {
        return jsonListeTvserie;

    }

    @Override
    public TvSerie hentTvSerie(String tittel) {
        for (TvSerie bestemtTvSerie : jsonListeTvserie) {
            //må bruke eaquales!!!!
            if (bestemtTvSerie.getTittel().equals(tittel)) {
                return bestemtTvSerie;
            }
        }
        //hvis den ikke eksistrer så returneres null.
        return null;
    }

    @Override
    public Episode getEnEpisodeISesong(String tittel, int sesong, int episode) {
        //opretter en array list for å kunne holde på verdiene, som henter fra methoden over
        ArrayList<Episode> episode1 = getEpisoderISesong(tittel,sesong);
        //for alle x verdier i lista episode1 så gjør man følgende
        for (Episode x: episode1) {
            //hvis verdien i liista er like det epsiode nr vi legger inn
            if (x.getEpisodeNummer() == episode) {
                //returnerer verdien som vi da finner.
                return x;
            }

        }

        return null;
    }

    @Override
    public ArrayList<Episode> getEpisoderISesong(String tittel, int sesong) {

        return hentTvSerie(tittel).hentEpisoderISesong(sesong);
    }

    @Override
    public void createEpisode(String tvserie, String title, int sesonNr, int episodeNr, String beskrivelse, double spilletid, LocalDate utgivelsesdato, String bildeurl) {

    }

    @Override
    public void updateEpisode(String tvserie, int sesongNr, int episodeNr, String title, int sesongNummer, int episodeNummer, String beskrivelse, double spilletid, LocalDate utgivelsesdato, String bildeurl) {

    }

    @Override
    public void deleteEpisode(String tvserie, int sesongNr, int episodeNr) {

    }


    //skriver til json format:
    public void writeToJson (ArrayList<TvSerie> tvserie, String filepath) {
    ObjectMapper objectMapper = new ObjectMapper();
    File file = new File(filepath);
        try {
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, tvserie);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
