// FCMOD

package net.minecraft.src;

import java.util.Random;

public class FCBlockSnowCover extends FCBlockGroundCover
{
    protected FCBlockSnowCover( int iBlockID )
    {
        super( iBlockID, Material.snow );
        
        setTickRandomly( true );
        
        setStepSound( soundSnowFootstep );
        
        setUnlocalizedName( "snow" );
    }
    //hiracho added to slow down when more snowlayers
    @Override
    public void onEntityCollidedWithBlock( World world, int i, int j, int k, Entity entity )
    {
    	// don't slow down movement in air as it affects the ability of entities to jump up blocks
    	
    	if ( entity.IsAffectedByMovementModifiers() && entity.onGround )
    	{
    		double snowheight = (double)(world.getBlockMetadata(i, j, k));
	        entity.motionX *= (1.0 - (0.075*snowheight));
	        entity.motionZ *= (1.0 - (0.075*snowheight));
    	}
    }
 
    
    @Override
    public boolean canPlaceBlockAt( World world, int i, int j, int k )
    {
    	int iBlockBelowID = world.getBlockId( i, j - 1, k );
    	Block blockBelow = Block.blocksList[iBlockBelowID];
    	
    	if ( blockBelow != null && blockBelow.GetIsBlockWarm( world, i, j - 1, k ) )
    	{
    		return false;
    	}
    	
    	return super.canPlaceBlockAt( world, i, j, k );
    }
    
	// FCTODO: I don't think this function is necessary
    //hiracho added function to account for metadata when good tool

