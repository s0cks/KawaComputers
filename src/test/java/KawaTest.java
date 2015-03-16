import kawac.api.NativesRegistry;
import kawac.common.natives.KawaOS;
import kawac.common.natives.KawaRedstone;
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
        kawa.setBindings(NativesRegistry.createBindings(kawa), ScriptContext.ENGINE_SCOPE);
        kawa.eval(new InputStreamReader(getClass().getResourceAsStream("KawaTest.scm")));
    }
}