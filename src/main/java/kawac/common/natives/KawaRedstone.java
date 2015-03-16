package kawac.common.natives;

import kawac.api.natives.AbstractNativeWrapper;
import net.minecraftforge.common.util.ForgeDirection;

import com.google.gson.stream.JsonWriter;

import java.io.StringWriter;

public final class KawaRedstone
extends AbstractNativeWrapper{
    @Override
    public String getName(){
        return "rs";
    }

    public void setOutput(ForgeDirection dir, boolean on){
        if(on){
            System.out.println("Setting redstone on @ side " + dir.name());
        } else{
            System.out.println("Setting redstone off @ side " + dir.name());
        }
    }

    public boolean isOn(ForgeDirection dir){
        return false;
    }

    public boolean isConnected(ForgeDirection dir){
        return false;
    }

    public ForgeDirection direction(String name){
        try{
            if(ForgeDirection.valueOf(name.toUpperCase()) != null){
                return ForgeDirection.valueOf(name);
            } else{
                return ForgeDirection.UNKNOWN;
            }
        } catch(Exception e){
            return ForgeDirection.UNKNOWN;
        }
    }

    public void listDirections(){
        try{
            StringWriter writer = new StringWriter();
            JsonWriter jsonWriter = new JsonWriter(writer).beginArray();
            for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
                jsonWriter.value(dir.name());
            }
            jsonWriter.endArray();
            System.out.println(writer.toString());
        } catch(Exception e){
            e.printStackTrace(System.err);
        }
    }
}