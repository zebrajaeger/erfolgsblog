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

    private String text;

    @Lob
    private byte[] imagePreview;
    @Lob
    private byte[] image;
    @Lob
    private byte[] file;

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

    public byte[] getImagePreview() {
        return imagePreview;
    }

    public void setImagePreview(byte[] imagePreview) {
        this.imagePreview = imagePreview;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
