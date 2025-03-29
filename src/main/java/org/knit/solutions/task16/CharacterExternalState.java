package org.knit.solutions.task16;

public class CharacterExternalState {
    private double x;
    private double y;
    private String font;

    public CharacterExternalState(double x, double y, String font) {
        this.x = x;
        this.y = y;
        this.font = font;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    @Override
    public String toString() {
        return  "x=" + x +
                ", y=" + y +
                ", font='" + font + '\'';
    }
}
