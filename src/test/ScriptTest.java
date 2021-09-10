package test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author baB_hyf
 * @date 2021/09/10
 */
public class ScriptTest {

    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
        scriptEngine.eval("print('hello world!')");
    }
}
