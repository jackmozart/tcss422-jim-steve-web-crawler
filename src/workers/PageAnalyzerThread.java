package workers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import page.Page;
import statistics.Statistics;

public class PageAnalyzerThread extends Thread{

	private BlockingQueue<Page> pages_to_analyze;
	private BlockingQueue<Page> completed_pages;
	private StopBit stop_bit;
	private Statistics stats;
	
	public PageAnalyzerThread(BlockingQueue<Page> the_pages_to_analyze, BlockingQueue<Page> the_completed_pages, StopBit the_stop_bit, Statistics the_stats){
		super();
		pages_to_analyze = the_pages_to_analyze;
		completed_pages = the_completed_pages;
		stop_bit = the_stop_bit;
		stats = the_stats;
	}
	
	@Override
	public void run() {
		while(!stop_bit.isStopped()){
			try{	
				Page temp = pages_to_analyze.poll(1, TimeUnit.SECONDS);
				if(temp != null){
					long start_time = System.nanoTime();
					temp.analyze();
					long total_time = System.nanoTime() - start_time;
					synchronized(this){
						stats.addAnalyzeTime(total_time);
						stats.addPagesAnalyzed(1);
					}
					completed_pages.put(temp);
				}
			} catch (InterruptedException e) {
				//If I was interrupted, do nothing, just return.
			}
		}
	}
	
}
