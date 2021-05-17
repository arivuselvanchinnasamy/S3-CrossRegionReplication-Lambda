package com.arivu.function.dto;

import java.util.Map;

public class S3CrossRegionReplicationResponseDTO {

	Map<String, Boolean> transferStatus;

	/**
	 * @return the transferStatus
	 */
	public Map<String, Boolean> getTransferStatus() {
		return transferStatus;
	}

	/**
	 * @param transferStatus the transferStatus to set
	 */
	public void setTransferStatus(Map<String, Boolean> transferStatus) {
		this.transferStatus = transferStatus;
	}	
}
