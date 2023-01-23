package net.minecraft.src;

public class BPMItemBeefRib extends FCItemFood 
{
	public BPMItemBeefRib (int iItemID) 
	{
		super (iItemID, 2, 0.25F, false, "BPMItemBeefRib", true);
    	
		SetIncineratedInCrucible();
    	setUnlocalizedName( "SuperBTWItemCowRib" );
    	
    	this.setCreativeTab(CreativeTabs.tabFood);
    	
    	SetStandardFoodPoisoningEffect();
    	
    	maxStackSize = 1;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		// TODO Auto-generated method stub
		//System.out.println("ATTEMPTING TO EAT RAW RIB? Idiot");
		par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
		return super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
	}
}
