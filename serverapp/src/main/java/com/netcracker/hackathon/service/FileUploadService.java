package com.netcracker.hackathon.service;

import java.util.List;

import com.netcracker.hackathon.domain.FileDetails;
import com.netcracker.hackathon.service.dto.FileUploadReqDTO;

public interface FileUploadService {

	boolean uploadFile(FileUploadReqDTO reqDTO);

	List<FileDetails> getFiles();

}
