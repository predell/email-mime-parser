package com.predell.email;

/**
 *
 */
public class EmailMessageType {

	private EmailMessageTypeHierarchy hierarchy;

	/**
	 *
	 * @return Email message type hierarchy
	 */
	public EmailMessageTypeHierarchy getEmailMessageTypeHierarchy() {
		return hierarchy;
	}

	/**
	 *
	 * @param hierarchy Hierarchy
	 */
	public EmailMessageType(EmailMessageTypeHierarchy hierarchy) {
		this.hierarchy = hierarchy;
	}

	/**
	 *
	 */
	public enum EmailMessageTypeHierarchy {
		/**
		 *
		 */
		parent,
		/**
		 *
		 */
		child
	}
}


