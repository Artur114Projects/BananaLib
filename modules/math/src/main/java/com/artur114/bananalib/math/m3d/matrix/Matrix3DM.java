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
import com.artur114.bananalib.math.internal.DoubleStack;
import com.artur114.bananalib.math.internal.FloatStack;
import com.artur114.bananalib.math.internal.Hasher;
import com.artur114.bananalib.math.internal.ThreadLocalPool;
import com.artur114.bananalib.math.m2d.vec.*;
import com.artur114.bananalib.math.m3d.box.*;
import com.artur114.bananalib.math.m3d.vec.*;

import java.nio.DoubleBuffer;

public class Matrix3DM implements IMatrix3DM {
    private static final ThreadLocalPool<Matrix3DM> pool = new ThreadLocalPool<>(new Matrix3DM[4], Matrix3DM::new, matrix -> {
        matrix.resetStack().setIdentity();
        matrix.released = true;
        return matrix;
    }, matrix -> {
        matrix.released = false;
        return matrix;
    }, matrix -> matrix.released);

    public static Matrix3DM obtain() {
        return pool.obtain();
    }

    public static void release(Matrix3DM matrix) {
        pool.release(matrix);
    }

    private DoubleStack stateStack = null;
    private double m00, m01, m02, m03;
    private double m10, m11, m12, m13;
    private double m20, m21, m22, m23;
    private boolean released;

    public Matrix3DM() {
        this.m00 = 1.0D; this.m01 = 0.0D; this.m02 = 0.0D; this.m03 = 0.0D;
        this.m10 = 0.0D; this.m11 = 1.0D; this.m12 = 0.0D; this.m13 = 0.0D;
        this.m20 = 0.0D; this.m21 = 0.0D; this.m22 = 1.0D; this.m23 = 0.0D;
    }

    public Matrix3DM(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23) {
        this.m00 = m00; this.m01 = m01; this.m02 = m02; this.m03 = m03;
        this.m10 = m10; this.m11 = m11; this.m12 = m12; this.m13 = m13;
        this.m20 = m20; this.m21 = m21; this.m22 = m22; this.m23 = m23;
    }

    public Matrix3DM(IMatrix3DC m) {
        this(
            m.m00(), m.m01(), m.m02(), m.m03(),
            m.m10(), m.m11(), m.m12(), m.m13(),
            m.m20(), m.m21(), m.m22(), m.m23()
        );
    }

    public Matrix3DM(IMatrix3FC m) {
        this(
            m.m00(), m.m01(), m.m02(), m.m03(),
            m.m10(), m.m11(), m.m12(), m.m13(),
            m.m20(), m.m21(), m.m22(), m.m23()
        );
    }

    public Matrix3DM(DoubleBuffer buf) {
        this(
            buf.get(), buf.get(), buf.get(), buf.get(),
            buf.get(), buf.get(), buf.get(), buf.get(),
            buf.get(), buf.get(), buf.get(), buf.get()
        );
    }

    @Override
    public IMatrix3DM set(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23) {
        this.m00 = m00; this.m01 = m01; this.m02 = m02; this.m03 = m03;
        this.m10 = m10; this.m11 = m11; this.m12 = m12; this.m13 = m13;
        this.m20 = m20; this.m21 = m21; this.m22 = m22; this.m23 = m23;
        return this;
    }

    @Override
    public IMatrix3DM set(IMatrix3FC m) {
        this.m00 = m.m00(); this.m01 = m.m01(); this.m02 = m.m02(); this.m03 = m.m03();
        this.m10 = m.m10(); this.m11 = m.m11(); this.m12 = m.m12(); this.m13 = m.m13();
        this.m20 = m.m20(); this.m21 = m.m21(); this.m22 = m.m22(); this.m23 = m.m23();
        return this;
    }

    @Override
    public IMatrix3DM set(IMatrix3DC m) {
        this.m00 = m.m00(); this.m01 = m.m01(); this.m02 = m.m02(); this.m03 = m.m03();
        this.m10 = m.m10(); this.m11 = m.m11(); this.m12 = m.m12(); this.m13 = m.m13();
        this.m20 = m.m20(); this.m21 = m.m21(); this.m22 = m.m22(); this.m23 = m.m23();
        return this;
    }

    @Override
    public IMatrix3DM set(double[] ms) {
        if (ms.length < 12) throw new IllegalArgumentException();
        this.m00 = ms[0]; this.m01 = ms[1]; this.m02 = ms[2]; this.m03 = ms[3];
        this.m10 = ms[4]; this.m11 = ms[5]; this.m12 = ms[6]; this.m13 = ms[7];
        this.m20 = ms[8]; this.m21 = ms[9]; this.m22 = ms[10]; this.m23 = ms[11];
        return this;
    }

    @Override
    public IMatrix3DM setM00(double m00) {
        this.m00 = m00;
        return this;
    }

    @Override
    public IMatrix3DM setM01(double m01) {
        this.m01 = m01;
        return this;
    }

    @Override
    public IMatrix3DM setM02(double m02) {
        this.m02 = m02;
        return this;
    }

    @Override
    public IMatrix3DM setM03(double m03) {
        this.m03 = m03;
        return this;
    }

    @Override
    public IMatrix3DM setM10(double m10) {
        this.m10 = m10;
        return this;
    }

    @Override
    public IMatrix3DM setM11(double m11) {
        this.m11 = m11;
        return this;
    }

    @Override
    public IMatrix3DM setM12(double m12) {
        this.m12 = m12;
        return this;
    }

    @Override
    public IMatrix3DM setM13(double m13) {
        this.m13 = m13;
        return this;
    }

    @Override
    public IMatrix3DM setM20(double m20) {
        this.m20 = m20;
        return this;
    }

    @Override
    public IMatrix3DM setM21(double m21) {
        this.m21 = m21;
        return this;
    }

    @Override
    public IMatrix3DM setM22(double m22) {
        this.m22 = m22;
        return this;
    }

    @Override
    public IMatrix3DM setM23(double m23) {
        this.m23 = m23;
        return this;
    }

