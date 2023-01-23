// FCMOD

package net.minecraft.src;

import java.util.Random;

public class FCBlockStoneRough extends FCBlockFullBlock
{
    public static FCBlockStoneRough[] m_startaLevelBlockArray = new FCBlockStoneRough[3];
    
	public int m_iStrataLevel;
	
    public FCBlockStoneRough( int iBlockID, int iStrataLevel )
    {
        super( iBlockID, Material.rock );
        
        m_iStrataLevel = iStrataLevel;
        m_startaLevelBlockArray[iStrataLevel] = this;
        
        if ( iStrataLevel == 0 )
        {
            setHardness( 2.25F );
            setResistance( 10F );
        }
        else if ( iStrataLevel == 1 )
        {
            setHardness( 3F );
            setResistance( 13F );
        }
        else // 2
        {
            setHardness( 4.5F );
            setResistance( 20F );
        }
        
        SetPicksEffectiveOn();
        SetChiselsEffectiveOn();
        
        setStepSound(soundStoneFootstep);
        
        setUnlocalizedName( "fcBlockStoneRough" );        
    }
    
    @Override
    public boolean CanConvertBlock( ItemStack stack, World world, int i, int j, int k )
    {
    	if ( m_iStrataLevel == 0 )
    	{
    		// special case for stone pick so that it harvests top strata stone, but as an improper tool
    		
        	if ( stack != null && stack.getItem() instanceof FCItemPickaxe )
        	{
    			int iToolLevel = ((FCItemTool)stack.getItem()).toolMaterial.getHarvestLevel();
    			
    			if ( iToolLevel <= 1 )
    			{
    				return false;
    			}
        	}
    		
    	}

    	return true;
    }
    
    @Override
    public boolean ConvertBlock( ItemStack stack, World world, int i, int j, int k, int iFromSide )
    {
    	int iMetadata = world.getBlockMetadata( i, j, k );
    	
    	if ( iMetadata < 15 )
    	{
    		iMetadata++;
    		
	    	if ( !world.isRemote && IsEffectiveItemConversionTool( stack, world, i, j, k ) )
	    	{
	        	if ( iMetadata <=  8 )
	        	{
	        		if ( ( iMetadata & 1 ) == 0 )
	        		{
	        	        world.playAuxSFX( FCBetterThanWolves.m_iStoneRippedOffAuxFXID, i, j, k, 0 );							        
	        	        
	    	            FCUtilsItem.EjectStackFromBlockTowardsFacing( world, i, j, k, 
	    	            	new ItemStack( FCBetterThanWolves.fcItemStone, 1, m_iStrataLevel ), iFromSide );
	        		}
	        		else if ( iMetadata <= 5 && IsUberItemConversionTool( stack, world, i, j, k ) )
    				{
        				// iron or better chisel on top two strata ejects bricks instead
        				// of loose stones
        				
        				iMetadata += 3;
        				
	        	        world.playAuxSFX( FCBetterThanWolves.m_iStoneRippedOffAuxFXID, i, j, k, 0 );							        
	        	        
                        FCUtilsItem.EjectStackFromBlockTowardsFacing( world, i, j, k, 
                        	new ItemStack( FCBetterThanWolves.fcItemStoneBrick ), iFromSide );
    				}
	        	}
	        	else if ( iMetadata == 12 )
	        	{
        	        world.playAuxSFX( FCBetterThanWolves.m_iGravelRippedOffStoneAuxFXID, i, j, k, 0 );							        
        	        
	                FCUtilsItem.EjectStackFromBlockTowardsFacing( world, i, j, k, 
	                	new ItemStack( FCBetterThanWolves.fcItemPileGravel, 1 ), iFromSide );
	        	}
	    	}
	    	
        	world.setBlockMetadataWithNotify( i, j, k, iMetadata );

	    	return true;
    	}
    	else 
    	{
    		// final stage resulting in destruction
    		
    		if ( !world.isRemote && IsEffectiveItemConversionTool( stack, world, i, j, k ) )
    		{
		        world.playAuxSFX( FCBetterThanWolves.m_iGravelRippedOffStoneAuxFXID, i, j, k, 0 );    		
		        
	            FCUtilsItem.DropStackAsIfBlockHarvested( world, i, j, k, 
	            	new ItemStack( FCBetterThanWolves.fcItemPileGravel, 1 ) );
    		}
        	
        	return false;
    	}    	
    }
    
