package net.thetabx.jmcgui.MPWEntities;

public class ESelf extends MPWEntity {

    private double stance;
    private float yaw;
    private float pitch;
    private boolean onGround;

    public ESelf(int eId, double x, double y, double z, String name, double stance, float yaw, float pitch, boolean onGround) {
        super(eId, EntityType.Self, x, y, z, name);
        this.stance = stance;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPtich(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public double getStance() {
        return stance;
    }

    public void setStance(double stance) {
        this.stance = stance;
    }

    public boolean getOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public void setXYZ(double x, double y, double z) {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }
}
