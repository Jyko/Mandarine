package fr.pepitruc.mandarine;

import fr.pepitruc.mandarine.repository.WeatherRestRepository;
import fr.pepitruc.mandarine.repository.converter.WeatherReportConverter;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class WeatherRestRepositoryTest {


    @Test
    @Disabled
    public void test() throws IOException {

        var weatherRestRepository = new WeatherRestRepository(new WeatherReportConverter());
        weatherRestRepository.getDailyWeatherReport(77, Lists.list("Melun"));

    }
}
