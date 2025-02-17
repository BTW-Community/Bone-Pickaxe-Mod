// FCMOD

package net.minecraft.src;

import java.util.Iterator;

public abstract class FCBlockBedBase extends BlockBed
{
    public FCBlockBedBase( int iBlockID )
    {
    	super( iBlockID );
    }
    
    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par1World.isRemote)
        {
            return true;
        }
        else
        {
            int var10 = par1World.getBlockMetadata(par2, par3, par4);

            if (!isBlockHeadOfBed(var10))
            {
                int var11 = getDirection(var10);
                par2 += footBlockToHeadBlockMap[var11][0];
                par4 += footBlockToHeadBlockMap[var11][1];

                if (par1World.getBlockId(par2, par3, par4) != this.blockID)
                {
                    return true;
                }

                var10 = par1World.getBlockMetadata(par2, par3, par4);
            }

            if (par1World.provider.canRespawnHere() && par1World.getBiomeGenForCoords(par2, par4) != BiomeGenBase.hell)
            {
                if (isBedOccupied(var10))
                {
                    EntityPlayer var19 = null;
                    Iterator var12 = par1World.playerEntities.iterator();

                    while (var12.hasNext())
                    {
                        EntityPlayer var21 = (EntityPlayer)var12.next();

                        if (var21.isPlayerSleeping())
                        {
                            ChunkCoordinates var14 = var21.playerLocation;

                            if (var14.posX == par2 && var14.posY == par3 && var14.posZ == par4)
                            {
                                var19 = var21;
                            }
                        }
                    }

                    if (var19 != null)
                    {
                        par5EntityPlayer.addChatMessage("tile.bed.occupied");
                        return true;
                    }

                    setBedOccupied(par1World, par2, par3, par4, false);
                }

                EnumStatus var20 = par5EntityPlayer.sleepInBedAt(par2, par3, par4);

                if (var20 == EnumStatus.OK)
                {
                    setBedOccupied(par1World, par2, par3, par4, true);
                    return true;
                }
                else
                {
                    if (var20 == EnumStatus.NOT_POSSIBLE_NOW)
                    {
                        par5EntityPlayer.addChatMessage("tile.bed.noSleep");
                    }
                    else if (var20 == EnumStatus.NOT_SAFE)
                    {
                        par5EntityPlayer.addChatMessage("tile.bed.notSafe");
                    }

                    return true;
                }
            }
            else
            {
            	par5EntityPlayer.addChatMessage("tile.bed.sleepImpossible");
            	
                return true;
            }
        }
    }
    
	@Override
    public void setBlockBoundsBasedOnState( IBlockAccess blockAccess, int i, int j, int k )
    {
    	// override to deprecate parent
    }
	
    //------------- Class Specific Methods ------------//
	
	public boolean blocksHealing() {
		return false;
	}
	
	//----------- Client Side Functionality -----------//
}