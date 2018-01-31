package model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Embeddable
public class UserSubTopicId implements java.io.Serializable {
	@ManyToOne
	private User user;
	@ManyToOne
	private Topic topic;

	public UserSubTopicId(User user, Topic topic) {
		super();
		this.user = user;
		this.topic = topic;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

}
