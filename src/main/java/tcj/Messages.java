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

import static org.fusesource.jansi.Ansi.*;

public class Messages {

    String versionMsg;
    String helpMsg;
    String welcomeMsg;
    String asciiMsg;
    String licenseMsg;

    public Messages() {

      String[] versionStrings = {
        "TRTL CLI java", 
        "Made by Sajo8", 
        "Based off of TRTL CLI made by mrrovot, zack796, and brandonlehman", 
        "Version 1.0.0"};
      versionMsg = colorMessage(versionStrings, "yellow");

      String[] helpStrings = {
        "Usage: [options]", 
        "\n", 
        "TRTL CLI java", 
        "\n", 
        "Options", 
        "\n", 
        "version|v  output this version number", 
        "help|h     output this help message", 
        "\n",
        "Commands:",
        "\n",
        "market|m         List market data",
        "supply|s         Lists circulating supply",
        "network|n        Shows network data",
        "price|p [qty]    Gives current price information",
        "ascii|a [pic]    Displays ASCII art",
        "ascii list|al    Displays a list of ASCII art",
        "checkpoints|c    Gets latest checkpoint update",
        "nodes|no         Displays a table of available remote nodes",
        "tx|t [hash]      Displays information on mempool/given tx hash",
        "pools|po [pool]  Displays a table of all TurtleCoin mining pools",
        "\n",
        "license|l        Show license information",
        "exit|quit|e|q    Exit TRTL CLI java"
      };
      helpMsg = colorMessage(helpStrings, "yellow");

      String welcomeStrings[] = {
        "Welcome to TRTL CLI java!",
        "\n",
        "Options:",
        "\n",
        "version|v  output the version number",
        "help|h     output the help message",
        "license|l  show license information"
      };
      welcomeMsg = colorMessage(welcomeStrings, "green");

      String asciiStrings[] = {
        "Available ASCII art:",
        "\n",
        "flyingturtle",
        "happyturtle",
        "pineapple",
        "seaturtle",
        "snail",
        "swanson",
        "TRTL",
        "turtle",
        "turtlefighter",
        "walker"
      };
      asciiMsg = colorMessage(asciiStrings, "yellow");

      String licenseStrings[] = {
        "TRTL CLI java Copyright (C) 2019 Sajo8",
        "This program comes with ABSOLUTELY NO WARRANTY",
        "This is free software, and you are welcome to redistribute it",
        "under certain conditions"
      };
      licenseMsg = colorMessage(licenseStrings, "yellow");
    }

    /* Function which takes a list of strings, then colors them and returns it with newlines
    * Only adds one newline after each string
    * If you need more than that, then add each additional one needed in the list itself
    * Ex:
    * If you need one line after the other, just put strings
    * If you want one fully blank line then add 1 "\n" in the list passed
    * Beginning newline _not_ required */
    private String colorMessage(String[] messages, String color) { 
      String formattedMessage;
      String coloredMessage = "\n";
      for (String s: messages) {       
        // If an extra newline is needed, then just added and move onto next string
        if (s == "\n") {
          coloredMessage += "\n";
          continue;
        }    
        formattedMessage = String.format("@|%s %s|@", color, s);
        coloredMessage += ansi().render(formattedMessage).toString()
        ;
        coloredMessage += "\n";
      }
    return coloredMessage;
  }
}