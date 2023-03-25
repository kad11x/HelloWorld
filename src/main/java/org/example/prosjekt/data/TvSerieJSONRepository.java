package org.example.prosjekt.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.prosjekt.model.Episode;
import org.example.prosjekt.model.TvSerie;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TvSerieJSONRepository implements TvSerieRepository {

    private ArrayList<TvSerie> jsonListeTvserie;



    //Lager en konstruktør som tar i mot en fil og leser det til arraylist.
    public TvSerieJSONRepository(String file) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        jsonListeTvserie = mapper.readValue(new File(file), new TypeReference<ArrayList<TvSerie>>() {});
    }

    //skulle også lage en egen fil metode som leser informasjonen fra JSON fila.
    public static List<TvSerie> readFromJson(String filepath) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            //vruker denne om jeg skal ha med local time:
            objectMapper.registerModule(new JavaTimeModule());

            TvSerie[] tvseriesArray = objectMapper.readValue(new File(filepath), TvSerie[].class);

            return Arrays.asList(tvseriesArray);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //sjekke hva som er henrikten:
        return new ArrayList<>();

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


    //skriver til json format:
    public static void writeToJson (ArrayList<TvSerie> tvserie, String filepath) {
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
