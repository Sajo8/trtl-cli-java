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

public class Utils {
    
    /* Function which takes a list of strings, then colors them and returns it with newlines
    * Only adds one newline after each string
    * If you need more than that, then add each additional one needed in the list itself
    * Ex:
    * If you need one line after the other, just put strings
    * If you want one fully blank line then add 1 "\n" in the list passed
    * Beginning newline _not_ required */
    public String colorMessage(String[] messages, String color) { 
        String formattedMessage;
        String coloredMessage = "\n";
        for (String s: messages) {       
          // If an extra newline is needed, then just add it and move onto next string
          if (s == "\n") {
            coloredMessage += "\n";
            continue;
          }    
          formattedMessage = String.format("@|%s %s|@", color, s);
          coloredMessage += ansi().render(formattedMessage).toString();
          coloredMessage += "\n";
        }
      return coloredMessage;
    }

    // Same as colorMessage(), but for a single line only
    // No newlines added
    public String colorLine(String message, String color) {
      String formattedMessage;
      String coloredMessage = "";
      formattedMessage = String.format("@|%s %s|@", color, message);
      coloredMessage += ansi().render(formattedMessage).toString();
      return coloredMessage;
    }

}