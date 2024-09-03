import java.util.List;

import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlElement;
import org.htmlunit.html.HtmlPage;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.htmlunit.html.HtmlAnchor;

public class HackerNewsScraper2 {

	public static void main(String[] args) {		
//		String baseUrl = "https://news.ycombinator.com/";
		
		/* Set up the http client to make the request */
		WebClient webClient = new WebClient();
		
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false); // why do we need to disable css selectors
		
		try {
			HtmlPage page = webClient.getPage("https://news.ycombinator.com/");
			
		/* Create a List to hold the items from HackerRank */
			List<HtmlElement> hrList = page.getByXPath("//tr[@class='athing']");
			if(hrList.isEmpty()) System.out.println("List is empty, nothing from the webpage");
			else { 
				for(HtmlElement item : hrList) {
					String title = ((HtmlElement) item
							.getFirstByXPath("./td[not(@valign='top')][@class='title']"))
							.asNormalizedText();
					
					String url = ((HtmlAnchor) item
							.getFirstByXPath("./td[not(@valign='top')][@class='title']/span[@class='titleline']/a"))
							.getHrefAttribute();
//Figure out why i'm having trouble with the newer HTMLAnchorElement that seems to be an option on the HtmlUnit docs but I can't import it
					String author = ((HtmlElement) item
							.getFirstByXPath("./following-sibling::tr/td[@class='subtext']/span[@class='subline']/a[@class='hnuser']"))
							.asNormalizedText();
					int position = Integer.parseInt(((HtmlElement) item
							.getFirstByXPath("./td[@valign='top']/span[@class='rank']"))
							.asNormalizedText().replace(".", ""));
					int score = Integer.parseInt(((HtmlElement) item
							.getFirstByXPath("./following-sibling::tr/td[@class='subtext']/span[@class='subline']/span[@class='score']"))
							.asNormalizedText()
							.replace(" points", ""));
					int id = Integer.parseInt(item.getAttribute("id"));
					
					HackerNewsItem2 hnItem = new HackerNewsItem2(title, url, author, score, position, id);
					ObjectMapper mapper = new ObjectMapper(); 
						//from the Jackson depency to print this as JSON\
					String jsonString = mapper.writeValueAsString(hnItem);
					System.out.println(jsonString);
				}
			} // else ends
		} catch(Exception e) {
			e.printStackTrace();
		} 
		finally {
			webClient.close();
		}
	}
}
