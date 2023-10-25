package com.example.videostreamerapi.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Array;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.videostreamerapi.exceptions.VideoNotFoundException;
import com.example.videostreamerapi.model.Video;
import com.example.videostreamerapi.repository.VideoRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class VideoServiceImplTest {

	@Autowired
	VideoService service;

	@MockBean
	VideoRepository videoRepository;

	String testName = "myVid";

	@Test
	void getVideo() throws Exception {
		Video expected = new Video(testName, null);
		when(videoRepository.findByName(testName)).thenReturn(expected);
		when(videoRepository.existsByName(testName)).thenReturn(true);
		// when(service.getVideo(testName)).thenReturn(expected);

		Video actual = service.getVideo(testName);

		assertEquals(expected, actual);

	}

	@Test
	void getVideoNotFound() throws Exception {
		String testName = "myNonExistentVideo";

		when(videoRepository.existsByName(testName)).thenReturn(false);

		assertThrows(VideoNotFoundException.class, () -> {
			service.getVideo(testName);
		});
	}

	@Test
	void getAllVideoNames() {
		String nome1 = "video1";
		String nome2 = "video2";
		List<String> videos = new ArrayList<String>();
		videos.add(nome1);
		videos.add(nome2);
		when(videoRepository.getAllEntryNames()).thenReturn(videos);
		
		List<String> listaVideos = service.getAllVideoNames();
		assertEquals(2, videos.size());
		assertTrue(listaVideos.contains(nome1));
		assertTrue(listaVideos.contains(nome2));

	}

	@Test
	void saveVideo() {
	}

}
