package page;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

public class Page {
	private URI my_path;
	private String my_html;
	
	private List<URI> my_links;
	private List<String> my_words;
	
	public Page(URI the_path){
		my_path = the_path;
		my_html = "";
		my_links = new ArrayList<URI>();
		my_words = new ArrayList<String>();
	}
	
	public void setContents(String the_contents){
		my_html = the_contents;
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
	
	public String getHTML(){
		return my_html;
	}
	
	public List<URI> getLinks(){
		return my_links;
	}
	
	public List<String> getWords(){
		return my_words;
	}

	public Page retrieve(){
		
		StringBuilder html = new StringBuilder();
		
		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(my_path.toURL().openStream()));
			while(in.ready()){
				html.append(in.readLine().trim());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		my_html = html.toString();
		
		
		return this;
	}
	public Page parse() {

		// TODO: resolve relative links

		Source page_source = new Source(my_html);

		page_source.fullSequentialParse();

		List<Element> linkElements = page_source
				.getAllElements(HTMLElementName.A);
		for (Element linkElement : linkElements) {
			String href = linkElement.getAttributeValue("href");
			if (href == null)
				continue;

			if (href.endsWith(".html") || href.endsWith(".txt")) {
				addLink(my_path.resolve(href));
			}
		}

		String page_text = page_source.getTextExtractor().toString();

		Pattern word_pat = Pattern.compile("\\b(\\w+)\\b");
		Matcher word_mat = word_pat.matcher(page_text);

		while (word_mat.find()) {
			addWord(word_mat.group(1));
		}

		return this;
	}
	
	public Page analyze(){
		//TODO analyze the text.
		
		return this;
	}
}
