
package com.sarfaraz.covid19_india.model.data;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Tested {

    @SerializedName("individualstestedperconfirmedcase")
    private String mIndividualstestedperconfirmedcase;
    @SerializedName("positivecasesfromsamplesreported")
    private String mPositivecasesfromsamplesreported;
    @SerializedName("samplereportedtoday")
    private String mSamplereportedtoday;
    @SerializedName("source")
    private String mSource;
    @SerializedName("testpositivityrate")
    private String mTestpositivityrate;
    @SerializedName("testsconductedbyprivatelabs")
    private String mTestsconductedbyprivatelabs;
    @SerializedName("testsperconfirmedcase")
    private String mTestsperconfirmedcase;
    @SerializedName("totalindividualstested")
    private String mTotalindividualstested;
    @SerializedName("totalpositivecases")
    private String mTotalpositivecases;
    @SerializedName("totalsamplestested")
    private String mTotalsamplestested;
    @SerializedName("updatetimestamp")
    private String mUpdatetimestamp;

    public String getIndividualstestedperconfirmedcase() {
        return mIndividualstestedperconfirmedcase;
    }

    public void setIndividualstestedperconfirmedcase(String individualstestedperconfirmedcase) {
        mIndividualstestedperconfirmedcase = individualstestedperconfirmedcase;
    }

    public String getPositivecasesfromsamplesreported() {
        return mPositivecasesfromsamplesreported;
    }

    public void setPositivecasesfromsamplesreported(String positivecasesfromsamplesreported) {
        mPositivecasesfromsamplesreported = positivecasesfromsamplesreported;
    }

    public String getSamplereportedtoday() {
        return mSamplereportedtoday;
    }

    public void setSamplereportedtoday(String samplereportedtoday) {
        mSamplereportedtoday = samplereportedtoday;
    }

    public String getSource() {
        return mSource;
    }

    public void setSource(String source) {
        mSource = source;
    }

    public String getTestpositivityrate() {
        return mTestpositivityrate;
    }

    public void setTestpositivityrate(String testpositivityrate) {
        mTestpositivityrate = testpositivityrate;
    }

    public String getTestsconductedbyprivatelabs() {
        return mTestsconductedbyprivatelabs;
    }

    public void setTestsconductedbyprivatelabs(String testsconductedbyprivatelabs) {
        mTestsconductedbyprivatelabs = testsconductedbyprivatelabs;
    }

    public String getTestsperconfirmedcase() {
        return mTestsperconfirmedcase;
    }

    public void setTestsperconfirmedcase(String testsperconfirmedcase) {
        mTestsperconfirmedcase = testsperconfirmedcase;
    }

    public String getTotalindividualstested() {
        return mTotalindividualstested;
    }

    public void setTotalindividualstested(String totalindividualstested) {
        mTotalindividualstested = totalindividualstested;
    }

    public String getTotalpositivecases() {
        return mTotalpositivecases;
    }

    public void setTotalpositivecases(String totalpositivecases) {
        mTotalpositivecases = totalpositivecases;
    }

    public String getTotalsamplestested() {
        return mTotalsamplestested;
    }

    public void setTotalsamplestested(String totalsamplestested) {
        mTotalsamplestested = totalsamplestested;
    }

    public String getUpdatetimestamp() {
        return mUpdatetimestamp;
    }

    public void setUpdatetimestamp(String updatetimestamp) {
        mUpdatetimestamp = updatetimestamp;
    }

}
