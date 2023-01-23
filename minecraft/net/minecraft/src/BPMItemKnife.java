package net.minecraft.src;

public class BPMItemKnife extends Item
{
	protected int m_iWeaponDamage = 0;
	
	protected BPMItemKnife(int iItemID)
	{
		super(iItemID);
		setFull3D();
		this.setMaxStackSize(1);
		
		SetFilterableProperties( Item.m_iFilterable_Narrow );
	}
	
	public int getMaterial()
	{
		return 1; //0 = flint, 1 = iron
	}
	
    public int getDamageVsEntity( Entity entity )
    {
        return m_iWeaponDamage;
    }
    
    public boolean onBlockDestroyed( ItemStack stack, World world, int iBlockID, int i, int j, int k, EntityLiving usingEntity )
    {
    	stack.damageItem( 1, usingEntity );
        return true;
    }
    
    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
    {
        par1ItemStack.damageItem(1, par3EntityLiving);
        return true;
    }
    
    private boolean isDamaged;
    //this is a boolean because sometimes I make recipes that eat the knife rather than damage it!
    
    @Override
    public void OnUsedInCrafting(int iItemDamage, EntityPlayer player, ItemStack outputStack)
    {
    	isDamaged = false;
    }
    
    public boolean IsDamagedInCrafting() {
    	
    	return isDamaged;
    }
    
    

}