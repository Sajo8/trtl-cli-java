/*
    Copyright (C) 2018 Sajo8

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see .
*/

package tcj;

// To do GET requests
import kong.unirest.Unirest;
import kong.unirest.JsonNode;
import kong.unirest.HttpResponse;

// To format numbers appropriately
import java.text.NumberFormat;
import java.text.DecimalFormat;

// To parse json
import org.json.JSONArray;
import org.json.JSONObject;

// To color message
import tcj.Utils;

public class Supply {

    static Utils utils = new Utils();

    public String getSupplyData() {

        System.out.println(utils.colorLine("\nReceiving supply stats...", "yellow"));
        NumberFormat nf = NumberFormat.getInstance();

        // Get data
        HttpResponse<JsonNode> jsonResponse = Unirest.get("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&ids=turtlecoin&order=market_cap_desc&per_page=100&page=1&sparkline=false")
            .asJson();
        // Extract json
        JSONArray actualJSON = jsonResponse.getBody().getArray();
        // Get the market data dict
        JSONObject marketData = actualJSON.getJSONObject(0);

        // Get circulating supply
        Double circ_supply_double = marketData.getDouble("circulating_supply");
        String circ_supply = String.valueOf(circ_supply_double);
        circ_supply = nf.format(circ_supply_double) + " TRTL";

        // Format a string to return
        String supplyDataString = "\n"
            + "Circulating Supply: " + circ_supply
            + "\n";
        
        return supplyDataString;

    }

}