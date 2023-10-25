package com.example.videostreamerapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.videostreamerapi.exceptions.VideoAlreadyExistsException;
import com.example.videostreamerapi.exceptions.VideoNotFoundException;
import com.example.videostreamerapi.model.Video;
import com.example.videostreamerapi.repository.VideoRepository;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private VideoRepository videoRepository;

	 

	@Override
	public Video getVideo(String name) {
		if (!videoRepository.existsByName(name)) {
			throw new VideoNotFoundException("video no encontrado", null);
		}
		return videoRepository.findByName(name);

	}

	@Override
	public void saveVideo(MultipartFile file, String name) throws Exception {
		if (videoRepository.existsByName(name)) {
			throw new VideoAlreadyExistsException();
		}
		Video newVideo = new Video(name , file.getBytes());
		videoRepository.save(newVideo);
	}

	@Override
	public List<String> getAllVideoNames() {
		return videoRepository.getAllEntryNames();
	}

}
