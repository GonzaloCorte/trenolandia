package com.gontreno.trenolandia.arrival_inspector.domain;

import java.time.Duration;
import java.time.OffsetTime;
import java.time.ZonedDateTime;

public class LastTrainArrival {

    private final String trainNumber;
    private final String trainStation;

    private final ZonedDateTime plannedArrival;
    private final ZonedDateTime actualArrival;


    public LastTrainArrival(String trainNumber, String trainStation, ZonedDateTime plannedArrival, ZonedDateTime actualArrival) {
        this.trainNumber = trainNumber;
        this.trainStation = trainStation;
        this.plannedArrival = plannedArrival;
        this.actualArrival = actualArrival;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getTrainStation() {
        return trainStation;
    }

    public ZonedDateTime getPlannedArrival() {
        return plannedArrival;
    }

    public ZonedDateTime getActualArrival() {
        return actualArrival;
    }


    public long getDelay() {
        Duration difference = Duration.between(plannedArrival, actualArrival);

        return difference.getSeconds();
    }
}
