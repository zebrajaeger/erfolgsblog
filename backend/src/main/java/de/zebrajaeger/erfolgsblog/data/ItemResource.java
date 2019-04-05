package de.zebrajaeger.erfolgsblog.data;

import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/**
 * @author Lars Brandt, Silpion IT Solutions GmbH
 */
public class ItemResource {
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Item item;
    @Lob
    private byte[] data;

    private String name;

    private String dataHashCode;

    private Type type;

    //
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataHashCode() {
        return dataHashCode;
    }

    public void setDataHashCode(String dataHashCode) {
        this.dataHashCode = dataHashCode;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        FILE, IMAGE
    }
}
