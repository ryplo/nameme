package scraper;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class Scraper {
	
	public Document connectHtml(String link) throws IOException, URISyntaxException {
		Document doc = Jsoup.connect(link).get();
		String title = doc.title();
		System.out.println(title);
		return doc;
	}
	
	public void findArtistResults(Document doc, List<String> artists, List<String> links) {
//		Map<String, String> results = new HashMap<String,String>();
//		HashMap<String, String> results = new HashMap<String, String>();
//		List<String> artists = new ArrayList<String>();
//		List<String> links = new ArrayList<String>();
//		ArrayList<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();
		Element resTable = doc.select("table").first();
		Element resBody = resTable.select("tbody").first();
		Elements resRows = resBody.select("tr");
		Iterator<Element> resIt = resRows.iterator();
		int i = 0;
		
		while(resIt.hasNext()) {
			Element resTr = resIt.next();
			Element resTd = resTr.select("td").first();
			Element resLink = resTd.select("a").first();
//			HashMap<String, String> result = new HashMap<String, String>();
//			result.put(resTd.text(), resLink.attr("href"));
//			results.add(result);
			artists.add(resTd.text());
			links.add(resLink.attr("href"));
			System.out.println(resTd.text() + "\t" + resLink.attr("href"));
			i++;
		}
	}
	
	public List<String> findAlbumResults(Document doc) {
		List<String> results = new ArrayList<String>();
		Elements divs = doc.getElementsByClass("album");
		Iterator<Element> divsIt = divs.iterator();
		int i = 0;
		
		while(divsIt.hasNext()) {
			Element div = divsIt.next();
			System.out.println(div.select("b").text());
			results.add(div.select("b").text());
			i++;
		}
		
		return results;
	}
	
}

