package com.artur114.bananalib.math;

import com.artur114.bananalib.math.core.m3d.vec.IVec3DC;
import com.artur114.bananalib.math.core.m3d.vec.IVec3IC;
import com.artur114.bananalib.math.m3d.vec.IVec3DM;
import com.artur114.bananalib.math.m3d.vec.Vec3D;
import com.artur114.bananalib.math.m3d.vec.Vec3DM;

// TODO поправить конструкторы в боксах и векторах

/**
 * A class providing utilitarian mathematical methods <br>
 * As well as optimized alternatives to methods from {@link Math}
 *
 * @author Artur114
 * @since 1.0
 * @see Math
 */
public final class BananaMath {
    public static final double DOUBLE_EQUALS_EPS = 1.0E-12D;
    public static final float FLOAT_EQUALS_EPS = 1E-4F;
    public static final double DOUBLE_EPS = 1.0E-12D;
    public static final float FLOAT_EPS = 1.0E-6F;

    /**
     * Float overload of the basic linear interpolation formula
     * @param start Start number
     * @param end End number
     * @param pct delta, must be within [0, 1]
     * @return Interpolated value by {@code pct} from {@code start} to {@code end}
     *
     * @since 1.0
     * @see #lerp(double, double, double)
     */
    public static float lerp(float start, float end, float pct) {
        return start + (end - start) * pct;
    }

    /**
     * Double with Float {@code pct} overloading the basic linear interpolation formula
     * @param start Start number
     * @param end End number
     * @param pct delta, must be within [0, 1]
     * @return Interpolated value by {@code pct} from {@code start} to {@code end}
     *
     * @since 1.0
     * @see #lerp(float, float, float)
     */
    public static double lerp(double start, double end, float pct) {
        return start + (end - start) * pct;
    }

    /**
     * Float with Double {@code pct} overloading the basic linear interpolation formula
     * @param start Start number
     * @param end End number
     * @param pct delta, must be within [0, 1]
     * @return Interpolated value by {@code pct} from {@code start} to {@code end}
     *
     * @since 1.0
     * @see #lerp(double, double, double)
     */
    public static float lerp(float start, float end, double pct) {
        return (float) (start + (end - start) * pct);
    }

    /**
     * Double overload of the basic linear interpolation formula
     * @param start Start number
     * @param end End number
     * @param pct delta, must be within [0, 1]
     * @return Interpolated value by {@code pct} from {@code start} to {@code end}
     *
     * @since 1.0
     * @see #lerp(float, float, float)
     */
    public static double lerp(double start, double end, double pct) {
        return start + (end - start) * pct;
    }

    /**
     * @since 1.0
     */
    public static int mod(double v) {
        return v < 0 ? -1 : 0;
    }

    /**
     * @since 1.0
     */
    public static int mod(float v) {
        return v < 0 ? -1 : 0;
    }

    /**
     * @since 1.0
     */
    public static int mod(int v) {
        return v < 0 ? -1 : 0;
    }

    /**
     * @since 1.0
     */
    public static double sin(double rad) {
        double sin = Math.sin(rad);
        if (Math.abs(sin) < DOUBLE_EPS) sin = 0.0;
        if (Math.abs(sin - 1.0) < DOUBLE_EPS) sin = 1.0;
        if (Math.abs(sin + 1.0) < DOUBLE_EPS) sin = -1.0;
        return sin;
    }

    /**
     * @since 1.0
     */
    public static double cos(double rad) {
        double cos = Math.cos(rad);
        if (Math.abs(cos) < DOUBLE_EPS) cos = 0.0;
        if (Math.abs(cos - 1.0) < DOUBLE_EPS) cos = 1.0;
        if (Math.abs(cos + 1.0) < DOUBLE_EPS) cos = -1.0;
        return cos;
    }

    /**
     * @since 1.0
     */
    public static int round(double d) {
        d = d + 0.5 + DOUBLE_EPS;
        int i = (int) d;
        return d < (double) i ? i - 1 : i;
    }

