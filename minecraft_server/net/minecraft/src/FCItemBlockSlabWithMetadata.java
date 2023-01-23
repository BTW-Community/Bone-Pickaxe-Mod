package net.minecraft.src;

public class FCItemBlockSlabWithMetadata extends FCItemBlockSlab {

	    public FCItemBlockSlabWithMetadata( int iItemID )
	    {
	        super( iItemID );        
	    }

	    @Override
	    public int getMetadata(int par1)
	    {
	        return par1;
	    }
}