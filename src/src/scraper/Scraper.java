package scraper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class Scraper {

	public static void main(String[] args) {
	try {
		getHtml();
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
	
	
	public static void getHtml() throws IOException {
		Document doc = Jsoup.connect("http://www.jsoup.org/").get();
		Elements links = doc.select("a");
		for(Element e : links)
		{
			System.out.println(e.attr("href"));
		}
			System.out.println("yay!");
	}

}
