package com.cts.springboot.com.cts.mockupProject;

public class UpdateMessage {
	private String responseMessage;
	


	public UpdateMessage(String responseMessage) {
				this.responseMessage = responseMessage;
	}

	public UpdateMessage()
	{
		
	}
	
	

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	

	
}
