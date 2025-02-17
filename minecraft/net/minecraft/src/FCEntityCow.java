// FCMOD

package net.minecraft.src;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Iterator;
import java.util.List;

public class FCEntityCow extends EntityCow
{
    protected static final int m_iGotMilkDataWatcherID = 26;
    
    private static final int m_iFullMilkAccumulationCount = FCUtilsMisc.m_iTicksPerGameDay;

	private static final int m_iKickAttackTicksToCooldown = 40;    
	private static final double m_dKickAttackRange = 1.75D;
    public static final int m_iKickAttackDuration = 20;
	public static final double m_dKickAttackTipCollisionWidth = 2.75D;
	public static final double m_dKickAttackTipCollisionHalfWidth = ( m_dKickAttackTipCollisionWidth / 2D );
	public static final double m_dKickAttackTipCollisionHeight = 2D;
	public static final double m_dKickAttackTipCollisionHalfHeight = ( m_dKickAttackTipCollisionHeight / 2D );
    
    private int m_iMilkAccumulationCount = 0;
    
    private int m_iKickAttackCooldownTimer = m_iKickAttackTicksToCooldown;
    
    public int m_iKickAttackInProgressCounter = -1;
    
    public int m_iKickAttackLegUsed = 0;
    
    public FCEntityCow( World world )
    {
        super( world );
        
        tasks.RemoveAllTasks();
        
        tasks.addTask( 0, new EntityAISwimming( this ) );
        tasks.addTask( 1, new FCEntityAIAnimalFlee( this, 0.38F ) );
        tasks.addTask( 2, new EntityAIMate( this, 0.2F ) );
        tasks.addTask( 3, new FCEntityAIMultiTempt( this, 0.25F ) );
        tasks.addTask( 4, new FCEntityAIGraze( this ) );
        tasks.addTask( 5, new FCEntityAIMoveToLooseFood( this, 0.2F ) );
        tasks.addTask( 6, new FCEntityAIMoveToGraze( this, 0.2F ) );
        tasks.addTask( 7, new EntityAIFollowParent( this, 0.25F ) );
        tasks.addTask( 8, new FCEntityAIWanderSimple( this, 0.25F ) );
        tasks.addTask( 9, new EntityAIWatchClosest( this, EntityPlayer.class, 6F ) );
        tasks.addTask( 10, new EntityAILookIdle( this ) );
    }
    
