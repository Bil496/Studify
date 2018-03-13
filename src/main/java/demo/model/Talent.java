package demo.model;

import java.io.Serializable;

public class Talent implements Serializable {
    
    private SubTopic subTopic;
    private Integer score;

    public Talent() {
	
    }

    public Talent(SubTopic subtopic, Integer score) {
        this.subTopic = subtopic;
        this.score = score;
    }

    public SubTopic getSubTopic() {
        return subTopic;
    }

    public void setSubTopic(SubTopic subTopic) {
        this.subTopic = subTopic;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
    
    private static final long serialVersionUID = 1L;
    
}
