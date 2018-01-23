package model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Embeddable
public class UserSubTopicId implements java.io.Serializable {
    @ManyToOne
    private User user;

    @ManyToOne
    private SubTopic subTopic;

    public UserSubTopicId(User user, SubTopic subTopic) {
	super();
	this.user = user;
	this.subTopic = subTopic;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public SubTopic getSubTopic() {
	return subTopic;
    }

    public void setSubTopic(SubTopic subTopic) {
	this.subTopic = subTopic;
    }
}