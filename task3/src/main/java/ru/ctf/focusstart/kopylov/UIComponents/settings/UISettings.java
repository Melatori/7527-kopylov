package ru.ctf.focusstart.kopylov.UIComponents.settings;

import ru.ctf.focusstart.kopylov.UIComponents.Palette;
import ru.ctf.focusstart.kopylov.logic.FieldBuilder;
import ru.ctf.focusstart.kopylov.logic.Game;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;

public class UISettings {
    private JFrame settingsFrame;
    private JTextField heightInput = new JTextField();
    private JTextField widthInput = new JTextField();
    private JTextField bombCountInput = new JTextField();

    public UISettings() {
        settingsFrame = new JFrame("Settings");

        settingsFrame.add(createMainPanel());

        settingsFrame.pack();
        settingsFrame.dispose();
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(true);
        mainPanel.setBackground(Palette.getBackgroundColor());

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(createFieldParamsPanel(), BorderLayout.CENTER);
        mainPanel.add(createButtonsPanel(), BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel createFieldParamsPanel() {
        JPanel fieldParams = new JPanel();
        fieldParams.setOpaque(true);
        fieldParams.setBackground(Palette.getBackgroundColor());

        JLabel heightLabel = new JLabel("Высота");
        heightLabel.setForeground(Palette.getDarkTextColor());
        JLabel widthLabel = new JLabel("Ширина");
        widthLabel.setForeground(Palette.getDarkTextColor());
        JLabel bombCountLabel = new JLabel("Количество бомб");
        bombCountLabel.setForeground(Palette.getDarkTextColor());

        heightInput.setText(String.valueOf(FieldBuilder.getHeight()));
        heightInput.setOpaque(true);
        heightInput.setBackground(Palette.getInputBackgroundColor());
        heightInput.setBorder(new BorderUIResource.LineBorderUIResource(Palette.getInputBorderColor(), 1));
        heightInput.setForeground(Palette.getDarkTextColor());

        widthInput.setText(String.valueOf(FieldBuilder.getWidth()));
        widthInput.setOpaque(true);
        widthInput.setBackground(Palette.getInputBackgroundColor());
        widthInput.setBorder(new BorderUIResource.LineBorderUIResource(Palette.getInputBorderColor(), 1));
        widthInput.setForeground(Palette.getDarkTextColor());

        bombCountInput.setText(String.valueOf(FieldBuilder.getBombCount()));
        bombCountInput.setOpaque(true);
        bombCountInput.setBackground(Palette.getInputBackgroundColor());
        bombCountInput.setBorder(new BorderUIResource.LineBorderUIResource(Palette.getInputBorderColor(), 1));
        bombCountInput.setForeground(Palette.getDarkTextColor());

        fieldParams.add(heightLabel);
        fieldParams.add(heightInput);
        fieldParams.add(widthLabel);
        fieldParams.add(widthInput);
        fieldParams.add(bombCountLabel);
        fieldParams.add(bombCountInput);

        fieldParams.setLayout(new GridLayout(3, 2, 8, 4));

        return fieldParams;
    }

    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(true);
        buttonsPanel.setBackground(Palette.getBackgroundColor());

        JButton accept = new JButton("Accept");
        accept.setOpaque(true);
        accept.setBackground(Palette.getButtonBackgroundColor());
        accept.setBorderPainted(false);
        accept.setForeground(Palette.getDarkTextColor());

        accept.addActionListener((args) -> acceptFieldParams(Integer.valueOf(heightInput.getText()), Integer.valueOf(widthInput.getText()), Integer.valueOf(bombCountInput.getText())));
        JButton cancel = new JButton("Cancel");
        cancel.setOpaque(true);
        cancel.setBackground(Palette.getButtonBackgroundColor());
        cancel.setBorderPainted(false);
        cancel.setForeground(Palette.getDarkTextColor());

        cancel.addActionListener((args) -> close());

        buttonsPanel.add(accept);
        buttonsPanel.add(cancel);

        return buttonsPanel;
    }

    private void acceptFieldParams(int height, int width, int bombCount) {
        if (FieldBuilder.setField(height, width, bombCount)) {
            Game.restartGame();
            close();
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect parameters", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void open() {
        settingsFrame.setVisible(true);
    }

    private void close() {
        settingsFrame.dispose();
    }
}
