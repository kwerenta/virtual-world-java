package pl.edu.pg.ui;

import pl.edu.pg.Organism;
import pl.edu.pg.UI;
import pl.edu.pg.World;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    private final JButton[][] cells;
    private final World world;
    private final JTextArea logsArea;

    public GameScreen() {
        super();

        world = UI.getInstace().getWorld();
        cells = new JButton[world.getHeight()][world.getWidth()];
        logsArea = new JTextArea();

        setLayout(new BorderLayout());

        createBoardPanel();
        createLogsPanel();
    }

    public void updateBoard() {
        for (int i = 0; i < world.getHeight(); i++) {
            for (int j = 0; j < world.getWidth(); j++) {
                Organism organism = world.getMap(j, i);
                JButton cell = cells[i][j];

                if (organism != null) {
                    cell.setText(organism.getSymbol());
                    cell.setBackground(organism.getColor());
                } else {
                    cell.setBackground(Color.BLACK);
                    cell.setText("");
                }
            }
        }
    }

    public void updateLogs() {
        logsArea.setText("");
        while (!world.areLogsEmpty())
            logsArea.append(world.popLog() + '\n');
    }

    private void createBoardPanel() {
        JPanel board = new JPanel();
        board.setLayout(new GridLayout(world.getHeight(), world.getWidth()));
        for (int i = 0; i < world.getHeight(); i++) {
            for (int j = 0; j < world.getWidth(); j++) {
                Organism organism = world.getMap(j, i);
                JButton cell = new JButton();
                cell.setFocusable(false);
                cell.setBorderPainted(false);
                cell.setOpaque(true);
                cell.setBackground(Color.BLACK);

                if (organism != null) {
                    cell.setText(organism.getSymbol());
                    cell.setBackground(organism.getColor());
                }

                cells[i][j] = cell;
                board.add(cell);
            }
        }

        add(board, BorderLayout.CENTER);
    }

    private void createLogsPanel() {
        logsArea.setEditable(false);
        logsArea.setBackground(Color.LIGHT_GRAY);
        logsArea.setFocusable(false);

        JScrollPane logs = new JScrollPane(logsArea);
        logs.setPreferredSize(new Dimension(200, UI.HEIGHT));
        logs.setFocusable(false);

        add(logs, BorderLayout.LINE_END);
    }
}