package client.model.library;

import client.model.anime.Anime;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Library {

    private final String FILE = "Library.ser";
    private List<Anime> library;
    public Library(){

        library = getListfromFile(FILE);

    }






    public ArrayList<String> getEpisodes(Anime anime){
        ArrayList<String> episodes = new ArrayList<String>();

        if(nullCheck()){
            Anime entry = getAnimeByTitle(anime.getName());
            if(entry!=null){
                for(String episode : entry.getEpisodeList()){
                    episodes.add(episode);
                }
            }
        }


        return episodes;
    }

    public String getImageUrl(Anime anime){
        return anime.getImage();
    }

    public String summary(Anime anime){
        return  anime.getSummary();
    }

    public ArrayList<String> getLibrary(){
       ArrayList<String> lib = new ArrayList<String>();
        if(nullCheck()){
            for(Anime anime: library){
                lib.add(anime.getName());
            }
        }
        return lib;
    }

    public Anime getAnimeByTitle(String title){
        Anime tmp = new Anime(null, null,null,0,null);

        if(nullCheck()){
            for(Anime anime: library)
                if(anime.getName().equals(title))
                    return anime;
        }

        return tmp;
    }

    private boolean nullCheck(){
        return library!=null || !library.isEmpty();
    }


    public void addToLibrary(Anime anime){
        library.add(anime);
    }
    public List<Anime> getAnimes(){
        return library;
    }

    private List<Anime> getListfromFile(String fileName){
        List<Anime> recoveredQuarks = null;
        try{
            InputStream file = new FileInputStream(fileName);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream (buffer);
            try{
                recoveredQuarks = (List<Anime>)input.readObject();


            }
            finally {
                input.close();
            }

        }
        catch(ClassNotFoundException ex){
            System.out.println("class not found");

        }
        catch(IOException ex){
            System.out.println("ioexception");

        }
        finally {
            return recoveredQuarks;
        }


    }






}
