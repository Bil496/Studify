package model;

import javax.persistence.*;

@Entity(name = "Talent")
@AssociationOverrides({
        @AssociationOverride(name = "userSubTopicId.user", joinColumns = @JoinColumn(name = "user_id")),
        @AssociationOverride(name = "userSubTopicId.subTopic", joinColumns = @JoinColumn(name = "subTopic_id"))})
public class Talent implements Comparable<Talent> {
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

    @Override
    public int compareTo(Talent o) {
        return userSubTopicId.getSubTopic().getId().compareTo(o.userSubTopicId.getSubTopic().getId());
    }

}