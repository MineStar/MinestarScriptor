/*
 * Copyright (C) 2012 MineStar.de 
 * 
 * This file is part of MinestarScriptor.
 * 
 * MinestarScriptor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 * 
 * MinestarScriptor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with MinestarScriptor.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.minestar.handler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Pattern;

public class CommandScanner {

    private URL projectSite;
    private String projectName;

    public CommandScanner(URL projectSite, String projectName) {
        this.projectSite = projectSite;
        this.projectName = projectName;
    }

    private final static String LINE_SEPARATOR = System.getProperty("line.separator");

    public String scan() {
        StringBuilder sBuilder = new StringBuilder("Dokumenation vom Projekt '" + projectName + "':");
        sBuilder.append(LINE_SEPARATOR);

        try {
            System.out.println("Open Stream to URL " + projectSite);
            BufferedReader bReader = new BufferedReader(new InputStreamReader(projectSite.openStream()));
            System.out.println("Stream opened, start parsing file");
            String line = "";
            boolean foundCommands = false;
            while ((line = bReader.readLine()) != null) {
                if (!foundCommands && line.contains("cmdList = new CommandList"))
                    foundCommands = true;

                else if (foundCommands) {
                    if (line.contains("return true;"))
                        break;
                    else {
                        line = line.trim();
                        if (line.isEmpty() || line.startsWith(")") || line.startsWith("//") || line.equals(");"))
                            continue;
                        line = tidyUp(line);
                        sBuilder.append(parseCommand(line));
                        sBuilder.append(LINE_SEPARATOR);
                    }

                }
            }

            bReader.close();
            System.out.println("Finished URL");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sBuilder.length() ;
    }

    private String tidyUp(String string) {
        int start = string.indexOf("(");
        int end = string.indexOf(")");
        if (start <= 0 || end <= 0 || start >= end) {
            System.out.println("Fehler bei : " + string);
            return string;
        } else
            return string.substring(string.indexOf("(") + 1, string.indexOf(")"));
    }
    private static final Pattern P = Pattern.compile("\"");

    private String parseCommand(String commandLine) {
        String split[] = P.split(commandLine);
        StringBuilder sBuilder = new StringBuilder();
        if (split.length < 3)
            return commandLine;

        // SYNTAX
        sBuilder.append("Syntax = '");
        sBuilder.append(split[1]);
        sBuilder.append("\'");

        // ARGUMENTS
        sBuilder.append("\tArgumente = '");
        if (split.length >= 4)
            sBuilder.append(split[3]);
        sBuilder.append("\'");

        // PERMISSIONS
        sBuilder.append("\tPermission = '");
        if (split.length >= 6)
            sBuilder.append(split[5]);
        sBuilder.append('\'');

        return sBuilder.toString();
    }
}
