package ru.ctf.focusstart.kopylov.ui.components.chat.messages;

import ru.ctf.focusstart.kopylov.Message;
import ru.ctf.focusstart.kopylov.logic.ClientManager;
import ru.ctf.focusstart.kopylov.logic.receiver.ReceiverMessageListener;
import ru.ctf.focusstart.kopylov.ui.Palette;
import ru.ctf.focusstart.kopylov.ui.components.chat.messages.message.UIMessage;
import ru.ctf.focusstart.kopylov.ui.components.chat.messages.message.UISystemMessage;
import ru.ctf.focusstart.kopylov.ui.dragon.components.DragonPanel;

import javax.swing.*;
import java.awt.*;

public class UIMessagesFrame extends DragonPanel implements ReceiverMessageListener {
    private DragonPanel panel = new DragonPanel();
    private JScrollPane scrollPane = new JScrollPane(panel);
    private GridBagConstraints constraints = new GridBagConstraints();
    private int messageCounter = 0;

    {
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(2, 4, 2, 4);
        constraints.weightx = 1.0;
        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.fill = GridBagConstraints.BOTH;
    }

    public UIMessagesFrame(ClientManager manager) {
        build();


        manager.addMessageListener(this);
    }

    private void build() {
        setLayout(new BorderLayout());
        panel.setLayout(new GridBagLayout());

        panel.setBackground(Palette.PRIMARY);
        panel.setForeground(Palette.TEXT_ON_PRIMARY);

        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void addNewMessage(String username, String message, String time) {
        constraints.gridy = messageCounter;

        panel.add(new UIMessage(username, message, time), constraints);
        messageCounter++;
        validate();
        repaint();
    }

    private void addNewSystemMessage(String message, String time) {
        constraints.gridy = messageCounter;

        panel.add(new UISystemMessage(message, time), constraints);
        messageCounter++;
        validate();
        repaint();
    }

    @Override
    public void handleNewServerMessageEvent(Message message) {
        addNewMessage(message.getAuthor(), message.getMessage(), message.getTime());
    }

    @Override
    public void handleNewMessageEvent(Message message) {
        addNewSystemMessage(message.getMessage(), message.getTime());
    }
}
