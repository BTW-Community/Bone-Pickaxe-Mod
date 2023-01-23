package net.minecraft.src;

public class BPMBlockTimeCube extends BlockContainer 
{

	protected BPMBlockTimeCube(int par1, Material par2Material) 
	{
		super(par1, Material.redstoneLight);
		setUnlocalizedName("tnt_bottom");
		this.setCreativeTab(CreativeTabs.tabTransport);
		setHardness(10);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		// TODO Auto-generated method stub
		BPMTileEntityTimeCube var2 = new BPMTileEntityTimeCube();
		return var2;
	}

}
