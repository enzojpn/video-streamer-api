package com.example.videostreamerapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.videostreamerapi.model.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {
	Video findByName(String name);

	boolean existsByName(String name);

	@Query(nativeQuery = true, value = "Select name from video")
	List<String> getAllEntryNames();

}
