package com.artur114.bananalib.asm.util;

public interface IASMLogger {
    void warn(String log);
    void info(String log);
    void error(String log);
    void debug(String log);
    void warn(String log, Object arg);
    void info(String log, Object arg);
    void error(String log, Object arg);
    void debug(String log, Object arg);
    void warn(String log, Object... args);
    void info(String log, Object... args);
    void error(String log, Object... args);
    void debug(String log, Object... args);
    void warn(String log, Object arg, Object arg1);
    void info(String log, Object arg, Object arg1);
    void error(String log, Object arg, Object arg1);
    void debug(String log, Object arg, Object arg1);
}
