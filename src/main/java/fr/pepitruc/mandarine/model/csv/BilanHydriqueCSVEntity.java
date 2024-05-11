package fr.pepitruc.mandarine.model.csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BilanHydriqueCSVEntity implements Comparable<BilanHydriqueCSVEntity> {

    @CsvBindByName(column = "0_Station")
    private String station;

    @CsvBindByName(column = "1_Date")
    @CsvDate("dd/MM/yyyy")
    private LocalDate date;

    @CsvBindByName(column = "2_Précipitations", locale = "fr")
    private double rainRate;

    @CsvBindByName(column = "3_ETP", locale = "fr")
    private double etp;

    @Override
    public int compareTo(final BilanHydriqueCSVEntity o) {

        var stationCompare = this.getStation().compareTo(o.getStation());
        if (stationCompare == 0)
            stationCompare = this.getDate().compareTo(o.getDate());
        return stationCompare;
    }
}
