package gui;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayDeque;
import java.util.Queue;

import page.Page;
import workers.PageAnalyzerThread;
import workers.PageParserThread;
import workers.PageRetrieverThread;
import workers.StopBit;

public class CrawlerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Page a_page = null;

		try {
			a_page = new Page(new URI("http://jpgunter.com/crawler/"));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StopBit stop_bit = new StopBit();

		Queue<Page> pages_to_retrieve = new ArrayDeque<Page>();
		Queue<Page> pages_to_parse = new ArrayDeque<Page>();
		Queue<Page> pages_to_analyze = new ArrayDeque<Page>();
		Queue<Page> completed = new ArrayDeque<Page>();

		pages_to_retrieve.add(a_page);

		Thread pa = new Thread(new PageAnalyzerThread(pages_to_analyze, completed,
		    stop_bit));
		Thread pp = new Thread(new PageParserThread(pages_to_retrieve, pages_to_parse,
		    pages_to_analyze, stop_bit));
		Thread pr = new Thread(new PageRetrieverThread(pages_to_retrieve,
		    pages_to_parse, stop_bit));

		pa.start();
		pp.start();
		pr.start();

		while (true) {
			try {
				while(!completed.isEmpty()){
					Page comp_page = completed.remove();

					System.out.println("Completed: " + comp_page.getPath().toString());
					for (URI u : comp_page.getLinks()) {
						System.out.println("Link: " + u);
					}
					for (String w : comp_page.getWords()) {
						System.out.println("Word: " + w);
					}
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		

		// CrawlerGUI a_CrawlerGui = new CrawlerGUI();
		// a_CrawlerGui.start();
	}

}
