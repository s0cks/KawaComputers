package kawac.common.lib.natives;

import kawac.api.natives.AbstractNativeWrapper;

public final class KawaOS
extends AbstractNativeWrapper{
    @Override
    public String getName(){
        return "os";
    }

    public void println(Object obj){
        System.out.println(obj);
    }

    public void print(Object obj){
        System.out.print(obj);
    }
}