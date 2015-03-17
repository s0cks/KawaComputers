import gnu.mapping.Procedure1;
import kawac.api.NativesRegistry;
import org.junit.Test;

import java.io.InputStreamReader;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public final class KawaTest{
    @Test
    public void testMe()
    throws ScriptException{
        ScriptEngine kawa = new ScriptEngineManager().getEngineByExtension("scm");
        kawa.setBindings(NativesRegistry.createBindings(kawa), ScriptContext.ENGINE_SCOPE);
        kawa.put("println", new Procedure1(){
            @Override
            public Object apply1(Object o)
            throws Throwable{
                System.out.println(o);
                return null;
            }
        });
        kawa.eval(new InputStreamReader(getClass().getResourceAsStream("/assets/kawac/bios.scm")));
    }
}