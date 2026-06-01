package com.artur114.bananalib.math.m3d.matrix;

import com.artur114.bananalib.math.BananaMath;
import com.artur114.bananalib.math.core.m2d.vec.IVec2DC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2IC;
import com.artur114.bananalib.math.core.m3d.box.IBox3DC;
import com.artur114.bananalib.math.core.m3d.box.IBox3IC;
import com.artur114.bananalib.math.core.m3d.matrix.IMatrix3DC;
import com.artur114.bananalib.math.core.m3d.matrix.IMatrix3FC;
import com.artur114.bananalib.math.core.m3d.vec.IVec3DC;
import com.artur114.bananalib.math.core.m3d.vec.IVec3IC;
import com.artur114.bananalib.math.internal.FloatStack;
import com.artur114.bananalib.math.internal.Hasher;
import com.artur114.bananalib.math.internal.ThreadLocalPool;
import com.artur114.bananalib.math.m2d.vec.*;
import com.artur114.bananalib.math.m3d.box.*;
import com.artur114.bananalib.math.m3d.vec.*;

import java.nio.FloatBuffer;

public class Matrix3FM implements IMatrix3FM {
    private static final ThreadLocalPool<Matrix3FM> pool = new ThreadLocalPool<>(new Matrix3FM[4], Matrix3FM::new, matrix -> {
        matrix.resetStack().setIdentity();
        matrix.released = true;
        return matrix;
    }, matrix -> {
        matrix.released = false;
        return matrix;
    }, matrix -> matrix.released);

    public static Matrix3FM obtain() {
        return pool.obtain();
    }

    public static void release(Matrix3FM matrix) {
        pool.release(matrix);
    }

    private FloatStack stateStack = null;
    private float m00, m01, m02, m03;
    private float m10, m11, m12, m13;
    private float m20, m21, m22, m23;
    private boolean released;

    public Matrix3FM() {
        this.m00 = 1.0F; this.m01 = 0.0F; this.m02 = 0.0F; this.m03 = 0.0F;
        this.m10 = 0.0F; this.m11 = 1.0F; this.m12 = 0.0F; this.m13 = 0.0F;
        this.m20 = 0.0F; this.m21 = 0.0F; this.m22 = 1.0F; this.m23 = 0.0F;
    }

    public Matrix3FM(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23) {
        this.m00 = (float) m00; this.m01 = (float) m01; this.m02 = (float) m02; this.m03 = (float) m03;
        this.m10 = (float) m10; this.m11 = (float) m11; this.m12 = (float) m12; this.m13 = (float) m13;
        this.m20 = (float) m20; this.m21 = (float) m21; this.m22 = (float) m22; this.m23 = (float) m23;
    }

    public Matrix3FM(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21, float m22, float m23) {
        this.m00 = m00; this.m01 = m01; this.m02 = m02; this.m03 = m03;
        this.m10 = m10; this.m11 = m11; this.m12 = m12; this.m13 = m13;
        this.m20 = m20; this.m21 = m21; this.m22 = m22; this.m23 = m23;
    }

    public Matrix3FM(IMatrix3D m) {
        this(
            m.m00(), m.m01(), m.m02(), m.m03(),
            m.m10(), m.m11(), m.m12(), m.m13(),
            m.m20(), m.m21(), m.m22(), m.m23()
        );
    }

    public Matrix3FM(IMatrix3F m) {
        this(
            m.m00(), m.m01(), m.m02(), m.m03(),
            m.m10(), m.m11(), m.m12(), m.m13(),
            m.m20(), m.m21(), m.m22(), m.m23()
        );
    }

    public Matrix3FM(FloatBuffer buf) {
        this(
            buf.get(), buf.get(), buf.get(), buf.get(),
            buf.get(), buf.get(), buf.get(), buf.get(),
            buf.get(), buf.get(), buf.get(), buf.get()
        );
    }

    @Override
    public IMatrix3FM set(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21, float m22, float m23) {
        this.m00 = m00; this.m01 = m01; this.m02 = m02; this.m03 = m03;
        this.m10 = m10; this.m11 = m11; this.m12 = m12; this.m13 = m13;
        this.m20 = m20; this.m21 = m21; this.m22 = m22; this.m23 = m23;
        return this;
    }

    @Override
    public IMatrix3FM set(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23) {
        this.m00 = (float) m00; this.m01 = (float) m01; this.m02 = (float) m02; this.m03 = (float) m03;
        this.m10 = (float) m10; this.m11 = (float) m11; this.m12 = (float) m12; this.m13 = (float) m13;
        this.m20 = (float) m20; this.m21 = (float) m21; this.m22 = (float) m22; this.m23 = (float) m23;
        return this;
    }

    @Override
    public IMatrix3FM set(IMatrix3FC m) {
        this.m00 = m.m00(); this.m01 = m.m01(); this.m02 = m.m02(); this.m03 = m.m03();
        this.m10 = m.m10(); this.m11 = m.m11(); this.m12 = m.m12(); this.m13 = m.m13();
        this.m20 = m.m20(); this.m21 = m.m21(); this.m22 = m.m22(); this.m23 = m.m23();
        return this;
    }

    @Override
    public IMatrix3FM set(IMatrix3DC m) {
        this.m00 = (float) m.m00(); this.m01 = (float) m.m01(); this.m02 = (float) m.m02(); this.m03 = (float) m.m03();
        this.m10 = (float) m.m10(); this.m11 = (float) m.m11(); this.m12 = (float) m.m12(); this.m13 = (float) m.m13();
        this.m20 = (float) m.m20(); this.m21 = (float) m.m21(); this.m22 = (float) m.m22(); this.m23 = (float) m.m23();
        return this;
    }

    @Override
    public IMatrix3FM set(float[] ms) {
        if (ms.length < 12) throw new IllegalArgumentException();
        this.m00 = ms[0]; this.m01 = ms[1]; this.m02 = ms[2]; this.m03 = ms[3];
        this.m10 = ms[4]; this.m11 = ms[5]; this.m12 = ms[6]; this.m13 = ms[7];
        this.m20 = ms[8]; this.m21 = ms[9]; this.m22 = ms[10]; this.m23 = ms[11];
        return this;
    }

    @Override
    public IMatrix3FM setM00(float m00) {
        this.m00 = m00;
        return this;
    }

    @Override
    public IMatrix3FM setM01(float m01) {
        this.m01 = m01;
        return this;
    }

    @Override
    public IMatrix3FM setM02(float m02) {
        this.m02 = m02;
        return this;
    }

    @Override
    public IMatrix3FM setM03(float m03) {
        this.m03 = m03;
        return this;
    }

    @Override
    public IMatrix3FM setM10(float m10) {
        this.m10 = m10;
        return this;
    }

    @Override
    public IMatrix3FM setM11(float m11) {
        this.m11 = m11;
        return this;
    }

    @Override
    public IMatrix3FM setM12(float m12) {
        this.m12 = m12;
        return this;
    }

    @Override
    public IMatrix3FM setM13(float m13) {
        this.m13 = m13;
        return this;
    }

    @Override
    public IMatrix3FM setM20(float m20) {
        this.m20 = m20;
        return this;
    }

    @Override
    public IMatrix3FM setM21(float m21) {
        this.m21 = m21;
        return this;
    }

    @Override
    public IMatrix3FM setM22(float m22) {
        this.m22 = m22;
        return this;
    }

    @Override
    public IMatrix3FM setM23(float m23) {
        this.m23 = m23;
        return this;
    }

    @Override
    public IMatrix3FM setIdentity() {
        this.m00 = 1.0F; this.m01 = 0.0F; this.m02 = 0.0F; this.m03 = 0.0F;
        this.m10 = 0.0F; this.m11 = 1.0F; this.m12 = 0.0F; this.m13 = 0.0F;
        this.m20 = 0.0F; this.m21 = 0.0F; this.m22 = 1.0F; this.m23 = 0.0F;
        return this;
    }

    @Override
    public IMatrix3FM collapseStack() {
        this.stateStack = null;
        return this;
    }

    @Override
    public IMatrix3FM resetStack() {
        if (this.stateStack != null) {
            this.stateStack.reset();
        }
        return this;
    }

    @Override
    public IMatrix3FM pushMatrix() {
        if (this.stateStack == null) {
            this.stateStack = new FloatStack(12);
        }
        float[] arr = this.stateStack.newEntry();
        arr[0] = this.m00;
        arr[1] = this.m01;
        arr[2] = this.m02;
        arr[3] = this.m03;

        arr[4] = this.m10;
        arr[5] = this.m11;
        arr[6] = this.m12;
        arr[7] = this.m13;

        arr[8] = this.m20;
        arr[9] = this.m21;
        arr[10] = this.m22;
        arr[11] = this.m23;
        return this;
    }

    @Override
    public IMatrix3FM popMatrix() {
        if (this.stateStack == null) {
            throw new IllegalStateException();
        }
        return this.set(this.stateStack.pull());
    }

    @Override
    public float m00() {
        return this.m00;
    }

    @Override
    public float m01() {
        return this.m01;
    }

    @Override
    public float m02() {
        return this.m02;
    }

    @Override
    public float m03() {
        return this.m03;
    }

    @Override
    public float m10() {
        return this.m10;
    }

    @Override
    public float m11() {
        return this.m11;
    }

    @Override
    public float m12() {
        return this.m12;
    }

    @Override
    public float m13() {
        return this.m13;
    }

    @Override
    public float m20() {
        return this.m20;
    }

    @Override
    public float m21() {
        return this.m21;
    }

    @Override
    public float m22() {
        return this.m22;
    }

    @Override
    public float m23() {
        return this.m23;
    }

    @Override
    public float m30() {
        return 0.0F;
    }

    @Override
    public float m31() {
        return 0.0F;
    }

    @Override
    public float m32() {
        return 0.0F;
    }

    @Override
    public float m33() {
        return 1.0F;
    }

    @Override
    public IMatrix3FM invert() {
        if (!this.isReversible()) throw new ArithmeticException("Couldn't invert unreversible matrix: " + this);
        float detInv = 1.0F / this.determinant();
        float i00 = (this.m11 * this.m22 - this.m21 * this.m12) * detInv;
        float i01 = -(this.m01 * this.m22 - this.m21 * this.m02) * detInv;
        float i02 = (this.m01 * this.m12 - this.m11 * this.m02) * detInv;
        float i10 = -(this.m10 * this.m22 - this.m20 * this.m12) * detInv;
        float i11 = (this.m00 * this.m22 - this.m20 * this.m02) * detInv;
        float i12 = -(this.m00 * this.m12 - this.m10 * this.m02) * detInv;
        float i20 = (this.m10 * this.m21 - this.m20 * this.m11) * detInv;
        float i21 = -(this.m00 * this.m21 - this.m20 * this.m01) * detInv;
        float i22 = (this.m00 * this.m11 - this.m10 * this.m01) * detInv;
        float i03 = -(i00 * this.m03 + i01 * this.m13 + i02 * this.m23);
        float i13 = -(i10 * this.m03 + i11 * this.m13 + i12 * this.m23);
        float i23 = -(i20 * this.m03 + i21 * this.m13 + i22 * this.m23);
        return this.set(i00, i01, i02, i03, i10, i11, i12, i13, i20, i21, i22, i23);
    }

    @Override
    public IMatrix3FM transpose() {
        return this.set(
            this.m00, this.m10, this.m20, this.m30(),
            this.m01, this.m11, this.m21, this.m31(),
            this.m02, this.m12, this.m22, this.m32()
        );
    }

    @Override
    public float determinant() {
        return (this.m00 * (this.m11 * this.m22 - this.m12 * this.m21) - this.m01 * (this.m10 * this.m22 - this.m12 * this.m20) + this.m02 * (this.m10 * this.m21 - this.m11 * this.m20));
    }

    @Override
    public boolean isReversible() {
        return Math.abs(this.determinant()) > BananaMath.FLOAT_EPS;
    }

