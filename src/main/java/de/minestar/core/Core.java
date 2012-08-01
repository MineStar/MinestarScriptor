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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import javax.swing.UIManager;

import de.minestar.handler.CommandScanner;

public class Core {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            Settings.init(new File("C:/Users/Meldanor/Desktop/MinestarScriptorTest.txt"));

            List<String> projects = Settings.getProjects();
            CommandScanner scanner = null;

            System.out.println("Scanning all projects");
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(new File("C:/Users/Meldanor/Desktop/MinestarScriptorTestAusgabe.txt")));
            for (String project : projects) {
                scanner = new CommandScanner(Settings.getProjectURL(project), project);
                bWriter.write(scanner.scan());
                bWriter.newLine();
            }
            bWriter.close();

            System.out.println("Finished all projects");
//            new MainFrame().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
