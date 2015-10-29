package artist;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.*;
import java.util.Scanner;


import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import scraper.Scraper;

public class Artist {
	
	private static Scanner myScanner = new Scanner(System.in);
	private static final String URL = "http://search.azlyrics.com/search.php?q=";
	private static List<String> results = new ArrayList<String>();
	private static int choiceNum;
	private static String choiceArtist;
	
	public static String getArtist() throws IOException, URISyntaxException {
		setArtistResults();
		getResultsInput();
		formatArtistChoice();
		return choiceArtist;
	}
	
	public static String getArtistInput() {
		System.out.println("Which artist do you think you know best?");
		String artist = myScanner.nextLine();
		return artist;
	}
	
	public static void setArtistResults() throws IOException, URISyntaxException {
		Scraper scraper = new Scraper();
		Document doc = scraper.connectHtml(URL, getArtistInput());
		results = scraper.getResults(doc);
	}
	
	
	public static void getResultsInput() {
		int resultsSize = results.size();
		System.out.println("Select by typing in the number");
		for (int i = 0; i < resultsSize; i++) {
			System.out.println(results.get(i));
		}
		String choiceIn = myScanner.nextLine();
		System.out.println(results.get(Integer.parseInt(choiceIn)-1));
		choiceNum = (Integer.parseInt(choiceIn)-1);
	}
	
	public static void formatArtistChoice() {
		choiceArtist = results.get(choiceNum).replaceAll("[^a-zA-Z ]", "").trim();
		System.out.println(choiceArtist);
	}
	
}
