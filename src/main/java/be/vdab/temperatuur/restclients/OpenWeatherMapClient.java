package be.vdab.temperatuur.restclients;

import be.vdab.temperatuur.dto.OpenWeatherMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author Arne Van Eycken
 * @version 1.0
 */

public class OpenWeatherMapClient implements WeatherClient {
    private final WebClient client;
    private final String uri;
    OpenWeatherMapClient(WebClient.Builder builder, @Value("${openweathermapapi}") String uri){
        this.client = builder.build();
        this.uri = uri;
    }

    @Override
    public Optional<BigDecimal> getTemperatuur(String gemeente) {
        try {
            return Optional.of(client.get()
            .uri(uri,uriBuilder -> uriBuilder.build(gemeente))
            .retrieve().bodyToMono(OpenWeatherMap.class).block()
            .getMain().getTemp());
        } catch (WebClientResponseException.NotFound ex){
            return Optional.empty();
        }
    }
}
