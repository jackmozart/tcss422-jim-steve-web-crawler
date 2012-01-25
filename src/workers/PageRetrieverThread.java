package workers;

import java.net.URI;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import page.Page;

public class PageRetrieverThread extends Thread {

	private Queue<Page> pages_to_get;
	private Queue<Page> pages_to_parse;
	private StopBit stop_bit;

	public PageRetrieverThread(Queue<Page> the_pages_to_get,
	    Queue<Page> the_pages_to_parse, StopBit the_stop_bit) {
		super();
		pages_to_get = the_pages_to_get;
		pages_to_parse = the_pages_to_parse;
		stop_bit = the_stop_bit;
	}

	@Override
	public void run() {
		while (!stop_bit.isStopped()) {
			try {
				while (!pages_to_get.isEmpty()) {
					pages_to_parse.add(pages_to_get.remove().retrieve());
				}
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
