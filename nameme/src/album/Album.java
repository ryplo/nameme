package album;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;

import scraper.Scraper;

public class Album {
	
	private static final String URL = "http://www.azlyrics.com/";
	
	public List<String> formatAlbums(List<String> results) {
		int i = 0; 
		for (String result : results) {
			i++;
			result = result.replaceAll("[\"]", "");
			System.out.println(i + ". " + result);
		}
		return results;
	}
	
	
}
