package workers;

import java.net.URI;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import page.Page;

public class PageParserThread extends Thread {

	private Queue<Page> pages_to_analyze;
	private Queue<Page> pages_to_parse;
	private Queue<Page> pages_to_get;
	private StopBit stop_bit;
	
	private Set<URI> all_links;
	
	public PageParserThread(Queue<Page> the_pages_to_get, Queue<Page> the_pages_to_parse,
	    Queue<Page> the_pages_to_analyze, StopBit the_stop_bit) {
		super();
		pages_to_analyze = the_pages_to_analyze;
		pages_to_parse = the_pages_to_parse;
		pages_to_get = the_pages_to_get;
		stop_bit = the_stop_bit;
		all_links = new HashSet<URI>();
	}

	@Override
	public void run() {
		while (!stop_bit.isStopped()) {
			try {
				
				while (!pages_to_parse.isEmpty()) {
					Page temp = pages_to_parse.remove().parse();
					pages_to_analyze.add(temp);
					
					for(URI u:temp.getLinks()){
						if(all_links.add(u)){
							pages_to_get.add(new Page(u));
						}
					}
					
				}
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
