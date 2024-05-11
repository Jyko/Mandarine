package fr.pepitruc.mandarine;

import fr.pepitruc.mandarine.repository.WeatherRestRepository;
import fr.pepitruc.mandarine.repository.converter.WeatherReportConverter;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;

public class WeatherRestRepositoryTest {


    @Test
    public void test() throws IOException {

        var weatherRestRepository = new WeatherRestRepository(new WeatherReportConverter());
        weatherRestRepository.getDailyWeatherReport(77, Lists.list("Melun"));

    }
}
