package net.minecraft.src;

public class BPMItemBonePickaxe extends FCItemPickaxe
{

	public BPMItemBonePickaxe (int iItemID) 
	{
		//super (iItemID, EnumToolMaterial.BONE);
		super (iItemID, EnumToolMaterial.BONE);
		
    	SetIncineratedInCrucible();
    	setUnlocalizedName( "SuperBTWItemBonePickaxe" );
    	
    	this.setCreativeTab(CreativeTabs.tabTools);
		
	}
	
}
