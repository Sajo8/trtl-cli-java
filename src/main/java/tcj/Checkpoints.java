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

// To parse json
import org.json.JSONArray;

// To parse time passed
import java.time.Instant;
import java.time.Duration;

// To color message
import tcj.Utils;

public class Checkpoints {

    static Utils utils = new Utils();

    public String getCheckpointsData() {

        System.out.println(utils.colorLine("\nReceiving checkpoint info...", "yellow"));

        Instant currentTime = Instant.now();

        // Get data
        HttpResponse<JsonNode> jsonResponse = Unirest.get("https://api.github.com/repos/turtlecoin/checkpoints/commits")
            .asJson();
        // Extract json
        JSONArray actualJSON = jsonResponse.getBody().getArray();

        String lastUpdateTime = actualJSON.getJSONObject(0).getJSONObject("commit").getJSONObject("author").getString("date");

        Instant lastUpdate = Instant.parse( lastUpdateTime );

        Duration timeElapsedSinceUpdate = Duration.between( lastUpdate , currentTime );
        
        long totalDays = timeElapsedSinceUpdate.toDays();
        long totalHours = timeElapsedSinceUpdate.toHours();
        long totalMinutes = timeElapsedSinceUpdate.toMinutes();
        long totalSeconds = timeElapsedSinceUpdate.toSeconds();
        
        String timeElapsedString = "Checkpoints updated ";
        String downloadFromString = "Learn how to use them at https://docs.turtlecoin.lol/guides/wallets/using-checkpoints\n";

        if (totalDays >= 1) {
            timeElapsedString += totalDays + " day(s), ";
            timeElapsedString += totalHours - (totalDays * 24) + " hour(s)";
        } else if (totalHours >= 1) {
            timeElapsedString += totalHours + " hour(s), ";
            timeElapsedString += totalMinutes - (totalHours * 60) + " minute(s)";
        } else if (totalMinutes >= 1) {
            timeElapsedString += totalMinutes + " minute(s), ";
            timeElapsedString += totalSeconds - (totalMinutes * 60) + " second(s)";
        } else {
            timeElapsedString += totalSeconds + " second(s)";
        }
        timeElapsedString += " ago.\n";

        String checkpointsDataString = timeElapsedString + downloadFromString;

        return checkpointsDataString;
    }
}