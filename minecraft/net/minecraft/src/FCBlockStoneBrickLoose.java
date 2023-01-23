// FCMOD

package net.minecraft.src;

import java.util.List;

public class FCBlockStoneBrickLoose extends FCBlockLavaReceiver
{
    public FCBlockStoneBrickLoose( int iBlockID )
    {
        super( iBlockID, Material.rock );
        
        setHardness( 1F ); // setHardness( 2.25F ); regular stone brick
        setResistance( 5F ); // setResistance( 10F ); regular stone brick
        
        SetPicksEffectiveOn();
        
        setStepSound( soundStoneFootstep );
        
        setUnlocalizedName( "fcBlockStoneBrickLoose" );        
        
		setCreativeTab( CreativeTabs.tabBlock );
    }
    
	@Override
	public int damageDropped(int metadata) {
		return getStrata(metadata) << 2; // this block stores strata in last 2 bits
	}
    
    @Override
    public boolean OnMortarApplied( World world, int i, int j, int k ) {
		world.setBlockAndMetadataWithNotify( i, j, k, Block.stoneBrick.blockID,  getStrata( world, i, j, k) << 2);
		
		return true;
    }
    
    //------------- Class Specific Methods ------------//
    
	//------------ Client Side Functionality ----------//    
    
    private Icon m_iconLavaCracks;
    private Icon m_IconByMetadataArray[] = new Icon[3];
    
    @Override
    public void registerIcons( IconRegister register ) {
        super.registerIcons( register );
        
        m_iconLavaCracks = register.registerIcon( "fcOverlayStoneBrickLava" );
        
		m_IconByMetadataArray[0] = blockIcon;		
		m_IconByMetadataArray[1] = register.registerIcon( "fcBlockStoneBrickLoose_1" );
		m_IconByMetadataArray[2] = register.registerIcon( "fcBlockStoneBrickLoose_2" );

    }
    
    @Override
    protected Icon GetLavaCracksOverlay() {
    	return m_iconLavaCracks;
    } 

    
    @Override
	public void getSubBlocks( int iBlockID, CreativeTabs creativeTabs, List list ) {
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
    public Icon getIcon( int iSide, int iMetadata ) {
        return m_IconByMetadataArray[getStrata(iMetadata)];    	
    }
}