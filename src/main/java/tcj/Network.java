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

public class Network {
    
    static Utils utils = new Utils();

    public String getNetworkData() {

        System.out.println(utils.colorLine("\nReceiving network stats...", "yellow"));

        NumberFormat nf = NumberFormat.getInstance();
        NumberFormat nfScientific = new DecimalFormat("################################################.###########################################");

        // Get data
        HttpResponse<JsonNode> jsonResponse = Unirest.get("https://blockapi.turtlepay.io:443/getinfo")
            .asJson();
        // Extract json
        JSONArray actualJSON = jsonResponse.getBody().getArray();
        // Get the market data dict
        JSONObject networkData = actualJSON.getJSONObject(0);
        
        Double networkHeightDouble = networkData.getDouble("network_height");
        String networkHeight = nf.format(networkHeightDouble);

        
        Double global_hashrate_kh = networkData.getDouble("hashrate");
        Double global_hashrate_mh = global_hashrate_kh / 1000000;
        global_hashrate_mh = (double) Math.round(global_hashrate_mh * 100) / 100;
        String global_hashrate = Double.toString(global_hashrate_mh);
        global_hashrate = global_hashrate += " MH/s";


        Double miningDifficultyScientific = networkData.getDouble("difficulty");
        Double miningDifficultyNoCommas = Double.parseDouble(nfScientific.format(miningDifficultyScientific));
        String miningDifficulty = nf.format(miningDifficultyNoCommas);


        String networkDataString = "\n"
            + "Network block height: " + networkHeight
            + "\n"
            + "Current global hashrate: " + global_hashrate
            + "\n"
            + "Mining difficulty: " + miningDifficulty
            + "\n";

        return networkDataString;

    }

}