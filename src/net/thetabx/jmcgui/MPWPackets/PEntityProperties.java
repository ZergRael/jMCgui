package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

/**
 * Created with IntelliJ IDEA.
 * User: Zerg
 * Date: 11/10/13
 * Time: 01:30
 */
public class PEntityProperties extends MPWPacket {
    public static final short packetId = 0x2C;
    // Last update 74

    // Server to client
    private int entityId;
    private int propertiesCount;
    private PropertyData[] propertyData;

    public PEntityProperties(TCPReader in) throws Exception {
        super(packetId);
        entityId = in.readInt();
        propertiesCount = in.readInt();
        propertyData = new PropertyData[propertiesCount];
        for(int i = 0; i < propertiesCount; i++) {
            String key = in.readString();
            double value = in.readDouble();
            short listLength = in.readShort();
            ModifierData[] modifierData = new ModifierData[listLength];
            for(short j = 0; j < listLength; j++) {
                modifierData[j] = new ModifierData(in.readLong(), in.readLong(), in.readDouble(), in.readByte());
            }
            propertyData[i] = new PropertyData(key, value, listLength, modifierData);
        }

    }

    public int getEntityId() {
        return entityId;
    }

    public int getPropertiesCount() {
        return propertiesCount;
    }

    public PropertyData[] getPropertyData() {
        return propertyData;
    }

    class PropertyData
    {
        public String key;
        public double value;
        public short listLength;
        public ModifierData[] modifierData;

        public PropertyData(String key, double value, short listLength, ModifierData[] modifierData)
        {
            this.key = key;
            this.value = value;
            this.listLength = listLength;
            this.modifierData = modifierData;
        }
    }

    class ModifierData
    {
        public long UUID1;
        public long UUID2;
        public double amount;
        public byte operation;

        public ModifierData(long UUID1, long UUID2, double amount, byte operation)
        {
            this.UUID1 = UUID1;
            this.UUID2 = UUID2;
            this.amount = amount;
            this.operation = operation;
        }
    }
}
