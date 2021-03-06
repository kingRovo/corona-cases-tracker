package com.example.coronacasestracker.services;

import com.example.coronacasestracker.model.CoronaStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CasesServices {

    private static String Cases_DATA_URL = "https://covid19.who.int/WHO-COVID-19-global-data.csv";

    Date currentDate = new Date();

    private static final long ONE_DAY_MILLI_SECONDS = 24 * 60 * 60 * 1000;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    long previousDayMilliSeconds = currentDate.getTime() - ONE_DAY_MILLI_SECONDS;
    Date previousDate = new Date(previousDayMilliSeconds);
    String prvsDate = dateFormat.format(previousDate);



    private List<CoronaStats> allStats = new ArrayList<>();

    public List<CoronaStats> getAllStats() {
        return allStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
        List<CoronaStats> newStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Cases_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {






            if (prvsDate.equals(record.get(0))) {

                CoronaStats coronaStats = new CoronaStats();
                coronaStats.setCountry(record.get("Country"));
                coronaStats.setNewCases(Integer.parseInt(record.get("New_cases")));
                coronaStats.setNewdeath(Integer.parseInt(record.get("New_deaths")));
                int latestCases = Integer.parseInt(record.get(record.size() - 1));
                int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
                coronaStats.setLatestTotalCases(latestCases);
                coronaStats.setDiffFromPrevDay(latestCases - prevDayCases);
                newStats.add(coronaStats);
            }
       }
        this.allStats = newStats;
    }
}
