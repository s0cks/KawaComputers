package kawac.api;

import kawac.api.natives.AbstractNativeWrapper;

import java.util.HashSet;
import java.util.Set;
import javax.script.Bindings;
import javax.script.ScriptEngine;

public final class NativesRegistry{
    private static final Set<AbstractNativeWrapper> natives = new HashSet<AbstractNativeWrapper>();

    public static void registerNative(AbstractNativeWrapper nat){
        natives.add(nat);
    }

    public static Bindings createBindings(ScriptEngine engine){
        Bindings bindings = engine.createBindings();
        for(AbstractNativeWrapper nat : natives){
            bindings.put(nat.getName(), nat);
        }
        return bindings;
    }
}