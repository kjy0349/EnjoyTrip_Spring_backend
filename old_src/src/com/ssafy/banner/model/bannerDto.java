package com.ssafy.banner.model;

public class bannerDto {
	private String overview;
	private String title;
	private String addr;
	private int readCount;
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	/**
	 * @param overview
	 * @param title
	 * @param addr
	 */
	public bannerDto(String overview, String title, String addr, int readCount) {
		super();
		this.overview = overview;
		this.title = title;
		this.addr = addr;
		this.readCount = readCount;
	}
	@Override
	public String toString() {
		return "bannerDto [overview=" + overview + ", title=" + title + ", addr=" + addr + "]";
	}
	
	
	
	
}
