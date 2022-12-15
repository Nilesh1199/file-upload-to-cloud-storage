package com.netcracker.hackathon.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netcracker.hackathon.domain.FileDetails;
import com.netcracker.hackathon.repository.FileDetailsRepository;
import com.netcracker.hackathon.service.FileUploadService;
import com.netcracker.hackathon.service.dto.FileUploadReqDTO;
import com.netcracker.hackathon.util.AwsS3Utility;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	@Autowired
	private AwsS3Utility s3Utility;

	@Autowired
	private FileDetailsRepository fileDetailsRepository;

	@Override
	public boolean uploadFile(FileUploadReqDTO reqDTO) {
		String hash = generateHash();
		String fileName = "";
		if(reqDTO.getFileName().contains(".pdf")) {
			String[] splits = reqDTO.getFileName().split(".pdf");
			fileName = splits[0].concat(hash).concat(".pdf");
		}else {
			fileName = reqDTO.getFileName().concat(hash).concat(".pdf");
		}
		byte[] file = null;
		boolean isUploadSuccessfull = false;
		try {
			if (reqDTO.getFile() != null && reqDTO.getFile().getContentType().equalsIgnoreCase("application/pdf")
					&& reqDTO.getFile().getSize() < 1048576) {
				file = reqDTO.getFile().getBytes();
			}
		} catch (IOException e) {
			e.getMessage();
			return false;
		}
		if (file != null) {
			FileDetails uplodedFileDetails = s3Utility.uploadFile(fileName, file);
			if (uplodedFileDetails != null) {
				fileDetailsRepository.save(uplodedFileDetails);
				isUploadSuccessfull = true;
			}
		}
		return isUploadSuccessfull;
	}

	private String generateHash() {
		int n = 7;
		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}

	@Override
	public List<FileDetails> getFiles() {
		return fileDetailsRepository.findAll();
	}

}
