package fr.pepitruc.mandarine.service.csv;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import jakarta.validation.constraints.NotNull;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

@Service
public class CsvService {

    private final String GENERATED_FILES_DIR = "Résultat";

    public <M, E>Path generateDailyReportCsvFile(
            @NotNull final Collection<M> models,
            @NotNull final CsvGenerationParameter<M, E> csvGenerationParameter
    ) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {

        final List<E> entities = csvGenerationParameter.getConverter().convertToEntities(models)
                .stream()
                .sorted()
                .toList();

        final Path csvFile = this.prepareFile(csvGenerationParameter);
        this.writeFile(csvFile.toFile(), entities);

        return csvFile;
    }

    private <M, E>Path prepareFile(final CsvGenerationParameter<M, E> csvGenerationParameter) throws IOException {

        final var pattern = "{0}_{1}_{2}_{3}h{4}m{5}s.csv";
        final var dirPath = Files.createDirectories(Paths.get(MessageFormat.format("./{0}", GENERATED_FILES_DIR)));
        final var time = LocalTime.now();
        return Files.createFile(Paths.get(
                dirPath.toString(),
                MessageFormat.format(pattern,
                        csvGenerationParameter.getFileNamePrefix(),
                        csvGenerationParameter.getDepartement(),
                        StringUtils.join(csvGenerationParameter.getStationsName(), '-'),
                        time.getHour(),
                        time.getMinute(),
                        time.getSecond()))
        );
    }

    private <E> void writeFile(final File csvFile, final List<E> entities) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        try (final Writer writer = new FileWriter(csvFile)) {

            final StatefulBeanToCsv<E> sbc = new StatefulBeanToCsvBuilder<E>(writer)
                    .withSeparator(';')
                    .build();

            sbc.write(entities);
        }
    }
}
