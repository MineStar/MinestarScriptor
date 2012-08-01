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

package de.minestar.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class Settings {

    private static Map<String, URL> projects = new TreeMap<String, URL>();

    private Settings() {

    }

    private static final Pattern P = Pattern.compile(": ");

    private static void load(File configFile) {
        try {
            BufferedReader bReader = new BufferedReader(new FileReader(configFile));
            String line = "";
            String[] split;
            while ((line = bReader.readLine()) != null) {
                split = P.split(line);
                projects.put(split[0], new URL(split[1]));
            }

            bReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> getProjects() {
        return new ArrayList<String>(projects.keySet());
    }

    public static URL getProjectURL(String project) {
        return projects.get(project);
    }

    public static void init(File configFile) {
        load(configFile);
    }

}
