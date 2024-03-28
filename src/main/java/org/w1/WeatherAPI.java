package org.w1;
import org.json.JSONArray;
import spark.Request;
import spark.Response;

import java.sql.SQLException;

import static spark.Spark.*;

public class WeatherAPI {

    public static void main(String[] args) {
        port(8080); // Set port
        // Define route to fetch weather data
        get("/weather", WeatherAPI::getWeatherDataAsJson);
    }

    private static String getWeatherDataAsJson(Request req, Response res) throws SQLException {
        JSONArray jsonArray = new JSONArray();
        jsonArray = WeatherDataFetcher.getWeatherDataAsJson();
        res.type("application/json");
        return jsonArray.toString();
    }
}


