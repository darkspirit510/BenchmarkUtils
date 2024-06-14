package org.owasp.benchmarkutils.helpers;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ClassLoaderResourceProviderTest {

    @Test
    void returnsResourceAsString() throws ResourceNotFound {
        ClassLoaderResourceProvider resourceProvider =
                new ClassLoaderResourceProvider(ClassLoaderResourceProviderTest.class);

        String content = resourceProvider.loadAsString("testfiles/README.md");

        assertTrue(content.startsWith("# Testfiles"));
    }

    @Test
    void throwsExceptionWhenResourceWasNotFound() {
        ClassLoaderResourceProvider resourceProvider =
                new ClassLoaderResourceProvider(ClassLoaderResourceProviderTest.class);

        assertThrows(
                ResourceNotFound.class,
                () -> resourceProvider.loadAsString("does/not/exist.txt"),
                "No exception thrown");
    }
}
