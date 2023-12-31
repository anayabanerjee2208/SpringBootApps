package com.luv2code.junitdemo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

public class ConditionalTest {

    @Test
    @Disabled("Don't run until Jira123 is resolved")
    void basicTest(){
        //execute method and assertion
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void basicTestForWndowsOnly(){
        //execute method and assertion
    }
    @Test
    @EnabledOnOs(OS.MAC)
    void basicTestForMacOnly(){
        //execute method and assertion
    }

    @Test
    @EnabledOnOs({OS.WINDOWS, OS.MAC})
    void basicTestForWndowsAndMacOnly(){
        //execute method and assertion
    }
    @Test
    @EnabledOnOs(OS.LINUX)
    void basicTestForLinuxOnly(){
        //execute method and assertion
    }

    @Test
    @EnabledOnJre(JRE.JAVA_17)
    void basicTestForJRE17(){
        //execute method and assertion
    }
    @Test
    @EnabledOnJre(JRE.JAVA_13)
    void basicTestForJRE13(){
        //execute method and assertion
    }
    @Test
    @EnabledOnJre(JRE.JAVA_11)
    void basicTestForJRE11(){
        //execute method and assertion
    }

    @Test
    @EnabledForJreRange(min=JRE.JAVA_11, max=JRE.JAVA_17)
    void basicTestForJRERange(){
        //execute method and assertion
    }

    @Test
    @EnabledForJreRange(min=JRE.JAVA_11)
    void basicTestForMinJRERange11(){
        //execute method and assertion
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "LUV2CODE_DEV", matches = "DEV")
    void testOnlyForDevEnvironment(){

    }

    @Test
    @EnabledIfSystemProperty(named = "LUV2CODE_SYS_PROP", matches = "CI_CD_DEPLOY")
    void testOnlyForSystemProp(){

    }
}
