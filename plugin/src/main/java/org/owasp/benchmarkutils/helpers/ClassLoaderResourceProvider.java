package org.owasp.benchmarkutils.helpers;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import org.apache.commons.io.IOUtils;

public class ClassLoaderResourceProvider implements ResourceProvider {

    private final Class<?> sourceClass;

    public ClassLoaderResourceProvider(Class<?> sourceClass) {
        this.sourceClass = sourceClass;
    }

    @Override
    public String loadAsString(String path) throws ResourceNotFound {
        try (InputStream inputStream = getResourceAsStream(path)) {
            return IOUtils.toString(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new ResourceNotFound(e);
        }
    }

    private InputStream getResourceAsStream(String path) {
        return sourceClass.getClassLoader().getResourceAsStream(path);
    }
}
