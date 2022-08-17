package net.minecraft.src;

public class BPMRecipes extends FCRecipes
{
	private static final int m_iIgnoreMetadata = FCUtilsInventory.m_iIgnoreMetadata;
	
	public static final BPMRecipes instance = new BPMRecipes();
	
	private BPMRecipes() 
	{
	}

	public static void addRecipes() 
	{
		addFoodRecipes();
		addToolRecipes();
		addBlockRecipes();
		addMiscRecipes();
	}
	
	public static void addToolRecipes() 
	{
		CraftingManager.getInstance().getRecipeList().add( new BPMRecipesLeatherCutting() );
		CraftingManager.getInstance().getRecipeList().add( new BPMRecipesRibCutting() );
		CraftingManager.getInstance().getRecipeList().add( new BPMRecipesKnifeReturn() );
		CraftingManager.getInstance().getRecipeList().add( new BPMRecipesRibCracking() );
		
		AddMillStoneRecipe( new ItemStack( Item.dyePowder, 6, 15 ), new ItemStack( BPMDefinitions.rib, 1) );
		
		FCRecipes.AddShapelessRecipe(
				new ItemStack(BPMDefinitions.flintKnapping, 1, 10),
				new ItemStack[] { new ItemStack(Item.flint), new ItemStack(FCBetterThanWolves.fcItemStone) });
		
		FCRecipes.AddShapelessRecipe(
				new ItemStack(BPMDefinitions.ironKnife, 1),
				new ItemStack[] { new ItemStack(FCBetterThanWolves.fcItemNuggetIron), new ItemStack(FCBetterThanWolves.fcItemNuggetIron) });
		
		FCRecipes.AddShapelessRecipe(new ItemStack(BPMDefinitions.bonePickaxe, 1), new ItemStack[] {
				new ItemStack(BPMDefinitions.rib), new ItemStack(Item.silk), new ItemStack(Item.stick) });
		FCRecipes.AddShapelessRecipe(new ItemStack(BPMDefinitions.bonePickaxe, 1),
				new ItemStack[] { new ItemStack(BPMDefinitions.rib),
						new ItemStack(FCBetterThanWolves.fcItemHempFibers), new ItemStack(Item.stick) });
		
		AddRecipe(new ItemStack(BPMDefinitions.stoneHoeNew, 1),
				new Object[] { "XI", "SI", " I", Character.valueOf('X'), FCBetterThanWolves.fcItemStone,
						Character.valueOf('I'), Item.stick, Character.valueOf('S'), Item.silk, });
		AddRecipe(new ItemStack(BPMDefinitions.stoneHoeNew, 1),
				new Object[] { "IX", "IS", "I ", Character.valueOf('X'), FCBetterThanWolves.fcItemStone,
						Character.valueOf('I'), Item.stick, Character.valueOf('S'), Item.silk, });
		AddRecipe(new ItemStack(BPMDefinitions.stoneHoeNew, 1),
				new Object[] { "XI ", " IS", " I ", Character.valueOf('X'), FCBetterThanWolves.fcItemStone,
						Character.valueOf('I'), Item.stick, Character.valueOf('S'), Item.silk, });
		AddRecipe(new ItemStack(BPMDefinitions.stoneHoeNew, 1),
				new Object[] { " IX", "SI ", " I ", Character.valueOf('X'), FCBetterThanWolves.fcItemStone,
						Character.valueOf('I'), Item.stick, Character.valueOf('S'), Item.silk, });
		AddRecipe(new ItemStack(BPMDefinitions.stoneHoeNew, 1),
				new Object[] { "XIS", " I ", " I ", Character.valueOf('X'), FCBetterThanWolves.fcItemStone,
						Character.valueOf('I'), Item.stick, Character.valueOf('S'), Item.silk, });
		
		FCRecipes.AddShapelessRecipe(new ItemStack(Item.plateLeather, 1),
				new ItemStack[] { new ItemStack(FCBetterThanWolves.fcItemLeatherCut),
						new ItemStack(FCBetterThanWolves.fcItemLeatherCut),
						new ItemStack(FCBetterThanWolves.fcItemLeatherCut),
						new ItemStack(FCBetterThanWolves.fcItemLeatherCut) });
		FCRecipes.AddShapelessRecipe(new ItemStack(Item.legsLeather, 1),
				new ItemStack[] { new ItemStack(FCBetterThanWolves.fcItemLeatherCut),
						new ItemStack(FCBetterThanWolves.fcItemLeatherCut),
						new ItemStack(FCBetterThanWolves.fcItemLeatherCut) });
		FCRecipes.AddShapelessRecipe(new ItemStack(Item.bootsLeather, 1),
				new ItemStack[] { new ItemStack(FCBetterThanWolves.fcItemLeatherCut),
						new ItemStack(FCBetterThanWolves.fcItemLeatherCut) });
		FCRecipes.AddShapelessRecipe(new ItemStack(Item.helmetLeather, 1),
				new ItemStack[] { new ItemStack(FCBetterThanWolves.fcItemLeatherCut) });
		
		FCRecipes.AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemArmorTannedChest, 1),
				new ItemStack[] { new ItemStack(FCBetterThanWolves.fcItemTannedLeatherCut),
						new ItemStack(FCBetterThanWolves.fcItemTannedLeatherCut),
						new ItemStack(FCBetterThanWolves.fcItemTannedLeatherCut),
						new ItemStack(FCBetterThanWolves.fcItemTannedLeatherCut) });	
		FCRecipes.AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemArmorTannedLeggings, 1),
				new ItemStack[] { new ItemStack(FCBetterThanWolves.fcItemTannedLeatherCut),
						new ItemStack(FCBetterThanWolves.fcItemTannedLeatherCut),
						new ItemStack(FCBetterThanWolves.fcItemTannedLeatherCut) });
		FCRecipes.AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemArmorTannedBoots, 1),
				new ItemStack[] { new ItemStack(FCBetterThanWolves.fcItemTannedLeatherCut),
						new ItemStack(FCBetterThanWolves.fcItemTannedLeatherCut) });
		FCRecipes.AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemArmorTannedHelm, 1),
				new ItemStack[] { new ItemStack(FCBetterThanWolves.fcItemTannedLeatherCut) });
		
		FCRecipes.AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockTorchFiniteUnlit, 1),
				new ItemStack[] { new ItemStack(Item.coal), new ItemStack(BPMDefinitions.branch) });

		FCRecipes.AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemFireStarterSticks, 1), new ItemStack[] {
				new ItemStack(BPMDefinitions.branch), new ItemStack(BPMDefinitions.branch) });
		FCRecipes.AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemFireStarterSticks, 1),
				new ItemStack[] { new ItemStack(BPMDefinitions.branch), new ItemStack(Item.stick) });
		FCRecipes.AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemChiselWood, 1),
				new ItemStack[] { new ItemStack(BPMDefinitions.branch) });

		FCRecipes.AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemFireStarterBow, 1),
				new ItemStack[] { new ItemStack(BPMDefinitions.branch), new ItemStack(BPMDefinitions.branch),
						new ItemStack(Item.silk) });
		FCRecipes.AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemFireStarterBow, 1), new ItemStack[] {
				new ItemStack(BPMDefinitions.branch), new ItemStack(Item.stick), new ItemStack(Item.silk) });

		// all them recipes be like (LMAO!!! CAMPFIRE!!!!)
		FCRecipes.AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockCampfireUnlit, 1),
				new ItemStack[] { new ItemStack(BPMDefinitions.branch), new ItemStack(Item.stick),
						new ItemStack(Item.stick), new ItemStack(Item.stick) });
		FCRecipes.AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockCampfireUnlit, 1),
				new ItemStack[] { new ItemStack(BPMDefinitions.branch), new ItemStack(BPMDefinitions.branch),
						new ItemStack(Item.stick), new ItemStack(Item.stick) });
		FCRecipes.AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockCampfireUnlit, 1),
				new ItemStack[] { new ItemStack(BPMDefinitions.branch), new ItemStack(BPMDefinitions.branch),
						new ItemStack(BPMDefinitions.branch), new ItemStack(Item.stick) });
		FCRecipes.AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockCampfireUnlit, 1),
				new ItemStack[] { new ItemStack(BPMDefinitions.branch), new ItemStack(BPMDefinitions.branch),
						new ItemStack(BPMDefinitions.branch), new ItemStack(BPMDefinitions.branch) });
		

		FCRecipes.AddShapelessRecipe(new ItemStack(BPMDefinitions.deathClub, 1), new ItemStack[] {
				new ItemStack(BPMDefinitions.rib), new ItemStack(BPMDefinitions.rib), new ItemStack(Item.stick), new ItemStack(Item.stick) });
		
	}
	
	public static void addFoodRecipes()
	{
		FurnaceRecipes.smelting().addSmelting(BPMDefinitions.beefRib.itemID,
				new ItemStack(BPMDefinitions.cookedBeefRib), 0);
		
		AddCauldronRecipe(new ItemStack(BPMDefinitions.cookedBeefRib, 1),
				new ItemStack[] { new ItemStack(BPMDefinitions.beefRib, 1), });
		
		FCRecipes.AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemMeatCured, 2),
				new ItemStack[] { new ItemStack(FCBetterThanWolves.fcItemNitre), new ItemStack(FCBetterThanWolves.fcItemNitre),
						new ItemStack(BPMDefinitions.beefRib) });
		
	//Instead, use a Recipes type class	
