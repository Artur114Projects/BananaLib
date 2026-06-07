package com.artur114.bananalib.mc.util;

import net.minecraft.util.Rotation;

public enum EnumRot {
    NON, C90, C180, C270;

    public double radians() {
        switch (this) {
            case C90:
                return Math.PI * 0.5;
            case C180:
                return Math.PI;
            case C270:
                return Math.PI * 1.5;
            default:
                return 0.0F;
        }
    }

    public float degrees() {
        switch (this) {
            case C90:
                return 90.0F;
            case C180:
                return 180.0F;
            case C270:
                return 270.0F;
            default:
                return 0.0F;
        }
    }

    public Rotation mc() {
        switch (this) {
            case C90:
                return Rotation.CLOCKWISE_90;
            case C180:
                return Rotation.CLOCKWISE_180;
            case C270:
                return Rotation.COUNTERCLOCKWISE_90;
            default:
                return Rotation.NONE;
        }
    }

    public static EnumRot fromMc(Rotation rot) {
        switch (rot) {
            case CLOCKWISE_90:
                return C90;
            case CLOCKWISE_180:
                return C180;
            case COUNTERCLOCKWISE_90:
                return C270;
            default:
                return NON;
        }
    }
}
