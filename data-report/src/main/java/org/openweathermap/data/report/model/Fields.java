package org.openweathermap.data.report.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Fields {
    NO("no", "No."),
    CITY_ID("cityId", "City ID"),
    CITY_NAME("cityName", "City Name"),
    CLOUDINESS("cloudiness", "Cloudiness (%)"),
    DCT("dct", "Data Computation Time"),
    TIMEZONE("timezone", "Timezone"),
    LON("lon", "Longitude"),
    LAT("lat", "Latitude"),
    GRND_LEVEL("grndLevel", "Ground Level (hPa)"),
    SEA_LEVEL("seaLevel", "Sea Level (hPa)"),
    HUMIDITY("humidity", "Humidity (%)"),
    PRESSURE("pressure", "Pressure (hPa)"),
    TEMPERATURE("temperature", "Temperature (C)"),
    TEMPERATURE_MIN("temperatureMin", "Min Temperature (C)"),
    TEMPERATURE_MAX("temperatureMax", "Max Temperature (C)"),
    RAIN_ONE_HOUR("rainOneHour", "Rain Volume 1h (mm)"),
    RAIN_THREE_HOURS("rainThreeHours", "Rain Volume 3hrs (mm)"),
    SNOW_ONE_HOUR("snowOneHour", "Snow Volume 1h (mm)"),
    SNOW_THREE_HOURS("snowThreeHours", "Snow Volume 3hrs (mm)"),
    COUNTRY("country", "Country Code"),
    SUNRISE("sunrise", "Sunrise Time"),
    SUNSET("sunset", "Sunset Time"),
    STATUS("status", "Weather Status"),
    DESCRIPTION("description", "Weather Description"),
    SPEED("speed", "Wind Speed (m/s)"),
    DEGREE("degree", "Wind Direction (Degrees)"),
    GUST("gust", "Gust");

    private static final Map<String, String> map = Stream.of(values())
                                                         .collect(Collectors.toMap(Fields::getKey,
                                                                                   Fields::getDisplayName));
    private final String key;
    private final String displayName;

    Fields(String key, String displayName) {
        this.key = key;
        this.displayName = displayName;
    }

    public static String getDisplayName(String key) {
        return map.get(key);
    }

    public static List<String> getHeaderKeys() {
        return Stream.of(values())
                     .map(h -> h.getKey())
                     .collect(Collectors.toList());
    }

    public String getKey() {
        return key;
    }

    public String getDisplayName() {
        return displayName;
    }
}
