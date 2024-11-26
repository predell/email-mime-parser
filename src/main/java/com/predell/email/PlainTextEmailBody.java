package com.predell.email;

import org.apache.james.mime4j.stream.BodyDescriptor;

import java.io.InputStream;

/**
 *
 */
public class PlainTextEmailBody extends Attachment {

	/**
	 *
	 * @param bd Body descriptor
	 * @param is Input stream
	 */
	public PlainTextEmailBody(BodyDescriptor bd, InputStream is) {
		super(bd, is);
	}

	@Override
	public String getAttachmentName() {
		return "emailBody.txt";
	}
}
