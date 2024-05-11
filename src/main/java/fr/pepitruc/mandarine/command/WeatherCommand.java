package fr.pepitruc.mandarine.command;

import fr.pepitruc.mandarine.service.WeatherService;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Command(group = "météo")
public class WeatherCommand {

    private final WeatherService weatherService;

    public WeatherCommand(final WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Command(command = "bilan-hydrique", alias = "bh", description = "Génère un fichier CSV pour un copier/coller facile et sans erreur dans le fichier Excel \"Bilan Hydrique.xlsx\" de Remy. Exemple d'utilisation : \"bilan-hydrique 77 melun\"")
    public String bilanHydrique(
            @Option(required = true, description = "Le numéro du département des stations", longNames = {"departement", "dep"}, shortNames = 'd') int departement,
            @Option(required = true, description = "Les noms des stations séparés par une virgule ','", longNames = "stations", shortNames = 's') String[] stations
    ) {

        final List<String> stationsName = Arrays.stream(stations).map(s -> s.trim().toUpperCase()).toList();

        String message;

        try {
            final Path generatedBilanHydriqueFile = this.weatherService.generateBilanHydrique(departement, stationsName);
            message = STR."Le bilan hydrique a été généré et enregistré sous ./Bilan Hydrique/\"\{generatedBilanHydriqueFile.getFileName().toString()}\"";
        } catch (final Exception e) {
            message = "Le bilan hydrique n'a pas pû être généré, demandez à Julien pourquoi (le nullos, il code avec le cul) !";
        }

        return message;
    }

}
