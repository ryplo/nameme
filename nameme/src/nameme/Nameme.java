package nameme;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import scraper.Scraper;
import utils.Album;
import utils.Artist;
import utils.Song;

public class Nameme {
	
	private static Scanner myScanner = new Scanner(System.in);
	private static final String URL = "http://search.azlyrics.com/search.php?q=";
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		Scraper scraper = new Scraper();
		System.out.println("Which artist do you think you know best?");
		//should validate
		String artist = myScanner.nextLine();
		List<Artist> artistChoices = scraper.findArtistResults(URL + artist);
		Artist artistChoice = getArtistChoice(artistChoices);
		System.out.println(artistChoice.getName() + " : " + artistChoice.getUrl());
		List<Album> albumResults = scraper.findAlbumResults(artistChoice.getUrl());
		System.out.println("Select an album");
		int i = 0;
		for (Album album : albumResults) {
			i++;
			System.out.println(i + ". " + album.getAlbumName());
		}
		String choice = myScanner.nextLine();
		Album albumChoice = albumResults.get(Integer.valueOf(choice) - 1);
		List<Song> randomSongs = albumChoice.getRandomSongList();
		Random rand = new Random();
		System.out.println("random song size: " + randomSongs.size());
		int randNum = rand.nextInt(randomSongs.size());
		System.out.println("RANDNUM: " + randNum);
		Song correctSong = randomSongs.get(randNum);
		List<String> songLyric = scraper.getRandomLyric(correctSong);
		for (Song song : randomSongs) {
			System.out.println(song.getSongName());
		}
		System.out.println("rando lyrics");
		for (String lyric : songLyric) {
			System.out.println(lyric);
		}
		// nameme - get artist from user
		// nameme - get album from user
		// album - get random song + two fakes, return array of three songs
		// nameme - take first of array, call scraper to find lyrics for that song populate lyrics 
		// nameme - call song to get random lyric
			
		// song - get random lyric
		// get user input on choice
		// is same? 
		
		//get albumresults
		//create game (albumresults)
			// in game
			// pick random song, and two other random songs for choices
			// scraper to navigate to song lyric page
			// pick random line - output
			// check selection
			// if keep same artist, keep looping through here
			// if quit or new artist, quit here 
		
		//album: 
			// get random songs 
		//song:
			//get random lyric 
		
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
