package app.one.model;

public class Notification {
	String body, title,icon;
	public static String testMessage ="{\"to\" : \"e-CxrLGpsGs:APA91bGXpz5fOcdEdaauIWnw4BZMjqU9R7iya63R6SFmAMCeRaq0w1ztaRtgaXfyo_xeN4mQNm9zXctvvDrdKC1v8hzYp7iHZcRcUiWYLzJE5w1nSGB_Yw7FqRBNhl_j11OPEhaNTIoN\",\"data\" : {"+
     "\"Nick\" : \"Mario\",\"body\" : \"great match!\","+
     "\"Room\" : \"PortugalVSDenmark\"},}";

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Notification [body=" + body + ", title=" + title + ", icon=" + icon + "]";
	}
	
	

}
