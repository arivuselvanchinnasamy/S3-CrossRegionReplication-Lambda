package com.arivu.function;

import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arivu.function.dto.S3CrossRegionReplicationRequestDTO;
import com.arivu.function.dto.S3CrossRegionReplicationResponseDTO;
import com.arivu.function.service.IStorageService;

@Component
public class S3CrossRegionReplicationFunction implements 
		Function<S3CrossRegionReplicationRequestDTO, S3CrossRegionReplicationResponseDTO>{

	@Autowired
	private IStorageService storageService;
	
	@Override
	public S3CrossRegionReplicationResponseDTO apply(S3CrossRegionReplicationRequestDTO s3CrossRegionReplicationRequestDTO) {
		
		S3CrossRegionReplicationResponseDTO s3CrossRegionReplicationResponseDTO = new S3CrossRegionReplicationResponseDTO();
		Map<String, Boolean> replicationStatus = storageService.performS3CrossRegionReplication(s3CrossRegionReplicationRequestDTO);
		s3CrossRegionReplicationResponseDTO.setTransferStatus(replicationStatus);
		 return s3CrossRegionReplicationResponseDTO;	
	}

}
