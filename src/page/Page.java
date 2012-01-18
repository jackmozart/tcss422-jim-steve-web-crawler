package page;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Page {
	private URL my_path;
	private String my_contents;
	
	private List<String> my_links;
	private List<String> my_words;
	
	public Page(URL the_path, String the_contents){
		my_path = the_path;
		my_contents = the_contents;
		my_links = new ArrayList<String>();
		my_words = new ArrayList<String>();
	}
	
	public void addLink(String the_link){
		my_links.add(the_link);
	}
	
	public void addWord(String the_word){
		my_words.add(the_word);
	}
	
	public URL getPath(){
		return my_path;
	}
	
	public String getContents(){
		return my_contents;
	}
	
	public List<String> getLinks(){
		return my_links;
	}
	
	public List<String> getWords(){
		return my_words;
	}

	
}
