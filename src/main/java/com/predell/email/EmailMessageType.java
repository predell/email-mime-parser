package com.predell.email;

public class EmailMessageType {

	private EmailMessageTypeHierarchy hierarchy;

	public EmailMessageTypeHierarchy getEmailMessageTypeHierarchy() {
		return hierarchy;
	}

	public EmailMessageType(EmailMessageTypeHierarchy hierarchy) {
		this.hierarchy = hierarchy;
	}
	
	public enum EmailMessageTypeHierarchy {
		parent, child
	}
}


