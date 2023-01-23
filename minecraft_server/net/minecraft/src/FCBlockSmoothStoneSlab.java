package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class FCBlockSmoothStoneSlab extends BlockHalfSlab {
    public FCBlockSmoothStoneSlab( int iBlockID, boolean bDoubleSlab ) {
        super( iBlockID, bDoubleSlab, Material.rock );

        SetPicksEffectiveOn();

        setHardness(2.0F);
        setResistance(10.0F);

        setStepSound(soundStoneFootstep);
        setUnlocalizedName("stoneSlab");

        setCreativeTab( CreativeTabs.tabBlock );
    }

    @Override
    public int idDropped( int iMetadata, Random rand, int iFortuneModifier ) {
        return FCBetterThanWolves.fcBlockSmoothStoneSlabSingle.blockID;
    }

    @Override
	public int damageDropped(int metadata) {
		return getStrata(metadata); 
	}

    @Override
    public String getFullSlabName( int iMetadata ) {
        return super.getUnlocalizedName() + ".stone";
    }    

    @Override
    protected ItemStack createStackedBlock( int iMetadata ) {
        return new ItemStack( FCBetterThanWolves.fcBlockSmoothStoneSlabSingle.blockID, 2, getStrata(iMetadata) );
    }

    @Override
    public boolean DropComponentItemsOnBadBreak(World world, int i, int j, int k, int iMetadata, float fChanceOfDrop) {
    	int numItems = isDoubleSlab ? 2 : 1;
    	DropItemsIndividualy( world, i, j, k, FCBetterThanWolves.fcBlockCobblestoneLooseSlab.blockID, numItems, getStrata(iMetadata) << 2, fChanceOfDrop );

    	return true;
    }

    @Override
    public int getDamageValue(World par1World, int x, int y, int z) {
        return getStrata(par1World, x, y, z);
    }

    @Override
	public boolean HasContactPointToFullFace( IBlockAccess blockAccess, int i, int j, int k, int iFacing ) {
    	if ( !isDoubleSlab  && iFacing < 2 ) {
        	boolean bIsUpsideDown = GetIsUpsideDown( blockAccess, i, j, k );

        	return bIsUpsideDown == ( iFacing == 1 );
    	}    	

		return true;
	}

    @Override
	public boolean HasContactPointToSlabSideFace( IBlockAccess blockAccess, int i, int j, int k, int iFacing, boolean bIsSlabUpsideDown ) {
		return isDoubleSlab || bIsSlabUpsideDown == GetIsUpsideDown( blockAccess, i, j, k );
	}

	@Override
	public boolean HasMortar( IBlockAccess blockAccess, int i, int j, int k ) {
		return true;
	}

    //------------- Class Specific Methods ------------//

	/** returns 0 - 2 regardless of what metadata is used to store strata. BEWARE: different blocks store strata differently */
    public int getStrata( IBlockAccess blockAccess, int i, int j, int k ) {
		return getStrata( blockAccess.getBlockMetadata( i, j, k ) );
    }

    /** returns 0 - 2 regardless of what metadata is used to store strata. BEWARE: different blocks store strata differently */
    public int getStrata( int iMetadata ) {
    	return iMetadata & 3;
    }

	//----------- Client Side Functionality -----------//
}