// FCMOD

package net.minecraft.src;

import java.util.List;

public class FCBlockStoneBrickLooseSlab extends FCBlockMortarReceiverSlab
{
    public FCBlockStoneBrickLooseSlab( int iBlockID )
    {
        super( iBlockID, Material.rock );
        
        setHardness( 1F ); // setHardness( 2.25F ); regular stone brick
        setResistance( 5F ); // setResistance( 10F ); regular stone brick
        
        SetPicksEffectiveOn();
        
        setStepSound( soundStoneFootstep );
        
        setUnlocalizedName( "fcBlockStoneBrickLooseSlab" );
        
		setCreativeTab( CreativeTabs.tabBlock );
    }
    
	@Override
	public int GetCombinedBlockID( int iMetadata )
	{
		return FCBetterThanWolves.fcBlockStoneBrickLoose.blockID;
	}

	@Override
	public int GetCombinedMetadata( int iMetadata )
	{
		return getStrata(iMetadata) << 2;
	}
	
	@Override
	public boolean AttemptToCombineWithFallingEntity(World world, int i, int j, int k, EntityFallingSand entity) {
		if (entity.blockID == blockID && !GetIsUpsideDown(world, i, j, k)
				&& entity.metadata == world.getBlockMetadata(i, j, k)) {

			ConvertToFullBlock(world, i, j, k);

			return true;
		}

		return false;
	}
	
	@Override
	public int damageDropped(int metadata) {
		return metadata &= (~1);
	}
	
	@Override
	public boolean OnMortarApplied(World world, int i, int j, int k) {
		int iNewMetadata = getStrata(world, i, j, k);

		if (GetIsUpsideDown(world, i, j, k)) {
			iNewMetadata |= 8;
		}

		world.setBlockAndMetadataWithNotify(i, j, k, FCBetterThanWolves.fcBlockStoneBrickSlabSingle.blockID, iNewMetadata);

		return true;
	}
    
    //------------- Class Specific Methods ------------//    
    
    public int getStrata( IBlockAccess blockAccess, int i, int j, int k )
    {
		return getStrata( blockAccess.getBlockMetadata( i, j, k ) );
    }
    
    public int getStrata( int iMetadata )
    {
    	return (iMetadata & 12) >>> 2;
    }
    
	//----------- Client Side Functionality -----------//
	
    private Icon m_IconByMetadataArray[] = new Icon[3];
    
    @Override
	public void getSubBlocks( int iBlockID, CreativeTabs creativeTabs, List list )
    {
        list.add( new ItemStack( iBlockID, 1, 0 ) );
        list.add( new ItemStack( iBlockID, 1, 4 ) );
        list.add( new ItemStack( iBlockID, 1, 8 ) );
    }

	@Override
    public int getDamageValue(World world, int x, int y, int z) {
		// used only by pick block
		return world.getBlockMetadata(x, y, z);
    }
    
	@Override
    public void registerIcons( IconRegister register )
    {
        blockIcon = register.registerIcon( "fcBlockStoneBrickLoose" );
        
		m_IconByMetadataArray[0] = blockIcon;		
		m_IconByMetadataArray[1] = register.registerIcon( "fcBlockStoneBrickLoose_1" );
		m_IconByMetadataArray[2] = register.registerIcon( "fcBlockStoneBrickLoose_2" );

    }
	
	@Override
    public Icon getIcon( int iSide, int iMetadata )
    {
        return m_IconByMetadataArray[getStrata(iMetadata)];    	
    }
}