    @Override
    public IMatrix3FM mul(IMatrix3DC matrix) {
        double m1v00 = matrix.m00(), m1v01 = matrix.m01(), m1v02 = matrix.m02(), m1v03 = matrix.m03();
        double m1v10 = matrix.m10(), m1v11 = matrix.m11(), m1v12 = matrix.m12(), m1v13 = matrix.m13();
        double m1v20 = matrix.m20(), m1v21 = matrix.m21(), m1v22 = matrix.m22(), m1v23 = matrix.m23();
        float m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03;
        float m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13;
        float m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23;
        return this.set(
            (m1v00 * m2v00) + (m1v01 * m2v10) + (m1v02 * m2v20),
            (m1v00 * m2v01) + (m1v01 * m2v11) + (m1v02 * m2v21),
            (m1v00 * m2v02) + (m1v01 * m2v12) + (m1v02 * m2v22),
            (m1v00 * m2v03) + (m1v01 * m2v13) + (m1v02 * m2v23) + (m1v03),

            (m1v10 * m2v00) + (m1v11 * m2v10) + (m1v12 * m2v20),
            (m1v10 * m2v01) + (m1v11 * m2v11) + (m1v12 * m2v21),
            (m1v10 * m2v02) + (m1v11 * m2v12) + (m1v12 * m2v22),
            (m1v10 * m2v03) + (m1v11 * m2v13) + (m1v12 * m2v23) + (m1v13),

            (m1v20 * m2v00) + (m1v21 * m2v10) + (m1v22 * m2v20),
            (m1v20 * m2v01) + (m1v21 * m2v11) + (m1v22 * m2v21),
            (m1v20 * m2v02) + (m1v21 * m2v12) + (m1v22 * m2v22),
            (m1v20 * m2v03) + (m1v21 * m2v13) + (m1v22 * m2v23) + (m1v23)
        );
    }

    @Override
    public IMatrix3FM mul(IMatrix3FC matrix) {
        float m1v00 = matrix.m00(), m1v01 = matrix.m01(), m1v02 = matrix.m02(), m1v03 = matrix.m03();
        float m1v10 = matrix.m10(), m1v11 = matrix.m11(), m1v12 = matrix.m12(), m1v13 = matrix.m13();
        float m1v20 = matrix.m20(), m1v21 = matrix.m21(), m1v22 = matrix.m22(), m1v23 = matrix.m23();
        float m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03;
        float m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13;
        float m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23;
        return this.set(
            (m1v00 * m2v00) + (m1v01 * m2v10) + (m1v02 * m2v20),
            (m1v00 * m2v01) + (m1v01 * m2v11) + (m1v02 * m2v21),
            (m1v00 * m2v02) + (m1v01 * m2v12) + (m1v02 * m2v22),
            (m1v00 * m2v03) + (m1v01 * m2v13) + (m1v02 * m2v23) + (m1v03),

            (m1v10 * m2v00) + (m1v11 * m2v10) + (m1v12 * m2v20),
            (m1v10 * m2v01) + (m1v11 * m2v11) + (m1v12 * m2v21),
            (m1v10 * m2v02) + (m1v11 * m2v12) + (m1v12 * m2v22),
            (m1v10 * m2v03) + (m1v11 * m2v13) + (m1v12 * m2v23) + (m1v13),

            (m1v20 * m2v00) + (m1v21 * m2v10) + (m1v22 * m2v20),
            (m1v20 * m2v01) + (m1v21 * m2v11) + (m1v22 * m2v21),
            (m1v20 * m2v02) + (m1v21 * m2v12) + (m1v22 * m2v22),
            (m1v20 * m2v03) + (m1v21 * m2v13) + (m1v22 * m2v23) + (m1v23)
        );
    }

    @Override
    public IMatrix3FM mulPost(IMatrix3DC matrix) {
        float m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        float m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        float m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;
        double m2v00 = matrix.m00(), m2v01 = matrix.m01(), m2v02 = matrix.m02(), m2v03 = matrix.m03();
        double m2v10 = matrix.m10(), m2v11 = matrix.m11(), m2v12 = matrix.m12(), m2v13 = matrix.m13();
        double m2v20 = matrix.m20(), m2v21 = matrix.m21(), m2v22 = matrix.m22(), m2v23 = matrix.m23();
        return this.set(
            (m1v00 * m2v00) + (m1v01 * m2v10) + (m1v02 * m2v20),
            (m1v00 * m2v01) + (m1v01 * m2v11) + (m1v02 * m2v21),
            (m1v00 * m2v02) + (m1v01 * m2v12) + (m1v02 * m2v22),
            (m1v00 * m2v03) + (m1v01 * m2v13) + (m1v02 * m2v23) + (this.m03),

            (m1v10 * m2v00) + (m1v11 * m2v10) + (m1v12 * m2v20),
            (m1v10 * m2v01) + (m1v11 * m2v11) + (m1v12 * m2v21),
            (m1v10 * m2v02) + (m1v11 * m2v12) + (m1v12 * m2v22),
            (m1v10 * m2v03) + (m1v11 * m2v13) + (m1v12 * m2v23) + (this.m13),

            (m1v20 * m2v00) + (m1v21 * m2v10) + (m1v22 * m2v20),
            (m1v20 * m2v01) + (m1v21 * m2v11) + (m1v22 * m2v21),
            (m1v20 * m2v02) + (m1v21 * m2v12) + (m1v22 * m2v22),
            (m1v20 * m2v03) + (m1v21 * m2v13) + (m1v22 * m2v23) + (this.m23)
        );
    }

    @Override
    public IMatrix3FM mulPost(IMatrix3FC matrix) {
        float m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        float m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        float m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;
        float m2v00 = matrix.m00(), m2v01 = matrix.m01(), m2v02 = matrix.m02(), m2v03 = matrix.m03();
        float m2v10 = matrix.m10(), m2v11 = matrix.m11(), m2v12 = matrix.m12(), m2v13 = matrix.m13();
        float m2v20 = matrix.m20(), m2v21 = matrix.m21(), m2v22 = matrix.m22(), m2v23 = matrix.m23();
        return this.set(
            (m1v00 * m2v00) + (m1v01 * m2v10) + (m1v02 * m2v20),
            (m1v00 * m2v01) + (m1v01 * m2v11) + (m1v02 * m2v21),
            (m1v00 * m2v02) + (m1v01 * m2v12) + (m1v02 * m2v22),
            (m1v00 * m2v03) + (m1v01 * m2v13) + (m1v02 * m2v23) + (this.m03),

            (m1v10 * m2v00) + (m1v11 * m2v10) + (m1v12 * m2v20),
            (m1v10 * m2v01) + (m1v11 * m2v11) + (m1v12 * m2v21),
            (m1v10 * m2v02) + (m1v11 * m2v12) + (m1v12 * m2v22),
            (m1v10 * m2v03) + (m1v11 * m2v13) + (m1v12 * m2v23) + (this.m13),

            (m1v20 * m2v00) + (m1v21 * m2v10) + (m1v22 * m2v20),
            (m1v20 * m2v01) + (m1v21 * m2v11) + (m1v22 * m2v21),
            (m1v20 * m2v02) + (m1v21 * m2v12) + (m1v22 * m2v22),
            (m1v20 * m2v03) + (m1v21 * m2v13) + (m1v22 * m2v23) + (this.m23)
        );
    }

    @Override
    public IMatrix3FM div(int val) {
        return this.set(
            this.m00 / val, this.m01 / val, this.m02 / val, this.m03 / val,
            this.m10 / val, this.m11 / val, this.m12 / val, this.m13 / val,
            this.m20 / val, this.m21 / val, this.m22 / val, this.m23 / val
        );
    }

    @Override
    public IMatrix3FM div(float val) {
        return this.set(
            this.m00 / val, this.m01 / val, this.m02 / val, this.m03 / val,
            this.m10 / val, this.m11 / val, this.m12 / val, this.m13 / val,
            this.m20 / val, this.m21 / val, this.m22 / val, this.m23 / val
        );
    }

    @Override
    public IMatrix3FM div(double val) {
        return this.div((float) val);
    }

    @Override
    public IMatrix3FM scale(int val) {
        return this.set(
            (val * this.m00),
            (val * this.m01),
            (val * this.m02),
            (val * this.m03),

            (val * this.m10),
            (val * this.m11),
            (val * this.m12),
            (val * this.m13),

            (val * this.m20),
            (val * this.m21),
            (val * this.m22),
            (val * this.m23)
        );
    }

    @Override
    public IMatrix3FM scale(float val) {
        return this.set(
            (val * this.m00),
            (val * this.m01),
            (val * this.m02),
            (val * this.m03),

            (val * this.m10),
            (val * this.m11),
            (val * this.m12),
            (val * this.m13),

            (val * this.m20),
            (val * this.m21),
            (val * this.m22),
            (val * this.m23)
        );

    }

    @Override
    public IMatrix3FM scale(double val) {
        return this.scale((float) val);
    }

    @Override
    public IMatrix3FM scale(int x, int y, int z) {
        return this.set(
            (x * this.m00),
            (x * this.m01),
            (x * this.m02),
            (x * this.m03),

            (y * this.m10),
            (y * this.m11),
            (y * this.m12),
            (y * this.m13),

            (z * this.m20),
            (z * this.m21),
            (z * this.m22),
            (z * this.m23)
        );
    }

    @Override
    public IMatrix3FM scale(float x, float y, float z) {
        return this.set(
            (x * this.m00),
            (x * this.m01),
            (x * this.m02),
            (x * this.m03),

            (y * this.m10),
            (y * this.m11),
            (y * this.m12),
            (y * this.m13),

            (z * this.m20),
            (z * this.m21),
            (z * this.m22),
            (z * this.m23)
        );
    }

    @Override
    public IMatrix3FM scale(double x, double y, double z) {
        return this.scale((float) x, (float) y, (float) z);
    }

