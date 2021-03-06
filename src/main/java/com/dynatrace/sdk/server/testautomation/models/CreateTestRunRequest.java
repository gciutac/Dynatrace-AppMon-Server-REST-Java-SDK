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
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "testRun")
public class CreateTestRunRequest {

    @XmlTransient
    private String systemProfile;
    @XmlAttribute
    private String platform;
    @XmlAttribute
    private TestCategory category;

    @XmlAttribute
    private String versionMajor;
    @XmlAttribute
    private String versionMinor;
    @XmlAttribute
    private String versionRevision;
    @XmlAttribute(required = true)
    private String versionBuild;
    @XmlAttribute
    private String versionMilestone;
    @XmlAttribute
    private String marker;

    @XmlElement
    private TestMetaData additionalMetaData;

    @XmlElement
    private List<TestMetricFilter> includedMetrics = new ArrayList<>();

    //Required by JAXB
    public CreateTestRunRequest() {
    }

    public CreateTestRunRequest(String systemProfile, String versionBuild) {
        this.systemProfile = systemProfile;
        this.versionBuild = versionBuild;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getSystemProfile() {
        return this.systemProfile;
    }

    public void setSystemProfile(String systemProfile) {
        this.systemProfile = systemProfile;
    }

    public TestCategory getCategory() {
        return this.category;
    }

    public void setCategory(TestCategory category) {
        this.category = category;
    }

    public String getVersionMajor() {
        return this.versionMajor;
    }

    public void setVersionMajor(String versionMajor) {
        this.versionMajor = versionMajor;
    }

    public String getVersionMinor() {
        return this.versionMinor;
    }

    public void setVersionMinor(String versionMinor) {
        this.versionMinor = versionMinor;
    }

    public String getVersionRevision() {
        return this.versionRevision;
    }

    public void setVersionRevision(String versionRevision) {
        this.versionRevision = versionRevision;
    }

    public String getVersionBuild() {
        return this.versionBuild;
    }

    public void setVersionBuild(String versionBuild) {
        this.versionBuild = versionBuild;
    }

    public String getVersionMilestone() {
        return this.versionMilestone;
    }

    public void setVersionMilestone(String versionMilestone) {
        this.versionMilestone = versionMilestone;
    }

    public TestMetaData getAdditionalMetaData() {
        return this.additionalMetaData;
    }

    public void setAdditionalMetaData(TestMetaData additionalMetaData) {
        this.additionalMetaData = additionalMetaData;
    }

    public String getMarker() {
        return this.marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

	public List<TestMetricFilter> getIncludedMetrics() {
		return includedMetrics;
	}

	public void setIncludedMetrics(List<TestMetricFilter> includedMetrics) {
		this.includedMetrics = includedMetrics;
	}

	@Override
	public String toString() {
		return "CreateTestRunRequest [systemProfile=" + systemProfile + ", platform=" + platform + ", category=" + category
				+ ", versionMajor=" + versionMajor + ", versionMinor=" + versionMinor + ", versionRevision=" + versionRevision
				+ ", versionBuild=" + versionBuild + ", versionMilestone=" + versionMilestone + ", marker=" + marker
				+ ", additionalMetaData=" + additionalMetaData + ", includedMetrics=" + includedMetrics + "]";
	}


}
