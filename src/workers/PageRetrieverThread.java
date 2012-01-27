package workers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import page.Page;
import statistics.Statistics;

public class PageRetrieverThread extends Thread {

	private BlockingQueue<Page> pages_to_get;
	private BlockingQueue<Page> pages_to_parse;
	private StopBit stop_bit;
	private Statistics stats;
	
	public PageRetrieverThread(BlockingQueue<Page> the_pages_to_get,
			BlockingQueue<Page> the_pages_to_parse, StopBit the_stop_bit, Statistics the_stats) {
		super();
		pages_to_get = the_pages_to_get;
		pages_to_parse = the_pages_to_parse;
		stop_bit = the_stop_bit;
		stats = the_stats;
	}

	@Override
	public void run() {
		while (!stop_bit.isStopped()) {
			try {
				Page temp = pages_to_get.poll(1, TimeUnit.SECONDS);
				if(temp != null){
					long start_time = System.nanoTime();
					temp.retrieve();
					long total_time = System.nanoTime() - start_time;
					synchronized(this){
						stats.addRetrievalTime(total_time);
						stats.addPagesRetrieved(1);
					}
					pages_to_parse.put(temp);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
