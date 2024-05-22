package fr.pepitruc.mandarine.service;

import org.springframework.stereotype.Service;

import fr.pepitruc.mandarine.repository.StationRestRepository;
import jakarta.validation.constraints.NotNull;

@Service
public class StationService {

    private Map<String, int> stationsMap;

    private StationRestRepository stationRepository;


    public int getStationId(@NotNull final String stationName) {

        return this.stationRestRepository.getStationId(stationName);
    }

}
