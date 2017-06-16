package ivansteklow.vanillaex.tileentities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.mojang.authlib.GameProfile;

import ivansteklow.isdev.utils.Utils;
import ivansteklow.vanillaex.blocks.BlockBreaker;
import ivansteklow.vanillaex.capabilities.Worker;
import ivansteklow.vanillaex.config.VExConfig;
import ivansteklow.vanillaex.handlers.EnumHandler.ChipTypes;
import ivansteklow.vanillaex.init.ModBlocks;
import ivansteklow.vanillaex.init.ModCapabilities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityBlockBreaker extends TileEntity implements ITickable, ICapabilityProvider {

	private ItemStackHandler handler;
	private Random random;
	private Worker worker;

	public TileEntityBlockBreaker() {
		this.worker = new Worker(VExConfig.machineCooldownBasic,() -> {
			if (this.world.isBlockPowered(pos)) {
				IBlockState currentState = this.world.getBlockState(pos);
				this.world.setBlockState(pos, currentState.withProperty(BlockBreaker.ACTIVATED, Boolean.valueOf(true)));
				updateCooldownCap();
			}
		}, () -> {
			IBlockState currentState = this.world.getBlockState(pos);
			EnumFacing facing = (EnumFacing) currentState.getValue(BlockBreaker.FACING);
			breakBlock(facing);
		});
		this.handler = new ItemStackHandler(10);
		this.random = new Random();
	}

	/**
	 * Reads data from nbt where data is stored
	 */
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.worker.deserializeNBT(nbt.getCompoundTag("Worker"));
		this.handler.deserializeNBT(nbt.getCompoundTag("ItemStackHandler"));

		super.readFromNBT(nbt);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setTag("Worker", this.worker.serializeNBT());
		nbt.setTag("ItemStackHandler", this.handler.serializeNBT());

		return super.writeToNBT(nbt);
	}

	@Override
	public void update() {
		if (this.world != null && !this.world.isRemote) {
			if (!this.world.isBlockPowered(pos)) {
				if (!this.world.isAirBlock(pos) && this.world.getBlockState(pos).getBlock() == ModBlocks.blockBreaker) {
					if (this.world.getBlockState(pos).getValue(BlockBreaker.ACTIVATED)) {
						IBlockState currentState = this.world.getBlockState(pos);
						this.world.setBlockState(pos,
								currentState.withProperty(BlockBreaker.ACTIVATED, Boolean.valueOf(false)));
					}
				}
			}
			if (this.world.isBlockPowered(pos))
				this.worker.doWork();
		}
	}

	public void updateCooldownCap() {
		int cap = this.worker.getMaxWork();
		if (this.world.getBlockState(pos).getValue(BlockBreaker.TYPE) == ChipTypes.BASIC)
			cap = VExConfig.machineCooldownBasic;
		else
			cap = VExConfig.machineCooldownAdvanced;
		if (this.handler.getStackInSlot(9).getItem() == Items.ENCHANTED_BOOK) {
			Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(this.handler.getStackInSlot(9));
			if (enchantments.containsKey(Enchantments.EFFICIENCY)) {
				cap -= Math.pow(enchantments.get(Enchantments.EFFICIENCY), 2) % cap;
			}
		}
		this.worker.setMaxCooldown(cap);
	}

	public void breakBlock(EnumFacing facing) {
		BlockPos newPos = pos.offset(facing, 1);
		IBlockState state = this.world.getBlockState(newPos);
		Block block = state.getBlock();
		if (!block.isAir(state, this.world, newPos) && block.getBlockHardness(state, this.world, newPos) >= 0
				&& !(block instanceof BlockDynamicLiquid) && !(block instanceof BlockStaticLiquid)) {
			// Creates a fake player which will berak the block
			EntityPlayer player = new EntityPlayer(world, new GameProfile(null, "BlockBreaker")) {

				@Override
				public boolean isSpectator() {
					return true;
				}

				@Override
				public boolean isCreative() {
					return false;
				}
			};
			List<ItemStack> drops = new ArrayList<ItemStack>();
			boolean customDrops = false;
			if (this.handler.getStackInSlot(9).getItem() == Items.ENCHANTED_BOOK) {
				ItemStack enchantedBook = this.handler.getStackInSlot(9);
				Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(enchantedBook);
				if (enchantments.containsKey(Enchantments.FORTUNE)) {
					int fortune = enchantments.get(Enchantments.FORTUNE);
					drops.add(new ItemStack(block.getItemDropped(state, this.random, fortune),
							block.quantityDroppedWithBonus(fortune, this.random), block.damageDropped(state)));
					customDrops = true;
				}
				if (enchantments.containsKey(Enchantments.SILK_TOUCH)
						&& block.canSilkHarvest(world, newPos, state, player)) {
					// HARD FIX FOR LAPIS
					if (block == Blocks.LAPIS_ORE)
						drops.add(new ItemStack(block, 1));
					else
						drops.add(new ItemStack(block, 1, block.damageDropped(state)));
					customDrops = true;
				}
			}
			if (!customDrops)
				drops = block.getDrops(world, newPos, state, 0);
			for (ItemStack stack : drops) {
				Utils.addStackToInventory(this.handler, 9, stack, false);
			}
			if (!Utils.isInventoryFull(this.handler, 9)) {
				this.world.playEvent(2001, pos, Block.getStateId(state));
				this.world.playSound(null, pos, block.getSoundType(state, world, newPos, player).getBreakSound(),
						SoundCategory.BLOCKS, 1, 1);
				this.world.setBlockToAir(newPos);
				if (block == Blocks.ICE)
					this.world.setBlockState(newPos, Blocks.FLOWING_WATER.getDefaultState());
			}
		}
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		int metadata = getBlockMetadata();
		return new SPacketUpdateTileEntity(this.pos, metadata, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return nbt;
	}

	@Override
	public void handleUpdateTag(NBTTagCompound tag) {
		this.readFromNBT(tag);
	}

	@Override
	public NBTTagCompound getTileData() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return nbt;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) this.handler;
		if (capability == ModCapabilities.CAPABILITY_WORKER)
			return (T) this.worker;
		return super.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
				|| capability == ModCapabilities.CAPABILITY_WORKER)
			return true;
		return super.hasCapability(capability, facing);
	}

	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.getPos()) == this
				&& player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return false;
	}

}