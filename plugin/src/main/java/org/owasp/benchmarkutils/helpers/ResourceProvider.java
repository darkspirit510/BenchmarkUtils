package org.owasp.benchmarkutils.helpers;

public interface ResourceProvider {

    String loadAsString(String path) throws ResourceNotFound;
}
