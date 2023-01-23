package net.minecraft.src;

public class BPMRecipesKnifeReturn implements IRecipe
{
	public static ItemStack bladeProgressiveItem;
	
	public boolean matches(InventoryCrafting craftingInventory, World world) 
	{
		ItemStack progressiveItemStack = null;
		
        for ( int iTempSlot = 0; iTempSlot < craftingInventory.getSizeInventory(); ++iTempSlot )
        {
            ItemStack tempStack = craftingInventory.getStackInSlot( iTempSlot );

            if ( tempStack != null )
            {
                if ( isProgressiveItem( tempStack ) )
                {
                	if ( progressiveItemStack == null )
                	{
                		progressiveItemStack = tempStack;
                	}
                	else
                	{
                		return false;
                	}
                }
                else
                {
                	return false;
                }
            }
        }

        return progressiveItemStack != null;
   }
	
	private boolean isProgressiveItem (ItemStack stack)
	{
    	int iItemID = stack.itemID;
		
    	if ( iItemID == BPMDefinitions.leatherCutting.itemID ||
    		iItemID == BPMDefinitions.leatherCuttingIron.itemID ||
    		iItemID == BPMDefinitions.ribCutting.itemID ||
    		iItemID == BPMDefinitions.ribCuttingIron.itemID )
    	{
    		return true;
    	}
		return false;
	}
	
	public ItemStack getCraftingResult(InventoryCrafting craftingInventory)
	{
		ItemStack progressiveItemStack = null;
		
		for ( int iTempSlot = 0; iTempSlot < craftingInventory.getSizeInventory(); ++iTempSlot )
        {
            ItemStack tempStack = craftingInventory.getStackInSlot( iTempSlot );

            if ( tempStack != null )
            {
                if ( isProgressiveItem( tempStack ) )
                {
                	if ( progressiveItemStack == null )
                	{
                		progressiveItemStack = tempStack;
                	}
                	else
                	{
                		return null;
                	}
                }
                else
                {
                	return null;
                }
            }
        }
		
		if (progressiveItemStack != null)
		{
			ItemStack resultStack = null;
			
			int internalBladeDamage;
		        
			internalBladeDamage = progressiveItemStack.getTagCompound().getInteger("damage");
			
			if (!isIron(progressiveItemStack))
			{
	        	resultStack = new ItemStack(BPMDefinitions.flintKnife, 1, internalBladeDamage);
	        	
			}
			else
			{
				resultStack = new ItemStack(BPMDefinitions.ironKnife, 1, internalBladeDamage);
			}
			
			
			return resultStack;
		}
		
		return null; 
		
	}
	
	private boolean isIron(ItemStack stack)
	{
		int iItemID = stack.itemID;
		
		if (iItemID == BPMDefinitions.leatherCuttingIron.itemID
				|| iItemID == BPMDefinitions.ribCuttingIron.itemID)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public int getRecipeSize() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public ItemStack getRecipeOutput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean HasSecondaryOutput() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean matches(IRecipe recipe) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack[] getSecondaryOutput(IInventory inventory) {
		// TODO Auto-generated method stub
		return null;
	}   
}

