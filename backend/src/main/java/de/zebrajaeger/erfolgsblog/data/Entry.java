package de.zebrajaeger.erfolgsblog.data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Lars Brandt, Silpion IT Solutions GmbH
 */
@Entity
public class Entry {
    @Id
    private long id;

    private long created;

    private long time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
