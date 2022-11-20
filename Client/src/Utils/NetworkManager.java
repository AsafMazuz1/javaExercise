package Utils;

import java.io.IOException;
import java.util.List;

import Models.Item;
import Models.MessageResponse;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

// This class is used to make network calls
public class NetworkManager {
    private static final String API_URL = "http://localhost:8080/api/";

    public static List<Item> getStock() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL + "items/stock")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return ResultFormatter.getListOfItems(response.body().string());
        } catch (IOException e) {
            System.out.println("Server is not running!");
            return null;
        }

    }

    public static Double getPrice(long id) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL + "items/" + id + "/price")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return ResultFormatter.getItemPrice(response.body().string());
        } catch (IOException e) {
            System.out.println("Server is not running!");
            return null;
        }

    }

    public static MessageResponse buyItem(long id) {
        OkHttpClient client = new OkHttpClient();

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create("{}", JSON);

        Request request = new Request.Builder()
                .put(body)
                .url(API_URL + "items/" + id + "/buy")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return ResultFormatter.getMessageResponseFromString(response.body().string());
        } catch (IOException e) {
            System.out.println("Server is not running!");
            return null;
        }
    }
}
