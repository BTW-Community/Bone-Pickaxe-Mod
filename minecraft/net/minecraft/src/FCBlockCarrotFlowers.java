package net.minecraft.src;

public class FCBlockCarrotFlowers extends FCBlockCarrotBase {
	public FCBlockCarrotFlowers(int iBlockID) {
		super(iBlockID);
		this.setUnlocalizedName("fcBlockCarrotFlowers");
	}

	@Override
	protected int GetCropItemID() {
		return FCBetterThanWolves.fcItemCarrotSeeds.itemID;
	}

	@Override
	protected int GetSeedItemID() {
		return FCBetterThanWolves.fcItemCarrotSeeds.itemID;
	}
    
	//------------ Client Side Functionality ----------//
	
	@Override
    public boolean RenderBlock(RenderBlocks renderer, int x, int y, int z) {
        renderer.setRenderBounds(GetBlockBoundsFromPoolBasedOnState(renderer.blockAccess, x, y, z));
        
        FCBetterThanWolves.fcBlockWeeds.RenderWeeds( this, renderer, x, y, z );

    	return renderer.renderCrossedSquares(this, x, y, z);
    }
}