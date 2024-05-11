package fr.pepitruc.mandarine;

import fr.pepitruc.mandarine.repository.converter.WeatherReportConverter;
import fr.pepitruc.mandarine.repository.entity.OtherDailyParametersEntity;
import fr.pepitruc.mandarine.repository.entity.StandardDailyParametersEntity;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class CSVTest {

    private WeatherReportConverter converter = new WeatherReportConverter();

    @Test
    public void test_standard() throws IOException {
        var entities = converter.convertFromCSVToEntity(new File(Paths.get("src", "test", "resources", "Q_77_latest-2023-2024_RR-T-Vent.csv").toAbsolutePath().toString()), Lists.list("MELUN", "JOUY-LE-CHATEL", "LA BROSSE-MX"), StandardDailyParametersEntity.class);

        Assertions.assertThat(entities.size()).isEqualTo(1476);
    }

    @Test
    public void test_other() throws IOException {
        var entities = converter.convertFromCSVToEntity(new File(Paths.get("src", "test", "resources", "Q_77_latest-2023-2024_autres-parametres.csv").toAbsolutePath().toString()), Lists.list("MELUN"), OtherDailyParametersEntity.class);

        Assertions.assertThat(entities.size()).isEqualTo(493);
    }

}
