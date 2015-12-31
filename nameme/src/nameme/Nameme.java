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
		int score = 0;
		List<Artist> artistChoices = new ArrayList<Artist>();
		do {
			System.out.println("Which artist do you think you know best?");
			String artist = myScanner.nextLine();
			artistChoices = scraper.findArtistResults(URL + artist);
		} while (artistChoices.isEmpty());
		
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
		
		// do while they don't press the 'new artist/album' button
		do {
			// what if album is less than 3 songs? 
			List<Song> randomSongs = albumChoice.getRandomSongList();
			Random rand = new Random();
			System.out.println("random song size: " + randomSongs.size());
			int randNum = rand.nextInt(randomSongs.size());
			System.out.println("RANDNUM here: " + randNum);
			Song correctSong = randomSongs.get(randNum);
			List<String> songLyric = scraper.getRandomLyric(correctSong);
	
			System.out.println("\nWhich song are these lyrics from?");
			for (String lyric : songLyric) {
				System.out.println(lyric);
			}
			System.out.println("\nYour choices: ");
			int c = 0;
			for (Song song : randomSongs) {
				c++;
				System.out.println(c + song.getSongName());
			}
			
			// in the actual game, will not be sending int, but song name through button
			choice = myScanner.nextLine();
			
			Song songGuess = randomSongs.get(Integer.valueOf(choice) - 1);
			
			if (songGuess == correctSong) {
				score++;
				System.out.println("yay! score = " + score);
				
			}
			else {
				System.out.println("no you failed. score = " + score);
			}
		} while (!choice.equals("5"));
		
		
		
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
