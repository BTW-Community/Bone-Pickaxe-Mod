// FCMOD

package net.minecraft.src;

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.src.FCCraftingManagerTurntableRecipe.TurntableEffect;
import net.minecraft.src.FCEntityVillager.TradeConditional;
import net.minecraft.src.FCEntityVillager.TradeEffect;
import net.minecraft.src.FCEntityVillager.WeightedMerchantRecipeEntry;

public abstract class FCRecipes
{
	private static final int ignoreMetadata = FCUtilsInventory.m_iIgnoreMetadata;
	
	public static void AddAllModRecipes()
	{
		RemoveVanillaRecipes();
		
		AddClusteredRecipes();
		
		AddBlockRecipes();
		AddItemRecipes();	
		AddDyeRecipes();
		AddAlternateVanillaRecipes();
		AddConversionRecipes();
		AddSmeltingRecipes();
		AddCampfireRecipes();
		AddAnvilRecipes();
		AddCauldronRecipes();
		AddCrucibleRecipes();
		AddMillStoneRecipes();
		addKilnRecipes();
		addSawRecipes();
		addPistonPackingRecipes();
		addHopperFilteringRecipes();
		addLogChoppingRecipes();
		addTurntableRecipes();
		AddTuningForkRecipes();
		AddSubBlockRecipes();
		
		AddLegacyConversionRecipes();
		AddCustomRecipeClasses();
		
		addVillagerTrades();
		
		AddDebugRecipes();
	}
	
    public static ShapedRecipes AddRecipe( ItemStack itemStack, Object aobj[] )
	{
    	return CraftingManager.getInstance().addRecipe( itemStack, aobj );
	}
	
    public static ShapelessRecipes AddShapelessRecipe( ItemStack itemStack, Object aobj[] )
	{
    	return CraftingManager.getInstance().AddShapelessRecipe( itemStack, aobj );
	}
    
    public static ShapelessRecipes AddShapelessRecipeWithSecondaryOutputIndicator( ItemStack itemStack, ItemStack secondaryOutput, Object aobj[] )
	{
    	return AddShapelessRecipeWithSecondaryOutputIndicator(itemStack, new ItemStack[] {secondaryOutput}, aobj);
	}
    
    public static ShapelessRecipes AddShapelessRecipeWithSecondaryOutputIndicator( ItemStack itemStack, ItemStack[] secondaryOutputs, Object aobj[] )
	{
    	ShapelessRecipes recipe = CraftingManager.getInstance().AddShapelessRecipe( itemStack, aobj );
    	
    	if ( recipe != null )
    	{
    		recipe.SetSecondaryOutput(secondaryOutputs);
    	}
    	
    	return recipe;
	}
    
    public static ShapedRecipes AddShapedRecipeWithCustomClass( Class<? extends ShapedRecipes>recipeClass, ItemStack itemStack, Object aobj[] )
	{
    	return CraftingManager.getInstance().AddShapedRecipeWithCustomClass( recipeClass, itemStack, aobj );
	}
    
    public static void RemoveVanillaRecipe( ItemStack itemStack, Object aobj[] )
    {
    	CraftingManager.getInstance().RemoveRecipe( itemStack, aobj );
    }
    
    public static void RemoveVanillaShapelessRecipe( ItemStack itemStack, Object aobj[] )
    {
    	CraftingManager.getInstance().RemoveShapelessRecipe( itemStack, aobj );
    }
    
    public static void AddAnvilRecipe( ItemStack itemStack, Object aobj[] )
	{
    	FCCraftingManagerAnvil.getInstance().addRecipe( itemStack, aobj );
	}
	
    public static void AddShapelessAnvilRecipe( ItemStack itemStack, Object aobj[] )
	{
    	FCCraftingManagerAnvil.getInstance().addShapelessRecipe( itemStack, aobj );
	}
    
    public static void RemoveAnvilRecipe( ItemStack itemStack, Object aobj[] )
    {
    	FCCraftingManagerAnvil.getInstance().RemoveRecipe( itemStack, aobj );
    }
    
    public static void RemoveShapelessAnvilRecipe( ItemStack itemStack, Object aobj[] )
    {
    	FCCraftingManagerAnvil.getInstance().RemoveShapelessRecipe( itemStack, aobj );
    }
    
    public static void AddCauldronRecipe( ItemStack outputStack, ItemStack inputStacks[] )
    {
    	FCCraftingManagerCauldron.getInstance().AddRecipe( outputStack, inputStacks );
	}
    
    public static void AddCauldronRecipe( ItemStack outputStacks[], ItemStack inputStacks[] )
    {
    	FCCraftingManagerCauldron.getInstance().AddRecipe( outputStacks, inputStacks );
	}
    
    public static void AddStokedCauldronRecipe( ItemStack outputStack, ItemStack inputStacks[] )
    {
    	FCCraftingManagerCauldronStoked.getInstance().AddRecipe( outputStack, inputStacks );
	}
    
    public static void AddStokedCauldronRecipe( ItemStack outputStacks[], ItemStack inputStacks[] )
    {
    	FCCraftingManagerCauldronStoked.getInstance().AddRecipe( outputStacks, inputStacks );
	}
    
    public static void AddCrucibleRecipe( ItemStack outputStack, ItemStack inputStacks[] )
    {
    	FCCraftingManagerCrucible.getInstance().AddRecipe( outputStack, inputStacks );
	}
    
    public static void AddCrucibleRecipe( ItemStack outputStacks[], ItemStack inputStacks[] )
    {
    	FCCraftingManagerCrucible.getInstance().AddRecipe( outputStacks, inputStacks );
	}
    
    public static void AddStokedCrucibleRecipe( ItemStack outputStack, ItemStack inputStacks[] )
    {
    	FCCraftingManagerCrucibleStoked.getInstance().AddRecipe( outputStack, inputStacks );
	}
    
    public static void AddStokedCrucibleRecipe( ItemStack outputStacks[], ItemStack inputStacks[] )
    {
    	FCCraftingManagerCrucibleStoked.getInstance().AddRecipe( outputStacks, inputStacks );
	}
    
    public static void AddMillStoneRecipe( ItemStack outputStack, ItemStack inputStacks[] )
    {
    	FCCraftingManagerMillStone.getInstance().AddRecipe( outputStack, inputStacks );
	}
    
    public static void AddMillStoneRecipe( ItemStack outputStack, ItemStack inputStack )
    {
    	FCCraftingManagerMillStone.getInstance().AddRecipe( outputStack, inputStack );
	}
    
    public static void AddMillStoneRecipe( ItemStack outputStacks[], ItemStack inputStacks[] )
    {
    	FCCraftingManagerMillStone.getInstance().AddRecipe( outputStacks, inputStacks );
	}
    
    public static void AddCampfireRecipe( int iInputItemID, ItemStack outputStack ) 
    {
    	FCCraftingManagerCampfire.instance.AddRecipe( iInputItemID, outputStack );
    }
    
    //Kiln recipes
    //Cook time multiplier is a byte just so that the method signatures are all different
    public static void addKilnRecipe(ItemStack outputStack, Block block) {
    	addKilnRecipe(outputStack, block, ignoreMetadata, (byte) 1);
    }
    
    public static void addKilnRecipe(ItemStack outputStack, Block block, int metadata) {
    	addKilnRecipe(outputStack, block, metadata, (byte) 1);
    }

    public static void addKilnRecipe(ItemStack outputStack, Block block, int[] metadatas) {
    	addKilnRecipe(outputStack, block, metadatas, (byte) 1);
    }
    
    public static void addKilnRecipe(ItemStack outputStack, Block block, byte cookTimeMultiplier) {
    	addKilnRecipe(outputStack, block, ignoreMetadata, cookTimeMultiplier);
    }

    public static void addKilnRecipe(ItemStack outputStack, Block block, int metadata, byte cookTimeMultiplier) {
    	addKilnRecipe(outputStack, block, new int[] {metadata}, cookTimeMultiplier);
    }
    
    public static void addKilnRecipe(ItemStack outputStack, Block block, int[] metadatas, byte cookTimeMultiplier) {
    	addKilnRecipe(new ItemStack[] {outputStack}, block, metadatas, cookTimeMultiplier);
    }
    
    public static void addKilnRecipe(ItemStack[] outputStacks, Block block) {
    	addKilnRecipe(outputStacks, block, ignoreMetadata, (byte) 1);
    }
    
    public static void addKilnRecipe(ItemStack[] outputStacks, Block block, int metadata) {
    	addKilnRecipe(outputStacks, block, new int[] {metadata}, (byte) 1);
    }

    public static void addKilnRecipe(ItemStack[] outputStacks, Block block, int[] metadatas) {
    	addKilnRecipe(outputStacks, block, metadatas, (byte) 1);
    }
    
    public static void addKilnRecipe(ItemStack[] outputStacks, Block block, byte cookTimeMultiplier) {
    	addKilnRecipe(outputStacks, block, ignoreMetadata, cookTimeMultiplier);
    }

    public static void addKilnRecipe(ItemStack[] outputStacks, Block block, int metadata, byte cookTimeMultiplier) {
    	addKilnRecipe(outputStacks, block, new int[] {metadata}, cookTimeMultiplier);
    }
    
    public static void addKilnRecipe(ItemStack[] outputStacks, Block block, int[] metadatas, byte cookTimeMultiplier) {
    	FCCraftingManagerKiln.instance.addRecipe(outputStacks, block, metadatas, cookTimeMultiplier);
    }
    
    //Saw recipes
    public static void addSawRecipe(ItemStack outputStack, Block block) {
    	addSawRecipe(outputStack, block, ignoreMetadata);
    }
    
    public static void addSawRecipe(ItemStack outputStack, Block block, int metadata) {
    	addSawRecipe(new ItemStack[] {outputStack}, block, new int[] {metadata});
    }
    
    public static void addSawRecipe(ItemStack outputStack, Block block, int[] metadatas) {
    	addSawRecipe(new ItemStack[] {outputStack}, block, metadatas);
    }
    
    public static void addSawRecipe(ItemStack[] outputStacks, Block block) {
    	addSawRecipe(outputStacks, block, ignoreMetadata);
    }
    
    public static void addSawRecipe(ItemStack[] outputStacks, Block block, int metadata) {
    	addSawRecipe(outputStacks, block, new int[] {metadata});
    }
    
    public static void addSawRecipe(ItemStack[] outputStacks, Block block, int[] metadata) {
    	FCCraftingManagerSaw.instance.addRecipe(outputStacks, block, metadata);
    }
    
    //Piston Packing Recipes
    public static void addPistonPackingRecipe(Block output, ItemStack inputStack) {
    	addPistonPackingRecipe(output, 0, inputStack);
    }
    
    public static void addPistonPackingRecipe(Block output, int outputMetadata, ItemStack inputStack) {
    	addPistonPackingRecipe(output, outputMetadata, new ItemStack[] {inputStack});
    }
    
    public static void addPistonPackingRecipe(Block output, ItemStack[] inputStacks) {
    	addPistonPackingRecipe(output, 0, inputStacks);
    }
    
    public static void addPistonPackingRecipe(Block output, int outputMetadata, ItemStack[] inputStacks) {
    	FCCraftingManagerPistonPacking.instance.addRecipe(output, outputMetadata, inputStacks);
    }
    
    //Hopper filtering
    /**
     * Note that hopper recipe inputs are limited to stack sizes of 1 (which is enforced upon adding the recipe)
     * @param hopperOutput The item that goes inside the hopper
     * @param input The item to be filtered
     * @param filterUsed The filter to use
     */
    public static void addHopperFilteringRecipe(
    		ItemStack hopperOutput,
    		ItemStack input, ItemStack filterUsed)
    {
    	FCCraftingManagerHopperFilter.instance.addRecipe(hopperOutput, null, input, filterUsed);
    }
    
    /**
     * Note that hopper recipe inputs are limited to stack sizes of 1 (which is enforced upon adding the recipe)
     * @param hopperOutput The item that goes inside the hopper
     * @param filteredOutput The item that stays outside the hopper
     * @param input The item to be filtered
     * @param filterUsed The filter to use
     */
    public static void addHopperFilteringRecipe(
    		ItemStack hopperOutput, ItemStack filteredOutput,
    		ItemStack input, ItemStack filterUsed)
    {
    	FCCraftingManagerHopperFilter.instance.addRecipe(hopperOutput, filteredOutput, input, filterUsed);
    }
    
    /**
     * Note that hopper recipe inputs are limited to stack sizes of 1 (which is enforced upon adding the recipe)
     * @param filteredOutput The item that stays outside the hopper
     * @param input The item to be filtered
     */
    public static void addHopperSoulRecipe(ItemStack filteredOutput, ItemStack input) {
    	FCCraftingManagerHopperFilter.instance.addSoulRecipe(filteredOutput, input);
    }
    
    public static void addLogChoppingRecipe(ItemStack output, ItemStack[] secondaryOutputs, ItemStack input) {
    	CraftingManager.getInstance().getRecipeList().add(new FCCraftingRecipeLogChopping(output, secondaryOutputs, input));
    }
    
    public static void addLogChoppingRecipe(ItemStack output, ItemStack[] secondaryOutputs, 
    		ItemStack outputLowQuality, ItemStack[] secondaryOutputsLowQuality, ItemStack input)
    {
    	CraftingManager.getInstance().getRecipeList().add(new FCCraftingRecipeLogChopping(output, secondaryOutputs, outputLowQuality, secondaryOutputsLowQuality, input));
    }
    
    public static FCCraftingManagerTurntableRecipe addTurntableRecipe(Block output, Block block, int rotationsToCraft) {
    	return addTurntableRecipe(output, 0, null, block, new int[] {ignoreMetadata}, rotationsToCraft);
    }
    
    public static FCCraftingManagerTurntableRecipe addTurntableRecipe(Block output, int outputMetadata, Block block, int rotationsToCraft) {
    	return addTurntableRecipe(output, outputMetadata, null, block, new int[] {ignoreMetadata}, rotationsToCraft);
    }
    
    public static FCCraftingManagerTurntableRecipe addTurntableRecipe(Block output, int outputMetadata, Block block, int metadata, int rotationsToCraft) {
    	return addTurntableRecipe(output, outputMetadata, null, block, new int[] {metadata}, rotationsToCraft);
    }
    
    public static FCCraftingManagerTurntableRecipe addTurntableRecipe(Block output, int outputMetadata, Block block, int[] metadatas, int rotationsToCraft) {
    	return addTurntableRecipe(output, outputMetadata, null, block, metadatas, rotationsToCraft);
    }
    
    public static FCCraftingManagerTurntableRecipe addTurntableRecipe(Block output, ItemStack[] itemsEjected, Block block, int rotationsToCraft) {
    	return addTurntableRecipe(output, 0, itemsEjected, block, new int[] {ignoreMetadata}, rotationsToCraft);
    }
    
    public static FCCraftingManagerTurntableRecipe addTurntableRecipe(Block output, int outputMetadata, ItemStack[] itemsEjected, Block block, int rotationsToCraft) {
    	return addTurntableRecipe(output, outputMetadata, itemsEjected, block, new int[] {ignoreMetadata}, rotationsToCraft);
    }
    
    public static FCCraftingManagerTurntableRecipe addTurntableRecipe(Block output, int outputMetadata, ItemStack[] itemsEjected, Block block, int metadata, int rotationsToCraft) {
    	return addTurntableRecipe(output, outputMetadata, itemsEjected, block, new int[] {metadata}, rotationsToCraft);
    }
    
    public static FCCraftingManagerTurntableRecipe addTurntableRecipe(Block output, int outputMetadata, ItemStack[] itemsEjected, Block block, int[] metadatas, int rotationsToCraft) {
    	return FCCraftingManagerTurntable.instance.addRecipe(output, outputMetadata, itemsEjected, block, metadatas, rotationsToCraft);
    }
    
