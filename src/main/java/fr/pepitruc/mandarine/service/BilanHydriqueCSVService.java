package fr.pepitruc.mandarine.service;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import fr.pepitruc.mandarine.model.DailyWeatherReport;
import fr.pepitruc.mandarine.model.csv.BilanHydriqueCSVEntity;
import jakarta.validation.constraints.NotNull;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

@Service
public class BilanHydriqueCSVService {

    public Path generateBilanHydriqueFile(
            @NotNull final Collection<DailyWeatherReport> dailyWeatherReport,
            @NotNull final int departement,
            @NotNull final List<String> stationsName
    ) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {

        var bilanHydriqueCSVEntities = dailyWeatherReport.stream()
                .map(this::convertFromModelToCSVEntity)
                .sorted()
                .toList();

        var dirPath = Files.createDirectories(Paths.get("./Bilan Hydrique"));
        var time = LocalTime.now();
        var generatedFile = Files.createFile(Paths.get(dirPath.toString(), STR."/\{departement}_\{StringUtils.join(stationsName, '-')}_\{time.getHour()}h\{time.getMinute()}m\{time.getSecond()}s.csv"));

        try (final Writer writer = new FileWriter(generatedFile.toFile())) {

            final StatefulBeanToCsv<BilanHydriqueCSVEntity> sbc = new StatefulBeanToCsvBuilder<BilanHydriqueCSVEntity>(writer)
                    .withSeparator(';')
                    .build();

            sbc.write(bilanHydriqueCSVEntities);
        }

        return generatedFile;
    }

    private BilanHydriqueCSVEntity convertFromModelToCSVEntity(final DailyWeatherReport dwr) {
        return BilanHydriqueCSVEntity.builder()
                .station(dwr.getStationName())
                .date(dwr.getDate())
                .rainRate(dwr.getEtp())
                .etp(dwr.getEtp())
                .build();
    }
}
