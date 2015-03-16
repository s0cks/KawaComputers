package kawac.api.fs;

import kawac.api.ISaveable;

public interface IFileSystem
extends ISaveable{
    public boolean exists(String path);
    public boolean isDirectory(String path);
    public boolean delete(String path);
    public boolean makeDirectory(String path);
    public boolean rename(String from, String to);
}