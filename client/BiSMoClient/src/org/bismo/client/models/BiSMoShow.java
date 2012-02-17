package org.bismo.client.models;

public class BiSMoShow {
	
	private int tvId;
	private int appId;
	private int showId;
	private String showTitle;
	
	public String getShowTitle() {
		return showTitle;
	}

	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}

	private long showDuration;

	public int getTvId() {
		return tvId;
	}

	public void setTvId(int tvId) {
		this.tvId = tvId;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	public long getShowDuration() {
		return showDuration;
	}

	public void setShowDuration(long showDuration) {
		this.showDuration = showDuration;
	}
	
	
	
}
