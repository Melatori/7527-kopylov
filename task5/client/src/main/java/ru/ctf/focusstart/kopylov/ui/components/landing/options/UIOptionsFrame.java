package ru.ctf.focusstart.kopylov.ui.components.landing.options;

import ru.ctf.focusstart.kopylov.ui.Palette;
import ru.ctf.focusstart.kopylov.ui.dragon.components.DragonButton;
import ru.ctf.focusstart.kopylov.ui.dragon.components.DragonPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UIOptionsFrame extends DragonPanel {
    private List<UIOptionsLoginListener> listeners = new ArrayList<>();

    private DragonButton loginButton = new DragonButton("Login");

    public UIOptionsFrame() {
        build();
    }

    private void build() {
        setBackground(Palette.PRIMARY_LIGHT);
        setLayout(new GridBagLayout());

        loginButton.addActionListener(e -> handleLoginButton());

        add(loginButton);
    }

    public void addOptionsListener(UIOptionsLoginListener listener) {
        listeners.add(listener);
    }

    private void handleLoginButton() {
        for (UIOptionsLoginListener listener : listeners) {
            listener.handleLoginButtonPressed();
        }
    }
}
