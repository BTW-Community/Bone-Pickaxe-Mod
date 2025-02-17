// FCMOD

package net.minecraft.src;

import java.util.List;

import com.prupe.mcpatcher.mal.block.RenderBlocksUtils;

public abstract class FCUtilsPrimitiveGeometric
{
	public abstract FCUtilsPrimitiveGeometric MakeTemporaryCopy();
	
	public abstract void RotateAroundJToFacing( int iFacing );
	
	public abstract void TiltToFacingAlongJ( int iFacing );
	
	public abstract void AddToRayTrace( FCUtilsRayTraceVsComplexBlock rayTrace );
	
	public abstract void Translate( double dDeltaX, double dDeltaY, double dDeltaZ );
	
    public void AddIntersectingBoxesToCollisionList( World world, int i, int j, int k, AxisAlignedBB boxToIntersect, List collisionList )
    {
    	// not every primitive type will add boxes
    }
    
    public int GetAssemblyID()
    {
    	// not every primitive type will support assembly IDs
    	
    	return -1;
    }
	
	//----------- Client Side Functionality -----------//

	public abstract boolean RenderAsBlock( RenderBlocks renderBlocks, Block block, int i, int j, int k );
	
	public abstract boolean RenderAsBlockWithColorMultiplier( RenderBlocks renderBlocks, Block block, int i, int j, int k, float fRed, float fGreen, float fBlue );
	
	public boolean RenderAsBlockWithColorMultiplier(RenderBlocks renderBlocks, Block block, int x, int y, int z) {
        int colorMultiplier = block.colorMultiplier(renderBlocks.blockAccess, x, y, z);
        
        float red = (float) (colorMultiplier >> 16 & 255) / 255F;
        float green = (float) (colorMultiplier >> 8 & 255) / 255F;
        float blue = (float) (colorMultiplier & 255) / 255F;
        
        RenderBlocksUtils.setupColorMultiplier(block, renderBlocks.blockAccess, x, y, z, renderBlocks.hasOverrideBlockTexture(), red, green, blue);
        
        return RenderAsBlockWithColorMultiplier(renderBlocks, block, x, y, z, red, green, blue);
	}
	
	public abstract boolean RenderAsBlockWithTexture( RenderBlocks renderBlocks, Block block, int i, int j, int k, Icon icon );
	
	public abstract boolean RenderAsBlockFullBrightWithTexture( RenderBlocks renderBlocks, Block block, int i, int j, int k, Icon icon );
	
	public abstract void RenderAsItemBlock( RenderBlocks renderBlocks, Block block, int iItemDamage );
	
	public abstract void RenderAsFallingBlock( RenderBlocks renderBlocks, Block block, int i, int j, int k, int iMetadata );
}
