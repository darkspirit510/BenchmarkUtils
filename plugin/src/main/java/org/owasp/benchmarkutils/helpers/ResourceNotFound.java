package org.owasp.benchmarkutils.helpers;

import java.io.IOException;

public class ResourceNotFound extends IOException {
    public ResourceNotFound(Exception e) {
        super(e);
    }
}
