package com.javapoint1.controller;


import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;

import javax.imageio.ImageIO;

import org.springframework.core.io.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class StudentController {
	
	@GetMapping("/get-text")
	public @ResponseBody String getText() {
	    return "Hello world";
	}
	
	@RequestMapping("/fileupload")
	public String getTemplate() {
		return "rakesh";
	}
	
	@PostMapping(
			value="/upload"
		)
	public Resource  upload(@RequestParam("image_uploads") MultipartFile file, @RequestParam("fileExtension") String fileExtension) throws IllegalStateException, IOException {
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
		Path pathToFile = Paths.get(resultFile);
        UrlResource resource = null;
        try {
            resource = new UrlResource(pathToFile.toUri());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resource;
	}
	
	@GetMapping("/download")
	public @ResponseBody ResponseEntity<Resource> download() throws IOException {
		String resultFile = "E:\\Spring Boot\\simple-file-uploader\\UploadedFiles\\download.png";
		Path pathToFile = Paths.get(resultFile);
        /*UrlResource resource = null;
        try {
            resource = new UrlResource(pathToFile.toUri());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resource;*/
        
		File file = new File(resultFile);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=img.jpg");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        //Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(pathToFile));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
	}
}
