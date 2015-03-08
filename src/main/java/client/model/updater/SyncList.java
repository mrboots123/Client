package client.model.updater;


import client.model.anime.Anime;
import client.model.library.Library;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by SamsungEvo on 3/5/2015.
 */
public class SyncList {
    final String NIL_KEYWORD = "BACK TO TOP";
    private final String MAIN_LIST_URL = "http://www.soul-anime.tv/anime-list.html";
    private final String INDEX_OF_START= "div id=\"A\" class=\"title\">";
    private final String INDEX_0F_END = "<a href=\"#back\">";
    private Document list;
    private Library library;
    private List<Anime> animes;

    public SyncList(){
        library = new Library();
        animes = library.getAnimes();
        try {
            String htmllList = Jsoup.connect(MAIN_LIST_URL).get().toString();
            htmllList = htmllList.substring(htmllList.indexOf(INDEX_OF_START),htmllList.lastIndexOf(INDEX_0F_END));
            list = Jsoup.parse(htmllList);
        } catch (IOException e) {
            System.out.println("Exception parsing document from JSoup");
            e.printStackTrace();
        }
    }


    public void test(){
        ArrayList<String> newTitles = hasNewTitles();
        if(newTitles.size() > 0){

            try {
                String s = Jsoup.connect(newTitles.get(0)).get().toString();
                System.out.println(s);
                Document formAnime = Jsoup.parse(s);
                Element synopsis = formAnime.select("p").first();
                Element title = formAnime.select("strong").get(1);
                Element image = formAnime.select("").first();
                System.out.println(synopsis.text());
            } catch (IOException e) {
                System.out.println("Exception adding new titles");
                e.printStackTrace();
            }
            for(String url : newTitles){

            }

        }



    }


    public ArrayList hasNewTitles() {
        ArrayList<String> urls = new ArrayList<String>();

        List<String> names = new LinkedList<String>();
        Elements elements = list.select("li");



        for (Element element : elements) {

            if (!element.text().equals(NIL_KEYWORD)){
                names.add(element.text());

            }


        }


        List<String> tmpAnime = new LinkedList<String>();
        for (Anime anime : library.getAnimes()) {
           // System.out.println(anime.getName());
            tmpAnime.add(anime.getName());
        }

        for(int i =0; i < names.size(); i++){
            if (tmpAnime.contains(names.get(i))) {
                // System.out.println("d");
            } else {
                urls.add(elements.get(i).select("a[href]").attr("href"));
                //System.out.println( elements.get(i).select("a[href]").attr("href"));
            }
        }
        return urls;
    }

}


