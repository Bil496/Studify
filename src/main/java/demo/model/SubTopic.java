package demo.model;

import java.io.Serializable;

public class SubTopic implements Serializable {
    
    private Integer id;
    private String title;

    public SubTopic() {
	
    }

    public SubTopic(Integer id, String title) {
        this.id = id;
        this.title = title;
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
    
    private static final long serialVersionUID = 1L;
    
}
