package ru.ctf.focusstart.kopylov.UIComponents.mainwindow;

import ru.ctf.focusstart.kopylov.UIComponents.Palette;
import ru.ctf.focusstart.kopylov.UIComponents.scoreboard.UIScoreboard;
import ru.ctf.focusstart.kopylov.UIComponents.settings.UISettings;

import javax.swing.*;

class UIMenu {
    private JMenu menu;
    UIMenu() {
        menu = new JMenu("Options");
        menu.setOpaque(true);
        menu.setBackground(Palette.getHeadColor());
        menu.setForeground(Palette.getTextColor());
        menu.setBorderPainted(false);
        menu.getPopupMenu().setOpaque(true);
        menu.getPopupMenu().setBackground(Palette.getHeadColor());
        menu.getPopupMenu().setForeground(Palette.getTextColor());
        menu.setBorderPainted(false);

        JMenuItem settingsItem = new JMenuItem("Settings");
        settingsItem.setOpaque(true);
        settingsItem.setBackground(Palette.getHeaderBackgroundColor());
        settingsItem.setForeground(Palette.getTextColor());
        settingsItem.setBorderPainted(false);
        settingsItem.addActionListener((args) -> {
            UISettings settings = new UISettings();
            settings.open();
        });

        JMenuItem scoreboardItem = new JMenuItem("Scoreboard");
        scoreboardItem.setOpaque(true);
        scoreboardItem.setBackground(Palette.getHeaderBackgroundColor());
        scoreboardItem.setForeground(Palette.getTextColor());
        scoreboardItem.setBorderPainted(false);
        scoreboardItem.addActionListener((args) -> {
            UIScoreboard scoreboard = new UIScoreboard();
            scoreboard.open();
        });

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setOpaque(true);
        exitItem.setBackground(Palette.getHeaderBackgroundColor());
        exitItem.setForeground(Palette.getTextColor());
        exitItem.setBorderPainted(false);
        exitItem.addActionListener(e -> System.exit(0));

        menu.add(settingsItem);
        menu.add(scoreboardItem);
        menu.add(exitItem);
    }

    JMenu getMenu() {
        return menu;
    }
}
