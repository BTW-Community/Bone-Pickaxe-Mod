package net.minecraft.src;

import java.util.List;

public class FCItemStoneBrick extends Item {

	public FCItemStoneBrick( int iItemID ) {
		super( iItemID );

		setMaxDamage( 0 );
		setHasSubtypes( true );
		
		setCreativeTab( CreativeTabs.tabMaterials );	    
	}

	//------------- Class Specific Methods ------------//

	//------------ Client Side Functionality ----------//
}