	@Override
    public void dropBlockAsItemWithChance( World world, int i, int j, int k, int iMetadata, float fChance, int iFortuneModifier )
    {
        if ( !world.isRemote )
        {
        	int iItemIDDropped = FCBetterThanWolves.fcItemStone.itemID;
        	int iNumDropped = 1;
        	int metadataDropped = m_iStrataLevel;
        	
        	if ( iMetadata < 8 )
        	{
		        iNumDropped = 8 - ( iMetadata / 2 );		        
        	}
        	else
        	{
        		iItemIDDropped = FCBetterThanWolves.fcItemPileGravel.itemID;
        		metadataDropped = 0;
        		
        		if ( iMetadata < 12 )
        		{
        			iNumDropped = 2;
        		}
        	}
        	
	        for( int iTempCount = 0; iTempCount < iNumDropped; iTempCount++ )
	        {
                dropBlockAsItem_do( world, i, j, k, new ItemStack( iItemIDDropped, 1, metadataDropped ) );
	        }
        }
    }

    @Override
    public void OnBlockDestroyedWithImproperTool( World world, EntityPlayer player, int i, int j, int k, int iMetadata )
    {
        world.playAuxSFX( FCBetterThanWolves.m_iGravelRippedOffStoneAuxFXID, i, j, k, 0 );
        
    	DropComponentItemsWithChance( world, i, j, k, iMetadata, 1F );
    }
    
	@Override
    public boolean canDropFromExplosion( Explosion explosion )
    {
        return false;
    }
    
	@Override
    public void onBlockDestroyedByExplosion( World world, int i, int j, int k, Explosion explosion )
    {
		int iMetadata = world.getBlockMetadata( i, j, k );
		
		float fChanceOfPileDrop = 1.0F;
		
		if ( explosion != null )
		{
			fChanceOfPileDrop = 1.0F / explosion.explosionSize;
		}
		
    	DropComponentItemsWithChance( world, i, j, k, iMetadata, fChanceOfPileDrop );
    }
	
    @Override
    public int GetHarvestToolLevel( IBlockAccess blockAccess, int i, int j, int k )
    {
    	if ( m_iStrataLevel > 1 )
    	{
    		return m_iStrataLevel + 1;
    	}
    	
    	return 2;
    }
    
    @Override
    public int GetEfficientToolLevel( IBlockAccess blockAccess, int i, int j, int k )
    {
    	if ( m_iStrataLevel > 0 )
    	{
    		return m_iStrataLevel + 1;
    	}
    	
    	return 0;
    }
    
    @Override
    public boolean AreChiselsEffectiveOn( World world, int i, int j, int k )
    {
    	if ( world.getBlockMetadata( i, j, k ) >= 8 )
    	{
    		// chisels stop being effective once all the whole stone items are harvested, to make tunneling problematic
    		
    		return false;
    	}
    	
    	return super.AreChiselsEffectiveOn( world, i, j, k );
    }
    
	@Override
    protected boolean canSilkHarvest()
    {
        return false;
    }    
    
    @Override
    public boolean IsNaturalStone( IBlockAccess blockAccess, int i, int j, int k )
    {
    	return true;
    }
    
    @Override
    public ItemStack GetStackRetrievedByBlockDispenser( World world, int i, int j, int k ) {
    	if (world.getBlockMetadata(i, j, k) != 0) {
    		return null;
    	}
    	else {
    		return super.GetStackRetrievedByBlockDispenser(world, i, j, k);
    	}
    }
    
    @Override
    public void OnRemovedByBlockDispenser( World world, int i, int j, int k ) {
    	int metadata = world.getBlockMetadata(i, j, k);
    	
    	if (metadata != 0) {
    		this.dropBlockAsItem(world, i, j, k, metadata, 0);
    	}
    	
    	super.OnRemovedByBlockDispenser(world, i, j, k);
    }
    
