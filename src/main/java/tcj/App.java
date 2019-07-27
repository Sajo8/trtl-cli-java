package tcj;

import kong.unirest.Unirest;
import kong.unirest.JsonNode;
import kong.unirest.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import tcj.Messages;

import java.util.Scanner; 

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
        Scanner reader = new Scanner(System.in);

        while (true) {

            if (app.firstRun) {
                System.out.println(app.msgs.licenseMsg);
                System.out.println(app.msgs.welcomeMsg);
                app.firstRun = false;
            }
            
            String s;
            System.out.print("> ");
            s = reader.nextLine();
    
            if (s.equals("h")) {
                System.out.println(app.msgs.helpMsg);
            } else if (s.equals("v")) {
                System.out.println(app.msgs.versionMsg);
            } else if (s.equals("l")) {
                System.out.println(app.msgs.licenseMsg);
            } else if (s.equals("n")) {
                HttpResponse<JsonNode> jsonResponse = Unirest.get("https://raw.githubusercontent.com/turtlecoin/turtlecoin-pools-json/master/v2/turtlecoin-pools.json")
                    .asJson();
                JSONObject actualJSON = jsonResponse.getBody().getObject();
                JSONArray thePools = actualJSON.getJSONArray("pools");
                System.out.println(thePools.toString(4));
            }

            if (s.equals("close")) {
                System.out.println("Bye!");
                reader.close();
                System.exit(0);
            }
        }
    }
}