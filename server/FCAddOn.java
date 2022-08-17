// FCMOD

package net.minecraft.src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.FCAddOnHandler.DependencyType;

public abstract class FCAddOn {
	protected String addonName;
	protected String versionString;
	protected String prefix;

	protected boolean isRequiredClientAndServer = false;
	protected boolean shouldVersionCheck = false;

	private boolean awaitingLoginAck = false;
	private int ticksSinceAckRequested = 0;
	private static final int maxTicksForAckWait = 50;

	public String addonCustomPacketChannelVersionCheck;
	public String addonCustomPacketChannelVersionCheckAck;

	private ArrayList<String> configProperties = new ArrayList();
	private Map<String, String> configPropertyDefaults = new HashMap();
	private Map<String, String> configPropertyComments = new HashMap();
	
	private Map<FCAddOn, FCAddOnHandler.DependencyType> dependencyList = new HashMap();
	
	//Used for dependency mapping, should not be otherwise touched
	public final Set<FCAddOn> dependents = new HashSet();
	public boolean hasBeenInitialized = false;

	protected FCAddOn() {
		FCAddOnHandler.AddMod(this);
	}

	/**
	 * @param addonName Used for display in version checking
	 * @param versionString Used for version checking
	 * @param prefix Used for translations and packet channels
	 */
	public FCAddOn(String addonName, String versionString, String prefix) {
		this();
		this.addonName = addonName;
		this.versionString = versionString;
		this.prefix = prefix;
		this.addonCustomPacketChannelVersionCheck = prefix + "|VC";
		this.addonCustomPacketChannelVersionCheckAck = prefix + "|VC_Ack";
		this.shouldVersionCheck = true;
		this.isRequiredClientAndServer = true;
	}

	//------ Override Methods ------//
	/*
	 * These are methods which should be overridden 
	 * to add functionality to your addon
	 */
	
	public void PreInitialize() {}

	public abstract void Initialize();

	public void PostInitialize() {}

	public void OnLanguageLoaded( StringTranslate translator ) {}

	/**
	 * Where the addon should handle the values read from config files
	 * This is called after Initialize() is called
	 * @param propertyValues Key-value pair for each property which has been registered
	 */
	public void handleConfigProperties(Map<String, String> propertyValues) {}
	
	/**
	 * Called when a player joins the world
	 */
	public void serverPlayerConnectionInitialized(NetServerHandler serverHandler, EntityPlayerMP playerMP) {}

	/**
	 * Returns true if the packet has been processed, false otherwise
	 */    
	public boolean ServerCustomPacketReceived(NetServerHandler handler, Packet250CustomPayload packet) {
		return false;
	}

	/**
	 * Used for storing custom information in the save file
	 * Has hooks to save information per dimension or globally
	 * @return
	 */
	public FCAddOnUtilsWorldData createWorldData() {
		return null;
	}
	
	/**
	 * Called when decorating a chunk (adding trees, ores, etc) to allow addons to add their own generators
	 * Look in BiomeDecorator for guidance in how to create and use a generator for those things
	 * @param decorator The decorator instance
	 * @param world The current world
	 * @param rand The random number generator. Always use this generator for deterministic generation.
	 * @param x 
	 * @param y
	 * @param biome The biome being decorated. Biomes during decoration are lower resolution, only being caluclated per chunk not per block
	 */
	public void decorateWorld(FCIBiomeDecorator decorator, World world, Random rand, int x, int y, BiomeGenBase biome) {}
	
	//------ API Methods ------//
	/*
	 * These are methods which should be called from
	 * your addon to define behavior
	 */

	/**
	 * Registers a property to load via the config
	 * This should be done either PreInitialize() as properties are read between pre-initialization and initialization
	 * @param propertyName The name for the property
	 * @param defaultValue The default value. If the config file cannot be found, one will be generated and populated with the provided default
	 * @param comment Comment to show for the property (optional through overload)
	 */
	protected void registerProperty(String propertyName, String defaultValue, String comment) {
		if (!configPropertyDefaults.containsKey(propertyName)) {
			configProperties.add(propertyName);
			configPropertyDefaults.put(propertyName, defaultValue);
			configPropertyComments.put(propertyName, comment);
		}
		else {
			FCAddOnHandler.LogWarning("Cannot add config property \"" + propertyName + "\" for " + addonName + " because a property with that name has already been registered for this addon");
		}
	}
	
	protected void registerProperty(String propertyName, String defaultValue) {
		this.registerProperty(propertyName, defaultValue, "");
	}

	public String getName() {
		return this.addonName;
	}

	public String getVersionString() {
		return this.versionString;
	}
	
	/*
	public DependencyType getDependencyForAddon(FCAddOn addon) {
		if (addon == FCBetterThanWolves.m_instance) {
			return DependencyType.LOAD_AFTER;
		}
		
		DependencyType dependencyType = this.dependencyList.get(addon);
		
		if (dependencyType != null) {
			return dependencyType;
		}
		else {
			return DependencyType.NO_DEPENDENCY;
		}
	}
	
	public boolean setDependencyForAddon(String addonName, FCAddOnHandler.DependencyType dependencyType) {
		if (FCAddOnHandler.isModInstalled(addonName)) {
			FCAddOn addon = FCAddOnHandler.getModByName(addonName);
			this.setDependencyForAddon(addon, dependencyType);
			
			return true;
		}
		else {
			return false;
		}
	}
	
	public void setDependencyForAddon(FCAddOn addon, FCAddOnHandler.DependencyType dependencyType) {
		this.dependencyList.put(addon, dependencyType);
	}
	*/

