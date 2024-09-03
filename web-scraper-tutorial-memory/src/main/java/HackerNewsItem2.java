// 
public class HackerNewsItem2 {
	
	String title, url, author;
	int score, position, id;
	
	public HackerNewsItem2(String title, String url, String author, int score, int position, int id) {
		super();
		this.title = title;
		this.url = url;
		this.author = author;
		this.score = score;
		this.position = position;
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getPosition() {
		return position;
	}
	
	public int getId() {
		return id;
	}

}
