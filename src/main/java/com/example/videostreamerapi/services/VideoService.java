package com.example.videostreamerapi.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.videostreamerapi.model.Video;

public interface VideoService {

	
	Video getVideo(String name);
	
	void saveVideo(MultipartFile file,String name) 	throws Exception;
	
	List<String> getAllVideoNames();
}
