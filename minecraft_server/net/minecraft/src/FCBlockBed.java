// FCMOD

package net.minecraft.src;

public class FCBlockBed extends FCBlockBedBase {
	public FCBlockBed(int blockID) {
		super(blockID);
    	
		this.setStepSound(Block.soundClothFootstep);
		this.SetBlockMaterial(FCBetterThanWolves.fcMaterialPlanks);
		
    	InitBlockBounds(0D, 0D, 0D, 1D, 0.5625D, 1D);
	}
	
	@Override
	public boolean DropComponentItemsOnBadBreak(World world, int x, int y, int z, int iMetadata, float chanceOfDrop) {
		DropItemsIndividualy(world, x, y, z, FCBetterThanWolves.fcItemSawDust.itemID, 3, 0, chanceOfDrop);
		DropItemsIndividualy(world, x, y, z, Item.stick.itemID, 1, 0, chanceOfDrop);
		DropItemsIndividualy(world, x, y, z, FCBetterThanWolves.fcItemPadding.itemID, 2, 0, chanceOfDrop);
		
		return true;
	}
	
    //------------- Class Specific Methods ------------//
	
	//----------- Client Side Functionality -----------//
}