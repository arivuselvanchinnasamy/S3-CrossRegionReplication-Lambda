package com.arivu.function.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.Copy;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.arivu.function.dto.S3CrossRegionReplicationRequestDTO;
import com.arivu.function.service.IStorageService;

@Service
public class S3StorageServiceImpl implements IStorageService {

	private static final Logger logger = LoggerFactory.getLogger(S3StorageServiceImpl.class);
	
	private AmazonS3 amazonS3Client(String region) {
		return AmazonS3ClientBuilder.standard()
				.withCredentials(new ProfileCredentialsProvider()).withRegion(region)
				.build();
	}
	/*@Override
	public List<S3ObjectSummary> getAllFiles(String sourceBucketName) {
		ObjectListing objectListing = amazonS3Client.listObjects(new ListObjectsRequest().withBucketName(sourceBucketName));
		List<S3ObjectSummary> s3ObjectSummaries = objectListing.getObjectSummaries();
		return s3ObjectSummaries;
	}*/
	
	@Override
	public Map<String, Boolean> performS3CrossRegionReplication(S3CrossRegionReplicationRequestDTO s3CrossRegionReplicationRequestDTO) {
		AmazonS3 amazonS3SourceClient = amazonS3Client(s3CrossRegionReplicationRequestDTO.getSourceBucketRegion());		
		Map<String, Boolean> transferStatus = new LinkedHashMap<>();
		ObjectListing objectListing = amazonS3SourceClient.listObjects(new ListObjectsRequest().withBucketName(s3CrossRegionReplicationRequestDTO.getSourceBucketName()));
		objectListing.getObjectSummaries().parallelStream().map(S3ObjectSummary::getKey)
					.forEach(key-> {
						boolean transferIsSuccessfull = performTransfer(s3CrossRegionReplicationRequestDTO, key, amazonS3SourceClient);
						transferStatus.put(key, Boolean.valueOf(transferIsSuccessfull));
					});
		return transferStatus;
	}
	
	private boolean performTransfer(S3CrossRegionReplicationRequestDTO s3CrossRegionReplicationRequestDTO, String objectKey, AmazonS3 amazonS3SourceClient) {
		AmazonS3 amazonS3TargetClient = amazonS3Client(s3CrossRegionReplicationRequestDTO.getTargetBucketRegion());
		String sourceBucketName = s3CrossRegionReplicationRequestDTO.getSourceBucketName();
		String targetBucketName = s3CrossRegionReplicationRequestDTO.getTargetBucketName();		
		logger.info("sourceBucketName {}, targetBucketName {}, objectKey {}", sourceBucketName, targetBucketName, objectKey);
		boolean transferStatus = false;
		TransferManager transferManager = TransferManagerBuilder.standard().withS3Client(amazonS3TargetClient).build();
		try {
			CopyObjectRequest copyObjectRequest =  new CopyObjectRequest(sourceBucketName, objectKey, targetBucketName, objectKey);
		    Copy copy =  transferManager.copy(copyObjectRequest, amazonS3SourceClient, null);
		    copy.waitForCopyResult();
	        transferManager.shutdownNow();
	        amazonS3SourceClient.shutdown();
	        amazonS3TargetClient.shutdown();
		    transferStatus = true;
		} catch (AmazonClientException | InterruptedException e) {
			logger.error(e.getMessage());
			transferStatus = false;
		}
		transferManager.shutdownNow();
		return transferStatus;
	}
}
