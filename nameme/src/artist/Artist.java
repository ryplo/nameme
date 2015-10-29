package artist;

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

import scraper.Scraper;

public class Artist {
	
	private static Scanner myScanner = new Scanner(System.in);
	private static final String URL = "http://search.azlyrics.com/search.php?q=";
	private static List<String> results = new ArrayList<String>();
	
	public static String getArtist() throws IOException, URISyntaxException {
		results = getArtistResults();
		return getResultsChoice();
	}
	
	public static String getArtistInput() {
		System.out.println("Which artist do you think you know best?");
		String artist = myScanner.nextLine();
		return artist;
	}
	
	public static List<String> getArtistResults() throws IOException, URISyntaxException {
		Scraper scraper = new Scraper();
		Document doc = scraper.connectHtml(URL, getArtistInput());
		return results = scraper.getResults(doc);
	}
	
	public static String getResultsChoice() {
		int resultsSize = results.size();
		System.out.println("Select by typing in the number");
		for (int i = 0; i < resultsSize; i++) {
			System.out.println(results.get(i));
		}
		String choice = myScanner.nextLine();
		System.out.println(results.get(Integer.parseInt(choice)-1));
		return choice;
	}
	
}