    /**
     * @since 1.0
     */
    public static int round(float f) {
        f = f + 0.5F + FLOAT_EPS;
        int i = (int) f;
        return f < (float) i ? i - 1 : i;
    }

    /**
     * @since 1.0
     */
    public static int floor(double d) {
        int i = (int) d;
        return d < (double) i ? i - 1 : i;
    }

    /**
     * @since 1.0
     */
    public static int floor(float f) {
        int i = (int) f;
        return f < (float) i ? i - 1 : i;
    }

    /**
     * @since 1.0
     */
    public static int ceil(float f) {
        int i = (int) f;
        return f > (float) i ? i + 1 : i;
    }

    /**
     * @since 1.0
     */
    public static int ceil(double d) {
        int i = (int) d;
        return d > (double) i ? i + 1 : i;
    }

    /**
     * @since 1.1
     */
    public static boolean isInView(IVec3DC pos, IVec3DC cameraPos, IVec3DC cameraLook, float fovAngleDeg, double maxDistance) {
        Vec3DM vec = Vec3DM.obtain();
        IVec3DM toTarget = vec.set(pos).subtract(cameraPos.x(), cameraPos.y(), cameraPos.z());
        if (toTarget.length() > maxDistance) return false;
        toTarget.normalize();
        double dot = cameraLook.dot(toTarget.x(), toTarget.y(), toTarget.z());
        double minDot = cos(Math.toRadians(fovAngleDeg));
        Vec3DM.release(vec);
        return dot >= minDot;
    }

    /**
     * @since 1.1
     */
    public static boolean isInView(IVec3IC pos, IVec3DC cameraPos, IVec3DC cameraLook, float fovAngleDeg, double maxDistance) {
        Vec3DM vec = Vec3DM.obtain();
        IVec3DM toTarget = vec.set(pos).subtract(cameraPos.x(), cameraPos.y(), cameraPos.z());
        if (toTarget.length() > maxDistance) return false;
        toTarget.normalize();
        double dot = cameraLook.dot(toTarget.x(), toTarget.y(), toTarget.z());
        double minDot = cos(Math.toRadians(fovAngleDeg));
        Vec3DM.release(vec);
        return dot >= minDot;
    }

    /**
     * @since 1.1
     */
    public static boolean isInView(IVec3DC pos, IVec3IC cameraPos, IVec3DC cameraLook, float fovAngleDeg, double maxDistance) {
        Vec3DM vec = Vec3DM.obtain();
        IVec3DM toTarget = vec.set(pos).subtract(cameraPos.x(), cameraPos.y(), cameraPos.z());
        if (toTarget.length() > maxDistance) return false;
        toTarget.normalize();
        double dot = cameraLook.dot(toTarget.x(), toTarget.y(), toTarget.z());
        double minDot = cos(Math.toRadians(fovAngleDeg));
        Vec3DM.release(vec);
        return dot >= minDot;
    }

    /**
     * @since 1.1
     */
    public static boolean isInView(IVec3IC pos, IVec3IC cameraPos, IVec3DC cameraLook, float fovAngleDeg, double maxDistance) {
        Vec3DM vec = Vec3DM.obtain();
        IVec3DM toTarget = vec.set(pos).subtract(cameraPos.x(), cameraPos.y(), cameraPos.z());
        if (toTarget.length() > maxDistance) return false;
        toTarget.normalize();
        double dot = cameraLook.dot(toTarget.x(), toTarget.y(), toTarget.z());
        double minDot = cos(Math.toRadians(fovAngleDeg));
        Vec3DM.release(vec);
        return dot >= minDot;
    }

    public static float snap(float val, float acc) {
        if (Math.abs(acc) < FLOAT_EPS) return val;
        return Math.round(val / acc) * acc;
    }

    public static double snap(double val, double acc) {
        if (Math.abs(acc) < DOUBLE_EPS) return val;
        return Math.round(val / acc) * acc;
    }
}
