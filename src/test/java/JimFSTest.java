import kawac.api.utils.FSUtils;
import kawac.api.utils.Streams;
import net.minecraft.util.ResourceLocation;
import org.junit.Test;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

public final class JimFSTest{
    @Test
    public void test()
    throws IOException{
        FileSystem fs = Jimfs.newFileSystem(Configuration.unix());
        ls(fs.getPath("/"));

        FSUtils.save(fs, "/bios.scm", Streams.consume(new ResourceLocation("kawac", "bios.scm")));

        ls(fs.getPath("/"));
        System.out.println(FSUtils.cat(fs.getPath("/bios.scm")));
    }

    private void ls(Path dir){
        try(DirectoryStream<Path> dirStream = Files.newDirectoryStream(dir)){
            for(Path path : dirStream){
                System.out.println(path);
            }
        } catch(Exception e){
            e.printStackTrace(System.err);
        }
    }
}