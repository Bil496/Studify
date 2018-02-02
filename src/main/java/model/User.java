package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

// TODO: Fetch types for collections has to change to Lazy after.
@Entity(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(name = "User_Topic", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
	    @JoinColumn(name = "topic_id") })
    private Set<Topic> topics = new HashSet<Topic>();

    @OneToMany(mappedBy = "userSubTopicId.user", fetch = FetchType.EAGER)
    private Set<Talent> talents = new HashSet<Talent>();

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(name = "User_Team", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
	    @JoinColumn(name = "team_id") })
    private Set<Team> teams = new HashSet<Team>();

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getSurname() {
	return surname;
    }

    public void setSurname(String surname) {
	this.surname = surname;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public Set<Topic> getTopics() {
	return topics;
    }

    public void setTopics(Set<Topic> topics) {
	this.topics = topics;
    }

    public Set<Talent> getTalents() {
	return talents;
    }

    public void setTalents(Set<Talent> talents) {
	this.talents = talents;
    }

    public Set<Team> getTeams() {
	return teams;
    }

    public void setTeams(Set<Team> teams) {
	this.teams = teams;
    }
}