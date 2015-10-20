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
		
		//parse + print headings 
		Element thead = doc.select("thead").first();
		Element trhead = thead.select("tr").first();
		Elements ths = trhead.select("th");
		Iterator<Element> thIt = ths.iterator();
		
		boolean header = true;

			for (int i = 0; i < 6; i++) {
				if (header) {
					Element th = (Element) thIt.next();
					if ( (i == 0) || (i == 4) || (i == 5) )
					{
					System.out.print(th.text() + "     ");
					}
				}
			}
		System.out.println("");
		
		//parse + print body
		Element table = doc.select("tbody").first();
		Elements trs = table.select("tr");
		Iterator<Element> trIt = trs.iterator();
		
		while (trIt.hasNext()) {
			Element tr = trIt.next();
			Elements tds = tr.select("td");
			Iterator<Element> tdIt = tds.iterator();
			for (int i = 0; i < 6; i++) {
				Element td = (Element) tdIt.next();
				if ( (i == 0) || (i == 4) || (i == 5) )
				{
				System.out.print(td.text() + "     ");
				}
			}
			System.out.println("");
				
		}
		

	}

}

