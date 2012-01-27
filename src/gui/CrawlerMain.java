package gui;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import page.Page;
import statistics.Statistics;
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
		
		Statistics stats = new Statistics();
		
		BlockingQueue<Page> pages_to_retrieve = new LinkedBlockingQueue<Page>();
		BlockingQueue<Page> pages_to_parse = new LinkedBlockingQueue<Page>();
		BlockingQueue<Page> pages_to_analyze = new LinkedBlockingQueue<Page>();
		BlockingQueue<Page> completed = new LinkedBlockingQueue<Page>();
		
		pages_to_retrieve.add(a_page);

		Thread pa = new Thread(new PageAnalyzerThread(pages_to_analyze, completed,
		    stop_bit, stats));
		Thread pp = new Thread(new PageParserThread(pages_to_retrieve, pages_to_parse,
		    pages_to_analyze, stop_bit, stats));
		Thread pr = new Thread(new PageRetrieverThread(pages_to_retrieve,
		    pages_to_parse, stop_bit, stats));

		pa.start();
		pp.start();
		pr.start();

		while (true) {
			try {
				while(stats.getPagesRetrieved() > 0){
					printStats(stats);
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
	
	public synchronized static void printStats(Statistics the_stats){
		try {
	    Runtime.getRuntime().exec("clear");
    } catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
    }
		System.out.format("Pages Retrieved:\t%d\n" +
				              "Pages Parsed:   \t%d\n" +
				              "Pages Analyzed: \t%d\n", the_stats.getPagesRetrieved(), the_stats.getPagesParsed(), the_stats.getPagesAnalyzed());
	}

}
