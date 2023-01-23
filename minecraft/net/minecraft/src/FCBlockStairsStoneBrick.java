// FCMOD

package net.minecraft.src;

import java.util.Random;

public class FCBlockStairsStoneBrick extends FCBlockStairs {
	private int strata;
	
    protected FCBlockStairsStoneBrick( int iBlockID, int strata ) {
        super( iBlockID, Block.stoneBrick, strata << 2 );
        
        this.strata = strata;
        
        SetPicksEffectiveOn();
        
        setUnlocalizedName( "stairsStoneBrickSmooth" );
    }
	
    @Override
    public int idDropped( int iMetaData, Random rand, int iFortuneModifier ) {
    	int blockID = FCBetterThanWolves.fcBlockStoneBrickLooseStairs.blockID;
    	
    	if (strata != 0) {
    		if (strata == 1) {
    			blockID = FCBetterThanWolves.fcBlockStoneBrickLooseStairsMidStrata.blockID;
    		}
    		else {
    			blockID = FCBetterThanWolves.fcBlockStoneBrickLooseStairsDeepStrata.blockID;
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