package tk.mcvier3ck.moreenergy;

import java.util.List;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tk.mcvier3ck.moreenergy.block.BlockRegistry;
import tk.mcvier3ck.moreenergy.item.ItemRegistry;
import tk.mcvier3ck.moreenergy.net.GuiOpenMessage;
import tk.mcvier3ck.moreenergy.proxy.CommonProxy;
import tk.mcvier3ck.moreenergy.tileentity.TileEntityRegistry;

@Mod(modid = "MoreEnergy", name = "MoreEnergy", version = "1.0.0")
public class MoreEnergy {

	public static final String VERSION = "1.0.0";

	@Instance(value = "MoreEnergy")
	public static MoreEnergy instance;

	@SidedProxy(clientSide = "tk.mcvier3ck.moreenergy.proxy.ClientProxy", serverSide = "tk.mcvier3ck.moreenergy.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static final int EnergyStorageID = 0;
	public static final int EnergyInfuserID = 1;
	public static final int ArmorID = 2;
	public static final int ArmorTier1ID = 3;
	public static final int ArmorTier2ID = 4;
	public static final int ArmorTier3ID = 5;

	public static CreativeTabs tabMoreEnergy = new CreativeTabs("MoreEnergyTab") {

		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {

			return ItemRegistry.RedShard;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public String getTabLabel() {
			return "MoreEnergy";
		}

		@Override
		@SideOnly(Side.CLIENT)
		public String getTranslatedTabLabel() {
			return "MoreEnergy";
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public void displayAllReleventItems(List list) {
			list.add(0, new ItemStack(TileEntityRegistry.EnergyInfuser));
			list.add(1, new ItemStack(TileEntityRegistry.EnergyStorage));

			list.add(2, new ItemStack(BlockRegistry.RedOre));
			list.add(3, new ItemStack(BlockRegistry.BlueOre));
			list.add(4, new ItemStack(BlockRegistry.GreenOre));
			list.add(5, new ItemStack(BlockRegistry.YellowOre));
			list.add(6, new ItemStack(BlockRegistry.MachineBlock));

			list.add(7, new ItemStack(ItemRegistry.RedDiamond));
			list.add(8, new ItemStack(ItemRegistry.RedIngot));
			list.add(9, new ItemStack(ItemRegistry.RedShard));

			list.add(10, new ItemStack(ItemRegistry.BlueDiamond));
			list.add(11, new ItemStack(ItemRegistry.BlueIngot));
			list.add(12, new ItemStack(ItemRegistry.BlueShard));

			list.add(13, new ItemStack(ItemRegistry.GreenDiamond));
			list.add(14, new ItemStack(ItemRegistry.GreenIngot));
			list.add(15, new ItemStack(ItemRegistry.GreenShard));

			list.add(16, new ItemStack(ItemRegistry.YellowDiamond));
			list.add(17, new ItemStack(ItemRegistry.YellowIngot));
			list.add(18, new ItemStack(ItemRegistry.YellowShard));

			list.add(19, new ItemStack(ItemRegistry.RedHelmet));
			list.add(20, new ItemStack(ItemRegistry.RedChestplate));
			list.add(21, new ItemStack(ItemRegistry.RedLeggings));
			list.add(22, new ItemStack(ItemRegistry.RedBoots));

			list.add(23, new ItemStack(ItemRegistry.BlueHelmet));
			list.add(24, new ItemStack(ItemRegistry.BlueChestplate));
			list.add(25, new ItemStack(ItemRegistry.BlueLeggings));
			list.add(26, new ItemStack(ItemRegistry.BlueBoots));

			list.add(27, new ItemStack(ItemRegistry.GreenHelmet));
			list.add(28, new ItemStack(ItemRegistry.GreenChestplate));
			list.add(29, new ItemStack(ItemRegistry.GreenLeggings));
			list.add(30, new ItemStack(ItemRegistry.GreenBoots));

			list.add(31, new ItemStack(ItemRegistry.YellowHelmet));
			list.add(32, new ItemStack(ItemRegistry.YellowChestplate));
			list.add(33, new ItemStack(ItemRegistry.YellowLeggings));
			list.add(34, new ItemStack(ItemRegistry.YellowBoots));

			list.add(35, new ItemStack(ItemRegistry.ArmorUpgrade1));
			list.add(36, new ItemStack(ItemRegistry.ArmorUpgrade2));
			list.add(37, new ItemStack(ItemRegistry.ArmorUpgrade3));
			list.add(38, new ItemStack(ItemRegistry.EmptyCrystal));
			list.add(39, new ItemStack(ItemRegistry.SpeedCrystal));
			list.add(40, new ItemStack(ItemRegistry.NightVisionCrystal));

		}
	};
	
	public static SimpleNetworkWrapper network;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
		network = NetworkRegistry.INSTANCE.newSimpleChannel("BetterEnergy");
		network.registerMessage(GuiOpenMessage.Handler.class, GuiOpenMessage.class, 1, Side.SERVER);
		
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
		
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
		
	}

}
