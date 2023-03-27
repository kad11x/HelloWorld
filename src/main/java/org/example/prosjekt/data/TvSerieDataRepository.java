package org.example.prosjekt.data;

import org.example.prosjekt.model.Episode;
import org.example.prosjekt.model.Person;
import org.example.prosjekt.model.TvSerie;

import java.time.LocalDate;
import java.util.ArrayList;

public class TvSerieDataRepository implements TvSerieRepository{

    private ArrayList<TvSerie> tvSerierliste = new ArrayList<>();



//sette opp en konstruktør
    public TvSerieDataRepository() {
        //oprette minst en tv serie med noen episoder og legge dem til i listen
        TvSerie theWitcher= new TvSerie("The Witcher",
                "Hekser og eventyr", LocalDate.now(),"https://static.posters.cz/image/750/lerretsbilde-the-witcher-stare-i115526.jpg");
        theWitcher.addEpisode(new Episode("Wichcraft",1,1,50,LocalDate.now(),"syke hekser som synger og danser hiphop",new Person("navn","kerr",LocalDate.now()),"https://www.theworkprint.com/wp-content/uploads/2019/12/Refugee.jpg"));
        theWitcher.addEpisode(new Episode("Made of steel",2,1,45,LocalDate.now(),"sNå er di lei av danse så di spiser bruker på mc donlds",new Person("mc chees","blabla",LocalDate.now()),"https://showsnob.com/files/image-exchange/2016/04/ie_32068-1.jpeg"));
        theWitcher.addEpisode(new Episode("Dont do it",1,2,56, LocalDate.now(),"Di dro deretter til fredrikstad for å se på statuen av han som bærer planke",new Person("planke per","blabla",LocalDate.now()),"https://m.media-amazon.com/images/M/MV5BYWZlZTc0YmUtZjU0OS00MmU4LThjZjgtZmY1MDI3ZTU4M2YzXkEyXkFqcGdeQXVyMTM2MDI5MzM0._V1_.jpg"));
        theWitcher.addEpisode(new Episode("The End",2,2,66,LocalDate.now(),"Det er kaldt og kjedelig, de drar hjem for å spille dataspill",new Person("spill spiltesen","blabla",LocalDate.now()),"https://pbs.twimg.com/media/FAJ3togXMAE4jU9.png"));


        //Enda et tvserie med noen episoder:
        TvSerie strangerThing= new TvSerie("Stranger Things",
                "World under", LocalDate.now(),"https://media.nbclosangeles.com/2022/05/StrangerThings_S3_Illustrated_Vertical_FINAL_RGB_Digital__EN.jpg?quality=85&strip=all&resize=1200%2C675");
        strangerThing.addEpisode(new Episode("Chapter One: The Vanishing of Will Byers",
                1,1,70,LocalDate.now(),"rare mennesker som går rundt og opp og ned og mye rart og enda mere rundt til di er lei for det er jeg",new Person("kadir amini","blabla",LocalDate.now()),"https://hips.hearstapps.com/hmg-prod/images/stranger-things-season-4-part-2-1653325426.jpeg"));
        strangerThing.addEpisode(new Episode("Chapter Two: The Weirdo on Maple Street",
                2,1,56,LocalDate.now(),"måtte selvsagt skrive en til så her er den siste så er kodesnutten ute",new Person("The End","blabla",LocalDate.now()),"https://sm.ign.com/ign_in/news/s/stranger-t/stranger-things-season-5-the-title-of-the-first-episode-has_y7fd.jpg"));
        //legger til sereiene i lista.
        tvSerierliste.add(theWitcher);
        tvSerierliste.add(strangerThing);


    }



    public ArrayList<TvSerie> getTvSerier() {
        return tvSerierliste;
    }



    //henter ut en bestemt tv serie
    public TvSerie hentTvSerie(String tittel) {
        for (TvSerie bestemtTvSerie : tvSerierliste) {
            //må bruke eaquales!!!!
            if (bestemtTvSerie.getTittel().equals(tittel)) {
                return bestemtTvSerie;
            }
        }
        //hvis den ikke eksistrer så returneres null.
        return null;
    }


    public ArrayList<Episode> getEpisoderISesong(String tittel, int sesong) {
        return hentTvSerie(tittel).hentEpisoderISesong(sesong);

    }

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


}
