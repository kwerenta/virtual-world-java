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

        setLayout(new BorderLayout());
        JPanel board = new JPanel();
        board.setLayout(new GridLayout(world.getHeight(), world.getWidth()));
        for (int i = 0; i < world.getHeight(); i++) {
            for (int j = 0; j < world.getWidth(); j++) {
                Organism organism = world.getMap(j, i);
                JButton cell = new JButton();
                cell.setFocusable(false);
                if (organism != null)
                    cell.setText(organism.getSymbol());

                cells[i][j] = cell;
                board.add(cell);
            }
        }

        logsArea = new JTextArea();
        logsArea.setEditable(false);
        logsArea.setBackground(Color.LIGHT_GRAY);
        logsArea.setFocusable(false);

        JScrollPane logs = new JScrollPane(logsArea);
        logs.setPreferredSize(new Dimension(200, UI.HEIGHT));
        logs.setFocusable(false);

        add(board, BorderLayout.CENTER);
        add(logs, BorderLayout.LINE_END);
    }

    public void updateBoard() {
        for (int i = 0; i < world.getHeight(); i++) {
            for (int j = 0; j < world.getWidth(); j++) {
                Organism organism = world.getMap(j, i);
                JButton cell = cells[i][j];
                if (organism != null)
                    cell.setText(organism.getSymbol());
                else
                    cell.setText("");

            }
        }
    }

    public void updateLogs() {
        logsArea.setText("");
        while (!world.areLogsEmpty())
            logsArea.append(world.popLog() + '\n');
    }
}