    //------------- Class Specific Methods ------------//
	
    public boolean IsEffectiveItemConversionTool( ItemStack stack, World world, int i, int j, int k )
    {
    	if ( stack != null && stack.getItem() instanceof FCItemChisel )
    	{
			int iToolLevel = ((FCItemChisel)stack.getItem()).toolMaterial.getHarvestLevel();
			
			return iToolLevel >= GetEfficientToolLevel( world, i, j, k );
    	}  
    	
    	return false;
    }
	
    public boolean IsUberItemConversionTool( ItemStack stack, World world, int i, int j, int k )
    {
    	if ( stack != null && stack.getItem() instanceof FCItemChisel )
    	{
			int iToolLevel = ((FCItemChisel)stack.getItem()).toolMaterial.getHarvestLevel();
			
			return iToolLevel >= GetUberToolLevel( world, i, j, k );
    	}  
    	
    	return false;
    }
    
    public int GetUberToolLevel( IBlockAccess blockAccess, int i, int j, int k )
    {
    	return 2;
    }
    
	private void DropComponentItemsWithChance( World world, int i, int j, int k, int iMetadata, float fChanceOfItemDrop )
	{
    	if ( iMetadata < 8 )
    	{
    		int iNumStoneDropped = 4 - ( iMetadata / 2 );
	        
			DropItemsIndividualy( world, i, j, k, FCBetterThanWolves.fcItemStone.itemID, iNumStoneDropped, m_iStrataLevel, fChanceOfItemDrop );
    	}
    	
    	int iNumGravelDropped = 1;
    	
		if ( iMetadata < 12 )
		{
			iNumGravelDropped = 2;
		}
		
		DropItemsIndividualy( world, i, j, k, FCBetterThanWolves.fcItemPileGravel.itemID, iNumGravelDropped, 0, fChanceOfItemDrop );
	}
    
	//----------- Client Side Functionality -----------//
    
    private Icon m_iconBroken; 
    private Icon[] m_crackIcons;
    
	@Override
    public void registerIcons( IconRegister register )
    {
		String sBaseName = getUnlocalizedName2(); // "2" function strips "tile." at start of name
		
		if ( m_iStrataLevel > 0 )
		{
			sBaseName += "_" + m_iStrataLevel;
		}
			
        blockIcon = register.registerIcon( sBaseName );
		
		m_iconBroken = register.registerIcon( sBaseName + "_broken" );
		
        m_crackIcons = new Icon[7];

        for ( int iTempIndex = 0; iTempIndex < 7; iTempIndex++ )
        {
        	m_crackIcons[iTempIndex] = register.registerIcon( "fcOverlayStoneRough_" + ( iTempIndex + 1 ) );
        }
    }
	
	@Override
    public Icon getIcon( int iSide, int iMetadata )
    {
		if ( iMetadata >= 8 )
		{
			return m_iconBroken;
		}
		
		return blockIcon;
    }
	
    @Override
    public void RenderBlockSecondPass( RenderBlocks renderBlocks, int i, int j, int k, boolean bFirstPassResult )
    {
    	if ( bFirstPassResult )
    	{
	    	IBlockAccess blockAccess = renderBlocks.blockAccess;
	    	int iMetadata = blockAccess.getBlockMetadata( i, j, k );
	    	
	    	if ( iMetadata > 0 && iMetadata != 8 )
	    	{
	    		int iTextureIndex = 0;
	    		
	    		if ( iMetadata < 8 )
	    		{
		    		iTextureIndex = MathHelper.clamp_int( iMetadata - 1, 0, 6 );		    		
	    		}
	    		else
	    		{
		    		iTextureIndex = MathHelper.clamp_int( iMetadata - 9, 0, 6 );		    		
	    		}
	    		
    			Icon overlayTexture = m_crackIcons[iTextureIndex];
    			
            	if ( overlayTexture != null )
            	{
            		RenderBlockWithTexture( renderBlocks, i, j, k, overlayTexture );
            	}
	    	}
    	}
    }    
}