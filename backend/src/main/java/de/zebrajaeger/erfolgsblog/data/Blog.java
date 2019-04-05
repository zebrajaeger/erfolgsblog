package de.zebrajaeger.erfolgsblog.data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Lars Brandt, Silpion IT Solutions GmbH
 */
@Entity
public class Blog {
    private long id;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Entry> entries = new HashSet<>();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Entry> getEntries() {
        return entries;
    }

    public void setEntries(Set<Entry> entries) {
        this.entries = entries;
    }
}
