package page;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Page {
	private URI my_path;
	private String my_contents;
	
	private List<URI> my_links;
	private Map<String, Integer> my_words;
	
	public Page(URI the_path, String the_contents){
		my_path = the_path;
		my_contents = the_contents;
		my_links = new ArrayList<URI>();
		my_words = new HashMap<String, Integer>();
	}
	
	public void addLink(URI the_link){
		my_links.add(the_link);
	}
	
	public void addWord(String the_word){
		if(my_words.containsKey(the_word.toUpperCase())){
			my_words.put(the_word.toUpperCase(), my_words.get(the_word.toUpperCase()) + 1);
		} else {
			my_words.put(the_word.toUpperCase(), 1);
		}
	}
	
	public URI getPath(){
		return my_path;
	}
	
	public String getHTML(){
		return my_contents;
	}
	
	public List<URI> getLinks(){
		return my_links;
	}
	
	public Map<String,Integer> getWords(){
		return my_words;
	}

	
}
