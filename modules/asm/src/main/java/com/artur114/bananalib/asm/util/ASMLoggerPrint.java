package com.artur114.bananalib.asm.util;

import java.io.PrintStream;

public class ASMLoggerPrint implements IASMLogger {
    private final PrintStream out;

    public ASMLoggerPrint(PrintStream out) {
        this.out = out;
    }

    @Override
    public void warn(String log) {
        this.printString("WARN", log);
    }

    @Override
    public void info(String log) {
        this.printString("INFO", log);
    }

    @Override
    public void error(String log) {
        this.printString("ERROR", log);
    }

    @Override
    public void debug(String log) {
        this.printString("DEBUG", log);
    }

    @Override
    public void warn(String log, Object arg) {
        this.printString("WARN", this.format1Arg(log, arg));
    }

    @Override
    public void info(String log, Object arg) {
        this.printString("INFO", this.format1Arg(log, arg));
    }

    @Override
    public void error(String log, Object arg) {
        this.printString("ERROR", this.format1Arg(log, arg));
    }

    @Override
    public void debug(String log, Object arg) {
        this.printString("DEBUG", this.format1Arg(log, arg));
    }

    @Override
    public void warn(String log, Object... args) {
        this.printString("WARN", this.format(log, args));
    }

    @Override
    public void info(String log, Object... args) {
        this.printString("INFO", this.format(log, args));
    }

    @Override
    public void error(String log, Object... args) {
        this.printString("ERROR", this.format(log, args));
    }

    @Override
    public void debug(String log, Object... args) {
        this.printString("DEBUG", this.format(log, args));
    }

    @Override
    public void warn(String log, Object arg, Object arg1) {
        this.printString("WARN", this.format2Arg(log, arg, arg1));
    }

    @Override
    public void info(String log, Object arg, Object arg1) {
        this.printString("INFO", this.format2Arg(log, arg, arg1));
    }

    @Override
    public void error(String log, Object arg, Object arg1) {
        this.printString("ERROR", this.format2Arg(log, arg, arg1));
    }

    @Override
    public void debug(String log, Object arg, Object arg1) {
        this.printString("DEBUG", this.format2Arg(log, arg, arg1));
    }

    private void printString(String type, String log) {
        this.out.println("[" + type + "] " + log);
    }

    private String format(String log, Object... args) {
        StringBuilder builder = new StringBuilder();
        int lastSplit = 0;
        for (int i = 0; i != args.length; i++) {
            int preLastSplit = lastSplit;
            lastSplit = log.indexOf("{}", lastSplit);
            if (lastSplit == -1) {
                lastSplit = preLastSplit; break;
            }
            builder.append(log, preLastSplit, lastSplit).append(args[i]);
            lastSplit += 2;
        }
        return builder.append(log, lastSplit, log.length()).toString();
    }

    private String format1Arg(String log, Object arg) {
        int split = log.indexOf("{}");
        if (split == -1) return log;
        return log.substring(0, split) + arg + log.substring(split + 2);
    }

    private String format2Arg(String log, Object arg, Object arg1) {
        int split1 = log.indexOf("{}");
        if (split1 == -1) return log;
        int split2 = log.indexOf("{}", split1 + 2);
        if (split2 == -1) return new StringBuilder(log).replace(split1, split1 + 2, String.valueOf(arg)).toString();
        return log.substring(0, split1) + arg + log.substring(split1 + 2, split2) + arg1 + log.substring(split2 + 2);
    }
}
