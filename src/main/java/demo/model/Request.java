package demo.model;

public class Request {
    
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

}
