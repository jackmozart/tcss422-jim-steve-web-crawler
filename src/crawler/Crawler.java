package crawler;

import java.net.URL;

public class Crawler {
	private URL my_seed_url;
	private int my_max_pages;
	
	public Crawler(URL the_seed_url, int the_max_pages){
		my_seed_url = the_seed_url;
		my_max_pages = the_max_pages;
	}
}
