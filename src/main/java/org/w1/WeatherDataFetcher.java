package org.w1;

import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.*;

public class WeatherDataFetcher {

    // Assuming these are your database credentials
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/openweather";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    public static JSONArray getWeatherDataAsJson() throws SQLException {
        JSONArray jsonArray = new JSONArray();
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        String sql = "SELECT * FROM weather_data";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("city", resultSet.getString("city"));
            jsonObject.put("temperature", resultSet.getDouble("temperature"));
            jsonObject.put("humidity", resultSet.getDouble("humidity"));
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }

    public static void main(String[] args) throws SQLException {

            JSONArray weatherData = getWeatherDataAsJson();
            System.out.println(weatherData.toString());

    }
}


