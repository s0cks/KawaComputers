import kawac.api.NativesRegistry;
import kawac.common.lib.natives.KawaOS;
import kawac.common.lib.natives.KawaRedstone;
import kawac.common.lib.natives.KawaSerializer;
import org.junit.Test;

import java.io.InputStreamReader;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public final class KawaTest{
    @Test
    public void testMe()
    throws ScriptException{
        ScriptEngine kawa = NativesRegistry.getKawa();
        NativesRegistry.registerNative(new KawaOS());
        NativesRegistry.registerNative(new KawaRedstone());
        NativesRegistry.registerNative(new KawaSerializer());
        kawa.setBindings(NativesRegistry.createBindings(kawa), ScriptContext.ENGINE_SCOPE);
        kawa.eval(new InputStreamReader(getClass().getResourceAsStream("KawaTest.scm")));
    }
}