    @Override
    public IMatrix3DM setIdentity() {
        this.m00 = 1.0D; this.m01 = 0.0D; this.m02 = 0.0D; this.m03 = 0.0D;
        this.m10 = 0.0D; this.m11 = 1.0D; this.m12 = 0.0D; this.m13 = 0.0D;
        this.m20 = 0.0D; this.m21 = 0.0D; this.m22 = 1.0D; this.m23 = 0.0D;
        return this;
    }

    @Override
    public IMatrix3DM collapseStack() {
        this.stateStack = null;
        return this;
    }

    @Override
    public IMatrix3DM resetStack() {
        if (this.stateStack != null) {
            this.stateStack.reset();
        }
        return this;
    }

    @Override
    public IMatrix3DM pushMatrix() {
        if (this.stateStack == null) {
            this.stateStack = new DoubleStack(12);
        }
        double[] arr = this.stateStack.newEntry();
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
    public IMatrix3DM popMatrix() {
        if (this.stateStack == null) {
            throw new IllegalStateException();
        }
        return this.set(this.stateStack.pull());
    }

    @Override
    public double m00() {
        return this.m00;
    }

    @Override
    public double m01() {
        return this.m01;
    }

    @Override
    public double m02() {
        return this.m02;
    }

    @Override
    public double m03() {
        return this.m03;
    }

    @Override
    public double m10() {
        return this.m10;
    }

    @Override
    public double m11() {
        return this.m11;
    }

    @Override
    public double m12() {
        return this.m12;
    }

    @Override
    public double m13() {
        return this.m13;
    }

    @Override
    public double m20() {
        return this.m20;
    }

    @Override
    public double m21() {
        return this.m21;
    }

    @Override
    public double m22() {
        return this.m22;
    }

    @Override
    public double m23() {
        return this.m23;
    }

    @Override
    public double m30() {
        return 0.0D;
    }

    @Override
    public double m31() {
        return 0.0D;
    }

    @Override
    public double m32() {
        return 0.0D;
    }

    @Override
    public double m33() {
        return 1.0D;
    }

    @Override
    public IMatrix3DM invert() {
        if (!this.isReversible()) throw new ArithmeticException("Couldn't invert unreversible matrix: " + this);
        double detInv = 1.0D / this.determinant();
        double i00 = (this.m11 * this.m22 - this.m21 * this.m12) * detInv;
        double i01 = -(this.m01 * this.m22 - this.m21 * this.m02) * detInv;
        double i02 = (this.m01 * this.m12 - this.m11 * this.m02) * detInv;
        double i10 = -(this.m10 * this.m22 - this.m20 * this.m12) * detInv;
        double i11 = (this.m00 * this.m22 - this.m20 * this.m02) * detInv;
        double i12 = -(this.m00 * this.m12 - this.m10 * this.m02) * detInv;
        double i20 = (this.m10 * this.m21 - this.m20 * this.m11) * detInv;
        double i21 = -(this.m00 * this.m21 - this.m20 * this.m01) * detInv;
        double i22 = (this.m00 * this.m11 - this.m10 * this.m01) * detInv;
        double i03 = -(i00 * this.m03 + i01 * this.m13 + i02 * this.m23);
        double i13 = -(i10 * this.m03 + i11 * this.m13 + i12 * this.m23);
        double i23 = -(i20 * this.m03 + i21 * this.m13 + i22 * this.m23);
        return this.set(i00, i01, i02, i03, i10, i11, i12, i13, i20, i21, i22, i23);
    }

    @Override
    public IMatrix3DM transpose() {
        return this.set(
            this.m00, this.m10, this.m20, this.m30(),
            this.m01, this.m11, this.m21, this.m31(),
            this.m02, this.m12, this.m22, this.m32()
        );
    }

    @Override
    public double determinant() {
        return this.m00 * (this.m11 * this.m22 - this.m12 * this.m21) -
                this.m01 * (this.m10 * this.m22 - this.m12 * this.m20) +
                this.m02 * (this.m10 * this.m21 - this.m11 * this.m20);
    }

    @Override
    public boolean isReversible() {
        return Math.abs(this.determinant()) > BananaMath.DOUBLE_EPS;
    }

    @Override
    public IMatrix3DM mul(IMatrix3DC matrix) {
        double m1v00 = matrix.m00(), m1v01 = matrix.m01(), m1v02 = matrix.m02(), m1v03 = matrix.m03();
        double m1v10 = matrix.m10(), m1v11 = matrix.m11(), m1v12 = matrix.m12(), m1v13 = matrix.m13();
        double m1v20 = matrix.m20(), m1v21 = matrix.m21(), m1v22 = matrix.m22(), m1v23 = matrix.m23();
        double m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03;
        double m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13;
        double m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23;
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
    public IMatrix3DM mul(IMatrix3FC matrix) {
        float m1v00 = matrix.m00(), m1v01 = matrix.m01(), m1v02 = matrix.m02(), m1v03 = matrix.m03();
        float m1v10 = matrix.m10(), m1v11 = matrix.m11(), m1v12 = matrix.m12(), m1v13 = matrix.m13();
        float m1v20 = matrix.m20(), m1v21 = matrix.m21(), m1v22 = matrix.m22(), m1v23 = matrix.m23();
        double m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03;
        double m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13;
        double m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23;
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
    public IMatrix3DM mulPost(IMatrix3DC matrix) {
        double m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        double m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        double m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;
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
    public IMatrix3DM mulPost(IMatrix3FC matrix) {
        double m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        double m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        double m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;
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
    public IMatrix3DM div(int val) {
        return this.set(
            this.m00 / val, this.m01 / val, this.m02 / val, this.m03 / val,
            this.m10 / val, this.m11 / val, this.m12 / val, this.m13 / val,
            this.m20 / val, this.m21 / val, this.m22 / val, this.m23 / val
        );
    }

    @Override
    public IMatrix3DM div(double val) {
        return this.set(
            this.m00 / val, this.m01 / val, this.m02 / val, this.m03 / val,
            this.m10 / val, this.m11 / val, this.m12 / val, this.m13 / val,
            this.m20 / val, this.m21 / val, this.m22 / val, this.m23 / val
        );
    }

    @Override
    public IMatrix3DM scale(int val) {
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
    public IMatrix3DM scale(double val) {
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
    public IMatrix3DM scale(int x, int y, int z) {
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
    public IMatrix3DM scale(double x, double y, double z) {
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
    public IMatrix3DM scale(IVec3DC vec) {
        return this.scale(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3DM scale(IVec3IC vec) {
        return this.scale(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3DM scale(IVec2IC vec, int z) {
        return this.scale(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM scale(IVec2IC vec, double z) {
        return this.scale(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM scale(IVec2DC vec, int z) {
        return this.scale(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM scale(IVec2DC vec, double z) {
        return this.scale(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM scale(IVec2IC vec) {
        return this.scale(vec.x(), vec.y(), 1);
    }

    @Override
    public IMatrix3DM scale(IVec2DC vec) {
        return this.scale(vec.x(), vec.y(), 1.0D);
    }

    @Override
    public IMatrix3DM translate(int x, int y, int z) {
        return this.set(
            this.m00, this.m01, this.m02, this.m03 + x,
            this.m10, this.m11, this.m12, this.m13 + y,
            this.m20, this.m21, this.m22, this.m23 + z
        );
    }

    @Override
    public IMatrix3DM translate(double x, double y, double z) {
        return this.set(
            this.m00, this.m01, this.m02, this.m03 + x,
            this.m10, this.m11, this.m12, this.m13 + y,
            this.m20, this.m21, this.m22, this.m23 + z
        );
    }

    @Override
    public IMatrix3DM translate(IVec3DC vec) {
        return this.translate(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3DM translate(IVec3IC vec) {
        return this.translate(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3DM translate(IVec2IC vec, int z) {
        return this.translate(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM translate(IVec2IC vec, double z) {
        return this.translate(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM translate(IVec2DC vec, int z) {
        return this.translate(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM translate(IVec2DC vec, double z) {
        return this.translate(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM translate(IVec2IC vec) {
        return this.translate(vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3DM translate(IVec2DC vec) {
        return this.translate(vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3DM rotateX(double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);

        double m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13;
        double m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23;
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
    public IMatrix3DM rotateY(double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);

        double m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03;
        double m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23;
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
    public IMatrix3DM rotateZ(double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);

        double m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03;
        double m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13;
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
    public IMatrix3DM rotate(double degrees, int x, int y, int z) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }
        double l = Math.sqrt(x * x + y * y + z * z);
        if (l < BananaMath.DOUBLE_EPS) {
            return this;
        }
        double ax = x / l, ay = y / l, az = z / l;
        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);
        double cosM1 = 1 - cos;

        double m1v00 = cos + ax * ax * cosM1, m1v01 = ax * ay * cosM1 - az * sin, m1v02 = ax * az * cosM1 + ay * sin;
        double m1v10 = ay * ax * cosM1 + az * sin, m1v11 = cos + ay * ay * cosM1, m1v12 = ay * az * cosM1 - ax * sin;
        double m1v20 = az * ax * cosM1 - ay * sin, m1v21 = az * ay * cosM1 + ax * sin, m1v22 = cos + az * az * cosM1;

        double m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03;
        double m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13;
        double m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23;
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
    public IMatrix3DM rotate(double degrees, double x, double y, double z) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }
        double l = Math.sqrt(x * x + y * y + z * z);
        if (l < BananaMath.DOUBLE_EPS) {
            return this;
        }
        double ax = x / l, ay = y / l, az = z / l;
        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);
        double cosM1 = 1 - cos;

        double m1v00 = cos + ax * ax * cosM1, m1v01 = ax * ay * cosM1 - az * sin, m1v02 = ax * az * cosM1 + ay * sin;
        double m1v10 = ay * ax * cosM1 + az * sin, m1v11 = cos + ay * ay * cosM1, m1v12 = ay * az * cosM1 - ax * sin;
        double m1v20 = az * ax * cosM1 - ay * sin, m1v21 = az * ay * cosM1 + ax * sin, m1v22 = cos + az * az * cosM1;

        double m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03;
        double m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13;
        double m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23;
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
    public IMatrix3DM rotate(double degrees, IVec3DC vec) {
        return this.rotate(degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3DM rotate(double degrees, IVec3IC vec) {
        return this.rotate(degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3DM rotate(double degrees, IVec2IC vec, int z) {
        return this.rotate(degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM rotate(double degrees, IVec2IC vec, double z) {
        return this.rotate(degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM rotate(double degrees, IVec2DC vec, int z) {
        return this.rotate(degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM rotate(double degrees, IVec2DC vec, double z) {
        return this.rotate(degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM rotate(double degrees, IVec2IC vec) {
        return this.rotate(degrees, vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3DM rotate(double degrees, IVec2DC vec) {
        return this.rotate(degrees, vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3DM rotateXAround(int x, int y, int z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);

        double m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13 - y;
        double m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23 - z;
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
    public IMatrix3DM rotateXAround(double x, double y, double z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);

        double m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13 - y;
        double m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23 - z;
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
    public IMatrix3DM rotateXAround(IVec3IC point, double degrees) {
        return this.rotateXAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3DM rotateXAround(IVec3DC point, double degrees) {
        return this.rotateXAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3DM rotateXAround(IVec2IC vec, int z, double degrees) {
        return this.rotateXAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3DM rotateXAround(IVec2IC vec, double z, double degrees) {
        return this.rotateXAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3DM rotateXAround(IVec2DC vec, int z, double degrees) {
        return this.rotateXAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3DM rotateXAround(IVec2DC vec, double z, double degrees) {
        return this.rotateXAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3DM rotateXAround(IVec2IC vec, double degrees) {
        return this.rotateXAround(vec.x(), vec.y(), 0, degrees);
    }

    @Override
    public IMatrix3DM rotateXAround(IVec2DC vec, double degrees) {
        return this.rotateXAround(vec.x(), vec.y(), 0.0D, degrees);
    }

    @Override
    public IMatrix3DM rotateYAround(int x, int y, int z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);

        double m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03 - x;
        double m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23 - z;
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
    public IMatrix3DM rotateYAround(double x, double y, double z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);

        double m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03 - x;
        double m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23 - z;
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
    public IMatrix3DM rotateYAround(IVec3IC point, double degrees) {
        return this.rotateYAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3DM rotateYAround(IVec3DC point, double degrees) {
        return this.rotateYAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3DM rotateYAround(IVec2IC vec, int z, double degrees) {
        return this.rotateYAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3DM rotateYAround(IVec2IC vec, double z, double degrees) {
        return this.rotateYAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3DM rotateYAround(IVec2DC vec, int z, double degrees) {
        return this.rotateYAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3DM rotateYAround(IVec2DC vec, double z, double degrees) {
        return this.rotateYAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3DM rotateYAround(IVec2IC vec, double degrees) {
        return this.rotateYAround(vec.x(), vec.y(), 0, degrees);
    }

    @Override
    public IMatrix3DM rotateYAround(IVec2DC vec, double degrees) {
        return this.rotateYAround(vec.x(), vec.y(), 0.0D, degrees);
    }

    @Override
    public IMatrix3DM rotateZAround(int x, int y, int z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);

        double m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03 - x;
        double m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13 - y;
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
    public IMatrix3DM rotateZAround(double x, double y, double z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);

        double m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03 - x;
        double m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13 - y;
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
    public IMatrix3DM rotateZAround(IVec3IC point, double degrees) {
        return this.rotateZAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3DM rotateZAround(IVec3DC point, double degrees) {
        return this.rotateZAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3DM rotateZAround(IVec2IC vec, int z, double degrees) {
        return this.rotateZAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3DM rotateZAround(IVec2IC vec, double z, double degrees) {
        return this.rotateZAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3DM rotateZAround(IVec2DC vec, int z, double degrees) {
        return this.rotateZAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3DM rotateZAround(IVec2DC vec, double z, double degrees) {
        return this.rotateZAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3DM rotateZAround(IVec2IC vec, double degrees) {
        return this.rotateZAround(vec.x(), vec.y(), 0, degrees);
    }

    @Override
    public IMatrix3DM rotateZAround(IVec2DC vec, double degrees) {
        return this.rotateZAround(vec.x(), vec.y(), 0.0D, degrees);
    }

    @Override
    public IMatrix3DM rotateAround(IVec3DC point, double degrees, int x, int y, int z) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }
        double l = Math.sqrt(x * x + y * y + z * z);
        if (l < BananaMath.DOUBLE_EPS) {
            return this;
        }
        double ax = x / l, ay = y / l, az = z / l;
        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);
        double cosM1 = 1 - cos;

        double m1v00 = cos + ax * ax * cosM1, m1v01 = ax * ay * cosM1 - az * sin, m1v02 = ax * az * cosM1 + ay * sin;
        double m1v10 = ay * ax * cosM1 + az * sin, m1v11 = cos + ay * ay * cosM1, m1v12 = ay * az * cosM1 - ax * sin;
        double m1v20 = az * ax * cosM1 - ay * sin, m1v21 = az * ay * cosM1 + ax * sin, m1v22 = cos + az * az * cosM1;

        double m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03 - point.x();
        double m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13 - point.y();
        double m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23 - point.z();
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
    public IMatrix3DM rotateAround(IVec3DC point, double degrees, double x, double y, double z) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }
        double l = Math.sqrt(x * x + y * y + z * z);
        if (l < BananaMath.DOUBLE_EPS) {
            return this;
        }
        double ax = x / l, ay = y / l, az = z / l;
        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);
        double cosM1 = 1 - cos;

        double m1v00 = cos + ax * ax * cosM1, m1v01 = ax * ay * cosM1 - az * sin, m1v02 = ax * az * cosM1 + ay * sin;
        double m1v10 = ay * ax * cosM1 + az * sin, m1v11 = cos + ay * ay * cosM1, m1v12 = ay * az * cosM1 - ax * sin;
        double m1v20 = az * ax * cosM1 - ay * sin, m1v21 = az * ay * cosM1 + ax * sin, m1v22 = cos + az * az * cosM1;

        double m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03 - point.x();
        double m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13 - point.y();
        double m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23 - point.z();
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
    public IMatrix3DM rotateAround(IVec3DC point, double degrees, IVec3DC vec) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3DM rotateAround(IVec3DC point, double degrees, IVec3IC vec) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3DM rotateAround(IVec3DC point, double degrees, IVec2IC vec, int z) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM rotateAround(IVec3DC point, double degrees, IVec2IC vec, double z) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM rotateAround(IVec3DC point, double degrees, IVec2DC vec, int z) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM rotateAround(IVec3DC point, double degrees, IVec2DC vec, double z) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM rotateAround(IVec3DC point, double degrees, IVec2IC vec) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3DM rotateAround(IVec3DC point, double degrees, IVec2DC vec) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), 0.0D);
    }

    @Override
    public IMatrix3DM rotateAround(IVec3IC point, double degrees, int x, int y, int z) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }
        double l = Math.sqrt(x * x + y * y + z * z);
        if (l < BananaMath.DOUBLE_EPS) {
            return this;
        }
        double ax = x / l, ay = y / l, az = z / l;
        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);
        double cosM1 = 1 - cos;

        double m1v00 = cos + ax * ax * cosM1, m1v01 = ax * ay * cosM1 - az * sin, m1v02 = ax * az * cosM1 + ay * sin;
        double m1v10 = ay * ax * cosM1 + az * sin, m1v11 = cos + ay * ay * cosM1, m1v12 = ay * az * cosM1 - ax * sin;
        double m1v20 = az * ax * cosM1 - ay * sin, m1v21 = az * ay * cosM1 + ax * sin, m1v22 = cos + az * az * cosM1;

        double m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03 - point.x();
        double m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13 - point.y();
        double m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23 - point.z();
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
    public IMatrix3DM rotateAround(IVec3IC point, double degrees, double x, double y, double z) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }
        double l = Math.sqrt(x * x + y * y + z * z);
        if (l < BananaMath.DOUBLE_EPS) {
            return this;
        }
        double ax = x / l, ay = y / l, az = z / l;
        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);
        double cosM1 = 1 - cos;

        double m1v00 = cos + ax * ax * cosM1, m1v01 = ax * ay * cosM1 - az * sin, m1v02 = ax * az * cosM1 + ay * sin;
        double m1v10 = ay * ax * cosM1 + az * sin, m1v11 = cos + ay * ay * cosM1, m1v12 = ay * az * cosM1 - ax * sin;
        double m1v20 = az * ax * cosM1 - ay * sin, m1v21 = az * ay * cosM1 + ax * sin, m1v22 = cos + az * az * cosM1;

        double m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03 - point.x();
        double m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13 - point.y();
        double m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23 - point.z();
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
    public IMatrix3DM rotateAround(IVec3IC point, double degrees, IVec3DC vec) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3DM rotateAround(IVec3IC point, double degrees, IVec3IC vec) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3DM rotateAround(IVec3IC point, double degrees, IVec2IC vec, int z) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM rotateAround(IVec3IC point, double degrees, IVec2IC vec, double z) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM rotateAround(IVec3IC point, double degrees, IVec2DC vec, int z) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM rotateAround(IVec3IC point, double degrees, IVec2DC vec, double z) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM rotateAround(IVec3IC point, double degrees, IVec2IC vec) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3DM rotateAround(IVec3IC point, double degrees, IVec2DC vec) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), 0.0D);
    }

    @Override
    public IMatrix3DM localScale(int val) {
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
    public IMatrix3DM localScale(double val) {
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
    public IMatrix3DM localScale(int x, int y, int z) {
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
    public IMatrix3DM localScale(double x, double y, double z) {
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
    public IMatrix3DM localScale(IVec3DC vec) {
        return this.localScale(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3DM localScale(IVec3IC vec) {
        return this.localScale(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3DM localScale(IVec2IC vec, int z) {
        return this.localScale(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM localScale(IVec2IC vec, double z) {
        return this.localScale(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM localScale(IVec2DC vec, int z) {
        return this.localScale(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM localScale(IVec2DC vec, double z) {
        return this.localScale(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM localScale(IVec2IC vec) {
        return this.localScale(vec.x(), vec.y(), 1);
    }

    @Override
    public IMatrix3DM localScale(IVec2DC vec) {
        return this.localScale(vec.x(), vec.y(), 1.0D);
    }

    @Override
    public IMatrix3DM localTranslate(int x, int y, int z) {
        double m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        double m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        double m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;
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
    public IMatrix3DM localTranslate(double x, double y, double z) {
        double m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        double m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        double m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;
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
    public IMatrix3DM localTranslate(IVec3DC vec) {
        return this.localTranslate(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3DM localTranslate(IVec3IC vec) {
        return this.localTranslate(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3DM localTranslate(IVec2IC vec, int z) {
        return this.localTranslate(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM localTranslate(IVec2IC vec, double z) {
        return this.localTranslate(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM localTranslate(IVec2DC vec, int z) {
        return this.localTranslate(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM localTranslate(IVec2DC vec, double z) {
        return this.localTranslate(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM localTranslate(IVec2IC vec) {
        return this.localTranslate(vec.x(), vec.y(), 0.0D);
    }

    @Override
    public IMatrix3DM localTranslate(IVec2DC vec) {
        return this.localTranslate(vec.x(), vec.y(), 0.0D);
    }

    @Override
    public IMatrix3DM localRotateX(double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);

        double m1v01 = this.m01, m1v02 = this.m02;
        double m1v11 = this.m11, m1v12 = this.m12;
        double m1v21 = this.m21, m1v22 = this.m22;
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
    public IMatrix3DM localRotateY(double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);

        double m1v00 = this.m00, m1v02 = this.m02;
        double m1v10 = this.m10, m1v12 = this.m12;
        double m1v20 = this.m20, m1v22 = this.m22;
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
    public IMatrix3DM localRotateZ(double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);

        double m1v00 = this.m00, m1v01 = this.m01;
        double m1v10 = this.m10, m1v11 = this.m11;
        double m1v20 = this.m20, m1v21 = this.m21;
        return this.set(
            (m1v00 * cos) + (m1v01 * sin),
            (m1v00 * -sin) + (m1v01 * cos),
            this.m02,
            this.m03,

            (m1v10 * cos) + (m1v11 * sin),
            (m1v10 * -sin) + (m1v11 * cos),
            this.m12,
            this.m13,

            (m1v20 * cos) + (m1v21 * sin),
            (m1v20 * -sin) + (m1v21 * cos),
            this.m22,
            this.m23
        );
    }

    @Override
    public IMatrix3DM localRotate(double degrees, int x, int y, int z) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }
        double l = Math.sqrt(x * x + y * y + z * z);
        if (l < BananaMath.DOUBLE_EPS) {
            return this;
        }
        double ax = x / l, ay = y / l, az = z / l;
        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);
        double cosM1 = 1 - cos;

        double m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        double m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        double m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;
        double m2v00 = cos + ax * ax * cosM1, m2v01 = ax * ay * cosM1 - az * sin, m2v02 = ax * az * cosM1 + ay * sin;
        double m2v10 = ay * ax * cosM1 + az * sin, m2v11 = cos + ay * ay * cosM1, m2v12 = ay * az * cosM1 - ax * sin;
        double m2v20 = az * ax * cosM1 - ay * sin, m2v21 = az * ay * cosM1 + ax * sin, m2v22 = cos + az * az * cosM1;
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
    public IMatrix3DM localRotate(double degrees, double x, double y, double z) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }
        double l = Math.sqrt(x * x + y * y + z * z);
        if (l < BananaMath.DOUBLE_EPS) {
            return this;
        }
        double ax = x / l, ay = y / l, az = z / l;
        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);
        double cosM1 = 1 - cos;

        double m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        double m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        double m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;
        double m2v00 = cos + ax * ax * cosM1, m2v01 = ax * ay * cosM1 - az * sin, m2v02 = ax * az * cosM1 + ay * sin;
        double m2v10 = ay * ax * cosM1 + az * sin, m2v11 = cos + ay * ay * cosM1, m2v12 = ay * az * cosM1 - ax * sin;
        double m2v20 = az * ax * cosM1 - ay * sin, m2v21 = az * ay * cosM1 + ax * sin, m2v22 = cos + az * az * cosM1;
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
    public IMatrix3DM localRotate(double degrees, IVec3DC vec) {
        return this.localRotate(degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3DM localRotate(double degrees, IVec3IC vec) {
        return this.localRotate(degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3DM localRotate(double degrees, IVec2IC vec, int z) {
        return this.localRotate(degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM localRotate(double degrees, IVec2IC vec, double z) {
        return this.localRotate(degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM localRotate(double degrees, IVec2DC vec, int z) {
        return this.localRotate(degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM localRotate(double degrees, IVec2DC vec, double z) {
        return this.localRotate(degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM localRotate(double degrees, IVec2IC vec) {
        return this.localRotate(degrees, vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3DM localRotate(double degrees, IVec2DC vec) {
        return this.localRotate(degrees, vec.x(), vec.y(), 0.0D);
    }

    @Override
    public IMatrix3DM localRotateXAround(int x, int y, int z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);

        double m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        double m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        double m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;

        double m2v01 = (m1v01 * cos) + (m1v02 * sin), m2v02 = (m1v01 * -sin) + (m1v02 * cos), m2v03 = (m1v00 * x) + (m1v01 * y) + (m1v02 * z) + (this.m03);
        double m2v11 = (m1v11 * cos) + (m1v12 * sin), m2v12 = (m1v11 * -sin) + (m1v12 * cos), m2v13 = (m1v10 * x) + (m1v11 * y) + (m1v12 * z) + (this.m13);
        double m2v21 = (m1v21 * cos) + (m1v22 * sin), m2v22 = (m1v21 * -sin) + (m1v22 * cos), m2v23 = (m1v20 * x) + (m1v21 * y) + (m1v22 * z) + (this.m23);

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
    public IMatrix3DM localRotateXAround(double x, double y, double z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);

        double m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        double m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        double m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;

        double m2v01 = (m1v01 * cos) + (m1v02 * sin), m2v02 = (m1v01 * -sin) + (m1v02 * cos), m2v03 = (m1v00 * x) + (m1v01 * y) + (m1v02 * z) + (this.m03);
        double m2v11 = (m1v11 * cos) + (m1v12 * sin), m2v12 = (m1v11 * -sin) + (m1v12 * cos), m2v13 = (m1v10 * x) + (m1v11 * y) + (m1v12 * z) + (this.m13);
        double m2v21 = (m1v21 * cos) + (m1v22 * sin), m2v22 = (m1v21 * -sin) + (m1v22 * cos), m2v23 = (m1v20 * x) + (m1v21 * y) + (m1v22 * z) + (this.m23);

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
    public IMatrix3DM localRotateXAround(IVec3IC point, double degrees) {
        return this.localRotateXAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3DM localRotateXAround(IVec3DC point, double degrees) {
        return this.localRotateXAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3DM localRotateXAround(IVec2IC vec, int z, double degrees) {
        return this.localRotateXAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3DM localRotateXAround(IVec2IC vec, double z, double degrees) {
        return this.localRotateXAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3DM localRotateXAround(IVec2DC vec, int z, double degrees) {
        return this.localRotateXAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3DM localRotateXAround(IVec2DC vec, double z, double degrees) {
        return this.localRotateXAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3DM localRotateXAround(IVec2IC vec, double degrees) {
        return this.localRotateXAround(vec.x(), vec.y(), 0, degrees);
    }

    @Override
    public IMatrix3DM localRotateXAround(IVec2DC vec, double degrees) {
        return this.localRotateXAround(vec.x(), vec.y(), 0.0D, degrees);
    }

    @Override
    public IMatrix3DM localRotateYAround(int x, int y, int z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);

        double m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        double m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        double m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;

        double m2v00 = (m1v00 * cos) + (m1v02 * -sin), m2v02 = (m1v00 * sin) + (m1v02 * cos), m2v03 = (m1v00 * x) + (m1v01 * y) + (m1v02 * z) + (this.m03);
        double m2v10 = (m1v10 * cos) + (m1v12 * -sin), m2v12 = (m1v10 * sin) + (m1v12 * cos), m2v13 = (m1v10 * x) + (m1v11 * y) + (m1v12 * z) + (this.m13);
        double m2v20 = (m1v20 * cos) + (m1v22 * -sin), m2v22 = (m1v20 * sin) + (m1v22 * cos), m2v23 = (m1v20 * x) + (m1v21 * y) + (m1v22 * z) + (this.m23);

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
    public IMatrix3DM localRotateYAround(double x, double y, double z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);

        double m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        double m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        double m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;

        double m2v00 = (m1v00 * cos) + (m1v02 * -sin), m2v02 = (m1v00 * sin) + (m1v02 * cos), m2v03 = (m1v00 * x) + (m1v01 * y) + (m1v02 * z) + (this.m03);
        double m2v10 = (m1v10 * cos) + (m1v12 * -sin), m2v12 = (m1v10 * sin) + (m1v12 * cos), m2v13 = (m1v10 * x) + (m1v11 * y) + (m1v12 * z) + (this.m13);
        double m2v20 = (m1v20 * cos) + (m1v22 * -sin), m2v22 = (m1v20 * sin) + (m1v22 * cos), m2v23 = (m1v20 * x) + (m1v21 * y) + (m1v22 * z) + (this.m23);

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
    public IMatrix3DM localRotateYAround(IVec3IC point, double degrees) {
        return this.localRotateYAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3DM localRotateYAround(IVec3DC point, double degrees) {
        return this.localRotateYAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3DM localRotateYAround(IVec2IC vec, int z, double degrees) {
        return this.localRotateYAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3DM localRotateYAround(IVec2IC vec, double z, double degrees) {
        return this.localRotateYAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3DM localRotateYAround(IVec2DC vec, int z, double degrees) {
        return this.localRotateYAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3DM localRotateYAround(IVec2DC vec, double z, double degrees) {
        return this.localRotateYAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3DM localRotateYAround(IVec2IC vec, double degrees) {
        return this.localRotateYAround(vec.x(), vec.y(), 0, degrees);
    }

    @Override
    public IMatrix3DM localRotateYAround(IVec2DC vec, double degrees) {
        return this.localRotateYAround(vec.x(), vec.y(), 0.0D, degrees);
    }

    @Override
    public IMatrix3DM localRotateZAround(int x, int y, int z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);

        double m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        double m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        double m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;

        double m2v00 = (m1v00 * cos) + (m1v01 * sin), m2v01 = (m1v00 * -sin) + (m1v01 * cos), m2v03 = (m1v00 * x) + (m1v01 * y) + (m1v02 * z) + (this.m03);
        double m2v10 = (m1v10 * cos) + (m1v11 * sin), m2v11 = (m1v10 * -sin) + (m1v11 * cos), m2v13 = (m1v10 * x) + (m1v11 * y) + (m1v12 * z) + (this.m13);
        double m2v20 = (m1v20 * cos) + (m1v21 * sin), m2v21 = (m1v20 * -sin) + (m1v21 * cos), m2v23 = (m1v20 * x) + (m1v21 * y) + (m1v22 * z) + (this.m23);

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
    public IMatrix3DM localRotateZAround(double x, double y, double z, double degrees) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }

        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);

        double m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        double m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        double m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;

        double m2v00 = (m1v00 * cos) + (m1v01 * sin), m2v01 = (m1v00 * -sin) + (m1v01 * cos), m2v03 = (m1v00 * x) + (m1v01 * y) + (m1v02 * z) + (this.m03);
        double m2v10 = (m1v10 * cos) + (m1v11 * sin), m2v11 = (m1v10 * -sin) + (m1v11 * cos), m2v13 = (m1v10 * x) + (m1v11 * y) + (m1v12 * z) + (this.m13);
        double m2v20 = (m1v20 * cos) + (m1v21 * sin), m2v21 = (m1v20 * -sin) + (m1v21 * cos), m2v23 = (m1v20 * x) + (m1v21 * y) + (m1v22 * z) + (this.m23);

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
    public IMatrix3DM localRotateZAround(IVec3IC point, double degrees) {
        return this.localRotateZAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3DM localRotateZAround(IVec3DC point, double degrees) {
        return this.localRotateZAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3DM localRotateZAround(IVec2IC vec, int z, double degrees) {
        return this.localRotateZAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3DM localRotateZAround(IVec2IC vec, double z, double degrees) {
        return this.localRotateZAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3DM localRotateZAround(IVec2DC vec, int z, double degrees) {
        return this.localRotateZAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3DM localRotateZAround(IVec2DC vec, double z, double degrees) {
        return this.localRotateZAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3DM localRotateZAround(IVec2IC vec, double degrees) {
        return this.localRotateZAround(vec.x(), vec.y(), 0, degrees);
    }

    @Override
    public IMatrix3DM localRotateZAround(IVec2DC vec, double degrees) {
        return this.localRotateZAround(vec.x(), vec.y(), 0.0D, degrees);
    }

    @Override
    public IMatrix3DM localRotateAround(IVec3DC point, double degrees, int x, int y, int z) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }
        double l = Math.sqrt(x * x + y * y + z * z);
        if (l < BananaMath.DOUBLE_EPS) {
            return this;
        }
        double ax = x / l, ay = y / l, az = z / l;
        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);
        double cosM1 = 1 - cos;

        double m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        double m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        double m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;
        double m2v00 = cos + ax * ax * cosM1, m2v01 = ax * ay * cosM1 - az * sin, m2v02 = ax * az * cosM1 + ay * sin, m2v03 = (m1v00 * point.x()) + (m1v01 * point.y()) + (m1v02 * point.z()) + (this.m03);
        double m2v10 = ay * ax * cosM1 + az * sin, m2v11 = cos + ay * ay * cosM1, m2v12 = ay * az * cosM1 - ax * sin, m2v13 = (m1v10 * point.x()) + (m1v11 * point.y()) + (m1v12 * point.z()) + (this.m13);
        double m2v20 = az * ax * cosM1 - ay * sin, m2v21 = az * ay * cosM1 + ax * sin, m2v22 = cos + az * az * cosM1, m2v23 = (m1v20 * point.x()) + (m1v21 * point.y()) + (m1v22 * point.z()) + (this.m23);
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
    public IMatrix3DM localRotateAround(IVec3DC point, double degrees, double x, double y, double z) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }
        double l = Math.sqrt(x * x + y * y + z * z);
        if (l < BananaMath.DOUBLE_EPS) {
            return this;
        }
        double ax = x / l, ay = y / l, az = z / l;
        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);
        double cosM1 = 1 - cos;

        double m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        double m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        double m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;
        double m2v00 = cos + ax * ax * cosM1, m2v01 = ax * ay * cosM1 - az * sin, m2v02 = ax * az * cosM1 + ay * sin, m2v03 = (m1v00 * point.x()) + (m1v01 * point.y()) + (m1v02 * point.z()) + (this.m03);
        double m2v10 = ay * ax * cosM1 + az * sin, m2v11 = cos + ay * ay * cosM1, m2v12 = ay * az * cosM1 - ax * sin, m2v13 = (m1v10 * point.x()) + (m1v11 * point.y()) + (m1v12 * point.z()) + (this.m13);
        double m2v20 = az * ax * cosM1 - ay * sin, m2v21 = az * ay * cosM1 + ax * sin, m2v22 = cos + az * az * cosM1, m2v23 = (m1v20 * point.x()) + (m1v21 * point.y()) + (m1v22 * point.z()) + (this.m23);
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
    public IMatrix3DM localRotateAround(IVec3DC point, double degrees, IVec3DC vec) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3DM localRotateAround(IVec3DC point, double degrees, IVec3IC vec) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3DM localRotateAround(IVec3DC point, double degrees, IVec2IC vec, int z) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM localRotateAround(IVec3DC point, double degrees, IVec2IC vec, double z) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM localRotateAround(IVec3DC point, double degrees, IVec2DC vec, int z) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM localRotateAround(IVec3DC point, double degrees, IVec2DC vec, double z) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM localRotateAround(IVec3DC point, double degrees, IVec2IC vec) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3DM localRotateAround(IVec3DC point, double degrees, IVec2DC vec) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), 0.0D);
    }

    @Override
    public IMatrix3DM localRotateAround(IVec3IC point, double degrees, int x, int y, int z) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }
        double l = Math.sqrt(x * x + y * y + z * z);
        if (l < BananaMath.DOUBLE_EPS) {
            return this;
        }
        double ax = x / l, ay = y / l, az = z / l;
        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);
        double cosM1 = 1 - cos;

        double m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        double m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        double m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;
        double m2v00 = cos + ax * ax * cosM1, m2v01 = ax * ay * cosM1 - az * sin, m2v02 = ax * az * cosM1 + ay * sin, m2v03 = (m1v00 * point.x()) + (m1v01 * point.y()) + (m1v02 * point.z()) + (this.m03);
        double m2v10 = ay * ax * cosM1 + az * sin, m2v11 = cos + ay * ay * cosM1, m2v12 = ay * az * cosM1 - ax * sin, m2v13 = (m1v10 * point.x()) + (m1v11 * point.y()) + (m1v12 * point.z()) + (this.m13);
        double m2v20 = az * ax * cosM1 - ay * sin, m2v21 = az * ay * cosM1 + ax * sin, m2v22 = cos + az * az * cosM1, m2v23 = (m1v20 * point.x()) + (m1v21 * point.y()) + (m1v22 * point.z()) + (this.m23);
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
    public IMatrix3DM localRotateAround(IVec3IC point, double degrees, double x, double y, double z) {
        if (Math.abs(degrees) < BananaMath.DOUBLE_EPS) {
            return this;
        }
        double l = Math.sqrt(x * x + y * y + z * z);
        if (l < BananaMath.DOUBLE_EPS) {
            return this;
        }
        double ax = x / l, ay = y / l, az = z / l;
        double rads = Math.toRadians(degrees);
        double sin = BananaMath.sin(rads);
        double cos = BananaMath.cos(rads);
        double cosM1 = 1 - cos;

        double m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        double m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        double m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;
        double m2v00 = cos + ax * ax * cosM1, m2v01 = ax * ay * cosM1 - az * sin, m2v02 = ax * az * cosM1 + ay * sin, m2v03 = (m1v00 * point.x()) + (m1v01 * point.y()) + (m1v02 * point.z()) + (this.m03);
        double m2v10 = ay * ax * cosM1 + az * sin, m2v11 = cos + ay * ay * cosM1, m2v12 = ay * az * cosM1 - ax * sin, m2v13 = (m1v10 * point.x()) + (m1v11 * point.y()) + (m1v12 * point.z()) + (this.m13);
        double m2v20 = az * ax * cosM1 - ay * sin, m2v21 = az * ay * cosM1 + ax * sin, m2v22 = cos + az * az * cosM1, m2v23 = (m1v20 * point.x()) + (m1v21 * point.y()) + (m1v22 * point.z()) + (this.m23);
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
    public IMatrix3DM localRotateAround(IVec3IC point, double degrees, IVec3DC vec) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3DM localRotateAround(IVec3IC point, double degrees, IVec3IC vec) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3DM localRotateAround(IVec3IC point, double degrees, IVec2IC vec, int z) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM localRotateAround(IVec3IC point, double degrees, IVec2IC vec, double z) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM localRotateAround(IVec3IC point, double degrees, IVec2DC vec, int z) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM localRotateAround(IVec3IC point, double degrees, IVec2DC vec, double z) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3DM localRotateAround(IVec3IC point, double degrees, IVec2IC vec) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3DM localRotateAround(IVec3IC point, double degrees, IVec2DC vec) {
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
    public DoubleBuffer writeToBuffer(DoubleBuffer buf) {
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

        buf.put(0.0D);
        buf.put(0.0D);
        buf.put(0.0D);
        buf.put(1.0D);

        return buf;
    }

    @Override
    public IMatrix3DM toMutable() {
        return this;
    }

    @Override
    public IMatrix3D toImmutable() {
        return new Matrix3D(this);
    }

    @Override
    public IMatrix3FM toFloat() {
        return new Matrix3FM(this);
    }

    @Override
    public IMatrix3DM copy() {
        Matrix3DM copy = new Matrix3DM(this);
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
        if (obj instanceof IMatrix3D) {
            IMatrix3D m = ((IMatrix3D) obj);
            return
                    m.m00() == this.m00 && m.m01() == this.m01 && m.m02() == this.m02 && m.m03() == this.m03 &&
                    m.m10() == this.m10 && m.m11() == this.m11 && m.m12() == this.m12 && m.m13() == this.m13 &&
                    m.m20() == this.m20 && m.m21() == this.m21 && m.m22() == this.m22 && m.m23() == this.m23 &&
                    m.m30() == 0.0D && m.m31() == 0.0D && m.m32() == 0.0D && m.m33() == 1.0D;
        } else if (obj instanceof IMatrix3F) {
            IMatrix3F m = ((IMatrix3F) obj);
            return
                    m.m00() == this.m00 && m.m01() == this.m01 && m.m02() == this.m02 && m.m03() == this.m03 &&
                    m.m10() == this.m10 && m.m11() == this.m11 && m.m12() == this.m12 && m.m13() == this.m13 &&
                    m.m20() == this.m20 && m.m21() == this.m21 && m.m22() == this.m22 && m.m23() == this.m23 &&
                    m.m30() == 0.0D && m.m31() == 0.0D && m.m32() == 0.0D && m.m33() == 1.0D;
        }
        return false;
    }

    @Override
    public boolean equalsEps(Object obj, double eps) {
        if (obj instanceof IMatrix3D) {
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

                    Math.abs(m.m30() - 0.0D) <= eps &&
                    Math.abs(m.m31() - 0.0D) <= eps &&
                    Math.abs(m.m32() - 0.0D) <= eps &&
                    Math.abs(m.m33() - 1.0D) <= eps;
        } else if (obj instanceof IMatrix3F) {
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

                    Math.abs(m.m30() - 0.0D) <= eps &&
                    Math.abs(m.m31() - 0.0D) <= eps &&
                    Math.abs(m.m32() - 0.0D) <= eps &&
                    Math.abs(m.m33() - 1.0D) <= eps;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Hasher.hash(
            this.m00, this.m01, this.m02, this.m03,
            this.m10, this.m11, this.m12, this.m13,
            this.m20, this.m21, this.m22, this.m23,
            0.0D, 0.0D, 0.0D, 1.0D
        );
    }
}
