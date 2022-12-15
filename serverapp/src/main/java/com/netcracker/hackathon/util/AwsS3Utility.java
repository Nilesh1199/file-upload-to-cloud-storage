package com.netcracker.hackathon.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import org.joda.time.Instant;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.netcracker.hackathon.domain.FileDetails;

@Service
public class AwsS3Utility {

	public FileDetails uploadFile(String fileName, byte[] file) {
//		Please update teh Access key and Secret access key.
		final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials("EnterAccessKey",
				"EnterSecretAccessKey");
		AmazonS3 s3 = (AmazonS3) AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1)
				.withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).build();
		FileDetails fileDetails = new FileDetails();
//		Update bucket name with unique name
		String bucketName = "bucket_name";
		if (!s3.doesBucketExistV2(bucketName)) {
			try {
				s3.createBucket(bucketName);
			} catch (AmazonS3Exception e) {
				System.err.println(e.getErrorMessage());
			}
		}

		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType("application/pdf");
		InputStream fileStream = new ByteArrayInputStream(file);
		PutObjectResult objectResult= s3.putObject(new PutObjectRequest(bucketName, fileName, fileStream, metadata)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		if(!objectResult.getETag().isEmpty()) {
			URL url = s3.getUrl(bucketName, fileName);
			System.out.println(url.toString());

			fileDetails.setFileName(fileName);
			fileDetails.setFileUrl(url.toString());
			fileDetails.setUploadedDate(Instant.now().toString());
			return fileDetails;
		}else {
			return null;
		}
		
	}

}
