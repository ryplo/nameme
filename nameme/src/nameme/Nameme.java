package nameme;

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

import album.Album;
import artist.Artist;
import scraper.Scraper;

public class Nameme {
	
	private static Scanner myScanner = new Scanner(System.in);
	private static final String URL = "http://search.azlyrics.com/search.php?q=";
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		Scraper scraper = new Scraper();
		String artist = getArtistInput();
		List<Artist> artistChoices = scraper.findArtistResults(URL + artist);
		Artist artistChoice = getArtistChoice(artistChoices);
		System.out.println(artistChoice.getName() + " : " + artistChoice.getUrl());
		List<Album> albumResults = scraper.findAlbumResults(artistChoice.getUrl());
	}
	
	public static String getArtistInput() {
		System.out.println("Which artist do you think you know best?");
		String artist = myScanner.nextLine();
		return artist;
	}
	
	private static Artist getArtistChoice(List<Artist> artistResults) {
		int i = 1; 
		for (Artist artist : artistResults) {
			System.out.println(i + ". " + artist.getName());
			i++;
		}
		System.out.println("Enter your choice.");
		String choice = myScanner.nextLine();
		return artistResults.get(Integer.valueOf(choice) - 1);
	}
	
}
