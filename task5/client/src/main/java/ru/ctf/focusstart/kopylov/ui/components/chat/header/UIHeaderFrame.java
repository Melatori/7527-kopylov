package ru.ctf.focusstart.kopylov.ui.components.chat.header;

import ru.ctf.focusstart.kopylov.logic.ClientManager;
import ru.ctf.focusstart.kopylov.logic.connection.ConnectionListener;
import ru.ctf.focusstart.kopylov.ui.Palette;
import ru.ctf.focusstart.kopylov.ui.dragon.components.DragonButton;
import ru.ctf.focusstart.kopylov.ui.dragon.components.DragonLabel;
import ru.ctf.focusstart.kopylov.ui.dragon.components.DragonPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UIHeaderFrame extends DragonPanel implements ConnectionListener {
    private DragonLabel online = new DragonLabel();

    private List<UILogoutListener> listeners = new ArrayList<>();

    private ClientManager manager;

    public UIHeaderFrame(ClientManager manager) {
        this.manager = manager;
        build();
        this.manager.addConnectionListener(this);
    }

    public void addListener(UILogoutListener listener) {
        listeners.add(listener);
    }

    private void build() {
        setBackground(Palette.PRIMARY_DARK);
        setPreferredSize(new Dimension(200,40));

        online.setForeground(Palette.TEXT_ON_PRIMARY);
        online.setText("Online");

        setLayout(new BorderLayout());

        add(online, BorderLayout.WEST);
    }

    @Override
    public void handleConnectionEstablishEvent() {
        online.setText("Online");
    }

    @Override
    public void handleConnectionProblemEvent() {
        online.setText("Offline");
    }
}
