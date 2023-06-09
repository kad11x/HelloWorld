package org.example.prosjekt.data;

import org.example.prosjekt.model.Episode;
import org.example.prosjekt.model.Person;
import org.example.prosjekt.model.TvSerie;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TvSerieCSVRepository implements TvSerieRepository {
    //creating instansvariable for the arraylist that will keep the informasjon after we have read the csv file
    private ArrayList<TvSerie> tvserieListe = new ArrayList<>();
    //creating a hashmap that will keep the data linked bi the tittle and the type tvserie.
    //we are using LinkedHashMap because it gives the data in the best order.
    LinkedHashMap<String, TvSerie> filmList = new LinkedHashMap<>();

    public TvSerieCSVRepository(String file, String splitter) throws IOException {

        try(BufferedReader readCSV = new BufferedReader(new FileReader(file))){
            //line which stores each line in csv
            String line;
            //skip first line since header is text
            //while loop read each line in csv
            while((line = readCSV.readLine()) != null){
                String[] values = line.split(splitter);

                //her lager vi variabel av typen String som inneholder di forsjellige variablene som vi treger og leser fra csv filen som er like values og indesxen demmes.
                //Tvserie
                String title = values[0];
                String beskrivelse = values[1];
                String serieDato = values[2];
                String serieBildeurl = values[3];

                //episodes
                String epTitle = values[4];
                String episodeBeskrivelse = values[5];
                String episodeNr = values[6];
                String sesongNr = values[7];
                String spilleTid = values[8];
                String episodeDato = values[9];
                String episodeBildeurl = values[10];
                //skue spiller
                String skueSpillerFornavn = values[11];
                String skuespillerEtternavn = values[12];
                String skuespiller_birthday = values[13];




                //tom først
                TvSerie serietittle = filmList.get(title);


                if (serietittle == null) {
                    //create tvserie from current iteration
                    serietittle = new TvSerie(title,beskrivelse, LocalDate.parse(serieDato),serieBildeurl);
                    //add serie object as value to hashmap
                    filmList.put(title, serietittle);

                }
                //Add episode to serie

                Person regissor = new Person(skueSpillerFornavn,skuespillerEtternavn,LocalDate.parse(skuespiller_birthday));
                Episode episode = new Episode(epTitle,Integer.parseInt(episodeNr),Integer.parseInt(sesongNr),Double.parseDouble(spilleTid),LocalDate.parse(episodeDato),episodeBeskrivelse,regissor,episodeBildeurl);
                serietittle.addEpisode(episode);







            }

            tvserieListe = new ArrayList<>(filmList.values());




            writeToCSV(tvserieListe,"test.csv");

        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }


    }


    @Override
    public ArrayList<TvSerie> getTvSerier() {
        return tvserieListe;
    }

    @Override
    public TvSerie hentTvSerie(String tittel) {
        for (TvSerie bestemtTvSerie : tvserieListe) {
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



    public void writeToCSV (ArrayList<TvSerie> liste, String filepath) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {


                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {

                    for (TvSerie serie : liste) {
                        for (Episode e : serie.getEpisoder()) {
                            writer.write(serie.getTittel() + ";" + serie.getBeskrivelse() + ";" + serie.getUtgivelsesdato() + ";" + serie.getBildeUrl() + ";");
                            writer.write(e.getTittel() + ";" + e.getBeskrivelse() + ";" + e.getEpisodeNummer() + ";" + e.getSesongNummer() + ";" + e.getSpilletid() + ";" + e.getutgivelsesdato() + ";" + e.getBildeUrl() + ";" + e.getRegissor().getFornavn() + ";" + e.getRegissor().getEtternavn() + ";" + e.getRegissor().getFodselsdato());
                            writer.newLine();
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        thread.start();
        System.out.println(thread.getName());


    }

    @Override
    public void updateEpisode(String tvserie, int sesongNr, int episodeNr, String title, int sesongNummer, int episodeNummer, String beskrivelse, double spilletid, LocalDate utgivelsesdato, String bildeurl) {
        //episoden vi skal foradnre på
        TvSerie TS = hentTvSerie(tvserie);

        //vi oppdater med dataen brukern legger inn
        TS.updateEpisode(title,sesongNummer,episodeNummer,beskrivelse,spilletid,utgivelsesdato,bildeurl);

        //skriver dertetter til csv fil
        writeToCSV(tvserieListe,"tester2.csv");

    }


    @Override
    public void createEpisode(String tvserie, String title, int sesonNr, int episodeNr, String beskrivelse, double spilletid, LocalDate utgivelsesdato, String bildeurl) {
        //tvserien som blir kalt
        TvSerie TS= hentTvSerie(tvserie);

        //bruker metoden create for å lagge til en episode
        TS.createEpisode(title,sesonNr,episodeNr,beskrivelse,spilletid,utgivelsesdato,bildeurl);

        //skriver til csv fil
        writeToCSV(tvserieListe,"tester2.csv");

    }






    @Override
    public void deleteEpisode(String tvserie, int sesong, int episode) {
        TvSerie serie = hentTvSerie(tvserie);
        //lage metoden i tvserie
        serie.deleteEpisode(sesong,episode);


        //skriver til csv fil
        writeToCSV(tvserieListe,"tester2.csv");

    }
}
