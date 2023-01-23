package net.minecraft.src;

public class BPMItemDeathClub extends FCItemClub
{
	
    public static final int m_iWeaponDamage = 5;    
    public static final int m_iDurability = 20;
    
    public BPMItemDeathClub( int iItemID )
    {
        super( iItemID, m_iDurability, m_iWeaponDamage, "SuperBTWItemDeathClub" );
        
    	SetFurnaceBurnTime( FCEnumFurnaceBurnTime.SHAFT );   	
    }

}