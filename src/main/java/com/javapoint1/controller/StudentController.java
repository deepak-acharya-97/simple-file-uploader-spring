package com.javapoint1.controller;


import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;

import javax.imageio.ImageIO;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class StudentController {
	
	/*@RequestMapping("/name")
	public String getName() {
		return "Deepak";
	}*/
	
	@RequestMapping("/fileupload")
	public String getTemplate() {
		return "rakesh";
	}
	
	@PostMapping(
			value="/upload",
			produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
		)
	public ResponseEntity<String>  upload(@RequestParam("image_uploads") MultipartFile file, @RequestParam("fileExtension") String fileExtension) throws IllegalStateException, IOException {
		//Path path = FileSystems.getDefault().getPath(".");
		String destPathUrl = "E:\\Spring Boot\\simple-file-uploader\\UploadedFiles\\"+file.getOriginalFilename();
		file.transferTo(new File(destPathUrl));
		String resultFileName = file.getOriginalFilename().split("\\.")[0]+"."+fileExtension;
		
		String resultFile = "E:\\Spring Boot\\simple-file-uploader\\UploadedFiles\\"+resultFileName;
		FileInputStream inputStream = new FileInputStream(destPathUrl);
		FileOutputStream outputStream = new FileOutputStream(resultFile);
		// reads input image from file
        BufferedImage inputImage = ImageIO.read(inputStream);
         
        // writes to the output image in specified format
        boolean result = ImageIO.write(inputImage, fileExtension, outputStream);
         
        // needs to close the streams
        outputStream.close();
        inputStream.close();
		Path path = Paths.get(resultFile);
		//Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/download/")
				.path(resultFile)
				.toUriString();
		return ResponseEntity.ok(fileDownloadUri);
	}
}
