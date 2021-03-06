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

package com.dynatrace.sdk.server.testautomation.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.dynatrace.sdk.server.testautomation.adapters.DateStringIso8601Adapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "testRun")
public class TestRun {

    @XmlAttribute
    @XmlJavaTypeAdapter(DateStringIso8601Adapter.class)
    private Date startTime;
    @XmlAttribute
    private String platform;
    @XmlAttribute
    private String systemProfile;
    @XmlAttribute
    private TestCategory category;
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String href;
    @XmlAttribute
    private boolean finished;

    @XmlAttribute
    private String versionMajor;
    @XmlAttribute
    private String versionMinor;
    @XmlAttribute
    private String versionRevision;
    @XmlAttribute
    private String versionBuild;
    @XmlAttribute
    private CreationMode creationMode;

    @XmlAttribute
    private Integer numPassed;
    @XmlAttribute
    private Integer numFailed;
    @XmlAttribute
    private Integer numVolatile;
    @XmlAttribute
    private Integer numImproved;
    @XmlAttribute
    private Integer numDegraded;
    @XmlAttribute
    private Integer numInvalidated;

    @XmlElement
    private List<TestMetricFilter> includedMetrics = new ArrayList<>();

    @XmlElement
    private List<TestResult> testResults = new ArrayList<>();

    public TestRun(Date startTime, String platform, String systemProfile, TestCategory category, String id, String href, String versionMajor, String versionMinor, String versionRevision, String versionBuild, CreationMode creationMode, Integer numPassed, Integer numFailed, Integer numVolatile, Integer numImproved, Integer numDegraded, Integer numInvalidated, List<TestResult> testResults) {
        this.startTime = startTime;
        this.platform = platform;
        this.systemProfile = systemProfile;
        this.category = category;
        this.id = id;
        this.href = href;
        this.versionMajor = versionMajor;
        this.versionMinor = versionMinor;
        this.versionRevision = versionRevision;
        this.versionBuild = versionBuild;
        this.creationMode = creationMode;
        this.numPassed = numPassed;
        this.numFailed = numFailed;
        this.numVolatile = numVolatile;
        this.numImproved = numImproved;
        this.numDegraded = numDegraded;
        this.numInvalidated = numInvalidated;
        this.testResults = testResults;
    }

    //Required by JAXB
    public TestRun() {}


	public Date getStartTime() {
		return startTime;
	}
	public String getPlatform() {
        return this.platform;
    }

    public String getSystemProfile() {
        return this.systemProfile;
    }

    public TestCategory getCategory() {
        return this.category;
    }

    public String getId() {
        return this.id;
    }

    public String getHref() {
        return this.href;
    }

    public String getVersionMajor() {
        return this.versionMajor;
    }

    public String getVersionMinor() {
        return this.versionMinor;
    }

    public String getVersionRevision() {
        return this.versionRevision;
    }

    public String getVersionBuild() {
        return this.versionBuild;
    }

    public CreationMode getCreationMode() {
        return this.creationMode;
    }

    public List<TestResult> getTestResults() {
        return this.testResults;
    }

    public Integer getPassedCount() {
        return this.numPassed;
    }

    public Integer getFailedCount() {
        return this.numFailed;
    }

    public Integer getVolatileCount() {
        return this.numVolatile;
    }

    public Integer getImprovedCount() {
        return this.numImproved;
    }

    public Integer getDegradedCount() {
        return this.numDegraded;
    }

    public Integer getInvalidatedCount() {
        return this.numInvalidated;
    }

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public List<TestMetricFilter> getIncludedMetrics() {
		return includedMetrics;
	}

	public void setIncludedMetrics(List<TestMetricFilter> includedMetrics) {
		this.includedMetrics = includedMetrics;
	}

	@Override
	public String toString() {
		return "TestRun [startTime=" + startTime + ", platform=" + platform + ", systemProfile=" + systemProfile + ", category="
				+ category + ", id=" + id + ", href=" + href + ", finished=" + finished + ", versionMajor=" + versionMajor
				+ ", versionMinor=" + versionMinor + ", versionRevision=" + versionRevision + ", versionBuild=" + versionBuild
				+ ", creationMode=" + creationMode + ", numPassed=" + numPassed + ", numFailed=" + numFailed + ", numVolatile="
				+ numVolatile + ", numImproved=" + numImproved + ", numDegraded=" + numDegraded + ", numInvalidated="
				+ numInvalidated + ", includedMetrics=" + includedMetrics + ", testResults=" + testResults + "]";
	}


}
