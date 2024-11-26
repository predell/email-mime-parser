package com.predell.email;

import org.apache.james.mime4j.stream.BodyDescriptor;

/**
 *
 */
public class MultipartType {
	
	private BodyDescriptor bodyDiscriptor;

	/**
	 *
	 * @param bodyDescriptor Body descriptor
	 */
	public MultipartType(BodyDescriptor bodyDescriptor){
		this.bodyDiscriptor = bodyDescriptor; 
	}

	/**
	 *
	 * @return Body descriptor
	 */
	public BodyDescriptor getBodyDescriptor() {
		return bodyDiscriptor;
	}	
}
