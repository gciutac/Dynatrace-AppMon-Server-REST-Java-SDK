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

import com.dynatrace.sdk.server.exceptions.ServerConnectionException;
import com.dynatrace.sdk.server.testautomation.models.TestCategory;
import com.dynatrace.sdk.server.exceptions.ServerResponseException;
import com.dynatrace.sdk.server.testautomation.models.TestRun;
import com.github.tomakehurst.wiremock.client.BasicCredentials;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.net.UnknownHostException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ServiceTest {
    @Rule
    public WireMockRule wireMock = new WireMockRule(8080);
    private Service service;

    @Before
    public void setup() {
        this.service = new Service(new DynatraceClient(new BasicServerConfiguration("admin", "admin", false, "localhost", 8080, false, 1000)), "") {
        };
    }

    @Test
    public void invalidStatusCodeWithError() throws Exception {
        stubFor(get(urlPathEqualTo("/test")).willReturn(aResponse().withStatus(404).
        		withBody("{\"code\": 404, \"message\": \"Profile not found\"}")));
        try {
            this.service.doGetRequest("/test", Service.getEmtpyResolver());
            fail("Exception was expected to be thrown");
        } catch (ServerResponseException e) {
            assertThat(e.getStatusCode(), is(404));
            assertThat(e.getMessage(), is("Profile not found"));
        }
    }

    @Test
    public void invalidStatusCodeWithoutError() throws Exception {
        stubFor(get(urlPathEqualTo("/404")).willReturn(aResponse().withStatus(404)));
        stubFor(get(urlPathEqualTo("/300")).willReturn(aResponse().withStatus(300)));

        try {
            this.service.doGetRequest("/404", Service.getEmtpyResolver());
            fail("Exception was expected to be thrown");
        } catch (ServerResponseException e) {
            assertThat(e.getStatusCode(), is(404));
        }

        try {
            this.service.doGetRequest("/300", Service.getEmtpyResolver());
            fail("Exception was expected to be thrown");
        } catch (ServerResponseException e) {
            assertThat(e.getStatusCode(), is(300));
        }
    }

    @Test
    public void validResponse() throws Exception {
        stubFor(get(urlPathEqualTo("/test")).withBasicAuth("admin", "admin").willReturn(aResponse().withStatus(200)
        		.withBody("{\"category\":\"unit\"}")));
        TestRun tr = this.service.doGetRequest("/test", Service.getBodyResponseResolver(TestRun.class));
        //we test the rest of marshalling process for TestRun in a separate test
        assertThat(tr.getCategory(), Is.is(TestCategory.UNIT));
        verify(getRequestedFor(urlPathEqualTo("/test")).withBasicAuth(new BasicCredentials("admin", "admin")));
    }

    @Test
    public void malformedJson() throws Exception {
        stubFor(get(urlPathEqualTo("/test")).willReturn(aResponse().withStatus(200).withBody("{{ maformed json }")));
        try {
            this.service.doGetRequest("/test", Service.getBodyResponseResolver(TestRun.class));
            fail("Exception was expected to be thrown");
        } catch (ServerResponseException e) {
            assertTrue(e.getCause() instanceof JAXBException);
        }
    }

    @Test
    public void invalidPort() throws Exception {
        //http://en.wikipedia.org/wiki/Ephemeral_port
        this.service = new Service(new DynatraceClient(new BasicServerConfiguration("admin", "admin", false, "localhost", 49153, false, 1000))) {
        };
        try {
            this.service.doGetRequest("/", Service.getEmtpyResolver());
        } catch (ServerConnectionException e) {
            assertTrue((e.getCause() instanceof HttpHostConnectException) || (e.getCause() instanceof ConnectTimeoutException));
        }
    }

    @Test
    public void invalidHostname() throws Exception {
        this.service = new Service(new DynatraceClient(new BasicServerConfiguration("admin", "admin", false, "INVALID", 8080, false, 1000))) {
        };
        try {
            this.service.doGetRequest("/", Service.getEmtpyResolver());
            fail("Exception was expected to be thrown");
        } catch (ServerConnectionException e) {
            assertTrue(e.getCause() instanceof UnknownHostException);
        }
    }
}
