package com.arivu.function.dto;

public class S3CrossRegionReplicationRequestDTO {

	private String sourceBucketName;
	
	private String sourceBucketRegion;
	
	private String targetBucketName;
	
	private String targetBucketRegion;

	/**
	 * @return the sourceBucketName
	 */
	public String getSourceBucketName() {
		return sourceBucketName;
	}

	/**
	 * @param sourceBucketName the sourceBucketName to set
	 */
	public void setSourceBucketName(String sourceBucketName) {
		this.sourceBucketName = sourceBucketName;
	}

	/**
	 * @return the sourceBucketRegion
	 */
	public String getSourceBucketRegion() {
		return sourceBucketRegion;
	}

	/**
	 * @param sourceBucketRegion the sourceBucketRegion to set
	 */
	public void setSourceBucketRegion(String sourceBucketRegion) {
		this.sourceBucketRegion = sourceBucketRegion;
	}

	/**
	 * @return the targetBucketName
	 */
	public String getTargetBucketName() {
		return targetBucketName;
	}

	/**
	 * @param targetBucketName the targetBucketName to set
	 */
	public void setTargetBucketName(String targetBucketName) {
		this.targetBucketName = targetBucketName;
	}

	/**
	 * @return the targetBucketRegion
	 */
	public String getTargetBucketRegion() {
		return targetBucketRegion;
	}

	/**
	 * @param targetBucketRegion the targetBucketRegion to set
	 */
	public void setTargetBucketRegion(String targetBucketRegion) {
		this.targetBucketRegion = targetBucketRegion;
	}	
	
}
