package com.artur114.bananalib.mc.nbt;

import com.artur114.bananalib.mc.nbt.auto.AutoNBTMetaBuilder;
import com.artur114.bananalib.mc.nbt.auto.meta.ClassNBTMeta;
import com.artur114.bananalib.mc.nbt.auto.meta.IFieldNBTMeta;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @since 1.1
 * @author Artur114
 */
public class BananaAutoNBT {
    private static final Logger log = LogManager.getLogger("BananaLib/AutoNBT");

    public static NBTTagCompound writeToNBT(Object obj, NBTTagCompound nbt) {
        ClassNBTMeta meta = AutoNBTMetaBuilder.metaFor(obj.getClass());

        if (meta.fieldsRec().isEmpty()) {
            log.warn("Attempt to write to NBT object without meta, class {}", obj.getClass());
            return nbt;
        }

        for (IFieldNBTMeta field : meta.fieldsRec()) {
            field.write(obj, nbt);
        }

        return nbt;
    }

    public static void readFromNBT(Object obj, NBTTagCompound nbt) {
        ClassNBTMeta meta = AutoNBTMetaBuilder.metaFor(obj.getClass());

        if (meta.fieldsRec().isEmpty()) {
            log.warn("Attempt to read from NBT object without meta, class {}", obj.getClass());
        }

        for (IFieldNBTMeta field : meta.fieldsRec()) {
            field.read(obj, nbt);
        }
    }
}
