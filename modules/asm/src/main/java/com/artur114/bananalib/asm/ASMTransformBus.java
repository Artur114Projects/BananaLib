package com.artur114.bananalib.asm;

import com.artur114.bananalib.asm.util.IASMLogger;

import java.util.*;
import java.util.function.BiConsumer;

public class ASMTransformBus {
    private final List<BiConsumer<IASMTransformer, Exception>> downListeners = new ArrayList<>();
    private final List<IASMTransformer> transformers = new ArrayList<>();

    public void registerDownListener(BiConsumer<IASMTransformer, Exception> listener) {
        this.downListeners.add(listener);
    }

    public void registerTransformer(IASMTransformer transformer) {
        this.transformers.add(transformer);
        this.sortTransformers();
    }

    public void registerTransformer(IASMTransformer... transformers) {
        this.transformers.addAll(Arrays.asList(transformers));
        this.sortTransformers();
    }

    public void registerTransformer(Collection<IASMTransformer> transformers) {
        this.transformers.addAll(transformers);
        this.sortTransformers();
    }

    public byte[] transform(IASMLogger logger, String className, byte[] bytecode) {
        for (IASMTransformer transformer : this.transformers) {
            if (transformer.isTarget(className)) {
                try {
                    bytecode = transformer.transform(logger, className, bytecode);
                } catch (Exception e) {
                    for (BiConsumer<IASMTransformer, Exception> listener : this.downListeners) {
                        try {
                            listener.accept(transformer, e);
                        } catch (Exception ignored) {}
                    }
                }
            }
        }
        return bytecode;
    }

    private void sortTransformers() {
        this.transformers.sort(Comparator.comparingInt(IASMTransformer::priority).reversed());
    }
}
