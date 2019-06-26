package ru.ctf.focusstart.kopylov.ui.components.landing.login;

import ru.ctf.focusstart.kopylov.logic.ClientManager;
import ru.ctf.focusstart.kopylov.logic.user.UserListener;
import ru.ctf.focusstart.kopylov.ui.Palette;
import ru.ctf.focusstart.kopylov.ui.dragon.components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class UILoginFrame extends DragonPanel implements UserListener {
    private ClientManager manager;

    private enum LoginState {
        OK,
        WRONG_NAME,
        WRONG_SERVER_ADDRESS,
        NAME_OCCUPIED,
        CONNECTION_PROBLEM
    }

    private List<UILoginListener> listeners = new ArrayList<>();

    private DragonInput loginInput = new DragonInput();
    private DragonAddressInput addressInput = new DragonAddressInput();
    private DragonLabel errLabel = new DragonLabel();

    {
        errLabel.setForeground(Palette.ERR_TEXT);
    }

    private DragonButton confirmButton = new DragonButton("Enter");
    private DragonButton cancelButton = new DragonButton("Cancel");

    public UILoginFrame(ClientManager manager) {
        this.manager = manager;
        build();
        this.manager.addUserListener(this);
        this.manager.addConnectionListener(this);

        loginInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    handleConfirmButton();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        addressInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    handleConfirmButton();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        setState(LoginState.OK);
    }

    private void build() {
        setBackground(Palette.PRIMARY_LIGHT);

        setLayout(new GridBagLayout());

        JLabel nameLabel = new JLabel("Username");
        JLabel addressLabel = new JLabel("Address");

        GridBagConstraints nameLabelConstraints = new GridBagConstraints();
        nameLabelConstraints.fill = GridBagConstraints.HORIZONTAL;
        nameLabelConstraints.gridy = 0;
        nameLabelConstraints.gridwidth = 2;

        GridBagConstraints nameInputConstraints = new GridBagConstraints();
        nameInputConstraints.fill = GridBagConstraints.HORIZONTAL;
        nameInputConstraints.gridy = 1;
        nameInputConstraints.gridwidth = 2;

        GridBagConstraints addressLabelConstraints = new GridBagConstraints();
        addressLabelConstraints.fill = GridBagConstraints.HORIZONTAL;
        addressLabelConstraints.gridy = 2;
        addressLabelConstraints.gridwidth = 2;

        GridBagConstraints addressInputConstraints = new GridBagConstraints();
        addressInputConstraints.fill = GridBagConstraints.HORIZONTAL;
        addressInputConstraints.gridy = 3;
        addressInputConstraints.gridwidth = 2;

        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.gridy = 4;
        labelConstraints.gridwidth = 2;
        labelConstraints.insets.bottom = 4;

        GridBagConstraints buttonConstraint = new GridBagConstraints();
        buttonConstraint.fill = GridBagConstraints.HORIZONTAL;
        buttonConstraint.gridy = 5;

        cancelButton.addActionListener(e -> handleCancelButton());
        confirmButton.addActionListener((e) -> handleConfirmButton());

        add(nameLabel, nameLabelConstraints);
        add(loginInput, nameInputConstraints);
        add(addressLabel, addressLabelConstraints);
        add(addressInput, addressInputConstraints);
        add(errLabel, labelConstraints);
        add(confirmButton, buttonConstraint);
        add(cancelButton, buttonConstraint);
    }

    public void addLoginListener(UILoginListener listener) {
        listeners.add(listener);
    }

    @Override
    public void handleUsernameOccupiedEvent() {
        loginInput.setEnabled(true);
        confirmButton.setEnabled(true);
        cancelButton.setEnabled(true);
        setState(LoginState.NAME_OCCUPIED);
    }

    @Override
    public void handleUsernameReceivedEvent() {
    }

    @Override
    public void handleConnectionEstablishEvent() {
        setState(LoginState.OK);
    }

    @Override
    public void handleConnectionProblemEvent() {
        loginInput.setEnabled(true);
        addressInput.setEnabled(true);
        confirmButton.setEnabled(true);
        cancelButton.setEnabled(true);
        setState(LoginState.CONNECTION_PROBLEM);
    }

    private void handleConfirmButton() {
        if (loginInput.getText().contains(" ") || loginInput.getText().equals("")) {
            setState(LoginState.WRONG_NAME);
            return;
        }
        if (!addressInput.isEditValid()) {
            setState(LoginState.WRONG_SERVER_ADDRESS);
            return;
        }
        setState(LoginState.OK);
        loginInput.setEnabled(false);
        addressInput.setEnabled(false);
        confirmButton.setEnabled(false);
        cancelButton.setEnabled(false);
        manager.setConnection(addressInput.getText(), loginInput.getText());
    }

    private void handleCancelButton() {
        for (UILoginListener listener : listeners) {
            listener.handleCancelLoginButtonPressed();
        }
    }

    private void setState(LoginState state) {
        switch (state) {
            case OK:
                errLabel.hideD();
                break;
            case WRONG_NAME:
                errLabel.showD();
                errLabel.setText("Wrong name");
                break;
            case WRONG_SERVER_ADDRESS:
                errLabel.showD();
                errLabel.setText("Wrong server address");
            case NAME_OCCUPIED:
                errLabel.showD();
                errLabel.setText("Name already taken");
                break;
            case CONNECTION_PROBLEM:
                errLabel.showD();
                errLabel.setText("Connection problem");
                break;
        }
    }
}
