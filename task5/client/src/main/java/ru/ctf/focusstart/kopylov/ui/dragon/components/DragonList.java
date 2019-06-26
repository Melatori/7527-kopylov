package ru.ctf.focusstart.kopylov.ui.dragon.components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DragonList extends DragonPanel {
    private List<DragonLabel> labels = new ArrayList<>();
    private Color backgroundColor = new Color(0, 0, 0);
    private Color foregroundColor = new Color(255, 255, 255);

    public DragonList() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void addD(String text) {
        for (DragonLabel label : labels) {
            if (label.getText().equals(text)) {
                return;
            }
        }

        DragonLabel label = new DragonLabel(text);
        label.setBackground(backgroundColor);
        label.setForeground(foregroundColor);

        labels.add(label);
        add(label);
    }

    public void removeD(String text) {
        for (DragonLabel label : labels) {
            if (label.getText().equals(text)) {
                labels.remove(label);
                remove(label);
                return;
            }
        }
    }

    public void setBackgroundD(Color backgroundColor) {
        if (this.backgroundColor == backgroundColor) {
            return;
        }
        this.backgroundColor = backgroundColor;

        setBackground(backgroundColor);
        for (DragonLabel label : labels) {
            label.setBackground(backgroundColor);
        }
    }

    public void setForegroundD(Color foregroundColor) {
        if (this.foregroundColor == foregroundColor) {
            return;
        }
        this.foregroundColor = foregroundColor;

        for (DragonLabel label : labels) {
            label.setForeground(foregroundColor);
        }
    }
}
