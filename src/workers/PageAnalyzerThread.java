package workers;

import java.util.Queue;

import page.Page;

public class PageAnalyzerThread extends Thread{

	private Queue<Page> pages_to_analyze;
	private Queue<Page> completed_pages;
	private StopBit stop_bit;
	
	public PageAnalyzerThread(Queue<Page> the_pages_to_analyze, Queue<Page> the_completed_pages, StopBit the_stop_bit){
		super();
		pages_to_analyze = the_pages_to_analyze;
		completed_pages = the_completed_pages;
		stop_bit = the_stop_bit;
	}
	
	@Override
	public void run() {
		while(!stop_bit.isStopped()){
			try{	
				while(!pages_to_analyze.isEmpty()){
					completed_pages.add(pages_to_analyze.remove().analyze());
				}
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
