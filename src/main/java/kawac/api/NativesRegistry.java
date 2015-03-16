package kawac.api;

import kawac.api.natives.AbstractNativeScript;

import java.util.HashSet;
import java.util.Set;
import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public final class NativesRegistry{
    private static final Set<AbstractNativeScript> natives = new HashSet<AbstractNativeScript>();
    private static ScriptEngine kawa;

    public static void registerNative(AbstractNativeScript nat){
        natives.add(nat);
    }

    public static Bindings createBindings(ScriptEngine engine){
        Bindings bindings = engine.createBindings();
        for(AbstractNativeScript nat : natives){
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