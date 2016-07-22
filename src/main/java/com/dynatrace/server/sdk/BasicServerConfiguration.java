package com.dynatrace.server.sdk;

import org.apache.http.annotation.Immutable;
import org.apache.http.annotation.ThreadSafe;

import java.security.Principal;

/**
 * A basic immutable implementation of {@link ServerConfiguration} interface.
 * Immutable and thus thread-safe.
 *
 * @author Maciej Mionskowski
 */
@Immutable
@ThreadSafe
public class BasicServerConfiguration implements ServerConfiguration {
    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 8021;
    public static final boolean DEFAULT_SSL = true;
    public static final boolean DEFAULT_SKIP_CERTIFICATE_CHECK = true;

    private final String name;
    private final String password;
    private final boolean ssl;
    private final String host;
    private final int port;
    private final boolean skipCertificateCheck;

    /**
     * Constructs BasicServerConfiguration with given {@code username} and {@code password} and populating it with default values:
     * <dl>
     * <dt>Host</dt>
     * <dd>{@value DEFAULT_HOST}</dd>
     * <dt>Port</dt>
     * <dd>{@value DEFAULT_PORT}</dd>
     * <dt>SSL</dt>
     * <dd>{@value DEFAULT_SSL}</dd>
     * </dl>
     *
     * @param name     - server's username
     * @param password - server's password
     */
    public BasicServerConfiguration(String name, String password) {
        this(name, password, DEFAULT_SSL, DEFAULT_HOST, DEFAULT_PORT, DEFAULT_SKIP_CERTIFICATE_CHECK);
    }

    /**
     * Constructs BasicServerConfiguration with given parameters
     *
     * @param name                 - server's username
     * @param password             - server's password
     * @param host                 - server's host
     * @param ssl                  - whether to preceed hostname with https:// or leave http://
     * @param port                 - server's port
     * @param skipCertificateCheck - whether to accept invalid certificates
     */
    public BasicServerConfiguration(String name, String password, boolean ssl, String host, int port, boolean skipCertificateCheck) {
        this.name = name;
        this.password = password;
        this.ssl = ssl;
        this.host = host;
        this.port = port;
        this.skipCertificateCheck = skipCertificateCheck;
    }

    public Principal getUserPrincipal() {
        return this;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean isSSL() {
        return this.ssl;
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public boolean isSkipCertificateCheck() {
        return this.skipCertificateCheck;
    }
}
