package parser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import page.Page;

public class PageParser {
	public static Page parse(Page the_page) {

		// TODO: resolve relative links

		Pattern html_link = Pattern
				.compile("href=\"([a-zA-Z0-9\\-./\\?\\=\\:]+\\.html)\"");
		Matcher html_match = html_link.matcher(the_page.getContents());

		while (html_match.find()) {
			the_page.addLink(the_page.getPath().resolve(html_match.group(1)));
			// System.out.println("found a match: "+ html_match.group(1));
		}

		Pattern txt_link = Pattern
				.compile("href=\"([a-zA-Z0-9\\-./\\?\\=\\:]+\\.txt)\"");
		Matcher txt_match = txt_link.matcher(the_page.getContents());

		while (txt_match.find()) {
			the_page.addLink(the_page.getPath().resolve(txt_match.group(1)));
			// System.out.println("found a match: "+ txt_match.group(1));
		}
		
		String body = "";
		
		Pattern body_pat = Pattern.compile("<body.*>(.+)</body");
		Matcher body_mat = body_pat.matcher(the_page.getContents());

		if(body_mat.find()){
			body = body_mat.group(1);
		}
		

		Pattern word_pat = Pattern.compile("[^A-Za-z']([A-Za-z']+)[^A-Za-z']");
		Matcher word_mat = word_pat.matcher(body);
		
		while(word_mat.find()){
			the_page.addWord(word_mat.group(1));
		}
		
		return the_page;
	}
}
