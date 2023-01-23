// FCMOD

package net.minecraft.src;

public class FCDamageSourceCustom extends DamageSource
{
    public static DamageSource m_DamageSourceSaw = new FCDamageSourceCustom( "fcSaw" );	
    public static DamageSource m_DamageSourceChoppingBlock = new FCDamageSourceCustom( "fcChoppingBlock" );	
    public static DamageSource m_DamageSourceGroth = new FCDamageSourceCustom( "fcGroth" ).setDamageBypassesArmor();	
    public static DamageSource m_DamageSourceGrothSpores = new FCDamageSourceCustom( "fcGrothSpores" ).setDamageBypassesArmor();	
    public static DamageSource m_DamageSourceMelon = new FCDamageSourceCustom( "fcMelon" );	
    public static DamageSource m_DamageSourcePumpkin = new FCDamageSourceCustom( "fcPumpkin" );	
    public static DamageSource m_DamageSourceGloom = new FCDamageSourceCustom( "fcGloom" ).setDamageBypassesArmor();
	
    protected FCDamageSourceCustom( String sName )
    {
    	super( sName );
    }
}
