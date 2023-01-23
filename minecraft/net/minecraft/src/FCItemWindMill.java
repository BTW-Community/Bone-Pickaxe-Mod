// FCMOD

package net.minecraft.src;

public class FCItemWindMill extends Item
{
    public FCItemWindMill( int iItemID )
    {
        super( iItemID );
        
        maxStackSize = 1;
        
        SetBuoyant();
        
        setUnlocalizedName( "fcItemWindMill" );
        
        setCreativeTab( CreativeTabs.tabRedstone );
    }
    
    @Override
    public boolean onItemUse( ItemStack itemStack, EntityPlayer player, World world, int i, int j, int k, int iFacing, float fClickX, float fClickY, float fClickZ )    
    {
        int iTargetBlockID = world.getBlockId( i, j, k );
        
        if ( iTargetBlockID == FCBetterThanWolves.fcBlockAxle.blockID )
        {
        	int iAxisAlignment = ((FCBlockAxle)(FCBetterThanWolves.fcBlockAxle)).
        		GetAxisAlignment( world, i, j, k );
        	
        	if ( iAxisAlignment != 0 )
        	{
        		boolean bIAligned = false;
        		
        		if ( iAxisAlignment == 2 )
        		{
	            	bIAligned = true;
        		}

    			FCEntityWindMill windMill = (FCEntityWindMill) EntityList.createEntityOfType(FCEntityWindMill.class, world, 
            		(float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, bIAligned );
			
        		if ( windMill.ValidateAreaAroundDevice() )
        		{
        			if ( windMill.IsClearOfBlockingEntities() )
        			{
	                    if ( !world.isRemote )
	                    {
	            			windMill.setRotationSpeed( windMill.ComputeRotation() );
	                        
			                world.spawnEntityInWorld( windMill );
	                    }
		                
		                itemStack.stackSize--;
        			}
        			else
        			{
                        if( world.isRemote )
                        {
                        	player.addChatMessage( "message.windMill.placementObstructed" );
                        }
        			}
        		}
        		else
        		{
                    if( world.isRemote )
                    {
                    	player.addChatMessage( "message.windMill.notEnoughRoom" );

                    }
        		}
            }
            
            return true;
        }        
        
        return false;
    }
} 
