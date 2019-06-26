package ru.ctf.focusstart.kopylov.ui.components.landing;

import ru.ctf.focusstart.kopylov.logic.ClientManager;
import ru.ctf.focusstart.kopylov.ui.Palette;
import ru.ctf.focusstart.kopylov.ui.components.landing.login.UILoginListener;
import ru.ctf.focusstart.kopylov.ui.components.landing.login.UILoginFrame;
import ru.ctf.focusstart.kopylov.ui.components.landing.options.UIOptionsFrame;
import ru.ctf.focusstart.kopylov.ui.components.landing.options.UIOptionsLoginListener;
import ru.ctf.focusstart.kopylov.ui.dragon.components.DragonPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UILandingFrame extends DragonPanel implements UIOptionsLoginListener, UILoginListener {
    private enum LandingFrameState {
        LOGIN_OPTIONS,
        LOGIN_WITH_NAME_FRAME
    }

    private List<UILandingListener> listeners = new ArrayList<>();

    private UILoginFrame loginFrame;
    private UIOptionsFrame optionsFrame = new UIOptionsFrame();

    public UILandingFrame(ClientManager manager) {
        loginFrame = new UILoginFrame(manager);
        build();
        setState(LandingFrameState.LOGIN_OPTIONS);
    }

    private void build() {
        setBackground(Palette.PRIMARY_LIGHT);

        setLayout(new OverlayLayout(this));

        add(optionsFrame);
        add(loginFrame);

        optionsFrame.addOptionsListener(this);
        loginFrame.addLoginListener(this);
    }

    public void addUILandingListener(UILandingListener listener) {
        listeners.add(listener);
    }

    @Override
    public void handleLoginButtonPressed() {
        setState(LandingFrameState.LOGIN_WITH_NAME_FRAME);
    }

    @Override
    public void handleCancelLoginButtonPressed() {
        setState(LandingFrameState.LOGIN_OPTIONS);
    }

    private void setState(LandingFrameState state) {
        switch (state) {
            case LOGIN_OPTIONS:
                optionsFrame.showD();
                loginFrame.hideD();
                break;
            case LOGIN_WITH_NAME_FRAME:
                loginFrame.showD();
                optionsFrame.hideD();
                break;
        }
    }
}
