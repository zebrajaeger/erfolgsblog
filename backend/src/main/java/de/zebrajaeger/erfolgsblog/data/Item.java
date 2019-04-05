package de.zebrajaeger.erfolgsblog.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Lars Brandt, Silpion IT Solutions GmbH
 */
@Entity
public class Item {
    @Id
    private long id;
    private String text;
    private List<ItemResource> resources = new LinkedList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ItemResource> getResources() {
        return resources;
    }

    public void setResources(List<ItemResource> resources) {
        this.resources = resources;
    }
}
