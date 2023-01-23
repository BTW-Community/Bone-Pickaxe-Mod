package net.minecraft.src;

public class BPMItemFlintKnife extends BPMItemKnife
{
	static int durability = 15;
	private int m_iWeapondDamage;
	
	public BPMItemFlintKnife(int iItemID)
	{
		super(iItemID);
		setUnlocalizedName( "SuperBTWItemFlintBlade" );  
		SetFilterableProperties( Item.m_iFilterable_Narrow );
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setMaxDamage(durability);
		m_iWeaponDamage = 3; //slightly weaker than stone axe
		
        this.setMaxDamage(durability);
	}
	
	public int getMaterial()
	{
		return 0; //0 = flint knife
	}
	
    public float getStrVsBlock( ItemStack stack, World world, Block block, int i, int j, int k )
    {
    	float fStrength = 1;//super.getStrVsBlock( stack, world, block, i, j, k );
    	
    	if ( block.blockID == Block.web.blockID 
    			|| block.blockID == FCBetterThanWolves.fcBlockWeb.blockID)
    	{	
    		return fStrength *= 30;
    	}
    	
        Material material = block.blockMaterial;
        
        if ( material == Material.plants || material == Material.vine || material == Material.coral || material == Material.leaves || material == Material.pumpkin || material == FCBetterThanWolves.fcMaterialLog)
        {
        	return 3.0F;
        } 
        
//        if (block.blockID == Block.wood.blockID)
//        {
//        	return 3.0F;
//        }
        
    	return fStrength;
    }
}
