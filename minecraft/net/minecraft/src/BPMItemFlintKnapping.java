package net.minecraft.src;

public class BPMItemFlintKnapping extends Item 
{
    public static final String[] bowPullIconNameArray = new String[] {
    		"flint_pull_0", "flint_pull_1", "flint_pull_2", "flint_pull_transition"};
    public Icon[] iconArray;
    
	public BPMItemFlintKnapping(int iItemID)
	{
		super(iItemID);
	    
		setUnlocalizedName( "BPMItemFlintKnapping" );  

	    maxStackSize = 1;
	 
	    this.setMaxDamage(10); //amount of hits required
	}
	
    public boolean hasIconForDuration()
    {
        return true;
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
    	//same as bow, not sure what this does... but it is essential!
        return 72000; 
    }
	
	public ItemStack onItemRightClick( ItemStack stack, World world, EntityPlayer player )
    {
    	player.setItemInUse( stack, getMaxItemUseDuration( stack ) );
    	
    	
    	return stack;
    }
	
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }
    
    public void onPlayerStoppedUsing( ItemStack itemStack, World world, EntityPlayer player, int iTicksInUseRemaining )
    {	
    	if (itemStack.getTagCompound() == null)
    	{
    		NBTTagCompound newTag = new NBTTagCompound();
    		itemStack.setTagCompound(newTag);
    		itemStack.getTagCompound().setInteger("badHits", 0);
    	}
    	int badHits = itemStack.getTagCompound().getInteger("badHits");
    	
    	int var6 = this.getMaxItemUseDuration(itemStack) - iTicksInUseRemaining;
    	
    	if (var6 < 5)
    	{
    		return;
    	}
        if (var6 < 20 || var6 > 35)
        {
        	badHits++;
        	//System.out.println("Uh oh! bad hits: " + badHits);
        	
        	player.playSound( "random.bow", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F );
        	
        	itemStack.getTagCompound().setInteger("badHits", badHits);
            
            if (badHits > 3)
            {
        		itemStack.damageItem( 11, player );
        		//FCUtilsItem.GivePlayerStackOrEject( player, new ItemStack(FCBetterThanWolves.fcItemStone, 1));
        		//player.inventory.mainInventory[player.inventory.currentItem] = null;
        		player.inventory.mainInventory[player.inventory.currentItem] = new ItemStack( FCBetterThanWolves.fcItemStone, 1);
            }
        }
        else if (var6 > 20)
        {
        	itemStack.damageItem( -1, player);
        	//System.out.println("Item damage = " + itemStack.getItemDamage());
        	player.playSound( "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F );
            //var7 = 1.0F;
        	
        	if (itemStack.getItemDamage() <= 0)
        	{
        		
        		FCUtilsItem.GivePlayerStackOrEject( player, new ItemStack(FCBetterThanWolves.fcItemStone, 1));
        		//itemStack.damageItem( 11, player );
        		//to remove item from inv VVV
        		player.inventory.mainInventory[player.inventory.currentItem] = null;
        		player.inventory.mainInventory[player.inventory.currentItem] = new ItemStack( BPMDefinitions.flintKnife, 1);
        	}
        }
    	
    }
    
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);
        this.iconArray = new Icon[bowPullIconNameArray.length];

        for (int var2 = 0; var2 < this.iconArray.length; ++var2)
        {
            this.iconArray[var2] = par1IconRegister.registerIcon(bowPullIconNameArray[var2]);
        }
    }
    
	@Override
	public Icon getAnimationIcon(EntityPlayer player) {
		ItemStack itemInUse = player.getItemInUse();

		if (itemInUse != null && itemInUse.itemID == this.itemID) {
			int timeInUse = itemInUse.getMaxItemUseDuration() - player.getItemInUseCount();

			return getDrawIcon(timeInUse);
		}
		else return itemIcon;
	}
	
	public Icon getDrawIcon(int itemInUseDuration) {
		if (itemInUseDuration < 4) {
			return iconArray[3];
		} else if (itemInUseDuration < 11) {
			return iconArray[0];
		} else if (itemInUseDuration < 21) {
			return iconArray[1];
		} else {
			return iconArray[2];
		}
	}
}
