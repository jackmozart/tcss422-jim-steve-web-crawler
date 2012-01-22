package gui;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import page.Page;
import parser.PageParser;
import retriever.PageRetriever;

public class CrawlerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Page a_page = null;

		try {
			a_page = PageRetriever.retrieve(new URI("http://www.crypto.com/papers/"));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(a_page.getHTML());
		
		PageParser.parse(a_page);
		
		List<URI> urls = a_page.getLinks();
		
		for(URI u:urls){
			System.out.println("link found:" + u);
		}
		
		List<String> words = a_page.getWords();
		
		for(String s : words){
			System.out.format("Word: %s\n", s);
		}
		
		
		
		//CrawlerGUI a_CrawlerGui = new CrawlerGUI();
		//a_CrawlerGui.start();
	}

}
