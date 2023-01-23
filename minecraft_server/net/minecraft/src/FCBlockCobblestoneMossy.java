// FCMOD

package net.minecraft.src;

import java.util.Random;

public class FCBlockCobblestoneMossy extends Block
{
    public FCBlockCobblestoneMossy( int iBlockID )
    {
        super( iBlockID, Material.rock );
        
        setHardness( 2F );
        setResistance( 10 );
        SetPicksEffectiveOn();
        
        setStepSound( soundStoneFootstep );
        
        setUnlocalizedName( "stoneMoss" );
        
        setCreativeTab( CreativeTabs.tabBlock );
    }
    
    @Override
    public int GetHarvestToolLevel( IBlockAccess blockAccess, int i, int j, int k )
    {
		return 2; // iron or higher
    }
    
	@Override
	public boolean DropComponentItemsOnBadBreak( World world, int i, int j, int k, int iMetadata, float fChanceOfDrop )
	{
		DropItemsIndividualy( world, i, j, k, FCBetterThanWolves.fcItemStone.itemID, 6, iMetadata, fChanceOfDrop );
		
		return true;
	}
	
	@Override
	public int damageDropped(int metadata) {
		return getStrata(metadata);
	}
	
	@Override
    public int getDamageValue(World world, int x, int y, int z) {
		// used only by pick block
		return world.getBlockMetadata(x, y, z);
    }

    //------------- Class Specific Methods ------------//    
    
    public int getStrata( IBlockAccess blockAccess, int i, int j, int k )
    {
		return getStrata( blockAccess.getBlockMetadata( i, j, k ) );
    }

    public int getStrata( int iMetadata )
    {
    	return iMetadata & 3;
    }
    
	//----------- Client Side Functionality -----------//
}
