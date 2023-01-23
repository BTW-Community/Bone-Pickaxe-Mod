//FCMOD

package net.minecraft.src;

public class FCItemStone extends Item
{
	private final int m_iWeaponDamage;
	
    public FCItemStone( int iItemID )
    {
        super( iItemID );
        
    	setUnlocalizedName( "fcItemStone" );
    	
    	SetFilterableProperties( Item.m_iFilterable_Small );
    	
    	setCreativeTab( CreativeTabs.tabMaterials );
    	
    	//AARON added some damage :)
    	m_iWeaponDamage = 1; //just like in genesis!

    }
    
	
    public int getDamageVsEntity( Entity entity )
    {
        return m_iWeaponDamage;
    }
    
    //------------- Class Specific Methods ------------//
    
	//------------ Client Side Functionality ----------//
}
    

