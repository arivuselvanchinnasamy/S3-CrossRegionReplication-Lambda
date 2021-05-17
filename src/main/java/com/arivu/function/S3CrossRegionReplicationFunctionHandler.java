package com.arivu.function;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

import com.arivu.function.dto.S3CrossRegionReplicationRequestDTO;
import com.arivu.function.dto.S3CrossRegionReplicationResponseDTO;

public class S3CrossRegionReplicationFunctionHandler extends 
		SpringBootRequestHandler<S3CrossRegionReplicationRequestDTO, S3CrossRegionReplicationResponseDTO> {

}
