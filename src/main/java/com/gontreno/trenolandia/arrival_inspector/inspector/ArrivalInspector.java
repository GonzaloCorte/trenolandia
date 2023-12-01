package com.gontreno.trenolandia.arrival_inspector.inspector;

import com.gontreno.trenolandia.arrival_inspector.domain.CheckedArrival;
import com.gontreno.trenolandia.arrival_inspector.domain.PlainArrival;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ArrivalInspector {

    List<CheckedArrival> filterFiveTrainsMoreDelayed(List<PlainArrival> plannedArrivals, List<PlainArrival> actualArrivals);

    List<CheckedArrival> getDifferenceBetweenPlannedVsActualArrivals(
            List<PlainArrival> plannedArrivals, List<PlainArrival> actualArrivals);
}
