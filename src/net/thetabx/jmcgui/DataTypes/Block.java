package net.thetabx.jmcgui.DataTypes;

public enum Block {
    Unknown(-1),
    Air(0),
    Stone(1, 16, 0),
    GrassBlock(2, 0, 0),
    Dirt(3, 32, 0),
    CobbleStone(4, 0, 16),
    WoodenPlanks(5, 64, 0),
    Saplings(6, 240, 0),
    Bedrock(7, 16, 16),
    Water(8, 0, 144),
    StationaryWater(9, 0, 144),
    Lava(10, 208, 224),
    StationaryLava(11, 208, 224),
    Sand(12, 0, 176),
    Gravel(13, 48, 16),
    GoldOre(14, 0, 32),
    IronOre(15, 16, 32),
    CoalOre(16, 32, 32),
    Wood(17, 80, 16),
    Leaves(18, 64, 48),
    Sponge(19, 0, 48),
    Glass(20, 16, 48),
    SandStone(24, 0, 176),
    Grass(31, 0, 0),
    Wool(35, 0, 64),
    Torch(50, 0, 80);

    private final int id;
    private int x = 128;
    private int y = 160;

    private Block(int id) {
        this.id = id;
    }

    private Block(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static int getX(Block block) {
        return block.getX();
    }

    public static int getY(Block block) {
        return block.getY();
    }

    public static Block getBlock(int id) {
        Block blocks[] = Block.values();
        for (Block block : blocks) {
            if (block.getId() == id)
                return block;
        }
        return Block.Unknown;
    }
}
