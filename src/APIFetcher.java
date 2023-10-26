import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIFetcher {

    private static final String API_URL = "https://quotation-api-cdn.dunamu.com/v1/forex/recent?codes=FRX.KRWUSD";

    public static String fetchUSDDate() {
        JSONObject jsonObject = fetchJSONObject();
        return jsonObject.getString("date");
    }

    public static String fetchUSDTime() {
        JSONObject jsonObject = fetchJSONObject();
        return jsonObject.getString("time");
    }

    public static double fetchUSDKRW() {
        JSONObject jsonObject = fetchJSONObject();
        return jsonObject.getDouble("basePrice");
    }

    private static JSONObject fetchJSONObject() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            JSONArray jsonArray = new JSONArray(content.toString());
            return jsonArray.getJSONObject(0);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
