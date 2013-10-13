package net.thetabx.jmcgui.DataTypes;

public class MapCoord {

    private int x;
    private int z;

    public MapCoord(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public MapCoord(double x, double z) {
        this.x = (int) (x / 16);
        this.z = (int) (z / 16);
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    public double getTrueX() {
        return x * 16;
    }

    public double getTrueZ() {
        return z * 16;
    }

    @Override
    public String toString() {
        return "MapCoord [x=" + x + ", z=" + z + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + z;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MapCoord other = (MapCoord) obj;
        return x == other.x && z == other.z;
    }
}
