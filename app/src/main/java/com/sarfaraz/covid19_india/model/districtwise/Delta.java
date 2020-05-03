
package com.sarfaraz.covid19_india.model.districtwise;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Delta {

    @Expose
    private Long confirmed;
    @Expose
    private Long deceased;
    @Expose
    private Long recovered;

    public Long getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Long confirmed) {
        this.confirmed = confirmed;
    }

    public Long getDeceased() {
        return deceased;
    }

    public void setDeceased(Long deceased) {
        this.deceased = deceased;
    }

    public Long getRecovered() {
        return recovered;
    }

    public void setRecovered(Long recovered) {
        this.recovered = recovered;
    }

    @Override
    public String toString() {
        return "Delta{" +
                "confirmed=" + confirmed +
                ", deceased=" + deceased +
                ", recovered=" + recovered +
                '}';
    }
}
