package scraper;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class Scraper {
	
	public static Document connectHtml(String link, String search) throws IOException, URISyntaxException {
		Document doc = Jsoup.connect(link + search).get();
		String title = doc.title();
		System.out.println(title);
		return doc;

	}
	
	public static List<String> getResults(Document doc) {
		List<String> results = new ArrayList<String>();
		Element resTable = doc.select("table").first();
		Element resBody = resTable.select("tbody").first();
		Elements resRows = resBody.select("tr");
		Iterator<Element> resIt = resRows.iterator();
		int i = 0;
		
		while(resIt.hasNext()) {
			Element resTr = resIt.next();
			Element resTd = resTr.select("td").first();
			results.add(resTd.text());
			i++;
		}
		return results;
	}
	
}

