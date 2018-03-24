package demo.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MergeRequest implements Serializable {
    
    private Integer id;

    @JsonIgnore
    private Team requester;
    @JsonIgnore
    private Team requested;

    private boolean accepted = false;
    private boolean denied = false;

    public MergeRequest(Team requester, Team requested) {
        this.requester = requester;
        this.requested = requested;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Team getRequester() {
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
        requester.removeMergeRequest(this);
        requested.removeMergeRequest(this);
        requested.merge(requester);
    }

    public void deny() {
        if (accepted) {
            throw new RuntimeException("This request is already accepted!");
        }
        if (denied) {
            throw new RuntimeException("This request is denied earlier!");
        }
        denied = true;
        requester.removeMergeRequest(this);
        requested.removeMergeRequest(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MergeRequest mergeRequest = (MergeRequest) o;

        return id.equals(mergeRequest.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public JSONObject toJSONObject(String... ignore) {
        List<String> ignoreList = Arrays.asList(ignore);

        Map<String, Object> map = new HashMap<>();
        if (!ignoreList.contains("id")) map.put("id", getId());
        if (!ignoreList.contains("requester")) map.put("requester", getRequester().toJSONObject("members", "requests"));
        if (!ignoreList.contains("requested")) map.put("requested", getRequested().toJSONObject("members", "requests"));

        return new JSONObject(map);
    }

    private static final long serialVersionUID = 1L;

}
