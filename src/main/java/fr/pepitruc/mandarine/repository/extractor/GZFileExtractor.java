package fr.pepitruc.mandarine.repository.extractor;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.ResponseExtractor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class GZFileExtractor implements ResponseExtractor<File> {

    @Override
    public File extractData(final ClientHttpResponse response) throws IOException {

        final var tmp = File.createTempFile("mandarine_", ".csv");

        try (
                final FileOutputStream out = new FileOutputStream(tmp);
                final GZIPInputStream in = new GZIPInputStream(response.getBody());
        ) {
            StreamUtils.copy(in, out);
            return tmp;
        }
    }
}
