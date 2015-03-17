package kawac.common.lib.computer;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;

import java.nio.file.FileSystem;

public final class Computer{
    public final FileSystem fileSystem = Jimfs.newFileSystem(Configuration.unix());

    public Computer(){

    }

    private void initFileSystem(){

    }
}