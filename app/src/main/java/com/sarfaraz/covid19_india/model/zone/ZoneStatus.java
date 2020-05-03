
package com.sarfaraz.covid19_india.model.zone;

import java.util.List;
import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class ZoneStatus {

    @Expose
    private List<Zone> zones;

    public List<Zone> getZones() {
        return zones;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }

}
