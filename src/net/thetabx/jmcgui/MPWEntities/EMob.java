package net.thetabx.jmcgui.MPWEntities;

public class EMob extends MPWEntity {

    public EMob(int eId, double x, double y, double z, byte mobType) {
        super(eId, EntityType.Mob, x, y, z, MobType.getName(mobType));
    }

    public enum MobType {
        /*50	 Creeper	 0.6	 1.8
		51	 Skeleton	 0.6	 1.8
		52	 Spider	 1.4	 0.9
		53	 Giant Zombie	 3.6	 10.8
		54	 Zombie	 0.6	 1.8
		55	 Slime	 0.6 * size	 0.6 * size
		56	 Ghast	 4	 4
		57	 Zombie Pigman	 0.6	 1.8
		58	 Enderman		
		59	 Cave Spider		
		60	 Silverfish		
		61	 Blaze		
		62	 Magma Cube	 0.6 * size	 0.6 * size
		63	 Ender Dragon		
		90	 Pig	 0.9	 0.9
		91	 Sheep	 0.6	 1.3
		92	 Cow	 0.9	 1.3
		93	 Chicken	 0.3	 0.4
		94	 Squid	 0.95	 0.95
		95	 Wolf	 0.6	 1.8
		96	 Mooshroom		
		97	 Snowman		
		98	 Ocelot		
		99	 Iron Golem		
		120	 Villager*/

        Creeper(50), Skeleton(51), Spider(52), GiantZombie(53), Zombie(54), Slime(55), Ghast(56), ZombiePigman(57), Enderman(58), CaveSpider(59), Silverfish(60),
        Blaze(61), MagmaCube(62), EnderDragon(63), Pig(90), Sheep(91), Cow(92), Chicken(93), Squid(94), Wolf(95), Mooshroom(96), Snowman(97), Ocelot(98),
        IronGolem(99), Villager(120);

        private int mobType;

        private MobType(int mobType) {
            this.mobType = mobType;
        }

        public int getMobType() {
            return this.mobType;
        }

        public static String getName(int mobType) {
            MobType[] m = MobType.values();
            for (int i = 0; i < m.length; i++) {
                if (m[i].getMobType() == mobType)
                    return m[i].name();
            }

            System.out.println("WARNING UNKNOWN MOBTYPE " + mobType);

            return null;
        }

    }
}
