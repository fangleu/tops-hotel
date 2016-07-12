package com.car.image.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-servlet.xml"})
public class ImageTest {
	
	@Test
	public void Test() throws IOException {
		
//		File file =  new File("F:/image/cat.jpg");
//		
//		UUID.randomUUID().toString();
//		
//		final FileInputStream fis = new FileInputStream("F:/image/cat.jpg");
//		MultipartFile multipartFile = new MockMultipartFile("photopath","ff3.jpg","image/jpeg",fis);
//		String fileName = multipartFile.getOriginalFilename();
//		String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
//		String newFileName = UUID.randomUUID().toString();
//		multipartFile.transferTo(new File("F:/image/" + newFileName));
//		
//		System.out.println("test" + suffix);
		
		
		
	}

}
