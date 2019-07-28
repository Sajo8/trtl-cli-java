package tcj;

// Get user input
import java.util.Scanner; 
// Parse user input
import java.util.Arrays;

import tcj.Messages;
import tcj.Utils;

public class App 
{
    boolean firstRun;
    
    static Messages msgs = new Messages();;
    static Utils utils = new Utils();

    static String[] helpCommands = {"help", "h"}; 
    static String[] versionCommands = {"version", "v"};
    
    static String[] marketCommands = {"market", "m"};
    static String[] supplyCommands = {"supply", "input"};
    static String[] networkCommands = {"network", "n"};
    static String[] priceCommands = {"price", "p"};
    static String[] asciiCommands = {"ascii", "a"};
    static String[] asciiListCommands = {"ascii list", "al"};
    static String[] checkpointsCommands = {"checkpoints", "c"};
    static String[] nodesCommands = {"nodes", "no"};
    static String[] txCommands = {"tx", "t"};
    static String[] poolCommands = {"pools", "po"};

    static String[] licenseCommands = {"license", "l"};
    static String[] exitCommands = {"exit", "quit", "e", "q"};
    


    public static void main( String[] args )
    {
        
        System.out.println(msgs.licenseMsg);
        System.out.println(msgs.welcomeMsg);

        Scanner reader = new Scanner(System.in);

        while (true) {
            
            // Get input
            System.out.print("> ");
            String input = reader.nextLine();
            // Trim trailing and leading blank spaces
            input = input.toLowerCase().strip();
            // Restart if empty
            if (input == "") {
                continue;
            }
            // Split it into a list
            String[] split_input = input.split(" ");
            // First index is main command, 2nd is the args
            String command = split_input[0];
            try {
                String commandArgs = split_input[1];
            } catch(Exception e) {

            }
            
            
            if (Arrays.asList(helpCommands).contains(command)) {
            
                System.out.println(msgs.helpMsg);
            
            } else if (Arrays.asList(versionCommands).contains(command)) {
            
                System.out.println(msgs.versionMsg);
            
            } else if (Arrays.asList(licenseCommands).contains(command)) {
            
                System.out.println(msgs.licenseMsg);
            
            } else if (Arrays.asList(nodesCommands).contains(command)) {

    
            } else if (Arrays.asList(exitCommands).contains(command)) {
            
                System.out.println("Bye!");
                reader.close();
                System.exit(0);
            
            } else {
                
                // Format string with user input
                String errorMsg = String.format("Sorry, input not recognized: \"%s\"", input);

                // Color it red and print it out
                errorMsg = utils.colorLine(errorMsg, "red");
                System.out.println(errorMsg + "\n");
            }
        }
    }
}