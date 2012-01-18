package gui;

import java.net.MalformedURLException;
import java.net.URL;

import page.Page;
import retriever.PageRetriever;

public class CrawlerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Page a_page = null;
		try {
			a_page = PageRetriever.retrieve(new URL("http://jpgunter.com/"));
			
			
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(a_page.getContents());
	}

}
