package com.gontreno.trenolandia.arrival_inspector.inspector;

import com.gontreno.trenolandia.arrival_inspector.domain.CheckedArrival;
import com.gontreno.trenolandia.arrival_inspector.domain.PlainArrival;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArrivalInspectorImpl implements ArrivalInspector{

    public List<CheckedArrival> filterFiveTrainsMoreDelayed(List<PlainArrival> plannedArrivals, List<PlainArrival> actualArrivals){

        List<CheckedArrival> checkedArrivals = getDifferenceBetweenPlannedVsActualArrivals(plannedArrivals, actualArrivals);

        checkedArrivals.sort(Comparator.comparingLong(CheckedArrival::getDelay).reversed());

        return checkedArrivals.subList(0, 5);
    }

    public List<CheckedArrival> getDifferenceBetweenPlannedVsActualArrivals(
            List<PlainArrival> plannedArrivals, List<PlainArrival> actualArrivals) {

        List<CheckedArrival> checkedArrivals = new LinkedList<>();

        for (int i = 0; i < plannedArrivals.size(); i++) {
            PlainArrival plannedArrival = plannedArrivals.get(i);
            PlainArrival actualArrival = actualArrivals.get(i);

            checkedArrivals.add(
                new CheckedArrival(
                   plannedArrival.getTrainNumber(),
                   plannedArrival.getTrainStation(),
                   plannedArrival.formatArrivalToOffsetTime(),
                   actualArrival.formatArrivalToOffsetTime(),
                   plannedArrival.getDelayFromComparingActualArrival(
                           actualArrival.getArrival()
                   )
                )
            );
        }

        return checkedArrivals;
    }
}
