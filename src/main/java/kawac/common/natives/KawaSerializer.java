package kawac.common.natives;

import kawac.api.natives.AbstractNativeWrapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.StringWriter;

public final class KawaSerializer
extends AbstractNativeWrapper{
    private final Gson gson = new GsonBuilder().create();

    @Override
    public String getName(){
        return "serializer";
    }

    public Object serialize(Object obj){
        try{
            StringWriter writer = new StringWriter();
            gson.toJson(obj, writer);
            return writer.toString();
        } catch(Exception e){
            return null;
        }
    }

    public Object unserialize(String str, Class<?> clazz){
        try{
            return gson.fromJson(str, clazz);
        } catch(Exception e){
            e.printStackTrace(System.err);
            return null;
        }
    }
}