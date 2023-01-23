package net.minecraft.src;

public class BPMItemBranch extends Item
{
	public BPMItemBranch (int iItemID) 
	{
		//, SuperBTWDefinitions.branchBlock.blockID
		super (iItemID);
		
		setFull3D();
		SetBuoyant();
		SetFurnaceBurnTime( FCEnumFurnaceBurnTime.SHAFT );
    	SetIncineratedInCrucible();
    	SetFilterableProperties( m_iFilterable_Narrow );
    	setUnlocalizedName( "SuperBTWItemBranch" );
    	
    	this.setCreativeTab(CreativeTabs.tabMaterials);
		
	}
}
