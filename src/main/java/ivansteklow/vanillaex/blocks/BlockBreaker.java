package ivansteklow.vanillaex.blocks;

import ivansteklow.isdev.utils.Utils;
import ivansteklow.vanillaex.ModCore;
import ivansteklow.vanillaex.client.gui.GuiHandler;
import ivansteklow.vanillaex.handlers.EnumHandler.ChipTypes;
import ivansteklow.vanillaex.init.Refs;
import ivansteklow.vanillaex.tileentities.TileEntityBlockBreaker;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class BlockBreaker extends BlockMachine {

	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	public static final PropertyBool ACTIVATED = PropertyBool.create("activated");

	public BlockBreaker() {
		super();
		setUnlocalizedName("blockBreaker");
		this.setRegistryName(new ResourceLocation(Refs.MOD_ID, "blockBreaker"));
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, ChipTypes.BASIC)
				.withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVATED, Boolean.valueOf(false)));
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World world, BlockPos pos) {
		TileEntityBlockBreaker te = (TileEntityBlockBreaker) world.getTileEntity(pos);
		ItemStackHandler handler = (ItemStackHandler) te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
				null);
		return Utils.calculateRedstone(handler);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { TYPE, FACING, ACTIVATED });
	}

	@Override
	public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return side != EnumFacing.UP || side != EnumFacing.DOWN;
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer))
				.withProperty(ACTIVATED, Boolean.valueOf(false))
				.withProperty(TYPE, getStateFromMeta(meta * EnumFacing.values().length).getValue(TYPE));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		ChipTypes type = (ChipTypes) state.getValue(TYPE);
		EnumFacing facing = (EnumFacing) state.getValue(FACING);
		int meta = type.getID() * EnumFacing.values().length + facing.ordinal();
		return meta;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		ChipTypes type = ChipTypes.values()[(int) (meta / EnumFacing.values().length) % ChipTypes.values().length];
		EnumFacing facing = EnumFacing.values()[meta % EnumFacing.values().length];
		return this.getDefaultState().withProperty(TYPE, type).withProperty(FACING, facing);
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this), 1,
				(int) (getMetaFromState(world.getBlockState(pos)) / EnumFacing.values().length));
	}

	@Override
	public int damageDropped(IBlockState state) {
		return (int) (getMetaFromState(state) / EnumFacing.values().length);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityBlockBreaker();
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityBlockBreaker();
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntityBlockBreaker te = (TileEntityBlockBreaker) world.getTileEntity(pos);
		IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		for (int slot = 0; slot < handler.getSlots(); slot++) {
			ItemStack stack = handler.getStackInSlot(slot);
			InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
		}
		super.breakBlock(world, pos, state);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing heldItem, float side, float hitX, float hitY) {
		if (!worldIn.isRemote) {
			playerIn.openGui(ModCore.instance, GuiHandler.BLOCKBREAKER, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}

}
