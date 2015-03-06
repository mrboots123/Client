package client.model.anime;


import java.io.Serializable;
import java.util.ArrayList;


public class Anime implements Serializable{

    private String name, image, summary;
    private int episodeCount;
    private ArrayList<String> episodeList;


    public Anime(String name, String image, String summary, int episodeCount, ArrayList<String> episodeList){
        this.name =name;
        this.image = image;
        this.summary = summary;
        this.episodeCount = episodeCount;
        this.episodeList = episodeList;

    }

    public String getName(){return name;}

    public String getImage(){return image;}

    public String getSummary(){return summary;}

    public int getEpisodeCount(){return episodeCount;}

    public ArrayList<String> getEpisodeList(){return episodeList;}

    public String toString(){
        return String.format("");
    }




}