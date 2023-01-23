// FCMOD

package net.minecraft.src;

import java.util.Random;

public class FCBlockCobblestoneLooseStairs extends FCBlockMortarReceiverStairs {
	private int strata;
	
	protected FCBlockCobblestoneLooseStairs( int iBlockID, int strata ) {
        super( iBlockID, FCBetterThanWolves.fcBlockCobblestoneLoose, strata << 2 );
        
        this.strata = strata; 
        
        SetPicksEffectiveOn();
        SetChiselsEffectiveOn();    
        
        setUnlocalizedName( "fcBlockCobblestoneLooseStairs" );        
    }
	
    @Override
    public boolean OnMortarApplied( World world, int i, int j, int k ) { 	
    	int blockID = Block.stairsCobblestone.blockID;
    	
    	if (strata != 0) {
    		if (strata == 1) {
    			blockID = FCBetterThanWolves.fcBlockCobblestoneStairsMidStrata.blockID;
    		}
    		else {
    			blockID = FCBetterThanWolves.fcBlockCobblestoneStairsDeepStrata.blockID;
    		}
    	}
    	
		world.setBlockAndMetadataWithNotify( i, j, k, blockID, 
			world.getBlockMetadata( i, j, k ) );
		
		return true;
    }
    
    //------------- Class Specific Methods ------------//

    public int getStrata() {
		return strata;
	}

	//----------- Client Side Functionality -----------//
}