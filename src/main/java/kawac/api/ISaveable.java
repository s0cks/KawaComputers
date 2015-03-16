package kawac.api;

import net.minecraft.nbt.NBTTagCompound;

public interface ISaveable{
    public void save(NBTTagCompound comp);
    public void load(NBTTagCompound comp);
}