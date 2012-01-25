package workers;

import java.util.Queue;

import page.Page;


public class PageRetrieverThread extends Thread {
	
	private Queue<Page> pages_to_get;
	private Queue<Page> pages_to_parse;
	private Boolean stop_bit;
	
	public PageRetrieverThread(Queue<Page> the_pages_to_get, Queue<Page> the_pages_to_parse, Boolean the_stop_bit){
		super();
		pages_to_get = the_pages_to_get;
		pages_to_parse = the_pages_to_parse;
	}
	
	@Override
	public void run() {
		while(stop_bit){
			try {
				pushToParseQueue(getNextPage().retrieve());
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private synchronized Page getNextPage(){
		Page next = null;
		if(!pages_to_get.isEmpty()){
			next = pages_to_get.remove();
		}
		return next;
	}
	
	private synchronized void pushToParseQueue(Page the_page){
		pages_to_parse.add(the_page);
	}

}
