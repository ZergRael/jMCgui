package net.thetabx.jmcgui.MPWObjects;

public class SlotData {

    private short itemBlockId;
    private byte itemCount;
    private short itemDamage;
    private short nbtDataLength;
    private byte[] nbtData;

    private short[] enchantableItemsIds = new short[]{
            0x103, //Flint and steel
            0x105, //Bow
            0x15A, //Fishing rod
            0x167, //Shears

            //TOOLS
            //sword, shovel, pickaxe, axe, hoe
            0x10C, 0x10D, 0x10E, 0x10F, 0x122, //WOOD
            0x110, 0x111, 0x112, 0x113, 0x123, //STONE
            0x10B, 0x100, 0x101, 0x102, 0x124, //IRON
            0x114, 0x115, 0x116, 0x117, 0x125, //DIAMOND
            0x11B, 0x11C, 0x11D, 0x11E, 0x126, //GOLD

            //ARMOUR
            //helmet, chestplate, leggings, boots
            0x12A, 0x12B, 0x12C, 0x12D, //LEATHER
            0x12E, 0x12F, 0x130, 0x131, //CHAIN
            0x132, 0x133, 0x134, 0x135, //IRON
            0x136, 0x137, 0x138, 0x139, //DIAMOND
            0x13A, 0x13B, 0x13C, 0x13D  //GOLD
    };

    public SlotData() {
        this.itemBlockId = -1;
    }

    public SlotData(short itemBlockId, byte itemCount, short itemDamage) {
        this.itemBlockId = itemBlockId;
        this.itemCount = itemCount;
        this.itemDamage = itemDamage;
        this.nbtDataLength = -1;
    }

    public SlotData(short itemBlockId, byte itemCount, short itemDamage, short nbtDataLength, byte[] nbtData) {
        this.itemBlockId = itemBlockId;
        this.itemCount = itemCount;
        this.itemDamage = itemDamage;
        this.nbtDataLength = nbtDataLength;
        this.nbtData = nbtData;
    }

    public short getItemBlockId() {
        return itemBlockId;
    }

    public byte getItemCount() {
        return itemCount;
    }

    public short getNbtDataLength() {
        return nbtDataLength;
    }

    public byte[] getNbtData() {
        return nbtData;
    }

    public short getItemDamage() {
        return itemDamage;
    }
}
