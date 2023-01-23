package net.minecraft.src;

public class BPMItemRib extends Item 
{
	public BPMItemRib (int iItemID)
	{
		super(iItemID);
		
		setFull3D();
		
    	SetIncineratedInCrucible();
    	setUnlocalizedName( "SuperBTWItemRib" );
    	
    	this.setCreativeTab(CreativeTabs.tabMaterials);
	}
	
}
