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
import org.json.JSONObject;

// To hold parsed data
import java.util.ArrayList;
import java.util.List;

// To make a table to format output
import tcj.TableGenerator;

// To color message
import tcj.Utils;

public class Nodes {

    static Utils utils = new Utils();
    static TableGenerator table = new TableGenerator();
    

    public String getNodeData() {

        System.out.println(utils.colorLine("\nReceiving node data...", "yellow  "));

        List<String> headersList = new ArrayList<>();
        headersList.add("Name");
        headersList.add("URL");
        headersList.add("Port");
        headersList.add("SSL");
        headersList.add("Cache");

        List<List<String>> rowsList = new ArrayList<>();

        // Get data
        HttpResponse<JsonNode> jsonResponse = Unirest.get("https://raw.githubusercontent.com/turtlecoin/turtlecoin-nodes-json/master/turtlecoin-nodes.json")
            .asJson();
        // Extract json
        JSONObject actualJSON = jsonResponse.getBody().getObject();
        // Get the list of nodes
        JSONArray nodesList = actualJSON.getJSONArray("nodes");

        for (int i = 0; i < nodesList.length(); i++) {
            
            List<String> row = new ArrayList<>(); 
            
            JSONObject node = nodesList.getJSONObject(i);
            
            String nodeName = node.getString("name");
            nodeName = utils.colorLine(nodeName, "green");

            String nodeUrl = node.getString("url");
            nodeUrl = utils.colorLine(nodeUrl, "green");
            
            String nodePort = Integer.toString(node.getInt("port"));
            nodePort = utils.colorLine(nodePort, "yellow");
            
            // If the node has SSL, color it red. Else, color it red.
            String nodeIsSSL = Boolean.toString(node.getBoolean("ssl"));
            nodeIsSSL = (nodeIsSSL.equals("true")) ? utils.colorLine(nodeIsSSL, "green") : utils.colorLine(nodeIsSSL, "red");
            
            // If the node has cache, color it red. Else, color it red.
            String nodeHasCache = Boolean.toString(node.getBoolean("cache"));
            nodeHasCache = (nodeHasCache.equals("true")) ? utils.colorLine(nodeHasCache, "green") : utils.colorLine(nodeHasCache, "red");
            
            row.add(nodeName);
            row.add(nodeUrl);
            row.add(nodePort);
            row.add(nodeIsSSL);
            row.add(nodeHasCache);

            rowsList.add(row);

        }

        String tableOfNodes = table.generateTable(headersList, rowsList);
        return tableOfNodes;

    }
}