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

import javax.swing.UIManager;

import de.minestar.gui.MainFrame;

public class Core {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            new MainFrame().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
