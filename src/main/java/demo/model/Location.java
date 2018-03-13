package demo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import model.Topic;

public class Location {
    
    private Integer id;
    private String title;
    
    @JsonIgnore
    private List<Topic> topics = new ArrayList<>();

    public Location() {
    }

    public Location(Integer id, String title, List<Topic> topics) {
        this.id = id;
        this.title = title;
        this.topics = topics;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        return id.equals(location.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
