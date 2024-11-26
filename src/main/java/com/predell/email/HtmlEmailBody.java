package com.predell.email;

import org.apache.james.mime4j.stream.BodyDescriptor;

import java.io.InputStream;

/**
 *
 */
public class HtmlEmailBody extends Attachment {

	/**
	 *
	 * @param bd Body descriptor
	 * @param is Input stream
	 */
	public HtmlEmailBody(BodyDescriptor bd, InputStream is) {
		super(bd, is);
	}

	@Override
	public String getAttachmentName() {
		return "emailBody.html";
	}

}
