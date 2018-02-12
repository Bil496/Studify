package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name = "SubTopic")
public class SubTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JsonIgnore
    @ManyToOne
    private Topic topic;

    @Column(nullable = false)
    private String title;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }
    
    @JsonIgnore
    public Topic getTopic() {
	return topic;
    }
    @JsonProperty
    public void setTopic(Topic topic) {
	this.topic = topic;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }
}
