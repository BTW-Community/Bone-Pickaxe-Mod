package net.minecraft.src;

public class BPMDefinitions {
	
	private static final int
		id_ironKnife = 20001,
		id_flintKnife = 20002,
		id_bonePickaxe = 20003,
		id_beefRib = 20004,
		id_cookedBeefRib = 20005,
		id_rib = 20006,
		id_deathClub = 20007,
		id_stoneHoeNew = 20008,
		id_flintKnapping = 20009,
		id_leatherCutting = 20010,
		id_ribCutting = 20011,
		id_branch = 20012
		;
	
	private static final int
		id_meatCube = 2000,
		id_branchBlock = 2001,
		id_timeCube = 2002
		;
		
	public static Item bonePickaxe;
	public static Item beefRib;
	public static Item cookedBeefRib;
	public static Item rib;
	public static Item stoneHoeNew;
	public static Item flintKnife;
	public static Item flintKnapping;
	public static Item leatherCutting;
	public static Item ribCutting;
	public static Item branch;
	
	public static Block branchBlock;
	public static Block timeCube;
	public static Block meatCube;
	
	public static void addTileEntityDefinitions()
	{
		addTileEntityDefsMapping();

	}
	
	public static void addDefinitions() 
	{
		bonePickaxe = new BPMItemBonePickaxe(id_bonePickaxe - 256);
		beefRib = new BPMItemBeefRib(id_beefRib - 256);
		cookedBeefRib = new BPMItemCookedBeefRib(id_cookedBeefRib - 256);
		rib = new BPMItemRib(id_rib - 256);
		//SET HOE TO BONE!
		stoneHoeNew = ( new FCItemHoe( id_stoneHoeNew, EnumToolMaterial./*BONE*/BONE ) ).setUnlocalizedName( "hoeStone" );
		flintKnife = new BPMItemFlintKnife(id_flintKnife - 256);
		flintKnapping = new BPMItemFlintKnapping(id_flintKnapping - 256);
		leatherCutting = new BPMItemLeatherCutting(id_leatherCutting - 256);
		ribCutting = new BPMItemRibCutting(id_ribCutting - 256);
		branch = new BPMItemBranch(id_branch - 256);
		
		FCBetterThanWolves.fcBlockHempCrop = Block.replaceBlock(FCBetterThanWolves.fcBlockHempCrop.blockID, BPMBlockHempCrop.class, BPM.instance);
	
		meatCube = new BPMBlockMeatCube(id_meatCube);
		Item.itemsList[meatCube.blockID] = new ItemBlock(meatCube.blockID - 256);
		
		branchBlock = new BPMBlockBranch(id_branchBlock);
		Item.itemsList[branchBlock.blockID] = new ItemBlock(branchBlock.blockID - 256); 
	}
	
	private static void addTileEntityDefsMapping()
	{
		timeCube = new BPMBlockTimeCube(id_timeCube, null);
		Item.itemsList[timeCube.blockID] = new ItemBlock(timeCube.blockID - 256)
				.setMaxStackSize( 1 );
		
		TileEntity.addMapping(BPMBlockTimeCube.class, "timeCube");
	}

}
