package client;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {


        try {
            Document doc = Jsoup.connect("http://www.soul-anime.tv/show/gundam-g-no-reconguista/").timeout(0).get();
            Element links = doc.select("ul[class]").first();
            Elements e =  links.select("a");

            System.out.println(Integer.parseInt(e.last().attr("href").replaceAll("[\\D]", "")));
            /*for(Element element : e){
                System.out.println(element.attr("href"));
            }*/


        } catch (IOException e) {
            e.printStackTrace();
        }




}


}
