package pl.edu.pg.ui;

import pl.edu.pg.Organism;

import javax.swing.*;
import java.awt.*;

public class BoardCell extends JButton {
    private final static Color DEFAULT_BACKGROUND = Color.BLACK;

    public BoardCell(Organism organism) {
        setFocusable(false);
        setBorderPainted(false);
        setOpaque(true);
        setOrganism(organism);
    }

    public void setOrganism(Organism organism) {
        if (organism != null) {
            setText(organism.getSymbol());
            setBackground(organism.getColor());
            return;
        }

        setText("");
        setBackground(DEFAULT_BACKGROUND);
    }
}