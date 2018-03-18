package demo.model;

import java.io.Serializable;

public class Request implements Serializable {
    
    private Integer id;
    
    private User requester;
    private Team requested;
    
    private boolean accepted = false;
    private boolean denied = false;

    public Request(User requester, Team requested) {
	this.requester = requester;
	this.requested = requested;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public User getRequester() {
	return requester;
    }
    
    public Team getRequested() {
	return requested;
    }
    
    public void accept() {
	if (accepted) {
	    throw new RuntimeException("This request is already accepted!");
	}
	if (denied) {
	    throw new RuntimeException("This request is denied earlier!");
	}
	accepted = true;
	requester.removeRequest(this);
	requested.removeRequest(this);
	requester.setCurrentTeam(requested);
    }
    
    public void deny() {
	if (accepted) {
	    throw new RuntimeException("This request is already accepted!");
	}
	if (denied) {
	    throw new RuntimeException("This request is denied earlier!");
	}
	accepted = true;
	requester.removeRequest(this);
	requested.removeRequest(this);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        return id.equals(request.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
    private static final long serialVersionUID = 1L;

}
