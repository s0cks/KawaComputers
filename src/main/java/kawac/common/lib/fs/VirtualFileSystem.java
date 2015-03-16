package kawac.common.lib.fs;

import kawac.api.fs.IFile;
import kawac.api.fs.IFileSystem;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants.NBT;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class VirtualFileSystem
implements IFileSystem{
    protected VirtualDirectory root = new VirtualDirectory();

    @Override
    public boolean exists(String path){
        return root.get(path) != null;
    }

    @Override
    public boolean isDirectory(String path){
        return exists(path) && root.get(path).isDirectory();
    }

    @Override
    public boolean delete(String path){
        return false;
    }

    @Override
    public boolean makeDirectory(String path){
        return false;
    }

    @Override
    public boolean rename(String from, String to){
        return false;
    }

    @Override
    public void save(NBTTagCompound comp){
        this.root.save(comp);
    }

    @Override
    public void load(NBTTagCompound comp){
        this.root.load(comp);
    }

    protected interface IVirtualObject{
        public boolean isDirectory();
        public boolean canDelete();
        public void load(NBTTagCompound comp);
        public void save(NBTTagCompound comp);
        public IVirtualObject get(String path);
    }

    protected final class VirtualFile
    implements IVirtualObject{
        private ByteBuffer data;
        private IFile file = null;

        @Override
        public boolean isDirectory(){
            return false;
        }

        @Override
        public boolean canDelete(){
            return true;
        }

        @Override
        public void load(NBTTagCompound comp){
            this.data.clear();
            this.data = ByteBuffer.wrap(comp.getByteArray("data"));
        }

        @Override
        public void save(NBTTagCompound comp){
            comp.setByteArray("data", this.data.array());
        }

        @Override
        public IVirtualObject get(String path){
            return this;
        }
    }

    protected final class VirtualDirectory
    implements IVirtualObject{
        private final Map<String, IVirtualObject> children = new HashMap<>();

        public boolean mkdir(String path){
            if(this.children.containsKey(path)){
                return false;
            } else{
                this.children.put(path, new VirtualDirectory());
                return true;
            }
        }

        public boolean touch(String name){
            if(this.children.containsKey(name)){
                return false;
            } else{
                this.children.put(name, new VirtualFile());
                return true;
            }
        }

        @Override
        public boolean isDirectory(){
            return true;
        }

        @Override
        public boolean canDelete(){
            return this.children.isEmpty();
        }

        @Override
        public void load(NBTTagCompound comp){
            this.children.clear();
            NBTTagList childrenNBT = comp.getTagList("children", NBT.TAG_COMPOUND);
            for(int i = 0; i < childrenNBT.tagCount(); i++){
                NBTTagCompound childNBT = childrenNBT.getCompoundTagAt(i);
                IVirtualObject child = childNBT.getBoolean("isDirectory") ? new VirtualDirectory() : new VirtualFile();
                child.load(childNBT);
                this.children.put(childNBT.getString("name"), child);
            }
        }

        @Override
        public void save(NBTTagCompound comp){
            NBTTagList childrenNBT = new NBTTagList();
            for(Entry<String, IVirtualObject> entry : this.children.entrySet()){
                NBTTagCompound childNBT = new NBTTagCompound();
                childNBT.setBoolean("isDirectory", entry.getValue().isDirectory());
                childNBT.setString("name", entry.getKey());
                entry.getValue().save(childNBT);
                childrenNBT.appendTag(childNBT);
            }
            comp.setTag("children", childrenNBT);
        }

        @Override
        public IVirtualObject get(String child){
            return this.children.get(child);
        }
    }

    protected final class VirtualFileInputStream
    extends InputStream{
        private final VirtualFile file;

        private boolean isClosed = false;
        private int pos = 0;

        public VirtualFileInputStream(VirtualFile file){
            this.file = file;
        }

        @Override
        public int available(){
            if(this.isClosed){
                return 0;
            } else{
                return Math.max(file.data.capacity() - this.pos, 0);
            }
        }

        @Override
        public void close(){
            this.isClosed = true;
        }

        @Override
        public int read()
        throws IOException{
            if(!this.isClosed){
                if(this.available() == 0){
                    return -1;
                } else{
                    this.pos++;
                    return this.file.data.get(this.pos - 1);
                }
            } else{
                throw new IOException("File is closed");
            }
        }

        @Override
        public void reset()
        throws IOException{
            if(!this.isClosed){
                this.pos = 0;
            } else{
                throw new IOException("File is closed");
            }
        }

        @Override
        public long skip(long l)
        throws IOException{
            if(!this.isClosed){
                this.pos = (int) Math.min((this.pos + l), Integer.MAX_VALUE);
                return this.pos;
            } else{
                throw new IOException("File is closed");
            }
        }
    }
}