package fr.pepitruc.mandarine.repository;

import fr.pepitruc.mandarine.model.DailyWeatherReport;
import fr.pepitruc.mandarine.repository.converter.WeatherReportConverter;
import fr.pepitruc.mandarine.repository.entity.OtherDailyParametersEntity;
import fr.pepitruc.mandarine.repository.entity.StandardDailyParametersEntity;
import fr.pepitruc.mandarine.repository.extractor.GZFileExtractor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WeatherRestRepository {

    // TODO : Separate the base URL and the file ID
    // TODO : Base URL as a Spring property
    final String BASE_URL = "https://object.files.data.gouv.fr/meteofrance/data/synchro_ftp/BASE/QUOT";
    final String PATTERN_STANDARD_DAILY_REPORT_FILENAME = "{0}/Q_{1,number,#}_latest-{2,number,#}-{3,number,#}_RR-T-Vent.csv.gz";
    final String PATTERN_OTHER_DAILY_REPORT_FILENAME = "{0}/Q_{1,number,#}_latest-{2,number,#}-{3,number,#}_autres-parametres.csv.gz";

    private final WeatherReportConverter weatherReportConverter;
    private final RestTemplate restTemplate;

    public WeatherRestRepository(final WeatherReportConverter weatherReportConverter) {
        this.weatherReportConverter = weatherReportConverter;
        this.restTemplate = new RestTemplate();
    }

    public ArrayList<DailyWeatherReport> getDailyWeatherReport(final int departement, final List<String> stationsName) throws IOException {

        var date = LocalDate.now();
        var currentYear = date.getYear();
        var lastYear = date.minusYears(1).getYear();

        final File standardDailyReportFile = this.restTemplate.execute(MessageFormat.format(PATTERN_STANDARD_DAILY_REPORT_FILENAME, BASE_URL, departement, lastYear, currentYear), HttpMethod.GET, null, new GZFileExtractor());
        var standardDailyParameters = this.weatherReportConverter.convertFromCSVToEntity(standardDailyReportFile, stationsName, StandardDailyParametersEntity.class);
        final File otherDailyReportFile = this.restTemplate.execute(MessageFormat.format(PATTERN_OTHER_DAILY_REPORT_FILENAME, BASE_URL, departement, lastYear, currentYear), HttpMethod.GET, null, new GZFileExtractor());
        var otherDailyParameters = this.weatherReportConverter.convertFromCSVToEntity(otherDailyReportFile, stationsName, OtherDailyParametersEntity.class);

        // Data aggregation, cleaning and formatting
        return this.weatherReportConverter.convertFromEntityToModel(standardDailyParameters, otherDailyParameters);
    }

}
