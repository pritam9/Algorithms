package com.twitter.aggregator;

import java.util.Date;

public class Engagement implements Comparable<Engagement> {
	
	private Date date;
	private int engagement_count;
	private String engagement_name;
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the engagement_count
	 */
	public int getEngagement_count() {
		return engagement_count;
	}
	/**
	 * @param engagement_count the engagement_count to set
	 */
	public void setEngagement_count(int engagement_count) {
		this.engagement_count = engagement_count;
	}
	/**
	 * @return the engagement_name
	 */
	public String getEngagement_name() {
		return engagement_name;
	}
	/**
	 * @param engagement_name the engagement_name to set
	 */
	public void setEngagement_name(String engagement_name) {
		this.engagement_name = engagement_name;
	}
	
	@Override
	public int compareTo(Engagement o) {
		// TODO Auto-generated method stub
		return getDate().compareTo(o.getDate());
	}

	
}
