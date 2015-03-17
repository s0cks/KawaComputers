package kawac.api.utils;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public final class FSUtils{
    public static String cat(Path path){
        try(FileChannel in = FileChannel.open(path, StandardOpenOption.READ);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            WritableByteChannel out = Channels.newChannel(bos)){

            in.transferTo(0, Long.MAX_VALUE, out);
            return new String(bos.toByteArray(), StandardCharsets.UTF_8);
        } catch(Exception e){
            return "Error Reading File " + path + "(" + e + ")";
        }
    }

    public static void save(FileSystem fs, String path, String contents){
        Path p = fs.getPath(path);
        try(FileChannel out = FileChannel.open(p, StandardOpenOption.CREATE, StandardOpenOption.WRITE)){
            out.write(ByteBuffer.wrap(contents.getBytes(StandardCharsets.UTF_8)));
        } catch(Exception e){
            e.printStackTrace(System.err);
        }
    }

    public static FileSystem load(NBTTagCompound comp){
        FileSystem fs = Jimfs.newFileSystem(Configuration.unix());
        load(fs, comp);
        return fs;
    }

    private static void load(FileSystem fs, NBTTagCompound comp){
        NBTTagList files = comp.getTagList("files", 10);
        for(int i = 0; i < files.tagCount(); i++){
            NBTTagCompound c = files.getCompoundTagAt(i);
            String name = c.getString("path");
            byte[] bits = c.getByteArray("contents");
            toPath(fs, name, bits);
        }
    }

    public static void save(FileSystem fs, NBTTagCompound comp){
        save(fs.getPath("/"), comp);
    }

    private static void save(Path file, NBTTagCompound comp){
        if(Files.isDirectory(file)){
            try(DirectoryStream<Path> dirStream = Files.newDirectoryStream(file)){
                NBTTagList files = new NBTTagList();
                for(Path path : dirStream){
                    if(path.getFileName().toString().equalsIgnoreCase("work")){
                        continue;
                    }

                    NBTTagCompound c = new NBTTagCompound();
                    save(path, c);
                    files.appendTag(c);
                }
                comp.setTag("files", files);
            } catch(Exception e){
                e.printStackTrace(System.err);
            }
        } else{
            comp.setString("path", file.toString());
            comp.setByteArray("contents", toBytes(file));
        }
    }

    private static Path toPath(FileSystem fs, String path,  byte[] bytes){
        Path file = fs.getPath(path);
        try(FileChannel out = FileChannel.open(file, StandardOpenOption.CREATE, StandardOpenOption.WRITE)){
            out.write(ByteBuffer.wrap(bytes));
            return file;
        } catch(Exception e){
            e.printStackTrace(System.err);
            return file;
        }
    }

    private static byte[] toBytes(Path path){
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream();
            FileChannel in = FileChannel.open(path, StandardOpenOption.READ);
            WritableByteChannel out = Channels.newChannel(bos)){

            in.transferTo(0, Long.MAX_VALUE, out);
            return bos.toByteArray();
        } catch(Exception e){
            e.printStackTrace(System.err);
            return new byte[0];
        }
    }
}
