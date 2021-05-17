package com.arivu.function.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;

@Configuration
public class ApplicationConfiguration {

	@Value("${cloud.aws.region.static}")
	private String region;

	@Bean
	public AmazonS3 amazonS3SourceClient() {
		return AmazonS3ClientBuilder.standard()
				.withCredentials(new ProfileCredentialsProvider()).withRegion(region)
				.build();
	}
	
	/*@Bean
	public TransferManager transferManager() {
		TransferManager transferManager = TransferManagerBuilder.standard().build();
		return transferManager;
	}*/
}
