package ivansteklow.vanillaex.blocks;

import ivansteklow.isdev.interfaces.IMetaBlockName;
import ivansteklow.vanillaex.handlers.EnumHandler.ChipTypes;
import ivansteklow.vanillaex.init.ModItems;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * The base class for all machines blocks
 * 
 * @author IvanSteklow
 *
 */
public abstract class BlockMachine extends BlockContainer implements IMetaBlockName, ITileEntityProvider {

	public static final PropertyEnum TYPE = PropertyEnum.create("type", ChipTypes.class);

	public BlockMachine() {
		super(Material.IRON);
		this.setHardness(3);
		this.setResistance(20);
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, ChipTypes.BASIC));
		this.isBlockContainer = true;
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return ChipTypes.values()[stack.getItemDamage() % ChipTypes.values().length].getName();
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list) {
		for (int i = 0; i < ChipTypes.values().length; i++) {
			list.add(new ItemStack(itemIn, 1, i));
		}
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this), 1, (int) (getMetaFromState(world.getBlockState(pos))));
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((ChipTypes) state.getValue(TYPE)).getID();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(TYPE, ChipTypes.values()[meta % ChipTypes.values().length]);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { TYPE });
	}

	public void updateMachineTier(World world, EntityPlayer player, EnumHand hand, BlockPos pos, ItemStack stack) {
		if (stack.getItem() == ModItems.itemChip) {
			ChipTypes newType = ChipTypes.values()[stack.getItemDamage() % ChipTypes.values().length];
			ChipTypes currentType = (ChipTypes) world.getBlockState(pos).getValue(TYPE);
			IBlockState newState = world.getBlockState(pos).withProperty(TYPE, newType);
			if (newType.getID() > currentType.getID()) {
				world.setBlockState(pos, newState, 2);
			}
			ItemStack newStack = stack.copy();
			newStack.shrink(1);
			player.setHeldItem(hand, newStack);
			if (player.getHeldItem(hand).getCount() <= 0)
				player.setHeldItem(hand, ItemStack.EMPTY);
		}
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return null;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return null;
	}

}
