package demo.model;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubTopic implements Serializable {

    private Integer id;
    private String title;

    public SubTopic() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubTopic subTopic = (SubTopic) o;

        return id.equals(subTopic.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public JSONObject toJSONObject(String... ignore) {
        List<String> ignoreList = Arrays.asList(ignore);

        Map<String, Object> map = new HashMap<>();
        if (!ignoreList.contains("id"))
            map.put("id", getId() != null ? getId() : JSONObject.NULL);

        if (!ignoreList.contains("title"))
            map.put("title", getTitle() != null ? getTitle() : JSONObject.NULL);

        return new JSONObject(map);
    }

    private static final long serialVersionUID = 1L;

}
