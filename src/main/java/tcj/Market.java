package tcj;

// To do GET requests
import kong.unirest.Unirest;
import kong.unirest.JsonNode;
import kong.unirest.HttpResponse;

import java.text.NumberFormat;

// To parse json
import org.json.JSONArray;
import org.json.JSONObject;

// To color message
import tcj.Utils;

import java.text.DecimalFormat;

public class Market {
    
    static Utils utils = new Utils();

    public String getMarketData() {
        
        System.out.println(utils.colorLine("\nReceiving market stats...", "yellow"));
        NumberFormat nfScientific = new DecimalFormat("################################################.###########################################");
        NumberFormat nfUSD = NumberFormat.getCurrencyInstance();
        NumberFormat nf = NumberFormat.getInstance();

        // Get data
        HttpResponse<JsonNode> jsonResponse = Unirest.get("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&ids=turtlecoin&order=market_cap_desc&per_page=100&page=1&sparkline=false")
            .asJson();
        // Extract json
        JSONArray actualJSON = jsonResponse.getBody().getArray();
        // Get the market data dict
        JSONObject marketData = actualJSON.getJSONObject(0);
        
        Double usdPriceScientific = marketData.getDouble("current_price");
        String usdPrice = "$" + nfScientific.format(usdPriceScientific);

        Double h24_price_change_double = marketData.getDouble
        ("price_change_percentage_24h");
        String h24_price_change = String.valueOf(h24_price_change_double);
        h24_price_change += "%";

        // If it's negative change, make it red. Else, make it green.
        h24_price_change = (h24_price_change.startsWith("-")) ? utils.colorLine(h24_price_change, "red") : utils.colorLine(h24_price_change, "green");

        Double circ_supply_double = marketData.getDouble("circulating_supply");
        String circ_supply = String.valueOf(circ_supply_double);
        circ_supply = nf.format(circ_supply_double) + " TRTL";

        String marketDataString = "\n"
            + "Current USD Price: " + usdPrice
            + "\n"
            + "24h price change: " + h24_price_change
            + "\n"
            + "Circulating Supply: " + circ_supply
            + "\n";
        
        return marketDataString;
    }

}