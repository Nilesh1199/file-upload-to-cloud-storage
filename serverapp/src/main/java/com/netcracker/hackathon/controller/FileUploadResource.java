package com.netcracker.hackathon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netcracker.hackathon.domain.FileDetails;
import com.netcracker.hackathon.service.FileUploadService;
import com.netcracker.hackathon.service.dto.FileUploadReqDTO;
import com.netcracker.hackathon.service.dto.ResponseStatusBody;

//import io.github.jhipster.web.util.ResponseUtil;
@RestController
@CrossOrigin(origins ="*", allowedHeaders = "*")
@RequestMapping("/api")
public class FileUploadResource {
	
//	private final Logger log = LoggerFactory.getLogger(FileUploadResource.class);
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@PostMapping("/upload-file")
	public ResponseEntity<ResponseStatusBody> uploadFile(@ModelAttribute @RequestBody FileUploadReqDTO reqDTO){
		boolean status= fileUploadService.uploadFile(reqDTO);
		if(status) {
			ResponseStatusBody responseStatus= new ResponseStatusBody();
			responseStatus.setMessage("File Upload Success");
			responseStatus.setStatus(HttpStatus.OK);
			responseStatus.setCode(1009);
			return ResponseEntity.ok().body(responseStatus);
		}else {
			ResponseStatusBody responseStatus= new ResponseStatusBody();
			responseStatus.setMessage("File Upload failed");
			responseStatus.setStatus(HttpStatus.BAD_REQUEST);
			responseStatus.setCode(1009);
			return ResponseEntity.badRequest().body(responseStatus);
		}
	}
	
	@GetMapping("/get-files")
	public List<FileDetails> getUploadedFiles(){
		return fileUploadService.getFiles();
	}
}
