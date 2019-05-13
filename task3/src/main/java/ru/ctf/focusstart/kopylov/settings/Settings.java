package ru.ctf.focusstart.kopylov.settings;

import javax.swing.*;
import java.awt.*;

public class Settings {
    private JFrame settingsFrame;

    public Settings(){
        settingsFrame = new JFrame("Settings");

        JPanel mainPanel = new JPanel();

        JPanel fieldParams = new JPanel();

        JLabel heightLabel = new JLabel("Высота");
        JLabel widthLabel = new JLabel("Ширина");
        JLabel bombCountLabel = new JLabel("Количество бомб");

        JTextField heightInput = new JTextField();
        JTextField widthInput = new JTextField();
        JTextField bombCountInput = new JTextField();

        fieldParams.add(heightLabel);
        fieldParams.add(heightInput);
        fieldParams.add(widthLabel);
        fieldParams.add(widthInput);
        fieldParams.add(bombCountLabel);
        fieldParams.add(bombCountInput);

        fieldParams.setLayout(new GridLayout(3, 2));

        JPanel buttonsPanel = new JPanel();

        JButton accept = new JButton("Accept");
        accept.addActionListener((args) -> settingsFrame.dispose());
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener((args) -> settingsFrame.dispose());

        buttonsPanel.add(accept);
        buttonsPanel.add(cancel);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(fieldParams, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        settingsFrame.add(mainPanel);

        settingsFrame.pack();
        settingsFrame.dispose();
    }

    public void open() {
        settingsFrame.setVisible(true);
    }

    public void close() {
        settingsFrame.dispose();
    }
}
