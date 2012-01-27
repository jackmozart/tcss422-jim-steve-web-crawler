package statistics;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Statistics {
	private int pages_retrieved = 0;
	private int pages_parsed = 0;
	private int pages_analyzed = 0;
	private int total_links = 0;
	private int links_followed = 0;
	
	private long retrieval_time = 0;
	private long parse_time = 0;
	private long analyze_time = 0;
	
	Map<String, Integer> keyword_count = new HashMap<String, Integer>();

	public int getPagesRetrieved() {
  	return pages_retrieved;
  }

	public int getPagesParsed() {
  	return pages_parsed;
  }

	public int getPagesAnalyzed() {
  	return pages_analyzed;
  }

	public int getTotalLinks() {
  	return total_links;
  }

	public int getLinksFollowed() {
  	return links_followed;
  }

	public double getRetrievalTime() {
  	return retrieval_time;
  }

	public double getParseTime() {
  	return parse_time;
  }
	
	public double getAnalyzeTime(){
		return analyze_time;
	}
	
	public Map<String, Integer> getKeywordCount() {
  	return keyword_count;
  }
	
	public Set<String> getKeywords(){
		return keyword_count.keySet();
	}
	
	public void addPagesRetrieved(int the_pages_retrieved) {
  	pages_retrieved += the_pages_retrieved;
  }

	public void addPagesParsed(int the_pages_parsed) {
  	pages_parsed += the_pages_parsed;
  }

	public void addPagesAnalyzed(int the_pages_analyzed) {
  	pages_analyzed += the_pages_analyzed;
  }

	public void addTotalLinks(int the_total_links) {
  	total_links += the_total_links;
  }

	public void addLinksFollowed(int the_links_followed) {
  	links_followed += the_links_followed;
  }

	public void addRetrievalTime(long the_retrieval_time) {
  	retrieval_time += the_retrieval_time;
  }

	public void addParseTime(long the_parse_time) {
  	parse_time += the_parse_time;
  }
	
	public void addAnalyzeTime(long the_analyze_time){
		analyze_time += the_analyze_time;
	}
	
	public void addKeyword(String the_keyword){
		keyword_count.put(the_keyword, 0);
	}
	
	public void addKeywordCount(String the_keyword, int the_count) {
  	keyword_count.put(the_keyword, keyword_count.get(the_keyword) + the_count);
  }
	
	public double getHitsPerPage(String the_keyword){
		double hpp = 0.0;
		if(pages_analyzed != 0){
			hpp = keyword_count.get(the_keyword) / pages_analyzed;
		}
		return hpp;
	}
	
	public double getAverageRetrievalTime(){
		double art = 0.0;
		if(pages_retrieved != 0){
			art = (double)retrieval_time / (double)pages_retrieved;
		}
		return art;
	}
	
	public double getAverageParseTime(){
		double art = 0.0;
		if(pages_parsed != 0){
			return (double)parse_time / (double)pages_parsed;
		}
		return art;
	}
	
	public double getAverageAnalyzeTime(){
		double art = 0.0;
		if(pages_analyzed != 0){
			return (double)analyze_time / (double)pages_analyzed;
		}
		return art;
	}
	
	
}
