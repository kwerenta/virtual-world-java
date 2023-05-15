package pl.edu.pg.ui;

import pl.edu.pg.Organism;
import pl.edu.pg.OrganismsFactory;
import pl.edu.pg.Point;
import pl.edu.pg.UI;

import javax.swing.*;
import java.awt.*;

public class BoardCell extends JButton {
    private final static Color DEFAULT_BACKGROUND = Color.BLACK;
    private JComboBox<Organism.Species> list;
    private final Point location;

    public BoardCell(Organism organism, Point location) {
        this.location = location;
        setFocusable(false);
        setBorderPainted(false);
        setOpaque(true);
        setMargin(new Insets(0, 0, 0, 0));
        setOrganism(organism);
        addActionListener(e -> showOrganismsList());
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

    private void showOrganismsList() {
        if (!getText().isEmpty()) return;

        JDialog dialog = new JDialog(UI.getInstace());
        dialog.setLayout(new BorderLayout());

        list = new JComboBox<>(Organism.Species.values());
        dialog.add(list, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        dialog.add(buttons, BorderLayout.SOUTH);

        JButton spawnButton = new JButton("Spawn");
        spawnButton.addActionListener(e -> {
            spawnOrganism();
            dialog.dispose();
        });
        buttons.add(spawnButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dialog.dispose());
        buttons.add(cancelButton);

        dialog.setVisible(true);
        dialog.pack();
    }

    private void spawnOrganism() {
        Organism organism = OrganismsFactory.getOrganism((Organism.Species) list.getSelectedItem(), UI.getInstace().getWorld(), location);
        UI.getInstace().getWorld().spawn(organism);
        UI.getInstace().getGameScreen().updateBoard();
    }
}