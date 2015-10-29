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

import artist.Artist;
import scraper.Scraper;

public class Nameme {

	private static Scanner myScanner = new Scanner(System.in);
	private static Scraper scraper;
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		Artist artist = new Artist();
		artist.getArtist();
	}

	


}
