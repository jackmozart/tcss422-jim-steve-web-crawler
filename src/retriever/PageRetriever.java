package retriever;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.text.html.HTMLDocument;

import page.Page;

public class PageRetriever {
	public static Page retrieve(URI path){
		
		String page_html = "";
		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(path.toURL().openStream()));
			while(in.ready()){
				page_html += in.readLine().trim();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Page(path, page_html);
	}
}
