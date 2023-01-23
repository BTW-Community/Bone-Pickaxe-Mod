// FCMOD

package net.minecraft.src;

import java.util.Random;

public class FCBlockStairsCobblestone extends FCBlockStairs {
	private int strata;
	
    protected FCBlockStairsCobblestone( int iBlockID, int strata ) {
        super( iBlockID, Block.cobblestone, strata );
        
        this.strata = strata;
        
        SetPicksEffectiveOn();
        
        setUnlocalizedName( "stairsStone" );
    }
	
    @Override
    public int idDropped( int iMetaData, Random rand, int iFortuneModifier ) {   	
    	int blockID = FCBetterThanWolves.fcBlockCobblestoneLooseStairs.blockID;
    	
    	if (strata != 0) {
    		if (strata == 1) {
    			blockID = FCBetterThanWolves.fcBlockCobblestoneLooseStairsMidStrata.blockID;
    		}
    		else {
    			blockID = FCBetterThanWolves.fcBlockCobblestoneLooseStairsDeepStrata.blockID;
    		}
    	}
    	
        return blockID;
    }
    
    @Override
    public void OnBlockDestroyedWithImproperTool( World world, EntityPlayer player, int i, int j, int k, int iMetadata ) {
        dropBlockAsItem( world, i, j, k, iMetadata, 0 );
    }
    
	@Override
	public boolean HasMortar( IBlockAccess blockAccess, int i, int j, int k ) {
		return true;
	}
	
    //------------- Class Specific Methods ------------//
	
    public int getStrata() {
		return strata;
	}
    
	//----------- Client Side Functionality -----------//
}