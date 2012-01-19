package page;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Page {
	private URI my_path;
	private String my_contents;
	
	private List<URI> my_links;
	private List<String> my_words;
	
	public Page(URI the_path, String the_contents){
		my_path = the_path;
		my_contents = the_contents;
		my_links = new ArrayList<URI>();
		my_words = new ArrayList<String>();
	}
	
	public void addLink(URI the_link){
		my_links.add(the_link);
	}
	
	public void addWord(String the_word){
		my_words.add(the_word);
	}
	
	public URI getPath(){
		return my_path;
	}
	
	public String getContents(){
		return my_contents;
	}
	
	public List<URI> getLinks(){
		return my_links;
	}
	
	public List<String> getWords(){
		return my_words;
	}

	
}
