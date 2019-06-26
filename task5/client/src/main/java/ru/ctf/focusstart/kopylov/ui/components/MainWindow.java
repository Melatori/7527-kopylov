package ru.ctf.focusstart.kopylov.ui.components;

import ru.ctf.focusstart.kopylov.logic.ClientManager;
import ru.ctf.focusstart.kopylov.logic.user.UserAuthorizationListener;
import ru.ctf.focusstart.kopylov.ui.components.chat.UIChatFrame;
import ru.ctf.focusstart.kopylov.ui.components.landing.UILandingFrame;
import ru.ctf.focusstart.kopylov.ui.dragon.components.DragonPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainWindow extends JFrame implements UserAuthorizationListener {
    private enum MainWindowState {
        LOGIN,
        CHAT
    }

    private final DragonPanel mainPanel = new DragonPanel();
    private final UIChatFrame chatFrame;
    private final UILandingFrame landingFrame;

    public MainWindow(ClientManager manager) {
        manager.addAuthorizationListener(this);

        chatFrame = new UIChatFrame(manager);
        landingFrame = new UILandingFrame(manager);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                manager.shutdownConnection();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setMinimumSize(new Dimension(200,200));
        setPreferredSize(new Dimension(400,600));

        build();

        setResizable(true);
        setVisible(true);
    }

    private void build() {

        mainPanel.setBackground(Color.RED);

        mainPanel.add(chatFrame);
        mainPanel.add(landingFrame);

        mainPanel.setLayout(new OverlayLayout(mainPanel));
        add(mainPanel);

        setState(MainWindowState.LOGIN);

        pack();
    }

    @Override
    public void handleAuthorizationCompleteEvent() {
        setState(MainWindowState.CHAT);
    }

    @Override
    public void handleAuthorizationFailedEvent() {
        setState(MainWindowState.LOGIN);
    }

    private void setState(MainWindowState state) {
        switch (state) {
            case LOGIN:
                landingFrame.showD();
                chatFrame.hideD();
                break;
            case CHAT:
                chatFrame.showD();
                landingFrame.hideD();
                break;
        }
    }
}
