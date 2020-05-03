
package com.sarfaraz.covid19_india.model.districtwise;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class DistrictDatum {

    @Expose
    private int active;
    @Expose
    private int confirmed;
    @Expose
    private int deceased;
    @Expose
    private Delta delta;
    @Expose
    private String district;
    @Expose
    private String notes;
    @Expose
    private int recovered;

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getDeceased() {
        return deceased;
    }

    public void setDeceased(int deceased) {
        this.deceased = deceased;
    }

    public Delta getDelta() {
        return delta;
    }

    public void setDelta(Delta delta) {
        this.delta = delta;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    @Override
    public String toString() {
        return "DistrictDatum{" +
                "active=" + active +
                ", confirmed=" + confirmed +
                ", deceased=" + deceased +
                ", delta=" + delta +
                ", district='" + district + '\'' +
                ", notes='" + notes + '\'' +
                ", recovered=" + recovered +
                '}';
    }
}
