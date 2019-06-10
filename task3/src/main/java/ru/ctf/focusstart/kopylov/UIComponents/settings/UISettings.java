package ru.ctf.focusstart.kopylov.UIComponents.settings;

import ru.ctf.focusstart.kopylov.UIComponents.Palette;
import ru.ctf.focusstart.kopylov.logic.game.GameManager;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;

public class UISettings {
    private JFrame settingsFrame;
    private JTextField heightInput = new JTextField();
    private JTextField widthInput = new JTextField();
    private JTextField bombCountInput = new JTextField();
    private GameManager gameManager;

    public UISettings(GameManager gameManager) {
        this.gameManager = gameManager;

        settingsFrame = new JFrame("Settings");

        settingsFrame.add(createMainPanel());

        settingsFrame.pack();
        settingsFrame.dispose();
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(true);
        mainPanel.setBackground(Palette.BACKGROUND_COLOR);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(createFieldParamsPanel(), BorderLayout.CENTER);
        mainPanel.add(createButtonsPanel(), BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel createFieldParamsPanel() {
        JPanel fieldParams = new JPanel();
        fieldParams.setOpaque(true);
        fieldParams.setBackground(Palette.BACKGROUND_COLOR);

        JLabel heightLabel = new JLabel("Высота");
        heightLabel.setForeground(Palette.DARK_TEXT_COLOR);
        JLabel widthLabel = new JLabel("Ширина");
        widthLabel.setForeground(Palette.DARK_TEXT_COLOR);
        JLabel bombCountLabel = new JLabel("Количество бомб");
        bombCountLabel.setForeground(Palette.DARK_TEXT_COLOR);

        heightInput.setText(String.valueOf(gameManager.getFieldHeight()));
        heightInput.setOpaque(true);
        heightInput.setBackground(Palette.INPUT_BACKGROUND_COLOR);
        heightInput.setBorder(new BorderUIResource.LineBorderUIResource(Palette.INPUT_BORDER_COLOR, 1));
        heightInput.setForeground(Palette.DARK_TEXT_COLOR);

        widthInput.setText(String.valueOf(gameManager.getFieldWidth()));
        widthInput.setOpaque(true);
        widthInput.setBackground(Palette.INPUT_BACKGROUND_COLOR);
        widthInput.setBorder(new BorderUIResource.LineBorderUIResource(Palette.INPUT_BORDER_COLOR, 1));
        widthInput.setForeground(Palette.DARK_TEXT_COLOR);

        bombCountInput.setText(String.valueOf(gameManager.getFieldBombCount()));
        bombCountInput.setOpaque(true);
        bombCountInput.setBackground(Palette.INPUT_BACKGROUND_COLOR);
        bombCountInput.setBorder(new BorderUIResource.LineBorderUIResource(Palette.INPUT_BORDER_COLOR, 1));
        bombCountInput.setForeground(Palette.DARK_TEXT_COLOR);

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
        buttonsPanel.setBackground(Palette.BACKGROUND_COLOR);

        JButton accept = new JButton("Accept");
        accept.setOpaque(true);
        accept.setBackground(Palette.BUTTON_BACKGROUND_COLOR);
        accept.setBorderPainted(false);
        accept.setForeground(Palette.DARK_TEXT_COLOR);

        accept.addActionListener((args) -> acceptFieldParams(Integer.valueOf(heightInput.getText()), Integer.valueOf(widthInput.getText()), Integer.valueOf(bombCountInput.getText())));
        JButton cancel = new JButton("Cancel");
        cancel.setOpaque(true);
        cancel.setBackground(Palette.BUTTON_BACKGROUND_COLOR);
        cancel.setBorderPainted(false);
        cancel.setForeground(Palette.DARK_TEXT_COLOR);

        cancel.addActionListener((args) -> close());

        buttonsPanel.add(accept);
        buttonsPanel.add(cancel);

        return buttonsPanel;
    }

    private void acceptFieldParams(int height, int width, int bombCount) {
        if (isCorrectField(height, width, bombCount)) {
            gameManager.restartGame(height, width, bombCount);
            close();
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect parameters", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static boolean isCorrectField(int height, int width, int bombCount) {
        if (height < 3) {
            return false;
        }
        if (width < 3) {
            return false;
        }
        if (bombCount < 1) {
            return false;
        }
        return bombCount < width * height;
    }

    public void open() {
        settingsFrame.setVisible(true);
    }

    private void close() {
        settingsFrame.dispose();
    }
}
