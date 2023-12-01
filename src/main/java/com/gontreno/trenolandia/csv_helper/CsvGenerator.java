package com.gontreno.trenolandia.csv_helper;

import com.gontreno.trenolandia.arrival_inspector.domain.LastTrainArrival;

import java.time.OffsetTime;
import java.time.ZonedDateTime;
import java.util.List;

public class CsvGenerator {

    private static final String CSV_HEADERS = "Numero treno,Destinazione,Ora pianificata,Ora effettiva,Ritardo";

    public static String generateCsv(List<LastTrainArrival> lastTrainArrivals) {
        StringBuilder csvContent = new StringBuilder();

        csvContent.append(CSV_HEADERS).append("\n");

        lastTrainArrivals.forEach(lastTrainArrival ->
                csvContent
                    .append(lastTrainArrival.getTrainNumber())
                    .append(lastTrainArrival.getTrainStation())
                    .append(zonedDateTimeToOffsetTime(lastTrainArrival.getPlannedArrival()))
                    .append(zonedDateTimeToOffsetTime(lastTrainArrival.getActualArrival()))
                    .append(lastTrainArrival.getDelay())
                        .append("\n")
                );

        return csvContent.toString();
    }

    private static OffsetTime zonedDateTimeToOffsetTime(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toOffsetDateTime().toOffsetTime();
    }
}
