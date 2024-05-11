package fr.pepitruc.mandarine.repository.entity;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class DailyParametersEntity {

    // ########## META ##########

    /** Numéro Météo-France du poste sur 8 chiffres */
    @CsvBindByName(column = "NUM_POSTE")
    private String stationId;

    @CsvBindByName(column = "NOM_USUEL")
    private String stationName;

    @CsvBindByName(column = "LAT")
    private double latitude;

    @CsvBindByName(column = "LON")
    private double longitude;

    @CsvBindByName(column = "ALTI")
    private double altitude;

    /** Date de la mesure */
    @CsvBindByName(column = "AAAAMMJJ")
    private String date;

    public String generateUniqueKey() {
        return stationId + date;
    }

}
