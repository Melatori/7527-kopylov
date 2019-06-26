package ru.ctf.focusstart.kopylov.ui.components.chat.messages.message;

import ru.ctf.focusstart.kopylov.ui.Palette;
import ru.ctf.focusstart.kopylov.ui.dragon.components.DragonLabel;
import ru.ctf.focusstart.kopylov.ui.dragon.components.DragonPanel;

import javax.swing.*;
import java.awt.*;

public class UIMessage extends DragonPanel {
    public UIMessage(String username, String message, String time) {
        setBackground(Palette.SECONDARY);

        DragonLabel usernameLabel = new DragonLabel(username + ": ");
        usernameLabel.setForeground(Palette.TEXT_ON_SECONDARY);
        DragonLabel messageLabel = new DragonLabel(message);
        messageLabel.setForeground(Palette.TEXT_ON_SECONDARY);
        DragonLabel timeLabel = new DragonLabel(time);
        timeLabel.setForeground(Palette.TEXT_ON_SECONDARY);
        DragonPanel messagePanel = new DragonPanel();
        messagePanel.setOpaque(false);
        DragonPanel timePanel = new DragonPanel();
        timePanel.setOpaque(false);

        setLayout(new BorderLayout());
        messagePanel.setLayout(new BorderLayout());
        timePanel.setLayout(new BorderLayout());

        messagePanel.add(usernameLabel, BorderLayout.WEST);
        messagePanel.add(messageLabel, BorderLayout.CENTER);

        timePanel.add(timeLabel, BorderLayout.EAST);

        add(messagePanel, BorderLayout.NORTH);
        add(timePanel, BorderLayout.SOUTH);
    }
}