    @Override
    public IMatrix3FM scale(IVec3DC vec) {
        return this.scale(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3FM scale(IVec3IC vec) {
        return this.scale(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3FM scale(IVec2IC vec, int z) {
        return this.scale(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM scale(IVec2IC vec, double z) {
        return this.scale(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM scale(IVec2DC vec, int z) {
        return this.scale(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM scale(IVec2DC vec, double z) {
        return this.scale(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM scale(IVec2IC vec) {
        return this.scale(vec.x(), vec.y(), 1);
    }

    @Override
    public IMatrix3FM scale(IVec2DC vec) {
        return this.scale(vec.x(), vec.y(), 1.0D);
    }

    @Override
    public IMatrix3FM translate(int x, int y, int z) {
        return this.set(
            this.m00, this.m01, this.m02, this.m03 + x,
            this.m10, this.m11, this.m12, this.m13 + y,
            this.m20, this.m21, this.m22, this.m23 + z
        );
    }

    @Override
    public IMatrix3FM translate(float x, float y, float z) {
        return this.set(
            this.m00, this.m01, this.m02, this.m03 + x,
            this.m10, this.m11, this.m12, this.m13 + y,
            this.m20, this.m21, this.m22, this.m23 + z
        );
    }

    @Override
    public IMatrix3FM translate(double x, double y, double z) {
        return this.translate((float) x, (float) y, (float) z);
    }

    @Override
    public IMatrix3FM translate(IVec3DC vec) {
        return this.translate(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3FM translate(IVec3IC vec) {
        return this.translate(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3FM translate(IVec2IC vec, int z) {
        return this.translate(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM translate(IVec2IC vec, double z) {
        return this.translate(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM translate(IVec2DC vec, int z) {
        return this.translate(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM translate(IVec2DC vec, double z) {
        return this.translate(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM translate(IVec2IC vec) {
        return this.translate(vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3FM translate(IVec2DC vec) {
        return this.translate(vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3FM rotateX(float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13;
        float m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23;
        return this.set(
            this.m00, this.m01, this.m02, this.m03,

            (cos * m2v10) + (-sin * m2v20),
            (cos * m2v11) + (-sin * m2v21),
            (cos * m2v12) + (-sin * m2v22),
            (cos * m2v13) + (-sin * m2v23),

            (sin * m2v10) + (cos * m2v20),
            (sin * m2v11) + (cos * m2v21),
            (sin * m2v12) + (cos * m2v22),
            (sin * m2v13) + (cos * m2v23)
        );
    }

    @Override
    public IMatrix3FM rotateY(float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03;
        float m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23;
        return this.set(
            (cos * m2v00) + (sin * m2v20),
            (cos * m2v01) + (sin * m2v21),
            (cos * m2v02) + (sin * m2v22),
            (cos * m2v03) + (sin * m2v23),

            this.m10, this.m11, this.m12, this.m13,

            (-sin * m2v00) + (cos * m2v20),
            (-sin * m2v01) + (cos * m2v21),
            (-sin * m2v02) + (cos * m2v22),
            (-sin * m2v03) + (cos * m2v23)
        );
    }

    @Override
    public IMatrix3FM rotateZ(float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03;
        float m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13;
        return this.set(
            (cos * m2v00) + (-sin * m2v10),
            (cos * m2v01) + (-sin * m2v11),
            (cos * m2v02) + (-sin * m2v12),
            (cos * m2v03) + (-sin * m2v13),

            (sin * m2v00) + (cos * m2v10),
            (sin * m2v01) + (cos * m2v11),
            (sin * m2v02) + (cos * m2v12),
            (sin * m2v03) + (cos * m2v13),

            this.m20, this.m21, this.m22, this.m23
        );
    }

    @Override
    public IMatrix3FM rotate(float degrees, int x, int y, int z) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }
        float l = (float) Math.sqrt(x * x + y * y + z * z);
        if (l < BananaMath.FLOAT_EPS) {
            return this;
        }
        float ax = x / l, ay = y / l, az = z / l;
        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);
        float cosM1 = 1.0F - cos;

        float m1v00 = cos + ax * ax * cosM1, m1v01 = ax * ay * cosM1 - az * sin, m1v02 = ax * az * cosM1 + ay * sin;
        float m1v10 = ay * ax * cosM1 + az * sin, m1v11 = cos + ay * ay * cosM1, m1v12 = ay * az * cosM1 - ax * sin;
        float m1v20 = az * ax * cosM1 - ay * sin, m1v21 = az * ay * cosM1 + ax * sin, m1v22 = cos + az * az * cosM1;

        float m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03;
        float m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13;
        float m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23;
        return this.set(
            (m1v00 * m2v00) + (m1v01 * m2v10) + (m1v02 * m2v20),
            (m1v00 * m2v01) + (m1v01 * m2v11) + (m1v02 * m2v21),
            (m1v00 * m2v02) + (m1v01 * m2v12) + (m1v02 * m2v22),
            (m1v00 * m2v03) + (m1v01 * m2v13) + (m1v02 * m2v23),

            (m1v10 * m2v00) + (m1v11 * m2v10) + (m1v12 * m2v20),
            (m1v10 * m2v01) + (m1v11 * m2v11) + (m1v12 * m2v21),
            (m1v10 * m2v02) + (m1v11 * m2v12) + (m1v12 * m2v22),
            (m1v10 * m2v03) + (m1v11 * m2v13) + (m1v12 * m2v23),

            (m1v20 * m2v00) + (m1v21 * m2v10) + (m1v22 * m2v20),
            (m1v20 * m2v01) + (m1v21 * m2v11) + (m1v22 * m2v21),
            (m1v20 * m2v02) + (m1v21 * m2v12) + (m1v22 * m2v22),
            (m1v20 * m2v03) + (m1v21 * m2v13) + (m1v22 * m2v23)
        );
    }

    @Override
    public IMatrix3FM rotate(float degrees, float x, float y, float z) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }
        float l = (float) Math.sqrt(x * x + y * y + z * z);
        if (l < BananaMath.FLOAT_EPS) {
            return this;
        }
        float ax = x / l, ay = y / l, az = z / l;
        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);
        float cosM1 = 1.0F - cos;

        float m1v00 = cos + ax * ax * cosM1, m1v01 = ax * ay * cosM1 - az * sin, m1v02 = ax * az * cosM1 + ay * sin;
        float m1v10 = ay * ax * cosM1 + az * sin, m1v11 = cos + ay * ay * cosM1, m1v12 = ay * az * cosM1 - ax * sin;
        float m1v20 = az * ax * cosM1 - ay * sin, m1v21 = az * ay * cosM1 + ax * sin, m1v22 = cos + az * az * cosM1;

        float m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03;
        float m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13;
        float m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23;
        return this.set(
            (m1v00 * m2v00) + (m1v01 * m2v10) + (m1v02 * m2v20),
            (m1v00 * m2v01) + (m1v01 * m2v11) + (m1v02 * m2v21),
            (m1v00 * m2v02) + (m1v01 * m2v12) + (m1v02 * m2v22),
            (m1v00 * m2v03) + (m1v01 * m2v13) + (m1v02 * m2v23),

            (m1v10 * m2v00) + (m1v11 * m2v10) + (m1v12 * m2v20),
            (m1v10 * m2v01) + (m1v11 * m2v11) + (m1v12 * m2v21),
            (m1v10 * m2v02) + (m1v11 * m2v12) + (m1v12 * m2v22),
            (m1v10 * m2v03) + (m1v11 * m2v13) + (m1v12 * m2v23),

            (m1v20 * m2v00) + (m1v21 * m2v10) + (m1v22 * m2v20),
            (m1v20 * m2v01) + (m1v21 * m2v11) + (m1v22 * m2v21),
            (m1v20 * m2v02) + (m1v21 * m2v12) + (m1v22 * m2v22),
            (m1v20 * m2v03) + (m1v21 * m2v13) + (m1v22 * m2v23)
        );
    }

    @Override
    public IMatrix3FM rotate(float degrees, double x, double y, double z) {
        return this.rotate(degrees, (float) x, (float) y, (float) z);
    }

    @Override
    public IMatrix3FM rotate(float degrees, IVec3DC vec) {
        return this.rotate(degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3FM rotate(float degrees, IVec3IC vec) {
        return this.rotate(degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3FM rotate(float degrees, IVec2IC vec, int z) {
        return this.rotate(degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM rotate(float degrees, IVec2IC vec, double z) {
        return this.rotate(degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM rotate(float degrees, IVec2DC vec, int z) {
        return this.rotate(degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM rotate(float degrees, IVec2DC vec, double z) {
        return this.rotate(degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM rotate(float degrees, IVec2IC vec) {
        return this.rotate(degrees, vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3FM rotate(float degrees, IVec2DC vec) {
        return this.rotate(degrees, vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3FM rotateXAround(int x, int y, int z, float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13 - y;
        float m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23 - z;
        return this.set(
            this.m00, this.m01, this.m02, this.m03,

            (cos * m2v10) + (-sin * m2v20),
            (cos * m2v11) + (-sin * m2v21),
            (cos * m2v12) + (-sin * m2v22),
            (cos * m2v13) + (-sin * m2v23) + y,

            (sin * m2v10) + (cos * m2v20),
            (sin * m2v11) + (cos * m2v21),
            (sin * m2v12) + (cos * m2v22),
            (sin * m2v13) + (cos * m2v23) + z
        );
    }

    @Override
    public IMatrix3FM rotateXAround(float x, float y, float z, float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13 - y;
        float m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23 - z;
        return this.set(
            this.m00, this.m01, this.m02, this.m03,

            (cos * m2v10) + (-sin * m2v20),
            (cos * m2v11) + (-sin * m2v21),
            (cos * m2v12) + (-sin * m2v22),
            (cos * m2v13) + (-sin * m2v23) + y,

            (sin * m2v10) + (cos * m2v20),
            (sin * m2v11) + (cos * m2v21),
            (sin * m2v12) + (cos * m2v22),
            (sin * m2v13) + (cos * m2v23) + z
        );
    }

    @Override
    public IMatrix3FM rotateXAround(double x, double y, double z, float degrees) {
        return this.rotateXAround((float) x, (float) y, (float) z, degrees);
    }

    @Override
    public IMatrix3FM rotateXAround(IVec3IC point, float degrees) {
        return this.rotateXAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3FM rotateXAround(IVec3DC point, float degrees) {
        return this.rotateXAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3FM rotateXAround(IVec2IC vec, int z, float degrees) {
        return this.rotateXAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3FM rotateXAround(IVec2IC vec, double z, float degrees) {
        return this.rotateXAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3FM rotateXAround(IVec2DC vec, int z, float degrees) {
        return this.rotateXAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3FM rotateXAround(IVec2DC vec, double z, float degrees) {
        return this.rotateXAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3FM rotateXAround(IVec2IC vec, float degrees) {
        return this.rotateXAround(vec.x(), vec.y(), 0, degrees);
    }

    @Override
    public IMatrix3FM rotateXAround(IVec2DC vec, float degrees) {
        return this.rotateXAround(vec.x(), vec.y(), 0.0D, degrees);
    }

    @Override
    public IMatrix3FM rotateYAround(int x, int y, int z, float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03 - x;
        float m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23 - z;
        return this.set(
            (cos * m2v00) + (sin * m2v20),
            (cos * m2v01) + (sin * m2v21),
            (cos * m2v02) + (sin * m2v22),
            (cos * m2v03) + (sin * m2v23) + x,

            this.m10, this.m11, this.m12, this.m13,

            (-sin * m2v00) + (cos * m2v20),
            (-sin * m2v01) + (cos * m2v21),
            (-sin * m2v02) + (cos * m2v22),
            (-sin * m2v03) + (cos * m2v23) + z
        );
    }

    @Override
    public IMatrix3FM rotateYAround(float x, float y, float z, float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03 - x;
        float m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23 - z;
        return this.set(
            (cos * m2v00) + (sin * m2v20),
            (cos * m2v01) + (sin * m2v21),
            (cos * m2v02) + (sin * m2v22),
            (cos * m2v03) + (sin * m2v23) + x,

            this.m10, this.m11, this.m12, this.m13,

            (-sin * m2v00) + (cos * m2v20),
            (-sin * m2v01) + (cos * m2v21),
            (-sin * m2v02) + (cos * m2v22),
            (-sin * m2v03) + (cos * m2v23) + z
        );
    }

    @Override
    public IMatrix3FM rotateYAround(double x, double y, double z, float degrees) {
        return this.rotateYAround((float) x, (float) y, (float) z, degrees);
    }

    @Override
    public IMatrix3FM rotateYAround(IVec3IC point, float degrees) {
        return this.rotateYAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3FM rotateYAround(IVec3DC point, float degrees) {
        return this.rotateYAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3FM rotateYAround(IVec2IC vec, int z, float degrees) {
        return this.rotateYAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3FM rotateYAround(IVec2IC vec, double z, float degrees) {
        return this.rotateYAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3FM rotateYAround(IVec2DC vec, int z, float degrees) {
        return this.rotateYAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3FM rotateYAround(IVec2DC vec, double z, float degrees) {
        return this.rotateYAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3FM rotateYAround(IVec2IC vec, float degrees) {
        return this.rotateYAround(vec.x(), vec.y(), 0, degrees);
    }

    @Override
    public IMatrix3FM rotateYAround(IVec2DC vec, float degrees) {
        return this.rotateYAround(vec.x(), vec.y(), 0.0D, degrees);
    }

    @Override
    public IMatrix3FM rotateZAround(int x, int y, int z, float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03 - x;
        float m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13 - y;
        return this.set(
            (cos * m2v00) + (-sin * m2v10),
            (cos * m2v01) + (-sin * m2v11),
            (cos * m2v02) + (-sin * m2v12),
            (cos * m2v03) + (-sin * m2v13) + x,

            (sin * m2v00) + (cos * m2v10),
            (sin * m2v01) + (cos * m2v11),
            (sin * m2v02) + (cos * m2v12),
            (sin * m2v03) + (cos * m2v13) + y,

            this.m20, this.m21, this.m22, this.m23
        );
    }

    @Override
    public IMatrix3FM rotateZAround(float x, float y, float z, float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03 - x;
        float m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13 - y;
        return this.set(
            (cos * m2v00) + (-sin * m2v10),
            (cos * m2v01) + (-sin * m2v11),
            (cos * m2v02) + (-sin * m2v12),
            (cos * m2v03) + (-sin * m2v13) + x,

            (sin * m2v00) + (cos * m2v10),
            (sin * m2v01) + (cos * m2v11),
            (sin * m2v02) + (cos * m2v12),
            (sin * m2v03) + (cos * m2v13) + y,

            this.m20, this.m21, this.m22, this.m23
        );
    }

    @Override
    public IMatrix3FM rotateZAround(double x, double y, double z, float degrees) {
        return this.rotateZAround((float) x, (float) y, (float) z, degrees);
    }

    @Override
    public IMatrix3FM rotateZAround(IVec3IC point, float degrees) {
        return this.rotateZAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3FM rotateZAround(IVec3DC point, float degrees) {
        return this.rotateZAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3FM rotateZAround(IVec2IC vec, int z, float degrees) {
        return this.rotateZAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3FM rotateZAround(IVec2IC vec, double z, float degrees) {
        return this.rotateZAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3FM rotateZAround(IVec2DC vec, int z, float degrees) {
        return this.rotateZAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3FM rotateZAround(IVec2DC vec, double z, float degrees) {
        return this.rotateZAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3FM rotateZAround(IVec2IC vec, float degrees) {
        return this.rotateZAround(vec.x(), vec.y(), 0, degrees);
    }

    @Override
    public IMatrix3FM rotateZAround(IVec2DC vec, float degrees) {
        return this.rotateZAround(vec.x(), vec.y(), 0.0D, degrees);
    }

    @Override
    public IMatrix3FM rotateAround(IVec3DC point, float degrees, int x, int y, int z) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }
        float l = (float) Math.sqrt(x * x + y * y + z * z);
        if (l < BananaMath.FLOAT_EPS) {
            return this;
        }
        float ax = x / l, ay = y / l, az = z / l;
        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);
        float cosM1 = 1 - cos;

        float m1v00 = cos + ax * ax * cosM1, m1v01 = ax * ay * cosM1 - az * sin, m1v02 = ax * az * cosM1 + ay * sin;
        float m1v10 = ay * ax * cosM1 + az * sin, m1v11 = cos + ay * ay * cosM1, m1v12 = ay * az * cosM1 - ax * sin;
        float m1v20 = az * ax * cosM1 - ay * sin, m1v21 = az * ay * cosM1 + ax * sin, m1v22 = cos + az * az * cosM1;

        float m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = (float) (this.m03 - point.x());
        float m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = (float) (this.m13 - point.y());
        float m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = (float) (this.m23 - point.z());
        return this.set(
            (m1v00 * m2v00) + (m1v01 * m2v10) + (m1v02 * m2v20),
            (m1v00 * m2v01) + (m1v01 * m2v11) + (m1v02 * m2v21),
            (m1v00 * m2v02) + (m1v01 * m2v12) + (m1v02 * m2v22),
            (m1v00 * m2v03) + (m1v01 * m2v13) + (m1v02 * m2v23) + point.x(),

            (m1v10 * m2v00) + (m1v11 * m2v10) + (m1v12 * m2v20),
            (m1v10 * m2v01) + (m1v11 * m2v11) + (m1v12 * m2v21),
            (m1v10 * m2v02) + (m1v11 * m2v12) + (m1v12 * m2v22),
            (m1v10 * m2v03) + (m1v11 * m2v13) + (m1v12 * m2v23) + point.y(),

            (m1v20 * m2v00) + (m1v21 * m2v10) + (m1v22 * m2v20),
            (m1v20 * m2v01) + (m1v21 * m2v11) + (m1v22 * m2v21),
            (m1v20 * m2v02) + (m1v21 * m2v12) + (m1v22 * m2v22),
            (m1v20 * m2v03) + (m1v21 * m2v13) + (m1v22 * m2v23) + point.z()
        );
    }

    @Override
    public IMatrix3FM rotateAround(IVec3DC point, float degrees, float x, float y, float z) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }
        float l = (float) Math.sqrt(x * x + y * y + z * z);
        if (l < BananaMath.FLOAT_EPS) {
            return this;
        }
        float ax = x / l, ay = y / l, az = z / l;
        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);
        float cosM1 = 1 - cos;

        float m1v00 = cos + ax * ax * cosM1, m1v01 = ax * ay * cosM1 - az * sin, m1v02 = ax * az * cosM1 + ay * sin;
        float m1v10 = ay * ax * cosM1 + az * sin, m1v11 = cos + ay * ay * cosM1, m1v12 = ay * az * cosM1 - ax * sin;
        float m1v20 = az * ax * cosM1 - ay * sin, m1v21 = az * ay * cosM1 + ax * sin, m1v22 = cos + az * az * cosM1;

        float m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = (float) (this.m03 - point.x());
        float m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = (float) (this.m13 - point.y());
        float m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = (float) (this.m23 - point.z());
        return this.set(
            (m1v00 * m2v00) + (m1v01 * m2v10) + (m1v02 * m2v20),
            (m1v00 * m2v01) + (m1v01 * m2v11) + (m1v02 * m2v21),
            (m1v00 * m2v02) + (m1v01 * m2v12) + (m1v02 * m2v22),
            (m1v00 * m2v03) + (m1v01 * m2v13) + (m1v02 * m2v23) + point.x(),

            (m1v10 * m2v00) + (m1v11 * m2v10) + (m1v12 * m2v20),
            (m1v10 * m2v01) + (m1v11 * m2v11) + (m1v12 * m2v21),
            (m1v10 * m2v02) + (m1v11 * m2v12) + (m1v12 * m2v22),
            (m1v10 * m2v03) + (m1v11 * m2v13) + (m1v12 * m2v23) + point.y(),

            (m1v20 * m2v00) + (m1v21 * m2v10) + (m1v22 * m2v20),
            (m1v20 * m2v01) + (m1v21 * m2v11) + (m1v22 * m2v21),
            (m1v20 * m2v02) + (m1v21 * m2v12) + (m1v22 * m2v22),
            (m1v20 * m2v03) + (m1v21 * m2v13) + (m1v22 * m2v23) + point.z()
        );
    }

    @Override
    public IMatrix3FM rotateAround(IVec3DC point, float degrees, double x, double y, double z) {
        return this.rotateAround(point, degrees, (float) x, (float) y, (float) z);
    }

    @Override
    public IMatrix3FM rotateAround(IVec3DC point, float degrees, IVec3DC vec) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3FM rotateAround(IVec3DC point, float degrees, IVec3IC vec) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3FM rotateAround(IVec3DC point, float degrees, IVec2IC vec, int z) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM rotateAround(IVec3DC point, float degrees, IVec2IC vec, double z) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM rotateAround(IVec3DC point, float degrees, IVec2DC vec, int z) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM rotateAround(IVec3DC point, float degrees, IVec2DC vec, double z) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM rotateAround(IVec3DC point, float degrees, IVec2IC vec) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3FM rotateAround(IVec3DC point, float degrees, IVec2DC vec) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), 0.0D);
    }

    @Override
    public IMatrix3FM rotateAround(IVec3IC point, float degrees, int x, int y, int z) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }
        float l = (float) Math.sqrt(x * x + y * y + z * z);
        if (l < BananaMath.FLOAT_EPS) {
            return this;
        }
        float ax = x / l, ay = y / l, az = z / l;
        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);
        float cosM1 = 1 - cos;

        float m1v00 = cos + ax * ax * cosM1, m1v01 = ax * ay * cosM1 - az * sin, m1v02 = ax * az * cosM1 + ay * sin;
        float m1v10 = ay * ax * cosM1 + az * sin, m1v11 = cos + ay * ay * cosM1, m1v12 = ay * az * cosM1 - ax * sin;
        float m1v20 = az * ax * cosM1 - ay * sin, m1v21 = az * ay * cosM1 + ax * sin, m1v22 = cos + az * az * cosM1;

        float m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03 - point.x();
        float m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13 - point.y();
        float m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23 - point.z();
        return this.set(
            (m1v00 * m2v00) + (m1v01 * m2v10) + (m1v02 * m2v20),
            (m1v00 * m2v01) + (m1v01 * m2v11) + (m1v02 * m2v21),
            (m1v00 * m2v02) + (m1v01 * m2v12) + (m1v02 * m2v22),
            (m1v00 * m2v03) + (m1v01 * m2v13) + (m1v02 * m2v23) + point.x(),

            (m1v10 * m2v00) + (m1v11 * m2v10) + (m1v12 * m2v20),
            (m1v10 * m2v01) + (m1v11 * m2v11) + (m1v12 * m2v21),
            (m1v10 * m2v02) + (m1v11 * m2v12) + (m1v12 * m2v22),
            (m1v10 * m2v03) + (m1v11 * m2v13) + (m1v12 * m2v23) + point.y(),

            (m1v20 * m2v00) + (m1v21 * m2v10) + (m1v22 * m2v20),
            (m1v20 * m2v01) + (m1v21 * m2v11) + (m1v22 * m2v21),
            (m1v20 * m2v02) + (m1v21 * m2v12) + (m1v22 * m2v22),
            (m1v20 * m2v03) + (m1v21 * m2v13) + (m1v22 * m2v23) + point.z()
        );
    }

    @Override
    public IMatrix3FM rotateAround(IVec3IC point, float degrees, float x, float y, float z) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }
        float l = (float) Math.sqrt(x * x + y * y + z * z);
        if (l < BananaMath.FLOAT_EPS) {
            return this;
        }
        float ax = x / l, ay = y / l, az = z / l;
        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);
        float cosM1 = 1 - cos;

        float m1v00 = cos + ax * ax * cosM1, m1v01 = ax * ay * cosM1 - az * sin, m1v02 = ax * az * cosM1 + ay * sin;
        float m1v10 = ay * ax * cosM1 + az * sin, m1v11 = cos + ay * ay * cosM1, m1v12 = ay * az * cosM1 - ax * sin;
        float m1v20 = az * ax * cosM1 - ay * sin, m1v21 = az * ay * cosM1 + ax * sin, m1v22 = cos + az * az * cosM1;

        float m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03 - point.x();
        float m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13 - point.y();
        float m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23 - point.z();
        return this.set(
            (m1v00 * m2v00) + (m1v01 * m2v10) + (m1v02 * m2v20),
            (m1v00 * m2v01) + (m1v01 * m2v11) + (m1v02 * m2v21),
            (m1v00 * m2v02) + (m1v01 * m2v12) + (m1v02 * m2v22),
            (m1v00 * m2v03) + (m1v01 * m2v13) + (m1v02 * m2v23) + point.x(),

            (m1v10 * m2v00) + (m1v11 * m2v10) + (m1v12 * m2v20),
            (m1v10 * m2v01) + (m1v11 * m2v11) + (m1v12 * m2v21),
            (m1v10 * m2v02) + (m1v11 * m2v12) + (m1v12 * m2v22),
            (m1v10 * m2v03) + (m1v11 * m2v13) + (m1v12 * m2v23) + point.y(),

            (m1v20 * m2v00) + (m1v21 * m2v10) + (m1v22 * m2v20),
            (m1v20 * m2v01) + (m1v21 * m2v11) + (m1v22 * m2v21),
            (m1v20 * m2v02) + (m1v21 * m2v12) + (m1v22 * m2v22),
            (m1v20 * m2v03) + (m1v21 * m2v13) + (m1v22 * m2v23) + point.z()
        );
    }

    @Override
    public IMatrix3FM rotateAround(IVec3IC point, float degrees, double x, double y, double z) {
        return this.rotateAround(point, degrees, (float) x, (float) y, (float) z);
    }

    @Override
    public IMatrix3FM rotateAround(IVec3IC point, float degrees, IVec3DC vec) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3FM rotateAround(IVec3IC point, float degrees, IVec3IC vec) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3FM rotateAround(IVec3IC point, float degrees, IVec2IC vec, int z) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM rotateAround(IVec3IC point, float degrees, IVec2IC vec, double z) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM rotateAround(IVec3IC point, float degrees, IVec2DC vec, int z) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM rotateAround(IVec3IC point, float degrees, IVec2DC vec, double z) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM rotateAround(IVec3IC point, float degrees, IVec2IC vec) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3FM rotateAround(IVec3IC point, float degrees, IVec2DC vec) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), 0.0D);
    }

    @Override
    public IMatrix3FM localScale(int val) {
        return this.set(
            (this.m00 * val),
            (this.m01 * val),
            (this.m02 * val),
            this.m03,

            (this.m10 * val),
            (this.m11 * val),
            (this.m12 * val),
            this.m13,

            (this.m20 * val),
            (this.m21 * val),
            (this.m22 * val),
            this.m23
        );
    }

    @Override
    public IMatrix3FM localScale(float val) {
        return this.set(
            (this.m00 * val),
            (this.m01 * val),
            (this.m02 * val),
            this.m03,

            (this.m10 * val),
            (this.m11 * val),
            (this.m12 * val),
            this.m13,

            (this.m20 * val),
            (this.m21 * val),
            (this.m22 * val),
            this.m23
        );
    }

    @Override
    public IMatrix3FM localScale(double val) {
        return this.localScale((float) val);
    }

    @Override
    public IMatrix3FM localScale(int x, int y, int z) {
        return this.set(
            (this.m00 * x),
            (this.m01 * x),
            (this.m02 * x),
            this.m03,

            (this.m10 * y),
            (this.m11 * y),
            (this.m12 * y),
            this.m13,

            (this.m20 * z),
            (this.m21 * z),
            (this.m22 * z),
            this.m23
        );
    }

    @Override
    public IMatrix3FM localScale(float x, float y, float z) {
        return this.set(
            (this.m00 * x),
            (this.m01 * x),
            (this.m02 * x),
            this.m03,

            (this.m10 * y),
            (this.m11 * y),
            (this.m12 * y),
            this.m13,

            (this.m20 * z),
            (this.m21 * z),
            (this.m22 * z),
            this.m23
        );
    }

    @Override
    public IMatrix3FM localScale(double x, double y, double z) {
        return this.localScale((float) x, (float) y, (float) z);
    }

    @Override
    public IMatrix3FM localScale(IVec3DC vec) {
        return this.localScale(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3FM localScale(IVec3IC vec) {
        return this.localScale(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3FM localScale(IVec2IC vec, int z) {
        return this.localScale(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM localScale(IVec2IC vec, double z) {
        return this.localScale(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM localScale(IVec2DC vec, int z) {
        return this.localScale(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM localScale(IVec2DC vec, double z) {
        return this.localScale(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM localScale(IVec2IC vec) {
        return this.localScale(vec.x(), vec.y(), 1);
    }

    @Override
    public IMatrix3FM localScale(IVec2DC vec) {
        return this.localScale(vec.x(), vec.y(), 1.0D);
    }

    @Override
    public IMatrix3FM localTranslate(int x, int y, int z) {
        float m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        float m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        float m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;
        return this.set(
            m1v00, m1v01, m1v02,
            (m1v00 * x) + (m1v01 * y) + (m1v02 * z) + (this.m03),

            m1v10, m1v11, m1v12,
            (m1v10 * x) + (m1v11 * y) + (m1v12 * z) + (this.m13),

            m1v20, m1v21, m1v22,
            (m1v20 * x) + (m1v21 * y) + (m1v22 * z) + (this.m23)
        );
    }

    @Override
    public IMatrix3FM localTranslate(float x, float y, float z) {
        float m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        float m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        float m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;
        return this.set(
            m1v00, m1v01, m1v02,
            (m1v00 * x) + (m1v01 * y) + (m1v02 * z) + (this.m03),

            m1v10, m1v11, m1v12,
            (m1v10 * x) + (m1v11 * y) + (m1v12 * z) + (this.m13),

            m1v20, m1v21, m1v22,
            (m1v20 * x) + (m1v21 * y) + (m1v22 * z) + (this.m23)
        );
    }

    @Override
    public IMatrix3FM localTranslate(double x, double y, double z) {
        return this.localTranslate((float) x, (float) y, (float) z);
    }

    @Override
    public IMatrix3FM localTranslate(IVec3DC vec) {
        return this.localTranslate(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3FM localTranslate(IVec3IC vec) {
        return this.localTranslate(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3FM localTranslate(IVec2IC vec, int z) {
        return this.localTranslate(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM localTranslate(IVec2IC vec, double z) {
        return this.localTranslate(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM localTranslate(IVec2DC vec, int z) {
        return this.localTranslate(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM localTranslate(IVec2DC vec, double z) {
        return this.localTranslate(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM localTranslate(IVec2IC vec) {
        return this.localTranslate(vec.x(), vec.y(), 0.0D);
    }

    @Override
    public IMatrix3FM localTranslate(IVec2DC vec) {
        return this.localTranslate(vec.x(), vec.y(), 0.0D);
    }

    @Override
    public IMatrix3FM localRotateX(float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m1v01 = this.m01, m1v02 = this.m02;
        float m1v11 = this.m11, m1v12 = this.m12;
        float m1v21 = this.m21, m1v22 = this.m22;
        return this.set(
            this.m00,
            (m1v01 * cos) + (m1v02 * sin),
            (m1v01 * -sin) + (m1v02 * cos),
            this.m03,

            this.m10,
            (m1v11 * cos) + (m1v12 * sin),
            (m1v11 * -sin) + (m1v12 * cos),
            this.m13,

            this.m20,
            (m1v21 * cos) + (m1v22 * sin),
            (m1v21 * -sin) + (m1v22 * cos),
            this.m23
        );
    }

    @Override
    public IMatrix3FM localRotateY(float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m1v00 = this.m00, m1v02 = this.m02;
        float m1v10 = this.m10, m1v12 = this.m12;
        float m1v20 = this.m20, m1v22 = this.m22;
        return this.set(
            (m1v00 * cos) + (m1v02 * -sin),
            this.m01,
            (m1v00 * sin) + (m1v02 * cos),
            this.m03,

            (m1v10 * cos) + (m1v12 * -sin),
            this.m11,
            (m1v10 * sin) + (m1v12 * cos),
            this.m13,

            (m1v20 * cos) + (m1v22 * -sin),
            this.m21,
            (m1v20 * sin) + (m1v22 * cos),
            this.m23
        );
    }

    @Override
    public IMatrix3FM localRotateZ(float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m1v00 = this.m00, m1v01 = this.m01;
        float m1v10 = this.m10, m1v11 = this.m11;
        float m1v20 = this.m20, m1v21 = this.m21;
        return this.set(
            (m1v00 * cos) + (m1v01 * sin),
            (m1v00 * -sin) + (m1v01 * cos),
            this.m02, this.m03,

            (m1v10 * cos) + (m1v11 * sin),
            (m1v10 * -sin) + (m1v11 * cos),
            this.m12, this.m13,

            (m1v20 * cos) + (m1v21 * sin),
            (m1v20 * -sin) + (m1v21 * cos),
            this.m22, this.m23
        );
    }

    @Override
    public IMatrix3FM localRotate(float degrees, int x, int y, int z) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }
        float l = (float) Math.sqrt(x * x + y * y + z * z);
        if (l < BananaMath.FLOAT_EPS) {
            return this;
        }
        float ax = x / l, ay = y / l, az = z / l;
        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);
        float cosM1 = 1 - cos;

        float m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        float m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        float m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;
        float m2v00 = cos + ax * ax * cosM1, m2v01 = ax * ay * cosM1 - az * sin, m2v02 = ax * az * cosM1 + ay * sin;
        float m2v10 = ay * ax * cosM1 + az * sin, m2v11 = cos + ay * ay * cosM1, m2v12 = ay * az * cosM1 - ax * sin;
        float m2v20 = az * ax * cosM1 - ay * sin, m2v21 = az * ay * cosM1 + ax * sin, m2v22 = cos + az * az * cosM1;
        return this.set(
            (m1v00 * m2v00) + (m1v01 * m2v10) + (m1v02 * m2v20),
            (m1v00 * m2v01) + (m1v01 * m2v11) + (m1v02 * m2v21),
            (m1v00 * m2v02) + (m1v01 * m2v12) + (m1v02 * m2v22),
            this.m03,

            (m1v10 * m2v00) + (m1v11 * m2v10) + (m1v12 * m2v20),
            (m1v10 * m2v01) + (m1v11 * m2v11) + (m1v12 * m2v21),
            (m1v10 * m2v02) + (m1v11 * m2v12) + (m1v12 * m2v22),
            this.m13,

            (m1v20 * m2v00) + (m1v21 * m2v10) + (m1v22 * m2v20),
            (m1v20 * m2v01) + (m1v21 * m2v11) + (m1v22 * m2v21),
            (m1v20 * m2v02) + (m1v21 * m2v12) + (m1v22 * m2v22),
            this.m23
        );
    }

    @Override
    public IMatrix3FM localRotate(float degrees, float x, float y, float z) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }
        float l = (float) Math.sqrt(x * x + y * y + z * z);
        if (l < BananaMath.FLOAT_EPS) {
            return this;
        }
        float ax = x / l, ay = y / l, az = z / l;
        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);
        float cosM1 = 1 - cos;

        float m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        float m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        float m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;
        float m2v00 = cos + ax * ax * cosM1, m2v01 = ax * ay * cosM1 - az * sin, m2v02 = ax * az * cosM1 + ay * sin;
        float m2v10 = ay * ax * cosM1 + az * sin, m2v11 = cos + ay * ay * cosM1, m2v12 = ay * az * cosM1 - ax * sin;
        float m2v20 = az * ax * cosM1 - ay * sin, m2v21 = az * ay * cosM1 + ax * sin, m2v22 = cos + az * az * cosM1;
        return this.set(
            (m1v00 * m2v00) + (m1v01 * m2v10) + (m1v02 * m2v20),
            (m1v00 * m2v01) + (m1v01 * m2v11) + (m1v02 * m2v21),
            (m1v00 * m2v02) + (m1v01 * m2v12) + (m1v02 * m2v22),
            this.m03,

            (m1v10 * m2v00) + (m1v11 * m2v10) + (m1v12 * m2v20),
            (m1v10 * m2v01) + (m1v11 * m2v11) + (m1v12 * m2v21),
            (m1v10 * m2v02) + (m1v11 * m2v12) + (m1v12 * m2v22),
            this.m13,

            (m1v20 * m2v00) + (m1v21 * m2v10) + (m1v22 * m2v20),
            (m1v20 * m2v01) + (m1v21 * m2v11) + (m1v22 * m2v21),
            (m1v20 * m2v02) + (m1v21 * m2v12) + (m1v22 * m2v22),
            this.m23
        );
    }

    @Override
    public IMatrix3FM localRotate(float degrees, double x, double y, double z) {
        return this.localRotate(degrees, (float) x, (float) y, (float) z);
    }

    @Override
    public IMatrix3FM localRotate(float degrees, IVec3DC vec) {
        return this.localRotate(degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3FM localRotate(float degrees, IVec3IC vec) {
        return this.localRotate(degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3FM localRotate(float degrees, IVec2IC vec, int z) {
        return this.localRotate(degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM localRotate(float degrees, IVec2IC vec, double z) {
        return this.localRotate(degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM localRotate(float degrees, IVec2DC vec, int z) {
        return this.localRotate(degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM localRotate(float degrees, IVec2DC vec, double z) {
        return this.localRotate(degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM localRotate(float degrees, IVec2IC vec) {
        return this.localRotate(degrees, vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3FM localRotate(float degrees, IVec2DC vec) {
        return this.localRotate(degrees, vec.x(), vec.y(), 0.0D);
    }

    @Override
    public IMatrix3FM localRotateXAround(int x, int y, int z, float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        float m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        float m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;

        float m2v01 = (m1v01 * cos) + (m1v02 * sin), m2v02 = (m1v01 * -sin) + (m1v02 * cos), m2v03 = (m1v00 * x) + (m1v01 * y) + (m1v02 * z) + (this.m03);
        float m2v11 = (m1v11 * cos) + (m1v12 * sin), m2v12 = (m1v11 * -sin) + (m1v12 * cos), m2v13 = (m1v10 * x) + (m1v11 * y) + (m1v12 * z) + (this.m13);
        float m2v21 = (m1v21 * cos) + (m1v22 * sin), m2v22 = (m1v21 * -sin) + (m1v22 * cos), m2v23 = (m1v20 * x) + (m1v21 * y) + (m1v22 * z) + (this.m23);

        return this.set(
            m1v00, m2v01, m2v02,
            (m1v00 * -x) + (m2v01 * -y) + (m2v02 * -z) + (m2v03),

            m1v10, m2v11, m2v12,
            (m1v10 * -x) + (m2v11 * -y) + (m2v12 * -z) + (m2v13),

            m1v20, m2v21, m2v22,
            (m1v20 * -x) + (m2v21 * -y) + (m2v22 * -z) + (m2v23)
        );
    }

    @Override
    public IMatrix3FM localRotateXAround(float x, float y, float z, float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        float m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        float m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;

        float m2v01 = (m1v01 * cos) + (m1v02 * sin), m2v02 = (m1v01 * -sin) + (m1v02 * cos), m2v03 = (m1v00 * x) + (m1v01 * y) + (m1v02 * z) + (this.m03);
        float m2v11 = (m1v11 * cos) + (m1v12 * sin), m2v12 = (m1v11 * -sin) + (m1v12 * cos), m2v13 = (m1v10 * x) + (m1v11 * y) + (m1v12 * z) + (this.m13);
        float m2v21 = (m1v21 * cos) + (m1v22 * sin), m2v22 = (m1v21 * -sin) + (m1v22 * cos), m2v23 = (m1v20 * x) + (m1v21 * y) + (m1v22 * z) + (this.m23);

        return this.set(
            m1v00, m2v01, m2v02,
            (m1v00 * -x) + (m2v01 * -y) + (m2v02 * -z) + (m2v03),

            m1v10, m2v11, m2v12,
            (m1v10 * -x) + (m2v11 * -y) + (m2v12 * -z) + (m2v13),

            m1v20, m2v21, m2v22,
            (m1v20 * -x) + (m2v21 * -y) + (m2v22 * -z) + (m2v23)
        );
    }

    @Override
    public IMatrix3FM localRotateXAround(double x, double y, double z, float degrees) {
        return this.localRotateXAround((float) x, (float) y, (float) z, degrees);
    }

    @Override
    public IMatrix3FM localRotateXAround(IVec3IC point, float degrees) {
        return this.localRotateXAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3FM localRotateXAround(IVec3DC point, float degrees) {
        return this.localRotateXAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3FM localRotateXAround(IVec2IC vec, int z, float degrees) {
        return this.localRotateXAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3FM localRotateXAround(IVec2IC vec, double z, float degrees) {
        return this.localRotateXAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3FM localRotateXAround(IVec2DC vec, int z, float degrees) {
        return this.localRotateXAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3FM localRotateXAround(IVec2DC vec, double z, float degrees) {
        return this.localRotateXAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3FM localRotateXAround(IVec2IC vec, float degrees) {
        return this.localRotateXAround(vec.x(), vec.y(), 0, degrees);
    }

    @Override
    public IMatrix3FM localRotateXAround(IVec2DC vec, float degrees) {
        return this.localRotateXAround(vec.x(), vec.y(), 0.0D, degrees);
    }

    @Override
    public IMatrix3FM localRotateYAround(int x, int y, int z, float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        float m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        float m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;

        float m2v00 = (m1v00 * cos) + (m1v02 * -sin), m2v02 = (m1v00 * sin) + (m1v02 * cos), m2v03 = (m1v00 * x) + (m1v01 * y) + (m1v02 * z) + (this.m03);
        float m2v10 = (m1v10 * cos) + (m1v12 * -sin), m2v12 = (m1v10 * sin) + (m1v12 * cos), m2v13 = (m1v10 * x) + (m1v11 * y) + (m1v12 * z) + (this.m13);
        float m2v20 = (m1v20 * cos) + (m1v22 * -sin), m2v22 = (m1v20 * sin) + (m1v22 * cos), m2v23 = (m1v20 * x) + (m1v21 * y) + (m1v22 * z) + (this.m23);

        return this.set(
            m2v00, m1v01, m2v02,
            (m2v00 * -x) + (m1v01 * -y) + (m2v02 * -z) + (m2v03),

            m2v10, m1v11, m2v12,
            (m2v10 * -x) + (m1v11 * -y) + (m2v12 * -z) + (m2v13),

            m2v20, m1v21, m2v22,
            (m2v20 * -x) + (m1v21 * -y) + (m2v22 * -z) + (m2v23)
        );
    }

    @Override
    public IMatrix3FM localRotateYAround(float x, float y, float z, float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        float m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        float m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;

        float m2v00 = (m1v00 * cos) + (m1v02 * -sin), m2v02 = (m1v00 * sin) + (m1v02 * cos), m2v03 = (m1v00 * x) + (m1v01 * y) + (m1v02 * z) + (this.m03);
        float m2v10 = (m1v10 * cos) + (m1v12 * -sin), m2v12 = (m1v10 * sin) + (m1v12 * cos), m2v13 = (m1v10 * x) + (m1v11 * y) + (m1v12 * z) + (this.m13);
        float m2v20 = (m1v20 * cos) + (m1v22 * -sin), m2v22 = (m1v20 * sin) + (m1v22 * cos), m2v23 = (m1v20 * x) + (m1v21 * y) + (m1v22 * z) + (this.m23);

        return this.set(
            m2v00, m1v01, m2v02,
            (m2v00 * -x) + (m1v01 * -y) + (m2v02 * -z) + (m2v03),

            m2v10, m1v11, m2v12,
            (m2v10 * -x) + (m1v11 * -y) + (m2v12 * -z) + (m2v13),

            m2v20, m1v21, m2v22,
            (m2v20 * -x) + (m1v21 * -y) + (m2v22 * -z) + (m2v23)
        );
    }

    @Override
    public IMatrix3FM localRotateYAround(double x, double y, double z, float degrees) {
        return this.localRotateYAround((float) x, (float) y, (float) z, degrees);
    }

    @Override
    public IMatrix3FM localRotateYAround(IVec3IC point, float degrees) {
        return this.localRotateYAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3FM localRotateYAround(IVec3DC point, float degrees) {
        return this.localRotateYAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3FM localRotateYAround(IVec2IC vec, int z, float degrees) {
        return this.localRotateYAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3FM localRotateYAround(IVec2IC vec, double z, float degrees) {
        return this.localRotateYAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3FM localRotateYAround(IVec2DC vec, int z, float degrees) {
        return this.localRotateYAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3FM localRotateYAround(IVec2DC vec, double z, float degrees) {
        return this.localRotateYAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3FM localRotateYAround(IVec2IC vec, float degrees) {
        return this.localRotateYAround(vec.x(), vec.y(), 0, degrees);
    }

    @Override
    public IMatrix3FM localRotateYAround(IVec2DC vec, float degrees) {
        return this.localRotateYAround(vec.x(), vec.y(), 0.0D, degrees);
    }

    @Override
    public IMatrix3FM localRotateZAround(int x, int y, int z, float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        float m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        float m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;

        float m2v00 = (m1v00 * cos) + (m1v01 * sin), m2v01 = (m1v00 * -sin) + (m1v01 * cos), m2v03 = (m1v00 * x) + (m1v01 * y) + (m1v02 * z) + (this.m03);
        float m2v10 = (m1v10 * cos) + (m1v11 * sin), m2v11 = (m1v10 * -sin) + (m1v11 * cos), m2v13 = (m1v10 * x) + (m1v11 * y) + (m1v12 * z) + (this.m13);
        float m2v20 = (m1v20 * cos) + (m1v21 * sin), m2v21 = (m1v20 * -sin) + (m1v21 * cos), m2v23 = (m1v20 * x) + (m1v21 * y) + (m1v22 * z) + (this.m23);

        return this.set(
            m2v00, m2v01, m1v02,
            (m2v00 * -x) + (m2v01 * -y) + (m1v02 * -z) + (m2v03),

            m2v10, m2v11, m1v12,
            (m2v10 * -x) + (m2v11 * -y) + (m1v12 * -z) + (m2v13),

            m2v20, m2v21, m1v22,
            (m2v20 * -x) + (m2v21 * -y) + (m1v22 * -z) + (m2v23)
        );
    }

    @Override
    public IMatrix3FM localRotateZAround(float x, float y, float z, float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        float m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        float m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;

        float m2v00 = (m1v00 * cos) + (m1v01 * sin), m2v01 = (m1v00 * -sin) + (m1v01 * cos), m2v03 = (m1v00 * x) + (m1v01 * y) + (m1v02 * z) + (this.m03);
        float m2v10 = (m1v10 * cos) + (m1v11 * sin), m2v11 = (m1v10 * -sin) + (m1v11 * cos), m2v13 = (m1v10 * x) + (m1v11 * y) + (m1v12 * z) + (this.m13);
        float m2v20 = (m1v20 * cos) + (m1v21 * sin), m2v21 = (m1v20 * -sin) + (m1v21 * cos), m2v23 = (m1v20 * x) + (m1v21 * y) + (m1v22 * z) + (this.m23);

        return this.set(
            m2v00, m2v01, m1v02,
            (m2v00 * -x) + (m2v01 * -y) + (m1v02 * -z) + (m2v03),

            m2v10, m2v11, m1v12,
            (m2v10 * -x) + (m2v11 * -y) + (m1v12 * -z) + (m2v13),

            m2v20, m2v21, m1v22,
            (m2v20 * -x) + (m2v21 * -y) + (m1v22 * -z) + (m2v23)
        );
    }

    @Override
    public IMatrix3FM localRotateZAround(double x, double y, double z, float degrees) {
        return this.localRotateZAround((float) x, (float) y, (float) z, degrees);
    }

    @Override
    public IMatrix3FM localRotateZAround(IVec3IC point, float degrees) {
        return this.localRotateZAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3FM localRotateZAround(IVec3DC point, float degrees) {
        return this.localRotateZAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3FM localRotateZAround(IVec2IC vec, int z, float degrees) {
        return this.localRotateZAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3FM localRotateZAround(IVec2IC vec, double z, float degrees) {
        return this.localRotateZAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3FM localRotateZAround(IVec2DC vec, int z, float degrees) {
        return this.localRotateZAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3FM localRotateZAround(IVec2DC vec, double z, float degrees) {
        return this.localRotateZAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3FM localRotateZAround(IVec2IC vec, float degrees) {
        return this.localRotateZAround(vec.x(), vec.y(), 0, degrees);
    }

    @Override
    public IMatrix3FM localRotateZAround(IVec2DC vec, float degrees) {
        return this.localRotateZAround(vec.x(), vec.y(), 0.0D, degrees);
    }

    @Override
    public IMatrix3FM localRotateAround(IVec3DC point, float degrees, int x, int y, int z) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }
        float l = (float) Math.sqrt(x * x + y * y + z * z);
        if (l < BananaMath.FLOAT_EPS) {
            return this;
        }
        float ax = x / l, ay = y / l, az = z / l;
        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);
        float cosM1 = 1 - cos;

        float m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        float m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        float m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;
        float m2v00 = cos + ax * ax * cosM1, m2v01 = ax * ay * cosM1 - az * sin, m2v02 = ax * az * cosM1 + ay * sin, m2v03 = (float) ((m1v00 * point.x()) + (m1v01 * point.y()) + (m1v02 * point.z()) + (this.m03));
        float m2v10 = ay * ax * cosM1 + az * sin, m2v11 = cos + ay * ay * cosM1, m2v12 = ay * az * cosM1 - ax * sin, m2v13 = (float) ((m1v10 * point.x()) + (m1v11 * point.y()) + (m1v12 * point.z()) + (this.m13));
        float m2v20 = az * ax * cosM1 - ay * sin, m2v21 = az * ay * cosM1 + ax * sin, m2v22 = cos + az * az * cosM1, m2v23 = (float) ((m1v20 * point.x()) + (m1v21 * point.y()) + (m1v22 * point.z()) + (this.m23));
        return this.set(
            (m1v00 * m2v00) + (m1v01 * m2v10) + (m1v02 * m2v20),
            (m1v00 * m2v01) + (m1v01 * m2v11) + (m1v02 * m2v21),
            (m1v00 * m2v02) + (m1v01 * m2v12) + (m1v02 * m2v22),
            (m2v00 * -point.x()) + (m2v01 * -point.y()) + (m2v02 * -point.z()) + (m2v03),

            (m1v10 * m2v00) + (m1v11 * m2v10) + (m1v12 * m2v20),
            (m1v10 * m2v01) + (m1v11 * m2v11) + (m1v12 * m2v21),
            (m1v10 * m2v02) + (m1v11 * m2v12) + (m1v12 * m2v22),
            (m2v10 * -point.x()) + (m2v11 * -point.y()) + (m2v12 * -point.z()) + (m2v13),

            (m1v20 * m2v00) + (m1v21 * m2v10) + (m1v22 * m2v20),
            (m1v20 * m2v01) + (m1v21 * m2v11) + (m1v22 * m2v21),
            (m1v20 * m2v02) + (m1v21 * m2v12) + (m1v22 * m2v22),
            (m2v20 * -point.x()) + (m2v21 * -point.y()) + (m2v22 * -point.z()) + (m2v23)
        );
    }

    @Override
    public IMatrix3FM localRotateAround(IVec3DC point, float degrees, float x, float y, float z) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }
        float l = (float) Math.sqrt(x * x + y * y + z * z);
        if (l < BananaMath.FLOAT_EPS) {
            return this;
        }
        float ax = x / l, ay = y / l, az = z / l;
        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);
        float cosM1 = 1 - cos;

        float m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        float m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        float m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;
        float m2v00 = cos + ax * ax * cosM1, m2v01 = ax * ay * cosM1 - az * sin, m2v02 = ax * az * cosM1 + ay * sin, m2v03 = (float) ((m1v00 * point.x()) + (m1v01 * point.y()) + (m1v02 * point.z()) + (this.m03));
        float m2v10 = ay * ax * cosM1 + az * sin, m2v11 = cos + ay * ay * cosM1, m2v12 = ay * az * cosM1 - ax * sin, m2v13 = (float) ((m1v10 * point.x()) + (m1v11 * point.y()) + (m1v12 * point.z()) + (this.m13));
        float m2v20 = az * ax * cosM1 - ay * sin, m2v21 = az * ay * cosM1 + ax * sin, m2v22 = cos + az * az * cosM1, m2v23 = (float) ((m1v20 * point.x()) + (m1v21 * point.y()) + (m1v22 * point.z()) + (this.m23));
        return this.set(
            (m1v00 * m2v00) + (m1v01 * m2v10) + (m1v02 * m2v20),
            (m1v00 * m2v01) + (m1v01 * m2v11) + (m1v02 * m2v21),
            (m1v00 * m2v02) + (m1v01 * m2v12) + (m1v02 * m2v22),
            (m2v00 * -point.x()) + (m2v01 * -point.y()) + (m2v02 * -point.z()) + (m2v03),

            (m1v10 * m2v00) + (m1v11 * m2v10) + (m1v12 * m2v20),
            (m1v10 * m2v01) + (m1v11 * m2v11) + (m1v12 * m2v21),
            (m1v10 * m2v02) + (m1v11 * m2v12) + (m1v12 * m2v22),
            (m2v10 * -point.x()) + (m2v11 * -point.y()) + (m2v12 * -point.z()) + (m2v13),

            (m1v20 * m2v00) + (m1v21 * m2v10) + (m1v22 * m2v20),
            (m1v20 * m2v01) + (m1v21 * m2v11) + (m1v22 * m2v21),
            (m1v20 * m2v02) + (m1v21 * m2v12) + (m1v22 * m2v22),
            (m2v20 * -point.x()) + (m2v21 * -point.y()) + (m2v22 * -point.z()) + (m2v23)
        );
    }

    @Override
    public IMatrix3FM localRotateAround(IVec3DC point, float degrees, double x, double y, double z) {
        return this.localRotateAround(point, degrees, (float) x, (float) y, (float) z);
    }

    @Override
    public IMatrix3FM localRotateAround(IVec3DC point, float degrees, IVec3DC vec) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3FM localRotateAround(IVec3DC point, float degrees, IVec3IC vec) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3FM localRotateAround(IVec3DC point, float degrees, IVec2IC vec, int z) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM localRotateAround(IVec3DC point, float degrees, IVec2IC vec, double z) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM localRotateAround(IVec3DC point, float degrees, IVec2DC vec, int z) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM localRotateAround(IVec3DC point, float degrees, IVec2DC vec, double z) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM localRotateAround(IVec3DC point, float degrees, IVec2IC vec) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3FM localRotateAround(IVec3DC point, float degrees, IVec2DC vec) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), 0.0D);
    }

    @Override
    public IMatrix3FM localRotateAround(IVec3IC point, float degrees, int x, int y, int z) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }
        float l = (float) Math.sqrt(x * x + y * y + z * z);
        if (l < BananaMath.FLOAT_EPS) {
            return this;
        }
        float ax = x / l, ay = y / l, az = z / l;
        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);
        float cosM1 = 1 - cos;

        float m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        float m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        float m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;
        float m2v00 = cos + ax * ax * cosM1, m2v01 = ax * ay * cosM1 - az * sin, m2v02 = ax * az * cosM1 + ay * sin, m2v03 = (m1v00 * point.x()) + (m1v01 * point.y()) + (m1v02 * point.z()) + (this.m03);
        float m2v10 = ay * ax * cosM1 + az * sin, m2v11 = cos + ay * ay * cosM1, m2v12 = ay * az * cosM1 - ax * sin, m2v13 = (m1v10 * point.x()) + (m1v11 * point.y()) + (m1v12 * point.z()) + (this.m13);
        float m2v20 = az * ax * cosM1 - ay * sin, m2v21 = az * ay * cosM1 + ax * sin, m2v22 = cos + az * az * cosM1, m2v23 = (m1v20 * point.x()) + (m1v21 * point.y()) + (m1v22 * point.z()) + (this.m23);
        return this.set(
            (m1v00 * m2v00) + (m1v01 * m2v10) + (m1v02 * m2v20),
            (m1v00 * m2v01) + (m1v01 * m2v11) + (m1v02 * m2v21),
            (m1v00 * m2v02) + (m1v01 * m2v12) + (m1v02 * m2v22),
            (m2v00 * -point.x()) + (m2v01 * -point.y()) + (m2v02 * -point.z()) + (m2v03),

            (m1v10 * m2v00) + (m1v11 * m2v10) + (m1v12 * m2v20),
            (m1v10 * m2v01) + (m1v11 * m2v11) + (m1v12 * m2v21),
            (m1v10 * m2v02) + (m1v11 * m2v12) + (m1v12 * m2v22),
            (m2v10 * -point.x()) + (m2v11 * -point.y()) + (m2v12 * -point.z()) + (m2v13),

            (m1v20 * m2v00) + (m1v21 * m2v10) + (m1v22 * m2v20),
            (m1v20 * m2v01) + (m1v21 * m2v11) + (m1v22 * m2v21),
            (m1v20 * m2v02) + (m1v21 * m2v12) + (m1v22 * m2v22),
            (m2v20 * -point.x()) + (m2v21 * -point.y()) + (m2v22 * -point.z()) + (m2v23)
        );
    }

    @Override
    public IMatrix3FM localRotateAround(IVec3IC point, float degrees, float x, float y, float z) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }
        float l = (float) Math.sqrt(x * x + y * y + z * z);
        if (l < BananaMath.FLOAT_EPS) {
            return this;
        }
        float ax = x / l, ay = y / l, az = z / l;
        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);
        float cosM1 = 1 - cos;

        float m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        float m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        float m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;
        float m2v00 = cos + ax * ax * cosM1, m2v01 = ax * ay * cosM1 - az * sin, m2v02 = ax * az * cosM1 + ay * sin, m2v03 = (m1v00 * point.x()) + (m1v01 * point.y()) + (m1v02 * point.z()) + (this.m03);
        float m2v10 = ay * ax * cosM1 + az * sin, m2v11 = cos + ay * ay * cosM1, m2v12 = ay * az * cosM1 - ax * sin, m2v13 = (m1v10 * point.x()) + (m1v11 * point.y()) + (m1v12 * point.z()) + (this.m13);
        float m2v20 = az * ax * cosM1 - ay * sin, m2v21 = az * ay * cosM1 + ax * sin, m2v22 = cos + az * az * cosM1, m2v23 = (m1v20 * point.x()) + (m1v21 * point.y()) + (m1v22 * point.z()) + (this.m23);
        return this.set(
            (m1v00 * m2v00) + (m1v01 * m2v10) + (m1v02 * m2v20),
            (m1v00 * m2v01) + (m1v01 * m2v11) + (m1v02 * m2v21),
            (m1v00 * m2v02) + (m1v01 * m2v12) + (m1v02 * m2v22),
            (m2v00 * -point.x()) + (m2v01 * -point.y()) + (m2v02 * -point.z()) + (m2v03),

            (m1v10 * m2v00) + (m1v11 * m2v10) + (m1v12 * m2v20),
            (m1v10 * m2v01) + (m1v11 * m2v11) + (m1v12 * m2v21),
            (m1v10 * m2v02) + (m1v11 * m2v12) + (m1v12 * m2v22),
            (m2v10 * -point.x()) + (m2v11 * -point.y()) + (m2v12 * -point.z()) + (m2v13),

            (m1v20 * m2v00) + (m1v21 * m2v10) + (m1v22 * m2v20),
            (m1v20 * m2v01) + (m1v21 * m2v11) + (m1v22 * m2v21),
            (m1v20 * m2v02) + (m1v21 * m2v12) + (m1v22 * m2v22),
            (m2v20 * -point.x()) + (m2v21 * -point.y()) + (m2v22 * -point.z()) + (m2v23)
        );
    }

    @Override
    public IMatrix3FM localRotateAround(IVec3IC point, float degrees, double x, double y, double z) {
        return this.localRotateAround(point, degrees, (float) x, (float) y, (float) z);
    }

    @Override
    public IMatrix3FM localRotateAround(IVec3IC point, float degrees, IVec3DC vec) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3FM localRotateAround(IVec3IC point, float degrees, IVec3IC vec) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3FM localRotateAround(IVec3IC point, float degrees, IVec2IC vec, int z) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM localRotateAround(IVec3IC point, float degrees, IVec2IC vec, double z) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM localRotateAround(IVec3IC point, float degrees, IVec2DC vec, int z) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM localRotateAround(IVec3IC point, float degrees, IVec2DC vec, double z) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3FM localRotateAround(IVec3IC point, float degrees, IVec2IC vec) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3FM localRotateAround(IVec3IC point, float degrees, IVec2DC vec) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), 0.0D);
    }

    @Override
    public IVec2I transform(int x, int y) {
        return new Vec2I(
            BananaMath.round((this.m00 * x) + (this.m01 * y) + this.m03),
            BananaMath.round((this.m10 * x) + (this.m11 * y) + this.m13)
        );
    }

    @Override
    public IVec2D transform(double x, double y) {
        return new Vec2D(
            (this.m00 * x) + (this.m01 * y) + this.m03,
            (this.m10 * x) + (this.m11 * y) + this.m13
        );
    }

    @Override
    public IVec3I transform(int x, int y, int z) {
        return new Vec3I(
            BananaMath.round((this.m00 * x) + (this.m01 * y) + (this.m02 * z) + this.m03),
            BananaMath.round((this.m10 * x) + (this.m11 * y) + (this.m12 * z) + this.m13),
            BananaMath.round((this.m20 * x) + (this.m21 * y) + (this.m22 * z) + this.m23)
        );
    }

    @Override
    public IVec3D transform(double x, double y, double z) {
        return new Vec3D(
            (this.m00 * x) + (this.m01 * y) + (this.m02 * z) + this.m03,
            (this.m10 * x) + (this.m11 * y) + (this.m12 * z) + this.m13,
            (this.m20 * x) + (this.m21 * y) + (this.m22 * z) + this.m23
        );
    }

    @Override
    public IVec3I transform(IVec3IC vec) {
        return this.transform(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IVec3IM transform(IVec3IM vec) {
        return vec.set(
            BananaMath.round((this.m00 * vec.x()) + (this.m01 * vec.y()) + (this.m02 * vec.z()) + this.m03),
            BananaMath.round((this.m10 * vec.x()) + (this.m11 * vec.y()) + (this.m12 * vec.z()) + this.m13),
            BananaMath.round((this.m20 * vec.x()) + (this.m21 * vec.y()) + (this.m22 * vec.z()) + this.m23)
        );
    }

    @Override
    public IVec3D transform(IVec3DC vec) {
        return this.transform(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IVec3DM transform(IVec3DM vec) {
        return vec.set(
            (this.m00 * vec.x()) + (this.m01 * vec.y()) + (this.m02 * vec.z()) + this.m03,
            (this.m10 * vec.x()) + (this.m11 * vec.y()) + (this.m12 * vec.z()) + this.m13,
            (this.m20 * vec.x()) + (this.m21 * vec.y()) + (this.m22 * vec.z()) + this.m23
        );
    }

    @Override
    public IVec3I[] transform(IVec3IC... vec) {
        IVec3I[] ret = new IVec3I[vec.length];
        for (int i = 0; i != vec.length; i++) {
            ret[i] = this.transform(vec[i]);
        }
        return ret;
    }

    @Override
    public IVec3IM[] transform(IVec3IM... vec) {
        for (int i = 0; i != vec.length; i++) {
            this.transform(vec[i]);
        }
        return vec;
    }

    @Override
    public IVec3D[] transform(IVec3DC... vec) {
        IVec3D[] ret = new IVec3D[vec.length];
        for (int i = 0; i != vec.length; i++) {
            ret[i] = this.transform(vec[i]);
        }
        return ret;
    }

    @Override
    public IVec3DM[] transform(IVec3DM... vec) {
        for (int i = 0; i != vec.length; i++) {
            this.transform(vec[i]);
        }
        return vec;
    }

    @Override
    public IVec2I transform(IVec2IC vec) {
        return this.transform(vec.x(), vec.y());
    }

    @Override
    public IVec2IM transform(IVec2IM vec) {
        return vec.set(
            BananaMath.round((this.m00 * vec.x()) + (this.m01 * vec.y()) + this.m03),
            BananaMath.round((this.m10 * vec.x()) + (this.m11 * vec.y()) + this.m13)
        );
    }

    @Override
    public IVec2D transform(IVec2DC vec) {
        return this.transform(vec.x(), vec.y());
    }

    @Override
    public IVec2DM transform(IVec2DM vec) {
        return vec.set(
            (this.m00 * vec.x()) + (this.m01 * vec.y()) + this.m03,
            (this.m10 * vec.x()) + (this.m11 * vec.y()) + this.m13
        );
    }

    @Override
    public IVec3I transform(IVec2IC vec, int z) {
        return this.transform(vec.x(), vec.y(), z);
    }

    @Override
    public IVec3D transform(IVec2IC vec, double z) {
        return this.transform(vec.x(), vec.y(), z);
    }

    @Override
    public IVec3D transform(IVec2DC vec, int z) {
        return this.transform(vec.x(), vec.y(), z);
    }

    @Override
    public IVec3D transform(IVec2DC vec, double z) {
        return this.transform(vec.x(), vec.y(), z);
    }

    @Override
    public IVec2I[] transform(IVec2IC... vec) {
        IVec2I[] ret = new IVec2I[vec.length];
        for (int i = 0; i != vec.length; i++) {
            ret[i] = this.transform(vec[i]);
        }
        return ret;
    }

    @Override
    public IVec2IM[] transform(IVec2IM... vec) {
        for (int i = 0; i != vec.length; i++) {
            this.transform(vec[i]);
        }
        return vec;
    }

    @Override
    public IVec2D[] transform(IVec2DC... vec) {
        IVec2D[] ret = new IVec2D[vec.length];
        for (int i = 0; i != vec.length; i++) {
            ret[i] = this.transform(vec[i]);
        }
        return ret;
    }

    @Override
    public IVec2DM[] transform(IVec2DM... vec) {
        for (int i = 0; i != vec.length; i++) {
            this.transform(vec[i]);
        }
        return vec;
    }

    @Override
    public IBox3I transform(IBox3IC box) {
        return new Box3I(
            BananaMath.round((this.m00 * box.minX()) + (this.m01 * box.minY()) + (this.m02 * box.minZ()) + this.m03),
            BananaMath.round((this.m10 * box.minX()) + (this.m11 * box.minY()) + (this.m12 * box.minZ()) + this.m13),
            BananaMath.round((this.m20 * box.minX()) + (this.m21 * box.minY()) + (this.m22 * box.minZ()) + this.m23),

            BananaMath.round((this.m00 * box.maxX()) + (this.m01 * box.maxY()) + (this.m02 * box.maxZ()) + this.m03),
            BananaMath.round((this.m10 * box.maxX()) + (this.m11 * box.maxY()) + (this.m12 * box.maxZ()) + this.m13),
            BananaMath.round((this.m20 * box.maxX()) + (this.m21 * box.maxY()) + (this.m22 * box.maxZ()) + this.m23)
        );
    }

    @Override
    public IBox3IM transform(IBox3IM box) {
        return box.set(
            BananaMath.round((this.m00 * box.minX()) + (this.m01 * box.minY()) + (this.m02 * box.minZ()) + this.m03),
            BananaMath.round((this.m10 * box.minX()) + (this.m11 * box.minY()) + (this.m12 * box.minZ()) + this.m13),
            BananaMath.round((this.m20 * box.minX()) + (this.m21 * box.minY()) + (this.m22 * box.minZ()) + this.m23),

            BananaMath.round((this.m00 * box.maxX()) + (this.m01 * box.maxY()) + (this.m02 * box.maxZ()) + this.m03),
            BananaMath.round((this.m10 * box.maxX()) + (this.m11 * box.maxY()) + (this.m12 * box.maxZ()) + this.m13),
            BananaMath.round((this.m20 * box.maxX()) + (this.m21 * box.maxY()) + (this.m22 * box.maxZ()) + this.m23)
        );
    }

    @Override
    public IBox3D transform(IBox3DC box) {
        return new Box3D(
            (this.m00 * box.minX()) + (this.m01 * box.minY()) + (this.m02 * box.minZ()) + this.m03,
            (this.m10 * box.minX()) + (this.m11 * box.minY()) + (this.m12 * box.minZ()) + this.m13,
            (this.m20 * box.minX()) + (this.m21 * box.minY()) + (this.m22 * box.minZ()) + this.m23,

            (this.m00 * box.maxX()) + (this.m01 * box.maxY()) + (this.m02 * box.maxZ()) + this.m03,
            (this.m10 * box.maxX()) + (this.m11 * box.maxY()) + (this.m12 * box.maxZ()) + this.m13,
            (this.m20 * box.maxX()) + (this.m21 * box.maxY()) + (this.m22 * box.maxZ()) + this.m23
        );
    }

    @Override
    public IBox3DM transform(IBox3DM box) {
        return box.set(
            (this.m00 * box.minX()) + (this.m01 * box.minY()) + (this.m02 * box.minZ()) + this.m03,
            (this.m10 * box.minX()) + (this.m11 * box.minY()) + (this.m12 * box.minZ()) + this.m13,
            (this.m20 * box.minX()) + (this.m21 * box.minY()) + (this.m22 * box.minZ()) + this.m23,

            (this.m00 * box.maxX()) + (this.m01 * box.maxY()) + (this.m02 * box.maxZ()) + this.m03,
            (this.m10 * box.maxX()) + (this.m11 * box.maxY()) + (this.m12 * box.maxZ()) + this.m13,
            (this.m20 * box.maxX()) + (this.m21 * box.maxY()) + (this.m22 * box.maxZ()) + this.m23
        );
    }

    @Override
    public IBox3I[] transform(IBox3IC... box) {
        IBox3I[] ret = new IBox3I[box.length];
        for (int i = 0; i != box.length; i++) {
            ret[i] = this.transform(box[i]);
        }
        return ret;
    }

    @Override
    public IBox3IM[] transform(IBox3IM... box) {
        for (int i = 0; i != box.length; i++) {
            this.transform(box[i]);
        }
        return box;
    }

    @Override
    public IBox3D[] transform(IBox3DC... box) {
        IBox3D[] ret = new IBox3D[box.length];
        for (int i = 0; i != box.length; i++) {
            ret[i] = this.transform(box[i]);
        }
        return ret;
    }

    @Override
    public IBox3DM[] transform(IBox3DM... box) {
        for (int i = 0; i != box.length; i++) {
            this.transform(box[i]);
        }
        return box;
    }

    @Override
    public FloatBuffer writeToBuffer(FloatBuffer buf) {
        buf.put(this.m00);
        buf.put(this.m01);
        buf.put(this.m02);
        buf.put(this.m03);

        buf.put(this.m10);
        buf.put(this.m11);
        buf.put(this.m12);
        buf.put(this.m13);

        buf.put(this.m20);
        buf.put(this.m21);
        buf.put(this.m22);
        buf.put(this.m23);

        buf.put(0.0F);
        buf.put(0.0F);
        buf.put(0.0F);
        buf.put(1.0F);

        return buf;
    }

    @Override
    public IMatrix3FM toMutable() {
        return this;
    }

    @Override
    public IMatrix3F toImmutable() {
        return new Matrix3F(this);
    }

    @Override
    public IMatrix3DM toDouble() {
        return null;//new Matrix3D(this);
    }

    @Override
    public IMatrix3FM copy() {
        Matrix3FM copy = new Matrix3FM(this);
        if (this.stateStack != null) {
            copy.stateStack = this.stateStack.copy();
        }
        return copy;
    }

    @Override
    public String toString() {
        return "[" + this.m00 + ", " + this.m01 + ", " + this.m02 + ", " + this.m03 + "]\n" +
                "[" + this.m10 + ", " + this.m11 + ", " + this.m12 + ", " + this.m13 + "]\n" +
                "[" + this.m20 + ", " + this.m21 + ", " + this.m22 + ", " + this.m23 + "]\n" +
                "[" + 0.0 + ", " + 0.0 + ", " + 0.0 + ", " + 1.0 + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IMatrix3F) {
            IMatrix3F m = ((IMatrix3F) obj);
            return
                    m.m00() == this.m00 && m.m01() == this.m01 && m.m02() == this.m02 && m.m03() == this.m03 &&
                    m.m10() == this.m10 && m.m11() == this.m11 && m.m12() == this.m12 && m.m13() == this.m13 &&
                    m.m20() == this.m20 && m.m21() == this.m21 && m.m22() == this.m22 && m.m23() == this.m23 &&
                    m.m30() == 0.0D && m.m31() == 0.0D && m.m32() == 0.0D && m.m33() == 1.0D;
        } else if (obj instanceof IMatrix3D) {
            IMatrix3D m = ((IMatrix3D) obj);
            return
                    m.m00() == this.m00 && m.m01() == this.m01 && m.m02() == this.m02 && m.m03() == this.m03 &&
                    m.m10() == this.m10 && m.m11() == this.m11 && m.m12() == this.m12 && m.m13() == this.m13 &&
                    m.m20() == this.m20 && m.m21() == this.m21 && m.m22() == this.m22 && m.m23() == this.m23 &&
                    m.m30() == 0.0D && m.m31() == 0.0D && m.m32() == 0.0D && m.m33() == 1.0D;
        }
        return false;
    }

    @Override
    public boolean equalsEps(Object obj, float eps) {
        if (obj instanceof IMatrix3F) {
            IMatrix3F m = ((IMatrix3F) obj);
            return
                    Math.abs(m.m00() - this.m00) <= eps &&
                    Math.abs(m.m01() - this.m01) <= eps &&
                    Math.abs(m.m02() - this.m02) <= eps &&
                    Math.abs(m.m03() - this.m03) <= eps &&

                    Math.abs(m.m10() - this.m10) <= eps &&
                    Math.abs(m.m11() - this.m11) <= eps &&
                    Math.abs(m.m12() - this.m12) <= eps &&
                    Math.abs(m.m13() - this.m13) <= eps &&

                    Math.abs(m.m20() - this.m20) <= eps &&
                    Math.abs(m.m21() - this.m21) <= eps &&
                    Math.abs(m.m22() - this.m22) <= eps &&
                    Math.abs(m.m23() - this.m23) <= eps &&

                    Math.abs(m.m30() - 0.0F) <= eps &&
                    Math.abs(m.m31() - 0.0F) <= eps &&
                    Math.abs(m.m32() - 0.0F) <= eps &&
                    Math.abs(m.m33() - 1.0F) <= eps;
        } else if (obj instanceof IMatrix3D) {
            IMatrix3D m = ((IMatrix3D) obj);
            return
                    Math.abs(m.m00() - this.m00) <= eps &&
                    Math.abs(m.m01() - this.m01) <= eps &&
                    Math.abs(m.m02() - this.m02) <= eps &&
                    Math.abs(m.m03() - this.m03) <= eps &&

                    Math.abs(m.m10() - this.m10) <= eps &&
                    Math.abs(m.m11() - this.m11) <= eps &&
                    Math.abs(m.m12() - this.m12) <= eps &&
                    Math.abs(m.m13() - this.m13) <= eps &&

                    Math.abs(m.m20() - this.m20) <= eps &&
                    Math.abs(m.m21() - this.m21) <= eps &&
                    Math.abs(m.m22() - this.m22) <= eps &&
                    Math.abs(m.m23() - this.m23) <= eps &&

                    Math.abs(m.m30() - 0.0F) <= eps &&
                    Math.abs(m.m31() - 0.0F) <= eps &&
                    Math.abs(m.m32() - 0.0F) <= eps &&
                    Math.abs(m.m33() - 1.0F) <= eps;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Hasher.hash(
            this.m00, this.m01, this.m02, this.m03,
            this.m10, this.m11, this.m12, this.m13,
            this.m20, this.m21, this.m22, this.m23,
            0.0F, 0.0F, 0.0F, 1.0F
        );
    }
}