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
                //   System.out.println(values[0] + " "+ values[1] + " "+values[2]+ " "+ values[3]+ " "+ values[4]+ " "+values[5]+ " "+values[6] + " "+values[7]+ " "+values[8]+ " "+values[9] + " "+values[10] + " "+values[11]+ " "+values[12] + " "+values[13]);

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




                //Here in first iteration of loop, the serieHash<title> is null. There is no value in it.
                //"title" is the key. So it has corresponding value.
                TvSerie serie = filmList.get(title);

                //When there is no value in "title", new TVserie object is created, also title with value is put() in serieHash
                //this means as long serieHash<title> is in not empty/null, no duplicate of current <title> will be created this.
                //this way below the if-block, episodes can be added to their correct TVSerie object
                //
                if (serie == null) {
                    //create tvserie from current iteration
                    serie = new TvSerie(title,beskrivelse, LocalDate.parse(serieDato),serieBildeurl);
                    //add serie object as value to hashmap
                    filmList.put(title, serie);

                }
                //Add episode to serie

                Person regissor = new Person(skueSpillerFornavn,skuespillerEtternavn,LocalDate.parse(skuespiller_birthday));
                Episode episode = new Episode(epTitle,Integer.parseInt(episodeNr),Integer.parseInt(sesongNr),Integer.parseInt(spilleTid),LocalDate.parse(episodeDato),episodeBeskrivelse,regissor,episodeBildeurl);
                episode.setRegissor(regissor);
                serie.addEpisode(episode);







            } // while loop ends here

            tvserieListe = new ArrayList<>(filmList.values());
            System.out.println(filmList.keySet());


            //oppgave 2.2-C not working well
            writeToCSV(tvserieListe,"test.csv");

        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }


    } //end of constructor


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

    //legge til 3 metoder til  sånna t jeg skan skrive fil til siden

    // oppgave 2.2 c

    public void writeToCSV (ArrayList<TvSerie> liste, String filepath) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {

            for (TvSerie serie : liste){
                for (Episode e: serie.getEpisoder()) {
                    //writing the tvserie
                    writer.write(serie.getTittel()+";"+ serie.getBeskrivelse()+";"+serie.getUtgivelsesdato()+ ";"+ serie.getBildeUrl());
                    //then writing the episode
                    writer.write(e.getTittel()+";"+e.getBeskrivelse()+";"+ e.getEpisodeNummer()+ ";"+ e.getSesongNummer()+ ";"+ e.getSpilletid()+ ";"+ e.getutgivelsesdato()+ ";"+ e.getBildeUrl()+ ";"+ e.getRegissor().getFornavn()+";"+ e.getRegissor().getEtternavn()+";"+e.getRegissor().getFodselsdato());
                    //new line
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //oppgave 2.3 - a create, update, delete methods


    //@Override
    public void createEpisode(String tvserie, String title, int sesonNr, int episodeNr, String beskrivelse, double spilletid, LocalDate utgivelsesdato, String bildeurl) {

    }

    //@Override
    public void updateEpisode(String tvserie, int sesongNr, int episodeNr, String title, int sesongNummer, int episodeNummer, String beskrivelse, double spilletid, LocalDate utgivelsesdato, String bildeurl) {

    }

    //@Override
    public void deleteEpisode(String tvserie, int sesongNr, int episodeNr) {

    }
}
