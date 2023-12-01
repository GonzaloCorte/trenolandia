package com.gontreno.trenolandia.arrival_inspector.inspector;

import com.gontreno.trenolandia.arrival_inspector.domain.LastTrainArrival;
import com.gontreno.trenolandia.arrival_inspector.domain.PlainArrival;

import java.util.Collection;
import java.util.List;

public interface ArrivalInspector {

    List<LastTrainArrival> filterFiveTrainsMoreDelayed(List<PlainArrival> plannedArrivals, List<PlainArrival> actualArrivals);

    Collection<LastTrainArrival> obtainAllLastTrainsArrivals(
            List<PlainArrival> plannedArrivals, List<PlainArrival> actualArrivals);
}
