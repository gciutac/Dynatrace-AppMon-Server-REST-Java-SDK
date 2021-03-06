/*
 * Dynatrace Server SDK
 * Copyright (c) 2008-2016, DYNATRACE LLC
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *  Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *  Neither the name of the dynaTrace software nor the names of its contributors
 * may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT
 * SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
 * DAMAGE.
 */

package com.dynatrace.sdk.server;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BasicServerConfigurationTest {
    @Test
    public void constructMinimal() {
        BasicServerConfiguration bsc = new BasicServerConfiguration("constructMinimalUser", "constructMinimalPassword");
        assertThat(bsc.getName(), is("constructMinimalUser"));
        assertThat(bsc.getPassword(), is("constructMinimalPassword"));
        assertThat(bsc.getHost(), is(BasicServerConfiguration.DEFAULT_HOST));
        assertThat(bsc.getPort(), is(BasicServerConfiguration.DEFAULT_PORT));
        assertThat(bsc.isSSL(), is(BasicServerConfiguration.DEFAULT_SSL));
        assertThat(bsc.isValidateCertificates(), is(BasicServerConfiguration.DEFAULT_VALIDATE_CERTIFICATES));
    }

    @Test
    public void construct() {
        BasicServerConfiguration bsc = new BasicServerConfiguration("constructUser", "constructPassword", !BasicServerConfiguration.DEFAULT_SSL, "8.8.8.8", 8080, !BasicServerConfiguration.DEFAULT_VALIDATE_CERTIFICATES, BasicServerConfiguration.DEFAULT_CONNECTION_TIMEOUT - 100);
        assertThat(bsc.getName(), is("constructUser"));
        assertThat(bsc.getPassword(), is("constructPassword"));
        assertThat(bsc.getHost(), is("8.8.8.8"));
        assertThat(bsc.getPort(), is(8080));
        assertThat(bsc.isSSL(), is(!BasicServerConfiguration.DEFAULT_SSL));
        assertThat(bsc.isValidateCertificates(), is(!BasicServerConfiguration.DEFAULT_VALIDATE_CERTIFICATES));
        assertThat(bsc.getTimeout(), is(BasicServerConfiguration.DEFAULT_CONNECTION_TIMEOUT - 100));
    }
}