//		FCRecipes.AddShapelessRecipe(new ItemStack(Item.beefRaw, 1),
//				new ItemStack[] { new ItemStack(BPMDefinitions.beefRib), new ItemStack(FCBetterThanWolves.fcItemRefinedAxe),
//						new ItemStack(BPMDefinitions.beefRib) });
//		FCRecipes.AddShapelessRecipe(new ItemStack(Item.beefRaw, 1),
//				new ItemStack[] { new ItemStack(BPMDefinitions.beefRib), new ItemStack(FCBetterThanWolves.fcItemBattleAxe),
//						new ItemStack(BPMDefinitions.beefRib) });
//		FCRecipes.AddShapelessRecipe(new ItemStack(Item.beefRaw, 1),
//				new ItemStack[] { new ItemStack(BPMDefinitions.beefRib), new ItemStack(Item.axeDiamond),
//						new ItemStack(BPMDefinitions.beefRib) });
//		FCRecipes.AddShapelessRecipe(new ItemStack(Item.beefRaw, 1),
//				new ItemStack[] { new ItemStack(BPMDefinitions.beefRib), new ItemStack(Item.axeIron),
//						new ItemStack(BPMDefinitions.beefRib) });
//		FCRecipes.AddShapelessRecipe(new ItemStack(Item.beefRaw, 1),
//				new ItemStack[] { new ItemStack(BPMDefinitions.beefRib), new ItemStack(Item.axeGold),
//						new ItemStack(BPMDefinitions.beefRib) });
//		FCRecipes.AddShapelessRecipe(new ItemStack(Item.beefRaw, 1),
//				new ItemStack[] { new ItemStack(BPMDefinitions.beefRib), new ItemStack(Item.axeStone),
//						new ItemStack(BPMDefinitions.beefRib) });
//		
//		FCRecipes.AddShapelessRecipe(new ItemStack(Item.beefRaw, 1),
//				new ItemStack[] { new ItemStack(BPMDefinitions.beefRib), new ItemStack(Item.swordIron),
//						new ItemStack(BPMDefinitions.beefRib) });
//		FCRecipes.AddShapelessRecipe(new ItemStack(Item.beefRaw, 1),
//				new ItemStack[] { new ItemStack(BPMDefinitions.beefRib), new ItemStack(Item.swordDiamond),
//						new ItemStack(BPMDefinitions.beefRib) });
	
		
	}
	
	public static void addBlockRecipes() 
	{
		AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockWorkbench, 1), new Object[] // ITS BACK BABY
		{ "###", "#X#", "###", '#', Block.planks, 'X', Item.ingotIron });
		
		FCRecipes.addSawRecipe(new ItemStack(FCBetterThanWolves.fcItemRawMysteryMeat, 9),
				BPMDefinitions.meatCube);
		
		addPistonPackingRecipe(BPMDefinitions.meatCube,
				new ItemStack(FCBetterThanWolves.fcItemRawMysteryMeat, 9));
	}
	
    public static void RemoveStokedCauldronRecipe( ItemStack itemStack, ItemStack inputStacks[] )
    {
        FCCraftingManagerCauldronStoked.getInstance().RemoveRecipe( itemStack, inputStacks );
    }
	
	public static void addMiscRecipes()
	{
        		RemoveStokedCauldronRecipe(
        			new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
        			new ItemStack[] {
        				new ItemStack( Item.leather, 1 ) 
                } );
        	
        		RemoveStokedCauldronRecipe(
        			new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
        			new ItemStack[] {
        				new ItemStack( Item.leather, 1 ) 
        		} );
                
            	RemoveStokedCauldronRecipe(
        			new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
        			new ItemStack[] {
        				new ItemStack( FCBetterThanWolves.fcItemTannedLeather, 1 ) 
        		} );
                    
            	RemoveStokedCauldronRecipe(
        			new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
        			new ItemStack[] {
        				new ItemStack( FCBetterThanWolves.fcItemScouredLeather, 1 ) 
        		} );
                
            	RemoveStokedCauldronRecipe(
        			new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
        			new ItemStack[] {
        				new ItemStack( FCBetterThanWolves.fcItemScouredLeatherCut, 2 ) 
        		} );
                
                RemoveStokedCauldronRecipe(
        			new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
        			new ItemStack[] {
        				new ItemStack( FCBetterThanWolves.fcItemLeatherCut, 2 ) 
        		} );
                
                RemoveStokedCauldronRecipe(
        			new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
        			new ItemStack[] {
        				new ItemStack( FCBetterThanWolves.fcItemTannedLeatherCut, 2 ) 
        		} );
                
                RemoveStokedCauldronRecipe(
        			new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
        			new ItemStack[] {
        				new ItemStack( FCBetterThanWolves.fcItemBelt, 2 ) 
        		} );

                RemoveStokedCauldronRecipe(
        			new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
        			new ItemStack[] {
        				new ItemStack( FCBetterThanWolves.fcItemStrap, 8 ) 
        		} );
        		
                RemoveStokedCauldronRecipe(
        			new ItemStack( FCBetterThanWolves.fcItemGlue, 2 ), 
        			new ItemStack[] {
        				new ItemStack( Item.helmetLeather, 1, m_iIgnoreMetadata ) 
        		} );
                
                RemoveStokedCauldronRecipe(
        			new ItemStack( FCBetterThanWolves.fcItemGlue, 4 ), 
        			new ItemStack[] {
        				new ItemStack( Item.plateLeather, 1, m_iIgnoreMetadata ) 
        		} );
                
                RemoveStokedCauldronRecipe(
        			new ItemStack( FCBetterThanWolves.fcItemGlue, 3 ), 
        			new ItemStack[] {
        				new ItemStack( Item.legsLeather, 1, m_iIgnoreMetadata ) 
        		} );
                
                RemoveStokedCauldronRecipe(
        			new ItemStack( FCBetterThanWolves.fcItemGlue, 2 ), 
        			new ItemStack[] {
        				new ItemStack( Item.bootsLeather, 1, m_iIgnoreMetadata ) 
        		} );
                
                RemoveStokedCauldronRecipe(
        			new ItemStack( FCBetterThanWolves.fcItemGlue, 2 ), 
        			new ItemStack[] {
        				new ItemStack( Item.saddle, 1 ) 
        		} );
        	
                RemoveStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemGlue, 2 ), 
				new ItemStack[] {
					new ItemStack( Item.leather, 1 ) 
				} );
                
                RemoveStokedCauldronRecipe(
            			new ItemStack( FCBetterThanWolves.fcItemGlue, 2 ), 
            			new ItemStack[] {
            				new ItemStack( FCBetterThanWolves.fcItemArmorTannedHelm, 1, m_iIgnoreMetadata ) 
            		} );
                    
                RemoveStokedCauldronRecipe(
            			new ItemStack( FCBetterThanWolves.fcItemGlue, 4 ), 
            			new ItemStack[] {
            				new ItemStack( FCBetterThanWolves.fcItemArmorTannedChest, 1, m_iIgnoreMetadata ) 
            		} );
                    
                RemoveStokedCauldronRecipe(
            			new ItemStack( FCBetterThanWolves.fcItemGlue, 3 ), 
            			new ItemStack[] {
            				new ItemStack( FCBetterThanWolves.fcItemArmorTannedLeggings, 1, m_iIgnoreMetadata ) 
            		} );
                    
                RemoveStokedCauldronRecipe(
            			new ItemStack( FCBetterThanWolves.fcItemGlue, 2 ), 
            			new ItemStack[] {
            				new ItemStack( FCBetterThanWolves.fcItemArmorTannedBoots, 1, m_iIgnoreMetadata ) 
            		} );
                
                RemoveStokedCauldronRecipe(
        				new ItemStack( FCBetterThanWolves.fcItemTallow, 1 ), 
        				new ItemStack[] {
        					new ItemStack( Item.porkCooked, 1 ) 
        			} );
                        
                RemoveStokedCauldronRecipe(
        				new ItemStack( FCBetterThanWolves.fcItemTallow, 1 ), 
        				new ItemStack[] {
        					new ItemStack( Item.porkRaw, 1 ) 
        			} );
	        
                //new rendering recipes
    	    AddStokedCauldronRecipe(
    			new ItemStack( FCBetterThanWolves.fcItemGlue, 2 ), 
    			new ItemStack[] {
    				new ItemStack( Item.leather, 1 ) 
    		} );                
                
	        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemGlue, 2 ), 
				new ItemStack[] {
					new ItemStack( FCBetterThanWolves.fcItemTannedLeather, 1 ) 
			} );  
	        
	        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemGlue, 2 ), 
				new ItemStack[] {
					new ItemStack( FCBetterThanWolves.fcItemScouredLeather, 1 ) 
			} );
	        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
				new ItemStack[] {
					new ItemStack( FCBetterThanWolves.fcItemScouredLeatherCut, 1 ) 
			} );
	    
	        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
				new ItemStack[] {
					new ItemStack( FCBetterThanWolves.fcItemLeatherCut, 1 ) 
			} );
	        
	        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
				new ItemStack[] {
					new ItemStack( FCBetterThanWolves.fcItemTannedLeatherCut, 1 ) 
			} );
	        
	        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
				new ItemStack[] {
					new ItemStack( FCBetterThanWolves.fcItemBelt, 1 ) 
			} );

	        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemGlue, 2 ), 
				new ItemStack[] {
					new ItemStack( FCBetterThanWolves.fcItemStrap, 8 ) 
			} );
			
			
	        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
				new ItemStack[] {
					new ItemStack( Item.helmetLeather, 1, m_iIgnoreMetadata ) 
			} );
	        
	        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemGlue, 4 ), 
				new ItemStack[] {
					new ItemStack( Item.plateLeather, 1, m_iIgnoreMetadata ) 
			} );
	        
	        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemGlue, 3 ), 
				new ItemStack[] {
					new ItemStack( Item.legsLeather, 1, m_iIgnoreMetadata ) 
			} );
	        
	        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemGlue, 2 ), 
				new ItemStack[] {
					new ItemStack( Item.bootsLeather, 1, m_iIgnoreMetadata ) 
			} );
	        
	        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemGlue, 2 ), 
				new ItemStack[] {
					new ItemStack( FCBetterThanWolves.fcItemStrap, 8 ) 
			} );
			
			//tanned
	        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemGlue, 1 ), 
				new ItemStack[] {
					new ItemStack( FCBetterThanWolves.fcItemArmorTannedHelm, 1, m_iIgnoreMetadata ) 
			} );
	        
	        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemGlue, 4 ), 
				new ItemStack[] {
					new ItemStack( FCBetterThanWolves.fcItemArmorTannedChest, 1, m_iIgnoreMetadata ) 
			} );
	        
	        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemGlue, 3 ), 
				new ItemStack[] {
					new ItemStack( FCBetterThanWolves.fcItemArmorTannedLeggings, 1, m_iIgnoreMetadata ) 
			} );
	        
	        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemGlue, 2 ), 
				new ItemStack[] {
					new ItemStack( FCBetterThanWolves.fcItemArmorTannedBoots, 1, m_iIgnoreMetadata ) 
			} );
	        
	        AddStokedCauldronRecipe(
				new ItemStack( FCBetterThanWolves.fcItemGlue, 4 ), //changed to 4 
				new ItemStack[] {
					new ItemStack( Item.saddle, 1 ) 
			} );
	        
	        AddStokedCauldronRecipe(
					new ItemStack( FCBetterThanWolves.fcItemTallow, 2 ), 
					new ItemStack[] {
						new ItemStack( Item.porkCooked, 1 ) 
				} );
	                
	        AddStokedCauldronRecipe(
					new ItemStack( FCBetterThanWolves.fcItemTallow, 2 ), 
					new ItemStack[] {
						new ItemStack( Item.porkRaw, 1 ) 
				} );
	}
	
	
}
