package net.minecraft.src;

import java.util.ArrayList;

public class BPMRecipesRibCutting implements IRecipe{
	public static ItemStack blade;

	 public boolean matches( InventoryCrafting craftingInventory, World world )
	    {
	        ItemStack bladeStack = null;
	        ItemStack leatherStack = null;

	        for ( int iTempSlot = 0; iTempSlot < craftingInventory.getSizeInventory(); ++iTempSlot )
	        {
	            ItemStack tempStack = craftingInventory.getStackInSlot( iTempSlot );

	            if ( tempStack != null )
	            {
	                if ( isBlade( tempStack ) )
	                {
	                	if ( bladeStack == null )
	                	{
	                		bladeStack = tempStack;
	                	}
	                	else
	                	{
	                		return false;
	                	}
	                }
	                else if ( isLeather( tempStack ) )
	                {
	                    if ( leatherStack == null )
	                    {
	                        leatherStack = tempStack;
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

	        return bladeStack != null && leatherStack != null;
	   }
	 
	 private boolean isBlade( ItemStack stack)
	 {
	    	int iItemID = stack.itemID;
   		
	    	if ( stack.getItem() instanceof BPMItemKnife //||
	    		/*iItemID == SuperBTWDefinitions.ironBlade.itemID*/ )
	    	{
	    		
	    		return true;
	    	}
	    	
	    	return false;
	 }
	 
	 private boolean isLeather( ItemStack stack )
	 {
	    	int iItemID = stack.itemID;
	    		    		
	    	if ( iItemID == BPMDefinitions.beefRib.itemID )
	    	{
	    		return true;	
	    	}
	    	
	    	return false;
	 }

		public ItemStack getCraftingResult(InventoryCrafting craftingInventory) {
		    {
		        ItemStack bladeStack = null;
		        ItemStack leatherStack = null;
		        
		        for ( int iTempSlot = 0; iTempSlot < craftingInventory.getSizeInventory(); ++iTempSlot )
		        {
		            ItemStack tempStack = craftingInventory.getStackInSlot( iTempSlot );

		            if ( tempStack != null )
		            {
		                if ( isBlade( tempStack ) )
		                {
		                	if ( bladeStack == null )
		                	{
		                		bladeStack = tempStack;
		                	}
		                	else
		                	{
		                		return null;
		                	}
		                }
		                else if ( isLeather( tempStack ) )
		                {
		                    if ( leatherStack == null )
		                    {
		                        leatherStack = tempStack;                        
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
		        
		        if ( leatherStack != null && bladeStack != null )
		        {
		        	ItemStack resultStack = null;
		        	
		        	ItemStack inheritedBlade = bladeStack;
		        	

		        	BPMItemKnife bladeItem = (BPMItemKnife)bladeStack.getItem();
	            	NBTTagCompound newTag = new NBTTagCompound();
	            	
		        	switch (bladeItem.getMaterial()) {
		        	
		        	case 0:

		            	resultStack = new ItemStack(BPMDefinitions.ribCutting, 1, 200); //this value must match!
		            	break;
		            	
		            	//test output
		            	//resultStack = bladeStack;
		        		
		        	case 1:
		        		
		            	resultStack = new ItemStack(BPMDefinitions.ribCuttingIron, 1, 75);
		            	break;
		        		
		        	default:
		        		
		            	return null;
		        	
		        	}
	            	
	            	resultStack.setTagCompound(newTag);
	            	resultStack.getTagCompound().setInteger("damage", inheritedBlade.getItemDamage());
		            
	            	
		            return resultStack;

		        }
		        
		    	return null;
		    }
		}


		public int getRecipeSize() {
			// TODO Auto-generated method stub
			return 2;
		}


		public ItemStack getRecipeOutput() {
			// TODO Auto-generated method stub
			return null;
		}


		public boolean HasSecondaryOutput() {
			// TODO Auto-generated method stub
			return false;
		}


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
