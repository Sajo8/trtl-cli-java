package tcj;

// To do GET requests
import kong.unirest.Unirest;
import kong.unirest.JsonNode;
import kong.unirest.HttpResponse;

// To parse json
import org.json.JSONArray;
import org.json.JSONObject;

// To make a table to format output
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

import tcj.Utils;

public class Nodes {

    static Utils utils = new Utils();

    public static void main(String[] args) {

        AsciiTable table = new AsciiTable();
        table.setTextAlignment(TextAlignment.CENTER);
        
        table.addRule();
        table.addRow("Name", "URL", "Port", "SSL", "Cache");
        table.addRule();

        // Get data
        HttpResponse<JsonNode> jsonResponse = Unirest.get("https://raw.githubusercontent.com/turtlecoin/turtlecoin-nodes-json/master/turtlecoin-nodes.json")
            .asJson();
        // Extract json
        JSONObject actualJSON = jsonResponse.getBody().getObject();
        // Get the list of nodes
        JSONArray nodesList = actualJSON.getJSONArray("nodes");

        for (int i = 0; i < nodesList.length(); i++) {
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
            
            System.out.println(nodeName);
            System.out.println(nodeUrl);
            System.out.println(nodePort);
            System.out.println(nodeIsSSL);
            System.out.println(nodeHasCache);
            table.addRule();
        }
        String rend = table.render();
        // System.out.println(rend);
                    
    }

}