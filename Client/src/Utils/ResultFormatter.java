package Utils;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Models.Item;
import Models.MessageResponse;

// This class is used to format the responses from the server
public class ResultFormatter {

    public static List<Item> getListOfItems(String itemString) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            return Arrays.asList(mapper.readValue(itemString, Item[].class));
        } catch (JsonMappingException e) {

            e.printStackTrace();
        } catch (JsonProcessingException e) {

            e.printStackTrace();
        }
        return null;

    }

    public static Double getItemPrice(String result) {
        if (result.length() > 0) {
            return Double.parseDouble(result);
        } else {
            return -1.0;
        }
    }

    public static MessageResponse getMessageResponseFromString(String response) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(response, MessageResponse.class);
        } catch (JsonProcessingException e) {

            e.printStackTrace();
        }
        return null;
    }
}