	//------ Internal Methods ------//
	/*
	 * These methods are used for internal processing and should
	 * not be touched unless you know what you're doing
	 */
	
	/**
	 * Loads the registered properties
	 * Addons should not need to override this but may if they would like to implement custom property handling
	 * @return A map of the property names paired with the value read from them. Returns null if no properties were registered or if there was a problem loading the file
	 */
	public Map<String, String> loadConfigProperties() {
		if (this.configPropertyDefaults.size() == 0) {
			return null;
		}
		
		String filename = this.prefix + ".properties";
		BufferedReader fileIn = null;

		try {
			fileIn = new BufferedReader(new FileReader("config/" + filename));
		} catch (FileNotFoundException e) {
			//Generate file if it does not exist
			try {
				Files.createDirectories(Paths.get("config"));

				File config = new File("config/" + filename);
				config.createNewFile();
				fileIn = new BufferedReader(new FileReader(config));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		if (fileIn != null) {
			Map<String, String> propertyValues = new HashMap();

			String line;
			
			//Reads properties from the config file
			try {
				while ((line = fileIn.readLine()) != null) {
					if (line.startsWith("#"))
						continue;
					
					String[] lineSplit = line.split("=");
					
					if (configPropertyDefaults.containsKey(lineSplit[0])) {
						propertyValues.put(lineSplit[0], lineSplit[1]);
					}
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			
			for (String propertyName : configProperties) {
				if (!propertyValues.containsKey(propertyName)) {
					propertyValues.put(propertyName, configPropertyDefaults.get(propertyName));
				}
			}

			try {
				fileIn.close();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			
			return propertyValues;
		}
		else {
			return null;
		}
	}
	
	/**
	 * Rewrites the existing config file to account for missing options
	 * Does a complete rewrite instead of appending in order to maintain option order
	 */
	public void repopulateConfigFile(Map<String, String> propertyValues) {
		String filename = this.prefix + ".properties";
		File config = new File("config/" + filename);
		
		try {
			BufferedWriter writer = Files.newBufferedWriter(config.toPath(), StandardOpenOption.TRUNCATE_EXISTING);
			
			for (String propertyName : this.configProperties) {
				String comment = configPropertyComments.get(propertyName);
				
				if (!comment.equals("")) {
					writer.write("\n# " + comment + "\n");
				}
				
				String propertyValue;
				
				if (propertyValues.containsKey(propertyName)) {
					propertyValue = propertyValues.get(propertyName); 
				}
				else {
					propertyValue = configPropertyDefaults.get(propertyName);
				}
				
				writer.write(propertyName + "=" + propertyValue + "\n");
			}
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Handles version checking
	 */
	public void sendVersionCheckToClient(NetServerHandler serverHandler, EntityPlayerMP playerMP) {
		if (!MinecraftServer.getServer().isSinglePlayer()) {
			//AARON
			FCUtilsWorld.SendPacketToPlayer(serverHandler, new Packet3Chat("\u00a7f" + addonName + " " + versionString));
			if (shouldVersionCheck) {
				ByteArrayOutputStream byteArrayOutput = new ByteArrayOutputStream();
				DataOutputStream dataOutput = new DataOutputStream(byteArrayOutput);

				try {
					dataOutput.writeUTF(versionString);
				}
				catch (Exception var9) {
					var9.printStackTrace();
				}

				Packet250CustomPayload var4 = new Packet250CustomPayload(addonCustomPacketChannelVersionCheck, byteArrayOutput.toByteArray());
				FCUtilsWorld.SendPacketToPlayer(serverHandler, var4);
				awaitingLoginAck = true;
			}
		}
		else {
			//AARON
			FCUtilsWorld.SendPacketToPlayer(serverHandler, new Packet3Chat("\u00a7f" + addonName + " " + versionString));		}
	}

	/**
	 * Called when client ack packet is received by the NetServerHandler
	 * If overriding, make sure to make a call to the super method if you want to maintain version checking
	 * @return true if packet was handled, false otherwise
	 */
	public boolean serverAckPacketReceived(NetServerHandler serverHandler, Packet250CustomPayload packet) {
		if (addonCustomPacketChannelVersionCheckAck.equals(packet.channel)) {
			FCUtilsWorld.SendPacketToPlayer(serverHandler, new Packet3Chat("\u00a7f" + addonName + " version check successful."));
			awaitingLoginAck = false;
			ticksSinceAckRequested = 0;
		}

		return false;
	}

	/**
	 * @return whether the server is awaiting the client's response to the version check
	 */
	public boolean getAwaitingLoginAck() {
		return awaitingLoginAck;
	}

	public void incrementTicksSinceAckRequested() {
		ticksSinceAckRequested++;
	}

	public boolean handleAckCheck() {
		if (ticksSinceAckRequested > maxTicksForAckWait) {
			awaitingLoginAck = false;
			ticksSinceAckRequested = 0;
			return false;
		}

		return true;
	}

	public String GetLanguageFilePrefix() {
		return this.prefix;
	}
}