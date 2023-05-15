package pl.edu.pg;

import java.awt.*;
import java.io.FileNotFoundException;

public class VirtualWorld {
    public static void main(String[] args) {
        EventQueue.invokeLater(UI::getInstace);
    }
}