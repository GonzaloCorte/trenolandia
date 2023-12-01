package com.gontreno.trenolandia.csv_helper;

import com.gontreno.trenolandia.arrival_inspector.domain.PlainArrival;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class CvsReader {

    private static String TYPE = "text/csv";
    static String[] HEADERs = { "Numero treno", "Nome stazione", "Ora di passaggio" };

    public static boolean isValidCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<PlainArrival> csvToArrivals(MultipartFile multipartFile) {

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.Builder.create().setHeader().setSkipHeaderRecord(true).setTrim(true).build());) {

            List<PlainArrival> arrivals = new LinkedList<>();

            csvParser.stream().forEach(csvRecord -> {
                PlainArrival arrival = new PlainArrival(
                        csvRecord.get("Numero treno"),
                        csvRecord.get("Nome stazione"),
                        localDateTimeFormatter(csvRecord.get("Ora di passaggio"))
                );
                arrivals.add(arrival);
            });

            return arrivals;

        } catch(IOException e) {
            throw new RuntimeException("Failed trying to parse CVS file: " + e.getMessage());
        }
    }

    private static ZonedDateTime localDateTimeFormatter(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

        return ZonedDateTime.parse(date, dateTimeFormatter);
    }
}
