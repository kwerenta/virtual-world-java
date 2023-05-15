package pl.edu.pg.ui;

import pl.edu.pg.UI;

import javax.swing.*;
import java.io.FileNotFoundException;

public class MenuBar extends JMenuBar {
    private final JMenu game;
    private final JMenu help;


    public MenuBar() {
        this.game = new JMenu("Game");
        this.help = new JMenu("Help");

        add(game);
        add(help);

        createGameMenu();
        createHelpMenu();
    }

    private void createGameMenu() {
        JMenuItem newGameItem = new JMenuItem("New");
        newGameItem.addActionListener(e -> UI.getInstace().destroyWorld());
        game.add(newGameItem);

        JMenuItem saveGameItem = new JMenuItem("Save");
        saveGameItem.addActionListener(e -> {
            if (UI.getInstace().getWorld() != null) UI.getInstace().getWorld().save();
        });
        game.add(saveGameItem);

        JMenuItem loadGameItem = new JMenuItem("Load");
        loadGameItem.addActionListener(e -> {
            try {
                UI.getInstace().loadWorld();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        game.add(loadGameItem);
    }

    private void createHelpMenu() {
        JMenuItem legendItem = new JMenuItem("Legend");
        legendItem.addActionListener(e -> showLegend());
        help.add(legendItem);

        JMenuItem controlsItem = new JMenuItem("Controls");
        controlsItem.addActionListener(e -> showControls());
        help.add(controlsItem);
    }

    private void showLegend() {
        JDialog legendDialog = new JDialog(UI.getInstace());

        JTextArea legend = new JTextArea();
        legend.append("Legend\n\n");
        legend.append("H - Human\n");
        legend.append("A - Antelope\n");
        legend.append("F - Fox\n");
        legend.append("S - Sheep\n");
        legend.append("T - Turtle\n");
        legend.append("W - Wolf\n");
        legend.append("B - Belladonna\n");
        legend.append("D - Dandelion\n");
        legend.append("G - Grass\n");
        legend.append("U - Guarana\n");
        legend.append("O - Hogweed\n");
        legend.setEditable(false);
        legendDialog.add(legend);

        legendDialog.setVisible(true);
        legendDialog.pack();
    }

    private void showControls() {
        JDialog controlsDialog = new JDialog(UI.getInstace());

        JTextArea controls = new JTextArea();
        controls.append("Controls\n\n");
        controls.append("arrows - player movement\n");
        controls.append("enter  - next turn");
        controls.setEditable(false);
        controlsDialog.add(controls);

        controlsDialog.setVisible(true);
        controlsDialog.pack();
    }
}