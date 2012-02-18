package org.bismo.client.models;

public class BiSMoShow {
	
	private int tvId;
	private String appId;
	private int showId;
	private String showTitle;
	private int totalVotes;
	private int showDuration;
	
	public int getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(int totalVotes) {
		this.totalVotes = totalVotes;
	}

	public String getShowTitle() {
		return showTitle;
	}

	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}

	public int getTvId() {
		return tvId;
	}

	public void setTvId(int tvId) {
		this.tvId = tvId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	public int getShowDuration() {
		return showDuration;
	}

	public void setShowDuration(int showDuration) {
		this.showDuration = showDuration;
	}
	
	
	
}
