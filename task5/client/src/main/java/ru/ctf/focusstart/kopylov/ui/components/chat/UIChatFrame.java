package ru.ctf.focusstart.kopylov.ui.components.chat;

import ru.ctf.focusstart.kopylov.logic.ClientManager;
import ru.ctf.focusstart.kopylov.logic.connection.ConnectionBrokenListener;
import ru.ctf.focusstart.kopylov.logic.connection.ConnectionListener;
import ru.ctf.focusstart.kopylov.ui.Palette;
import ru.ctf.focusstart.kopylov.ui.components.chat.header.UIHeaderFrame;
import ru.ctf.focusstart.kopylov.ui.components.chat.header.UILogoutListener;
import ru.ctf.focusstart.kopylov.ui.components.chat.messages.UIMessagesFrame;
import ru.ctf.focusstart.kopylov.ui.components.chat.online.UIOnlineFrame;
import ru.ctf.focusstart.kopylov.ui.components.chat.sender.UISenderFrame;
import ru.ctf.focusstart.kopylov.ui.dragon.components.DragonPanel;

import java.awt.*;

public class UIChatFrame extends DragonPanel implements ConnectionListener {
    private enum ChatFrameState {
        OK,
        CONNECTION_ERR
    }

    private UIHeaderFrame header;
    private UIOnlineFrame online;
    private UIMessagesFrame messages;
    private UISenderFrame sender;

    public UIChatFrame(ClientManager manager) {
        header = new UIHeaderFrame(manager);
        online = new UIOnlineFrame(manager);
        messages = new UIMessagesFrame(manager);
        sender = new UISenderFrame(manager);
        build();
    }

    private void build() {
        setBackground(Palette.PRIMARY_DARK);

        setLayout(new BorderLayout());

        online.setPreferredSize(new Dimension( 100, 200));

        add(header, BorderLayout.NORTH);
        add(online, BorderLayout.WEST);
        add(messages, BorderLayout.CENTER);
        add(sender, BorderLayout.SOUTH);
    }

    private void setState(ChatFrameState state) {
        switch (state) {
            case OK:
                sender.setEnabled(true);
                break;
            case CONNECTION_ERR:
                sender.setEnabled(false);
                break;
        }
    }

    public void addLogoutListener(UILogoutListener listener) {
        header.addListener(listener);
    }

    @Override
    public void handleConnectionEstablishEvent() {
        setState(ChatFrameState.OK);
    }

    @Override
    public void handleConnectionProblemEvent() {
        setState(ChatFrameState.CONNECTION_ERR);
    }
}
