
package com.sarfaraz.covid19_india.model.districtwise;

import java.util.List;
import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class AllDistricts {

    @Expose
    private List<DistrictDatum> districtData;
    @Expose
    private String state;
    @Expose
    private String statecode;

    public List<DistrictDatum> getDistrictData() {
        return districtData;
    }

    public void setDistrictData(List<DistrictDatum> districtData) {
        this.districtData = districtData;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatecode() {
        return statecode;
    }

    public void setStatecode(String statecode) {
        this.statecode = statecode;
    }

}
