package org.podpage.jadfri.request;

public class Response {

	private long time;
	private int code;
	private String data;

	public Response(String data, int code, long time) {
		this.time = time;
		this.code = code;
		this.data = data;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
