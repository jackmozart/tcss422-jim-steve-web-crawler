package parser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import page.Page;

public class PageParser {
	public static Page parse(Page the_page) {

		// TODO: resolve relative links
		
		Source page_source = new Source(the_page.getHTML());
		
		page_source.fullSequentialParse();
		
		List<Element> linkElements=page_source.getAllElements(HTMLElementName.A);
		for (Element linkElement : linkElements) {
			String href=linkElement.getAttributeValue("href");
			if (href==null) continue;
			
			if(href.endsWith(".html") || href.endsWith(".txt")){
				the_page.addLink(the_page.getPath().resolve(href));
			}
		}
		
		String page_text = page_source.getTextExtractor().toString();
		

		Pattern word_pat = Pattern.compile("\\b(\\w+)\\b");
		Matcher word_mat = word_pat.matcher(page_text);
		
		while(word_mat.find()){
			the_page.addWord(word_mat.group(1));
		}
		
		return the_page;
	}
}
