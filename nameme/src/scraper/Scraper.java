package scraper;

import java.io.IOException;
import java.util.Iterator;

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
		Document doc = Jsoup.connect("http://www.allsongsby.com/").get();
		String title = doc.title();
		System.out.println(title);
		
		Element table = doc.select("thead").first();
		Element tr = table.select("tr").first();
		Elements ths = tr.select("th");
		Iterator thIt = ths.iterator();
		
		for (int i = 0; i < 6; i++) {
			Element th = (Element) thIt.next();
			if ( (i == 0) || (i == 4) || (i == 5) )
			{
			System.out.print(th.text() + "     ");
			}
		}

	}

}


