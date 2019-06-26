package ru.ctf.focusstart.kopylov.ui.components.chat.online;

import ru.ctf.focusstart.kopylov.logic.ClientManager;
import ru.ctf.focusstart.kopylov.logic.receiver.ReceiverUsersOnlineListener;
import ru.ctf.focusstart.kopylov.ui.Palette;
import ru.ctf.focusstart.kopylov.ui.dragon.components.DragonList;
import ru.ctf.focusstart.kopylov.ui.dragon.components.DragonPanel;

import java.awt.*;
import java.util.List;

public class UIOnlineFrame extends DragonPanel implements ReceiverUsersOnlineListener {
    private DragonList list = new DragonList();

    public UIOnlineFrame(ClientManager manager) {
        manager.addUserOnlineListener(this);

        build();
    }

    private void build() {
        setMinimumSize(new Dimension(100,10));
        setBackground(Palette.PRIMARY_LIGHT);

        list.setBackgroundD(Palette.PRIMARY_LIGHT);
        list.setForegroundD(Palette.TEXT_ON_PRIMARY);

        add(list);
    }

    @Override
    public void handleSetUsersList(List<String> users) {
        for (String user : users) {
            list.addD(user);
        }
        validate();
        repaint();
    }

    @Override
    public void handleAddToUsersList(String user) {
        list.addD(user);
        validate();
        repaint();
    }

    @Override
    public void handleRemoveFromUsersList(String user) {
        list.removeD(user);
        validate();
        repaint();
    }
}
