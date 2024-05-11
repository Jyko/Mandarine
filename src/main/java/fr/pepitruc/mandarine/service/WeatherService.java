package fr.pepitruc.mandarine.service;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import fr.pepitruc.mandarine.repository.WeatherRestRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Service
public class WeatherService {

    private final WeatherRestRepository weatherRepository;
    private final BilanHydriqueCSVService csvGeneratorService;

    public WeatherService(final WeatherRestRepository weatherRepository, final BilanHydriqueCSVService csvGeneratorService) {
        this.weatherRepository = weatherRepository;
        this.csvGeneratorService = csvGeneratorService;
    }

    public void generateDailyReport(final int departement, final List<String> stationsName) throws IOException {
        var dailyWeatherReport = this.weatherRepository.getDailyWeatherReport(departement, stationsName);
    }

    public Path generateBilanHydrique(final int departement, final List<String> stationsName) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        var dailyWeatherReport = this.weatherRepository.getDailyWeatherReport(departement, stationsName);
        return this.csvGeneratorService.generateBilanHydriqueFile(dailyWeatherReport, departement, stationsName);
    }
}
