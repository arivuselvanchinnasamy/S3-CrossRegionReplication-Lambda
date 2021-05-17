package com.arivu.function.service;

import java.util.Map;

import com.arivu.function.dto.S3CrossRegionReplicationRequestDTO;

public interface IStorageService {

	//List<S3ObjectSummary> getAllFiles(String sourceBucketName) throws IOException;

	Map<String, Boolean> performS3CrossRegionReplication(
			S3CrossRegionReplicationRequestDTO s3CrossRegionReplicationRequestDTO);
}
