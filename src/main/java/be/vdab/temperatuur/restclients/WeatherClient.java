package be.vdab.temperatuur.restclients;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author Arne Van Eycken
 * @version 1.0
 */

public interface WeatherClient {
    Optional<BigDecimal> getTemperatuur(String gemeente);
}
