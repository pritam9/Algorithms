package app.one.model;

public class TokenDetails {
	String expires_on, userid;

	/**
	 * @return the expires_on
	 */
	public String getExpires_on() {
		return expires_on;
	}

	/**
	 * @param expires_on the expires_on to set
	 */
	public void setExpires_on(String expires_on) {
		this.expires_on = expires_on;
	}

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TokenDetails [expires_on=" + expires_on + ", userid=" + userid + "]";
	}
	
	
	

}
