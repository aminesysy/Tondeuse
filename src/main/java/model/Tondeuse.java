package model;

public class Tondeuse {

    private int posX;
    private int posY;
    private char orientation;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    private String operation;

    public Tondeuse(int posX, int posY, char orientation) {
        this.posX = posX;
        this.posY = posY;
        this.orientation = orientation;
    }

    public Tondeuse(int posX, int posY, char orientation, String operation) {
        this.posX = posX;
        this.posY = posY;
        this.orientation = orientation;
        this.operation = operation;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public char getchar() {
        return orientation;
    }

    public void setchar(char orientation) {
        this.orientation = orientation;
    }
    public void moveForward(Pelouse p) {
        switch (orientation) {
            case 'N' -> posY = posY < p.getSupY() ? posY+1 : posY;
            case 'S' -> posY = posY > 0 ? posY-1 : posY;
            case 'E' -> posX = posX < p.getSupX()? posX+1 : posX;
            case 'W' -> posX = (posX >0) ? posX -1 : posX;
        }
    }

    public void turnRight() {
        switch (orientation) {
            case 'N' -> orientation = 'E';
            case 'E' -> orientation  = 'S';
            case 'S' -> orientation = 'W';
            case 'W' -> orientation = 'N';
        }
    }

    public void turnLeft() {
        switch (orientation) {
            case 'N' -> orientation = 'W';
            case 'E' -> orientation  = 'N';
            case 'S' -> orientation = 'E';
            case 'W' -> orientation = 'S';
        }
    }

    @Override
    public String toString() {
        return "Tondeuse{" +
                "posX=" + posX +
                ", posY=" + posY +
                ", orientation=" + orientation +
                '}';
    }
}
