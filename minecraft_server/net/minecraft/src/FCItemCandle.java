package net.minecraft.src;

import java.util.List;

public class FCItemCandle extends FCItemPlacesAsBlock {
	public FCItemCandle(int itemID) {
		super(itemID, FCBetterThanWolves.fcBlockCandlePlain.blockID, 0, "fcItemCandle");

		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setHasSubtypes(true);

        this.SetBuoyant();
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int facing, float clickX, float clickY, float clickZ) {
		if (itemStack.stackSize == 0) {
			return false;
		}

		if (!player.canPlayerEdit(x, y, z, facing, itemStack)) {
			return false;
		}

		if (attemptToCombineWithBlock(itemStack, player, world, x, y, z)) {
			return true;
		}

		FCUtilsBlockPos targetPos = new FCUtilsBlockPos(x, y, z, facing);

		if (attemptToCombineWithBlock(itemStack, player, world, targetPos.i, targetPos.j, targetPos.k)) {
			return true;
		}
		else {
			return super.onItemUse(itemStack, player, world, x, y, z, facing, clickX, clickY, clickZ);
		}
	}

	@Override
	public int GetBlockIDToPlace(int itemDamage, int facing, float clickX, float clickY, float clickZ) {
		return this.getBlockIDForItemDamage(itemDamage);
	}

	@Override
	public String getItemDisplayName(ItemStack stack) {
		int itemDamage = stack.getItemDamage();
		
		if (itemDamage < 16) {
			return (FCUtilsColor.colorOrderCapital[itemDamage] + " " + StringTranslate.getInstance().translateNamedKey(getLocalizedName(stack))).trim();
		}
		else {
			return super.getItemDisplayName(stack);
		}
	}

	//------------- Class Specific Methods ------------//

	public boolean attemptToCombineWithBlock(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z) {
		if (canCombineWithBlock(world, x, y, z, itemStack.getItemDamage())) {
			int targetBlockID = world.getBlockId(x, y, z);
			Block targetBlock = Block.blocksList[targetBlockID];

			if (incrementCandleCount(world, x, y, z)) {
				world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, 
						targetBlock.GetStepSound(world, x, y, z).getPlaceSound(), 
						(targetBlock.GetStepSound(world, x, y, z).getPlaceVolume() + 1.0F) / 2.0F, 
						targetBlock.GetStepSound(world, x, y, z).getPlacePitch() * 0.8F);

				itemStack.stackSize--;
				
				return true;
			}
		}

		return false;
	}

	public boolean canCombineWithBlock(World world, int x, int y, int z, int itemDamage) {
		int blockID = world.getBlockId(x, y, z);
		Block block = Block.blocksList[blockID];

		if (blockID == this.getBlockIDForItemDamage(itemDamage)) {
			if (((FCBlockCandle) block).getCandleCount(world, x, y, z) < 4) {
				return true;
			}
		}

		return false;
	}
	
	public boolean incrementCandleCount(World world, int x, int y, int z) {
		int candleCount = ((FCBlockCandle) FCBetterThanWolves.fcBlockCandlePlain).getCandleCount(world, x, y, z);
		((FCBlockCandle) FCBetterThanWolves.fcBlockCandlePlain).setCandleCount(world, x, y, z, candleCount + 1);
		return true;
	}
	
	public int getBlockIDForItemDamage(int itemDamage) {
		if (itemDamage < 16) {
			return FCBetterThanWolves.fcBlockCandlesColored[itemDamage].blockID;
		}
		else {
			return FCBetterThanWolves.fcBlockCandlePlain.blockID;
		}
	}
}