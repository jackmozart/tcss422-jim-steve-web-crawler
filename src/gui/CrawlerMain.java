package gui;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

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
			a_page = PageRetriever.retrieve(new URI("http://www.jpgunter.com/crawler/"));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(a_page.getContents());
		
		PageParser.parse(a_page);
		
		List<URI> urls = a_page.getLinks();
		
		for(URI u:urls){
			System.out.println("link found:" + u);
		}
		
		for(String w:a_page.getWords()){
			System.out.println("word: "+w);
		}
		
		
		
		//CrawlerGUI a_CrawlerGui = new CrawlerGUI();
		//a_CrawlerGui.start();
	}

}
