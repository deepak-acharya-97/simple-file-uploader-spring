package com.javapoint1.controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
	
	@PostMapping("/upload")
	public String upload(@RequestParam("image_uploads") MultipartFile file) throws IllegalStateException, IOException {
		Path path = FileSystems.getDefault().getPath(".");
		String destPathUrl = "E:\\Spring Boot\\simple-file-uploader\\UploadedFiles\\"+file.getOriginalFilename();
		file.transferTo(new File(destPathUrl));
		return null;
	}
}
