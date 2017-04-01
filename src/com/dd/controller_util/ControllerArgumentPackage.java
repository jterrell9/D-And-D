package com.dd.controller_util;

import java.lang.ClassCastException;
import java.util.HashMap;
import java.util.Map;

public class ControllerArgumentPackage {
    private Map<String, Object> argMap = new HashMap<String, Object>();

    public void setArgument(String argName, Object arg){
        argMap.put(argName, arg);
    }

    public <T> T getArgument(String argName) {
        try {
            T argOut = (T)argMap.get(argName);
            if(argOut == null)
                throw new IllegalArgumentException("");
            return argOut;
        }
        catch(ClassCastException e) {
            throw new IllegalArgumentException("");
        }
    }
}
