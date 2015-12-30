package utils;

public class Song {
	
	private String songName;
	private String songUrl;
	private String songLyrics;
	private boolean isCorrect;
	
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public String getSongUrl() {
		return songUrl;
	}
	public void setSongUrl(String songUrl) {
		this.songUrl = songUrl;
	}
	public String getSongLyrics() {
		return songLyrics;
	}
	public void setSongLyrics(String songLyrics) {
		this.songLyrics = songLyrics;
	}
	public boolean isCorrect() {
		return isCorrect;
	}
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	
	
}
