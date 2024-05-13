package fr.pepitruc.mandarine.service;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import fr.pepitruc.mandarine.model.DailyWeatherReport;
import fr.pepitruc.mandarine.repository.WeatherRestRepository;
import fr.pepitruc.mandarine.service.csv.CsvService;
import fr.pepitruc.mandarine.service.csv.entity.DailyHydricReportCsvEntityConverter;
import fr.pepitruc.mandarine.service.csv.CSVGenerationException;
import fr.pepitruc.mandarine.service.csv.CsvGenerationParameter;
import fr.pepitruc.mandarine.service.csv.entity.DailyHydricReportCsvEntity;
import fr.pepitruc.mandarine.service.csv.entity.DailyWeatherReportCsvEntity;
import fr.pepitruc.mandarine.service.csv.entity.DailyWeatherReportCsvEntityConverter;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.List;

@Service
public class WeatherService {

    private final WeatherRestRepository weatherRepository;
    private final CsvService csvGeneratorService;

    public WeatherService(final WeatherRestRepository weatherRepository, final CsvService csvGeneratorService) {
        this.weatherRepository = weatherRepository;
        this.csvGeneratorService = csvGeneratorService;
    }

    public Path generateDailyWeatherReport(final int departement, final List<String> stationsName) throws CSVGenerationException {

        try {
            var dailyWeatherReport = this.weatherRepository.getDailyWeatherReport(departement, stationsName);
            var generationParams = CsvGenerationParameter.<DailyWeatherReport, DailyWeatherReportCsvEntity>builder()
                    .converter(new DailyWeatherReportCsvEntityConverter())
                    .fileNamePrefix("Bilan_Meteo")
                    .departement(departement)
                    .stationsName(stationsName)
                    .build();
            return this.csvGeneratorService.generateDailyReportCsvFile(dailyWeatherReport, generationParams);
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | IOException e) {
            var message = MessageFormat.format("La génération du CSV du Bilan Météo historiques pour le département du \"{0}\" et les stations \"{1}\" a échouée.", departement, StringUtils.join(stationsName, ','));
            throw new CSVGenerationException(message, e);
        }
    }

    public Path generateDailyHydricReport(final int departement, final List<String> stationsName) throws CSVGenerationException {

        try {
            var dailyWeatherReport = this.weatherRepository.getDailyWeatherReport(departement, stationsName);
            var generationParams = CsvGenerationParameter.<DailyWeatherReport, DailyHydricReportCsvEntity>builder()
                    .converter(new DailyHydricReportCsvEntityConverter())
                    .fileNamePrefix("Bilan_Hydrique")
                    .departement(departement)
                    .stationsName(stationsName)
                    .build();
            return this.csvGeneratorService.generateDailyReportCsvFile(dailyWeatherReport, generationParams);
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | IOException e) {
            var message = MessageFormat.format("La génération du CSV du Bilan Hydrique pour le département du \"{0}\" et les stations \"{1}\" a échouée.", departement, StringUtils.join(stationsName, ','));
            throw new CSVGenerationException(message, e);
        }
    }
}
