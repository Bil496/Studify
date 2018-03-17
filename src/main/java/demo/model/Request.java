package demo.model;

import java.io.Serializable;

public class Request implements Serializable {
    
    private Integer id;
    
    private User requester;
    private Team requested;

    public Request(User requester, Team requested) {
	this.requester = requester;
	this.requested = requested;
	requester.addRequest(this);
	requested.addRequest(this);
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
