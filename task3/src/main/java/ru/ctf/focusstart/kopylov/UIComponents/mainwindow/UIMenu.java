package ru.ctf.focusstart.kopylov.UIComponents.mainwindow;

import ru.ctf.focusstart.kopylov.UIComponents.Palette;
import ru.ctf.focusstart.kopylov.UIComponents.scoreboard.UIScoreboard;
import ru.ctf.focusstart.kopylov.UIComponents.settings.UISettings;
import ru.ctf.focusstart.kopylov.logic.game.GameManager;

import javax.swing.*;

class UIMenu {
    private JMenu menu;
    private GameManager gameManager;

    UIMenu(GameManager gameManager) {
        this.gameManager = gameManager;
        createMenu();
    }

    private void createMenu() {
        menu = new JMenu("Options");
        menu.setOpaque(true);
        menu.setBackground(Palette.HEADER_COLOR);
        menu.setForeground(Palette.TEXT_COLOR);
        menu.setBorderPainted(false);
        menu.getPopupMenu().setOpaque(true);
        menu.getPopupMenu().setBackground(Palette.HEADER_COLOR);
        menu.getPopupMenu().setForeground(Palette.TEXT_COLOR);
        menu.setBorderPainted(false);

        JMenuItem settingsItem = new JMenuItem("Settings");
        settingsItem.setOpaque(true);
        settingsItem.setBackground(Palette.HEADER_BACKGROUND_COLOR);
        settingsItem.setForeground(Palette.TEXT_COLOR);
        settingsItem.setBorderPainted(false);
        settingsItem.addActionListener((args) -> {
            UISettings settings = new UISettings(gameManager);
            settings.open();
        });

        JMenuItem scoreboardItem = new JMenuItem("Scoreboard");
        scoreboardItem.setOpaque(true);
        scoreboardItem.setBackground(Palette.HEADER_BACKGROUND_COLOR);
        scoreboardItem.setForeground(Palette.TEXT_COLOR);
        scoreboardItem.setBorderPainted(false);
        scoreboardItem.addActionListener((args) -> {
            UIScoreboard scoreboard = new UIScoreboard(gameManager);
            scoreboard.open();
        });

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setOpaque(true);
        exitItem.setBackground(Palette.HEADER_BACKGROUND_COLOR);
        exitItem.setForeground(Palette.TEXT_COLOR);
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
