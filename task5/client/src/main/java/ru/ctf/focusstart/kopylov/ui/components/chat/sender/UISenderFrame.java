package ru.ctf.focusstart.kopylov.ui.components.chat.sender;

import ru.ctf.focusstart.kopylov.logic.ClientManager;
import ru.ctf.focusstart.kopylov.logic.sender.SenderListener;
import ru.ctf.focusstart.kopylov.ui.Palette;
import ru.ctf.focusstart.kopylov.ui.dragon.components.DragonButton;
import ru.ctf.focusstart.kopylov.ui.dragon.components.DragonInput;
import ru.ctf.focusstart.kopylov.ui.dragon.components.DragonPanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UISenderFrame extends DragonPanel implements SenderListener {
    private ClientManager manager;

    private DragonInput input = new DragonInput();
    private DragonButton submitButton = new DragonButton("Send");

    public UISenderFrame(ClientManager manager) {
        this.manager = manager;
        build();
        this.manager.addSenderListener(this);
        input.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    submit();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void build() {
        setBackground(Palette.PRIMARY);
        setPreferredSize(new Dimension(200, 60));

        submitButton.setPreferredSize(new Dimension(70, 60));
        submitButton.setBackground(Palette.SECONDARY_DARK);

        GridBagLayout layout = new GridBagLayout();

        setLayout(layout);

        GridBagConstraints inputConstraint = new GridBagConstraints();
        inputConstraint.fill = GridBagConstraints.BOTH;
        inputConstraint.anchor = GridBagConstraints.NORTH;
        inputConstraint.gridx = 0;
        inputConstraint.gridy = 0;
        inputConstraint.weightx = 1.0;

        GridBagConstraints submitConstraint = new GridBagConstraints();
        submitConstraint.gridx = 1;
        submitConstraint.gridy = 0;

        submitButton.addActionListener(e -> submit());

        add(input, inputConstraint);
        add(submitButton, submitConstraint);
    }

    private void submit() {
        submitButton.setEnabled(false);
        manager.sendMessage(input.getText());
    }

    @Override
    public void handleMessageSentEvent() {
        input.setText("");
        submitButton.setEnabled(true);
    }
}
