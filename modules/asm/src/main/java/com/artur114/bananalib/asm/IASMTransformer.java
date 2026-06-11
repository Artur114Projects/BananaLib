package com.artur114.bananalib.asm;

import com.artur114.bananalib.asm.util.IASMLogger;

/**
 * Interface representing ASM transformer
 *
 * @author Artur114
 * @since 1.0
 * @see AbstractASMTransformer
 */
public interface IASMTransformer {
    /**
     * This method is called if the {@link #isTarget(String)} method returned true, all manipulations with byte code should occur in this method
     * @param logger logger for outputting logs
     * @param className class name
     * @param bytecode original byte code of the class
     * @return modified class byte code
     */
    byte[] transform(IASMLogger logger, String className, byte[] bytecode);

    /**
     * Called before calling the {@link #transform(IASMLogger, String, byte[])} method, if the method returns false then the {@link #transform(IASMLogger, String, byte[])} method will not be called
     * @param className Class name
     * @return true if class needs to be transformed
     */
    boolean isTarget(String className);

    /**
     * Higher the number, the sooner the transformer will be called
     * @return call priority
     */
    int priority();
}
