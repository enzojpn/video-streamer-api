package com.example.videostreamerapi.exceptions;

public class VideoNotFoundException extends RuntimeException{
	
 
	private static final long serialVersionUID = 1L;

	public VideoNotFoundException(String errorMessage, Throwable err)
	{
		super(errorMessage, err);
	}

}
