package net.minecraft.src;

import java.util.Random;

//hi!
public class BPM extends FCAddOn
{
	

/**
 * @author EpicAaron29 (aaron on the discord)
 *
 */

	public static BPM instance = new BPM();
	
    private BPM() 
    {
        super("uwu~ you play BTW???", ";)", "");
    }

	@Override
	public void Initialize() 
	{
		
		FCAddOnHandler.LogMessage(this.getName() + " Version " + this.getVersionString() + " Getting Excited!!!");
		
    	BPMDefinitions.addDefinitions();
    	BPMDefinitions.addTileEntityDefinitions(); //for time cube
    	BPMRecipes.addRecipes();
		
    	FCAddOnHandler.LogMessage(this.getName() + " uwu~ you play BTW???");

	}

	public String GetLanguageFilePrefix()
	{
		return "SuperBTW";
	}
	

	protected WorldGenerator branchGen;
    protected int branchesPerChunk;
	
	@Override
	//ADDING BRANCHES CODE!
	public void decorateWorld(FCIBiomeDecorator decorator, World world, Random rand, int x, int z, BiomeGenBase biome) 
	{
        this.branchGen = new BPMWorldGenBranches(BPMDefinitions.branchBlock.blockID);
        this.branchesPerChunk = 20;
        
        //AARON ADDED: flower placement, literally
        for (int var2 = 0; var2 < this.branchesPerChunk; ++var2)
        {
            int xPos = x + rand.nextInt(16) + 8;
            int yPos = rand.nextInt(128);
            int zPos = z + rand.nextInt(16) + 8;
            this.branchGen.generate(world, rand, xPos, yPos, zPos);
        }
	}	@Override
	public void PostInitialize() {
		//FCBetterThanWolves.fcBlockHempCrop.blockID, BPMBlockHempCrop.class, BPM.instance
		Block.replaceBlock(FCBetterThanWolves.fcBlockHempCrop.blockID, BPMBlockHempCrop.class, new String[] {"Stuff & Things"}, instance);
	}

}