    @Override
    protected void entityInit()
    {
        super.entityInit();
        
        dataWatcher.addObject( m_iGotMilkDataWatcherID, new Byte( (byte)0 ) );
    }
    
    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        
        par1NBTTagCompound.setBoolean( "fcGotMilk", GotMilk() );
        par1NBTTagCompound.setInteger( "fcMilkCount", m_iMilkAccumulationCount ); 
    }
    
    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        
        if ( par1NBTTagCompound.hasKey( "fcGotMilk" ) )
    	{
        	SetGotMilk( par1NBTTagCompound.getBoolean( "fcGotMilk" ) );
    	}
        
        if ( par1NBTTagCompound.hasKey( "fcMilkCount" ) )
    	{
        	m_iMilkAccumulationCount = par1NBTTagCompound.getInteger( "fcMilkCount" );
    	}
    }
    
    @Override
    public boolean isAIEnabled()
    {
    	return !getWearingBreedingHarness();
    }

    @Override
    public int getMaxHealth()
    {
    	return 15;
    }
    
    @Override
    public void onLivingUpdate()
    {
    	UpdateKickAttack();
    	
        super.onLivingUpdate();
        
    }
    
    @Override
    protected void dropFewItems( boolean bKilledByPlayer, int iLootingModifier )
    {
    	
    	dropItem( BPMDefinitions.beefRib.itemID, 1 );
    	
    	if ( !IsStarving() )
    	{
	        int iNumDrops = rand.nextInt( 3 ) + rand.nextInt( 1 + iLootingModifier ) + 1;
	        
	        if ( IsFamished() )
	        {
	        	iNumDrops = iNumDrops / 2;
	        }
	
	        for ( int iTempCount = 0; iTempCount < iNumDrops; ++iTempCount )
	        {
	            dropItem( Item.leather.itemID, 1 );
	        }
	
	        if ( !HasHeadCrabbedSquid() )
	        {
		        iNumDrops = rand.nextInt( 3 ) + 1 + rand.nextInt( 1 + iLootingModifier );
		        
		        if ( IsFamished() )
		        {
		        	iNumDrops = iNumDrops / 2;
		        }
		
		        for ( int iTempCount = 0; iTempCount < iNumDrops; ++iTempCount )
		        {
		            if ( isBurning() )
		            {
		                dropItem( Item.beefCooked.itemID, 1 );
		            }
		            else
		            {
		                dropItem( Item.beefRaw.itemID, 1 );
		            }
		        }
	        }
    	}
    }
    
    @Override
    public boolean isBreedingItem( ItemStack stack )
    {
        return stack.itemID == Item.cake.itemID;
    }

    @Override
    public boolean interact( EntityPlayer player )
    {
        ItemStack stack = player.inventory.getCurrentItem();

        if ( stack != null && stack.itemID == Item.bucketEmpty.itemID )
        {
        	if ( GotMilk() )
        	{
	        	stack.stackSize--;
	        	
	            if ( stack.stackSize <= 0 )
	            {
	                player.inventory.setInventorySlotContents( player.inventory.currentItem, new ItemStack( Item.bucketMilk ) );
	            }
	            else if ( !player.inventory.addItemStackToInventory( new ItemStack( Item.bucketMilk ) ) )
	            {
	                player.dropPlayerItem( new ItemStack( Item.bucketMilk.itemID, 1, 0 ) );
	            }
	
	            attackEntityFrom( DamageSource.generic, 0 );
	            
	            if ( !worldObj.isRemote )
	            {
	            	SetGotMilk( false );
	            	
			        worldObj.playAuxSFX( FCBetterThanWolves.m_iCowMilkedAuxFXID, 
		                MathHelper.floor_double( posX ), (int)posY,
		                MathHelper.floor_double( posZ ), 0 );
	            }
        	}
        	else
        	{
	            attackEntityFrom( DamageSource.causePlayerDamage( player ), 0 );
        	}

            return true;
        }
        
        // skip over EntityCow() super to avoid vanilla milking
        
        return EntityAnimalInteract( player ); 
    }

    @Override
    public void OnGrazeBlock( int i, int j, int k )
    {
    	super.OnGrazeBlock( i, j, k );
    	
    	if ( !getWearingBreedingHarness() )
    	{    	
	        CheckForGrazeSideEffects( i, j, k );
    	}
    }
    
    @Override
    public boolean IsSubjectToHunger()
    {
    	return true;
    }
    
    @Override
    public void OnBecomeFamished()
    {
    	super.OnBecomeFamished();
    	
    	if ( GotMilk() )
    	{
    		SetGotMilk( false );
    	}
    	
    	m_iMilkAccumulationCount = 0;
    }
    
    @Override
    public boolean CanGrazeMycelium()
    {
    	return true;
    }
    
    @Override
    public double getMountedYOffset()
    {
		return (double)height * 1.2D;
    }
    
    @Override
    protected boolean GetCanCreatureTypeBePossessed()
    {
    	return true;
    }
    
	@Override
	protected void GiveBirthAtTargetLocation( EntityAnimal targetMate, double dChildX, double dChildY, double dChildZ )
    {
		// small chance of normal birth when possessed
    	if ( ( IsFullyPossessed() || targetMate.IsFullyPossessed() ) && rand.nextInt( 8 ) != 0 )
    	{
    		if ( worldObj.provider.dimensionId != 1 && worldObj.rand.nextInt( 2 ) == 0 )
    		{
    			BirthMutant( targetMate, dChildX, dChildY, dChildZ );    			
    		}
    		else
    		{
    			StillBirth( targetMate, dChildX, dChildY, dChildZ );
    		}
    	}
    	else
    	{    	
    		super.GiveBirthAtTargetLocation( targetMate, dChildX, dChildY, dChildZ );
    	}
    }
    
    @Override
    public void initCreature()
    {
    	InitHungerWithVariance();
    	
        if ( !isChild() )
        {
        	m_iMilkAccumulationCount = worldObj.rand.nextInt( m_iFullMilkAccumulationCount + 
        		( m_iFullMilkAccumulationCount / 4 ) + 1 );
        	
        	if ( m_iMilkAccumulationCount >= m_iFullMilkAccumulationCount )
        	{
        		m_iMilkAccumulationCount = 0;
        		
            	SetGotMilk( true );
        	}
        }        
    }
    
    @Override
    public boolean IsValidZombieSecondaryTarget( EntityZombie zombie )
    {
    	return true;
    }
    
    @Override
    public FCEntityCow spawnBabyAnimal( EntityAgeable parent )
    {
        return (FCEntityCow) EntityList.createEntityOfType(FCEntityCow.class, worldObj );
    }

    @Override
    protected String getLivingSound()
    {
    	if ( !IsStarving() )
    	{
    		return "mob.cow.say";
    	}
    	else
    	{
    		return "mob.cow.hurt";
    	}
    }

    @Override
    protected void UpdateHungerState()
    {
    	if ( !GotMilk() && IsFullyFed() && !isChild() && !getWearingBreedingHarness() )
    	{
			// producing milk consumes extra food. Hunger will be validated in super method
			
			m_iHungerCountdown--;
			
        	m_iMilkAccumulationCount++;
        	
        	if ( m_iMilkAccumulationCount >= m_iFullMilkAccumulationCount )
        	{
        		SetGotMilk( true );        		
        		m_iMilkAccumulationCount = 0;
        		
		        worldObj.playAuxSFX( FCBetterThanWolves.m_iCowMilkFillAuxFXID, 
		        	MathHelper.floor_double( posX ), (int)posY + 1, 
		        	MathHelper.floor_double( posZ ), 0 );
        	}
    	}

    	// must call super method after extra hunger consumed above to validate
    	
    	super.UpdateHungerState(); 
    }
    
    @Override
    public float KnockbackMagnitude()
    {
    	return 0.3F;
    }
    
	//------------- Class Specific Methods ------------//
    
    public void CheckForGrazeSideEffects( int i, int j, int k )
    {
    	int iTargetBlockID = worldObj.getBlockId( i, j, k );
    	
    	if ( iTargetBlockID == Block.mycelium.blockID )
    	{
    		ConvertToMooshroom();
    	}
    }
    
    public void ConvertToMooshroom()
    {
        int iFXI = MathHelper.floor_double( posX );
        int iFXJ = MathHelper.floor_double( posY ) + 1;
        int iFXK = MathHelper.floor_double( posZ );
        
        int iExtendedFXData = 0;
        
        if ( isChild() )
        {
        	iExtendedFXData = 1;
        }
        
        worldObj.playAuxSFX( FCBetterThanWolves.m_iCowConvertToMooshroomAuxFXID, iFXI, iFXJ, iFXK, iExtendedFXData );
        
        setDead();
        
        FCEntityCow entityMooshroom = (FCEntityCow) EntityList.createEntityOfType(EntityMooshroom.class, worldObj );
        entityMooshroom.setLocationAndAngles( posX, posY, posZ, rotationYaw, rotationPitch );
        entityMooshroom.setEntityHealth( getHealth() );
        entityMooshroom.renderYawOffset = renderYawOffset;
        entityMooshroom.setGrowingAge( getGrowingAge() );
        
        worldObj.spawnEntityInWorld( entityMooshroom );
    }
    
    public boolean GotMilk()
    {
    	byte bGotMilk = dataWatcher.getWatchableObjectByte( m_iGotMilkDataWatcherID );
    	
    	if ( bGotMilk != 0 )
    	{
    		return true;
    	}
    	
    	return false;
    }
    
    protected void SetGotMilk( boolean bGotMilk )
    {    	
    	byte byteValue = 0;
    	
    	if ( bGotMilk )
    	{
    		byteValue = 1;
    	}
    	
        dataWatcher.updateObject( m_iGotMilkDataWatcherID, Byte.valueOf( byteValue ) );
    }
    
	private void UpdateKickAttack()
	{
    	if ( m_iKickAttackInProgressCounter >= 0 )
    	{
    		m_iKickAttackInProgressCounter++;
    		
    		if ( m_iKickAttackInProgressCounter >= m_iKickAttackDuration )
    		{
    			m_iKickAttackInProgressCounter = -1;
    		}
    	}
    	else if ( !worldObj.isRemote ) // attacks are only launched on the server
    	{
            m_iKickAttackCooldownTimer--;
            
    		// check if we should initiate an attack, which only applies if the cow is burning or has a target, which are the same conditions
            // that are used to determine if the cow is panicked and fleeing
            
            if ( isEntityAlive() && !isChild() && !getWearingBreedingHarness() && m_iKickAttackCooldownTimer <= 0 && ( isBurning() || getAITarget() != null ) )
            {
				Vec3 kickCenter = ComputeKickAttackCenter();
				
				AxisAlignedBB tipBox = AxisAlignedBB.getAABBPool().getAABB( 
					kickCenter.xCoord - m_dKickAttackTipCollisionHalfWidth, 
					kickCenter.yCoord - m_dKickAttackTipCollisionHalfHeight, 
					kickCenter.zCoord - m_dKickAttackTipCollisionHalfWidth, 
					kickCenter.xCoord + m_dKickAttackTipCollisionHalfWidth, 
					kickCenter.yCoord + m_dKickAttackTipCollisionHalfHeight, 
					kickCenter.zCoord + m_dKickAttackTipCollisionHalfWidth );
				
		        List potentialCollisionList = worldObj.getEntitiesWithinAABB( EntityLiving.class, tipBox );
		        
		        if ( !potentialCollisionList.isEmpty() )
		        {
            		boolean bAttackLaunched = false;
            		
    				Vec3 lineOfSightOrigin = Vec3.createVectorHelper( posX, posY + ( height / 2F ), posZ );
    				
    		        Iterator collisionIterator = potentialCollisionList.iterator();
    		        
    		        while ( collisionIterator.hasNext() )
    		        {
    		        	EntityLiving tempEntity = (EntityLiving)collisionIterator.next();
    		        	
    		        	if ( !( tempEntity instanceof FCEntityCow ) && tempEntity.isEntityAlive() && tempEntity.ridingEntity != this &&
    		        		CanEntityBeSeenForAttackToCenterOfMass( tempEntity, lineOfSightOrigin ) )
    		        	{
    		        		bAttackLaunched = true;
    		        		
		        			KickAttackHitTarget( tempEntity );
    		        	}
    		        }
    		        
    		        if ( bAttackLaunched )
    		        {
		        		LaunchKickAttack();    		        		
    		        }
		        }
            }
    	}
	}
		
    public boolean CanEntityBeSeenForAttackToCenterOfMass( Entity entity, Vec3 attackOrigin )
    {
        return worldObj.rayTraceBlocks_do_do( attackOrigin, 
        	worldObj.getWorldVec3Pool().getVecFromPool( entity.posX, entity.posY + ( entity.height / 2F ), entity.posZ ), false, true ) == null;
    }
    
	public Vec3 ComputeKickAttackCenter()
	{		
		float fAttackAngle = MathHelper.wrapAngleTo180_float( rotationYaw + 180F );

        double dPosX = (double)( -MathHelper.sin( fAttackAngle / 180.0F * (float)Math.PI ) ) * m_dKickAttackRange;
		double dPosY = height / 2F;
        double dPosZ = (double)( MathHelper.cos( fAttackAngle / 180.0F * (float)Math.PI ) ) * m_dKickAttackRange;
        
		dPosX += posX;		
		dPosY += posY;
		dPosZ += posZ;
		
		return Vec3.createVectorHelper( dPosX, dPosY, dPosZ );
	}	
	
	private void LaunchKickAttack()
	{
    	m_iKickAttackInProgressCounter = 0;
		m_iKickAttackCooldownTimer = m_iKickAttackTicksToCooldown;
		
		TransmitKickAttackToClients();
	}
	
    private void TransmitKickAttackToClients()
    {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream dataStream = new DataOutputStream( byteStream );
        
        try
        {
	        dataStream.writeInt( entityId );
	        dataStream.writeByte( (byte)FCBetterThanWolves.fcCustomEntityEventPacketTypeCowKickAttack );	        
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }        
	        
        Packet250CustomPayload packet = new Packet250CustomPayload( FCBetterThanWolves.fcCustomPacketChannelCustomEntityEvent, byteStream.toByteArray() );
        
        FCUtilsWorld.SendPacketToAllPlayersTrackingEntity( (WorldServer)worldObj, this, packet );
    }
    
    public void OnClientNotifiedOfKickAttack()
    {
    	m_iKickAttackInProgressCounter = 0;
    	
    	m_iKickAttackLegUsed = rand.nextInt( 2 );
    	
        worldObj.playSound( posX, posY, posZ, "random.bow", 1F, 
        	( rand.nextFloat() - rand.nextFloat() ) * 0.2F + 0.5F );    
    }
    
	private void KickAttackHitTarget( Entity hitEntity )
	{
		DamageSource cowSource = DamageSource.causeMobDamage( this );
		
		if ( hitEntity.attackEntityFrom( cowSource, 7 ) )
		{
            if ( isBurning() && rand.nextFloat() < 0.6F )
            {
                hitEntity.setFire( 4 );
            }
            
			hitEntity.OnKickedByCow( this );
		}
	}
	
    private boolean BirthMutant( EntityAnimal targetMate, 
    	double dChildX, double dChildY, double dChildZ )
    {
    	int iRandomFactor = rand.nextInt( 20 );
    	
    	if ( iRandomFactor == 0 )
    	{
            FCEntityCaveSpider childEntity = (FCEntityCaveSpider) EntityList.createEntityOfType(FCEntityCaveSpider.class, worldObj);
    		
            if ( childEntity != null )
            {
            	childEntity.setLocationAndAngles( dChildX, dChildY, dChildZ, rotationYaw, rotationPitch );
                
                worldObj.spawnEntityInWorld( childEntity );        
            }
    	}
    	else if ( iRandomFactor < 4 )
    	{
    		for ( int iTempCount = 0; iTempCount < 10; iTempCount++ )
    		{
	            FCEntityBat childEntity = (FCEntityBat) EntityList.createEntityOfType(FCEntityBat.class, worldObj);
	    		
	            if ( childEntity != null )
	            {
	            	childEntity.setLocationAndAngles( dChildX, dChildY, dChildZ, rotationYaw, rotationPitch );
	                
	                worldObj.spawnEntityInWorld( childEntity );        
	            }
    		}
    	}
    	else if ( iRandomFactor < 7 )
    	{
    		for ( int iTempCount = 0; iTempCount < 5; iTempCount++ )
    		{
	            EntitySilverfish childEntity = (EntitySilverfish) EntityList.createEntityOfType(EntitySilverfish.class, worldObj);
	    		
	            if ( childEntity != null )
	            {
	            	childEntity.setLocationAndAngles( dChildX, dChildY, dChildZ, rotationYaw, rotationPitch );
	                
	                worldObj.spawnEntityInWorld( childEntity );        
	            }
    		}
    	}
    	else
    	{
            FCEntitySquid childEntity = (FCEntitySquid) EntityList.createEntityOfType(FCEntitySquid.class, worldObj);
    		
            if ( childEntity != null )
            {
            	childEntity.setLocationAndAngles( dChildX, dChildY, dChildZ, rotationYaw, rotationPitch );
                
                worldObj.spawnEntityInWorld( childEntity );        
            }
    	}
    	
    	return true;        
    }
    
    protected void StillBirth( EntityAnimal targetMate, double dChildX, double dChildY, double dChildZ )
    {
    	// just a copy of GiveBirthAtTargetLocation() from parent class that kills the baby after birth
    	
        EntityAgeable childEntity = createChild( targetMate );

        if ( childEntity != null )
        {
            childEntity.setGrowingAge( -childEntity.GetTicksForChildToGrow() );
            
        	childEntity.setLocationAndAngles( dChildX, dChildY, dChildZ, rotationYaw, rotationPitch );
            
            worldObj.spawnEntityInWorld( childEntity );
            
            childEntity.attackEntityFrom( DamageSource.generic, 20 );
        }        
    }
    
	//----------- Client Side Functionality -----------//
    
	@Override
    public String getTexture()
    {
    	if ( getWearingBreedingHarness() )
    	{
			return "/btwmodtex/fc_mr_cow.png";
    	}
    	
    	int iHungerLevel = GetHungerLevel();
    	
    	if ( iHungerLevel == 1 )
    	{
			return "/btwmodtex/fcCowFamished.png";
    	}
    	else if ( iHungerLevel == 2 )
    	{
			return "/btwmodtex/fcCowStarving.png";
    	}
    	
        return super.getTexture();
    }
}
