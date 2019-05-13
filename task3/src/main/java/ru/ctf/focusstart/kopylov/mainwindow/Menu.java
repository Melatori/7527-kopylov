package ru.ctf.focusstart.kopylov.mainwindow;

import ru.ctf.focusstart.kopylov.settings.Settings;

import javax.swing.*;

class Menu {
    private JMenu menu;
    public Menu() {
        menu = new JMenu("Options");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        JMenuItem settingsItem = new JMenuItem("Settings");
        settingsItem.addActionListener((args) -> {
            Settings settings = new Settings();
            settings.open();
        });

        menu.add(settingsItem);
        menu.add(exitItem);
    }

    JMenu getMenu() {
        return menu;
    }
}
