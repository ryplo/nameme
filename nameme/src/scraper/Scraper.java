package scraper;

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

public class Scraper {

	private static Scanner myScanner = new Scanner(System.in);
	
	public static void main(String[] args) throws URISyntaxException, IOException {
		Document doc = getHtml(getArtistInput());		
		getResultsChoice(getResults(doc));
	}
	
	public static String getArtistInput() {
		System.out.println("Which artist do you think you know best?");
		String artist = myScanner.nextLine();
		return artist;
	}
	
	
	public static Document getHtml(String artist) throws IOException, URISyntaxException {
		Document doc = Jsoup.connect("http://search.azlyrics.com/search.php?q=" + artist).get();
		String title = doc.title();
		System.out.println(title);
		return doc;
	//	printHeader(doc);
	//	printBody(doc);

	}
	
	public static List<String> getResults(Document doc) {
		List<String> results = new ArrayList<String>();
		Element resTable = doc.select("table").first();
		Element resBody = resTable.select("tbody").first();
		Elements resRows = resBody.select("tr");
		Iterator<Element> resIt = resRows.iterator();
		int i = 0;
		
		while(resIt.hasNext()) {
			Element resTr = resIt.next();
			Element resTd = resTr.select("td").first();
			results.add(resTd.text());
			i++;
		}
		return results;
	}
	
	public static String getResultsChoice(List<String> results) {
		int resultsSize = results.size();
		System.out.println("Select by typing in the number");
		for (int i = 0; i < resultsSize; i++) {
			System.out.println(results.get(i));
		}
		String choice = myScanner.nextLine();
		return choice;
	}
	
	
	public static void printHeader(Document doc) {
		//parse + print headings 
		Element thead = doc.select("thead").first();
		Element trhead = thead.select("tr").first();
		Elements ths = trhead.select("th");
		Iterator<Element> thIt = ths.iterator();
		
		boolean header = true;

			for (int i = 0; i < 6; i++) {
				if (header) {
					Element th = (Element) thIt.next();
					if ( (i == 0) || (i == 4) || (i == 5) )
					{
					System.out.print(th.text() + "     ");
					}
				}
			}
		System.out.println("");
	}
	
	
	//prints body of table
	public static void printBody(Document doc) {
		//parse + print body
		Iterator<Element> trIt = getBodyTrIt(doc);
		while (trIt.hasNext()) {
			Element tr = trIt.next();
			Elements tds = tr.select("td");
			Iterator<Element> tdIt = tds.iterator();
			for (int i = 0; i < 6; i++) {
				Element td = (Element) tdIt.next();
				if ( (i == 0) || (i == 4) || (i == 5) )
				{
				System.out.print(td.text() + "     ");
				}
			}
			System.out.println("");
		}
	}
	
	//returns iterator for body table row elements
	public static Iterator<Element> getBodyTrIt(Document doc) {
		Element table = doc.select("tbody").first();
		Elements trs = table.select("tr");
		Iterator<Element> trIt = trs.iterator();
		return trIt;
	}
}

