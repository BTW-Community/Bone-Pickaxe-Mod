// FCMOD

package net.minecraft.src;

public class FCItemFishingRodBaited extends FCItemFishingRod
{
    public FCItemFishingRodBaited( int iItemID )
    {
        super( iItemID );
        
        setUnlocalizedName( "fcItemFishingRodBaited" );
        
        setCreativeTab( null );
    }

    @Override
    public ItemStack onItemRightClick( ItemStack stack, World world, EntityPlayer player )
    {
        if ( player.fishEntity != null )
        {
            int iItemDamage = player.fishEntity.catchFish();
            
            // need to reset stack as you lose bait when you catch a fish
            
            stack = player.getCurrentEquippedItem();
            
            stack.damageItem( iItemDamage, player );
            
            player.swingItem();
        }
        else
        {
            world.playSoundAtEntity( player, "random.bow", 0.5F, 
            	0.4F / ( itemRand.nextFloat() * 0.4F + 0.8F ) );

            if ( !world.isRemote )
            {
                world.spawnEntityInWorld( EntityList.createEntityOfType(EntityFishHook.class, world, player, true ) );
            }

            player.swingItem();
        }

        return stack;
    }

    @Override
    public void onCreated( ItemStack stack, World world, EntityPlayer player ) 
    {
		if ( player.m_iTimesCraftedThisTick == 0 && world.isRemote )
		{
			player.playSound( "mob.slime.attack", 0.25F, 
				(  world.rand.nextFloat() - world.rand.nextFloat()) * 0.1F + 0.7F );
		}
    }
    
	//----------- Client Side Functionality -----------//

    @Override
    public Icon getAnimationIcon(EntityPlayer player) {
    	if (player.getHeldItem() != null && player.getHeldItem().itemID == this.itemID && player.fishEntity != null) {
    		return GetCastIcon();
    	}
    	else return itemIcon;
    }
}
