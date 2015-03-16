package kawac.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = "kc",
        name = "KawaComputers",
        version = "0.0.0.0",
        useMetadata = true
)
public final class KawaComputers{
    @Instance("kc")
    public static KawaComputers instance;

    @SidedProxy(
            clientSide = "kawac.client.ClientProxy",
            serverSide = "kawac.common.ServerProxy"
    )
    public static ServerProxy proxy;

    public static final Logger logger = LogManager.getLogger(KawaComputers.class.getSimpleName());
    public static final CreativeTabs tab = new CreativeTabs("kawac"){
        @Override
        public Item getTabIconItem(){
            return Items.diamond;
        }
    };

    @EventHandler
    public void onPreInit(FMLPreInitializationEvent e){

    }

    @EventHandler
    public void onInit(FMLInitializationEvent e){
        KawaCBlocks.register();

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new KawaCGuiHandler());
    }

    @EventHandler
    public void onPostInit(FMLPostInitializationEvent e){

    }

    @EventHandler
    public void onServerStarting(FMLServerStartingEvent e){

    }
}