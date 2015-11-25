package artistService;

import java.util.Map;

public class ArtistService {
	
	public String formatArtistChoice(int choiceNum, Map<String, String> results) {
		System.out.println(results.get(choiceNum).replaceAll("[^a-zA-Z]", "").trim());
		return results.get(choiceNum).replaceAll("[^a-zA-Z]", "").trim();
	}
	
}
