package kawac.api.utils;

import net.minecraft.util.ResourceLocation;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public final class Streams{
    public static String consume(InputStream in){
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream()){
            byte[] chunk = new byte[1024];
            int len;
            while((len = in.read(chunk, 0, 1024)) > 0){
                bos.write(chunk, 0, len);
            }

            return new String(bos.toByteArray(), StandardCharsets.UTF_8);
        } catch(Exception e){
            return "Error: " + e.getLocalizedMessage();
        }
    }

    public static String consume(ResourceLocation loc){
        System.out.println("/assets/" + loc.getResourceDomain() + "/" + loc.getResourcePath());
        return consume(System.class.getResourceAsStream("/assets/" + loc.getResourceDomain() + "/" + loc.getResourcePath()));
    }
}