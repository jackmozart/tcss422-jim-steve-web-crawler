package gui;

import java.net.MalformedURLException;
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
			a_page = PageRetriever.retrieve(new URL("http://www.crypto.com/papers/"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(a_page.getContents());
		
		PageParser.parse(a_page);
		
		List<String> urls = a_page.getLinks();
		
		for(String u:urls){
			System.out.println("link found:" + u);
		}
	}

}
