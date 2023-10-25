package com.example.videostreamerapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.videostreamerapi.services.VideoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/videos")
public class VideoController {

	@Autowired
	private VideoService videoService;

	@PostMapping
	public ResponseEntity<String> save(@RequestParam("file") MultipartFile file, @RequestParam("name") String name)
			throws Exception {
		videoService.saveVideo(file, name);
		return ResponseEntity.ok("Video saved successfully.");
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<Resource> getVideoByName(@PathVariable("name") String name){
		return ResponseEntity
	               .status(HttpStatus.OK)
	               .contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(new ByteArrayResource(videoService.getVideo(name).getData()));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<String>> getAllVideoNames(){
		return ResponseEntity.ok(videoService.getAllVideoNames()); 
	}
}
