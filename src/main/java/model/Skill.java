package model;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

@Entity(name = "Skill")
@AssociationOverrides({ @AssociationOverride(name = "userSubTopicId.user", joinColumns = @JoinColumn(name = "user_id")),
		@AssociationOverride(name = "userSubTopicId.topic", joinColumns = @JoinColumn(name = "topic_id")) })
public class Skill {
	@EmbeddedId
	private UserSubTopicId userSubTopicId;
	@Column(nullable = false)
	private Integer score = 0;

	public UserSubTopicId getUserSubTopicId() {
		return userSubTopicId;
	}

	public void setUserSubTopicId(UserSubTopicId userSubTopicId) {
		this.userSubTopicId = userSubTopicId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}
