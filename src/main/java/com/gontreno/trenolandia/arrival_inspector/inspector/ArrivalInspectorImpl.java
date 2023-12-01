package com.gontreno.trenolandia.arrival_inspector.inspector;

import com.gontreno.trenolandia.arrival_inspector.domain.LastTrainArrival;
import com.gontreno.trenolandia.arrival_inspector.domain.PlainArrival;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArrivalInspectorImpl implements ArrivalInspector {

    public List<LastTrainArrival> filterFiveTrainsMoreDelayed(List<PlainArrival> plannedArrivals, List<PlainArrival> actualArrivals) {

        List<LastTrainArrival> lastTrainArrivals = new ArrayList<>( obtainAllLastTrainsArrivals(plannedArrivals, actualArrivals) );

        lastTrainArrivals.sort(Comparator.comparingLong(LastTrainArrival::getDelay).reversed());

        return lastTrainArrivals.subList(0, Math.min(5, lastTrainArrivals.size()));
    }

    public Collection<LastTrainArrival> obtainAllLastTrainsArrivals(
            List<PlainArrival> plannedArrivals, List<PlainArrival> actualArrivals) {

        Map<String, LastTrainArrival> lastTrainsArrivals = new HashMap<>();

        for (PlainArrival plannedArrival : plannedArrivals) {
            String trainNumber = plannedArrival.getTrainNumber();

            if (!lastTrainsArrivals.containsKey(trainNumber) ||
                    plannedArrival.getArrival().isAfter(lastTrainsArrivals.get(trainNumber).getPlannedArrival())
            ) {

                PlainArrival actualArrival = actualArrivals.stream()
                        .filter(arrival -> Objects.equals(arrival.getTrainNumber(), plannedArrival.getTrainNumber()) &&
                                Objects.equals(arrival.getTrainStation(), plannedArrival.getTrainStation()))
                        .findFirst().get();

                LastTrainArrival lastTrainArrival = new LastTrainArrival(
                        trainNumber,
                        plannedArrival.getTrainStation(),
                        plannedArrival.getArrival(),
                        actualArrival.getArrival()
                );

                lastTrainsArrivals.put(trainNumber, lastTrainArrival);
            }

        }

        return lastTrainsArrivals.values();
    }
}