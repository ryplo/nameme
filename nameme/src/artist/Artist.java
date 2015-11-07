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
	
	public String getArtist() throws IOException, URISyntaxException {
		String artist = getArtistInput();
		List<String> results = setArtistResults(artist);
		int choiceNum = getResultsInput(results);
		return formatArtistChoice(choiceNum, results);
	}
	
	public String getArtistInput() {
		System.out.println("Which artist do you think you know best?");
		String artist = myScanner.nextLine();
		return artist;
	}
	
	public List<String> setArtistResults(String artist) throws IOException, URISyntaxException {
		Scraper scraper = new Scraper();
		Document doc = scraper.connectHtml(URL + artist);
		return scraper.findArtistResults(doc);
	}
	
	
	public int getResultsInput(List<String> results) {
		int resultsSize = results.size();
		System.out.println("Select by typing in the number");
		for (int i = 0; i < resultsSize; i++) {
			System.out.println(results.get(i));
		}
		String choiceIn = myScanner.nextLine();
		System.out.println(results.get(Integer.parseInt(choiceIn)-1));
		return (Integer.parseInt(choiceIn)-1);
	}
	
	public String formatArtistChoice(int choiceNum, List<String> results) {
		System.out.println(results.get(choiceNum).replaceAll("[^a-zA-Z]", "").trim());
		return results.get(choiceNum).replaceAll("[^a-zA-Z]", "").trim();
	}
	
}