	private static void AddClusteredRecipes()
	{
		// trying a different way of organizing here, trying to keep things simple for new blocks added
		
		// rotten flesh blocks
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockRottenFleshSlab, 6 ), new Object[] {
    		"###", 
    		'#', FCBetterThanWolves.fcBlockRottenFlesh
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockRottenFlesh ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcBlockRottenFleshSlab ),
    		new ItemStack( FCBetterThanWolves.fcBlockRottenFleshSlab )
		} );		
		
		AddShapelessRecipe( new ItemStack( Item.rottenFlesh, 9 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcBlockRottenFlesh ),
		} );
		
		AddShapelessRecipe( new ItemStack( Item.rottenFlesh, 4 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcBlockRottenFleshSlab ),
		} );
		
		// bone blocks
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockBoneSlab, 6 ), new Object[] {
    		"###", 
    		'#', new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 1, FCBlockAestheticOpaque.m_iSubtypeBone )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 1, FCBlockAestheticOpaque.m_iSubtypeBone ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcBlockBoneSlab ),
    		new ItemStack( FCBetterThanWolves.fcBlockBoneSlab )
		} );		
		
		AddShapelessRecipe( new ItemStack( Item.bone, 9 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 1, 
				FCBlockAestheticOpaque.m_iSubtypeBone )
		} );
		
		AddShapelessRecipe( new ItemStack( Item.bone, 4 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcBlockBoneSlab ),
		} );
		
		// creeper oyster blocks
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockCreeperOystersSlab, 6 ), new Object[] {
    		"###", 
    		'#', FCBetterThanWolves.fcBlockCreeperOysters
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockCreeperOysters ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcBlockCreeperOystersSlab ),
    		new ItemStack( FCBetterThanWolves.fcBlockCreeperOystersSlab )
		} );		
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemCreeperOysters, 16 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcBlockCreeperOysters ),
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemCreeperOysters, 8 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcBlockCreeperOystersSlab ),
		} );
		
		// smoothstone stairs
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockSmoothstoneStairs, 6 ), new Object[] {
    		"#  ", 
    		"## ", 
    		"###", 
    		'#', new ItemStack( Block.stone, 1, 0) 
		} );		
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockSmoothstoneStairsMidStrata, 6 ), new Object[] {
	    		"#  ", 
	    		"## ", 
	    		"###", 
	    		'#', new ItemStack( Block.stone, 1, 1) 
			} );	
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockSmoothstoneStairsDeepStrata, 6 ), new Object[] {
	    		"#  ", 
	    		"## ", 
	    		"###", 
	    		'#', new ItemStack( Block.stone, 1, 2) 
			} );	
		
		// blood wood stairs and slabs

		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockWoodBloodStairs, 6 ), new Object[] {
    		"#  ", 
    		"## ", 
    		"###", 
    		'#', new ItemStack( Block.planks, 1, 4 )
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockWoodBloodStairs, 1 ), new Object[] {
            "# ", 
            "##", 
            Character.valueOf('#'), new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 4 ) 
		} );
		
		AddRecipe( new ItemStack( Block.woodSingleSlab, 6, 4 ), new Object[] {
			"###", 
			Character.valueOf('#'), new ItemStack( Block.planks, 1, 4 )
		} );
		
		// clustered recipe functions

		AddEarlyGameRecipes();
		AddToolRecipes();
		AddLooseStoneRecipes();
		AddLooseBrickRecipes();
		AddLooseStoneBrickRecipes();
		AddLooseNetherBrickRecipes();
		AddTorchRecipes();
		AddWickerRecipes();
		AddStairRecipes();
		AddWoolAndKnittingRecipes();
		AddSawDustRecipes();
		AddMeatCuringRecipes();
		AddPaneRecipes();
		AddSnowRecipes();
		AddChickenFeedRecipes();
		AddFishingRecipes();		
		AddDirtRecipes();
		AddGravelRecipes();
		AddSandRecipes();
		AddMechanicalRecipes();
		AddOreRecipes();
		AddPastryRecipes();
	}
	
	private static void AddEarlyGameRecipes()
	{
		// Chisels		
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemChiselWood ), new Object[] {	    		
    		new ItemStack( Item.stick )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemChiselStone), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcItemStone, 1, ignoreMetadata  )
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemChiselIron ), new Object[] {
    		"XX", 
    		"XX", 
    		'X', FCBetterThanWolves.fcItemNuggetIron
		} );
		
		AddRecipe(new ItemStack(FCBetterThanWolves.fcItemChiselDiamond), new Object[] {
    		"X", 
    		'X', FCBetterThanWolves.fcItemIngotDiamond
		});
		
		// shears
		
		AddRecipe(new ItemStack(FCBetterThanWolves.fcItemShearsDiamond), new Object[] {
    		"X ",
    		" X",
    		'X', FCBetterThanWolves.fcItemIngotDiamond
		});

		// clubs
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemClubWood ), new Object[] {
    		"X", 
    		"X", 
    		'X', Item.stick
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemClubBone ), new Object[] {
    		"X", 
    		"X", 
    		'X', Item.bone
		} );
		
		// fire starters
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemFireStarterSticks ), new Object[] {
    		"XX", 
    		'X', Item.stick
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemFireStarterBow ), new Object[] {	    		
    		new ItemStack( Item.stick ),
    		new ItemStack( Item.stick ),
    		new ItemStack( Item.silk )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemFireStarterBow ), new Object[] {	    		
    		new ItemStack( Item.stick ),
    		new ItemStack( Item.stick ),
    		new ItemStack( FCBetterThanWolves.fcItemHempFibers )
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockCampfireUnlit ), new Object[] {
    		"XX", 
    		"XX", 
    		'X', Item.stick
		} );
		
		AddShapelessRecipeWithSecondaryOutputIndicator( new ItemStack( Item.stick ), new ItemStack(Item.silk), new Object[] {	    		
    		new ItemStack( Item.bow, 1, ignoreMetadata )
		} );		
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockFurnaceBrickIdle ), new Object[] {
    		"XX", 
    		"XX", 
    		'X', FCBetterThanWolves.fcBlockBrickLooseSlab
		} );
		
		AddShapelessRecipe( new ItemStack( Item.coal ), new Object[] {
    		new ItemStack( FCBetterThanWolves.fcItemCoalDust ),
    		new ItemStack( FCBetterThanWolves.fcItemCoalDust )
		} );
		
		AddShapelessRecipe( new ItemStack( Item.clay ), new Object[] {
    		new ItemStack( FCBetterThanWolves.fcItemPileClay ),
    		new ItemStack( FCBetterThanWolves.fcItemPileClay )
		} );
		
		// ladders
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockLadder, 2 ), new Object[] {
			"#S#", 
			"###", 
			"#S#", 
			'#', Item.stick,
        	'S', Item.silk
		} );

		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockLadder, 2 ), new Object[] {
			"#S#", 
			"###", 
			"#S#", 
			'#', Item.stick,
        	'S', FCBetterThanWolves.fcItemHempFibers
		} );
		
		// arrows
		
		AddShapelessRecipe( new ItemStack( Item.arrow , 4 ), new Object[] {
    		new ItemStack( Item.feather ),
    		new ItemStack( Item.stick ),
    		new ItemStack( Item.silk ),
    		new ItemStack( Item.flint )
		} );
		
		AddShapelessRecipe( new ItemStack( Item.arrow , 4 ), new Object[] {
    		new ItemStack( Item.feather ),
    		new ItemStack( Item.stick ),
    		new ItemStack( FCBetterThanWolves.fcItemHempFibers ),
    		new ItemStack( Item.flint )
		} );
		
		//other
		AddShapelessRecipe(new ItemStack(Item.melonSeeds, 2), new Object[] {
			new ItemStack(FCBetterThanWolves.fcItemMelonMashed)
		});
	}

	private static void AddToolRecipes()
	{
		AddStoneToolRecipes();
		
		AddRecipe( new ItemStack( Item.hoeIron ), new Object[] {
			"X#", 
			" #", 
			" #",
			'#', Item.stick,
			'X', Item.ingotIron			
		} );
		
		AddRecipe( new ItemStack( Item.hoeDiamond ), new Object[] {
			"X#", 
			" #", 
			" #",
			'#', Item.stick,
			'X', FCBetterThanWolves.fcItemIngotDiamond			
		} );
		
		AddRecipe( new ItemStack( Item.hoeGold ), new Object[] {
			"X#", 
			" #", 
			" #",
			'#', Item.stick,
			'X', Item.ingotGold			
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemDiamondPlate, 1 ), new Object[] {
    		"#X#",
    		" Y ",
    		'#', FCBetterThanWolves.fcItemStrap, 
    		'X', FCBetterThanWolves.fcItemIngotDiamond,
    		'Y', FCBetterThanWolves.fcItemPadding
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemArmorPlate, 1 ), new Object[] {
			"#X#",
    		" Y ",
    		'#', FCBetterThanWolves.fcItemStrap, 
    		'X', FCBetterThanWolves.fcItemSteel,
    		'Y', FCBetterThanWolves.fcItemPadding
		} );
	}
	
	private static void AddStoneToolRecipes()
	{
		AddShapelessRecipe( new ItemStack( Item.shovelStone ), new Object[] {
        	Item.stick, 
        	new ItemStack( FCBetterThanWolves.fcItemStone, 1, ignoreMetadata  ), 
        	Item.silk
    	} );
        
		AddShapelessRecipe( new ItemStack( Item.shovelStone ), new Object[] {
        	Item.stick, 
        	new ItemStack( FCBetterThanWolves.fcItemStone, 1, ignoreMetadata  ), 
        	FCBetterThanWolves.fcItemHempFibers
    	} );
        
		AddShapelessRecipe( new ItemStack( Item.axeStone ), new Object[] {
        	Item.stick, 
        	new ItemStack( FCBetterThanWolves.fcItemStone, 1, ignoreMetadata  ), 
        	new ItemStack( FCBetterThanWolves.fcItemStone, 1, ignoreMetadata  ), 
        	Item.silk
    	} );
        
		AddShapelessRecipe( new ItemStack( Item.axeStone ), new Object[] {
        	Item.stick, 
        	new ItemStack( FCBetterThanWolves.fcItemStone, 1, ignoreMetadata  ), 
        	new ItemStack( FCBetterThanWolves.fcItemStone, 1, ignoreMetadata  ), 
        	FCBetterThanWolves.fcItemHempFibers
    	} );
        
		AddRecipe( new ItemStack( Item.pickaxeStone ), new Object[] {
        	"XXX", 
        	" #S", 
        	" # ",         	
        	'#', Item.stick, 
        	'X', new ItemStack( FCBetterThanWolves.fcItemStone, 1, ignoreMetadata  ), 
        	'S', Item.silk
    	} );
        
		AddRecipe( new ItemStack( Item.pickaxeStone ), new Object[] {
        	"XXX", 
        	" #S", 
        	" # ",         	
        	'#', Item.stick, 
        	'X', new ItemStack( FCBetterThanWolves.fcItemStone, 1, ignoreMetadata  ), 
        	'S', FCBetterThanWolves.fcItemHempFibers
    	} );
	}
	
	private static void AddLooseStoneRecipes() {

		/** Strata info:
		 * using separate blocks: stairs
		 * using last 2 bits: full block and slab
		 * using first 2 bits: itemstone
		 * 
		 */		

		int altBitStrata = 0;
		Block stairsType = FCBetterThanWolves.fcBlockCobblestoneLooseStairs;
		
		for( int strata = 0; strata < 3; strata++) {
			
			altBitStrata = strata << 2;
			
			if (strata == 1) {
				stairsType = FCBetterThanWolves.fcBlockCobblestoneLooseStairsMidStrata;
			}
			else if (strata == 2) {
				stairsType = FCBetterThanWolves.fcBlockCobblestoneLooseStairsDeepStrata;
			}
			
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockCobblestoneLoose, 1, altBitStrata), new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemStone, 1, strata ), 
			new ItemStack( FCBetterThanWolves.fcItemStone, 1, strata ), 
			new ItemStack( FCBetterThanWolves.fcItemStone, 1, strata ), 
			new ItemStack( FCBetterThanWolves.fcItemStone, 1, strata ), 
			new ItemStack( FCBetterThanWolves.fcItemStone, 1, strata ), 
			new ItemStack( FCBetterThanWolves.fcItemStone, 1, strata ), 
			new ItemStack( FCBetterThanWolves.fcItemStone, 1, strata ), 
			new ItemStack( FCBetterThanWolves.fcItemStone, 1, strata ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockCobblestoneLooseSlab, 1, altBitStrata ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemStone, 1, strata ), 
			new ItemStack( FCBetterThanWolves.fcItemStone, 1, strata ), 
			new ItemStack( FCBetterThanWolves.fcItemStone, 1, strata ), 
			new ItemStack( FCBetterThanWolves.fcItemStone, 1, strata ) 
		} );
		
		AddRecipe( new ItemStack( stairsType, 1 ), new Object[] {
    		"#  ", 
    		"## ", 
    		"###", 
    		'#', new ItemStack( FCBetterThanWolves.fcItemStone, 1, strata ) 
		} );
		
		AddRecipe( new ItemStack( stairsType, 8 ), new Object[] {
    		"#  ", 
    		"## ", 
    		"###", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockCobblestoneLoose, 1, altBitStrata ) 
		} );
		
		AddRecipe( new ItemStack( stairsType, 4 ), new Object[] {
    		"# ", 
    		"##", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockCobblestoneLoose, 1, altBitStrata ) 
		} );
		
		AddRecipe( new ItemStack( stairsType, 2 ), new Object[] {
    		"# ", 
    		"##", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockCobblestoneLooseSlab, 1, altBitStrata ) 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockCobblestoneLoose, 1, altBitStrata ), new Object[] {
    		"X", 
    		"X", 
    		'X', new ItemStack( FCBetterThanWolves.fcBlockCobblestoneLooseSlab, 4, altBitStrata )
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockCobblestoneLooseSlab, 4, altBitStrata ), new Object[] {
    		"XX", 
    		'X', new ItemStack( FCBetterThanWolves.fcBlockCobblestoneLoose, 1, altBitStrata )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemStone, 8, strata ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcBlockCobblestoneLoose, 1, altBitStrata ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemStone, 4, strata ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcBlockCobblestoneLooseSlab, 1,  altBitStrata ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemStone, 6, strata ), new Object[] {
			new ItemStack( stairsType, 1 ) 
		} );	
		}
	}
	
	private static void AddLooseBrickRecipes()
	{
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemBrickUnfired ), new Object[] {
			new ItemStack( Item.clay )
		} );		
			
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockBrickLoose ), new Object[] {
			new ItemStack( Item.brick ), 
			new ItemStack( Item.brick ), 
			new ItemStack( Item.brick ), 
			new ItemStack( Item.brick ), 
			new ItemStack( Item.brick ), 
			new ItemStack( Item.brick ), 
			new ItemStack( Item.brick ), 
			new ItemStack( Item.brick ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockBrickLooseSlab ), new Object[] {
			new ItemStack( Item.brick ), 
			new ItemStack( Item.brick ), 
			new ItemStack( Item.brick ), 
			new ItemStack( Item.brick ) 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockBrickLooseStairs ), new Object[] {
    		"#  ", 
    		"## ", 
    		"###", 
    		'#', new ItemStack( Item.brick ) 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockBrickLooseStairs, 8 ), new Object[] {
    		"#  ", 
    		"## ", 
    		"###", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockBrickLoose ) 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockBrickLooseStairs, 4 ), new Object[] {
    		"# ", 
    		"##", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockBrickLoose ) 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockBrickLooseStairs, 2 ), new Object[] {
    		"# ", 
    		"##", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockBrickLooseSlab ) 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockBrickLoose ), new Object[] {
    		"X", 
    		"X", 
    		'X', FCBetterThanWolves.fcBlockBrickLooseSlab
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockBrickLooseSlab, 4 ), new Object[] {
    		"XX", 
    		'X', FCBetterThanWolves.fcBlockBrickLoose
		} );
		
		AddShapelessRecipe( new ItemStack( Item.brick, 8 ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcBlockBrickLoose ) 
		} );
		
		AddShapelessRecipe( new ItemStack( Item.brick, 4 ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcBlockBrickLooseSlab ) 
		} );
		
		AddShapelessRecipe( new ItemStack( Item.brick, 6 ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcBlockBrickLooseStairs ) 
		} );
	}
	
	private static void AddLooseStoneBrickRecipes()
	{
		/** Strata info:
		 * using separate blocks: stairs
		 * using last 2 bits: full block and slab
		 * using first 2 bits: itemstone
		 * 
		 */		

		int altBitStrata = 0;
		Block stairsType = FCBetterThanWolves.fcBlockStoneBrickLooseStairs;
		
		for( int strata = 0; strata < 3; strata++) {
			
			altBitStrata = strata << 2;
			
			if (strata == 1) {
				stairsType = FCBetterThanWolves.fcBlockStoneBrickLooseStairsMidStrata;
			}
			else if (strata == 2) {
				stairsType = FCBetterThanWolves.fcBlockStoneBrickLooseStairsDeepStrata;
			}
			
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockStoneBrickLoose, 1, altBitStrata ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemStoneBrick, 1, strata ), 
			new ItemStack( FCBetterThanWolves.fcItemStoneBrick, 1, strata ), 
			new ItemStack( FCBetterThanWolves.fcItemStoneBrick, 1, strata ), 
			new ItemStack( FCBetterThanWolves.fcItemStoneBrick, 1, strata ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockStoneBrickLooseSlab, 1, altBitStrata ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemStoneBrick, 1, strata ), 
			new ItemStack( FCBetterThanWolves.fcItemStoneBrick, 1, strata ), 
		} );
		
		AddRecipe( new ItemStack( stairsType, 2 ), new Object[] {
    		"#  ", 
    		"## ", 
    		"###", 
    		'#', new ItemStack( FCBetterThanWolves.fcItemStoneBrick, 1, strata ) 
		} );
		
		AddRecipe( new ItemStack( stairsType, 8 ), new Object[] {
    		"#  ", 
    		"## ", 
    		"###", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockStoneBrickLoose, 1, altBitStrata ) 
		} );
		
		AddRecipe( new ItemStack( stairsType ), new Object[] {
    		"# ", 
    		"##", 
    		'#', new ItemStack( FCBetterThanWolves.fcItemStoneBrick, 1, strata ) 
		} );
		
		AddRecipe( new ItemStack( stairsType, 4 ), new Object[] {
    		"# ", 
    		"##", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockStoneBrickLoose, 1, altBitStrata ) 
		} );
		
		AddRecipe( new ItemStack( stairsType, 2 ), new Object[] {
    		"# ", 
    		"##", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockStoneBrickLooseSlab, 1, altBitStrata ) 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockStoneBrickLoose, 1, altBitStrata ), new Object[] {
    		"X", 
    		"X", 
    		'X', new ItemStack( FCBetterThanWolves.fcBlockStoneBrickLooseSlab, 1, altBitStrata )
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockStoneBrickLooseSlab, 4, altBitStrata ), new Object[] {
    		"XX", 
    		'X', new ItemStack( FCBetterThanWolves.fcBlockStoneBrickLoose, 1, altBitStrata )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemStoneBrick, 4, strata ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcBlockStoneBrickLoose, 1, altBitStrata ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemStoneBrick, 2, strata ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcBlockStoneBrickLooseSlab, 1, altBitStrata ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemStoneBrick, 3, strata ), new Object[] {
			new ItemStack( stairsType ) 
		} );

		// stone splitting with chisel
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemStone, 2, strata ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcItemChiselIron, 1, ignoreMetadata ),
    		new ItemStack( FCBetterThanWolves.fcItemStoneBrick, 1, strata )
		} );
		
		AddShapelessRecipeWithSecondaryOutputIndicator(
			new ItemStack( FCBetterThanWolves.fcItemStoneBrick, 4, strata ), 
			new ItemStack(FCBetterThanWolves.fcItemPileGravel), 
			new Object[] {	    		
					new ItemStack( FCBetterThanWolves.fcItemChiselIron, 1, ignoreMetadata ),
					new ItemStack( Block.stone, 1, strata )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemStone, 2, strata ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcItemChiselDiamond, 1, ignoreMetadata ),
    		new ItemStack( FCBetterThanWolves.fcItemStoneBrick, 1, strata )
		} );
		
		AddShapelessRecipeWithSecondaryOutputIndicator(
			new ItemStack( FCBetterThanWolves.fcItemStoneBrick, 4, strata), 
			new ItemStack(FCBetterThanWolves.fcItemPileGravel), 
			new Object[] {	    		
					new ItemStack( FCBetterThanWolves.fcItemChiselDiamond, 1, ignoreMetadata ),
					new ItemStack( Block.stone, 1, strata )
		} );
		}
	}
	
	private static void AddLooseNetherBrickRecipes()
	{
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemNetherBrickUnfired ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemNetherSludge ) 
		} );		
			
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockNetherBrickLoose ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemNetherBrick ), 
			new ItemStack( FCBetterThanWolves.fcItemNetherBrick ), 
			new ItemStack( FCBetterThanWolves.fcItemNetherBrick ), 
			new ItemStack( FCBetterThanWolves.fcItemNetherBrick ), 
			new ItemStack( FCBetterThanWolves.fcItemNetherBrick ), 
			new ItemStack( FCBetterThanWolves.fcItemNetherBrick ), 
			new ItemStack( FCBetterThanWolves.fcItemNetherBrick ), 
			new ItemStack( FCBetterThanWolves.fcItemNetherBrick ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockNetherBrickLooseSlab ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemNetherBrick ), 
			new ItemStack( FCBetterThanWolves.fcItemNetherBrick ), 
			new ItemStack( FCBetterThanWolves.fcItemNetherBrick ), 
			new ItemStack( FCBetterThanWolves.fcItemNetherBrick ) 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockNetherBrickLooseStairs ), new Object[] {
    		"#  ", 
    		"## ", 
    		"###", 
    		'#', new ItemStack( FCBetterThanWolves.fcItemNetherBrick ) 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockNetherBrickLooseStairs, 8 ), new Object[] {
    		"#  ", 
    		"## ", 
    		"###", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockNetherBrickLoose ) 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockNetherBrickLooseStairs, 4 ), new Object[] {
    		"# ", 
    		"##", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockNetherBrickLoose ) 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockNetherBrickLooseStairs, 2 ), new Object[] {
    		"# ", 
    		"##", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockNetherBrickLooseSlab ) 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockNetherBrickLoose ), new Object[] {
    		"X", 
    		"X", 
    		'X', FCBetterThanWolves.fcBlockNetherBrickLooseSlab
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockNetherBrickLooseSlab, 4 ), new Object[] {
    		"XX", 
    		'X', FCBetterThanWolves.fcBlockNetherBrickLoose
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemNetherBrick, 8 ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcBlockNetherBrickLoose ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemNetherBrick, 4 ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcBlockNetherBrickLooseSlab ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemNetherBrick, 6 ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcBlockNetherBrickLooseStairs ) 
		} );
	}
	
	private static void AddTorchRecipes()
	{
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockTorchNetherUnlit, 1 ), new Object[] {
			"#",
			"X",
            Character.valueOf('#'), FCBetterThanWolves.fcItemNethercoal, 
            Character.valueOf('X'), Item.stick 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockTorchFiniteUnlit ), new Object[] {
			"#",
			"X",
            Character.valueOf('#'), new ItemStack( Item.coal, 1, ignoreMetadata ), 
            Character.valueOf('X'), Item.stick 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockTorchNetherBurning ), new Object[] {
			new ItemStack( Block.torchWood )
		} );
	}
	
	private static void AddWickerRecipes()
	{
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemWickerWeaving, 1,
			FCItemWickerWeaving.m_iWickerWeavingMaxDamage - 1 ), new Object[] {
    		"##", 
    		"##", 
    		'#', Item.reed 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockBasketWicker ), new Object[] {
    		"##", 
    		"##", 
    		'#', FCBetterThanWolves.fcItemWickerPiece 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemWickerPiece, 4 ), new Object[] {	    		
			new ItemStack( FCBetterThanWolves.fcBlockBasketWicker )
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockHamper ), new Object[] {
    		"###", 
    		"#P#", 
    		"###", 
    		'#', FCBetterThanWolves.fcItemWickerPiece, 
    		'P', Block.planks 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockWickerPane ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcBlockGrate ),
    		new ItemStack( FCBetterThanWolves.fcItemWickerPiece )
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockWicker ), new Object[] {
    		"##", 
    		"##", 
    		'#', FCBetterThanWolves.fcBlockWickerPane
		} );

		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockWickerSlab ), new Object[] {
    		"##", 
    		'#', FCBetterThanWolves.fcBlockWickerPane
		} );

		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockWickerSlab, 4 ), new Object[] {
    		"##", 
    		'#', FCBetterThanWolves.fcBlockWicker
		} );

		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockWickerPane, 4 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcBlockWicker )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockWickerPane, 2 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcBlockWickerSlab )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockWicker ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcBlockWickerSlab ),
    		new ItemStack( FCBetterThanWolves.fcBlockWickerSlab )
		} );
		
		// conversion of deprecated blocks
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockWicker ), new Object[] {
    		new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 1, 
    			FCBlockAestheticOpaque.m_iSubtypeWicker )
		} );

		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockWickerSlab ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcAestheticNonOpaque, 1, 
    			FCBlockAestheticNonOpaque.m_iSubtypeWickerSlab )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockWickerPane ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcItemWickerPaneOld )
		} );
	}
	
	private static void AddStairRecipes()
	{
		AddRecipe(new ItemStack(Block.stairsWoodOak, 6), new Object[] {
			"#  ", 
			"## ", 
			"###", 
			'#', new ItemStack(Block.planks, 1, 0)
		});
		
		AddRecipe(new ItemStack(Block.stairsWoodBirch, 6), new Object[] {
			"#  ", 
			"## ", 
			"###", 
			'#', new ItemStack(Block.planks, 1, 2)
		});
		
		AddRecipe(new ItemStack(Block.stairsWoodSpruce, 6), new Object[] {
			"#  ", 
			"## ", 
			"###", 
			'#', new ItemStack(Block.planks, 1, 1)
		});
		
		AddRecipe(new ItemStack(Block.stairsWoodJungle, 6), new Object[] {
			"#  ", 
			"## ", 
			"###", 
			'#', new ItemStack(Block.planks, 1, 3)
		});
		
		AddRecipe(new ItemStack(Block.stairsBrick, 6), new Object[] {
			"#  ", 
			"## ", 
			"###", 
			'#', new ItemStack(Block.brick)
		});
		
		AddRecipe(new ItemStack(Block.stairsCobblestone, 6), new Object[] {
			"#  ", 
			"## ", 
			"###", 
			'#', new ItemStack(Block.cobblestone, 1, 0)
		});
		
		AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockCobblestoneStairsMidStrata, 6), new Object[] {
				"#  ", 
				"## ", 
				"###", 
				'#', new ItemStack(Block.cobblestone, 1, 1)
			});
		
		AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockCobblestoneStairsDeepStrata, 6), new Object[] {
				"#  ", 
				"## ", 
				"###", 
				'#', new ItemStack(Block.cobblestone, 1, 2)
			});
		
		
		AddRecipe(new ItemStack(Block.stairsNetherBrick, 6), new Object[] {
			"#  ", 
			"## ", 
			"###", 
			'#', new ItemStack(Block.netherBrick)
		});
		
		AddRecipe(new ItemStack(Block.stairsNetherQuartz, 6), new Object[] {
			"#  ", 
			"## ", 
			"###", 
			'#', new ItemStack(Block.blockNetherQuartz)
		});
		
		AddRecipe(new ItemStack(Block.stairsSandStone, 6), new Object[] {
			"#  ", 
			"## ", 
			"###", 
			'#', new ItemStack(Block.sandStone)
		});
		
		AddRecipe(new ItemStack(Block.stairsStoneBrick, 6), new Object[] {
			"#  ", 
			"## ", 
			"###", 
			'#', new ItemStack(Block.stoneBrick, 1, 0)
		});
		
		AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockStoneBrickStairsDeepStrata, 6), new Object[] {
				"#  ", 
				"## ", 
				"###", 
				'#', new ItemStack(Block.stoneBrick, 1, 2)
			});
		
		AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockStoneBrickStairsDeepStrata, 6), new Object[] {
				"#  ", 
				"## ", 
				"###", 
				'#', new ItemStack(Block.stoneBrick, 1, 2)
			});
	}
	
	private static void AddWoolAndKnittingRecipes()
	{
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemKnittingNeedles ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcItemChiselWood, 1, 0 ),
    		new ItemStack( FCBetterThanWolves.fcItemChiselWood, 1, 0 )
		} );
		
		AddShapedRecipeWithCustomClass( FCRecipesArmorWool.class, new ItemStack( FCBetterThanWolves.fcItemArmorWoolHelm ), new Object[] {
    		"##", 
    		'#', new ItemStack( FCBetterThanWolves.fcItemWoolKnit, 1, ignoreMetadata ) 
		} );
		
		AddShapedRecipeWithCustomClass( FCRecipesArmorWool.class, new ItemStack( FCBetterThanWolves.fcItemArmorWoolChest ), new Object[] {
    		"##", 
    		"##", 
    		'#', new ItemStack( FCBetterThanWolves.fcItemWoolKnit, 1, ignoreMetadata ) 
		} );
		
		AddShapedRecipeWithCustomClass( FCRecipesArmorWool.class, new ItemStack( FCBetterThanWolves.fcItemArmorWoolLeggings ), new Object[] {
    		"##", 
    		"# ", 
    		'#', new ItemStack( FCBetterThanWolves.fcItemWoolKnit, 1, ignoreMetadata ) 
		} );
		
		AddShapedRecipeWithCustomClass( FCRecipesArmorWool.class, new ItemStack( FCBetterThanWolves.fcItemArmorWoolLeggings ), new Object[] {
    		"# ", 
    		"##", 
    		'#', new ItemStack( FCBetterThanWolves.fcItemWoolKnit, 1, ignoreMetadata ) 
		} );
		
		AddShapedRecipeWithCustomClass( FCRecipesWoolBlock.class, new ItemStack( Block.cloth ), new Object[] {
    		" # ", 
    		"#W#", 
    		" # ", 
    		'#', new ItemStack( FCBetterThanWolves.fcItemWool, 1, ignoreMetadata ), 
    		'W', new ItemStack( FCBetterThanWolves.fcBlockWicker ) 
		} );
		
		for ( int iTempColor = 0; iTempColor < 16; iTempColor++ )
		{
			AddRecipe( new ItemStack( FCBetterThanWolves.fcWoolSlab, 6, iTempColor ), new Object[] {
	    		"###", 
	    		'#', new ItemStack( Block.cloth, 1, iTempColor )
			} );
			
			AddShapelessRecipe( new ItemStack( Block.cloth, 1, iTempColor ), new Object[] {
				new ItemStack( FCBetterThanWolves.fcWoolSlab, 1, iTempColor ), 
				new ItemStack( FCBetterThanWolves.fcWoolSlab, 1, iTempColor ) 				
			} );
		}
	}
	
	private static void AddSawDustRecipes()
	{
		// wood items to saw dust as backup method of burning in campfire & brick furnace
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemSawDust, 2 ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemFireStarterSticks, 1, ignoreMetadata ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemSawDust, 2 ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemFireStarterBow, 1, ignoreMetadata ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemSawDust, 2 ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemKnittingNeedles, 1, ignoreMetadata ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemSawDust, 4 ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemKnitting, 1, ignoreMetadata ) 
		} );
	}
	
	private static void AddMeatCuringRecipes()
	{
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemMeatCured ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemMuttonRaw ), new ItemStack( FCBetterThanWolves.fcItemNitre ) } );
			
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemMeatCured ), new Object[] {
			new ItemStack( Item.chickenRaw ), new ItemStack( FCBetterThanWolves.fcItemNitre ) } );
			
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemMeatCured ), new Object[] {
			new ItemStack( Item.beefRaw ), new ItemStack( FCBetterThanWolves.fcItemNitre ) } );
			
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemMeatCured ), new Object[] {
			new ItemStack( Item.fishRaw ), new ItemStack( FCBetterThanWolves.fcItemNitre ) } );
			
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemMeatCured ), new Object[] {
			new ItemStack( Item.porkRaw ), new ItemStack( FCBetterThanWolves.fcItemNitre ) } );
			
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemMeatCured ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemWolfRaw ), new ItemStack( FCBetterThanWolves.fcItemNitre ) } );
			
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemMeatCured ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemRawMysteryMeat ), new ItemStack( FCBetterThanWolves.fcItemNitre ) } );
			
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemMeatCured ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemBeastLiverRaw ), new ItemStack( FCBetterThanWolves.fcItemNitre ) } );
	}
	
	private static void AddPaneRecipes()
	{
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockGrate, 1 ), new Object[] {
    		"S#S", 
    		"###", 
    		"S#S", 
    		'#', new ItemStack( Item.stick ),
    		'S', new ItemStack( Item.silk )
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockGrate, 1 ), new Object[] {
    		"S#S", 
    		"###", 
    		"S#S", 
    		'#', new ItemStack( Item.stick ),
    		'S', new ItemStack( FCBetterThanWolves.fcItemHempFibers )
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockSlats, 1 ), new Object[] {
    		"##", 
    		"##", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, ignoreMetadata ) 
		} );		
		
		// conversion of deprecated items
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockGrate ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcItemGrateOld )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockSlats ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcItemSlatsOld )
		} );
	}
	
	private static void AddSnowRecipes()
	{
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockSnowLooseSlab ), new Object[] {
			new ItemStack( Item.snowball ),
			new ItemStack( Item.snowball ),
			new ItemStack( Item.snowball ),
			new ItemStack( Item.snowball )
		} );
		
		AddShapelessRecipe( new ItemStack( Item.snowball, 4 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcBlockSnowLooseSlab )
		} );
		
		AddShapelessRecipe( new ItemStack( Item.snowball, 4 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcBlockSnowSolidSlab )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockSnowLoose ), new Object[] {
			new ItemStack( Item.snowball ),
			new ItemStack( Item.snowball ),
			new ItemStack( Item.snowball ),
			new ItemStack( Item.snowball ),
			new ItemStack( Item.snowball ),
			new ItemStack( Item.snowball ),
			new ItemStack( Item.snowball ),
			new ItemStack( Item.snowball )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockSnowLoose ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcBlockSnowLooseSlab ),
			new ItemStack( FCBetterThanWolves.fcBlockSnowLooseSlab )
		} );
		
		AddShapelessRecipe( new ItemStack( Item.snowball, 8 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcBlockSnowLoose )
		} );
		
		AddShapelessRecipe( new ItemStack( Item.snowball, 8 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcBlockSnowSolid )
		} );
	}
	
	private static void AddChickenFeedRecipes()
	{
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemChickenFeed ), new Object[] {	    		
    		new ItemStack( Item.dyePowder, 1, 15 ), // bone meal 
    		new ItemStack( Item.seeds )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemChickenFeed ), new Object[] {	    		
    		new ItemStack( Item.dyePowder, 1, 15 ), // bone meal 
    		new ItemStack( FCBetterThanWolves.fcItemWheatSeeds )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemChickenFeed ), new Object[] {	    		
    		new ItemStack( Item.dyePowder, 1, 15 ), // bone meal 
    		new ItemStack( FCBetterThanWolves.fcItemHempSeeds )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemChickenFeed ), new Object[] {	    		
    		new ItemStack( Item.dyePowder, 1, 15 ), // bone meal 
    		new ItemStack( Item.pumpkinSeeds )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemChickenFeed ), new Object[] {	    		
    		new ItemStack( Item.dyePowder, 1, 15 ), // bone meal 
    		new ItemStack( Item.melonSeeds )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemChickenFeed ), new Object[] {	    		
    		new ItemStack( Item.dyePowder, 1, 15 ), // bone meal 
    		new ItemStack(FCBetterThanWolves.fcItemCarrotSeeds)
		} );
		
		AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemChickenFeed), new ItemStack[] {
				new ItemStack(Item.dyePowder, 1, 15),
				new ItemStack(Item.seeds)
		});
		
		AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemChickenFeed), new ItemStack[] {
				new ItemStack(Item.dyePowder, 1, 15),
				new ItemStack(FCBetterThanWolves.fcItemWheatSeeds)
		});
		
		AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemChickenFeed), new ItemStack[] {
				new ItemStack(Item.dyePowder, 1, 15),
				new ItemStack(FCBetterThanWolves.fcItemHempSeeds)
		});
		
		AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemChickenFeed), new ItemStack[] {
				new ItemStack(Item.dyePowder, 1, 15),
				new ItemStack(Item.pumpkinSeeds)
		});
		
		AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemChickenFeed), new ItemStack[] {
				new ItemStack(Item.dyePowder, 1, 15),
				new ItemStack(Item.melonSeeds)
		});
		
		AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemChickenFeed), new ItemStack[] {
				new ItemStack(Item.dyePowder, 1, 15),
				new ItemStack(FCBetterThanWolves.fcItemCarrotSeeds)
		});
	}
	
	private static void AddFishingRecipes()
	{
		AddShapelessRecipe( new ItemStack( Item.fishingRod ), new Object[] {	    		
			new ItemStack( Item.stick ), 
			new ItemStack( Item.silk ),
			new ItemStack( Item.silk ),
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron )
		} );		
		
		AddShapelessRecipe( new ItemStack( Item.fishingRod ), new Object[] {	    		
			new ItemStack( Item.stick ), 
			new ItemStack( Item.silk ),
			new ItemStack( Item.silk ),
			new ItemStack( FCBetterThanWolves.fcItemFishHookBone )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemCarvingBone, 1, 
			FCItemCarvingBone.m_iDefaultMaxDamage - 1 ), new Object[] {	    		
			new ItemStack( Item.bone )
		} );			
	}
	
	private static void AddDirtRecipes()
	{
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockDirtLooseSlab, 4 ), new Object[] {
    		"##", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockDirtLoose )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockDirtLooseSlab, 1 ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemPileDirt ), 
			new ItemStack( FCBetterThanWolves.fcItemPileDirt ), 
			new ItemStack( FCBetterThanWolves.fcItemPileDirt ), 
			new ItemStack( FCBetterThanWolves.fcItemPileDirt )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockDirtLoose, 1 ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemPileDirt ), 
			new ItemStack( FCBetterThanWolves.fcItemPileDirt ), 
			new ItemStack( FCBetterThanWolves.fcItemPileDirt ), 
			new ItemStack( FCBetterThanWolves.fcItemPileDirt ), 
			new ItemStack( FCBetterThanWolves.fcItemPileDirt ), 
			new ItemStack( FCBetterThanWolves.fcItemPileDirt ), 
			new ItemStack( FCBetterThanWolves.fcItemPileDirt ), 
			new ItemStack( FCBetterThanWolves.fcItemPileDirt )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockDirtLoose ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcBlockDirtLooseSlab ),
    		new ItemStack( FCBetterThanWolves.fcBlockDirtLooseSlab )
		} );

		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockDirtSlab, 4 ), new Object[] {
    		"##", 
    		'#', new ItemStack( Block.dirt )
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockDirtSlab, 4, FCBlockDirtSlab.m_iSubtypePackedEarth ), new Object[] {
    		"EE", 
    		'E', new ItemStack( FCBetterThanWolves.fcBlockAestheticOpaqueEarth, 1, FCBlockAestheticOpaqueEarth.m_iSubtypePackedEarth )
		} );
		
		AddShapelessRecipe( new ItemStack( Block.dirt ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcBlockDirtSlab ),
    		new ItemStack( FCBetterThanWolves.fcBlockDirtSlab )
		} );

		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockDirtLoose, 2 ), new Object[] {
    		new ItemStack( FCBetterThanWolves.fcBlockAestheticOpaqueEarth, 1, FCBlockAestheticOpaqueEarth.m_iSubtypePackedEarth ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockAestheticOpaqueEarth, 1, FCBlockAestheticOpaqueEarth.m_iSubtypePackedEarth ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcBlockDirtSlab, 1, FCBlockDirtSlab.m_iSubtypePackedEarth ),
    		new ItemStack( FCBetterThanWolves.fcBlockDirtSlab, 1, FCBlockDirtSlab.m_iSubtypePackedEarth )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockDirtLoose, 1 ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcBlockDirtSlab, 1, FCBlockDirtSlab.m_iSubtypePackedEarth ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemPileDirt, 8 ), new Object[] {
			new ItemStack( Block.dirt, 1 )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemPileDirt, 8 ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcBlockDirtLoose, 1 )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemPileDirt, 4 ), new Object[] {
    		new ItemStack( FCBetterThanWolves.fcBlockDirtSlab ),
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemPileDirt, 4 ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcBlockDirtLooseSlab, 1 )
		} );
	}
	
	private static void AddGravelRecipes()
	{
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockSlabSandAndGravel, 1, FCBlockSlabSandAndGravel.m_iSubtypeGravel ), new Object[] {
    		new ItemStack( FCBetterThanWolves.fcItemPileGravel ),
    		new ItemStack( FCBetterThanWolves.fcItemPileGravel ),
    		new ItemStack( FCBetterThanWolves.fcItemPileGravel ),
    		new ItemStack( FCBetterThanWolves.fcItemPileGravel )
		} );
		
		AddShapelessRecipe( new ItemStack( Block.gravel ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemPileGravel ), 
			new ItemStack( FCBetterThanWolves.fcItemPileGravel ), 
			new ItemStack( FCBetterThanWolves.fcItemPileGravel ), 
			new ItemStack( FCBetterThanWolves.fcItemPileGravel ),
			new ItemStack( FCBetterThanWolves.fcItemPileGravel ), 
			new ItemStack( FCBetterThanWolves.fcItemPileGravel ), 
			new ItemStack( FCBetterThanWolves.fcItemPileGravel ), 
			new ItemStack( FCBetterThanWolves.fcItemPileGravel )
		} );

		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemPileGravel, 8 ), new Object[] {
			new ItemStack( Block.gravel )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemPileGravel, 4 ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcBlockSlabSandAndGravel, 1, FCBlockSlabSandAndGravel.m_iSubtypeGravel )
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockSlabSandAndGravel, 4, FCBlockSlabSandAndGravel.m_iSubtypeGravel ), new Object[] {
    		"##", 
    		'#', new ItemStack( Block.gravel )
		} );
		
		AddShapelessRecipe( new ItemStack( Block.gravel ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcBlockSlabSandAndGravel, 1, FCBlockSlabSandAndGravel.m_iSubtypeGravel ),
    		new ItemStack( FCBetterThanWolves.fcBlockSlabSandAndGravel, 1, FCBlockSlabSandAndGravel.m_iSubtypeGravel )
		} );
	}
	
	private static void AddSandRecipes()
	{
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockSlabSandAndGravel, 1, FCBlockSlabSandAndGravel.m_iSubtypeSand ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemPileSand ), 
			new ItemStack( FCBetterThanWolves.fcItemPileSand ), 
			new ItemStack( FCBetterThanWolves.fcItemPileSand ), 
			new ItemStack( FCBetterThanWolves.fcItemPileSand )
		} );
		
		AddShapelessRecipe( new ItemStack( Block.sand ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemPileSand ), 
			new ItemStack( FCBetterThanWolves.fcItemPileSand ), 
			new ItemStack( FCBetterThanWolves.fcItemPileSand ), 
			new ItemStack( FCBetterThanWolves.fcItemPileSand ), 
			new ItemStack( FCBetterThanWolves.fcItemPileSand ), 
			new ItemStack( FCBetterThanWolves.fcItemPileSand ), 
			new ItemStack( FCBetterThanWolves.fcItemPileSand ), 
			new ItemStack( FCBetterThanWolves.fcItemPileSand )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemPileSand, 4 ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcBlockSlabSandAndGravel, 1, FCBlockSlabSandAndGravel.m_iSubtypeSand )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemPileSand, 8 ), new Object[] {
			new ItemStack( Block.sand )
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockSlabSandAndGravel, 4, FCBlockSlabSandAndGravel.m_iSubtypeSand ), new Object[] {
    		"##", 
    		'#', new ItemStack( Block.sand )
		} );
		
		AddShapelessRecipe( new ItemStack( Block.sand ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcBlockSlabSandAndGravel, 1, FCBlockSlabSandAndGravel.m_iSubtypeSand ),
    		new ItemStack( FCBetterThanWolves.fcBlockSlabSandAndGravel, 1, FCBlockSlabSandAndGravel.m_iSubtypeSand )
		} );
	}
	
	private static void AddMechanicalRecipes()
	{
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockAxle ), new Object[] {
    		"#X#", 
    		'#', Block.planks, 
    		'X', FCBetterThanWolves.fcItemRope 
		} );

		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockAxle ), new Object[] {
    		"#X#", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, ignoreMetadata ), 
    		'X', FCBetterThanWolves.fcItemRope 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockGearBox ), new Object[] {
    		"#X#", 
    		"XYX", 
    		"#X#", 
    		'#', Block.planks, 
    		'X', new ItemStack(FCBetterThanWolves.fcItemGear, 1, ignoreMetadata), 
    		'Y', FCBetterThanWolves.fcBlockAxle
		} );		
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockGearBox ), new Object[] {
    		"#X#", 
    		"XYX", 
    		"#X#", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, ignoreMetadata ), 
    		'X', new ItemStack(FCBetterThanWolves.fcItemGear, 1, ignoreMetadata), 
    		'Y', FCBetterThanWolves.fcBlockAxle
		} );		
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockRedstoneClutch ), new Object[] {
    		"#X#", 
    		"XYX", 
    		"#X#", 
    		'#', Block.planks, 
    		'X', new ItemStack(FCBetterThanWolves.fcItemGear, 1, ignoreMetadata), 
    		'Y', FCBetterThanWolves.fcItemRedstoneLatch
		} );		
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockRedstoneClutch ), new Object[] {
    		"#X#", 
    		"XYX", 
    		"#X#", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, ignoreMetadata ), 
    		'X', new ItemStack(FCBetterThanWolves.fcItemGear, 1, ignoreMetadata), 
    		'Y', FCBetterThanWolves.fcItemRedstoneLatch
		} );		
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcHandCrank ), new Object[] {
    		"  Y", 
    		" Y ", 
    		"#X#", 
    		'#', new ItemStack(FCBetterThanWolves.fcItemStoneBrick, 1, ignoreMetadata), 
    		'X', new ItemStack(FCBetterThanWolves.fcItemGear, 1, ignoreMetadata), 
    		'Y', Item.stick 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcMillStone ), new Object[] {
    		"YYY", 
    		"YYY", 
    		"YXY", 
    		'X', new ItemStack(FCBetterThanWolves.fcItemGear, 1, ignoreMetadata), 
    		'Y', new ItemStack(FCBetterThanWolves.fcItemStoneBrick, 1, ignoreMetadata) 
		} );		

		AddRecipe( new ItemStack( FCBetterThanWolves.fcTurntable ), new Object[] {
    		"###", 
    		"ZXZ", 
    		"ZYZ", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, ignoreMetadata ), 
    		'X', Item.pocketSundial, 
    		'Y', new ItemStack(FCBetterThanWolves.fcItemGear, 1, ignoreMetadata),
    		'Z', new ItemStack(FCBetterThanWolves.fcItemStoneBrick, 1, ignoreMetadata)
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBellows ), new Object[] {
    		"###", 
    		"XXX", 
    		"YZY", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, ignoreMetadata ), 
    		'X', FCBetterThanWolves.fcItemTannedLeather, 
    		'Y', new ItemStack(FCBetterThanWolves.fcItemGear, 1, ignoreMetadata),
    		'Z', FCBetterThanWolves.fcItemBelt
		} );		
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBellows ), new Object[] {
    		"###", 
    		"XXX", 
    		"YZY", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, ignoreMetadata ), 
    		'X', FCBetterThanWolves.fcItemTannedLeatherCut, 
    		'Y', new ItemStack(FCBetterThanWolves.fcItemGear, 1, ignoreMetadata),
    		'Z', FCBetterThanWolves.fcItemBelt
		} );
	}
	
	private static void AddOreRecipes()
	{
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemChunkIronOre ), new Object[] {
    		new ItemStack( FCBetterThanWolves.fcItemPileIronOre ),
    		new ItemStack( FCBetterThanWolves.fcItemPileIronOre )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemChunkIronOre ), new Object[] {
    		new ItemStack( Block.oreIron )
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockChunkOreStorageIron ), new Object[] {
    		"###", 
    		"###", 
    		"###", 
    		'#', FCBetterThanWolves.fcItemChunkIronOre
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemChunkIronOre, 9 ), new Object[] {
    		new ItemStack( FCBetterThanWolves.fcBlockChunkOreStorageIron )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemChunkGoldOre ), new Object[] {
    		new ItemStack( FCBetterThanWolves.fcItemPileGoldOre ),
    		new ItemStack( FCBetterThanWolves.fcItemPileGoldOre )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemChunkGoldOre ), new Object[] {
    		new ItemStack( Block.oreGold )
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockChunkOreStorageGold ), new Object[] {
    		"###", 
    		"###", 
    		"###", 
    		'#', FCBetterThanWolves.fcItemChunkGoldOre
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemChunkGoldOre, 9 ), new Object[] {
    		new ItemStack( FCBetterThanWolves.fcBlockChunkOreStorageGold )
		} );
	}
	
	private static void AddPastryRecipes()
	{
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemBreadDough ), new Object[] {
    		new ItemStack( FCBetterThanWolves.fcItemFlour ),
    		new ItemStack( FCBetterThanWolves.fcItemFlour ),
    		new ItemStack( FCBetterThanWolves.fcItemFlour )
		} );
        
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemPastryUncookedCookies, 1 ), 
			new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemChocolate ), 
			new ItemStack( FCBetterThanWolves.fcItemFlour ),
			new ItemStack( FCBetterThanWolves.fcItemFlour ),
			new ItemStack( FCBetterThanWolves.fcItemFlour ),
			new ItemStack( FCBetterThanWolves.fcItemFlour )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemPastryUncookedPumpkinPie, 1 ), 
			new Object[] {	    		
    		new ItemStack( Item.sugar ),
    		new ItemStack( FCBetterThanWolves.fcBlockPumpkinFresh ),
    		new ItemStack( FCBetterThanWolves.fcItemRawEgg ),
    		new ItemStack( FCBetterThanWolves.fcItemFlour ),
    		new ItemStack( FCBetterThanWolves.fcItemFlour ),
    		new ItemStack( FCBetterThanWolves.fcItemFlour ),
		} );
		
        AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemPastryUncookedCake, 1 ), 
        	new Object[] {
            new ItemStack( Item.sugar ), 
            new ItemStack( Item.sugar ), 
            new ItemStack( Item.sugar ), 
            new ItemStack( Item.bucketMilk ),
            new ItemStack( Item.bucketMilk ),
            new ItemStack( FCBetterThanWolves.fcItemFlour ), 
            new ItemStack( FCBetterThanWolves.fcItemFlour ), 
            new ItemStack( FCBetterThanWolves.fcItemFlour ), 
            new ItemStack( FCBetterThanWolves.fcItemRawEgg )
        });
	}
	
	private static void AddBlockRecipes()
	{		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockAestheticOpaqueEarth, 1, 
			FCBlockAestheticOpaqueEarth.m_iSubtypeDung ), new Object[] {
    		"###", 
    		"###", 
    		"###", 
    		'#', FCBetterThanWolves.fcItemDung
		} );		

		AddRecipe( new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 1, 
				FCBlockAestheticOpaque.m_iSubtypeHellfire ), new Object[] {
	    		"###", 
	    		"###", 
	    		"###", 
	    		'#', FCBetterThanWolves.fcItemConcentratedHellfire
			} );

		AddRecipe( new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 1, 
				FCBlockAestheticOpaque.m_iSubtypePadding ), new Object[] {
	    		"###", 
	    		"###", 
	    		"###", 
	    		'#', FCBetterThanWolves.fcItemPadding
			} );

		AddRecipe( new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 1, 
				FCBlockAestheticOpaque.m_iSubtypeSoap ), new Object[] {
	    		"###", 
	    		"###", 
	    		"###", 
	    		'#', FCBetterThanWolves.fcItemSoap
			} );

		AddRecipe( new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 1, 
				FCBlockAestheticOpaque.m_iSubtypeRope ), new Object[] {
	    		"###", 
	    		"###", 
	    		"###", 
	    		'#', FCBetterThanWolves.fcItemRope
			} );

		AddRecipe( new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 1, 
				FCBlockAestheticOpaque.m_iSubtypeFlint ), new Object[] {
	    		"###", 
	    		"###", 
	    		"###", 
	    		'#', Item.flint
			} );

		AddRecipe( new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 1, 
			FCBlockAestheticOpaque.m_iSubtypeEnderBlock ), new Object[] {
    		"###", 
    		"###", 
    		"###", 
    		'#', Item.enderPearl
		} );

		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockMiningCharge, 3 ), new Object[] {
    		"XYX", 
    		"###", 
    		"###", 
    		'#', FCBetterThanWolves.fcItemDynamite, 
    		'X', FCBetterThanWolves.fcItemRope,
    		'Y', Item.slimeBall
		} );

		AddRecipe( new ItemStack( FCBetterThanWolves.fcAestheticVegetation, 1, FCBlockAestheticVegetation.m_iSubtypeVineTrap ), new Object[] {
	    		"###", 
	    		'#', Block.vine
			} );

		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcAnvil, 1 ), new Object[] {	    		
    		new ItemStack( Item.netherStar ),
    		new ItemStack( FCBetterThanWolves.fcItemSoulFlux ),
    		new ItemStack( FCBetterThanWolves.fcBlockSoulforgeDormant )
		} );		
        
		AddRecipe( new ItemStack( FCBetterThanWolves.fcLightBulbOff, 1 ), new Object[] {
        	" # ", 
        	"#X#", 
        	" Y ", 
        	'#', Block.thinGlass, 
        	'X', FCBetterThanWolves.fcItemFilament, 
        	'Y', Item.redstone 
        } );
        
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBBQ ), new Object[] {
    		"XXX", 
    		"#Z#", 
    		"#Y#", 
    		'#', new ItemStack(FCBetterThanWolves.fcItemStoneBrick, 1, ignoreMetadata), 
    		'X', FCBetterThanWolves.fcItemConcentratedHellfire, 
    		'Y', Item.redstone, 
    		'Z', FCBetterThanWolves.fcItemElement
		} );

		AddRecipe( new ItemStack( FCBetterThanWolves.fcHopper ), new Object[] {
    		"# #", 
    		"XYX", 
    		" Z ", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, ignoreMetadata ), 
    		'X', new ItemStack(FCBetterThanWolves.fcItemGear, 1, ignoreMetadata), 
    		'Y', Block.pressurePlatePlanks,
    		'Z', new ItemStack( FCBetterThanWolves.fcBlockWoodCornerItemStubID, 1, ignoreMetadata )
		} );

		AddRecipe( new ItemStack( FCBetterThanWolves.fcSaw ), new Object[] {
    		"YYY", 
    		"XZX", 
    		"#X#", 
    		'#', Block.planks, 
    		'X', new ItemStack(FCBetterThanWolves.fcItemGear, 1, ignoreMetadata), 
    		'Y', Item.ingotIron,
    		'Z', FCBetterThanWolves.fcItemBelt
		} );		
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcSaw ), new Object[] {
    		"YYY", 
    		"XZX", 
    		"#X#", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, ignoreMetadata ), 
    		'X', new ItemStack(FCBetterThanWolves.fcItemGear, 1, ignoreMetadata), 
    		'Y', Item.ingotIron,
    		'Z', FCBetterThanWolves.fcItemBelt
		} );		
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcPlatform ), new Object[] {
    		"#X#", 
    		" # ", 
    		"#X#", 
    		'#', Block.planks, 
    		'X', FCBetterThanWolves.fcBlockWickerPane 
		} );		
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcPlatform ), new Object[] {
    		"X#X", 
    		" X ", 
    		"X#X", 
    		'#', FCBetterThanWolves.fcBlockWickerPane, 
    		'X', new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, ignoreMetadata )
		} );		
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcPulley ), new Object[] {
    		"#Y#", 
    		"XZX", 
    		"#Y#", 
    		'#', Block.planks,
    		'X', new ItemStack(FCBetterThanWolves.fcItemGear, 1, ignoreMetadata),
            'Y', Item.ingotIron, 
    		'Z', FCBetterThanWolves.fcItemRedstoneLatch
		} );		
		
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcPulley ), new Object[] {
    		"#Y#", 
    		"XZX", 
    		"#Y#", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, ignoreMetadata ),
    		'X', new ItemStack(FCBetterThanWolves.fcItemGear, 1, ignoreMetadata),
            'Y', Item.ingotIron, 
    		'Z', FCBetterThanWolves.fcItemRedstoneLatch
		} );		
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcCauldron ), new Object[] {
    		"#Y#", 
    		"#X#", 
    		"###", 
    		'#', Item.ingotIron, 
    		'X', Item.bucketWater, 
    		'Y', Item.bone 
		} );
		
		AddRecipe( new ItemStack( Block.rail, 12 ), new Object[] {
            "X X", 
            "XSX", 
            "X X", 
            'X', FCBetterThanWolves.fcItemNuggetIron, 
            'S', Item.stick
        });
		
		AddRecipe( new ItemStack( Block.railPowered, 6 ), new Object[] {
            "X X", 
            "XSX", 
            "XRX", 
            'X', FCBetterThanWolves.fcItemNuggetIron, 
            'S', Item.stick,
            'R', FCBetterThanWolves.fcItemRedstoneLatch
        });

		AddRecipe( new ItemStack( FCBetterThanWolves.fcDetectorRailWood, 6 ), new Object[] {
            "X X", 
            "X#X", 
            "XRX", 
            'X', FCBetterThanWolves.fcItemNuggetIron, 
            'R', Item.redstone, 
            '#', Block.pressurePlatePlanks
        });
		
		AddRecipe( new ItemStack( Block.railDetector, 6 ), new Object[] {
            "X X", 
            "X#X", 
            "XRX", 
            'X', FCBetterThanWolves.fcItemNuggetIron, 
            'R', Item.redstone, 
            '#', Block.pressurePlateStone
        });
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockDetectorRailSoulforgedSteel, 6 ), new Object[] {
            "X X", 
            "X#X", 
            "XRX", 
            'X', FCBetterThanWolves.fcItemNuggetIron, 
            'R', Item.redstone, 
            '#', FCBetterThanWolves.fcBlockPressurePlateSoulforgedSteel
        });
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockPlanterSoil ), new Object[] {
    		new ItemStack( FCBetterThanWolves.fcPlanter ), 
    		new ItemStack( FCBetterThanWolves.fcBlockDirtLoose )
		} );

		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockPlanterSoil ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcPlanter, 1, FCBlockPlanter.m_iTypeSoil ) 
		} );

		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockPlanterSoil ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcPlanter, 1, FCBlockPlanter.m_iTypeSoilFertilized ) 
		} );

		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcPlanter, 1, FCBlockPlanter.m_iTypeSoulSand ), new Object[] {
    		new ItemStack( FCBetterThanWolves.fcPlanter ), 
    		new ItemStack( Block.slowSand )
		} );

		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockScrewPump ), new Object[] {
    		"XGX", 
    		"WSW", 
    		"WgW", 
    		'W', new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, ignoreMetadata ), 
    		'g', new ItemStack(FCBetterThanWolves.fcItemGear, 1, ignoreMetadata),
    		'S', FCBetterThanWolves.fcItemScrew,
    		'G', FCBetterThanWolves.fcBlockGrate,
    		'X', FCBetterThanWolves.fcItemGlue
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcLens ), new Object[] {
    		"GDG", 
    		"G G", 
    		"G#G", 
    		'#', Block.glass, 
    		'G', Item.ingotGold, 
    		'D', Item.diamond 
		} );

		AddRecipe( new ItemStack( FCBetterThanWolves.fcLens ), new Object[] {
    		"G#G", 
    		"G G", 
    		"GDG", 
    		'#', Block.glass, 
    		'G', Item.ingotGold, 
    		'D', Item.diamond 
		} );

		AddRecipe( new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 2, FCBlockAestheticOpaque.m_iSubtypeBarrel ), new Object[] {
    		"###", 
    		"#X#", 
    		"###", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, ignoreMetadata ), 
    		'X', FCBetterThanWolves.fcItemGlue 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockWhiteStoneStairs, 6, 0 ), new Object[] {
    		"#  ", 
    		"## ", 
    		"###", 
    		'#', new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 1, FCBlockAestheticOpaque.m_iSubtypeWhiteStone ) 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockWhiteStoneStairs, 6, 8 ), new Object[] {
    		"#  ", 
    		"## ", 
    		"###", 
    		'#', new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 1, FCBlockAestheticOpaque.m_iSubtypeWhiteCobble ) 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcAestheticNonOpaque, 6, FCBlockAestheticNonOpaque.m_iSubtypeWhiteCobbleSlab ), new Object[] {
    		"###", 
    		'#', new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 1, FCBlockAestheticOpaque.m_iSubtypeWhiteCobble ) 
		} );
		
		AddShapelessRecipe( new ItemStack( Item.skull.itemID, 1, 5 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcItemSoulUrn ),
    		new ItemStack( FCBetterThanWolves.fcItemSoulFlux ),
    		new ItemStack( Item.skull.itemID, 1, 1 )
		} );		
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockShovel ), new Object[] {
			"#  ", 
			"## ", 
			"###", 
			'#', Item.ingotIron 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockSpikeIron ), new Object[] {
    		"n", 
    		"n", 
    		"I", 
    		'n', FCBetterThanWolves.fcItemNuggetIron, 
    		'I', Item.ingotIron 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockLightningRod ), new Object[] {
    		"n", 
    		"n", 
    		"I", 
    		'n', Item.goldNugget, 
    		'I', Item.ingotGold
		} );
	}
	
	private static void AddItemRecipes()
	{
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemGear, 2 ), new Object[] {
    		" X ", 
    		"X#X", 
    		" X ", 
    		'#', Block.planks, 
    		'X', Item.stick 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemRope, 1 ), new Object[] {
    		"##", 
    		"##", 
    		"##", 
    		'#', FCBetterThanWolves.fcItemHempFibers 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcAnchor ), new Object[] {
    		" X ", 
    		"###", 
    		'#', new ItemStack(FCBetterThanWolves.fcItemStoneBrick, 1, ignoreMetadata), 
    		'X', Item.ingotIron 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemWaterWheel ), new Object[] {
			"###",
    		"# #", 
    		"###", 
    		'#', FCBetterThanWolves.fcItemWoodBlade
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemWindMillBlade ), new Object[] {
    		"###", 
    		"XXX", 
    		'#', FCBetterThanWolves.fcItemHempCloth, 
    		'X', new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, ignoreMetadata ) 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemWindMillBlade ), new Object[] {
    		"###", 
    		"XXX", 
    		'#', FCBetterThanWolves.fcItemHempCloth, 
    		'X', Block.planks 
		} );
		
		AddShapelessRecipeWithSecondaryOutputIndicator( new ItemStack( FCBetterThanWolves.fcItemWindMillBlade ), new ItemStack(FCBetterThanWolves.fcItemWindMillBlade , 3), new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemWindMill ) 
		} );
		
		AddShapelessRecipeWithSecondaryOutputIndicator( new ItemStack( FCBetterThanWolves.fcItemWindMillBlade ), new ItemStack(FCBetterThanWolves.fcItemWindMillBlade , 7), new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemWindMillVertical ) 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemWindMill ), new Object[] {
			" # ",
    		"# #", 
    		" # ", 
    		'#', FCBetterThanWolves.fcItemWindMillBlade 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemHempCloth, 1 ), new Object[] {
    		"###", 
    		"###", 
    		"###", 
    		'#', FCBetterThanWolves.fcItemHempFibers 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemDung, 9 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcBlockAestheticOpaqueEarth, 1, 
				FCBlockAestheticOpaqueEarth.m_iSubtypeDung )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemConcentratedHellfire, 9 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 1, 
				FCBlockAestheticOpaque.m_iSubtypeHellfire )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemPadding, 9 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 1, 
				FCBlockAestheticOpaque.m_iSubtypePadding )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemSoap, 9 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 1, 
				FCBlockAestheticOpaque.m_iSubtypeSoap )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemRope, 9 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 1, 
				FCBlockAestheticOpaque.m_iSubtypeRope )
		} );
		
		AddShapelessRecipe( new ItemStack( Item.flint, 9 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 1, 
				FCBlockAestheticOpaque.m_iSubtypeFlint )
		} );
		
		AddShapelessRecipe( new ItemStack( Item.enderPearl, 9 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 1, 
				FCBlockAestheticOpaque.m_iSubtypeEnderBlock )
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemBelt, 1 ), new Object[] {
    		" # ", 
    		"# #", 
    		" # ", 
    		'#', FCBetterThanWolves.fcItemStrap 
		} );

		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemWoodBlade, 1 ), new Object[] {
    		"#  ", 
    		"#X#", 
    		"#  ", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, ignoreMetadata ), 
    		'X', FCBetterThanWolves.fcItemGlue 
		} );		
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemHaft, 1 ), new Object[] {
    		"Y", 
    		"X", 
    		"#", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, ignoreMetadata ), 
    		'X', FCBetterThanWolves.fcItemGlue, 
    		'Y', FCBetterThanWolves.fcItemStrap, 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemCompositeBow, 1 ), new Object[] {
    		"X#Y", 
    		"ZX#", 
    		"X#Y", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, ignoreMetadata ), 
    		'X', Item.bone, 
    		'Y', FCBetterThanWolves.fcItemGlue, 
    		'Z', Item.silk, 
		} );		
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemBroadheadArrow , 4 ), new Object[] {
    		new ItemStack( Item.feather ),
    		new ItemStack( Item.stick ),
    		new ItemStack( Item.silk ),
    		new ItemStack( FCBetterThanWolves.fcItemBroadheadArrowhead )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemBroadheadArrow , 4 ), new Object[] {
    		new ItemStack( Item.feather ),
    		new ItemStack( Item.stick ),
    		new ItemStack( FCBetterThanWolves.fcItemHempFibers ),
    		new ItemStack( FCBetterThanWolves.fcItemBroadheadArrowhead )
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemArmorGimpHelm ), new Object[] {
    		"###", 
    		"#I#", 
    		'#', FCBetterThanWolves.fcItemTannedLeather, 
    		'I', Item.ingotIron 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemArmorGimpChest ), new Object[] {
    		"# #", 
    		"I#I", 
    		"###", 
    		'#', FCBetterThanWolves.fcItemTannedLeather, 
    		'I', Item.ingotIron 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemArmorGimpLeggings ), new Object[] {
    		"#I#", 
    		"# #", 
    		"# #", 
    		'#', FCBetterThanWolves.fcItemTannedLeather, 
    		'I', Item.ingotIron 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemArmorGimpBoots ), new Object[] {
    		"# #", 
    		"I I", 
    		'#', FCBetterThanWolves.fcItemTannedLeather, 
    		'I', Item.ingotIron 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemArmorGimpHelm ), new Object[] {
    		"###", 
    		"#I#", 
    		'#', FCBetterThanWolves.fcItemTannedLeatherCut, 
    		'I', Item.ingotIron 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemArmorGimpChest ), new Object[] {
    		"# #", 
    		"I#I", 
    		"###", 
    		'#', FCBetterThanWolves.fcItemTannedLeatherCut, 
    		'I', Item.ingotIron 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemArmorGimpLeggings ), new Object[] {
    		"#I#", 
    		"# #", 
    		"# #", 
    		'#', FCBetterThanWolves.fcItemTannedLeatherCut, 
    		'I', Item.ingotIron 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemArmorGimpBoots ), new Object[] {
    		"# #", 
    		"I I", 
    		'#', FCBetterThanWolves.fcItemTannedLeatherCut, 
    		'I', Item.ingotIron 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemPadding ), new Object[] {
    		"C", 
    		"W", 
    		"C", 
    		'C', FCBetterThanWolves.fcItemHempCloth, 
    		'W', new ItemStack( FCBetterThanWolves.fcItemWool, 1, ignoreMetadata )
        } );
        
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemDynamite, 2 ), new Object[] {
    		"PF", 
    		"PN", 
    		"PS", 
    		'P', Item.paper, 
    		'F', FCBetterThanWolves.fcItemFuse, 
    		'N', FCBetterThanWolves.fcItemBlastingOil, 
    		'S', FCBetterThanWolves.fcItemSawDust 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemDynamite, 2 ), new Object[] {
    		"PF", 
    		"PN", 
    		"PS", 
    		'P', Item.paper, 
    		'F', FCBetterThanWolves.fcItemFuse, 
    		'N', FCBetterThanWolves.fcItemBlastingOil, 
    		'S', FCBetterThanWolves.fcItemSoulDust 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemBreedingHarness ), new Object[] {
    		"SLS", 
    		"LLL", 
    		"SLS", 
    		'S', FCBetterThanWolves.fcItemStrap, 
    		'L', FCBetterThanWolves.fcItemTannedLeather 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemBreedingHarness ), new Object[] {
    		"SLS", 
    		"LLL", 
    		"SLS", 
    		'S', FCBetterThanWolves.fcItemStrap, 
    		'L', FCBetterThanWolves.fcItemTannedLeatherCut 
		} );
		
		AddRecipe(new ItemStack(FCBetterThanWolves.fcItemCandle, 4, 16), new Object[] {
    		"H", 
    		"T", 
    		'H', FCBetterThanWolves.fcItemHempFibers, 
    		'T', FCBetterThanWolves.fcItemTallow 
		});
		
		AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemCandle, 4, 16), new Object[] {
    		new ItemStack(FCBetterThanWolves.fcItemCandleLegacy)
		});
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemScrew ), new Object[] {
    		"n# ", 
    		" #n", 
    		"n# ", 
    		'#', new ItemStack(Item.ingotIron),
    		'n', new ItemStack(FCBetterThanWolves.fcItemNuggetIron)
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemOcularOfEnder, 1, 0 ), new Object[] {
    		"ggg", 
    		"gEg", 
    		"ggg", 
    		'g', Item.goldNugget, 
    		'E', Item.enderPearl 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemEnderSpectacles ), new Object[] {
    		"OSO", 
    		'S', FCBetterThanWolves.fcItemStrap,
    		'O', FCBetterThanWolves.fcItemOcularOfEnder    		
		} );

		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemStake, 2 ), new Object[] {	    		
    		"S", 
    		"M", 
    		'M', new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, ignoreMetadata ),
    		'S', new ItemStack( Item.silk )
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemWindMillVertical ), new Object[] {
			"SSS",
    		"S S", 
    		"SSS", 
    		'S', FCBetterThanWolves.fcItemWindMillBlade 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemTastySandwich, 2 ), new Object[] {	    		
    		new ItemStack( Item.bread ),
    		new ItemStack( Item.beefCooked )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemTastySandwich, 2 ), new Object[] {	    		
    		new ItemStack( Item.bread ),
    		new ItemStack( Item.porkCooked )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemTastySandwich, 2 ), new Object[] {	    		
    		new ItemStack( Item.bread ),
    		new ItemStack( Item.chickenCooked )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemTastySandwich, 2 ), new Object[] {	    		
    		new ItemStack( Item.bread ),
    		new ItemStack( Item.fishCooked )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemTastySandwich, 2 ), new Object[] {	    		
    		new ItemStack( Item.bread ),
    		new ItemStack( FCBetterThanWolves.fcItemWolfCooked )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemTastySandwich, 2 ), new Object[] {	    		
    		new ItemStack( Item.bread ),
    		new ItemStack( FCBetterThanWolves.fcItemMuttonCooked )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemTastySandwich, 2 ), new Object[] {	    		
    		new ItemStack( Item.bread ),
    		new ItemStack( FCBetterThanWolves.fcItemCookedMysteryMeat )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemSteakAndPotatoes, 2 ), new Object[] {	    		
    		new ItemStack( Item.bakedPotato ),
    		new ItemStack( Item.beefCooked )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemSteakAndPotatoes, 2 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcItemBoiledPotato ),
    		new ItemStack( Item.beefCooked )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemHamAndEggs, 2 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcItemHardBoiledEgg ),
    		new ItemStack( Item.porkCooked )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemHamAndEggs, 2 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcItemFriedEgg ),
    		new ItemStack( Item.porkCooked )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemSteakDinner, 3 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcItemBoiledPotato ),
    		new ItemStack( FCBetterThanWolves.fcItemCookedCarrot ),
    		new ItemStack( Item.beefCooked )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemSteakDinner, 3 ), new Object[] {	    		
    		new ItemStack( Item.bakedPotato ),
    		new ItemStack( FCBetterThanWolves.fcItemCookedCarrot ),
    		new ItemStack( Item.beefCooked )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemPorkDinner, 3 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcItemBoiledPotato ),
    		new ItemStack( FCBetterThanWolves.fcItemCookedCarrot ),
    		new ItemStack( Item.porkCooked )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemPorkDinner, 3 ), new Object[] {	    		
    		new ItemStack( Item.bakedPotato ),
    		new ItemStack( FCBetterThanWolves.fcItemCookedCarrot ),
    		new ItemStack( Item.porkCooked )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemWolfDinner, 3 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcItemBoiledPotato ),
    		new ItemStack( FCBetterThanWolves.fcItemCookedCarrot ),
    		new ItemStack( FCBetterThanWolves.fcItemWolfCooked )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemWolfDinner, 3 ), new Object[] {	    		
    		new ItemStack( Item.bakedPotato ),
    		new ItemStack( FCBetterThanWolves.fcItemCookedCarrot ),
    		new ItemStack( FCBetterThanWolves.fcItemWolfCooked )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemRawKebab, 3 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcItemMushroomBrown ),
    		new ItemStack( FCBetterThanWolves.fcItemCarrot ),
    		new ItemStack( FCBetterThanWolves.fcItemMuttonRaw ),
    		new ItemStack( Item.stick ),
		} );
		
		AddShapelessRecipeWithSecondaryOutputIndicator( new ItemStack( Block.pumpkin ), new ItemStack(Item.pumpkinSeeds, 4), new Object[] {
			new ItemStack( FCBetterThanWolves.fcBlockPumpkinFresh )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemRawMushroomOmelet, 2 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcItemMushroomBrown ),
    		new ItemStack( FCBetterThanWolves.fcItemMushroomBrown ),
    		new ItemStack( FCBetterThanWolves.fcItemMushroomBrown ),
    		new ItemStack( FCBetterThanWolves.fcItemRawEgg )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemRawScrambledEggs, 2 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcItemRawEgg ),
    		new ItemStack( Item.bucketMilk )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemRawScrambledEggs, 2 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcItemHardBoiledEgg ),
    		new ItemStack( Item.bucketMilk )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemRawScrambledEggs, 2 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcItemFriedEgg ),
    		new ItemStack( Item.bucketMilk )
		} );
		
		AddRecipe( new ItemStack( Item.helmetChain ), new Object[] {
    		"###", 
    		"# #", 
    		'#', FCBetterThanWolves.fcItemMail 
		} );
		
		AddRecipe( new ItemStack( Item.plateChain ), new Object[] {
    		"# #", 
    		"###", 
    		"###", 
    		'#', FCBetterThanWolves.fcItemMail
		} );
		
		AddRecipe( new ItemStack( Item.legsChain ), new Object[] {
    		"###", 
    		"# #", 
    		"# #", 
    		'#', FCBetterThanWolves.fcItemMail 
		} );
		
		AddRecipe( new ItemStack( Item.bootsChain ), new Object[] {
    		"# #", 
    		"# #", 
    		'#', FCBetterThanWolves.fcItemMail 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemArmorPaddedHelm ), new Object[] {
    		"###", 
    		"# #", 
    		'#', FCBetterThanWolves.fcItemPadding 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemArmorPaddedChest ), new Object[] {
    		"# #", 
    		"###", 
    		"###", 
    		'#', FCBetterThanWolves.fcItemPadding
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemArmorPaddedLeggings ), new Object[] {
    		"###", 
    		"# #", 
    		"# #", 
    		'#', FCBetterThanWolves.fcItemPadding 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemArmorTannedHelm ), new Object[] {
    		"###", 
    		"# #", 
    		'#', FCBetterThanWolves.fcItemTannedLeather 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemArmorTannedChest ), new Object[] {
    		"# #", 
    		"###", 
    		"###", 
    		'#', FCBetterThanWolves.fcItemTannedLeather 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemArmorTannedLeggings ), new Object[] {
    		"###", 
    		"# #", 
    		"# #", 
    		'#', FCBetterThanWolves.fcItemTannedLeather 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemArmorTannedBoots ), new Object[] {
    		"# #", 
    		"# #", 
    		'#', FCBetterThanWolves.fcItemTannedLeather 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemArmorTannedHelm ), new Object[] {
    		"###", 
    		"# #", 
    		'#', FCBetterThanWolves.fcItemTannedLeatherCut 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemArmorTannedChest ), new Object[] {
    		"# #", 
    		"###", 
    		"###", 
    		'#', FCBetterThanWolves.fcItemTannedLeatherCut 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemArmorTannedLeggings ), new Object[] {
    		"###", 
    		"# #", 
    		"# #", 
    		'#', FCBetterThanWolves.fcItemTannedLeatherCut
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemArmorTannedBoots ), new Object[] {
    		"# #", 
    		"# #", 
    		'#', FCBetterThanWolves.fcItemTannedLeatherCut
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemIngotDiamond ), new Object[] {	    		
    		new ItemStack( Item.ingotIron ),
    		new ItemStack( Item.diamond ),
    		new ItemStack( FCBetterThanWolves.fcItemCreeperOysters )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemLeatherCut, 2 ), new Object[] {	    		
    		new ItemStack( Item.shears, 1, ignoreMetadata ),
    		new ItemStack( Item.leather )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemTannedLeatherCut, 2 ), new Object[] {	    		
    		new ItemStack( Item.shears, 1, ignoreMetadata ),
    		new ItemStack( FCBetterThanWolves.fcItemTannedLeather )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemScouredLeatherCut, 2 ), new Object[] {	    		
    		new ItemStack( Item.shears, 1, ignoreMetadata ),
    		new ItemStack( FCBetterThanWolves.fcItemScouredLeather )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemStrap, 4 ), new Object[] {	    		
    		new ItemStack( Item.shears, 1, ignoreMetadata ),
    		new ItemStack( FCBetterThanWolves.fcItemTannedLeatherCut )
		} );

		FCRecipes.AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemLeatherCut, 2), new Object[] {
				new ItemStack(FCBetterThanWolves.fcItemShearsDiamond, 1, 32767), 
				new ItemStack(Item.leather)
		});
		
		FCRecipes.AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemTannedLeatherCut, 2), new Object[] {
				new ItemStack(FCBetterThanWolves.fcItemShearsDiamond, 1, 32767), 
				new ItemStack(FCBetterThanWolves.fcItemTannedLeather)
		});
		
		FCRecipes.AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemScouredLeatherCut, 2), new Object[] {
				new ItemStack(FCBetterThanWolves.fcItemShearsDiamond, 1, 32767), 
				new ItemStack(FCBetterThanWolves.fcItemScouredLeather)
		});
		
		FCRecipes.AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemStrap, 4), new Object[] {
				new ItemStack(FCBetterThanWolves.fcItemShearsDiamond, 1, 32767), 
				new ItemStack(FCBetterThanWolves.fcItemTannedLeatherCut)
		});
		
		FCRecipes.AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemLeatherCut, 2), new Object[] {
				new ItemStack(FCBetterThanWolves.fcItemShearsDiamond, 1, 32767), 
				new ItemStack(Item.leather)
		});
		
		FCRecipes.AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemTannedLeatherCut, 2), new Object[] {
				new ItemStack(FCBetterThanWolves.fcItemShearsDiamond, 1, 32767), 
				new ItemStack(FCBetterThanWolves.fcItemTannedLeather)
		});
		
		FCRecipes.AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemScouredLeatherCut, 2), new Object[] {
				new ItemStack(FCBetterThanWolves.fcItemShearsDiamond, 1, 32767), 
				new ItemStack(FCBetterThanWolves.fcItemScouredLeather)
		});
		
		FCRecipes.AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemStrap, 4), new Object[] {
				new ItemStack(FCBetterThanWolves.fcItemShearsDiamond, 1, 32767), 
				new ItemStack(FCBetterThanWolves.fcItemTannedLeatherCut)
		});
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemGoldenDung ), new Object[] {
    		"ggg", 
    		"gDg", 
    		"ggg", 
    		'D', FCBetterThanWolves.fcItemDung,
    		'g', Item.goldNugget,
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemRedstoneLatch ), new Object[] {
    		"ggg", 
    		" r ", 
    		'g', Item.goldNugget,
    		'r', Item.redstone
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemBucketMilkChocolate ), new Object[] {	    		
    		new ItemStack( Item.bucketMilk ),
    		new ItemStack( Item.dyePowder, 1, 3 ) // Cocoa powder
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemStumpRemover, 2 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcItemCreeperOysters ),
    		new ItemStack( FCBetterThanWolves.fcItemMushroomRed ),
    		new ItemStack( Item.rottenFlesh )
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemPolishedLapis, 2 ), new Object[] {
    		"###", 
    		"GGG", 
    		" R ", 
    		'#', new ItemStack( Item.dyePowder, 1, 4 ), 
    		'G', Item.goldNugget, 
    		'R', Item.redstone 
		} );
		
		AddRecipe(new ItemStack(Item.comparator), new Object[] {
	    	" R ",
    		"RER",
    		"SSS",
    		'E', FCBetterThanWolves.fcItemPolishedLapis,
    		'S', new ItemStack(FCBetterThanWolves.fcItemStoneBrick, 1, ignoreMetadata),
    		'R', Block.torchRedstoneActive
		});
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemCorpseEye , 1 ), new Object[] {
	    		new ItemStack( FCBetterThanWolves.fcItemSoulUrn ),
	    		new ItemStack( Item.enderPearl ),
	    		new ItemStack( FCBetterThanWolves.fcItemRawMysteryMeat )
			} );
		
		// Recipe awaiting renaming functionality to be enabled
		//AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemNameTag), new Object[] {	    		
	    //	new ItemStack(Item.leather),
	    //	new ItemStack(FCBetterThanWolves.fcItemNuggetIron)
		//});
		
		//AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemNameTag), new Object[] {	    		
	    //	new ItemStack(FCBetterThanWolves.fcItemLeatherCut),
	    //	new ItemStack(FCBetterThanWolves.fcItemNuggetIron)
		//});
	}
	
	private static void AddDyeRecipes()
	{
		// only to 14 to avoid white on white conversion
        for ( int i = 0; i < 15; i++ )
        {
            AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemWool, 1, i ), new Object[] {
                new ItemStack( Item.dyePowder, 1, i ), 
                new ItemStack( FCBetterThanWolves.fcItemWool, 1, 15 )
            } );
        }
        
        AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemCandle, 1, 3 ), new Object[] {
            new ItemStack( FCBetterThanWolves.fcItemDung ), 
            new ItemStack( FCBetterThanWolves.fcItemCandle, 1, 16 )
        } );
        
        AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemWool, 1, 3 ), new Object[] {
            new ItemStack( FCBetterThanWolves.fcItemDung ), 
            new ItemStack( FCBetterThanWolves.fcItemWool, 1, 15 )
        } );
        
        for ( int i = 0; i < 15; i++ )
        {
        	AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemCandle, 1, i), new Object[] {
                    new ItemStack(Item.dyePowder, 1, i), 
                    new ItemStack(FCBetterThanWolves.fcItemCandle, 1, 16)
            });
        	
            AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcVase, 1, BlockCloth.getBlockFromDye( i ) ), new Object[] {
                new ItemStack( Item.dyePowder, 1, i ), 
                new ItemStack( Item.itemsList[FCBetterThanWolves.fcVase.blockID], 1, 0 )
            });
            
            AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcWoolSlab, 1, BlockCloth.getBlockFromDye( i ) ), new Object[] {
                new ItemStack( Item.dyePowder, 1, i ), 
                new ItemStack( Item.itemsList[FCBetterThanWolves.fcWoolSlab.blockID], 1, 0 )
            });
        }
		
        AddShapelessRecipe( new ItemStack( Block.cloth, 1, 12 ), new Object[] {
            new ItemStack( FCBetterThanWolves.fcItemDung ), 
            new ItemStack( Item.itemsList[Block.cloth.blockID], 1, 0 )
        } );
        
        AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcVase, 1, 12 ), new Object[] {
            new ItemStack( FCBetterThanWolves.fcItemDung ), 
            new ItemStack( Item.itemsList[FCBetterThanWolves.fcVase.blockID], 1, 0 )
        } );
        
        AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcWoolSlab, 1, 12 ), new Object[] {
            new ItemStack( FCBetterThanWolves.fcItemDung ), 
            new ItemStack( Item.itemsList[FCBetterThanWolves.fcWoolSlab.blockID], 1, 0 )
        } );
	}
	
	private static void AddAlternateVanillaRecipes()
	{
		// Alternate recipes for vanilla items

		AddRecipe( new ItemStack( Block.pistonBase, 1 ), new Object[] {
            "#I#", 
            "XYX",
            "XZX",
            Character.valueOf('#'), new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, ignoreMetadata ), 
            Character.valueOf('I'), Item.ingotIron,
            Character.valueOf('X'), new ItemStack(FCBetterThanWolves.fcItemStoneBrick, 1, ignoreMetadata),
            Character.valueOf('Y'), FCBetterThanWolves.fcItemSoulUrn,
            Character.valueOf('Z'), FCBetterThanWolves.fcItemRedstoneLatch
		} );
		
		AddRecipe( new ItemStack( Block.fenceGate, 1 ), new Object[] {
            "#X#", 
            Character.valueOf('#'), new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, ignoreMetadata ), 
            Character.valueOf('X'), new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, ignoreMetadata )
		} );
		
		AddRecipe( new ItemStack( Block.stairsWoodOak, 1 ), new Object[] {
            "# ", 
            "##", 
            Character.valueOf('#'), new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 0 ) 
		} );
		
		AddRecipe( new ItemStack( Block.stairsWoodSpruce, 1 ), new Object[] {
            "# ", 
            "##", 
            Character.valueOf('#'), new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 1 ) 
		} );

		AddRecipe( new ItemStack( Block.stairsWoodBirch, 1 ), new Object[] {
            "# ", 
            "##", 
            Character.valueOf('#'), new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 2 ) 
		} );

		AddRecipe( new ItemStack( Block.stairsWoodJungle, 1 ), new Object[] {
            "# ", 
            "##", 
            Character.valueOf('#'), new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 3 ) 
		} );
		
		AddRecipe( new ItemStack( Block.stairsStoneBrick ), new Object[] {
            "# ", 
            "##", 
            Character.valueOf('#'), new ItemStack( FCBetterThanWolves.fcBlockStoneBrickMouldingAndDecorative ) 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockStoneBrickStairsMidStrata ), new Object[] {
	            "# ", 
	            "##", 
	            Character.valueOf('#'), new ItemStack( FCBetterThanWolves.fcBlockStoneBrickMouldingAndDecorativeMidStrata ) 
			} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockStoneBrickStairsDeepStrata ), new Object[] {
	            "# ", 
	            "##", 
	            Character.valueOf('#'), new ItemStack( FCBetterThanWolves.fcBlockStoneBrickMouldingAndDecorativeDeepStrata ) 
			} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockWhiteStoneStairs ), new Object[] {
            "# ", 
            "##", 
            Character.valueOf('#'), new ItemStack( FCBetterThanWolves.fcBlockWhiteStoneMouldingAndDecorative ) 
		} );
		
		AddRecipe( new ItemStack( Block.stairsNetherBrick ), new Object[] {
            "# ", 
            "##", 
            Character.valueOf('#'), new ItemStack( FCBetterThanWolves.fcBlockNetherBrickMouldingAndDecorative ) 
		} );
		
		AddRecipe( new ItemStack( Block.stairsBrick ), new Object[] {
            "# ", 
            "##", 
            Character.valueOf('#'), new ItemStack( FCBetterThanWolves.fcBlockBrickMouldingAndDecorative ) 
		} );
		
		AddRecipe( new ItemStack( Block.stairsSandStone ), new Object[] {
            "# ", 
            "##", 
            Character.valueOf('#'), new ItemStack( FCBetterThanWolves.fcBlockSandstoneMouldingAndDecorative ) 
		} );
		
		AddRecipe( new ItemStack( Block.stairsNetherQuartz ), new Object[] {
            "# ", 
            "##", 
            Character.valueOf('#'), new ItemStack( FCBetterThanWolves.fcBlockMouldingAndDecorativeBlackStone ) 
		} );
		
		AddRecipe( new ItemStack( Item.sign, 3 ), new Object[] {
            "#", 
            "X", 
            Character.valueOf('#'), new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, ignoreMetadata ), 
            Character.valueOf('X'), new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, ignoreMetadata )
		} );
		
		AddRecipe( new ItemStack( Item.doorWood, 1 ), new Object[] {
            "##", 
            "##", 
            "##", 
            Character.valueOf('#'), new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, ignoreMetadata )
		} );
		
		AddRecipe( new ItemStack( Block.trapdoor, 1 ), new Object[] {
			"WW#", 
			"WW#", 
			Character.valueOf('#'), Item.stick, 
			Character.valueOf('W'), Block.planks
		} );
		
		AddRecipe( new ItemStack( Block.trapdoor, 2 ), new Object[] {
			"WW#", 
			"WW#", 
			Character.valueOf('#'), Item.stick, 
            Character.valueOf('W'), new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, ignoreMetadata )
		} );
		
		AddRecipe( new ItemStack( Item.boat, 1 ), new Object[] {
            "# #", 
            "###", 
            Character.valueOf('#'), new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, ignoreMetadata )
		} );
        
		AddRecipe( new ItemStack( Block.bookShelf ), new Object[] {
    		"###", 
    		"XXX", 
    		"###", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, ignoreMetadata ), 
    		'X', Item.book
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockChest ), new Object[] {
            "###", 
            "# #", 
            "###", 
    		'#', new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, ignoreMetadata ) 
        } );
		
		AddRecipe( new ItemStack( Item.minecartCrate, 1 ), new Object[] {
			"A", 
			"B", 
			'A', FCBetterThanWolves.fcBlockChest, 
			'B', Item.minecartEmpty
		} );
		
		AddRecipe( new ItemStack( Item.redstoneRepeater, 1 ), new Object[] {
        	"#X#", 
        	"III", 
        	'#', Block.torchRedstoneActive, 
        	'X', Item.pocketSundial, 
        	'I', new ItemStack(FCBetterThanWolves.fcItemStoneBrick, 1, ignoreMetadata) } );
        
		AddRecipe( new ItemStack( FCBetterThanWolves.fcInfernalEnchanter ), new Object[] {
    		"CBC", 
    		"SES", 
    		"SSS", 
    		'S', FCBetterThanWolves.fcItemSteel,
    		'C', new ItemStack( FCBetterThanWolves.fcItemCandle, 1, 0 ),
    		'E', Block.enchantmentTable,
    		'B', Item.bone
		} );
		
        AddShapelessRecipe( new ItemStack( Item.stick ), new Object[] {
        	new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, ignoreMetadata ) 
        } );
        
		AddRecipe( new ItemStack( Block.jukebox, 1 ), new Object[] {
			"###", 
			"#X#", 
			"###", 
			'#', new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, ignoreMetadata ),
			'X', Item.diamond
        });
		
		AddRecipe( new ItemStack( Block.music, 1 ), new Object[] {
			"###", 
			"#X#", 
			"###", 
			'#', new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, ignoreMetadata ),
			'X', FCBetterThanWolves.fcItemRedstoneLatch
		} );
		
		AddRecipe( new ItemStack( Block.tnt, 1), new Object[] {
			"GFG", 
			"GBG", 
			"GGG", 
			'B', new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 1, FCBlockAestheticOpaque.m_iSubtypeBarrel ),
			'G', Item.gunpowder,
			'F', FCBetterThanWolves.fcItemFuse
		} );
		
		AddShapelessRecipe( new ItemStack( Item.gunpowder ), new Object[] { 
            new ItemStack( FCBetterThanWolves.fcItemNitre ),
            new ItemStack( FCBetterThanWolves.fcItemBrimstone ),
            new ItemStack( FCBetterThanWolves.fcItemCoalDust ),
        } );
		
		AddRecipe( new ItemStack( Block.anvil, 1 ), new Object[] {
			"iii", 
			" i ", 
			"iii", 
			'i', Item.ingotIron
		} );
		
		// chiseled stone brick
		
		for (int strata = 0; strata < 3; strata++) {
			AddAnvilRecipe( new ItemStack( Block.stoneBrick, 12, 3 + (strata << 2)), new Object[] {
		    		"####", 
		    		"#  #", 
		    		"#  #", 
		    		"####", 
		    		'#', new ItemStack(Block.stoneBrick, 1, strata << 2)
				} );	
		}
		
		AddRecipe( new ItemStack( Item.bowlEmpty, 6 ), new Object[] {
            "# #", 
            " # ", 
            Character.valueOf('#'), new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, ignoreMetadata ), 
		} );
		
		AddRecipe( new ItemStack( Item.compass, 1 ), new Object[] {
			" # ", 
			"#X#", 
			" # ", 
			'#', FCBetterThanWolves.fcItemNuggetIron, 
			'X', Item.redstone
		} );
		
		AddRecipe( new ItemStack( Item.pocketSundial, 1 ), new Object[] {
			" # ", 
			"#X#", 
			" # ", 
			'#', Item.goldNugget, 
			'X', Item.netherQuartz } );
		
		AddShapelessRecipe( new ItemStack( Item.flintAndSteel, 1 ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron ), 
			new ItemStack( Item.flint )
		} );
		
		AddShapelessRecipe( new ItemStack( Item.clay, 9 ), new Object[] { 
            new ItemStack( FCBetterThanWolves.fcBlockUnfiredClay ),
        } );
		
		AddRecipe( new ItemStack( Item.bucketEmpty, 1 ), new Object[] {
			"# #", 
			"# #", 
			"###", 
			'#', FCBetterThanWolves.fcItemNuggetIron 
		} );
		
		AddShapelessRecipe( new ItemStack( Item.silk, 2 ), new Object[] { 
            new ItemStack( FCBetterThanWolves.fcBlockWeb ),
		} );		
		
		// high effeciency leather recipes
		
		AddRecipe( new ItemStack( Item.helmetLeather ), new Object[] {
    		"###", 
    		"# #", 
    		'#', FCBetterThanWolves.fcItemLeatherCut 
		} );
		
		AddRecipe( new ItemStack( Item.plateLeather ), new Object[] {
    		"# #", 
    		"###", 
    		"###", 
    		'#', FCBetterThanWolves.fcItemLeatherCut 
		} );
		
		AddRecipe( new ItemStack( Item.legsLeather ), new Object[] {
    		"###", 
    		"# #", 
    		"# #", 
    		'#', FCBetterThanWolves.fcItemLeatherCut 
		} );
		
		AddRecipe( new ItemStack( Item.bootsLeather ), new Object[] {
    		"# #", 
    		"# #", 
    		'#', FCBetterThanWolves.fcItemLeatherCut 
		} );
		
		AddShapelessRecipe( new ItemStack( Item.writableBook, 1 ), new Object[] {
			Item.paper, 
			Item.paper, 
			Item.paper, 
			FCBetterThanWolves.fcItemLeatherCut,
			new ItemStack( Item.dyePowder, 1, 0 ), 
			Item.feather
		} );
		
		AddShapelessRecipe( new ItemStack( Item.writableBook, 1 ), new Object[] {
			Item.paper, 
			Item.paper, 
			Item.paper, 
			Item.leather,
			new ItemStack( Item.dyePowder, 1, 0 ), 
			Item.feather
		} );
		
		AddRecipe( new ItemStack( Item.itemFrame, 1 ), new Object[] {
			"mmm", 
			"mlm", 
			"mmm", 
			'm', new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, ignoreMetadata ),
			'l', FCBetterThanWolves.fcItemLeatherCut
		} );		
        
		AddRecipe( new ItemStack( Item.itemFrame, 1 ), new Object[] {
			"mmm", 
			"mlm", 
			"mmm", 
			'm', Item.stick,
			'l', FCBetterThanWolves.fcItemLeatherCut
		} );		
        
		AddRecipe( new ItemStack( Item.helmetDiamond ), new Object[] {
			"XXX", 
			"XYX", 
			'X', FCBetterThanWolves.fcItemIngotDiamond,
			'Y', FCBetterThanWolves.fcItemDiamondPlate
		} ); 
	   
		AddRecipe( new ItemStack( Item.plateDiamond ), new Object[] {
			"Y Y", 
			"XXX", 
			"XXX", 
			'X', FCBetterThanWolves.fcItemIngotDiamond,
			'Y', FCBetterThanWolves.fcItemDiamondPlate
		} );
	   
		AddRecipe( new ItemStack( Item.legsDiamond ), new Object[] {
			"XXX", 
			"Y Y", 
			"Y Y", 
			'X', FCBetterThanWolves.fcItemIngotDiamond,
			'Y', FCBetterThanWolves.fcItemDiamondPlate
		} );
	   
		AddRecipe( new ItemStack( Item.bootsDiamond ), new Object[] {
			"X X", 
			"X X", 
			'X', FCBetterThanWolves.fcItemIngotDiamond
		} );
		
		AddRecipe( new ItemStack( Item.swordDiamond ), new Object[] {
			"X", 
			"X", 
			"#",
			'#', Item.stick,
			'X', FCBetterThanWolves.fcItemIngotDiamond
		} );
		
		AddRecipe( new ItemStack( Item.pickaxeDiamond ), new Object[] {
			"XXX", 
			" # ", 
			" # ",
			'#', Item.stick,
			'X', FCBetterThanWolves.fcItemIngotDiamond			
		} ); 
		
		AddRecipe( new ItemStack( Item.shovelDiamond ), new Object[] {
			"X", 
			"#", 
			"#",
			'#', Item.stick,
			'X', FCBetterThanWolves.fcItemIngotDiamond			
		} ); 
		
		AddRecipe( new ItemStack( Block.lever, 1 ), new Object[] {
			"X", 
			"#", 
			"r",
			'#', new ItemStack(FCBetterThanWolves.fcItemStoneBrick, 1, ignoreMetadata), 
			'X', Item.stick, 
			'r', Item.redstone 
		} );
		
		AddRecipe( new ItemStack( Block.stoneButton, 1 ), new Object[] {
        	"#",
        	"r",
    		'#', new ItemStack( Item.itemsList[FCBetterThanWolves.fcBlockSmoothStoneSidingAndCorner.blockID], 1, 1 ),
			'r', Item.redstone 
    	} );
		
		AddRecipe( new ItemStack( Block.stoneButton, 1 ), new Object[] {
        	"#",
        	"r",
    		'#', new ItemStack( Item.itemsList[FCBetterThanWolves.fcBlockSmoothStoneSidingAndCornerMidStrata.blockID], 1, 1 ),
			'r', Item.redstone 
    	} );
		
		AddRecipe( new ItemStack( Block.stoneButton, 1 ), new Object[] {
        	"#",
        	"r",
    		'#', new ItemStack( Item.itemsList[FCBetterThanWolves.fcBlockSmoothStoneSidingAndCornerDeepStrata.blockID], 1, 1 ),
			'r', Item.redstone 
    	} );
		
		AddRecipe( new ItemStack( Block.woodenButton, 1 ), new Object[] {
        	"#", 
        	"r",
    		'#', new ItemStack( FCBetterThanWolves.fcBlockWoodCornerItemStubID, 1, ignoreMetadata ),
			'r', Item.redstone 
    	} );
        
		AddRecipe( new ItemStack( Block.pressurePlateStone, 1 ), new Object[] {
        	"#", 
        	"r",
    		'#', new ItemStack( Item.itemsList[FCBetterThanWolves.fcBlockSmoothStoneSidingAndCorner.blockID], 1, 0 ),
			'r', Item.redstone 
    	} );
		
		AddRecipe( new ItemStack( Block.pressurePlateStone, 1 ), new Object[] {
	        	"#", 
	        	"r",
	    		'#', new ItemStack( Item.itemsList[FCBetterThanWolves.fcBlockSmoothStoneSidingAndCornerMidStrata.blockID], 1, 0 ),
				'r', Item.redstone 
	    	} );
		
		AddRecipe( new ItemStack( Block.pressurePlateStone, 1 ), new Object[] {
	        	"#", 
	        	"r",
	    		'#', new ItemStack( Item.itemsList[FCBetterThanWolves.fcBlockSmoothStoneSidingAndCornerDeepStrata.blockID], 1, 0 ),
				'r', Item.redstone 
	    	} );
	        
		AddRecipe( new ItemStack( Block.pressurePlatePlanks, 1 ), new Object[] {
        	"#", 
        	"r",
    		'#', new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, ignoreMetadata ), 
			'r', Item.redstone 
    	} );
		
		AddRecipe( new ItemStack( Item.doorIron, 1 ), new Object[] {
			"##r", 
			"## ", 
			"##r", 
			'#', Item.ingotIron,
			'r', FCBetterThanWolves.fcItemRedstoneLatch 
		} );
		
		AddRecipe( new ItemStack( Block.dispenser, 1 ), new Object[] {
			"###", 
			"#X#", 
			"#R#", 
			'#', new ItemStack(FCBetterThanWolves.fcItemStoneBrick, 1, ignoreMetadata), 
			'X', Item.bow, 
			'R', FCBetterThanWolves.fcItemRedstoneLatch
		} );
		
		AddRecipe( new ItemStack( Block.music, 1 ), new Object[] {
			"###", 
			"#X#", 
			"###", 
			'#', Block.planks, 
			'X', FCBetterThanWolves.fcItemRedstoneLatch
		} );
		
		AddShapelessRecipe( new ItemStack( Block.pumpkinLantern, 1 ), new Object[] {
			new ItemStack( Block.pumpkin ), 
			new ItemStack( FCBetterThanWolves.fcItemCandle, 1, ignoreMetadata ) 
		} );		
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockUnfiredClay, 1 ), new Object[] {
			"###", 
			"###", 
			"###", 
			'#', Item.clay
		} );
        
		// axe recipe rebalance
		
        AddRecipe( new ItemStack( Item.axeIron ), new Object[] {
        	"X ", 
        	"X#", 
        	" #",
        	'#', Item.stick, 
        	'X', Item.ingotIron
    	} );        
        
        AddRecipe( new ItemStack( Item.axeGold ), new Object[] {
        	"X ", 
        	"X#", 
        	" #",
        	'#', Item.stick, 
        	'X', Item.ingotGold
    	} );
        
		AddRecipe( new ItemStack( Item.axeDiamond ), new Object[] {
			"X ", 
			"X#", 
			" #", 
			'#', Item.stick,
			'X', FCBetterThanWolves.fcItemIngotDiamond			
		} );
		
		AddShapelessRecipe( new ItemStack( Item.stick, 2 ), new Object[] { 
            new ItemStack( Block.planks, 1, ignoreMetadata )
        } );		
		
		AddRecipe(new ItemStack(Item.bed), new Object[] {
			"###",
			"XXX",
			'#', FCBetterThanWolves.fcItemPadding,
			'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, ignoreMetadata)
		});
		
		AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemBedroll), new Object[] {
				new ItemStack(FCBetterThanWolves.fcItemWoolKnit, 1, ignoreMetadata),
				new ItemStack(FCBetterThanWolves.fcItemWoolKnit, 1, ignoreMetadata),
				new ItemStack(Item.silk)
		});
		
		// map creation and zooming
		
		AddRecipe( new ItemStack( Item.emptyMap, 1 ), new Object[] {
			"#S#", 
			"#X#", 
			"#S#", 
			'#', Item.paper, 
			'X', Item.compass,
			'S', FCBetterThanWolves.fcItemSoulUrn
		} );
		
		AddRecipe( new ItemStack( Item.emptyMap, 1, 1 ), new Object[] {
			"###", 
			"#X#", 
			"###", 
			'#', Item.paper, 
			'X', new ItemStack( Item.emptyMap, 1, 0 )
		} );
		
		AddRecipe( new ItemStack( Item.emptyMap, 1, 2 ), new Object[] {
			"###", 
			"#X#", 
			"###", 
			'#', Item.paper, 
			'X', new ItemStack( Item.emptyMap, 1, 1 )
		} );
		
		AddRecipe( new ItemStack( Item.emptyMap, 1, 3 ), new Object[] {
			"###", 
			"#X#", 
			"###", 
			'#', Item.paper, 
			'X', new ItemStack( Item.emptyMap, 1, 2 )
		} );
		
		AddRecipe( new ItemStack( Item.emptyMap, 1, 4 ), new Object[] {
			"###", 
			"#X#", 
			"###", 
			'#', Item.paper, 
			'X', new ItemStack( Item.emptyMap, 1, 3 )
		} );		
	}
	
	private static void AddConversionRecipes()
	{
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemHempFibers, 9 ), new Object[] {
    		new ItemStack( FCBetterThanWolves.fcItemHempCloth ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemHempFibers, 6 ), new Object[] {
    		new ItemStack( FCBetterThanWolves.fcItemRope ) 
		} );
		
		//Legacy support
		AddShapelessRecipe( new ItemStack( Block.cobblestone ), new Object[] {	    		
    		new ItemStack( Block.stoneSingleSlab, 1, 3 ), // metadata 3 is cobble slab
    		new ItemStack( Block.stoneSingleSlab, 1, 3 )
		} );

		AddShapelessRecipe( new ItemStack( Block.brick ), new Object[] {	    		
    		new ItemStack( Block.stoneSingleSlab, 1, 4 ), // metadata 4 is brick slab
    		new ItemStack( Block.stoneSingleSlab, 1, 4 )
		} );
		
		//Legacy support
		AddShapelessRecipe( new ItemStack( Block.stoneBrick ), new Object[] {	    		
    		new ItemStack( Block.stoneSingleSlab, 1, 5 ), // metadata 5 is stone brick slab
    		new ItemStack( Block.stoneSingleSlab, 1, 5 )
		} );
		
		AddShapelessRecipe( new ItemStack( Block.netherBrick ), new Object[] {	    		
    		new ItemStack( Block.stoneSingleSlab, 1, 6 ), // metadata 6 is nether brick slab
    		new ItemStack( Block.stoneSingleSlab, 1, 6 )
		} );
		
		for (int strata = 0; strata < 3; strata++) {
			AddShapelessRecipe(new ItemStack(Block.cobblestone, 1, strata), new Object[] {	    		
		    		new ItemStack(FCBetterThanWolves.fcBlockCobblestoneSlabSingle, 1, strata),
		    		new ItemStack(FCBetterThanWolves.fcBlockCobblestoneSlabSingle, 1, strata)
				} );
			AddShapelessRecipe(new ItemStack(Block.stoneBrick, 1, strata << 2), new Object[] {	    		
		    		new ItemStack(FCBetterThanWolves.fcBlockStoneBrickSlabSingle, 1, strata),
		    		new ItemStack(FCBetterThanWolves.fcBlockStoneBrickSlabSingle, 1, strata)
				} );
		}
		
		for ( int iTempWoodType = 0; iTempWoodType < 5; iTempWoodType++ )
		{
			AddShapelessRecipe( new ItemStack( Block.planks, 1, iTempWoodType ), new Object[] {	    		
	    		new ItemStack( Block.woodSingleSlab, 1, iTempWoodType ),
	    		new ItemStack( Block.woodSingleSlab, 1, iTempWoodType )
			} );			
		}
		
		AddShapelessRecipe( new ItemStack( Item.clay, 8 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcUnfiredPottery, 1, FCBlockUnfiredPottery.m_iSubtypeCrucible )
		} );
		
		AddShapelessRecipe( new ItemStack( Item.clay, 6 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcUnfiredPottery, 1, FCBlockUnfiredPottery.m_iSubtypePlanter )
		} );
		
		AddShapelessRecipe( new ItemStack( Item.clay, 4 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcUnfiredPottery, 1, FCBlockUnfiredPottery.m_iSubtypeVase )
		} );
		
		AddShapelessRecipe( new ItemStack( Item.clay, 2 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcUnfiredPottery, 1, FCBlockUnfiredPottery.m_iSubtypeUrn )
		} );
		
		AddShapelessRecipe( new ItemStack( Item.clay, 1 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcUnfiredPottery, 1, FCBlockUnfiredPottery.m_iSubtypeMould )
		} );
		
		AddRecipe( new ItemStack( Item.ingotIron, 1 ), new Object[] {
			"###", 
			"###", 
			"###", 
			'#', new ItemStack( FCBetterThanWolves.fcItemNuggetIron ),
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 9 ), new Object[] {
			new ItemStack( Item.ingotIron ) 
		} );
		
		AddRecipe( new ItemStack( FCBetterThanWolves.fcItemSteel, 1 ), new Object[] {
			"###", 
			"###", 
			"###", 
			'#', new ItemStack( FCBetterThanWolves.fcItemNuggetSteel ),
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemNuggetSteel, 9 ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemSteel ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemPileSoulSand, 4 ), new Object[] {
			new ItemStack( Block.slowSand, 1 )
		} );
		
		AddShapelessRecipe( new ItemStack( Block.slowSand, 1 ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemPileSoulSand ), 
			new ItemStack( FCBetterThanWolves.fcItemPileSoulSand ), 
			new ItemStack( FCBetterThanWolves.fcItemPileSoulSand ), 
			new ItemStack( FCBetterThanWolves.fcItemPileSoulSand )
		} );		
		
		AddShapelessRecipe( new ItemStack( Item.book, 3 ), new Object[] {
			new ItemStack( Block.bookShelf )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemWheatSeeds, 2 ), new Object[] {
			new ItemStack( FCBetterThanWolves.fcItemWheat )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemWheatSeeds ), new Object[] {
			new ItemStack( Item.seeds )
		} );
	}

	private static void AddSmeltingRecipes()
	{
		// food recipes (default cook times)
		
		FurnaceRecipes.smelting().addSmelting( FCBetterThanWolves.fcItemWolfRaw.itemID, new ItemStack( FCBetterThanWolves.fcItemWolfCooked ), 0 );
		
		FurnaceRecipes.smelting().addSmelting( FCBetterThanWolves.fcItemBreadDough.itemID, new ItemStack( Item.bread ), 0 );
		
		FurnaceRecipes.smelting().addSmelting( FCBetterThanWolves.fcItemRawEgg.itemID, new ItemStack( FCBetterThanWolves.fcItemFriedEgg ), 0 );
		
		FurnaceRecipes.smelting().addSmelting( FCBetterThanWolves.fcItemMuttonRaw.itemID, new ItemStack( FCBetterThanWolves.fcItemMuttonCooked ), 0 );
		
		FurnaceRecipes.smelting().addSmelting( FCBetterThanWolves.fcItemCarrot.itemID, new ItemStack( FCBetterThanWolves.fcItemCookedCarrot ), 0 );
		
		FurnaceRecipes.smelting().addSmelting( FCBetterThanWolves.fcItemRawKebab.itemID, new ItemStack( FCBetterThanWolves.fcItemCookedKebab ), 0 );
		
		FurnaceRecipes.smelting().addSmelting( FCBetterThanWolves.fcItemRawMysteryMeat.itemID, new ItemStack( FCBetterThanWolves.fcItemCookedMysteryMeat ), 0 );
		
		FurnaceRecipes.smelting().addSmelting( FCBetterThanWolves.fcItemRawScrambledEggs.itemID, new ItemStack( FCBetterThanWolves.fcItemCookedScrambledEggs ), 0 );
		
		FurnaceRecipes.smelting().addSmelting( FCBetterThanWolves.fcItemRawMushroomOmelet.itemID, new ItemStack( FCBetterThanWolves.fcItemCookedMushroomOmelet ), 0 );
		
		FurnaceRecipes.smelting().addSmelting( FCBetterThanWolves.fcItemPastryUncookedCake.itemID, new ItemStack( Item.cake ), 0 );
		FurnaceRecipes.smelting().addSmelting( FCBetterThanWolves.fcItemPastryUncookedCookies.itemID, new ItemStack( Item.cookie, 8 ), 0 );
		FurnaceRecipes.smelting().addSmelting( FCBetterThanWolves.fcItemPastryUncookedPumpkinPie.itemID, new ItemStack( Item.pumpkinPie ), 0 );
		
		FurnaceRecipes.smelting().addSmelting( FCBetterThanWolves.fcItemBeastLiverRaw.itemID, new ItemStack( FCBetterThanWolves.fcItemBeastLiverCooked ), 0 );
		
		// non-food (custom cook times)
		
		FurnaceRecipes.smelting().addSmelting( FCBetterThanWolves.fcItemNetherBrickUnfired.itemID, new ItemStack( FCBetterThanWolves.fcItemNetherBrick ), 0, 2 );
		
		FurnaceRecipes.smelting().addSmelting( FCBetterThanWolves.fcItemBrickUnfired.itemID, new ItemStack( Item.brick ), 0, 2 );
		
		FurnaceRecipes.smelting().addSmelting( FCBetterThanWolves.fcItemChunkIronOre.itemID, new ItemStack( FCBetterThanWolves.fcItemNuggetIron ), 0, 3 );
		
		FurnaceRecipes.smelting().addSmelting( FCBetterThanWolves.fcItemChunkGoldOre.itemID, new ItemStack( Item.goldNugget ), 0, 3 );
	}
	
	private static void AddCampfireRecipes()
	{
		AddCampfireRecipe( FCBetterThanWolves.fcItemWolfRaw.itemID, new ItemStack( FCBetterThanWolves.fcItemWolfCooked ) );		
		AddCampfireRecipe( FCBetterThanWolves.fcItemMuttonRaw.itemID, new ItemStack( FCBetterThanWolves.fcItemMuttonCooked ) );		
		AddCampfireRecipe( FCBetterThanWolves.fcItemRawMysteryMeat.itemID, new ItemStack( FCBetterThanWolves.fcItemCookedMysteryMeat ) );		
		AddCampfireRecipe( FCBetterThanWolves.fcItemBeastLiverRaw.itemID, new ItemStack( FCBetterThanWolves.fcItemBeastLiverCooked ) );
		
		AddCampfireRecipe( Item.porkRaw.itemID, new ItemStack( Item.porkCooked ) );
		AddCampfireRecipe( Item.beefRaw.itemID, new ItemStack( Item.beefCooked ) );
		AddCampfireRecipe( Item.chickenRaw.itemID, new ItemStack( Item.chickenCooked ) );
		AddCampfireRecipe( Item.fishRaw.itemID, new ItemStack( Item.fishCooked ) );
	}
	
	private static void AddAnvilRecipes()
	{
		AddAnvilRecipe( new ItemStack( FCBetterThanWolves.fcItemRefinedSword, 1 ), new Object[] {
    		"#", 
    		"#", 
    		"#", 
    		"X", 
    		'#', FCBetterThanWolves.fcItemSteel, 
    		'X', FCBetterThanWolves.fcItemHaft 
		} );
		
		AddAnvilRecipe( new ItemStack( FCBetterThanWolves.fcItemRefinedShovel, 1 ), new Object[] {
    		"#", 
    		"X", 
    		"X", 
    		"X", 
    		'#', FCBetterThanWolves.fcItemSteel, 
    		'X', FCBetterThanWolves.fcItemHaft 
		} );
		
		AddAnvilRecipe( new ItemStack( FCBetterThanWolves.fcItemRefinedPickAxe, 1 ), new Object[] {
    		"###", 
    		" X ", 
    		" X ", 
    		" X ", 
    		'#', FCBetterThanWolves.fcItemSteel, 
    		'X', FCBetterThanWolves.fcItemHaft 
		} );
		
		AddAnvilRecipe( new ItemStack( FCBetterThanWolves.fcItemMattock, 1 ), new Object[] {
    		" ###", 
    		"# X ", 
    		"  X ", 
    		"  X ", 
    		'#', FCBetterThanWolves.fcItemSteel, 
    		'X', FCBetterThanWolves.fcItemHaft 
		} );
		
		AddAnvilRecipe( new ItemStack( FCBetterThanWolves.fcItemRefinedHoe, 1 ), new Object[] {
    		"##", 
    		" X", 
    		" X", 
    		" X", 
    		'#', FCBetterThanWolves.fcItemSteel, 
    		'X', FCBetterThanWolves.fcItemHaft 
		} );
		
		AddAnvilRecipe( new ItemStack( FCBetterThanWolves.fcItemBattleAxe, 1 ), new Object[] {
    		"###", 
    		"#X#", 
    		" X ", 
    		" X ", 
    		'#', FCBetterThanWolves.fcItemSteel, 
    		'X', FCBetterThanWolves.fcItemHaft 
		} );
		
		AddAnvilRecipe( new ItemStack( FCBetterThanWolves.fcItemRefinedAxe, 1 ), new Object[] {
    		"# ", 
    		"#X", 
    		" X", 
    		" X", 
    		'#', FCBetterThanWolves.fcItemSteel, 
    		'X', FCBetterThanWolves.fcItemHaft 
		} );
		
		AddAnvilRecipe( new ItemStack( FCBetterThanWolves.fcItemPlateHelm, 1 ), new Object[] {
    		"####", 
    		"#  #", 
    		"#  #", 
    		" XX ", 
    		'#', FCBetterThanWolves.fcItemSteel, 
    		'X', FCBetterThanWolves.fcItemArmorPlate 
		} );
		
		AddAnvilRecipe( new ItemStack( FCBetterThanWolves.fcItemPlateBreastPlate, 1 ), new Object[] {
    		"X  X", 
    		"####", 
    		"####", 
    		"####", 
    		'#', FCBetterThanWolves.fcItemSteel, 
    		'X', FCBetterThanWolves.fcItemArmorPlate 
		} );
		
		AddAnvilRecipe( new ItemStack( FCBetterThanWolves.fcItemPlateLeggings, 1 ), new Object[] {
    		"####", 
    		"X##X", 
    		"X  X", 
    		"X  X", 
    		'#', FCBetterThanWolves.fcItemSteel, 
    		'X', FCBetterThanWolves.fcItemArmorPlate 
		} );
		
		AddAnvilRecipe( new ItemStack( FCBetterThanWolves.fcItemPlateBoots, 1 ), new Object[] {
    		" ## ", 
    		" ## ", 
    		"#XX#", 
    		'#', FCBetterThanWolves.fcItemSteel, 
    		'X', FCBetterThanWolves.fcItemArmorPlate 
		} );
		
		AddAnvilRecipe( new ItemStack( FCBetterThanWolves.fcItemBroadheadArrowhead, 2 ), new Object[] {
    		" # ", 
    		" # ", 
    		"###", 
    		" # ", 
    		'#', FCBetterThanWolves.fcItemNuggetSteel, 
		} );
		
		AddAnvilRecipe( new ItemStack( FCBetterThanWolves.fcBlockPressurePlateSoulforgedSteel, 2 ), new Object[] {
            "####", 
            " rr ", 
            Character.valueOf('#'), FCBetterThanWolves.fcItemSteel,
            Character.valueOf('r'), Item.redstone
        } );		
		
		AddAnvilRecipe( new ItemStack( FCBetterThanWolves.fcBlockDetector ), new Object[] {
    		"####", 
    		"XTTX", 
    		"#YY#", 
    		"#YY#", 
    		'#', new ItemStack(FCBetterThanWolves.fcItemStoneBrick, 1, ignoreMetadata), 
    		'X', FCBetterThanWolves.fcItemPolishedLapis, 
    		'Y', Item.redstone, 
    		'T', Block.torchRedstoneActive 
		} );

		AddAnvilRecipe( new ItemStack( FCBetterThanWolves.fcBlockDispenser ), new Object[] {
    		"####", 
    		"#ZZ#", 
    		"YTTY", 
    		"YXXY", 
    		'#', new ItemStack(Block.cobblestoneMossy, 1, ignoreMetadata), 
    		'X', Item.redstone,
    		'Y', new ItemStack(FCBetterThanWolves.fcItemStoneBrick, 1, ignoreMetadata), 
    		'Z', FCBetterThanWolves.fcItemSoulUrn,
    		'T', Block.torchRedstoneActive 
		} );
		
		AddAnvilRecipe( new ItemStack( FCBetterThanWolves.fcBuddyBlock, 1 ), new Object[] {
        	"##X#", 
        	"XYY#", 
        	"#YYX", 
        	"#X##", 
        	'#', new ItemStack(FCBetterThanWolves.fcItemStoneBrick, 1, ignoreMetadata), 
        	'X', FCBetterThanWolves.fcItemPolishedLapis, 
    		'Y', Block.torchRedstoneActive 
        } );
		
		AddAnvilRecipe( new ItemStack( FCBetterThanWolves.fcSoulforgedSteelBlock, 1 ), new Object[] {
    		"####", 
    		"####", 
    		"####", 
    		"####", 
    		'#', FCBetterThanWolves.fcItemSteel
		} );
		
		AddAnvilRecipe( new ItemStack( FCBetterThanWolves.fcItemTuningFork, 6 ), new Object[] {
    		"# #", 
    		"# #", 
    		" # ", 
    		" # ", 
    		'#', FCBetterThanWolves.fcItemSteel
		} );
		
		AddAnvilRecipe( new ItemStack( FCBetterThanWolves.fcItemCanvas ), new Object[] {
    		"MMMM", 
    		"MFFM", 
    		"MFFM", 
    		"MMMM", 
    		'F', FCBetterThanWolves.fcItemHempCloth, 
    		'M', new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, ignoreMetadata )
		} );
		
		AddAnvilRecipe( new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 1, 
			FCBlockAestheticOpaque.m_iSubtypeChoppingBlockClean ), new Object[] {
    		"#  #", 
    		"#  #", 
    		"####", 
    		'#', new ItemStack(FCBetterThanWolves.fcItemStoneBrick, 1, ignoreMetadata)
		} );

		AddAnvilRecipe( new ItemStack( FCBetterThanWolves.fcItemMail, 2 ), new Object[] {
    		"# # ", 
    		" # #", 
    		"# # ", 
    		" # #", 
    		'#', FCBetterThanWolves.fcItemNuggetIron
		} );
		
		AddAnvilRecipe( new ItemStack( FCBetterThanWolves.fcItemMail, 2 ), new Object[] {
    		" # #", 
    		"# # ", 
    		" # #", 
    		"# # ", 
    		'#', FCBetterThanWolves.fcItemNuggetIron
		} );
		
		AddAnvilRecipe( new ItemStack( FCBetterThanWolves.fcBlockSoulforgeDormant ), new Object[] {
    		"####", 
    		" #  ", 
    		" #  ", 
    		"####", 
    		'#', Item.ingotGold
		} );		
	}
	
    private static void AddCauldronRecipes()
	{
        AddCauldronRecipe( 
    		new ItemStack( Item.gunpowder, 2 ), 
    		new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemBrimstone ), 
				new ItemStack( FCBetterThanWolves.fcItemNitre ), 
				new ItemStack( FCBetterThanWolves.fcItemCoalDust ) 
		} );
    
        AddCauldronRecipe( 
    		new ItemStack( FCBetterThanWolves.fcItemFilament, 1 ), 
			new ItemStack[] {
    			new ItemStack( Item.lightStoneDust ), 
    			new ItemStack( Item.redstone ), 
    			new ItemStack( Item.silk )
        } );
    
        AddCauldronRecipe( 
    		new ItemStack( FCBetterThanWolves.fcItemFilament, 1 ), 
			new ItemStack[] {
    			new ItemStack( Item.lightStoneDust ), 
    			new ItemStack( Item.redstone ), 
    			new ItemStack( FCBetterThanWolves.fcItemHempFibers )
        } );
    
        AddCauldronRecipe( 
    		new ItemStack( FCBetterThanWolves.fcItemElement, 1 ), 
			new ItemStack[] {
    			new ItemStack( Item.blazePowder ), 
    			new ItemStack( Item.redstone ), 
    			new ItemStack( Item.silk )
        } );
    
        AddCauldronRecipe( 
    		new ItemStack( FCBetterThanWolves.fcItemElement, 1 ), 
			new ItemStack[] {
    			new ItemStack( Item.blazePowder ), 
    			new ItemStack( Item.redstone ), 
    			new ItemStack( FCBetterThanWolves.fcItemHempFibers )
        } );
    
        AddCauldronRecipe( 
    		new ItemStack( FCBetterThanWolves.fcItemFuse, 2 ), 
			new ItemStack[] {
    			new ItemStack( Item.gunpowder ), 
    			new ItemStack( Item.silk )
        } );
    
        AddCauldronRecipe( 
    		new ItemStack( FCBetterThanWolves.fcItemFuse, 2 ), 
			new ItemStack[] {
    			new ItemStack( Item.gunpowder ), 
    			new ItemStack( FCBetterThanWolves.fcItemHempFibers )
        } );
    
        AddCauldronRecipe( 
    		new ItemStack( FCBetterThanWolves.fcItemBlastingOil, 2 ), 
			new ItemStack[] {
    			new ItemStack( FCBetterThanWolves.fcItemHellfireDust ), 
    			new ItemStack( FCBetterThanWolves.fcItemTallow )
        } );
        
        AddCauldronRecipe( 
    		new ItemStack( FCBetterThanWolves.fcItemNetherSludge, 8 ), 
			new ItemStack[] {
    			new ItemStack( FCBetterThanWolves.fcItemPotash, 1, ignoreMetadata ), 
    			new ItemStack( FCBetterThanWolves.fcItemHellfireDust, 8, ignoreMetadata ) 
        } );

    	AddCauldronRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemNethercoal, 1 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemHellfireDust, 1 ),
				new ItemStack( FCBetterThanWolves.fcItemCoalDust, 1 )
		} );
        	
    	AddCauldronRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemConcentratedHellfire, 1 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemHellfireDust, 8 )
		} );
    	
    	AddCauldronRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemTannedLeather, 1 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemDung, 1 ),
				new ItemStack( FCBetterThanWolves.fcItemScouredLeather, 1 ),
				new ItemStack( FCBetterThanWolves.fcItemBark, 5, 0 ) // oak
		} );
    	
    	AddCauldronRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemTannedLeather, 1 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemDung, 1 ),
				new ItemStack( FCBetterThanWolves.fcItemScouredLeather, 1 ),
				new ItemStack( FCBetterThanWolves.fcItemBark, 3, 1 ) // spruce
		} );
    	
    	AddCauldronRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemTannedLeather, 1 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemDung, 1 ),
				new ItemStack( FCBetterThanWolves.fcItemScouredLeather, 1 ),
				new ItemStack( FCBetterThanWolves.fcItemBark, 8, 2 ) // birch
		} );
    	
    	AddCauldronRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemTannedLeather, 1 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemDung, 1 ),
				new ItemStack( FCBetterThanWolves.fcItemScouredLeather, 1 ),
				new ItemStack( FCBetterThanWolves.fcItemBark, 2, 3 ) // jungle
		} );
    	
    	AddCauldronRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemTannedLeather, 1 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemDung, 1 ),
				new ItemStack( FCBetterThanWolves.fcItemScouredLeather, 1 ),
				new ItemStack( FCBetterThanWolves.fcItemBark, 8, 4 ) // blood wood
		} );
    	
    	AddCauldronRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemTannedLeatherCut, 2 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemDung, 1 ),
				new ItemStack( FCBetterThanWolves.fcItemScouredLeatherCut, 2 ),
				new ItemStack( FCBetterThanWolves.fcItemBark, 5, 0 ) // oak
		} );
    	
    	AddCauldronRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemTannedLeatherCut, 2 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemDung, 1 ),
				new ItemStack( FCBetterThanWolves.fcItemScouredLeatherCut, 2 ),
				new ItemStack( FCBetterThanWolves.fcItemBark, 3, 1 ) // spruce
		} );
    	
    	AddCauldronRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemTannedLeatherCut, 2 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemDung, 1 ),
				new ItemStack( FCBetterThanWolves.fcItemScouredLeatherCut, 2 ),
				new ItemStack( FCBetterThanWolves.fcItemBark, 8, 2 ) // birch
		} );
    	
    	AddCauldronRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemTannedLeatherCut, 2 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemDung, 1 ),
				new ItemStack( FCBetterThanWolves.fcItemScouredLeatherCut, 2 ),
				new ItemStack( FCBetterThanWolves.fcItemBark, 2, 3 ) // jungle
		} );
    	
    	AddCauldronRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemTannedLeatherCut, 2 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemDung, 1 ),
				new ItemStack( FCBetterThanWolves.fcItemScouredLeatherCut, 2 ),
				new ItemStack( FCBetterThanWolves.fcItemBark, 8, 4 ) // blood wood
		} );
    	
    	AddCauldronRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemDonut, 2 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemFlour, 1 ),
				new ItemStack( Item.sugar, 1 )
		} );
    	
    	// only up to 14 to avoid white on white conversion
    	
        for ( int i = 0; i < 15; i++ )
        {
        	AddCauldronRecipe( 
    			new ItemStack( Block.cloth, 8, BlockCloth.getBlockFromDye( i ) ), 
    			new ItemStack[] {
                    new ItemStack( Item.dyePowder, 1, i ), 
        			new ItemStack( Block.cloth, 8, 0 ), 
			} );
        	
        	AddCauldronRecipe( 
    			new ItemStack( FCBetterThanWolves.fcWoolSlab, 16, BlockCloth.getBlockFromDye( i ) ), 
    			new ItemStack[] {
                    new ItemStack( Item.dyePowder, 1, i ), 
        			new ItemStack( FCBetterThanWolves.fcWoolSlab, 16, 0 ), 
			} );
        	
        	AddCauldronRecipe( 
    			new ItemStack( FCBetterThanWolves.fcItemWool, 32, i ), 
    			new ItemStack[] {
                    new ItemStack( Item.dyePowder, 1, i ), 
        			new ItemStack( FCBetterThanWolves.fcItemWool, 32, 15 ), 
			} );        	
        }
		
        AddCauldronRecipe( 
    		new ItemStack( Block.cloth, 8, 12 ), 
			new ItemStack[] {
    			new ItemStack( FCBetterThanWolves.fcItemDung, 1 ), 
    			new ItemStack( Item.itemsList[Block.cloth.blockID], 8, 0 )
        } );
        
        AddCauldronRecipe( 
    		new ItemStack( FCBetterThanWolves.fcWoolSlab, 16, 12 ), 
			new ItemStack[] {
    			new ItemStack( FCBetterThanWolves.fcItemDung, 1 ), 
    			new ItemStack( Item.itemsList[FCBetterThanWolves.fcWoolSlab.blockID], 16, 0 )
        } );
        
    	AddCauldronRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemWool, 32, 3 ), 
			new ItemStack[] {
    			new ItemStack( FCBetterThanWolves.fcItemDung, 1 ), 
    			new ItemStack( FCBetterThanWolves.fcItemWool, 32, 15 ), 
		} );
    	
        AddCauldronRecipe( new ItemStack( FCBetterThanWolves.fcAestheticVegetation, 1, FCBlockAestheticVegetation.m_iSubtypeBloodWoodSapling ),
			new ItemStack[] {
				new ItemStack( Block.sapling, 1, 0 ),
				new ItemStack( Block.sapling, 1, 1 ),
				new ItemStack( Block.sapling, 1, 2 ),
				new ItemStack( Block.sapling, 1, 3 ),
				new ItemStack( FCBetterThanWolves.fcItemSoulUrn, 8 ),
				new ItemStack( Item.netherStalkSeeds )
		} );
		
        AddCauldronRecipe( 
			new ItemStack[] {
        		new ItemStack( FCBetterThanWolves.fcBlockDirtLoose ),
        		new ItemStack( FCBetterThanWolves.fcItemBloodMossSpores ),
			},
			new ItemStack[] {
				new ItemStack( Block.mycelium ),
				new ItemStack( FCBetterThanWolves.fcItemMushroomBrown ),
				new ItemStack( FCBetterThanWolves.fcItemMushroomRed ),
				new ItemStack( FCBetterThanWolves.fcItemSoulUrn, 8 ),
				new ItemStack( FCBetterThanWolves.fcItemDung ),
				new ItemStack( Item.netherStalkSeeds )
		} );

        // bleeching
        
    	FCCraftingManagerCauldron.getInstance().AddRecipe( 
    		new ItemStack( Block.cloth, 8, 0 ), 
			new ItemStack[] {
    			new ItemStack( FCBetterThanWolves.fcItemPotash, 1, ignoreMetadata ), 
    			new ItemStack( Block.cloth, 8, 0  ),
    		}, true );    	
            
    	FCCraftingManagerCauldron.getInstance().AddRecipe( 
    		new ItemStack( FCBetterThanWolves.fcWoolSlab, 16, 0 ), 
			new ItemStack[] {
    			new ItemStack( FCBetterThanWolves.fcItemPotash, 1, ignoreMetadata ), 
    			new ItemStack( FCBetterThanWolves.fcWoolSlab, 16, 0 ),
    		}, true );
    	
    	FCCraftingManagerCauldron.getInstance().AddRecipe( 
    		new ItemStack( FCBetterThanWolves.fcItemWool, 32, 15 ), 
			new ItemStack[] {
    			new ItemStack( FCBetterThanWolves.fcItemPotash, 1, ignoreMetadata ), 
    			new ItemStack( FCBetterThanWolves.fcItemWool, 32, 15 ),
    		}, true );

        AddCauldronRecipe( 
    		new ItemStack( Item.dyePowder, 1, 2 ), 
			new ItemStack[] {
    			new ItemStack( Block.cactus ) 
        } );
        
        AddCauldronRecipe( 
    		new ItemStack( FCBetterThanWolves.fcItemBucketCement, 1 ), 
    		new ItemStack[] {
				new ItemStack( Block.sand ), 
				new ItemStack( Block.gravel ), 
				new ItemStack( Item.bucketEmpty ), 
				new ItemStack( FCBetterThanWolves.fcItemSoulUrn ) 
		} );
        
        AddCauldronRecipe( 
    		new ItemStack( FCBetterThanWolves.fcItemHardBoiledEgg ), 
    		new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemRawEgg ) 
		} );
        
        AddCauldronRecipe( 
    		new ItemStack( Block.pistonBase, 4 ), 
    		new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemSoap ), 
				new ItemStack( Block.pistonStickyBase, 4 ) 
		} );
        
        AddCauldronRecipe( 
    		new ItemStack( FCBetterThanWolves.fcItemBoiledPotato ), 
    		new ItemStack[] {
				new ItemStack( Item.potato ) 
		} );
    
        AddCauldronRecipe( 
    		new ItemStack( FCBetterThanWolves.fcItemBoiledPotato ), 
    		new ItemStack[] {
				new ItemStack( Item.bakedPotato ) 
		} );
    
        AddCauldronRecipe( 
    		new ItemStack( FCBetterThanWolves.fcItemCookedCarrot ), 
    		new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemCarrot ) 
		} );
        
        AddCauldronRecipe( 
        	new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 4, FCBlockAestheticOpaque.m_iSubtypeChoppingBlockClean ), 
    		new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemSoap ), 
				new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 4, FCBlockAestheticOpaque.m_iSubtypeChoppingBlockDirty ) 
		} );
    
        AddCauldronRecipe( 
    		new ItemStack[] { 
    			new ItemStack( FCBetterThanWolves.fcItemFishSoup, 2 ),    			
    			new ItemStack( Item.bucketEmpty )
    		},
    		 
    		new ItemStack[] {
				new ItemStack( Item.bucketMilk ), 
				new ItemStack( Item.fishCooked ), 
				new ItemStack( Item.bowlEmpty, 2 )
		} );
    
        AddCauldronRecipe( 
    		new ItemStack( FCBetterThanWolves.fcItemChickenSoup, 3 ), 
    		new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemBoiledPotato ), 
				new ItemStack( FCBetterThanWolves.fcItemCookedCarrot ), 
				new ItemStack( Item.chickenCooked ), 
				new ItemStack( Item.bowlEmpty, 3 )
		} );
    
        AddCauldronRecipe( 
    		new ItemStack( FCBetterThanWolves.fcItemHeartyStew, 5 ), 
    		new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemBoiledPotato ), 
				new ItemStack( FCBetterThanWolves.fcItemCookedCarrot ), 
				new ItemStack( FCBetterThanWolves.fcItemMushroomBrown, 3 ),
				new ItemStack( FCBetterThanWolves.fcItemFlour ), 
				new ItemStack( Item.beefCooked ), 
				new ItemStack( Item.bowlEmpty, 5 )
		} );
    
        AddCauldronRecipe( 
    		new ItemStack( FCBetterThanWolves.fcItemHeartyStew, 5 ), 
    		new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemBoiledPotato ), 
				new ItemStack( FCBetterThanWolves.fcItemCookedCarrot ), 
				new ItemStack( FCBetterThanWolves.fcItemMushroomBrown, 3 ),
				new ItemStack( FCBetterThanWolves.fcItemFlour ), 
				new ItemStack( Item.porkCooked ), 
				new ItemStack( Item.bowlEmpty, 5 )
		} );
    
        AddCauldronRecipe( 
    		new ItemStack( FCBetterThanWolves.fcItemHeartyStew, 5 ), 
    		new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemBoiledPotato ), 
				new ItemStack( FCBetterThanWolves.fcItemCookedCarrot ), 
				new ItemStack( FCBetterThanWolves.fcItemMushroomBrown, 3 ),
				new ItemStack( FCBetterThanWolves.fcItemFlour ), 
				new ItemStack( FCBetterThanWolves.fcItemMuttonCooked ), 
				new ItemStack( Item.bowlEmpty, 5 )
		} );
    
        AddCauldronRecipe( 
    		new ItemStack( FCBetterThanWolves.fcItemHeartyStew, 5 ), 
    		new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemBoiledPotato ), 
				new ItemStack( FCBetterThanWolves.fcItemCookedCarrot ), 
				new ItemStack( FCBetterThanWolves.fcItemMushroomBrown, 3 ),
				new ItemStack( FCBetterThanWolves.fcItemFlour ), 
				new ItemStack( FCBetterThanWolves.fcItemWolfCooked ), 
				new ItemStack( Item.bowlEmpty, 5 )
		} );
        
        AddCauldronRecipe( 
    		new ItemStack( FCBetterThanWolves.fcItemHeartyStew, 5 ), 
    		new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemBoiledPotato ), 
				new ItemStack( FCBetterThanWolves.fcItemCookedCarrot ), 
				new ItemStack( FCBetterThanWolves.fcItemMushroomBrown, 3 ),
				new ItemStack( FCBetterThanWolves.fcItemFlour ), 
				new ItemStack( FCBetterThanWolves.fcItemCookedMysteryMeat ),
				new ItemStack( Item.bowlEmpty, 5 )
		} );                
    
        AddCauldronRecipe( 
    		new ItemStack[] { 
    			new ItemStack( Item.bowlSoup, 2 ),
    			new ItemStack( Item.bucketEmpty )
    		},
    		new ItemStack[] {
    			new ItemStack( FCBetterThanWolves.fcItemMushroomBrown, 3 ),
				new ItemStack( Item.bucketMilk ), 
				new ItemStack( Item.bowlEmpty, 2 )
		} );
    
        AddCauldronRecipe( 
    		new ItemStack[] { 
    			new ItemStack( FCBetterThanWolves.fcItemChocolate, 2 ),    			
    			new ItemStack( Item.bucketEmpty )
    		},    		 
    		new ItemStack[] {
				new ItemStack( Item.dyePowder, 1, 3 ), 
				new ItemStack( Item.sugar ), 
				new ItemStack( Item.bucketMilk )
		} );
    
        // Stoked Cauldron Recipes

        // glue recipes
        
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
			new ItemStack[] {
				new ItemStack( Item.leather, 1 ) 
		} );
        
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemTannedLeather, 1 ) 
		} );
            
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemScouredLeather, 1 ) 
		} );
        
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemScouredLeatherCut, 2 ) 
		} );
        
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemLeatherCut, 2 ) 
		} );
        
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemTannedLeatherCut, 2 ) 
		} );
        
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemBelt, 2 ) 
		} );

        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemStrap, 8 ) 
		} );
		
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemGlue, 2 ), 
			new ItemStack[] {
				new ItemStack( Item.helmetLeather, 1, ignoreMetadata ) 
		} );
        
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemGlue, 4 ), 
			new ItemStack[] {
				new ItemStack( Item.plateLeather, 1, ignoreMetadata ) 
		} );
        
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemGlue, 3 ), 
			new ItemStack[] {
				new ItemStack( Item.legsLeather, 1, ignoreMetadata ) 
		} );
        
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemGlue, 2 ), 
			new ItemStack[] {
				new ItemStack( Item.bootsLeather, 1, ignoreMetadata ) 
		} );
        
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemGlue, 2 ), 
			new ItemStack[] {
				new ItemStack( Item.saddle, 1 ) 
		} );
        
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemGlue, 2 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemArmorGimpHelm, 1, ignoreMetadata ) 
		} );
        
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemGlue, 3 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemArmorGimpChest, 1, ignoreMetadata ) 
		} );
        
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemGlue, 3 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemArmorGimpLeggings, 1, ignoreMetadata ) 
		} );
        
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemArmorGimpBoots, 1, ignoreMetadata ) 
		} );
        
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemGlue, 3 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemBreedingHarness, 1, ignoreMetadata ) 
		} );
        
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
			new ItemStack[] {
				new ItemStack( Item.book, 2, ignoreMetadata ) 
		} );
    
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
			new ItemStack[] {
				new ItemStack( Item.writableBook, 2, ignoreMetadata ) 
		} );
    
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
			new ItemStack[] {
				new ItemStack( Item.writtenBook, 2, ignoreMetadata ) 
		} );
    
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
			new ItemStack[] {
				new ItemStack( Item.enchantedBook, 2, ignoreMetadata ) 
		} );
    
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemGlue, 2 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemArmorTannedHelm, 1, ignoreMetadata ) 
		} );
        
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemGlue, 4 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemArmorTannedChest, 1, ignoreMetadata ) 
		} );
        
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemGlue, 3 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemArmorTannedLeggings, 1, ignoreMetadata ) 
		} );
        
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemGlue, 2 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemArmorTannedBoots, 1, ignoreMetadata ) 
		} );
        
        // tallow recipes
        
        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemTallow, 1 ), 
				new ItemStack[] {
					new ItemStack( Item.porkCooked, 1 ) 
			} );
                
        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemTallow, 1 ), 
				new ItemStack[] {
					new ItemStack( Item.porkRaw, 1 ) 
			} );
                
        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemTallow, 1 ), 
				new ItemStack[] {
					new ItemStack( FCBetterThanWolves.fcItemWolfCooked, 8 ) 
			} );
                
        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemTallow, 1 ), 
				new ItemStack[] {
					new ItemStack( FCBetterThanWolves.fcItemWolfRaw, 8 ) 
			} );
        
        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemTallow, 1 ), 
				new ItemStack[] {
					new ItemStack( Item.beefCooked, 4 ) 
			} );
        
        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemTallow, 1 ), 
				new ItemStack[] {
					new ItemStack( Item.beefRaw, 4 ) 
			} );
        
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemTallow, 1 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemMuttonCooked, 4 ) 
		} );
    
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemTallow, 1 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemMuttonRaw, 4 ) 
		} );
    
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemTallow, 1 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemRawMysteryMeat, 2 ) 
		} );
        
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemTallow, 1 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemCookedMysteryMeat, 2 ) 
		} );
        
        // Potash Recipes
        
        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemPotash, 1 ), 
				new ItemStack[] {
					new ItemStack( Block.wood, 1, ignoreMetadata ) 
			} );
        
        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemPotash, 1 ), 
				new ItemStack[] {
					new ItemStack( FCBetterThanWolves.fcBloodWood, 1, ignoreMetadata ) 
			} );
        
        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemPotash, 1 ), 
				new ItemStack[] {
					new ItemStack( Block.planks, 6, ignoreMetadata ) 
			} );
        
        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemPotash, 1 ), 
				new ItemStack[] {
					new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 12, ignoreMetadata ),
			} );

        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemPotash, 1 ), 
				new ItemStack[] {
					new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 24, ignoreMetadata ),
			} );

        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemPotash, 1 ), 
				new ItemStack[] {
					new ItemStack( FCBetterThanWolves.fcBlockWoodCornerItemStubID, 48, ignoreMetadata ),
			} );

        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemPotash, 1 ), 
				new ItemStack[] {
					new ItemStack( FCBetterThanWolves.fcItemSawDust, 16 )
			} );

        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemPotash, 1 ), 
				new ItemStack[] {
					new ItemStack( FCBetterThanWolves.fcItemBark, 64, ignoreMetadata )
			} );
        
        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemPotash, 1 ), 
				new ItemStack[] {
					new ItemStack( FCBetterThanWolves.fcItemSoulDust, 16 )
			} );
        
        // misc stoked recipes
        
        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemSoap, 1 ), 
				new ItemStack[] {
					new ItemStack( FCBetterThanWolves.fcItemPotash, 1 ), 
					new ItemStack( FCBetterThanWolves.fcItemTallow, 1 ) 
			} );
        
        AddStokedCauldronRecipe(
			new ItemStack[] {
				new ItemStack( Item.silk, 2 ),
				new ItemStack( Item.stick, 2 )
			},
			new ItemStack[] {
				new ItemStack( Item.bow, 1, ignoreMetadata ) 
			} 
		);
        
        AddStokedCauldronRecipe(
			new ItemStack[] {
				new ItemStack( Item.stick, 2 ),
				new ItemStack( Item.silk, 1 ),
				new ItemStack( Item.bone, 1 )    	    		 
			},
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemCompositeBow, 1, ignoreMetadata ) 
			} 
		);
        
        AddStokedCauldronRecipe(
			new ItemStack[] {
				new ItemStack( Item.flint, 1 ),
				new ItemStack( Item.stick, 1 ),
				new ItemStack( Item.feather, 1 )
			},
			new ItemStack[] {
				new ItemStack( Item.arrow, 1, ignoreMetadata ) 
			} 
		);
                
        AddStokedCauldronRecipe(
			new ItemStack[] {
				new ItemStack( Item.flint, 1 )
			},
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemRottenArrow, 1, ignoreMetadata ) 
			} 
		);
            
        AddStokedCauldronRecipe(
			new ItemStack( FCBetterThanWolves.fcItemDogFood, 2 ), 
			new ItemStack[] {
				new ItemStack( Item.rottenFlesh, 4 ), 
				new ItemStack( Item.dyePowder, 4, 15 ), 
				new ItemStack( Item.sugar, 1 ) 
		} );
        
        AddStokedCauldronRecipe(
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemBrimstone, 1 ),
				new ItemStack( FCBetterThanWolves.fcItemSoulFlux, 1 )
			},
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemEnderSlag, 1 ) 
			} 
		);
	}
    
	private static void AddCrucibleRecipes()
	{
		// stoked recipes
		
    	// gold melting
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotGold, 3 ), 
			new ItemStack( Item.ingotGold, 2 ), 
			new ItemStack[] {
				new ItemStack( Item.pickaxeGold, 1, ignoreMetadata )
			} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotGold, 2 ), 
			new ItemStack( Item.goldNugget, 12 ), 
			new ItemStack[] {
				new ItemStack( Item.axeGold, 1, ignoreMetadata )
			} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotGold, 2 ), 
			new ItemStack( Item.goldNugget, 12 ), 
			new ItemStack[] {
				new ItemStack( Item.swordGold, 1, ignoreMetadata )
			} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotGold, 2 ), 
			new ItemStack( Item.goldNugget, 6 ), 
			new ItemStack[] {
				new ItemStack( Item.hoeGold, 1, ignoreMetadata )
			} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotGold, 1 ), 
			new ItemStack( Item.goldNugget, 6 ), 
			new ItemStack[] {
				new ItemStack( Item.shovelGold, 1, ignoreMetadata )
			} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotGold, 5 ), 
			new ItemStack( Item.goldNugget, 30 ), 
			new ItemStack[] {
				new ItemStack( Item.helmetGold, 1, ignoreMetadata )
			} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotGold, 8 ), 
			new ItemStack( Item.goldNugget, 48 ), 
			new ItemStack[] {
				new ItemStack( Item.plateGold, 1, ignoreMetadata )
			} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotGold, 7 ), 
			new ItemStack( Item.goldNugget, 42 ), 
			new ItemStack[] {
				new ItemStack( Item.legsGold, 1, ignoreMetadata )
			} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotGold, 4 ), 
			new ItemStack( Item.goldNugget, 24 ), 
			new ItemStack[] {
				new ItemStack( Item.bootsGold, 1, ignoreMetadata )
			} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.goldNugget, 4 ), 
			new ItemStack( Item.goldNugget, 3 ), 
			new ItemStack[] {
				new ItemStack( Item.pocketSundial )
			} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotGold, 1 ), 
			new ItemStack[] {
				new ItemStack( Item.goldNugget, 1 ),
				new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 2 ),
			},
			new ItemStack[] {
				new ItemStack( Block.railPowered, 2 )
		} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( Item.ingotGold, 9 ), 
			new ItemStack[] {
				new ItemStack( Block.blockGold )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.goldNugget, 8 ), 
			new ItemStack( Item.goldNugget, 5 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemOcularOfEnder )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.goldNugget, 16 ), 
			new ItemStack( Item.goldNugget, 11 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemEnderSpectacles, 1, ignoreMetadata )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.goldNugget, 8 ), 
			new ItemStack( Item.goldNugget, 6 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemGoldenDung )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.goldNugget, 3 ), 
			new ItemStack( Item.goldNugget, 2 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemRedstoneLatch )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.goldNugget, 3 ), 
			new ItemStack( Item.goldNugget, 2 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcBlockRedstoneClutch )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.goldNugget, 3 ), 
			new ItemStack( Item.goldNugget, 2 ), 
			new ItemStack[] {
				new ItemStack( Block.music )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.goldNugget, 90 ), 
			new ItemStack( Item.goldNugget, 60 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcBlockSoulforgeDormant )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.goldNugget, 11 ), 
			new ItemStack( Item.goldNugget, 8 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcBlockLightningRod )
		} );
		
		// iron melting recipes
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 4 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 2 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemMail )
			} );
			
		AddStokedCrucibleRecipe( 
			//new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 7 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 5 ), 
			new ItemStack[] {
				new ItemStack( Item.bucketEmpty )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 7 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 5 ), 
			new ItemStack[] {
				new ItemStack( Item.bucketLava )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 7 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 5 ), 
			new ItemStack[] {
				new ItemStack( Item.bucketWater )
		} );
			
		AddStokedCrucibleRecipe( 
			//new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 7 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 5 ), 
			new ItemStack[] {
				new ItemStack( Item.bucketMilk )
		} );
			
		AddStokedCrucibleRecipe( 
			//new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 7 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 5 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemBucketCement )
		} );
			
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 3 ), 
			new ItemStack( Item.ingotIron, 2 ), 
			new ItemStack[] {
				new ItemStack( Item.pickaxeIron, 1, ignoreMetadata )
		} );
				
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 2 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 12 ), 
			new ItemStack[] {
				new ItemStack( Item.axeIron, 1, ignoreMetadata )
		} );
				
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 2 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 12 ), 
			new ItemStack[] {
				new ItemStack( Item.swordIron, 1, ignoreMetadata )
		} );
					
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 2 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 6 ), 
			new ItemStack[] {
				new ItemStack( Item.hoeIron, 1, ignoreMetadata )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 1 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 6 ), 
			new ItemStack[] {
				new ItemStack( Item.shovelIron, 1, ignoreMetadata )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 5 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 30 ), 
			new ItemStack[] {
				new ItemStack( Item.helmetIron, 1, ignoreMetadata )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 8 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 48 ), 
			new ItemStack[] {
				new ItemStack( Item.plateIron, 1, ignoreMetadata )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 7 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 42 ), 
			new ItemStack[] {
				new ItemStack( Item.legsIron, 1, ignoreMetadata )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 4 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 24 ), 
			new ItemStack[] {
				new ItemStack( Item.bootsIron, 1, ignoreMetadata )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 20 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 13 ), 
			new ItemStack[] {
				new ItemStack( Item.helmetChain, 1, ignoreMetadata )
		} );
			
		AddStokedCrucibleRecipe( 
			//new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 32 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 21 ), 
			new ItemStack[] {
				new ItemStack( Item.plateChain, 1, ignoreMetadata )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 28 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 19 ), 
			new ItemStack[] {
				new ItemStack( Item.legsChain, 1, ignoreMetadata )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 16 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 11 ), 
			new ItemStack[] {
				new ItemStack( Item.bootsChain, 1, ignoreMetadata )
		} );
			
		AddStokedCrucibleRecipe( 
			//new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 4 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 3 ), 
			new ItemStack[] {
				new ItemStack( Item.compass )
		} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack[] {
				//new ItemStack( Item.ingotIron, 6 ),
				//new ItemStack( Item.goldNugget, 6 ),
				new ItemStack( Item.ingotIron, 4 ),
				new ItemStack( Item.goldNugget, 4 ),
			},
			new ItemStack[] {
				new ItemStack( Item.doorIron )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 4 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 3 ), 
			new ItemStack[] {
				new ItemStack( Item.map )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 2 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 12 ), 
			new ItemStack[] {
				new ItemStack( Item.shears, 1, ignoreMetadata )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 1 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 1 ), 
			new ItemStack[] {
				new ItemStack( Block.railDetector )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 1 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 1 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcDetectorRailWood )
		} );
		
		AddStokedCrucibleRecipe(
			new ItemStack[] {
				//new ItemStack( Item.ingotIron, 1 ), 
				new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 1 ), 
				new ItemStack( FCBetterThanWolves.fcItemNuggetSteel, 3 ), 
			},
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcBlockDetectorRailSoulforgedSteel, 1 )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 1 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 6 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcPulley )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 3 ), 
			new ItemStack( Item.ingotIron, 2 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcSaw )
		} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( Item.ingotIron, 9 ), 
			new ItemStack[] {
				new ItemStack( Block.blockIron )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 5 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 30 ), 
			new ItemStack[] {
				new ItemStack( Item.minecartEmpty )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 5 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 30 ), 
			new ItemStack[] {
				new ItemStack( Item.minecartCrate )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 5 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 30 ), 
			new ItemStack[] {
				new ItemStack( Item.minecartPowered )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 3 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 1 ), 
			new ItemStack[] {
				new ItemStack( Block.rail, 2 )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 3 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 18 ), 
			new ItemStack[] {
				new ItemStack( Block.fenceIron, 8 ) // iron bars
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 7 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 42 ), 
			new ItemStack[] {
				new ItemStack( Item.cauldron )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 7 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 42 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcCauldron )
		} );
		
		AddStokedCrucibleRecipe(
			new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 20),
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemScrew )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 6 ), 
			new ItemStack( Item.ingotIron, 4 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcBlockScrewPump )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 1 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 6 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemArmorGimpHelm )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 2 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 12 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemArmorGimpChest )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 1 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 6 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemArmorGimpLeggings )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 2 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 12 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemArmorGimpBoots )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 7 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 42 ), 
			new ItemStack[] {
				new ItemStack( Block.anvil )
		} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 1 ), 
			new ItemStack[] {
				new ItemStack( Block.tripWireSource, 1 )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 4 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 2 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemChiselIron, 1, ignoreMetadata )
		} );
			
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 1 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 6 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemMetalFragment )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 54 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 36 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcBlockShovel )
		} );
		
		AddStokedCrucibleRecipe( 
			//new ItemStack( Item.ingotIron, 11 ), 
			new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 8 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcBlockSpikeIron )
		} );
		
		// diamond melting recipes
		
		AddStokedCrucibleRecipe(
			new ItemStack(FCBetterThanWolves.fcItemIngotDiamond, 1), 
			new ItemStack[] {
				new ItemStack(FCBetterThanWolves.fcItemDiamondPlate)
		});
		
		AddStokedCrucibleRecipe(
			new ItemStack(FCBetterThanWolves.fcItemIngotDiamond, 1), 
			new ItemStack[] {
				new ItemStack(FCBetterThanWolves.fcItemChiselDiamond, 1, ignoreMetadata)
		});
		
		AddStokedCrucibleRecipe(
			new ItemStack(FCBetterThanWolves.fcItemIngotDiamond, 2), 
			new ItemStack[] {
				new ItemStack(FCBetterThanWolves.fcItemShearsDiamond, 1, ignoreMetadata)
		});
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemIngotDiamond, 3 ), 
			new ItemStack[] {
				new ItemStack( Item.pickaxeDiamond, 1, ignoreMetadata )
			} );
				
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemIngotDiamond, 2 ), 
			new ItemStack[] {
				new ItemStack( Item.axeDiamond, 1, ignoreMetadata )
			} );
				
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemIngotDiamond, 2 ), 
			new ItemStack[] {
				new ItemStack( Item.swordDiamond, 1, ignoreMetadata )
			} );
					
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemIngotDiamond, 1 ), 
			new ItemStack[] {
				new ItemStack( Item.hoeDiamond, 1, ignoreMetadata )
			} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemIngotDiamond, 1 ), 
			new ItemStack[] {
				new ItemStack( Item.shovelDiamond, 1, ignoreMetadata )
			} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemIngotDiamond, 6 ), 
			new ItemStack[] {
				new ItemStack( Item.helmetDiamond, 1, ignoreMetadata )
			} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemIngotDiamond, 8 ), 
			new ItemStack[] {
				new ItemStack( Item.plateDiamond, 1, ignoreMetadata )
			} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemIngotDiamond, 7 ), 
			new ItemStack[] {
				new ItemStack( Item.legsDiamond, 1, ignoreMetadata )
			} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemIngotDiamond, 4 ), 
			new ItemStack[] {
				new ItemStack( Item.bootsDiamond, 1, ignoreMetadata )
			} );
		
    	// steel items
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemSteel, 5 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemBattleAxe, 1, ignoreMetadata )
		} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemSteel, 2 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemRefinedAxe, 1, ignoreMetadata )
		} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemSteel, 3 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemRefinedPickAxe, 1, ignoreMetadata )
		} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemSteel, 4 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemMattock, 1, ignoreMetadata )
		} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemSteel, 3 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemRefinedSword, 1, ignoreMetadata )
		} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemSteel, 2 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemRefinedHoe, 1, ignoreMetadata )
		} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemSteel, 1 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemRefinedShovel, 1, ignoreMetadata )
		} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemSteel, 1 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemArmorPlate )
		} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemSteel, 10 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemPlateHelm, 1, ignoreMetadata )
		} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemSteel, 14 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemPlateBreastPlate, 1, ignoreMetadata )
		} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemSteel, 12 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemPlateLeggings, 1, ignoreMetadata )
		} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemSteel, 8 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemPlateBoots, 1, ignoreMetadata )
		} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemNuggetSteel, 3 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemBroadheadArrowhead, 1 )
		} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemNuggetSteel, 3 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemBroadheadArrow, 4 )
		} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemSteel, 16 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 1, 
						FCBlockAestheticOpaque.m_iSubtypeSteel )
		} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemSteel, 16 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcSoulforgedSteelBlock )
		} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemSteel, 1 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemTuningFork, 1, ignoreMetadata )
		} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemSteel, 2 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcBlockPressurePlateSoulforgedSteel, 1 )
		} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemNuggetSteel, 12 ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcAestheticNonOpaque, 1, FCBlockAestheticNonOpaque.m_iSubtypeLightningRod )
		} );
		
		// non-melting recipes
		
		AddStokedCrucibleRecipe( 
			new ItemStack( Item.ingotGold, 1  ), 
			new ItemStack[] {
				new ItemStack( Item.goldNugget, 9 )
			} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( Item.ingotIron, 1  ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemNuggetIron, 9 )
			} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemSteel, 1  ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcItemNuggetSteel, 9 )
			} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcItemSteel, 1 ), 
			new ItemStack[] {
				new ItemStack( Item.ingotIron, 1 ),
				new ItemStack( FCBetterThanWolves.fcItemCoalDust, 1 ),
				new ItemStack( FCBetterThanWolves.fcItemSoulUrn, 1 ),
				new ItemStack( FCBetterThanWolves.fcItemSoulFlux, 1 )				
			} );
		
		// glass creation

		AddStokedCrucibleRecipe( 
			new ItemStack( Block.glass, 4 ), 
			new ItemStack[] {
				new ItemStack( Block.sand, 8 ),
				new ItemStack( Item.netherQuartz )
			} );
		
		AddStokedCrucibleRecipe( 
			new ItemStack( Block.glass, 3 ), 
			new ItemStack[] {
				new ItemStack( Block.thinGlass, 8 )
			} );
		
		// smoothstone creation
		
		AddStokedCrucibleRecipe( 
			new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 1, FCBlockAestheticOpaque.m_iSubtypeWhiteStone ), 
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 1, FCBlockAestheticOpaque.m_iSubtypeWhiteCobble )
			} );		
	}
	
	private static void AddMillStoneRecipes()
	{
		// red dye
		
		AddMillStoneRecipe( new ItemStack( Item.dyePowder, 2, 1 ), new ItemStack( Block.plantRed ) );
		
		// yellow dye
		
		AddMillStoneRecipe( new ItemStack( Item.dyePowder, 2, 11 ), new ItemStack( Block.plantYellow ) );
		
		AddMillStoneRecipe( new ItemStack( Item.dyePowder, 1, 3 ), // cocoa powder 
			new ItemStack( FCBetterThanWolves.fcItemCocoaBeans ) );
		
		AddMillStoneRecipe( new ItemStack( FCBetterThanWolves.fcItemFlour ), 
			new ItemStack( Item.wheat ) );
		
		AddMillStoneRecipe( new ItemStack( FCBetterThanWolves.fcItemFlour ), 
			new ItemStack( FCBetterThanWolves.fcItemWheat ) );
		
		AddMillStoneRecipe( new ItemStack( Item.sugar ), new ItemStack( Item.reed ) );

		AddMillStoneRecipe( new ItemStack( FCBetterThanWolves.fcItemHempFibers, 4, 0 ), new ItemStack( FCBetterThanWolves.fcItemHemp ) );
		
		AddMillStoneRecipe( new ItemStack( FCBetterThanWolves.fcItemScouredLeather ), new ItemStack( Item.leather ) );
		
		AddMillStoneRecipe( new ItemStack( FCBetterThanWolves.fcItemScouredLeatherCut ), new ItemStack( FCBetterThanWolves.fcItemLeatherCut ) );
		
		// the following output stacks are split so they eject separately from the Mill Stone
		
		AddMillStoneRecipe( 
			new ItemStack[] {
				new ItemStack( Item.silk ), 
				new ItemStack( Item.silk ), 
				new ItemStack( Item.silk ), 
				new ItemStack( Item.silk ), 
				new ItemStack( Item.silk ), 
				new ItemStack( Item.silk ), 
				new ItemStack( Item.silk ), 
				new ItemStack( Item.silk ), 
				new ItemStack( Item.silk ), 
				new ItemStack( Item.silk ), 
				new ItemStack( Item.dyePowder, 1, 1 ), // red dye 			
				new ItemStack( Item.dyePowder, 1, 1 ), 			
				new ItemStack( Item.dyePowder, 1, 1 ), 			
				new ItemStack( FCBetterThanWolves.fcItemWolfRaw, 1, 0 )			
			},
			new ItemStack[] {
				new ItemStack( FCBetterThanWolves.fcCompanionCube ) 
		} );
		
		// companion slab
		
		AddMillStoneRecipe( new ItemStack( FCBetterThanWolves.fcItemWolfRaw, 1, 0 ), new ItemStack( FCBetterThanWolves.fcCompanionCube, 1, 1 ) );
		
		AddMillStoneRecipe( new ItemStack( FCBetterThanWolves.fcItemCoalDust, 2 ), new ItemStack( Item.coal, 1, ignoreMetadata )  );
		
		// bone meal		
		
		AddMillStoneRecipe( new ItemStack( Item.dyePowder, 3, 15 ), new ItemStack( Item.bone ) );
		AddMillStoneRecipe( new ItemStack( Item.dyePowder, 6, 15 ), new ItemStack( Item.skull.itemID, 1, 0 ) );
		AddMillStoneRecipe( new ItemStack( Item.dyePowder, 6, 15 ), new ItemStack( Item.skull.itemID, 1, 1 ) );
		
		AddMillStoneRecipe( new ItemStack( FCBetterThanWolves.fcItemGroundNetherrack ), new ItemStack( Block.netherrack ) );
		
		AddMillStoneRecipe(new ItemStack(Item.blazePowder, 2), new ItemStack(Item.blazeRod));
	}
	
	public static final byte cookTimeMultiplierClay = 4;
	public static final byte cookTimeMultiplierOre = 8;
	
	private static void addKilnRecipes() {
		//Ore
		addKilnRecipe(new ItemStack(Item.goldNugget),
				FCBetterThanWolves.fcBlockChunkOreGold,
				cookTimeMultiplierOre);
		
		addKilnRecipe(new ItemStack(Item.ingotGold),
				FCBetterThanWolves.fcBlockChunkOreStorageGold,
				cookTimeMultiplierOre);
		
		addKilnRecipe(new ItemStack(Item.goldNugget),
				Block.oreGold,
				cookTimeMultiplierOre);
		
		addKilnRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron),
				FCBetterThanWolves.fcBlockChunkOreIron,
				cookTimeMultiplierOre);
		
		addKilnRecipe(new ItemStack(Item.ingotIron),
				FCBetterThanWolves.fcBlockChunkOreStorageIron,
				cookTimeMultiplierOre);
		
		addKilnRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron),
				Block.oreIron,
				cookTimeMultiplierOre);

		//Charcoal
		addKilnRecipe(new ItemStack(Item.coal, 1, 1),
				FCBetterThanWolves.fcBloodWood,
				cookTimeMultiplierOre);
		
		addKilnRecipe(new ItemStack(Item.coal, 1, 1),
				Block.wood,
				cookTimeMultiplierOre);
		
		//Pottery
		addKilnRecipe(new ItemStack(Item.brick),
				FCBetterThanWolves.fcBlockUnfiredBrick,
				cookTimeMultiplierClay);
		
		addKilnRecipe(new ItemStack(FCBetterThanWolves.fcCrucible),
				FCBetterThanWolves.fcUnfiredPottery, FCBlockUnfiredPottery.m_iSubtypeCrucible,
				cookTimeMultiplierClay);
		
		addKilnRecipe(new ItemStack(FCBetterThanWolves.fcPlanter),
				FCBetterThanWolves.fcUnfiredPottery, FCBlockUnfiredPottery.m_iSubtypePlanter,
				cookTimeMultiplierClay);
		
		addKilnRecipe(new ItemStack(FCBetterThanWolves.fcVase),
				FCBetterThanWolves.fcUnfiredPottery, FCBlockUnfiredPottery.m_iSubtypeVase,
				cookTimeMultiplierClay);
		
		addKilnRecipe(new ItemStack(FCBetterThanWolves.fcItemUrn),
				FCBetterThanWolves.fcUnfiredPottery, FCBlockUnfiredPottery.m_iSubtypeUrn,
				cookTimeMultiplierClay);
		
		//Disabled until moulds are re-implemented
		//addKilnRecipe(new ItemStack(FCBetterThanWolves.fcItemMould),
		//		FCBetterThanWolves.fcUnfiredPottery, FCBlockUnfiredPottery.m_iSubtypeMould,
		//		cookTimeMultiplierClay);
		
		addKilnRecipe(new ItemStack(FCBetterThanWolves.fcItemNetherBrick),
				FCBetterThanWolves.fcUnfiredPottery,
				new int[] {FCBlockUnfiredPottery.m_iSubtypeNetherBrick, FCBlockUnfiredPottery.m_iSubtypeNetherBrickIAligned},
				cookTimeMultiplierClay);
		
		//Baking
		addKilnRecipe(new ItemStack(Item.cake),
				FCBetterThanWolves.fcUnfiredPottery, FCBlockUnfiredPottery.m_iSubtypeUncookedCake);

		addKilnRecipe(new ItemStack(Item.pumpkinPie),
				FCBetterThanWolves.fcUnfiredPottery, FCBlockUnfiredPottery.m_iSubtypeUncookedPumpkinPie);

		addKilnRecipe(new ItemStack(Item.bread),
				FCBetterThanWolves.fcUnfiredPottery, new int[] {FCBlockUnfiredPottery.m_iSubtypeUncookedBread, FCBlockUnfiredPottery.m_iSubtypeUncookedBreadIAligned});

		addKilnRecipe(new ItemStack(Item.cookie, 8),
				FCBetterThanWolves.fcUnfiredPottery, new int[] {FCBlockUnfiredPottery.m_iSubtypeUncookedCookies, FCBlockUnfiredPottery.m_iSubtypeUncookedCookiesIAligned});
		
		//Other
		addKilnRecipe(new ItemStack[] {
					new ItemStack(FCBetterThanWolves.fcItemEnderSlag), 
					new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, FCBlockAestheticOpaque.m_iSubtypeWhiteCobble)
				},
				Block.whiteStone,
				cookTimeMultiplierOre);
	}
	
	private static void addSawRecipes() {
		//Logs
		addSawRecipe(new ItemStack[] {
					new ItemStack(Block.planks, 4, 0),
					new ItemStack(FCBetterThanWolves.fcItemSawDust, 2),
					new ItemStack(FCBetterThanWolves.fcItemBark, 1, 0)
				},
				Block.wood, new int[] {0, 4, 8});
		
		addSawRecipe(new ItemStack[] {
					new ItemStack(Block.planks, 4, 1),
					new ItemStack(FCBetterThanWolves.fcItemSawDust, 2),
					new ItemStack(FCBetterThanWolves.fcItemBark, 1, 1)
				},
				Block.wood, new int[] {1, 5, 9});
		
		addSawRecipe(new ItemStack[] {
					new ItemStack(Block.planks, 4, 2),
					new ItemStack(FCBetterThanWolves.fcItemSawDust, 2),
					new ItemStack(FCBetterThanWolves.fcItemBark, 1, 2)
				},
				Block.wood, new int[] {2, 6, 10});
		
		addSawRecipe(new ItemStack[] {
					new ItemStack(Block.planks, 4, 3),
					new ItemStack(FCBetterThanWolves.fcItemSawDust, 2),
					new ItemStack(FCBetterThanWolves.fcItemBark, 1, 3)
				},
				Block.wood, new int[] {3, 7, 11});

		addSawRecipe(new ItemStack[] {
					new ItemStack(Block.planks, 4, 4),
					new ItemStack(FCBetterThanWolves.fcItemSoulDust, 1),
					new ItemStack(FCBetterThanWolves.fcItemSawDust, 1),
					new ItemStack(FCBetterThanWolves.fcItemBark, 1, 4)
				},
				FCBetterThanWolves.fcBloodWood);
		
		//Planks
		addSubBlockRecipesToSaw(Block.planks, 0, 
				FCBetterThanWolves.fcBlockWoodOakSidingAndCorner, 
				FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative, 
				FCBetterThanWolves.fcBlockWoodSidingItemStubID, 
				FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 
				FCBetterThanWolves.fcBlockWoodCornerItemStubID, 
				0);

		addSubBlockRecipesToSaw(Block.planks, 1, 
				FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner, 
				FCBetterThanWolves.fcBlockWoodSpruceMouldingAndDecorative, 
				FCBetterThanWolves.fcBlockWoodSidingItemStubID, 
				FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 
				FCBetterThanWolves.fcBlockWoodCornerItemStubID, 
				1);

		addSubBlockRecipesToSaw(Block.planks, 2, 
				FCBetterThanWolves.fcBlockWoodBirchSidingAndCorner, 
				FCBetterThanWolves.fcBlockWoodBirchMouldingAndDecorative, 
				FCBetterThanWolves.fcBlockWoodSidingItemStubID, 
				FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 
				FCBetterThanWolves.fcBlockWoodCornerItemStubID, 
				2);

		addSubBlockRecipesToSaw(Block.planks, 3, 
				FCBetterThanWolves.fcBlockWoodJungleSidingAndCorner, 
				FCBetterThanWolves.fcBlockWoodJungleMouldingAndDecorative, 
				FCBetterThanWolves.fcBlockWoodSidingItemStubID, 
				FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 
				FCBetterThanWolves.fcBlockWoodCornerItemStubID, 
				3);

		addSubBlockRecipesToSaw(Block.planks, 4, 
				FCBetterThanWolves.fcBlockWoodBloodSidingAndCorner, 
				FCBetterThanWolves.fcBlockWoodBloodMouldingAndDecorative, 
				FCBetterThanWolves.fcBlockWoodSidingItemStubID, 
				FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 
				FCBetterThanWolves.fcBlockWoodCornerItemStubID, 
				4);
		
		//Wood slabs
		addSawRecipe(new ItemStack(Item.itemsList[FCBetterThanWolves.fcBlockWoodMouldingItemStubID], 2, 0), 
				Block.woodSingleSlab, new int[] {0, 8});

		addSawRecipe(new ItemStack(Item.itemsList[FCBetterThanWolves.fcBlockWoodMouldingItemStubID], 2, 1), 
				Block.woodSingleSlab, new int[] {1, 9});
		
		addSawRecipe(new ItemStack(Item.itemsList[FCBetterThanWolves.fcBlockWoodMouldingItemStubID], 2, 2), 
				Block.woodSingleSlab, new int[] {2, 10});
		
		addSawRecipe(new ItemStack(Item.itemsList[FCBetterThanWolves.fcBlockWoodMouldingItemStubID], 2, 3), 
				Block.woodSingleSlab, new int[] {3, 11});
		
		addSawRecipe(new ItemStack(Item.itemsList[FCBetterThanWolves.fcBlockWoodMouldingItemStubID], 2, 4), 
				Block.woodSingleSlab, new int[] {4, 12});
		
		addSawRecipe(new ItemStack(Block.woodSingleSlab, 2, 0), 
				Block.woodDoubleSlab, 0);

		addSawRecipe(new ItemStack(Block.woodSingleSlab, 2, 1), 
				Block.woodDoubleSlab, 1);

		addSawRecipe(new ItemStack(Block.woodSingleSlab, 2, 2), 
				Block.woodDoubleSlab, 2);

		addSawRecipe(new ItemStack(Block.woodSingleSlab, 2, 3), 
				Block.woodDoubleSlab, 3);

		addSawRecipe(new ItemStack(Block.woodSingleSlab, 2, 4), 
				Block.woodDoubleSlab, 4);
		
		//Wood stairs
		addSawRecipe(new ItemStack[] {
					new ItemStack(Item.itemsList[FCBetterThanWolves.fcBlockWoodSidingItemStubID], 1, 0),
					new ItemStack(Item.itemsList[FCBetterThanWolves.fcBlockWoodMouldingItemStubID], 1, 0)
				},
				Block.stairsWoodOak);

		addSawRecipe(new ItemStack[] {
					new ItemStack(Item.itemsList[FCBetterThanWolves.fcBlockWoodSidingItemStubID], 1, 1),
					new ItemStack(Item.itemsList[FCBetterThanWolves.fcBlockWoodMouldingItemStubID], 1, 1)
				},
				Block.stairsWoodSpruce);

		addSawRecipe(new ItemStack[] {
					new ItemStack(Item.itemsList[FCBetterThanWolves.fcBlockWoodSidingItemStubID], 1, 2),
					new ItemStack(Item.itemsList[FCBetterThanWolves.fcBlockWoodMouldingItemStubID], 1, 2)
				},
				Block.stairsWoodBirch);

		addSawRecipe(new ItemStack[] {
					new ItemStack(Item.itemsList[FCBetterThanWolves.fcBlockWoodSidingItemStubID], 1, 3),
					new ItemStack(Item.itemsList[FCBetterThanWolves.fcBlockWoodMouldingItemStubID], 1, 3)
				},
				Block.stairsWoodJungle);

		addSawRecipe(new ItemStack[] {
					new ItemStack(Item.itemsList[FCBetterThanWolves.fcBlockWoodSidingItemStubID], 1, 4),
					new ItemStack(Item.itemsList[FCBetterThanWolves.fcBlockWoodMouldingItemStubID], 1, 4)
				},
				FCBetterThanWolves.fcBlockWoodBloodStairs);
		
		//Other Slabs
		addSawRecipe(new ItemStack(FCBetterThanWolves.fcBlockWickerSlab, 2),
				FCBetterThanWolves.fcBlockWicker);
		addSawRecipe(new ItemStack(FCBetterThanWolves.fcBlockWickerSlab, 2),
				FCBetterThanWolves.fcAestheticOpaque, FCBlockAestheticOpaque.m_iSubtypeWicker);

		addSawRecipe(new ItemStack(FCBetterThanWolves.fcBlockWickerPane, 2),
				FCBetterThanWolves.fcBlockWickerSlab);
		
		addSawRecipe(new ItemStack(Item.bone, 5),
				FCBetterThanWolves.fcAestheticOpaque, FCBlockAestheticOpaque.m_iSubtypeBone);
		
		addSawRecipe(new ItemStack(Item.bone, 2),
				FCBetterThanWolves.fcBlockBoneSlab);
		
		addSawRecipe(new ItemStack(Item.rottenFlesh, 5),
				FCBetterThanWolves.fcBlockRottenFlesh);
		
		addSawRecipe(new ItemStack(Item.rottenFlesh, 2),
				FCBetterThanWolves.fcBlockRottenFleshSlab);
		
		addSawRecipe(new ItemStack(FCBetterThanWolves.fcItemCreeperOysters, 8),
				FCBetterThanWolves.fcBlockCreeperOysters);

		addSawRecipe(new ItemStack(FCBetterThanWolves.fcItemCreeperOysters, 4),
				FCBetterThanWolves.fcBlockCreeperOystersSlab);
		
		//Other
		addSawRecipe(new ItemStack(Item.melon, 5),
				Block.melon);

		addSawRecipe(new ItemStack(Block.vine),
				Block.vine);
	}
	
	private static void addPistonPackingRecipes() {
		//Soil
		addPistonPackingRecipe(FCBetterThanWolves.fcBlockAestheticOpaqueEarth, FCBlockAestheticOpaqueEarth.m_iSubtypePackedEarth,
				new ItemStack(FCBetterThanWolves.fcBlockDirtLoose, 2));
		
		addPistonPackingRecipe(Block.dirt,
				new ItemStack(FCBetterThanWolves.fcItemPileDirt, 8));
		
		addPistonPackingRecipe(Block.gravel,
				new ItemStack(FCBetterThanWolves.fcItemPileGravel, 8));
		
		addPistonPackingRecipe(Block.sand,
				new ItemStack(FCBetterThanWolves.fcItemPileSand, 8));
		
		addPistonPackingRecipe(Block.slowSand,
				new ItemStack(FCBetterThanWolves.fcItemPileSoulSand, 8));

		addPistonPackingRecipe(FCBetterThanWolves.fcBlockUnfiredClay,
				new ItemStack(Item.clay, 9));
		addPistonPackingRecipe(FCBetterThanWolves.fcBlockUnfiredClay,
				new ItemStack(FCBetterThanWolves.fcItemBrickUnfired, 9));
		
		addPistonPackingRecipe(FCBetterThanWolves.fcBlockSnowSolid,
				new ItemStack(Item.snowball, 8));
		
		//Stone and Ore
		addPistonPackingRecipe(FCBetterThanWolves.fcBlockChunkOreStorageGold,
				new ItemStack(FCBetterThanWolves.fcItemChunkGoldOre, 9));

		addPistonPackingRecipe(FCBetterThanWolves.fcBlockChunkOreStorageIron,
				new ItemStack(FCBetterThanWolves.fcItemChunkIronOre, 9));

		addPistonPackingRecipe(Block.sandStone,
				new ItemStack(Block.sand, 2));

		addPistonPackingRecipe(FCBetterThanWolves.fcAestheticOpaque, FCBlockAestheticOpaque.m_iSubtypeFlint,
				new ItemStack(Item.flint, 9));

		for (int strata = 0; strata < 3; strata++) {
			addPistonPackingRecipe(FCBetterThanWolves.fcBlockCobblestoneLoose, strata << 2,
					new ItemStack(FCBetterThanWolves.fcItemStone, 8, strata));
		}
		
		
		//Bricks
		addPistonPackingRecipe(FCBetterThanWolves.fcBlockBrickLoose,
				new ItemStack(Item.brick, 8));

		addPistonPackingRecipe(FCBetterThanWolves.fcBlockNetherBrickLoose,
				new ItemStack(FCBetterThanWolves.fcItemNetherBrick, 8));
		
		for (int strata = 0; strata < 3; strata++) {
			addPistonPackingRecipe(FCBetterThanWolves.fcBlockStoneBrickLoose, strata << 2,
					new ItemStack(FCBetterThanWolves.fcItemStoneBrick, 4, strata));
		}
		
		//Mob Drops
		addPistonPackingRecipe(FCBetterThanWolves.fcAestheticOpaque, FCBlockAestheticOpaque.m_iSubtypeBone,
				new ItemStack(Item.bone, 9));

		addPistonPackingRecipe(FCBetterThanWolves.fcBlockCreeperOysters,
				new ItemStack(FCBetterThanWolves.fcItemCreeperOysters, 16));

		addPistonPackingRecipe(FCBetterThanWolves.fcBlockRottenFlesh,
				new ItemStack(Item.rottenFlesh, 9));
		
		addPistonPackingRecipe(FCBetterThanWolves.fcAestheticOpaque, FCBlockAestheticOpaque.m_iSubtypeEnderBlock,
				new ItemStack(Item.enderPearl, 9));
		
		//Other
		addPistonPackingRecipe(FCBetterThanWolves.fcBlockAestheticOpaqueEarth, FCBlockAestheticOpaqueEarth.m_iSubtypeDung,
				new ItemStack(FCBetterThanWolves.fcItemDung, 9));

		addPistonPackingRecipe(FCBetterThanWolves.fcAestheticOpaque, FCBlockAestheticOpaque.m_iSubtypeSoap,
				new ItemStack(FCBetterThanWolves.fcItemSoap, 9));
	}
	
	private static void addHopperFilteringRecipes() {
		addHopperFilteringRecipe(new ItemStack(Block.sand), new ItemStack(Item.flint), 
				new ItemStack(Block.gravel),
				new ItemStack(FCBetterThanWolves.fcBlockWickerPane));

		addHopperFilteringRecipe(new ItemStack(FCBetterThanWolves.fcItemWheatSeeds, 2), new ItemStack(FCBetterThanWolves.fcItemStraw), 
				new ItemStack(FCBetterThanWolves.fcItemWheat),
				new ItemStack(FCBetterThanWolves.fcBlockWickerPane));
		
		addHopperFilteringRecipe(new ItemStack(Item.melonSeeds, 2), null, 
				new ItemStack(FCBetterThanWolves.fcItemMelonMashed),
				new ItemStack(FCBetterThanWolves.fcBlockWickerPane));
		
		//Soul filtering
		addHopperSoulRecipe(new ItemStack(FCBetterThanWolves.fcItemHellfireDust),
				new ItemStack(FCBetterThanWolves.fcItemGroundNetherrack));

		addHopperSoulRecipe(new ItemStack(FCBetterThanWolves.fcItemSawDust),
				new ItemStack(FCBetterThanWolves.fcItemSoulDust));

		addHopperSoulRecipe(new ItemStack(FCBetterThanWolves.fcItemBrimstone),
				new ItemStack(Item.lightStoneDust));
	}
	
	private static void addLogChoppingRecipes() {
		for (int i = 0; i < 4; i++) {
			addLogChoppingRecipe(new ItemStack(Block.planks, 2, i), 
					new ItemStack[] {
							new ItemStack(FCBetterThanWolves.fcItemBark, 1, i),
							new ItemStack(FCBetterThanWolves.fcItemSawDust, 2, 0), 
					}, 
					new ItemStack(Item.stick, 2), 
					new ItemStack[] {
							new ItemStack(FCBetterThanWolves.fcItemBark, 1, i),
							new ItemStack(FCBetterThanWolves.fcItemSawDust, 4, 0),
					}, 
					new ItemStack(Block.wood, 1, i));
		}
		
		addLogChoppingRecipe(new ItemStack(Block.planks, 2, 4), 
				new ItemStack[] {
						new ItemStack(FCBetterThanWolves.fcItemBark, 1, 4),
						new ItemStack(FCBetterThanWolves.fcItemSawDust, 1, 0),
						new ItemStack(FCBetterThanWolves.fcItemSoulDust, 1, 0), 
				}, 
				new ItemStack(Item.stick, 2), 
				new ItemStack[] {
						new ItemStack(FCBetterThanWolves.fcItemBark, 1, 4),
						new ItemStack(FCBetterThanWolves.fcItemSawDust, 3, 0),
						new ItemStack(FCBetterThanWolves.fcItemSoulDust, 1, 0),
				}, 
				new ItemStack(FCBetterThanWolves.fcBloodWood, 1, 0));
	}
	
	private static final int numRotationsPottery = 8;
	
	public static TurntableEffect potteryEffect = new TurntableEffect() {
		@Override
		public void playEffect(World world, int x, int y, int z) {
			world.playAuxSFX(FCBetterThanWolves.m_iBlockDestroyRespectParticleSettingsAuxFXID, x, y, z, FCBetterThanWolves.fcBlockUnfiredClay.blockID);
		}
	};
	
	private static void addTurntableRecipes() {
		addTurntableRecipe(FCBetterThanWolves.fcUnfiredPottery, FCBlockUnfiredPottery.m_iSubtypeCrucible,
				new ItemStack[] {
						new ItemStack(Item.clay, 1)
				}, 
				FCBetterThanWolves.fcBlockUnfiredClay, 
				numRotationsPottery)
			.setEffect(potteryEffect);
		
		addTurntableRecipe(FCBetterThanWolves.fcUnfiredPottery, FCBlockUnfiredPottery.m_iSubtypePlanter, 
				new ItemStack[] {
						new ItemStack(Item.clay, 2)
				}, 
				FCBetterThanWolves.fcUnfiredPottery, FCBlockUnfiredPottery.m_iSubtypeCrucible,
				numRotationsPottery)
			.setEffect(potteryEffect);
		
		addTurntableRecipe(FCBetterThanWolves.fcUnfiredPottery, FCBlockUnfiredPottery.m_iSubtypeVase, 
				new ItemStack[] {
						new ItemStack(Item.clay, 2)
				}, 
				FCBetterThanWolves.fcUnfiredPottery, FCBlockUnfiredPottery.m_iSubtypePlanter,
				numRotationsPottery)
			.setEffect(potteryEffect);
		
		addTurntableRecipe(FCBetterThanWolves.fcUnfiredPottery, FCBlockUnfiredPottery.m_iSubtypeUrn, 
				new ItemStack[] {
						new ItemStack(Item.clay, 2)
				}, 
				FCBetterThanWolves.fcUnfiredPottery, FCBlockUnfiredPottery.m_iSubtypeVase,
				numRotationsPottery)
			.setEffect(potteryEffect);
		
		addTurntableRecipe(null, 0,
				new ItemStack[] {
						new ItemStack(Item.clay, 2)
				}, 
				FCBetterThanWolves.fcUnfiredPottery, FCBlockUnfiredPottery.m_iSubtypeUrn,
				numRotationsPottery)
			.setEffect(potteryEffect);
	}
	
	private static void AddTuningForkRecipes()
	{
		for ( int iTempDamage = 0; iTempDamage < 24; iTempDamage++ )
		{
			AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemTuningFork, 1, iTempDamage + 1 ), new Object[] {	    		
	    		new ItemStack( FCBetterThanWolves.fcItemTuningFork, 1, iTempDamage ) 
			} );
		}
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemTuningFork, 1, 0 ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcItemTuningFork, 1, 24 ) 
		} );
	}	
	    	
	private static void AddSubBlockRecipes()
	{
		AddWoodSubBlockRecipes();
		
		AddSubBlockRecipesOfType( Block.stone, 0, 
			FCBetterThanWolves.fcBlockSmoothStoneSidingAndCorner, 
			FCBetterThanWolves.fcBlockSmoothStoneMouldingAndDecorative, true );
		
		AddSubBlockRecipesOfType( Block.stone, 1, 
				FCBetterThanWolves.fcBlockSmoothStoneSidingAndCornerMidStrata, 
				FCBetterThanWolves.fcBlockSmoothStoneMouldingAndDecorativeMidStrata, true );
		
		AddSubBlockRecipesOfType( Block.stone, 2, 
				FCBetterThanWolves.fcBlockSmoothStoneSidingAndCornerDeepStrata, 
				FCBetterThanWolves.fcBlockSmoothStoneMouldingAndDecorativeDeepStrata, true );
		
		AddSubBlockRecipesOfType( Block.stoneBrick, 0, 
			FCBetterThanWolves.fcBlockStoneBrickSidingAndCorner, 
			FCBetterThanWolves.fcBlockStoneBrickMouldingAndDecorative, true );
		
		AddSubBlockRecipesOfType( Block.stoneBrick, 4, 
				FCBetterThanWolves.fcBlockStoneBrickSidingAndCornerMidStrata, 
				FCBetterThanWolves.fcBlockStoneBrickMouldingAndDecorativeMidStrata, true );
		
		AddSubBlockRecipesOfType( Block.stoneBrick, 8, 
				FCBetterThanWolves.fcBlockStoneBrickSidingAndCornerDeepStrata, 
				FCBetterThanWolves.fcBlockStoneBrickMouldingAndDecorativeDeepStrata, true );
		
		AddSubBlockRecipesOfType( FCBetterThanWolves.fcAestheticOpaque, FCBlockAestheticOpaque.m_iSubtypeWhiteStone, 
			FCBetterThanWolves.fcBlockWhiteStoneSidingAndCorner, 
			FCBetterThanWolves.fcBlockWhiteStoneMouldingAndDecorative, true );
		
		AddSubBlockRecipesOfType( Block.netherBrick, 0, 
			FCBetterThanWolves.fcBlockNetherBrickSidingAndCorner, 
			FCBetterThanWolves.fcBlockNetherBrickMouldingAndDecorative, false );
		
		// high effeciency for nether brick fences
		
		AddRecipe( new ItemStack( Block.netherFence, 2 ), new Object[] {
            "###", 
            Character.valueOf('#'), new ItemStack( FCBetterThanWolves.fcBlockNetherBrickMouldingAndDecorative, 1, 0 ) 
		} );
		
		AddSubBlockRecipesOfType( Block.brick, 0, 
			FCBetterThanWolves.fcBlockBrickSidingAndCorner, FCBetterThanWolves.fcBlockBrickMouldingAndDecorative, true );	
		
		AddSubBlockRecipesOfType( Block.sandStone, ignoreMetadata, 0, 
			FCBetterThanWolves.fcBlockSandstoneSidingAndCorner, FCBetterThanWolves.fcBlockSandstoneMouldingAndDecorative, true );
		
		AddSubBlockRecipesOfType( Block.blockNetherQuartz, 0, 
			FCBetterThanWolves.fcBlockSidingAndCornerBlackStone, FCBetterThanWolves.fcBlockMouldingAndDecorativeBlackStone, true );		
	}
	
	public static void AddSubBlockRecipesOfType( Block fullBlock, int iFullBlockMetadata, Block sidingAndCornerBlock, Block mouldingBlock, boolean bIncludeFence )
	{
		AddSubBlockRecipesOfType( fullBlock, iFullBlockMetadata, iFullBlockMetadata, sidingAndCornerBlock, mouldingBlock, bIncludeFence );
	}
	
	public static void AddSubBlockRecipesOfType( Block fullBlock, int iFullBlockInputMetadata, int iFullBlockOutputMetadata, Block sidingAndCornerBlock, Block mouldingBlock, boolean bIncludeFence )
	{
		// sub block creation recipes
		
		AddAnvilRecipe( new ItemStack( sidingAndCornerBlock, 8, 0 ), new Object[] {
    		"####", 
    		'#', new ItemStack( fullBlock, 1, iFullBlockInputMetadata )
		} );
		
		AddAnvilRecipe( new ItemStack( mouldingBlock, 8, 0 ), new Object[] {
    		"####", 
    		'#', new ItemStack( sidingAndCornerBlock, 1, 0 )
		} );
		
		AddAnvilRecipe( new ItemStack( sidingAndCornerBlock, 8, 1 ), new Object[] {
    		"####", 
    		'#', new ItemStack( mouldingBlock, 1, 0 )
		} );
		
		AddRecipe( new ItemStack( mouldingBlock, 1, FCBlockMouldingAndDecorative.m_iSubtypeColumn ), new Object[] {
    		"M", 
    		"M", 
    		"M", 
    		'M', new ItemStack( mouldingBlock, 1, 0 )
		} );
		
		AddRecipe( new ItemStack( mouldingBlock, 6, FCBlockMouldingAndDecorative.m_iSubtypePedestalUp ), new Object[] {
    		" S ", 
    		"###", 
    		"###", 
    		'#', new ItemStack( fullBlock, 1, iFullBlockInputMetadata ), 
    		'S', new ItemStack( sidingAndCornerBlock, 8, 0 )
		} );
		
		AddRecipe( new ItemStack( mouldingBlock, 4, FCBlockMouldingAndDecorative.m_iSubtypeTable ), new Object[] {
    		"###", 
    		" X ", 
    		" X ", 
    		'#', new ItemStack( sidingAndCornerBlock, 1, 0 ), 
    		'X', new ItemStack( mouldingBlock, 1, 0 )
		} );
		
		AddRecipe( new ItemStack( sidingAndCornerBlock, 4, FCBlockSidingAndCornerAndDecorative.m_iSubtypeBench ), new Object[] {
    		"###", 
    		" X ", 
    		'#', new ItemStack( sidingAndCornerBlock, 1, 0 ), 
    		'X', new ItemStack( mouldingBlock, 1, 0 )
		} );
		
		if ( bIncludeFence )
		{
			AddRecipe( new ItemStack( sidingAndCornerBlock, 6, FCBlockSidingAndCornerAndDecorative.m_iSubtypeFence ), new Object[] {
	            "###", 
	            "###", 
	            Character.valueOf('#'), new ItemStack( fullBlock, 1, iFullBlockInputMetadata ) 
			} );
			
			AddRecipe( new ItemStack( sidingAndCornerBlock, 2, FCBlockSidingAndCornerAndDecorative.m_iSubtypeFence ), new Object[] {
	            "###", 
	            Character.valueOf('#'), new ItemStack( mouldingBlock, 1, 0 ) 
			} );				
		}
		
		// sub block combine recipes
		
		AddShapelessRecipe( new ItemStack(  fullBlock, 1, iFullBlockOutputMetadata ), new Object[] { 
			new ItemStack( sidingAndCornerBlock, 1, 0 ),
			new ItemStack( sidingAndCornerBlock, 1, 0 ) 
		} );
		
		AddShapelessRecipe( new ItemStack(  sidingAndCornerBlock, 1, 0 ), new Object[] { 
			new ItemStack( mouldingBlock, 1, 0 ),
			new ItemStack( mouldingBlock, 1, 0 ) 
		} );
		
		AddShapelessRecipe( new ItemStack(  mouldingBlock, 1, 0 ), new Object[] { 
			new ItemStack( sidingAndCornerBlock, 1, 1 ),
			new ItemStack( sidingAndCornerBlock, 1, 1 ) 
		} );		
	}
	
	private static int[] sidingMetas;
	private static int[] mouldingMetas;
	private static int[] cornerMetas;
	
	static {
		//Builds the arrays containing every meta value for each primary subblock
		ArrayList<Integer> sidingMetaList = new ArrayList();
		ArrayList<Integer> mouldingMetaList = new ArrayList();
		ArrayList<Integer> cornerMetaList = new ArrayList();
		
		for (int i = 0; i < 16; i++) {
			if (i != FCBlockWoodSidingAndCornerAndDecorative.m_iSubtypeBench &&
					i != FCBlockWoodSidingAndCornerAndDecorative.m_iSubtypeFence &&
					!((FCBlockSidingAndCorner) FCBetterThanWolves.fcBlockWoodOakSidingAndCorner).GetIsCorner(i))
			{
				sidingMetaList.add(i);
			}
		}
		
		for (int i = 0; i < 16; i++) {
			if (!((FCBlockWoodMouldingAndDecorative) FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative).IsDecorative(i)) {
				mouldingMetaList.add(i);
			}
		}
		
		for (int i = 0; i < 16; i++) {
			if (i != FCBlockWoodSidingAndCornerAndDecorative.m_iSubtypeBench &&
					i != FCBlockWoodSidingAndCornerAndDecorative.m_iSubtypeFence &&
					((FCBlockSidingAndCorner) FCBetterThanWolves.fcBlockWoodOakSidingAndCorner).GetIsCorner(i))
			{
				cornerMetaList.add(i);
			}
		}
		
		sidingMetas = new int[sidingMetaList.size()];
		mouldingMetas = new int[mouldingMetaList.size()];
		cornerMetas = new int[cornerMetaList.size()];
		
		for (int i = 0; i < sidingMetaList.size(); i++) {
			sidingMetas[i] = sidingMetaList.get(i);
		}
		
		for (int i = 0; i < mouldingMetaList.size(); i++) {
			mouldingMetas[i] = mouldingMetaList.get(i);
		}
		
		for (int i = 0; i < cornerMetaList.size(); i++) {
			cornerMetas[i] = cornerMetaList.get(i);
		}
	}
	
	public static void addSubBlockRecipesToSaw(Block baseBlock, int baseMetadata, Block sidingAndCorner, Block moulding) {
		//Base
		addSawRecipe(new ItemStack(sidingAndCorner, 2, 0),
				baseBlock, baseMetadata);
		
		//Siding
		addSawRecipe(new ItemStack(moulding, 2, 0), 
				sidingAndCorner, sidingMetas);
		
		//Moulding
		addSawRecipe(new ItemStack(sidingAndCorner, 2, 1),
				moulding, mouldingMetas);
		
		//Corners
		addSawRecipe(new ItemStack(FCBetterThanWolves.fcItemGear, 2, 0),
				sidingAndCorner, cornerMetas);
		
		//Fence
		addSawRecipe(new ItemStack(sidingAndCorner, 1, 1),
				sidingAndCorner, FCBlockWoodSidingAndCornerAndDecorative.m_iSubtypeFence);
	}
	
	public static void addSubBlockRecipesToSaw(Block baseBlock, int baseMetadata, Block sidingAndCorner, Block moulding, int sidingItemID, int mouldingItemID, int cornerItemID, int itemMetadata) {
		//Base
		addSawRecipe(new ItemStack(sidingItemID, 2, itemMetadata),
				baseBlock, baseMetadata);
		
		//Siding
		addSawRecipe(new ItemStack(mouldingItemID, 2, itemMetadata), 
				sidingAndCorner, sidingMetas);
		
		//Moulding
		addSawRecipe(new ItemStack(cornerItemID, 2, itemMetadata),
				moulding, mouldingMetas);
		
		//Corners
		addSawRecipe(new ItemStack(FCBetterThanWolves.fcItemGear, 2),
				sidingAndCorner, cornerMetas);
		
		//Fence
		addSawRecipe(new ItemStack(cornerItemID, 1, itemMetadata),
				sidingAndCorner, FCBlockWoodSidingAndCornerAndDecorative.m_iSubtypeFence);
	}
	
	private static void AddWoodSubBlockRecipes()
	{
		// wood sub-blocks
	
		for ( int iWoodType = 0; iWoodType <= 4; iWoodType++ )
		{
			AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID, 6,
				FCItemBlockWoodMouldingDecorativeStub.GetItemDamageForType( iWoodType, FCItemBlockWoodMouldingDecorativeStub.m_iTypePedestal ) ), new Object[] {
	    		" S ", 
	    		"###", 
	    		"###", 
	    		'#', new ItemStack( Block.planks, 1, iWoodType ), 
	    		'S', new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, iWoodType ) 
			} );
			
			AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID, 1, 
				FCItemBlockWoodMouldingDecorativeStub.GetItemDamageForType( iWoodType, FCItemBlockWoodMouldingDecorativeStub.m_iTypeColumn ) ), new Object[] {
	    		"M", 
	    		"M", 
	    		"M", 
	    		'M', new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, iWoodType )
			} );
			
			AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID, 4, 
				FCItemBlockWoodMouldingDecorativeStub.GetItemDamageForType( iWoodType, FCItemBlockWoodMouldingDecorativeStub.m_iTypeTable ) ), new Object[] {
	    		"###", 
	    		" X ", 
	    		" X ", 
	    		'#', new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, iWoodType ), 
	    		'X', new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, iWoodType )
			} );
			
			AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockWoodSidingDecorativeItemStubID, 4, 
				FCItemBlockWoodSidingDecorativeStub.GetItemDamageForType( iWoodType, FCItemBlockWoodSidingDecorativeStub.m_iTypeBench ) ), new Object[] {
	    		"###", 
	    		" X ", 
	    		'#', new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, iWoodType ), 
	    		'X', new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, iWoodType )
			} );
			
			if ( iWoodType == 0 )
			{
				// Recipes for regular vanilla fences
				
				AddRecipe( new ItemStack( Block.fence, 6 ), new Object[] {
		            "###", 
		            "###", 
		    		'#', new ItemStack( Block.planks, 1, iWoodType ), 
				} );
				
				AddRecipe( new ItemStack( Block.fence, 2 ), new Object[] {
		            "###", 
		            Character.valueOf('#'), new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, iWoodType ) 
				} );				
			}
			else
			{
				AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockWoodSidingDecorativeItemStubID, 6, 
					FCItemBlockWoodSidingDecorativeStub.GetItemDamageForType( iWoodType, FCItemBlockWoodSidingDecorativeStub.m_iTypeFence ) ), new Object[] {
		            "###", 
		            "###", 
		    		'#', new ItemStack( Block.planks, 1, iWoodType ), 
				} );
				
				AddRecipe( new ItemStack( FCBetterThanWolves.fcBlockWoodSidingDecorativeItemStubID, 2, 
					FCItemBlockWoodSidingDecorativeStub.GetItemDamageForType( iWoodType, FCItemBlockWoodSidingDecorativeStub.m_iTypeFence ) ), new Object[] {
		            "###", 
		            Character.valueOf('#'), new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, iWoodType ) 
				} );				
			}
			
			// sub-block combine recipes
			
			AddShapelessRecipe( new ItemStack( Block.planks, 1, iWoodType ), new Object[] { 
				new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, iWoodType ),
				new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, iWoodType ) 
			} );
			
			AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, iWoodType ), new Object[] { 
				new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, iWoodType ),
				new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, iWoodType ) 
			} );
			
			AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, iWoodType ), new Object[] { 
				new ItemStack( FCBetterThanWolves.fcBlockWoodCornerItemStubID, 1, iWoodType ),
				new ItemStack( FCBetterThanWolves.fcBlockWoodCornerItemStubID, 1, iWoodType ) 
			} );			
		}
	}
	
	private static void AddLegacyConversionRecipes()
	{
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID, 1, 
			( FCItemBlockWoodMouldingDecorativeStub.m_iTypeTable << 2 ) ), new Object[] { 
			new ItemStack( FCBetterThanWolves.fcAestheticNonOpaque, 1, FCBlockAestheticNonOpaque.m_iSubtypeTable ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockSmoothStoneMouldingAndDecorative, 1, FCBlockMouldingAndDecorative.m_iSubtypePedestalUp ), new Object[] { 
			new ItemStack( FCBetterThanWolves.fcAestheticNonOpaque, 1, FCBlockAestheticNonOpaque.m_iSubtypePedestalUp ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockSmoothStoneMouldingAndDecorative, 1, FCBlockMouldingAndDecorative.m_iSubtypeColumn ), new Object[] { 
			new ItemStack( FCBetterThanWolves.fcAestheticNonOpaque, 1, FCBlockAestheticNonOpaque.m_iSubtypeColumn ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 0 ), new Object[] { 
			new ItemStack( FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockSmoothStoneSidingAndCorner, 1, 1 ), new Object[] { 
			new ItemStack( FCBetterThanWolves.fcBlockLegacySmoothstoneAndOakCorner, 1, 8 ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockWoodCornerItemStubID, 1, 0 ), new Object[] { 
			new ItemStack( FCBetterThanWolves.fcBlockLegacySmoothstoneAndOakCorner, 1, 0 ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockSmoothStoneSidingAndCorner, 1, 0 ), new Object[] { 
			new ItemStack( FCBetterThanWolves.fcBlockLegacySmoothstoneAndOakSiding, 1, 0 ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 0 ), new Object[] { 
			new ItemStack( FCBetterThanWolves.fcBlockLegacySmoothstoneAndOakSiding, 1, 1 ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockAestheticOpaqueEarth, 1, FCBlockAestheticOpaqueEarth.m_iSubtypeDung ), new Object[] { 
			new ItemStack( FCBetterThanWolves.fcAestheticOpaque, 1, FCBlockAestheticOpaque.m_iSubtypeDung ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemMushroomBrown ), new Object[] { 
			new ItemStack( Block.mushroomBrown ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemMushroomRed ), new Object[] { 
			new ItemStack( Block.mushroomRed ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockWorkbench ), new Object[] {	    		
    		new ItemStack( Block.workbench )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockChest ), new Object[] {	    		
    		new ItemStack( Block.chest )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockWeb ), new Object[] {	    		
    		new ItemStack( Block.web )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockLadder ), new Object[] { 
			new ItemStack( Block.ladder ) 
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockSnowSolid ), new Object[] {	    		
    		new ItemStack( Block.blockSnow )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcItemCarrot ), new Object[] {	    		
    		new ItemStack( FCBetterThanWolves.fcItemCarrot )
		} );
		
		AddShapelessRecipe( new ItemStack( FCBetterThanWolves.fcBlockGrassSlab ), new Object[] { 
			new ItemStack( FCBetterThanWolves.fcBlockDirtSlab, 1, FCBlockDirtSlab.m_iSubtypeGrass ) 
		} );
	}

	private static void AddCustomRecipeClasses()
	{
		CraftingManager.getInstance().getRecipeList().add( new FCRecipesFishingRodBaiting() );
		//CraftingManager.getInstance().getRecipeList().add( new FCRecipesLogChopping() );
		CraftingManager.getInstance().getRecipeList().add( new FCRecipesKnitting() );
	}
	
	private static void addVillagerTrades() {
		//Aliased for readability
		int farmer = FCEntityVillager.professionIDFarmer;
		int librarian = FCEntityVillager.professionIDLibrarian;
		int priest = FCEntityVillager.professionIDPriest;
		int blacksmith = FCEntityVillager.professionIDBlacksmith;
		int butcher = FCEntityVillager.professionIDButcher;
		
		// TODO: Rebalance some of the outlier values in trades
		
		// ------ Farmer ------ //
		//Level 1
		FCEntityVillager.addTradeToBuyMultipleItems(farmer, FCBetterThanWolves.fcBlockDirtLoose.blockID, 48, 64, 1F, 1).setDefault(farmer);
		FCEntityVillager.addTradeToBuyMultipleItems(farmer, Block.wood.blockID, 0, 32, 48, 0.15F, 1);
		FCEntityVillager.addTradeToBuyMultipleItems(farmer, Block.wood.blockID, 1, 32, 48, 0.15F, 1);
		FCEntityVillager.addTradeToBuyMultipleItems(farmer, Block.wood.blockID, 2, 32, 48, 0.15F, 1);
		FCEntityVillager.addTradeToBuyMultipleItems(farmer, Block.wood.blockID, 3, 32, 48, 0.15F, 1);
		FCEntityVillager.addTradeToBuyMultipleItems(farmer, FCBetterThanWolves.fcItemWool.itemID, 3, 16, 24, 1F, 1);
		FCEntityVillager.addTradeToBuyMultipleItems(farmer, Item.dyePowder.itemID, 15, 32, 48, 1F, 1);
		
		FCEntityVillager.addLevelUpTradeToBuySingleItem(farmer, Item.hoeIron.itemID, 1, 1, 1);
		
		//Level 2
		FCEntityVillager.addTradeToBuyMultipleItems(farmer, FCBetterThanWolves.fcItemFlour.itemID, 24, 32, 1F, 2);
		FCEntityVillager.addTradeToBuyMultipleItems(farmer, Item.sugar.itemID, 10, 20, 1F, 2);
		FCEntityVillager.addTradeToBuyMultipleItems(farmer, FCBetterThanWolves.fcItemCocoaBeans.itemID, 10, 16, 1F, 2);
		FCEntityVillager.addTradeToBuyMultipleItems(farmer, FCBetterThanWolves.fcItemMushroomBrown.itemID, 10, 16, 1F, 2);
		FCEntityVillager.addTradeToBuyMultipleItems(farmer, FCBetterThanWolves.fcItemHempSeeds.itemID, 24, 32, 1F, 2);
		FCEntityVillager.addTradeToBuyMultipleItems(farmer, Item.egg.itemID, 12, 12, 1F, 2);
		FCEntityVillager.addTradeToBuyMultipleItems(farmer, Block.thinGlass.blockID, 16, 32, 1F, 2);
		FCEntityVillager.addTradeToBuySingleItem(farmer, Item.bucketMilk.itemID, 1, 2, 0.25F, 2);
		
		FCEntityVillager.addTradeToSellMultipleItems(farmer, FCBetterThanWolves.fcItemWheat.itemID, 8, 16, 1F, 2);
		FCEntityVillager.addTradeToSellSingleItem(farmer, FCBetterThanWolves.fcItemReedRoots.itemID, 6, 8, 1F, 2);
		FCEntityVillager.addTradeToSellMultipleItems(farmer, Item.appleRed.itemID, 2, 4, 0.5F, 2);
		
		FCEntityVillager.addLevelUpTradeToBuySingleItem(farmer, FCBetterThanWolves.fcMillStone.blockID, 2, 2, 2);
		
		//Level 3
		FCEntityVillager.addTradeToBuyMultipleItems(farmer, Block.melon.blockID, 8, 10, 1F, 3);
		FCEntityVillager.addTradeToBuyMultipleItems(farmer, FCBetterThanWolves.fcBlockPumpkinFresh.blockID, 10, 16, 1F, 3);
		FCEntityVillager.addTradeToBuyMultipleItems(farmer, FCBetterThanWolves.fcItemStumpRemover.itemID, 8, 12, 1F, 3);
		FCEntityVillager.addTradeToBuyMultipleItems(farmer, FCBetterThanWolves.fcItemChocolate.itemID, 1, 2, 1F, 3);
		FCEntityVillager.addTradeToBuySingleItem(farmer, Item.shears.itemID, 1, 1, 0.5F, 3);
		FCEntityVillager.addTradeToBuySingleItem(farmer, Item.flintAndSteel.itemID, 1, 1, 0.5F, 3);
		FCEntityVillager.addComplexTrade(
				farmer,
				FCBetterThanWolves.fcItemStake.itemID, 0, 1, 1,
				Item.silk.itemID, 0, 16, 32,
				Item.emerald.itemID, 0, 1, 1,
				0.5F, 3
			);
		FCEntityVillager.addTradeToBuySingleItem(farmer, FCBetterThanWolves.fcItemSoap.itemID, 1, 2, 1F, 3)
			.registerEffectForTrade(FCEntityVillager.professionIDFarmer, new TradeEffect() {
				@Override
				public void playEffect(FCEntityVillager villager) {
					villager.worldObj.playSoundAtEntity(villager, "mob.slime.attack", 1.0F, (villager.rand.nextFloat() - villager.rand.nextFloat()) * 0.2F + 1.0F);
					
					villager.SetDirtyPeasant(0);
				}
			})
			.setConditional(new TradeConditional() {
				@Override
				public boolean shouldAddTrade(FCEntityVillager villager) {
					return villager.GetDirtyPeasant() > 0;
				}
			});
		
		FCEntityVillager.addTradeToSellMultipleItems(farmer, Item.bread.itemID, 4, 6, 1F, 3);
		FCEntityVillager.addTradeToSellMultipleItems(farmer, FCBetterThanWolves.fcItemCookedMushroomOmelet.itemID, 8, 12, 0.5F, 3);
		FCEntityVillager.addTradeToSellMultipleItems(farmer, FCBetterThanWolves.fcItemCookedScrambledEggs.itemID, 8, 12, 0.5F, 3);
		
		FCEntityVillager.addLevelUpTradeToBuySingleItem(farmer, FCBetterThanWolves.fcItemWaterWheel.itemID, 3, 3, 3);
		
		//Level 4
		FCEntityVillager.addTradeToBuySingleItem(farmer, FCBetterThanWolves.fcItemBucketCement.itemID, 2, 4, 1F, 4);
		FCEntityVillager.addTradeToBuyMultipleItems(farmer, FCBetterThanWolves.fcLightBulbOff.blockID, 2, 4, 1F, 4);
		
		FCEntityVillager.addTradeToSellMultipleItems(farmer, Item.cookie.itemID, 8, 16, 1F, 4);
		FCEntityVillager.addTradeToSellMultipleItems(farmer, Item.pumpkinPie.itemID, 1, 2, 1F, 4);
		FCEntityVillager.addTradeToSellSingleItem(farmer, Item.cake.itemID, 2, 4, 1F, 4);
		
		FCEntityVillager.addLevelUpTradeToBuy(farmer, FCBetterThanWolves.fcBlockPlanterSoil.blockID, 0, 8, 12, 4, 4, 4);
		
		//Level 5
		FCEntityVillager.addTradeToSellSingleItem(farmer, Block.mycelium.blockID, 10, 20, 1F, 5);
		FCEntityVillager.addArcaneScrollTrade(farmer, Enchantment.looting.effectId, 16, 32, 1F, 5);
		
		// ------ Librarian ------ //
		//Level 1
		FCEntityVillager.addTradeToBuyMultipleItems(librarian, Item.paper.itemID, 24, 32, 1F, 1);
		FCEntityVillager.addTradeToBuyMultipleItems(librarian, Item.dyePowder.itemID, 0, 24, 32, 1F, 1);
		FCEntityVillager.addTradeToBuyMultipleItems(librarian, Item.feather.itemID, 16, 24, 1F, 1);
		
		FCEntityVillager.addLevelUpTradeToBuySingleItem(librarian, Item.enchantedBook.itemID, 2, 2, 1);
		
		//Level 2
		FCEntityVillager.addTradeToBuyMultipleItems(librarian, Item.book.itemID, 1, 3, 1F, 2);
		FCEntityVillager.addTradeToBuySingleItem(librarian, Item.writableBook.itemID, 1, 1, 1F, 2);
		FCEntityVillager.addTradeToBuySingleItem(librarian, Block.bookShelf.blockID, 1, 1, 1F, 2);
		FCEntityVillager.addTradeToBuyMultipleItems(librarian, Item.netherStalkSeeds.itemID, 16, 24, 1F, 2);
		FCEntityVillager.addTradeToBuyMultipleItems(librarian, Item.lightStoneDust.itemID, 24, 32, 1F, 2);
		FCEntityVillager.addTradeToBuyMultipleItems(librarian, FCBetterThanWolves.fcItemNitre.itemID, 32, 48, 1F, 2);
		FCEntityVillager.addTradeToBuyMultipleItems(librarian, FCBetterThanWolves.fcItemBatWing.itemID, 8, 12, 1F, 2);
		FCEntityVillager.addTradeToBuyMultipleItems(librarian, Item.spiderEye.itemID, 4, 8, 1F, 2);
		FCEntityVillager.addTradeToBuyMultipleItems(librarian, Item.redstone.itemID, 32, 48, 1F, 2);
		
		FCEntityVillager.addLevelUpTradeToBuySingleItem(librarian, Item.brewingStand.itemID, 2, 2, 2);
		
		//Level 3
		FCEntityVillager.addTradeToBuyMultipleItems(librarian, FCBetterThanWolves.fcItemWitchWart.itemID, 6, 10, 1F, 3);
		FCEntityVillager.addTradeToBuyMultipleItems(librarian, FCBetterThanWolves.fcItemMysteriousGland.itemID, 14, 16, 1F, 3);
		FCEntityVillager.addTradeToBuyMultipleItems(librarian, Item.fermentedSpiderEye.itemID, 4, 8, 1F, 3);
		FCEntityVillager.addTradeToBuyMultipleItems(librarian, Item.ghastTear.itemID, 4, 6, 1F, 3);
		FCEntityVillager.addTradeToBuyMultipleItems(librarian, Item.magmaCream.itemID, 8, 12, 1F, 3);
		FCEntityVillager.addTradeToBuyMultipleItems(librarian, Item.blazePowder.itemID, 4, 6, 1F, 3);
		
		FCEntityVillager.addLevelUpTradeToBuySingleItem(librarian, FCBetterThanWolves.fcBlockDispenser.blockID, 4, 4, 3);
		
		//Level 4
		FCEntityVillager.addTradeToBuyMultipleItems(librarian, FCBetterThanWolves.fcBlockDetector.blockID, 2, 3, 1F, 4);
		FCEntityVillager.addTradeToBuyMultipleItems(librarian, FCBetterThanWolves.fcBuddyBlock.blockID, 2, 3, 1F, 4);
		FCEntityVillager.addTradeToBuyMultipleItems(librarian, FCBetterThanWolves.fcBlockDispenser.blockID, 2, 3, 1F, 4);
		FCEntityVillager.addTradeToBuySingleItem(librarian, FCBetterThanWolves.fcLens.blockID, 2, 3, 1F, 4);
		
		FCEntityVillager.addLevelUpTradeToBuySingleItem(librarian, FCBetterThanWolves.fcItemEnderSpectacles.itemID, 3, 3, 4);
		
		//Level 5
		FCEntityVillager.addTradeToBuyMultipleItems(librarian, FCBetterThanWolves.fcItemBrimstone.itemID, 16, 32, 1F, 5);
		FCEntityVillager.addTradeToBuyMultipleItems(librarian, FCBetterThanWolves.fcAestheticVegetation.blockID, 
				FCBlockAestheticVegetation.m_iSubtypeBloodWoodSapling, 8, 16, 1F, 5);
		FCEntityVillager.addTradeToBuySingleItem(librarian, FCBetterThanWolves.fcItemBloodMossSpores.itemID, 2, 3, 1F, 5);
		
		FCEntityVillager.addArcaneScrollTrade(librarian, Enchantment.power.effectId, 32, 48, 1F, 5);
		
		//Mandatory trades
		FCEntityVillager.addItemConversionTrade(librarian, Item.enderPearl.itemID, 6, 8, Item.eyeOfEnder.itemID, 1F, 5).setMandatory();
		
		// ------ Priest ------ //
		//Level 1
		FCEntityVillager.addTradeToBuyMultipleItems(priest, FCBetterThanWolves.fcItemHemp.itemID, 18, 22, 1F, 1).setDefault(priest);
		FCEntityVillager.addTradeToBuyMultipleItems(priest, FCBetterThanWolves.fcItemMushroomRed.itemID, 10, 16, 1F, 1);
		FCEntityVillager.addTradeToBuyMultipleItems(priest, Block.cactus.blockID, 32, 64, 1F, 1);
		FCEntityVillager.addTradeToBuySingleItem(priest, Item.painting.itemID, 2, 3, 0.5F, 1);
		FCEntityVillager.addTradeToBuySingleItem(priest, Item.flintAndSteel.itemID, 1, 1, 1F, 1);
		
		FCEntityVillager.addLevelUpTradeToBuySingleItem(priest, Block.enchantmentTable.blockID, 2, 2, 1);
		
		//Level 2
		FCEntityVillager.addEnchantmentTrade(priest, Item.swordIron.itemID, 2, 4, 0.25F, 2);
		FCEntityVillager.addEnchantmentTrade(priest, Item.axeIron.itemID, 2, 4, 0.25F, 2);
		FCEntityVillager.addEnchantmentTrade(priest, Item.pickaxeIron.itemID, 2, 4, 0.25F, 2);
		FCEntityVillager.addEnchantmentTrade(priest, Item.helmetIron.itemID, 2, 4, 0.25F, 2);
		FCEntityVillager.addEnchantmentTrade(priest, Item.plateIron.itemID, 2, 4, 0.25F, 2);
		FCEntityVillager.addEnchantmentTrade(priest, Item.legsIron.itemID, 2, 4, 0.25F, 2);
		FCEntityVillager.addEnchantmentTrade(priest, Item.bootsIron.itemID, 2, 4, 0.25F, 2);
		FCEntityVillager.addEnchantmentTrade(priest, Item.swordDiamond.itemID, 2, 4, 0.25F, 2);
		FCEntityVillager.addEnchantmentTrade(priest, Item.axeDiamond.itemID, 2, 4, 0.25F, 2);
		FCEntityVillager.addEnchantmentTrade(priest, Item.pickaxeDiamond.itemID, 2, 4, 0.25F, 2);
		FCEntityVillager.addEnchantmentTrade(priest, Item.helmetDiamond.itemID, 2, 4, 0.25F, 2);
		FCEntityVillager.addEnchantmentTrade(priest, Item.plateDiamond.itemID, 2, 4, 0.25F, 2);
		FCEntityVillager.addEnchantmentTrade(priest, Item.legsDiamond.itemID, 2, 4, 0.25F, 2);
		FCEntityVillager.addEnchantmentTrade(priest, Item.bootsDiamond.itemID, 2, 4, 0.25F, 2);
		
		FCEntityVillager.addLevelUpTradeToBuySingleItem(priest, FCBetterThanWolves.fcBlockArcaneVessel.blockID, 2, 2, 2);
		
		//Level 3
		((FCEntityVillager.WeightedMerchantRecipeEntry) FCEntityVillager.addTradeToBuyMultipleItems(
				priest, 
				FCBetterThanWolves.fcItemCandle.itemID, 4, 8, 
				1F, 3))
				.setRandomMetas(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, 0);
		FCEntityVillager.addTradeToBuyMultipleItems(priest, Item.skull.itemID, 0, 2, 4, 1F, 3);
		FCEntityVillager.addTradeToBuyMultipleItems(priest, Item.skull.itemID, 2, 2, 4, 1F, 3);
		FCEntityVillager.addTradeToBuyMultipleItems(priest, Item.skull.itemID, 4, 2, 4, 1F, 3);
		FCEntityVillager.addTradeToBuyMultipleItems(priest, FCBetterThanWolves.fcAestheticOpaque.blockID, FCBlockAestheticOpaque.m_iSubtypeBone, 2, 3, 1F, 3);
		
		FCEntityVillager.addLevelUpTradeToBuySingleItem(priest, Item.skull.itemID, 1, 3, 3, 3);
		
		//Level 4
		FCEntityVillager.addTradeToBuyMultipleItems(priest, FCBetterThanWolves.fcItemSoulUrn.itemID, 2, 3, 2F, 4);
		FCEntityVillager.addTradeToBuySingleItem(priest, FCBetterThanWolves.fcItemCanvas.itemID, 2, 3, 1F, 5);
		
		FCEntityVillager.addLevelUpTradeToBuySingleItem(priest, FCBetterThanWolves.fcInfernalEnchanter.blockID, 4, 4, 4);
		
		//Mandatory trades
		FCEntityVillager.addSkullconversionTrade(priest, 1, 6, 8, 5, 1F, 4).setMandatory();
		FCEntityVillager.addComplexTrade(
				priest, 
				FCBetterThanWolves.fcBlockSoulforgeDormant.blockID, 0, 1, 1, 
				Item.netherStar.itemID, 0, 1, 1, 
				FCBetterThanWolves.fcAnvil.blockID, 0, 1, 1, 
				1F, 4
			)
			.setMandatory()
			.registerEffectForTrade(priest, new TradeEffect() {
				@Override
				public void playEffect(FCEntityVillager villager) {
					villager.worldObj.playSoundAtEntity(villager, "random.anvil_land", 0.3F, villager.rand.nextFloat() * 0.1F + 0.9F);
					villager.worldObj.playSoundAtEntity(villager, "ambient.cave.cave4", 0.5F, villager.rand.nextFloat() * 0.05F + 0.5F);
				}
			});
		
		//Level 5
		FCEntityVillager.addArcaneScrollTrade(priest, Enchantment.fortune.effectId, 48, 64, 1F, 5);
		
		// ------ Blacksmith ------ //
		//Level 1
		FCEntityVillager.addTradeToBuyMultipleItems(blacksmith, Item.coal.itemID, 16, 24, 1F, 1).setDefault(blacksmith);
		FCEntityVillager.addTradeToBuyMultipleItems(blacksmith, Block.wood.blockID, 2, 32, 48, 1F, 1);
		FCEntityVillager.addTradeToBuyMultipleItems(blacksmith, FCBetterThanWolves.fcItemNuggetIron.itemID, 18, 27, 1F, 1);
		FCEntityVillager.addTradeToBuySingleItem(blacksmith, FCBetterThanWolves.fcBlockFurnaceBrickIdle.blockID, 1, 1, 1F, 1);
		
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, Item.swordIron.itemID, 4, 6, 1F, 1);
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, Item.axeIron.itemID, 4, 6, 1F, 1);
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, Item.pickaxeIron.itemID, 6, 9, 1F, 1);
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, Item.shovelIron.itemID, 2, 3, 1F, 1);
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, Item.hoeIron.itemID, 2, 3, 1F, 1);
		
		FCEntityVillager.addLevelUpTradeToBuySingleItem(blacksmith, FCBetterThanWolves.fcBBQ.blockID, 1, 1, 1);
		
		//Level 2
		FCEntityVillager.addTradeToBuyMultipleItems(blacksmith, FCBetterThanWolves.fcItemNethercoal.itemID, 12, 20, 1F, 2);
		FCEntityVillager.addTradeToBuyMultipleItems(blacksmith, FCBetterThanWolves.fcBBQ.blockID, 2, 3, 1F, 2);
		FCEntityVillager.addTradeToBuyMultipleItems(blacksmith, FCBetterThanWolves.fcItemCreeperOysters.itemID, 14, 16, 1F, 2);
		FCEntityVillager.addTradeToBuyMultipleItems(blacksmith, Item.goldNugget.itemID, 18, 27, 1F, 2);
		FCEntityVillager.addTradeToBuySingleItem(blacksmith, Item.diamond.itemID, 2, 3, 1F, 2);
		
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, Item.bootsIron.itemID, 4, 6, 1F, 2);
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, Item.helmetIron.itemID, 10, 15, 1F, 2);
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, Item.plateIron.itemID, 16, 24, 1F, 2);
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, Item.legsIron.itemID, 14, 21, 1F, 2);
		
		FCEntityVillager.addLevelUpTradeToBuySingleItem(blacksmith, FCBetterThanWolves.fcBellows.blockID, 2, 2, 2);
		
		//Level 3
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, Item.swordDiamond.itemID, 8, 12, 1F, 3);
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, Item.axeDiamond.itemID, 8, 12, 1F, 3);
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, Item.pickaxeDiamond.itemID, 12, 18, 1F, 3);
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, Item.shovelDiamond.itemID, 4, 6, 1F, 3);
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, Item.hoeDiamond.itemID, 4, 6, 1F, 3);
		
		FCEntityVillager.addLevelUpTradeToBuySingleItem(blacksmith, FCBetterThanWolves.fcCrucible.blockID, 3, 3, 3);
		
		//Level 4
		FCEntityVillager.addTradeToBuyMultipleItems(blacksmith, FCBetterThanWolves.fcItemSoulUrn.itemID, 2, 3, 1F, 4);
		FCEntityVillager.addTradeToBuyMultipleItems(blacksmith, FCBetterThanWolves.fcItemHaft.itemID, 6, 8, 1F, 4);
		FCEntityVillager.addTradeToBuyMultipleItems(blacksmith, FCBetterThanWolves.fcBlockMiningCharge.blockID, 4, 6, 1F, 4);
		
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, Item.bootsDiamond.itemID, 8, 12, 1F, 4);
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, Item.helmetDiamond.itemID, 20, 30, 1F, 4);
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, Item.plateDiamond.itemID, 32, 48, 1F, 4);
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, Item.legsDiamond.itemID, 28, 42, 1F, 4);

		FCEntityVillager.addLevelUpTradeToBuy(blacksmith, FCBetterThanWolves.fcItemSteel.itemID, 0, 8, 8, 4, 4, 4);
		
		//Level 5
		FCEntityVillager.addTradeToBuyMultipleItems(blacksmith, FCBetterThanWolves.fcItemSoulFlux.itemID, 16, 24, 1F, 5);
		
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, Item.bootsChain.itemID, 4, 6, 1F, 5);
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, Item.helmetChain.itemID, 10, 15, 1F, 5);
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, Item.plateChain.itemID, 16, 24, 1F, 5);
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, Item.legsChain.itemID, 14, 21, 1F, 5);
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, FCBetterThanWolves.fcItemRefinedSword.itemID, 16, 24, 1F, 5);
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, FCBetterThanWolves.fcItemRefinedAxe.itemID, 16, 24, 1F, 5);
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, FCBetterThanWolves.fcItemRefinedPickAxe.itemID, 24, 36, 1F, 5);
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, FCBetterThanWolves.fcItemRefinedShovel.itemID, 8, 16, 1F, 5);
		FCEntityVillager.addTradeToSellSingleItem(blacksmith, FCBetterThanWolves.fcItemRefinedHoe.itemID, 16, 24, 1F, 5);
		FCEntityVillager.addArcaneScrollTrade(blacksmith, Enchantment.unbreaking.effectId, 32, 48, 1F, 5);
		
		// ------ Butcher ------ //
		//Level 1
		FCEntityVillager.addTradeToBuyMultipleItems(butcher, Item.arrow.itemID, 24, 32, 1F, 1);
		FCEntityVillager.addTradeToBuySingleItem(butcher, Item.shears.itemID, 1, 1, 0.5F, 1);
		FCEntityVillager.addTradeToBuySingleItem(butcher, Item.fishingRod.itemID, 1, 1, 0.5F, 1);
		
		FCEntityVillager.addTradeToSellMultipleItems(butcher, Item.beefRaw.itemID, 8, 10, 1F, 1).setDefault(butcher);
		FCEntityVillager.addTradeToSellMultipleItems(butcher, Item.porkRaw.itemID, 8, 10, 1F, 1);
		FCEntityVillager.addTradeToSellMultipleItems(butcher, Item.chickenRaw.itemID, 10, 12, 1F, 1);
		FCEntityVillager.addTradeToSellMultipleItems(butcher, Item.fishRaw.itemID, 10, 12, 1F, 1);
		FCEntityVillager.addTradeToSellMultipleItems(butcher, FCBetterThanWolves.fcItemMuttonRaw.itemID, 10, 12, 1F, 1);
		FCEntityVillager.addTradeToSellMultipleItems(butcher, Item.leather.itemID, 7, 9, 1F, 1);
		
		FCEntityVillager.addLevelUpTradeToBuySingleItem(butcher, FCBetterThanWolves.fcCauldron.blockID, 1, 1, 1);
		
		//Level 2
		FCEntityVillager.addTradeToBuyMultipleItems(butcher, FCBetterThanWolves.fcItemDung.itemID, 10, 16, 1F, 2);
		FCEntityVillager.addTradeToBuyMultipleItems(butcher, FCBetterThanWolves.fcItemWolfRaw.itemID, 6, 8, 1F, 2);
		FCEntityVillager.addTradeToBuyMultipleItems(butcher, FCBetterThanWolves.fcItemBark.itemID, 1, 48, 64, 1F, 2);
		
		FCEntityVillager.addTradeToSellMultipleItems(butcher, FCBetterThanWolves.fcItemSteakAndPotatoes.itemID, 4, 8, 1F, 2);
		FCEntityVillager.addTradeToSellMultipleItems(butcher, FCBetterThanWolves.fcItemHamAndEggs.itemID, 4, 8, 1F, 2);
		FCEntityVillager.addTradeToSellMultipleItems(butcher, FCBetterThanWolves.fcItemTastySandwich.itemID, 4, 8, 1F, 2);
		FCEntityVillager.addTradeToSellMultipleItems(butcher, FCBetterThanWolves.fcItemFishSoup.itemID, 10, 12, 1F, 2);
		FCEntityVillager.addTradeToSellMultipleItems(butcher, FCBetterThanWolves.fcItemCookedKebab.itemID, 4, 8, 1F, 2);
		
		FCEntityVillager.addLevelUpTradeToBuySingleItem(butcher, FCBetterThanWolves.fcSaw.blockID, 2, 2, 2);
		
		//Level 3
		FCEntityVillager.addTradeToBuyMultipleItems(butcher, FCBetterThanWolves.fcItemCarrot.itemID, 10, 16, 1F, 3);
		FCEntityVillager.addTradeToBuyMultipleItems(butcher, Item.potato.itemID, 10, 16, 1F, 3);
		FCEntityVillager.addTradeToBuySingleItem(butcher, FCBetterThanWolves.fcItemBeastLiverRaw.itemID, 1, 2, 1F, 3);

		FCEntityVillager.addTradeToSellMultipleItems(butcher, FCBetterThanWolves.fcItemTannedLeather.itemID, 4, 8, 1F, 3);
		FCEntityVillager.addTradeToSellMultipleItems(butcher, FCBetterThanWolves.fcItemPorkDinner.itemID, 4, 6, 1F, 3);
		FCEntityVillager.addTradeToSellMultipleItems(butcher, FCBetterThanWolves.fcItemSteakDinner.itemID, 4, 6, 1F, 3);
		FCEntityVillager.addTradeToSellMultipleItems(butcher, FCBetterThanWolves.fcItemWolfDinner.itemID, 4, 6, 1F, 3);
		FCEntityVillager.addTradeToSellMultipleItems(butcher, FCBetterThanWolves.fcItemChickenSoup.itemID, 4, 6, 1F, 3);
		FCEntityVillager.addTradeToSellSingleItem(butcher, Item.saddle.itemID, 2, 3, 1F, 3);
		
		FCEntityVillager.addLevelUpTradeToBuySingleItem(butcher, FCBetterThanWolves.fcItemBreedingHarness.itemID, 3, 3, 3);
		
		//Level 4
		FCEntityVillager.addTradeToBuyMultipleItems(butcher, FCBetterThanWolves.fcItemRawMysteryMeat.itemID, 2, 3, 1F, 4);
		FCEntityVillager.addTradeToBuySingleItem(butcher, FCBetterThanWolves.fcItemScrew.itemID, 2, 3, 1F, 4);
		FCEntityVillager.addTradeToBuySingleItem(butcher, FCBetterThanWolves.fcItemCompositeBow.itemID, 2, 3, 1F, 4);
		
		FCEntityVillager.addTradeToSellMultipleItems(butcher, FCBetterThanWolves.fcItemHeartyStew.itemID, 3, 4, 1F, 4);
		FCEntityVillager.addTradeToSellSingleItem(butcher, FCBetterThanWolves.fcItemArmorTannedBoots.itemID, 2, 3, 0.5F, 4);
		FCEntityVillager.addTradeToSellSingleItem(butcher, FCBetterThanWolves.fcItemArmorTannedChest.itemID, 6, 8, 0.5F, 4);
		FCEntityVillager.addTradeToSellSingleItem(butcher, FCBetterThanWolves.fcItemArmorTannedHelm.itemID, 3, 4, 0.5F, 4);
		FCEntityVillager.addTradeToSellSingleItem(butcher, FCBetterThanWolves.fcItemArmorTannedLeggings.itemID, 4, 6, 0.5F, 4);
		
		FCEntityVillager.addLevelUpTradeToBuySingleItem(butcher, FCBetterThanWolves.fcAestheticOpaque.blockID, FCBlockAestheticOpaque.m_iSubtypeChoppingBlockDirty, 4, 4, 4);
		
		//Mandatory trades
		FCEntityVillager.addSkullconversionTrade(butcher, 0, 6, 8, 1, 1F, 4).setMandatory();
		
		//Level 5
		FCEntityVillager.addTradeToBuyMultipleItems(butcher, FCBetterThanWolves.fcItemDynamite.itemID, 4, 6, 1F, 5);
		FCEntityVillager.addTradeToBuySingleItem(butcher, FCBetterThanWolves.fcItemBattleAxe.itemID, 4, 5, 1F, 5);
		FCEntityVillager.addTradeToBuySingleItem(butcher, FCBetterThanWolves.fcCompanionCube.blockID, 1, 2, 1F, 5)
			.registerEffectForTrade(butcher, new TradeEffect() {
				@Override
				public void playEffect(FCEntityVillager villager) {
					villager.worldObj.playSoundAtEntity(villager, "mob.wolf.hurt", 5.0F, (villager.rand.nextFloat() - villager.rand.nextFloat()) * 0.2F + 1.0F);
				}
			});
		FCEntityVillager.addTradeToBuyMultipleItems(butcher, FCBetterThanWolves.fcItemBroadheadArrow.itemID, 6, 12, 1F, 5);
		FCEntityVillager.addComplexTrade(
				butcher,
				FCBetterThanWolves.fcBlockLightningRod.blockID, 0, 1, 1,
				FCBetterThanWolves.fcItemSoap.itemID, 0, 1, 1,
				Item.emerald.itemID, 0, 3, 5,
				0.5F, 5
			)
			.registerEffectForTrade(butcher, new TradeEffect() {
				@Override
				public void playEffect(FCEntityVillager villager) {
					villager.worldObj.playSoundAtEntity(villager, "random.classic_hurt", 1F, villager.getSoundPitch() * 2.0F);
				}
			});
			
		FCEntityVillager.addArcaneScrollTrade(butcher, Enchantment.sharpness.effectId, 32, 48, 1F, 5);
	}

	private static void RemoveVanillaRecipes()
	{
		RemoveVanillaRecipe( new ItemStack( Item.bread, 1 ), new Object[] {
            "###", 
            '#', Item.wheat
        } );
		
		RemoveVanillaShapelessRecipe( new ItemStack( Item.dyePowder, 3, 15 ), new Object[] {
	        Item.bone
        } );
		
		RemoveVanillaRecipe( new ItemStack( Item.sugar, 1 ), new Object[] {
		    "#", '#', Item.reed
		} );

		RemoveVanillaRecipe( new ItemStack( Item.cake, 1 ), new Object[] {
			"AAA", 
			"BEB", 
			"CCC", 
			'A', Item.bucketMilk, 
			'B', Item.sugar, 
			'C', Item.wheat, 
			'E', Item.egg
		} );
		
		RemoveVanillaShapelessRecipe( new ItemStack( Item.dyePowder, 2, 11 ), new Object[] {
            Block.plantYellow
        } );
        
		RemoveVanillaShapelessRecipe( new ItemStack( Item.dyePowder, 2, 1 ), new Object[] {
            Block.plantRed
        } );
		
		RemoveVanillaRecipe( new ItemStack( Block.tnt, 1 ), new Object[] {
			"X#X", 
			"#X#", 
			"X#X", 
			'X', Item.gunpowder, 
			'#', Block.sand
		} );
		
		RemoveVanillaRecipe( new ItemStack( Item.cookie, 8 ), new Object[] {
 			"#X#", 
 			'X', new ItemStack(Item.dyePowder, 1, 3), // cocoa beans 
 			'#', Item.wheat
 		} );
		
		RemoveVanillaRecipe( new ItemStack( Block.anvil, 1 ), new Object[] {
			"III", 
			" i ", 
			"iii", 
			'I', Block.blockIron, 
			'i', Item.ingotIron
		} );
		
		RemoveVanillaShapelessRecipe( new ItemStack( Item.bowlSoup ), new Object[] {
			Block.mushroomBrown, 
			Block.mushroomRed, 
			Item.bowlEmpty
		} );
		
		RemoveVanillaRecipe( new ItemStack( Block.melon ), new Object[] {
			"MMM", 
			"MMM", 
			"MMM", 
			'M', Item.melon 
		} );
		
		RemoveVanillaShapelessRecipe( new ItemStack( Item.pumpkinPie ), new Object[] {
			Block.pumpkin, 
			Item.sugar, 
			Item.egg
		} );
		
		RemoveVanillaRecipe( new ItemStack( Item.pumpkinSeeds, 4 ), new Object[] {
			"M", 
			'M', Block.pumpkin
		} );		
		
		RemoveVanillaRecipe( new ItemStack( Item.compass, 1 ), new Object[] {
			" # ", 
			"#X#", 
			" # ", 
			'#', Item.ingotIron, 
			'X', Item.redstone
		} );
		
		RemoveVanillaRecipe( new ItemStack( Item.pocketSundial, 1 ), new Object[] {
			" # ", 
			"#X#", 
			" # ", 
			'#', Item.ingotGold, 
			'X', Item.redstone } );
		
		RemoveVanillaRecipe( new ItemStack( Item.flintAndSteel, 1 ), new Object[] {
			"A ", 
			" B", 
			'A', Item.ingotIron, 
			'B', Item.flint
		} );
		
		RemoveVanillaShapelessRecipe( new ItemStack( Item.fermentedSpiderEye ), new Object[] {
			Item.spiderEye, 
			Block.mushroomBrown, 
			Item.sugar
		} );		
		
		RemoveVanillaRecipe( new ItemStack( Block.torchWood, 4 ), new Object[] {
			"X", 
			"#", 
			'X', Item.coal, 
			'#', Item.stick 
		} );
		
		RemoveVanillaRecipe( new ItemStack( Block.torchWood, 4 ), new Object[] { 
			"X", 
			"#", 
			'X', new ItemStack( Item.coal, 1, 1 ), 
			'#', Item.stick 
		} );
		
        RemoveVanillaRecipe( new ItemStack( Item.bucketEmpty, 1 ), new Object[] {
        	"# #", 
        	" # ", 
        	'#', Item.ingotIron
    	} );
        
        RemoveVanillaRecipe( new ItemStack( Item.redstoneRepeater, 1 ), new Object[] {
        	"#X#", 
        	"III", 
        	'#', Block.torchRedstoneActive, 
        	'X', Item.redstone, 
        	'I', Block.stone } );
        
        RemoveVanillaRecipe( new ItemStack( Block.snow, 6 ), new Object[] {
        	"###", 
        	'#', Block.blockSnow
    	} );
        
        RemoveVanillaRecipe( new ItemStack( Block.dropper, 1 ), new Object[] {
        	"###", 
        	"# #", 
        	"#R#", 
        	'#', Block.cobblestone, 
        	'R', Item.redstone
    	} );
        
        RemoveVanillaRecipe( new ItemStack( Block.stoneButton, 1 ), new Object[] {
        	"#", 
        	'#', 
        	Block.stone} );
        
        RemoveVanillaRecipe( new ItemStack( Block.woodenButton, 1 ), new Object[] {
        	"#", 
        	'#', Block.planks
    	} );
        
        RemoveVanillaRecipe( new ItemStack( Block.pressurePlateStone, 1 ), new Object[] {
        	"##", 
        	'#', Block.stone 
    	} );
        
        RemoveVanillaRecipe( new ItemStack( Block.pressurePlatePlanks, 1 ), new Object[] {
        	"##", 
        	'#', Block.planks
    	} );
        
        RemoveVanillaRecipe( new ItemStack( Block.pressurePlateIron, 1 ), new Object[] { 
    		"##", 
    		'#', Item.ingotIron
		} );
        
        RemoveVanillaRecipe( new ItemStack( Block.pressurePlateGold, 1 ), new Object[] {
        	"##", 
        	'#', Item.ingotGold
    	} );
        
        RemoveVanillaRecipe( new ItemStack( Block.daylightSensor ), new Object[] {
        	"GGG", 
        	"QQQ", 
        	"WWW", 
        	'G', Block.glass, 
        	'Q', Item.netherQuartz, 
        	'W', Block.woodSingleSlab
    	} );
        
        RemoveVanillaRecipe( new ItemStack( Block.hopperBlock ), new Object[] {
        	"I I", 
        	"ICI", 
        	" I ", 
        	'I', Item.ingotIron, 
        	'C', Block.chest
    	} );
        
        RemoveVanillaRecipe( new ItemStack( Block.rail, 16 ), new Object[] {
        	"X X", 
        	"XSX", 
        	"X X", 
        	'X', Item.ingotIron, 
        	'S', Item.stick } );
        
        RemoveVanillaRecipe( new ItemStack( Block.railPowered, 6 ), new Object[] {
        	"X X", 
        	"XSX", 
        	"XRX", 
        	'X', Item.ingotGold, 
        	'R', Item.redstone, 
        	'S', Item.stick } );
        
        RemoveVanillaRecipe( new ItemStack( Block.railDetector, 6 ), new Object[] {
        	"X X", 
        	"XSX", 
        	"XRX", 
        	'X', Item.ingotIron, 
        	'R', Item.redstone, 
        	'S', Block.pressurePlateStone } );
        
        RemoveVanillaRecipe( new ItemStack( Block.railActivator, 6 ), new Object[] {
        	"XSX", 
        	"X#X", 
        	"XSX", 
        	'X', Item.ingotIron, 
        	'#', Block.torchRedstoneActive, 
        	'S', Item.stick } );
        
        RemoveVanillaRecipe( new ItemStack( Item.comparator, 1 ), new Object[] {
        	" # ", 
        	"#X#", 
        	"III", 
        	'#', Block.torchRedstoneActive, 
        	'X', Item.netherQuartz, 
        	'I', Block.stone
    	} );

        RemoveVanillaRecipe( new ItemStack( Item.minecartTnt, 1 ), new Object[] {
        	"A", 
        	"B", 
        	'A', Block.tnt, 
        	'B', Item.minecartEmpty
    	} );
        
        RemoveVanillaRecipe( new ItemStack( Item.minecartHopper, 1 ), new Object[] { 
        	"A", 
        	"B", 
        	'A', Block.hopperBlock, 
        	'B', Item.minecartEmpty
    	} );
        
        RemoveVanillaRecipe( new ItemStack( Block.chestTrapped ), new Object[] {
        	"#-", 
        	'#', Block.chest, 
        	'-', Block.tripWireSource
    	} );

        // remove diamond tool & armor recipes
        
		RemoveVanillaRecipe( new ItemStack( Item.helmetDiamond ), new Object[] {
			"XXX", 
			"X X", 
			'X', Item.diamond
		} ); 
	   
		RemoveVanillaRecipe( new ItemStack( Item.plateDiamond ), new Object[] {
			"X X", 
			"XXX", 
			"XXX", 
			'X', Item.diamond
		} );
	   
		RemoveVanillaRecipe( new ItemStack( Item.legsDiamond ), new Object[] {
			"XXX", 
			"X X", 
			"X X", 
			'X', Item.diamond
		} );
	   
		RemoveVanillaRecipe( new ItemStack( Item.bootsDiamond ), new Object[] {
			"X X", 
			"X X", 
			'X', Item.diamond
		} );
		
		RemoveVanillaRecipe( new ItemStack( Item.swordDiamond ), new Object[] {
			"X", 
			"X", 
			"#",
			'#', Item.stick,
			'X', Item.diamond
		} );
		
		RemoveVanillaRecipe( new ItemStack( Item.pickaxeDiamond ), new Object[] {
			"XXX", 
			" # ", 
			" # ",
			'#', Item.stick,
			'X', Item.diamond			
		} ); 
		
		RemoveVanillaRecipe( new ItemStack( Item.shovelDiamond ), new Object[] {
			"X", 
			"#", 
			"#",
			'#', Item.stick,
			'X', Item.diamond			
		} ); 
		
		RemoveVanillaRecipe( new ItemStack( Item.hoeDiamond ), new Object[] {
			"XX", 
			" #", 
			" #",
			'#', Item.stick,
			'X', Item.diamond			
		} );
		
		RemoveVanillaRecipe( new ItemStack( Item.fishingRod, 1 ), new Object[] {
			"  #", 
			" #X", 
			"# X", 
			'#', Item.stick, 
			'X', Item.silk
		} );
		
		RemoveVanillaRecipe( new ItemStack( Block.cloth, 1 ), new Object[] {
			"##", 
			"##", 
			'#', Item.silk 
		} );
		
		RemoveVanillaRecipe( new ItemStack( Block.planks, 4, 0 ), new Object[] {
			"#", 
			'#', new ItemStack( Block.wood, 1, 0 )
		} );
		
		RemoveVanillaRecipe( new ItemStack( Block.planks, 4, 1 ), new Object[] {
			"#", 
			'#', new ItemStack( Block.wood, 1, 1 )
		} );
		
		RemoveVanillaRecipe( new ItemStack( Block.planks, 4, 2 ), new Object[] {
			"#", 
			'#', new ItemStack( Block.wood, 1, 2 ) 
		} );
		
		RemoveVanillaRecipe( new ItemStack( Block.planks, 4, 3 ), new Object[] {
			"#", 
			'#', new ItemStack( Block.wood, 1, 3 )
		} );
		
		RemoveVanillaRecipe( new ItemStack( Block.lever, 1 ), new Object[] {
			"X", 
			"#", 
			'#', Block.cobblestone, 
			'X', Item.stick } );
        
		RemoveVanillaRecipe( new ItemStack( Item.doorIron, 1 ), new Object[] {
			"##", 
			"##", 
			"##", 
			'#', Item.ingotIron
		} );
        
		RemoveVanillaRecipe( new ItemStack( Block.tripWireSource, 2 ), new Object[] {
			"I", 
			"S", 
			"#", 
			'#', Block.planks, 
			'S', Item.stick, 
			'I', Item.ingotIron 
		} );
		
		RemoveVanillaRecipe( new ItemStack( Block.dispenser, 1 ), new Object[] {
			"###", 
			"#X#", 
			"#R#", 
			'#', Block.cobblestone, 
			'X', Item.bow, 
			'R', Item.redstone
		} );
		
		RemoveVanillaRecipe( new ItemStack( Block.music, 1 ), new Object[] {
			"###", 
			"#X#", 
			"###", 
			'#', Block.planks, 
			'X', Item.redstone
		} );
		
		RemoveVanillaRecipe( new ItemStack( Block.enchantmentTable, 1 ), new Object[] {
			" B ", 
			"D#D", 
			"###", 
			'#', Block.obsidian, 
			'B', Item.book, 
			'D', Item.diamond 
		} );
        
        RemoveVanillaRecipe( new ItemStack( Item.swordWood ), new Object[] {
        	"X", 
        	"X", 
        	"#", 
        	'#', Item.stick, 
        	'X', Block.planks
    	} );
        
        RemoveVanillaRecipe( new ItemStack( Item.axeWood ), new Object[] {
        	"XX", 
        	"X#", 
        	" #",
        	'#', Item.stick, 
        	'X', Block.planks
    	} );        
        
        RemoveVanillaRecipe( new ItemStack( Item.pickaxeWood ), new Object[] {
        	"XXX", 
        	" # ", 
        	" # ",         	
        	'#', Item.stick, 
        	'X', Block.planks
    	} );        
        
        RemoveVanillaRecipe( new ItemStack( Item.shovelWood ), new Object[] {
            "X", 
            "#", 
            "#",
        	'#', Item.stick, 
        	'X', Block.planks
    	} );
        
        RemoveVanillaRecipe( new ItemStack( Item.hoeWood ), new Object[] {
            "XX", 
            " #", 
            " #",
        	'#', Item.stick, 
        	'X', Block.planks
    	} );
        
        RemoveVanillaRecipe( new ItemStack( Item.swordStone ), new Object[] {
        	"X", 
        	"X", 
        	"#", 
        	'#', Item.stick, 
        	'X', Block.cobblestone
    	} );
        
        RemoveVanillaRecipe( new ItemStack( Item.axeStone ), new Object[] {
        	"XX", 
        	"X#", 
        	" #",
        	'#', Item.stick, 
        	'X', Block.cobblestone
    	} );        
        
        RemoveVanillaRecipe( new ItemStack( Item.pickaxeStone ), new Object[] {
        	"XXX", 
        	" # ", 
        	" # ",         	
        	'#', Item.stick, 
        	'X', Block.cobblestone 
    	} );
        
        RemoveVanillaRecipe( new ItemStack( Item.shovelStone ), new Object[] {
            "X", 
            "#", 
            "#",
        	'#', Item.stick, 
        	'X', Block.cobblestone 
    	} );
        
        RemoveVanillaRecipe( new ItemStack( Item.hoeStone ), new Object[] {
            "XX", 
            " #", 
            " #",
        	'#', Item.stick, 
        	'X', Block.cobblestone
    	} );
        
        RemoveVanillaRecipe( new ItemStack( Item.axeIron ), new Object[] {
        	"XX", 
        	"X#", 
        	" #",
        	'#', Item.stick, 
        	'X', Item.ingotIron
    	} );        
        
		RemoveVanillaRecipe( new ItemStack( Item.hoeIron ), new Object[] {
			"XX", 
			" #", 
			" #",
			'#', Item.stick,
			'X', Item.ingotIron			
		} );
		
        RemoveVanillaRecipe( new ItemStack( Item.axeGold ), new Object[] {
        	"XX", 
        	"X#", 
        	" #",
        	'#', Item.stick, 
        	'X', Item.ingotGold
    	} );        
        
		RemoveVanillaRecipe( new ItemStack( Item.hoeGold ), new Object[] {
			"XX", 
			" #", 
			" #",
			'#', Item.stick,
			'X', Item.ingotGold			
		} );
		
		RemoveVanillaRecipe( new ItemStack( Item.axeDiamond ), new Object[] {
			"XX", 
			"X#", 
			" #", 
			'#', Item.stick,
			'X', Item.diamond			
		} );
		
		RemoveVanillaRecipe( new ItemStack( Item.arrow, 4 ), new Object[] {
			"X", 
			"#", 
			"Y", 
			'Y', Item.feather, 
			'X', Item.flint, 
			'#', Item.stick
		} );		
        
		RemoveVanillaRecipe( new ItemStack( Block.pistonBase, 1 ), new Object[] {
			"TTT", 
			"#X#", 
			"#R#", 
			'#', Block.cobblestone, 
			'X', Item.ingotIron, 
			'R', Item.redstone, 
			'T', Block.planks
		} );
		
		RemoveVanillaRecipe( new ItemStack( Item.brewingStand, 1 ), new Object[] {
			" B ", 
			"###", 
			'#', Block.cobblestone, 
			'B', Item.blazeRod
		} );
		
		RemoveVanillaRecipe( new ItemStack( Item.emptyMap, 1 ), new Object[] {
			"###", 
			"#X#", 
			"###", 
			'#', Item.paper, 
			'X', Item.compass
		} );
        
		RemoveVanillaShapelessRecipe( new ItemStack( Item.eyeOfEnder, 1 ), new Object[] {
			Item.enderPearl, 
			Item.blazePowder
		} );
        
		RemoveVanillaShapelessRecipe(new ItemStack(Item.blazePowder, 2), new Object[] {
			Item.blazeRod
		});
        
		RemoveVanillaRecipe( new ItemStack( Item.bed, 1 ), new Object[] {
			"###", 
			"XXX", 
			'#', Block.cloth, 
			'X', Block.planks
		} );
		
		RemoveVanillaRecipe( new ItemStack( Block.fence, 2 ), new Object[] {
			"###", 
			"###", 
			'#', Item.stick
		} );

		RemoveVanillaRecipe( new ItemStack( Block.trapdoor, 2 ), new Object[] {
			"###", 
			"###", 
			'#', Block.planks
		} );		

		RemoveVanillaRecipe( new ItemStack( Block.pumpkinLantern, 1 ), new Object[] {
			"A", 
			"B", 
			'A', Block.pumpkin, 'B', Block.torchWood 
		} );
		
		RemoveVanillaRecipe( new ItemStack( Block.blockClay, 1 ), new Object[] {
			"##", 
			"##", 
			'#', Item.clay
		} );

		RemoveVanillaRecipe( new ItemStack( Block.brick, 1 ), new Object[] {
			"##", 
			"##", 
			'#', Item.brick
		} );
        
		RemoveVanillaRecipe( new ItemStack( Block.ladder, 3 ), new Object[] {
			"# #", 
			"###", 
			"# #", 
			'#', Item.stick
		} );

		RemoveVanillaRecipe( new ItemStack( Block.furnaceIdle ), new Object[] {
			"###", 
			"# #", 
			"###", 
			'#', Block.cobblestone
		} );

		RemoveVanillaRecipe( new ItemStack( Block.sandStone ), new Object[] {
			"##", 
			"##", 
			'#', Block.sand
		} );
		
		RemoveVanillaRecipe( new ItemStack( Block.blockSnow, 1 ), new Object[] {
			"##", 
			"##", 
			'#', Item.snowball
		} );
		
		RemoveVanillaRecipe( new ItemStack( Block.stairsWoodOak, 4 ), new Object[] {
			"#  ", 
			"## ", 
			"###", 
			'#', new ItemStack(Block.planks, 1, 0)
		} );
		
		RemoveVanillaRecipe( new ItemStack( Block.stairsWoodBirch, 4 ), new Object[] {
			"#  ", 
			"## ", 
			"###", 
			'#', new ItemStack(Block.planks, 1, 2)
		} );
		
		RemoveVanillaRecipe( new ItemStack( Block.stairsWoodSpruce, 4 ), new Object[] {
			"#  ", 
			"## ", 
			"###", 
			'#', new ItemStack(Block.planks, 1, 1)
		} );
		
		RemoveVanillaRecipe( new ItemStack( Block.stairsWoodJungle, 4 ), new Object[] {
			"#  ", 
			"## ", 
			"###", 
			'#', new ItemStack(Block.planks, 1, 3)
		} );
		
		RemoveVanillaRecipe(new ItemStack(Block.stairsBrick, 4), new Object[] {
			"#  ", 
			"## ", 
			"###", 
			'#', new ItemStack(Block.brick)
		});
		
		RemoveVanillaRecipe(new ItemStack(Block.stairsCobblestone, 4), new Object[] {
			"#  ", 
			"## ", 
			"###", 
			'#', new ItemStack(Block.cobblestone)
		});
		
		RemoveVanillaRecipe(new ItemStack(Block.stairsNetherBrick, 4), new Object[] {
			"#  ", 
			"## ", 
			"###", 
			'#', new ItemStack(Block.netherBrick)
		});
		
		RemoveVanillaRecipe(new ItemStack(Block.stairsNetherQuartz, 4), new Object[] {
			"#  ", 
			"## ", 
			"###", 
			'#', new ItemStack(Block.blockNetherQuartz)
		});
		
		RemoveVanillaRecipe(new ItemStack(Block.stairsSandStone, 4), new Object[] {
			"#  ", 
			"## ", 
			"###", 
			'#', new ItemStack(Block.sandStone)
		});
		
		RemoveVanillaRecipe(new ItemStack(Block.stairsStoneBrick, 4), new Object[] {
			"#  ", 
			"## ", 
			"###", 
			'#', new ItemStack(Block.stoneBrick)
		});
		
		RemoveVanillaRecipe( new ItemStack( Block.workbench ), new Object[] {
			"##", 
			"##", 
			'#', Block.planks 
		} );		

		RemoveVanillaRecipe( new ItemStack( Block.chest ), new Object[] {
			"###", 
			"# #", 
			"###", 
			'#', Block.planks
		} );
		
		RemoveVanillaShapelessRecipe( new ItemStack( Item.book, 1 ), new Object[] {
			Item.paper, 
			Item.paper, 
			Item.paper, 
			Item.leather
		} );		
		
		RemoveVanillaRecipe( new ItemStack( Block.stoneBrick, 4 ), new Object[] {
			"##", 
			"##", 
			'#', Block.stone 
		} );
		
		// remove smelting. Must remove before adding replacement recipes as only one recipe can be associated with an itemID
		
		FurnaceRecipes.smelting().getSmeltingList().remove( Block.oreIron.blockID );
		FurnaceRecipes.smelting().getSmeltingList().remove( Block.oreGold.blockID );
		FurnaceRecipes.smelting().getSmeltingList().remove( Block.oreDiamond.blockID );
		FurnaceRecipes.smelting().getSmeltingList().remove( Block.sand.blockID );
		FurnaceRecipes.smelting().getSmeltingList().remove( Block.cobblestone.blockID );
		FurnaceRecipes.smelting().getSmeltingList().remove( Item.clay.itemID );
		FurnaceRecipes.smelting().getSmeltingList().remove( Block.wood.blockID );
		FurnaceRecipes.smelting().getSmeltingList().remove( Block.oreEmerald.blockID );
		FurnaceRecipes.smelting().getSmeltingList().remove( Block.netherrack.blockID );
		FurnaceRecipes.smelting().getSmeltingList().remove( Block.oreCoal.blockID );
		FurnaceRecipes.smelting().getSmeltingList().remove( Block.oreRedstone.blockID );
		FurnaceRecipes.smelting().getSmeltingList().remove( Block.oreLapis.blockID );
		FurnaceRecipes.smelting().getSmeltingList().remove( Block.oreNetherQuartz.blockID );
		FurnaceRecipes.smelting().getSmeltingList().remove( Block.glass.blockID );
	}
	
	private static void AddDebugRecipes()
    {
		// Debug Recipes (disable for release)

		/*
		AddVanillaRecipe( new ItemStack( Block.glass, 60, 0 ), new Object[] {
			"#",
			'#', Block.dirt 
		} );
		*/
    }	
}