    @Override
    public void harvestBlock( World world, EntityPlayer player, int i, int j, int k, int iMetadata )
    {
        int iItemID = Item.snowball.itemID;
        int amount = iMetadata/2 +1;     
        
        dropBlockAsItem_do( world, i, j, k, new ItemStack( iItemID, amount, 0 ) );
        world.setBlockToAir( i, j, k );
        player.addStat( StatList.mineBlockStatArray[this.blockID], 1 );
    }

    
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Item.snowball.itemID;
    }

    @Override
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }
    
	@Override
    public boolean canDropFromExplosion( Explosion explosion )
    {
        return false;
    }
    
    @Override
    public void OnBlockDestroyedWithImproperTool( World world, EntityPlayer player, int i, int j, int k, int iMetadata )
    {
    	dropBlockAsItem( world, i, j, k, iMetadata, 0 );
    }

    @Override
    public boolean CanConvertBlock( ItemStack stack, World world, int i, int j, int k )
    {
    	return true;
    }
    
    @Override
    public boolean ConvertBlock( ItemStack stack, World world, int i, int j, int k, int iFromSide )
    {
    	int iMetadata = world.getBlockMetadata( i, j, k );
    	if ( !world.isRemote && iMetadata>1)
    	{
    		world.setBlockMetadataWithNotify( i, j, k, iMetadata-2);
    			world.playAuxSFX( 2001, i, j, k, blockID );    	        
                FCUtilsItem.EjectStackFromBlockTowardsFacing( world, i, j, k, 
                	new ItemStack( Item.snowball, 1 ), iFromSide );
    	}
    	else if(!world.isRemote)
    		{
    			world.setBlockToAir(i, j, k);
    			FCUtilsItem.EjectStackFromBlockTowardsFacing( world, i, j, k, 
            	new ItemStack( Item.snowball, 1 ), iFromSide );
    		}

    	return true;
    }

    
    @Override
    public void updateTick( World world, int i, int j, int k, Random rand )
    {
    	int metadata =world.getBlockMetadata(i, j, k);
    	
        if ( world.getSavedLightValue( EnumSkyBlock.Block, i, j, k ) > 11 )
        {
        	//slowly melt snow instead of instant disappear
        	
        	if (metadata==0) world.setBlockToAir( i, j, k );

        	else world.setBlockMetadata(i, j, k, --metadata);
        }
        
        else if (world.IsSnowingAtPos(i, j, k)&& metadata<7) 
        {
        	int onfloorwithoffset=0;
    		int floorblockID = world.getBlockId(i, j-1, k);
    		
    		//check if this snow is on slab/plant etc want to check sidings one block down if so
        	if (floorblockID!=0)        	{
        		float flooroffset=blocksList[floorblockID].GroundCoverRestingOnVisualOffset(world, i,j-1,k);
        		if(flooroffset<0.0F)
        		{
        			onfloorwithoffset=1;
        		}
        	}
        	
        	int lowestside=7;
        	int steepness=2; // max difference between snow layers
        	boolean sidegrew=false;
        	
        	//check each direct sideways offset for limiting factors
        	for(int side=2; side<6;side++) 
        	{
        		
        		FCUtilsBlockPos sideblock = new FCUtilsBlockPos(i, j-onfloorwithoffset, k, Block.GetOppositeFacing(side));
        		int sideblockID = world.getBlockId(sideblock.i, sideblock.j, sideblock.k);

        		if (sideblockID != 0 )
        		{
            		float offset =blocksList[sideblockID].GroundCoverRestingOnVisualOffset(world, sideblock.i, sideblock.j, sideblock.k);
            		if (offset<0.0F) {
        			++sideblock.j; //add+1 to side height when snow block is overlaying actual side like slab/flowers
        			sideblockID = world.getBlockId(sideblock.i, sideblock.j, sideblock.k);
            		}
        		} 
        		
        		//is side block snow, how high?
        		if (sideblockID == Block.snow.blockID)
        		{
        			int sidemetadata = (world.getBlockMetadata(sideblock.i, sideblock.j, sideblock.k));
        			if (sidemetadata<= metadata-steepness)
        			{
        			world.scheduleBlockUpdate(sideblock.i, sideblock.j, sideblock.k, 
        					world.getBlockId( sideblock.i, sideblock.j, sideblock.k ), 
        					tickRate( world ));
        			sidegrew=true;
        			}
        			int sidelimiter=sidemetadata+steepness;
        			
            		//update lowest side
            		if (sidelimiter<lowestside) lowestside=sidelimiter; 
        		}
        		
        		//is sideblock not a full block, means snow edge, lowest max and can stop checking
        		else if (!(sideblockID != 0 && world.isBlockOpaqueCube(sideblock.i, sideblock.j, sideblock.k)))
        		{
        			lowestside=1;
        			break;
        		}

         	}

        	if (!sidegrew &&metadata<lowestside)
        	{
        		world.setBlockMetadataWithNotify(i, j, k, ++metadata);
        	}
        	
        }
    }     	
 

    @Override
	public void OnFluidFlowIntoBlock( World world, int i, int j, int k, BlockFluid newBlock )
	{
    	// override to prevent dropping of snowball
	}

    @Override
    public boolean GetCanBeSetOnFireDirectly( IBlockAccess blockAccess, int i, int j, int k )
    {
    	return true;
    }
    
    @Override
    public boolean GetCanBeSetOnFireDirectlyByItem( IBlockAccess blockAccess, int i, int j, int k )
    {
    	return false;
    }
    
    @Override
    public boolean SetOnFireDirectly( World world, int i, int j, int k )
    {
    	world.setBlockToAir( i, j, k );
		
    	return true;
    }
    
    @Override
    public int GetChanceOfFireSpreadingDirectlyTo( IBlockAccess blockAccess, int i, int j, int k )
    {
		return 60; // same chance as leaves and other highly flammable objects
    }
    
    @Override
    public void OnBrokenByPistonPush( World world, int i, int j, int k, int iMetadata )
    {
    	// override to prevent drop
    }
    
    //------------- Class Specific Methods ------------//
    
    public static boolean CanSnowCoverReplaceBlock( World world, int i, int j, int k )
    {
    	Block block = Block.blocksList[world.getBlockId( i, j, k )];
    	
    	return block == null || block.IsAirBlock() || ( block.IsGroundCover() && block != Block.snow );
    }
    
    public boolean isMaxSnowHeightLimited(World world, int i, int j, int k)//TODO WIP big snowgrow
    {
    	int validside=0;
    	for(int side=2; side<6;side++) //check each direct sideways offset
    	{
    		FCUtilsBlockPos sideblock = new FCUtilsBlockPos(i, j, k, Block.GetOppositeFacing(side));
    		int sideblockID = world.getBlockId(sideblock.i, sideblock.j, sideblock.k);
    		if (sideblockID != 0 && blocksList[sideblockID].GroundCoverRestingOnVisualOffset(world, sideblock.i, sideblock.j, sideblock.k)<0);
    		{
    			++sideblock.j; //add+1 to side height when snow block is overlaying actual side
    			sideblockID = world.getBlockId(sideblock.i, sideblock.j, sideblock.k);
    		}
    		if (sideblockID == Block.snow.blockID
    				||world.canSnowAt(sideblock.i, sideblock.j, sideblock.k)
    				||world.isBlockOpaqueCube(sideblock.i, sideblock.j, sideblock.k))
    			{
    			++validside;
    			} 
    		else return false; //TODO does this work
    	}
    	
    	return validside<4;
    }
    public void RaiseSnowOnSides(World world, int i, int j, int k) //TODO WIP big snowgrow
    {
    	
    	for(int side=2; side<6;side++) //check each direct sideways offset
    	{
    		FCUtilsBlockPos sideblock = new FCUtilsBlockPos(i, j, k, Block.GetOppositeFacing(side));
    		int sideblockID = world.getBlockId(sideblock.i, sideblock.j, sideblock.k);
    		if (sideblockID != 0 && blocksList[sideblockID].GroundCoverRestingOnVisualOffset(world, sideblock.i, sideblock.j, sideblock.k)<0);
    		{
    			++sideblock.j; //add+1 to side height when snow block is overlaying actual side
    			sideblockID = world.getBlockId(sideblock.i, sideblock.j, sideblock.k);
    		}
    		if (sideblockID == Block.snow.blockID
    				||world.canSnowAt(sideblock.i, sideblock.j, sideblock.k)
    				||world.isBlockOpaqueCube(sideblock.i, sideblock.j, sideblock.k))
    			{
    			
    			}   		
    	}
    }
    
	//----------- Client Side Functionality -----------//
    
    @Override    
    public MovingObjectPosition collisionRayTrace( World world, int i, int j, int k, Vec3 startRay, Vec3 endRay )
    {
    	float fVisualOffset = 0F;
    	
    	int iBlockBelowID = world.getBlockId( i, j - 1, k );
    	Block blockBelow = Block.blocksList[iBlockBelowID];
    	
    	if ( blockBelow != null )
    	{
    		fVisualOffset = blockBelow.GroundCoverRestingOnVisualOffset( world, i, j - 1, k ); 
    	}
    	
    	FCUtilsRayTraceVsComplexBlock rayTrace = new FCUtilsRayTraceVsComplexBlock( world, i, j, k, startRay, endRay );
    	
		rayTrace.AddBoxWithLocalCoordsToIntersectionList( 0D, fVisualOffset, 0D, 
    		1D, getSnowHeight(world,i,j,k) + fVisualOffset, 1D );
    	
        return rayTrace.GetFirstIntersection();    	
    }
    public double getSnowHeight(World world,int i,int j,int k)
    {
    	double height = (world.getBlockMetadata(i, j, k)+1)*0.125D;
    	return height;
    }
    
    
    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState( 
        	IBlockAccess blockAccess, int i, int j, int k )
        {
    		float snowheight = (float)(( blockAccess.getBlockMetadata( i, j, k )+1)*0.125F);
        	
        	return AxisAlignedBB.getAABBPool().getAABB( 0F, 0f, 0F, 1F, snowheight, 1F );
        }
    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool( World world, int i, int j, int k )
    {
    	float fVisualOffset = 0F;
    	
    	int iBlockBelowID = world.getBlockId( i, j - 1, k );
    	Block blockBelow = Block.blocksList[iBlockBelowID];
    	
    	if ( blockBelow != null )
    	{
    		fVisualOffset = blockBelow.GroundCoverRestingOnVisualOffset( world, i, j - 1, k ); 
    	}
    	
    	return GetBlockBoundsFromPoolBasedOnState( world, i, j, k ).offset( 
    		i, j + fVisualOffset, k );
    }
	@Override
    public void registerIcons( IconRegister register )
    {
        blockIcon = register.registerIcon( "snow" );
    }    
}
