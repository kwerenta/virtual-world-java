package pl.edu.pg.ui;

public enum Screens {
    MAIN_MENU("Main menu"), GAME("Game");

    private final String value;

    Screens(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}