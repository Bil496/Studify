package model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;

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

    @Column
    private Date createDate;

    @Column
    private Integer enrolledNumber;

    @Column
    private Integer waitingToGrouped;

    @Column
    private Integer totalGroupNumber;

    @Column
    private Date nextGroupingTime;

    @ManyToOne(cascade = CascadeType.ALL)
    private User creator;

    @ManyToMany(mappedBy = "topics")
    private Set<User> users = new HashSet<User>();

    @OneToMany(mappedBy = "topic")
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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Set<User> getUsers() {
        return users;
    }

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
