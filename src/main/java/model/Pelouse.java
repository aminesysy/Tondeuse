package model;

public class Pelouse {
    private int supX;
    private int supY;
    private static Pelouse instance = null;

    private Pelouse() {
    }
    public static Pelouse getInstance(int supX, int supY) {
        synchronized( Pelouse.class ) {
            if (instance == null) {
                instance = new Pelouse(supX, supY);
            }
        }
        return instance;
    }
    public static Pelouse getInstance() {
        synchronized( Pelouse.class ) {
            if (instance == null) {
                instance = new Pelouse(0, 0);
            }
        }
        return instance;
    }

    private Pelouse(int supX, int supY) {
        this.supX = supX;
        this.supY = supY;
    }

    public int getSupX() {
        return supX;
    }

    public int getSupY() {
        return supY;
    }

    @Override
    public String toString() {
        return "Pelouse{" +
                "supX=" + supX +
                ", supY=" + supY +
                '}';
    }
}
