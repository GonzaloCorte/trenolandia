package com.gontreno.trenolandia.arrival_inspector.domain;

import java.time.OffsetTime;

public class CheckedArrival {

    private final String trainNumber;
    private final String trainStation;
    private final OffsetTime plannedArrival;
    private final OffsetTime actualArrival;

    private final long delay;

    public CheckedArrival(String trainNumber, String trainStation, OffsetTime plannedArrival, OffsetTime actualArrival, long delay) {
        this.trainNumber = trainNumber;
        this.trainStation = trainStation;
        this.plannedArrival = plannedArrival;
        this.actualArrival = actualArrival;
        this.delay = delay;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getTrainStation() {
        return trainStation;
    }

    public OffsetTime getPlannedArrival() {
        return plannedArrival;
    }

    public OffsetTime getActualArrivals() {
        return actualArrival;
    }

    public long getDelay() {
        return delay;
    }
}
