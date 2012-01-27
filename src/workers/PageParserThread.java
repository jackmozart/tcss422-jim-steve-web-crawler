package workers;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import page.Page;
import statistics.Statistics;

public class PageParserThread extends Thread {

	private BlockingQueue<Page> pages_to_analyze;
	private BlockingQueue<Page> pages_to_parse;
	private BlockingQueue<Page> pages_to_get;
	private StopBit stop_bit;
	private Statistics stats;
	
	private Set<URI> all_links;
	
	public PageParserThread(BlockingQueue<Page> the_pages_to_get, BlockingQueue<Page> the_pages_to_parse,
			BlockingQueue<Page> the_pages_to_analyze, StopBit the_stop_bit, Statistics the_stats) {
		super();
		pages_to_analyze = the_pages_to_analyze;
		pages_to_parse = the_pages_to_parse;
		pages_to_get = the_pages_to_get;
		stop_bit = the_stop_bit;
		stats = the_stats;
		all_links = new HashSet<URI>();
	}

	@Override
	public void run() {
		while (!stop_bit.isStopped()) {
			try {
				Page temp = pages_to_parse.poll(1, TimeUnit.SECONDS);
				if(temp != null){
					long start_time = System.nanoTime();
					temp.parse();
					long total_time = System.nanoTime() - start_time;
					
					int link_add_count = 0;
					
					for(URI u:temp.getLinks()){
						if(all_links.add(u)){
							pages_to_get.add(new Page(u));
							link_add_count++;
						}
					}
					synchronized(this){
						stats.addParseTime(total_time);
						stats.addPagesParsed(1);
						stats.addLinksFollowed(link_add_count);
						stats.addTotalLinks(temp.getLinks().size());
						
						for(String kw : stats.getKeywords()){
							if(temp.getWords().containsKey(kw)){
								stats.addKeywordCount(kw, temp.getWords().get(kw));
							}
						}
						
					}
					
					pages_to_analyze.put(temp);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
