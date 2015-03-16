package kawac.api;

import kawac.api.natives.AbstractNativeWrapper;

import java.util.HashSet;
import java.util.Set;
import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public final class NativesRegistry{
    private static final Set<AbstractNativeWrapper> natives = new HashSet<AbstractNativeWrapper>();
    private static ScriptEngine kawa;

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

    public static ScriptEngine getKawa(){
        if(kawa == null){
            ScriptEngineManager manager = new ScriptEngineManager();
            kawa = manager.getEngineByExtension("scm");
        }

        return kawa;
    }
}