package fr.pepitruc.mandarine.repository.converter;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import fr.pepitruc.mandarine.model.DailyWeatherReport;
import fr.pepitruc.mandarine.repository.entity.DailyParametersEntity;
import fr.pepitruc.mandarine.repository.entity.OtherDailyParametersEntity;
import fr.pepitruc.mandarine.repository.entity.StandardDailyParametersEntity;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Component
public class WeatherReportConverter {

    public <T extends DailyParametersEntity> ArrayList<T> convertFromCSVToEntity(@NotNull final File file, final List<String> stations, final Class<T> clazz) throws IOException {

        ArrayList<T> reportData = new ArrayList<>();

        try (FileReader in = new FileReader(file)) {
            reportData = (ArrayList<T>) new CsvToBeanBuilder<T>(in)
                    .withType(clazz)
                    .withSeparator(';')
                    .withIgnoreEmptyLine(true)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withFieldAsNull(CSVReaderNullFieldIndicator.BOTH)
                    .withFilter(new CSVBeanFilterOnStation(stations))
                    .build()
                    .stream()
                    .collect(Collectors.toList());
        }

        return reportData;
    }

    public ArrayList<DailyWeatherReport> convertFromEntityToModel(@NotNull final List<StandardDailyParametersEntity> standardEntities, @NotNull final List<OtherDailyParametersEntity> otherEntities) {

        final Map<String, StandardDailyParametersEntity> standardEntitiesByKey = standardEntities.stream()
                .collect(Collectors.toMap(StandardDailyParametersEntity::generateUniqueKey, Function.identity()));

        final Map<String, OtherDailyParametersEntity> otherEntitiesByKey = otherEntities.stream()
                .collect(Collectors.toMap(OtherDailyParametersEntity::generateUniqueKey, Function.identity()));

        final List<DailyWeatherReport> models = Stream.concat(standardEntitiesByKey.keySet().stream(),
                        otherEntitiesByKey.keySet().stream())
                .distinct()
                .map(key -> createDailyWeatherReport(standardEntitiesByKey.get(key), otherEntitiesByKey.get(key)))
                .collect(Collectors.toList());

        return (ArrayList<DailyWeatherReport>) models;
    }

    private DailyWeatherReport createDailyWeatherReport(final StandardDailyParametersEntity standard, final OtherDailyParametersEntity other) {

        final boolean okStd = nonNull(standard);
        final boolean okOth = nonNull(other);

        return DailyWeatherReport.builder()
                .stationName(okStd && isNotBlank(standard.getStationName()) ?
                        standard.getStationName() :
                        okOth && isNotBlank(other.getStationName()) ?
                                other.getStationName() :
                                null)
                .date(this.convertToLocalDate(okStd && isNotBlank(standard.getDate()) ?
                        standard.getDate() :
                        okOth && isNotBlank(other.getDate()) ?
                            other.getDate() :
                            null))
                .rainRate(okStd ? standard.getRainRate() : 0d)
                .minTempIndoor(okStd ? standard.getMinTempIndoor() : 0d)
                .minTempTimeIndoor(this.convertToLocalTime(okStd && isNotBlank(standard.getMinTempTimeIndoor()) ?
                        standard.getMinTempTimeIndoor() :
                        null))
                .maxTempIndoor(okStd ? standard.getMaxTempIndoor() : 0d)
                .maxTempTimeIndoor(this.convertToLocalTime(okStd && isNotBlank(standard.getMaxTempTimeIndoor()) ?
                        standard.getMaxTempTimeIndoor() :
                        null))
                .deltaTempIndoor(okStd ? standard.getDeltaTempIndoor() : 0d)
                .minTempAtGroundLevel(okStd ? standard.getMinTempAtGroundLevel() : 0d)
                .avgHourlyTempIndoor(okStd ? standard.getAvgHourlyTempIndoor() : 0d)
                .freezingPeriodIndoor(okStd ? standard.getFreezingPeriodIndoor() : 0)
                .avgWindSpeed(okStd ? standard.getAvgWindSpeed() : 0d)
                .maxWindSpeed(okStd ? standard.getMaxWindSpeed() : 0d)
                .maxWindTime(this.convertToLocalTime(okStd && isNotBlank(standard.getMaxWindTime()) ?
                        standard.getMaxWindTime() :
                        null))
                .maxWindDirection(okStd ? standard.getMaxWindDirection() : 0)
                .insolationPeriod(okOth ? other.getInsolationPeriod() : 0)
                .dailyGlobalSolarRadiation(okOth ? other.getDailyGlobalSolarRadiation() : 0)
                .minHourlyHumidityPercentage(okOth ? other.getMinHourlyHumidityPercentage() : 0)
                .minHourlyHumidityPercentageTime(this.convertToLocalTime(okOth && isNotBlank(other.getMinHourlyHumidityPercentageTime()) ?
                        other.getMinHourlyHumidityPercentageTime() :
                        null))
                .maxHourlyHumidityPercentage(okOth ? other.getMaxHourlyHumidityPercentage() : 0)
                .maxHourlyHumidityPercentageTime(this.convertToLocalTime(okOth && isNotBlank(other.getMaxHourlyHumidityPercentageTime()) ?
                        other.getMaxHourlyHumidityPercentageTime() :
                        null))
                .avgHourlyHumidityPercentage(okOth ? other.getAvgHourlyHumidityPercentage() : 0)
                .etp(okOth ? other.getEtp() : 0d)
                .snowing(okOth && other.getSnowing() == 1)
                .snowQuantity(okOth ? other.getSnowQuantity() : 0d)
                .hailing(okOth && other.getHailing() == 1)
                .freezing(okOth && other.getFreezing() == 1)
                .build();
    }

    private LocalDate convertToLocalDate(final String date) {
        if (Objects.isNull(date))
            return null;

        return LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE);
    }

    private LocalTime convertToLocalTime(final String time) {
        if (Objects.isNull(time))
            return null;

        // 00:00 to 09:59 are written as 000 to 959 >:( fucking CSV bullshit format and lazy devs.
        var paddedTime = StringUtils.leftPad(time.trim(), 4, '0');

        return LocalTime.parse(paddedTime, DateTimeFormatter.ofPattern("HHmm"));
    }

}
