package kawac.common.natives;

import kawac.api.natives.AbstractNativeScript;

public final class KawaOS
extends AbstractNativeScript{
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