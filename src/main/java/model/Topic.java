package model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name = "Topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    @Min(2)
    private Integer minSize = 2;

    @Column
    @Min(2)
    private Integer maxSize = 5;
    
    @JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss.0")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date createDate;

    @Column
    private Integer enrolledNumber = 0;

    @Column
    private Integer waitingToGrouped = 0;

    @Column
    private Integer totalGroupNumber = 0;
    
    @JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss.0")
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date nextGroupingTime;
    
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private User creator;

    @JsonIgnore
    @ManyToMany(mappedBy = "topics")
    private Set<User> users = new HashSet<User>();
    
    @OneToMany(mappedBy = "topic", fetch = FetchType.EAGER)
    @org.hibernate.annotations.Cascade( {org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<SubTopic> subTopics = new HashSet<SubTopic>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getMinSize() {
        return minSize;
    }

    public void setMinSize(Integer minSize) {
        this.minSize = minSize;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @JsonIgnore
    public User getCreator() {
        return creator;
    }
    @JsonProperty
    public void setCreator(User creator) {
        this.creator = creator;
    }
    @JsonIgnore
    public Set<User> getUsers() {
        return users;
    }
    @JsonProperty
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<SubTopic> getSubTopics() {
        return subTopics;
    }

    public void setSubTopics(Set<SubTopic> subTopics) {
        this.subTopics = subTopics;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getEnrolledNumber() {
        return enrolledNumber;
    }

    public void setEnrolledNumber(Integer enrolledNumber) {
        this.enrolledNumber = enrolledNumber;
    }

    public Integer getWaitingToGrouped() {
        return waitingToGrouped;
    }

    public void setWaitingToGrouped(Integer waitingToGrouped) {
        this.waitingToGrouped = waitingToGrouped;
    }

    public Integer getTotalGroupNumber() {
        return totalGroupNumber;
    }

    public void setTotalGroupNumber(Integer totalGroupNumber) {
        this.totalGroupNumber = totalGroupNumber;
    }

    public Date getNextGroupingTime() {
        return nextGroupingTime;
    }

    public void setNextGroupingTime(Date nextGroupingTime) {
        this.nextGroupingTime = nextGroupingTime;
    }
}
