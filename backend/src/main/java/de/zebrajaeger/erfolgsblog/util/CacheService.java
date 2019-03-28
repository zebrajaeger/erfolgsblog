package de.zebrajaeger.erfolgsblog.util;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Lars Brandt, Silpion IT Solutions GmbH
 */
@Service
public class CacheService {
    @Value("${erfolgsblog.cache.path:previewCache}")
    private File previewCache;

    public boolean contains(String key) {
        return keyToFile(key).exists();
    }

    public byte[] get(String key) throws IOException {
        return FileUtils.readFileToByteArray(keyToFile(key));
    }

    public void set(String key, byte[] data) throws IOException {
        if (!previewCache.exists()) {
            previewCache.mkdirs();
        }

        FileUtils.writeByteArrayToFile(keyToFile(key), data);
    }

    private File keyToFile(String key) {
        return new File(previewCache, normalizeKey(key));
    }

    private String normalizeKey(String key) {
        try {
            return URLEncoder.encode(key, "UTF-8").replaceAll("[/\\\\]", "");
        } catch (UnsupportedEncodingException e) {
            throw new WTFException("???", e);
        }
    }
}
