package ivansteklow.vanillaex.handlers;

import net.minecraft.util.IStringSerializable;

/**
 * Enum handler for registering chip types
 * 
 * @author IvanSteklow
 *
 */
public class EnumHandler {

	/**
	 * Chip types enum
	 * 
	 * @author IvanSteklow
	 *
	 */
	public static enum ChipTypes implements IStringSerializable {
		BASIC("basic", 0), ADVANCED("advanced", 1);

		private int ID;
		private String name;

		private ChipTypes(String name, int ID) {
			this.ID = ID;
			this.name = name;
		}

		@Override
		public String getName() {
			return this.name;
		}

		public int getID() {
			return this.ID;
		}

		public String toString() {
			return getName();
		}

	}

}
