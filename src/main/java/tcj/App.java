package tcj;

import kong.unirest.Unirest;
import kong.unirest.JsonNode;
import kong.unirest.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import tcj.Messages;

public class App 
{
    boolean firstRun;
    Messages msgs;

    public App() {
        firstRun = true;
        msgs = new Messages();
    }

    public static void main( String[] args )
    {

        App app = new App();

        if (args.length == 0) {
            System.out.println("Something's wrong, give some params bruh");
            System.exit(1);
        }
        
        if (app.firstRun) {
            System.out.println(app.msgs.welcomeMsg);
            app.firstRun = false;
        }
        
        if (args[0] == "h") {
            System.out.println(app.msgs.helpMsg);
        } else if (args[0] == "v") {
            System.out.println(app.msgs.versionMsg);
        } else if (args[0] == "l") {
            System.out.println(app.msgs.licenseMsg);
        } else if (args[0] == "p") {
            HttpResponse<JsonNode> jsonResponse = Unirest.get("https://raw.githubusercontent.com/turtlecoin/turtlecoin-pools-json/master/v2/turtlecoin-pools.json")
                .asJson();
            JSONObject actualJSON = jsonResponse.getBody().getObject();
            JSONArray thePools = actualJSON.getJSONArray("pools");
            System.out.println(thePools.toString(4));
        }
    }
}