package de.zebrajaeger.erfolgsblog.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * @author Lars Brandt, Silpion IT Solutions GmbH
 */
@Entity
public class Item {
    @Id
    private long id;

    private Type type;
    private String text;

    @Lob
    private byte[] file;
    private String fileHash;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }

    public enum Type {
        TEXT_ONLY, FILE, IMAGE
    }
}
