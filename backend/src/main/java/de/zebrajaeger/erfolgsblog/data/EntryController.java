package de.zebrajaeger.erfolgsblog.data;

import de.zebrajaeger.erfolgsblog.util.CacheService;
import de.zebrajaeger.erfolgsblog.util.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lars Brandt, Silpion IT Solutions GmbH
 */
@RestController
public class EntryController {

    private EntryRepository entryRepository;
    private CacheService cacheService;

    public EntryController(EntryRepository entryRepository, CacheService cacheService) {
        this.entryRepository = entryRepository;
        this.cacheService = cacheService;
    }

    @GetMapping(path = "/api/entry")
    public Page<SimpleEntity> getEntries(Pageable pageable) {
        Page<Entry> all = entryRepository.findAll(pageable);
        List<SimpleEntity> simpleEntities = all.stream()
                .map(SimpleEntity::new)
                .collect(Collectors.toList());

        return new PageImpl<>(simpleEntities, all.getPageable(), all.getTotalElements());
    }

    @GetMapping(path = "/api/entry/{entryId}")
    public SimpleEntity getEntryById(@PathVariable() long entryId) {
        return new SimpleEntity(
                entryRepository
                        .findById(entryId)
                        .orElseThrow(ResourceNotFoundException::new)
        );
    }

    @GetMapping(path = "/api/entry/{entryId}/{itemIndex}/file")
    public byte[] getFile(@PathVariable() long entryId, @PathVariable() int itemIndex) {
        return findItem(entryId, itemIndex).getFile();
    }

    @GetMapping(path = "/api/entry/{entryId}/{itemIndex}/preview/{previewSize}")
    public byte[] getPreview(@PathVariable() long entryId, @PathVariable() int itemIndex, @PathVariable() String previewSize) throws IOException {
        Item item = findItem(entryId, itemIndex);
        if (Item.Type.IMAGE.equals(item.getType())) {

            // preview size
            String[] parts = previewSize.split("x");
            int w = 250, h = 250;
            if (parts.length == 1) {
                w = h = Integer.parseInt(parts[0]);
            } else if (parts.length == 2) {
                w = Integer.parseInt(parts[0]);
                h = Integer.parseInt(parts[1]);
            }

            // cache or create
            String key = item.getFileHash() + String.format("_%05dx&05d", w, h);
            if (cacheService.contains(key)) {
                return cacheService.get(key);
            } else {
                byte[] preview = generatePreview(item.getFile(), w, h);
                cacheService.set(key, preview);
                return preview;
            }
        }

        throw new ResourceNotFoundException();
    }

    private byte[] generatePreview(byte[] image, int previewW, int previewH) throws IOException {
        BufferedImage img;
        try (InputStream is = new ByteArrayInputStream(image)) {
            img = ImageIO.read(is);
        }

        float w = img.getWidth();
        float h = img.getHeight();
        float aW = w / (float) previewW;
        float aH = h / (float) previewH;

        float a = Math.max(aW, aH);

        int pW = (int) (w / a);
        int pH = (int) (h / a);

        int oX = (previewW - pW) / 2;
        int oY = (previewH - pH) / 2;

        BufferedImage preview = new BufferedImage(pW, pH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) preview.getGraphics();
        g.drawImage(img, oX, oY, pW, pH, null);
        g.dispose();

        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            ImageIO.write(preview, "PNG", os);
            return os.toByteArray();
        }
    }

    private Item findItem(@PathVariable long entryId, @PathVariable int itemIndex) {
        return entryRepository
                .findById(entryId)
                .orElseThrow(ResourceNotFoundException::new)
                .getItems()
                .get(itemIndex);
    }

    public static class SimpleEntity {
        private long id;
        private long created;
        private long time;
        private List<SimpleItem> items;

        public SimpleEntity(Entry e) {
            this.id = e.getId();
            this.created = e.getCreated();
            this.time = e.getTime();
            this.items = e.getItems().stream().map(SimpleItem::new).collect(Collectors.toList());
        }

        public long getId() {
            return id;
        }

        public long getCreated() {
            return created;
        }

        public long getTime() {
            return time;
        }

        public List<SimpleItem> getItems() {
            return items;
        }
    }

    private static class SimpleItem {
        private long id;
        private String text;
        private String fileHash;

        public SimpleItem(Item item) {
            this.id = item.getId();
            this.text = item.getText();
            this.fileHash = item.getFileHash();
        }

        public long getId() {
            return id;
        }

        public String getText() {
            return text;
        }

        public String getFileHash() {
            return fileHash;
        }
    }
}
