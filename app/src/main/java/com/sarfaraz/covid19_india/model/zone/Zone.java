
package com.sarfaraz.covid19_india.model.zone;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Zone {

    @Expose
    private String district;
    @Expose
    private String districtcode;
    @Expose
    private String lastupdated;
    @Expose
    private String source;
    @Expose
    private String state;
    @Expose
    private String zone;

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrictcode() {
        return districtcode;
    }

    public void setDistrictcode(String districtcode) {
        this.districtcode = districtcode;
    }

    public String getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(String lastupdated) {
        this.lastupdated = lastupdated;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "Zone{" +
                "district='" + district + '\'' +
                ", districtcode='" + districtcode + '\'' +
                ", lastupdated='" + lastupdated + '\'' +
                ", source='" + source + '\'' +
                ", state='" + state + '\'' +
                ", zone='" + zone + '\'' +
                '}';
    }
}
