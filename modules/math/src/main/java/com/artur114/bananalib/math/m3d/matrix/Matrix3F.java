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
import com.artur114.bananalib.math.internal.Hasher;
import com.artur114.bananalib.math.m2d.vec.*;
import com.artur114.bananalib.math.m3d.box.*;
import com.artur114.bananalib.math.m3d.vec.*;

import java.nio.FloatBuffer;

public class Matrix3F implements IMatrix3F {
    public static final Matrix3F IDENTITY = new Matrix3F();
    private final float m00, m01, m02, m03;
    private final float m10, m11, m12, m13;
    private final float m20, m21, m22, m23;
    private final float det;
    private final int hash;

    public Matrix3F() {
        this.m00 = 1.0F; this.m01 = 0.0F; this.m02 = 0.0F; this.m03 = 0.0F;
        this.m10 = 0.0F; this.m11 = 1.0F; this.m12 = 0.0F; this.m13 = 0.0F;
        this.m20 = 0.0F; this.m21 = 0.0F; this.m22 = 1.0F; this.m23 = 0.0F;
        this.hash = Hasher.hashIEEE754(
                this.m00, this.m01, this.m02, this.m03,
                this.m10, this.m11, this.m12, this.m13,
                this.m20, this.m21, this.m22, this.m23,
                0.0F, 0.0F, 0.0F, 1.0F
        );
        this.det = 1.0F;
    }

    public Matrix3F(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23) {
        this.m00 = (float) m00; this.m01 = (float) m01; this.m02 = (float) m02; this.m03 = (float) m03;
        this.m10 = (float) m10; this.m11 = (float) m11; this.m12 = (float) m12; this.m13 = (float) m13;
        this.m20 = (float) m20; this.m21 = (float) m21; this.m22 = (float) m22; this.m23 = (float) m23;
        this.hash = Hasher.hashIEEE754(
                this.m00, this.m01, this.m02, this.m03,
                this.m10, this.m11, this.m12, this.m13,
                this.m20, this.m21, this.m22, this.m23,
                0.0F, 0.0F, 0.0F, 1.0F
        );
        this.det =
            (float) (m00 * (m11 * m22 - m12 * m21) -
                    m01 * (m10 * m22 - m12 * m20) +
                    m02 * (m10 * m21 - m11 * m20));
    }

    public Matrix3F(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21, float m22, float m23) {
        this.m00 = m00; this.m01 = m01; this.m02 = m02; this.m03 = m03;
        this.m10 = m10; this.m11 = m11; this.m12 = m12; this.m13 = m13;
        this.m20 = m20; this.m21 = m21; this.m22 = m22; this.m23 = m23;
        this.hash = Hasher.hashIEEE754(
                this.m00, this.m01, this.m02, this.m03,
                this.m10, this.m11, this.m12, this.m13,
                this.m20, this.m21, this.m22, this.m23,
                0.0F, 0.0F, 0.0F, 1.0F
        );
        this.det =
                (m00 * (m11 * m22 - m12 * m21) -
                m01 * (m10 * m22 - m12 * m20) +
                m02 * (m10 * m21 - m11 * m20));
    }


    public Matrix3F(IMatrix3D m) {
        this(
            m.m00(), m.m01(), m.m02(), m.m03(),
            m.m10(), m.m11(), m.m12(), m.m13(),
            m.m20(), m.m21(), m.m22(), m.m23()
        );
    }

    public Matrix3F(IMatrix3F m) {
        this(
            m.m00(), m.m01(), m.m02(), m.m03(),
            m.m10(), m.m11(), m.m12(), m.m13(),
            m.m20(), m.m21(), m.m22(), m.m23()
        );
    }

    public Matrix3F(FloatBuffer buf) {
        this(
            buf.get(), buf.get(), buf.get(), buf.get(),
            buf.get(), buf.get(), buf.get(), buf.get(),
            buf.get(), buf.get(), buf.get(), buf.get()
        );
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
    public IMatrix3F invert() {
        if (!this.isReversible()) throw new ArithmeticException("Couldn't invert unreversible matrix: " + this);
        float detInv = 1.0F / this.det;
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
        return new Matrix3F(i00, i01, i02, i03, i10, i11, i12, i13, i20, i21, i22, i23);
    }

    @Override
    public IMatrix3F transpose() {
        return new Matrix3F(
            this.m00, this.m10, this.m20, this.m30(),
            this.m01, this.m11, this.m21, this.m31(),
            this.m02, this.m12, this.m22, this.m32()
        );
    }

    @Override
    public float determinant() {
        return this.det;
    }

    @Override
    public boolean isReversible() {
        return Math.abs(this.det) > BananaMath.FLOAT_EPS;
    }

    @Override
    public IMatrix3F mul(IMatrix3DC matrix) {
        double m1v00 = matrix.m00(), m1v01 = matrix.m01(), m1v02 = matrix.m02(), m1v03 = matrix.m03();
        double m1v10 = matrix.m10(), m1v11 = matrix.m11(), m1v12 = matrix.m12(), m1v13 = matrix.m13();
        double m1v20 = matrix.m20(), m1v21 = matrix.m21(), m1v22 = matrix.m22(), m1v23 = matrix.m23();
        float m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03;
        float m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13;
        float m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23;
        return new Matrix3F(
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
    public IMatrix3F mul(IMatrix3FC matrix) {
        float m1v00 = matrix.m00(), m1v01 = matrix.m01(), m1v02 = matrix.m02(), m1v03 = matrix.m03();
        float m1v10 = matrix.m10(), m1v11 = matrix.m11(), m1v12 = matrix.m12(), m1v13 = matrix.m13();
        float m1v20 = matrix.m20(), m1v21 = matrix.m21(), m1v22 = matrix.m22(), m1v23 = matrix.m23();
        float m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03;
        float m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13;
        float m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23;
        return new Matrix3F(
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
    public IMatrix3F mulPost(IMatrix3DC matrix) {
        float m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        float m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        float m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;
        double m2v00 = matrix.m00(), m2v01 = matrix.m01(), m2v02 = matrix.m02(), m2v03 = matrix.m03();
        double m2v10 = matrix.m10(), m2v11 = matrix.m11(), m2v12 = matrix.m12(), m2v13 = matrix.m13();
        double m2v20 = matrix.m20(), m2v21 = matrix.m21(), m2v22 = matrix.m22(), m2v23 = matrix.m23();
        return new Matrix3F(
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
    public IMatrix3F mulPost(IMatrix3FC matrix) {
        float m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        float m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        float m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;
        float m2v00 = matrix.m00(), m2v01 = matrix.m01(), m2v02 = matrix.m02(), m2v03 = matrix.m03();
        float m2v10 = matrix.m10(), m2v11 = matrix.m11(), m2v12 = matrix.m12(), m2v13 = matrix.m13();
        float m2v20 = matrix.m20(), m2v21 = matrix.m21(), m2v22 = matrix.m22(), m2v23 = matrix.m23();
        return new Matrix3F(
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
    public IMatrix3F div(int val) {
        return new Matrix3F(
            this.m00 / val, this.m01 / val, this.m02 / val, this.m03 / val,
            this.m10 / val, this.m11 / val, this.m12 / val, this.m13 / val,
            this.m20 / val, this.m21 / val, this.m22 / val, this.m23 / val
        );
    }

    @Override
    public IMatrix3F div(float val) {
        return new Matrix3F(
            this.m00 / val, this.m01 / val, this.m02 / val, this.m03 / val,
            this.m10 / val, this.m11 / val, this.m12 / val, this.m13 / val,
            this.m20 / val, this.m21 / val, this.m22 / val, this.m23 / val
        );
    }

    @Override
    public IMatrix3F div(double val) {
        return this.div((float) val);
    }

    @Override
    public IMatrix3F scale(int val) {
        return new Matrix3F(
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
    public IMatrix3F scale(float val) {
        return new Matrix3F(
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
    public IMatrix3F scale(double val) {
        return this.scale((float) val);
    }

    @Override
    public IMatrix3F scale(int x, int y, int z) {
        return new Matrix3F(
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
    public IMatrix3F scale(float x, float y, float z) {
        return new Matrix3F(
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
    public IMatrix3F scale(double x, double y, double z) {
        return this.scale((float) x, (float) y, (float) z);
    }

    @Override
    public IMatrix3F scale(IVec3DC vec) {
        return this.scale(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3F scale(IVec3IC vec) {
        return this.scale(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3F scale(IVec2IC vec, int z) {
        return this.scale(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F scale(IVec2IC vec, double z) {
        return this.scale(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F scale(IVec2DC vec, int z) {
        return this.scale(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F scale(IVec2DC vec, double z) {
        return this.scale(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F scale(IVec2IC vec) {
        return this.scale(vec.x(), vec.y(), 1);
    }

    @Override
    public IMatrix3F scale(IVec2DC vec) {
        return this.scale(vec.x(), vec.y(), 1.0D);
    }

    @Override
    public IMatrix3F translate(int x, int y, int z) {
        return new Matrix3F(
            this.m00, this.m01, this.m02, this.m03 + x,
            this.m10, this.m11, this.m12, this.m13 + y,
            this.m20, this.m21, this.m22, this.m23 + z
        );
    }

    @Override
    public IMatrix3F translate(float x, float y, float z) {
        return new Matrix3F(
            this.m00, this.m01, this.m02, this.m03 + x,
            this.m10, this.m11, this.m12, this.m13 + y,
            this.m20, this.m21, this.m22, this.m23 + z
        );
    }

    @Override
    public IMatrix3F translate(double x, double y, double z) {
        return this.translate((float) x, (float) y, (float) z);
    }

    @Override
    public IMatrix3F translate(IVec3DC vec) {
        return this.translate(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3F translate(IVec3IC vec) {
        return this.translate(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3F translate(IVec2IC vec, int z) {
        return this.translate(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F translate(IVec2IC vec, double z) {
        return this.translate(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F translate(IVec2DC vec, int z) {
        return this.translate(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F translate(IVec2DC vec, double z) {
        return this.translate(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F translate(IVec2IC vec) {
        return this.translate(vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3F translate(IVec2DC vec) {
        return this.translate(vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3F rotateX(float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13;
        float m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23;
        return new Matrix3F(
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
    public IMatrix3F rotateY(float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03;
        float m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23;
        return new Matrix3F(
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
    public IMatrix3F rotateZ(float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03;
        float m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13;
        return new Matrix3F(
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
    public IMatrix3F rotate(float degrees, int x, int y, int z) {
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
        return new Matrix3F(
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
    public IMatrix3F rotate(float degrees, float x, float y, float z) {
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
        return new Matrix3F(
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
    public IMatrix3F rotate(float degrees, double x, double y, double z) {
        return this.rotate(degrees, (float) x, (float) y, (float) z);
    }

    @Override
    public IMatrix3F rotate(float degrees, IVec3DC vec) {
        return this.rotate(degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3F rotate(float degrees, IVec3IC vec) {
        return this.rotate(degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3F rotate(float degrees, IVec2IC vec, int z) {
        return this.rotate(degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F rotate(float degrees, IVec2IC vec, double z) {
        return this.rotate(degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F rotate(float degrees, IVec2DC vec, int z) {
        return this.rotate(degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F rotate(float degrees, IVec2DC vec, double z) {
        return this.rotate(degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F rotate(float degrees, IVec2IC vec) {
        return this.rotate(degrees, vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3F rotate(float degrees, IVec2DC vec) {
        return this.rotate(degrees, vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3F rotateXAround(int x, int y, int z, float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13 - y;
        float m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23 - z;
        return new Matrix3F(
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
    public IMatrix3F rotateXAround(float x, float y, float z, float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13 - y;
        float m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23 - z;
        return new Matrix3F(
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
    public IMatrix3F rotateXAround(double x, double y, double z, float degrees) {
        return this.rotateXAround((float) x, (float) y, (float) z, degrees);
    }

    @Override
    public IMatrix3F rotateXAround(IVec3IC point, float degrees) {
        return this.rotateXAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3F rotateXAround(IVec3DC point, float degrees) {
        return this.rotateXAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3F rotateXAround(IVec2IC vec, int z, float degrees) {
        return this.rotateXAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3F rotateXAround(IVec2IC vec, double z, float degrees) {
        return this.rotateXAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3F rotateXAround(IVec2DC vec, int z, float degrees) {
        return this.rotateXAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3F rotateXAround(IVec2DC vec, double z, float degrees) {
        return this.rotateXAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3F rotateXAround(IVec2IC vec, float degrees) {
        return this.rotateXAround(vec.x(), vec.y(), 0, degrees);
    }

    @Override
    public IMatrix3F rotateXAround(IVec2DC vec, float degrees) {
        return this.rotateXAround(vec.x(), vec.y(), 0.0D, degrees);
    }

    @Override
    public IMatrix3F rotateYAround(int x, int y, int z, float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03 - x;
        float m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23 - z;
        return new Matrix3F(
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
    public IMatrix3F rotateYAround(float x, float y, float z, float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03 - x;
        float m2v20 = this.m20, m2v21 = this.m21, m2v22 = this.m22, m2v23 = this.m23 - z;
        return new Matrix3F(
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
    public IMatrix3F rotateYAround(double x, double y, double z, float degrees) {
        return this.rotateYAround((float) x, (float) y, (float) z, degrees);
    }

    @Override
    public IMatrix3F rotateYAround(IVec3IC point, float degrees) {
        return this.rotateYAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3F rotateYAround(IVec3DC point, float degrees) {
        return this.rotateYAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3F rotateYAround(IVec2IC vec, int z, float degrees) {
        return this.rotateYAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3F rotateYAround(IVec2IC vec, double z, float degrees) {
        return this.rotateYAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3F rotateYAround(IVec2DC vec, int z, float degrees) {
        return this.rotateYAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3F rotateYAround(IVec2DC vec, double z, float degrees) {
        return this.rotateYAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3F rotateYAround(IVec2IC vec, float degrees) {
        return this.rotateYAround(vec.x(), vec.y(), 0, degrees);
    }

    @Override
    public IMatrix3F rotateYAround(IVec2DC vec, float degrees) {
        return this.rotateYAround(vec.x(), vec.y(), 0.0D, degrees);
    }

    @Override
    public IMatrix3F rotateZAround(int x, int y, int z, float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03 - x;
        float m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13 - y;
        return new Matrix3F(
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
    public IMatrix3F rotateZAround(float x, float y, float z, float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m2v00 = this.m00, m2v01 = this.m01, m2v02 = this.m02, m2v03 = this.m03 - x;
        float m2v10 = this.m10, m2v11 = this.m11, m2v12 = this.m12, m2v13 = this.m13 - y;
        return new Matrix3F(
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
    public IMatrix3F rotateZAround(double x, double y, double z, float degrees) {
        return this.rotateZAround((float) x, (float) y, (float) z, degrees);
    }

    @Override
    public IMatrix3F rotateZAround(IVec3IC point, float degrees) {
        return this.rotateZAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3F rotateZAround(IVec3DC point, float degrees) {
        return this.rotateZAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3F rotateZAround(IVec2IC vec, int z, float degrees) {
        return this.rotateZAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3F rotateZAround(IVec2IC vec, double z, float degrees) {
        return this.rotateZAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3F rotateZAround(IVec2DC vec, int z, float degrees) {
        return this.rotateZAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3F rotateZAround(IVec2DC vec, double z, float degrees) {
        return this.rotateZAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3F rotateZAround(IVec2IC vec, float degrees) {
        return this.rotateZAround(vec.x(), vec.y(), 0, degrees);
    }

    @Override
    public IMatrix3F rotateZAround(IVec2DC vec, float degrees) {
        return this.rotateZAround(vec.x(), vec.y(), 0.0D, degrees);
    }

    @Override
    public IMatrix3F rotateAround(IVec3DC point, float degrees, int x, int y, int z) {
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
        return new Matrix3F(
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
    public IMatrix3F rotateAround(IVec3DC point, float degrees, float x, float y, float z) {
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
        return new Matrix3F(
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
    public IMatrix3F rotateAround(IVec3DC point, float degrees, double x, double y, double z) {
        return this.rotateAround(point, degrees, (float) x, (float) y, (float) z);
    }

    @Override
    public IMatrix3F rotateAround(IVec3DC point, float degrees, IVec3DC vec) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3F rotateAround(IVec3DC point, float degrees, IVec3IC vec) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3F rotateAround(IVec3DC point, float degrees, IVec2IC vec, int z) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F rotateAround(IVec3DC point, float degrees, IVec2IC vec, double z) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F rotateAround(IVec3DC point, float degrees, IVec2DC vec, int z) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F rotateAround(IVec3DC point, float degrees, IVec2DC vec, double z) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F rotateAround(IVec3DC point, float degrees, IVec2IC vec) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3F rotateAround(IVec3DC point, float degrees, IVec2DC vec) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), 0.0D);
    }

    @Override
    public IMatrix3F rotateAround(IVec3IC point, float degrees, int x, int y, int z) {
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
        return new Matrix3F(
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
    public IMatrix3F rotateAround(IVec3IC point, float degrees, float x, float y, float z) {
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
        return new Matrix3F(
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
    public IMatrix3F rotateAround(IVec3IC point, float degrees, double x, double y, double z) {
        return this.rotateAround(point, degrees, (float) x, (float) y, (float) z);
    }

    @Override
    public IMatrix3F rotateAround(IVec3IC point, float degrees, IVec3DC vec) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3F rotateAround(IVec3IC point, float degrees, IVec3IC vec) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3F rotateAround(IVec3IC point, float degrees, IVec2IC vec, int z) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F rotateAround(IVec3IC point, float degrees, IVec2IC vec, double z) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F rotateAround(IVec3IC point, float degrees, IVec2DC vec, int z) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F rotateAround(IVec3IC point, float degrees, IVec2DC vec, double z) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F rotateAround(IVec3IC point, float degrees, IVec2IC vec) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3F rotateAround(IVec3IC point, float degrees, IVec2DC vec) {
        return this.rotateAround(point, degrees, vec.x(), vec.y(), 0.0D);
    }

    @Override
    public IMatrix3F localScale(int val) {
        return new Matrix3F(
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
    public IMatrix3F localScale(float val) {
        return new Matrix3F(
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
    public IMatrix3F localScale(double val) {
        return this.localScale((float) val);
    }

    @Override
    public IMatrix3F localScale(int x, int y, int z) {
        return new Matrix3F(
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
    public IMatrix3F localScale(float x, float y, float z) {
        return new Matrix3F(
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
    public IMatrix3F localScale(double x, double y, double z) {
        return this.localScale((float) x, (float) y, (float) z);
    }

    @Override
    public IMatrix3F localScale(IVec3DC vec) {
        return this.localScale(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3F localScale(IVec3IC vec) {
        return this.localScale(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3F localScale(IVec2IC vec, int z) {
        return this.localScale(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F localScale(IVec2IC vec, double z) {
        return this.localScale(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F localScale(IVec2DC vec, int z) {
        return this.localScale(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F localScale(IVec2DC vec, double z) {
        return this.localScale(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F localScale(IVec2IC vec) {
        return this.localScale(vec.x(), vec.y(), 1);
    }

    @Override
    public IMatrix3F localScale(IVec2DC vec) {
        return this.localScale(vec.x(), vec.y(), 1.0D);
    }

    @Override
    public IMatrix3F localTranslate(int x, int y, int z) {
        float m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        float m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        float m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;
        return new Matrix3F(
            m1v00, m1v01, m1v02,
            (m1v00 * x) + (m1v01 * y) + (m1v02 * z) + (this.m03),

            m1v10, m1v11, m1v12,
            (m1v10 * x) + (m1v11 * y) + (m1v12 * z) + (this.m13),

            m1v20, m1v21, m1v22,
            (m1v20 * x) + (m1v21 * y) + (m1v22 * z) + (this.m23)
        );
    }

    @Override
    public IMatrix3F localTranslate(float x, float y, float z) {
        float m1v00 = this.m00, m1v01 = this.m01, m1v02 = this.m02;
        float m1v10 = this.m10, m1v11 = this.m11, m1v12 = this.m12;
        float m1v20 = this.m20, m1v21 = this.m21, m1v22 = this.m22;
        return new Matrix3F(
            m1v00, m1v01, m1v02,
            (m1v00 * x) + (m1v01 * y) + (m1v02 * z) + (this.m03),

            m1v10, m1v11, m1v12,
            (m1v10 * x) + (m1v11 * y) + (m1v12 * z) + (this.m13),

            m1v20, m1v21, m1v22,
            (m1v20 * x) + (m1v21 * y) + (m1v22 * z) + (this.m23)
        );
    }

    @Override
    public IMatrix3F localTranslate(double x, double y, double z) {
        return this.localTranslate((float) x, (float) y, (float) z);
    }

    @Override
    public IMatrix3F localTranslate(IVec3DC vec) {
        return this.localTranslate(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3F localTranslate(IVec3IC vec) {
        return this.localTranslate(vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3F localTranslate(IVec2IC vec, int z) {
        return this.localTranslate(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F localTranslate(IVec2IC vec, double z) {
        return this.localTranslate(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F localTranslate(IVec2DC vec, int z) {
        return this.localTranslate(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F localTranslate(IVec2DC vec, double z) {
        return this.localTranslate(vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F localTranslate(IVec2IC vec) {
        return this.localTranslate(vec.x(), vec.y(), 0.0D);
    }

    @Override
    public IMatrix3F localTranslate(IVec2DC vec) {
        return this.localTranslate(vec.x(), vec.y(), 0.0D);
    }

    @Override
    public IMatrix3F localRotateX(float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m1v01 = this.m01, m1v02 = this.m02;
        float m1v11 = this.m11, m1v12 = this.m12;
        float m1v21 = this.m21, m1v22 = this.m22;
        return new Matrix3F(
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
    public IMatrix3F localRotateY(float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m1v00 = this.m00, m1v02 = this.m02;
        float m1v10 = this.m10, m1v12 = this.m12;
        float m1v20 = this.m20, m1v22 = this.m22;
        return new Matrix3F(
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
    public IMatrix3F localRotateZ(float degrees) {
        if (Math.abs(degrees) < BananaMath.FLOAT_EPS) {
            return this;
        }

        float rads = (float) Math.toRadians(degrees);
        float sin = (float) BananaMath.sin(rads);
        float cos = (float) BananaMath.cos(rads);

        float m1v00 = this.m00, m1v01 = this.m01;
        float m1v10 = this.m10, m1v11 = this.m11;
        float m1v20 = this.m20, m1v21 = this.m21;
        return new Matrix3F(
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
    public IMatrix3F localRotate(float degrees, int x, int y, int z) {
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
        return new Matrix3F(
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
    public IMatrix3F localRotate(float degrees, float x, float y, float z) {
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
        return new Matrix3F(
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
    public IMatrix3F localRotate(float degrees, double x, double y, double z) {
        return this.localRotate(degrees, (float) x, (float) y, (float) z);
    }

    @Override
    public IMatrix3F localRotate(float degrees, IVec3DC vec) {
        return this.localRotate(degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3F localRotate(float degrees, IVec3IC vec) {
        return this.localRotate(degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3F localRotate(float degrees, IVec2IC vec, int z) {
        return this.localRotate(degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F localRotate(float degrees, IVec2IC vec, double z) {
        return this.localRotate(degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F localRotate(float degrees, IVec2DC vec, int z) {
        return this.localRotate(degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F localRotate(float degrees, IVec2DC vec, double z) {
        return this.localRotate(degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F localRotate(float degrees, IVec2IC vec) {
        return this.localRotate(degrees, vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3F localRotate(float degrees, IVec2DC vec) {
        return this.localRotate(degrees, vec.x(), vec.y(), 0.0D);
    }

    @Override
    public IMatrix3F localRotateXAround(int x, int y, int z, float degrees) {
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

        return new Matrix3F(
            m1v00, m2v01, m2v02,
            (m1v00 * -x) + (m2v01 * -y) + (m2v02 * -z) + (m2v03),

            m1v10, m2v11, m2v12,
            (m1v10 * -x) + (m2v11 * -y) + (m2v12 * -z) + (m2v13),

            m1v20, m2v21, m2v22,
            (m1v20 * -x) + (m2v21 * -y) + (m2v22 * -z) + (m2v23)
        );
    }

    @Override
    public IMatrix3F localRotateXAround(float x, float y, float z, float degrees) {
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

        return new Matrix3F(
            m1v00, m2v01, m2v02,
            (m1v00 * -x) + (m2v01 * -y) + (m2v02 * -z) + (m2v03),

            m1v10, m2v11, m2v12,
            (m1v10 * -x) + (m2v11 * -y) + (m2v12 * -z) + (m2v13),

            m1v20, m2v21, m2v22,
            (m1v20 * -x) + (m2v21 * -y) + (m2v22 * -z) + (m2v23)
        );
    }

    @Override
    public IMatrix3F localRotateXAround(double x, double y, double z, float degrees) {
        return this.localRotateXAround((float) x, (float) y, (float) z, degrees);
    }

    @Override
    public IMatrix3F localRotateXAround(IVec3IC point, float degrees) {
        return this.localRotateXAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3F localRotateXAround(IVec3DC point, float degrees) {
        return this.localRotateXAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3F localRotateXAround(IVec2IC vec, int z, float degrees) {
        return this.localRotateXAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3F localRotateXAround(IVec2IC vec, double z, float degrees) {
        return this.localRotateXAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3F localRotateXAround(IVec2DC vec, int z, float degrees) {
        return this.localRotateXAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3F localRotateXAround(IVec2DC vec, double z, float degrees) {
        return this.localRotateXAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3F localRotateXAround(IVec2IC vec, float degrees) {
        return this.localRotateXAround(vec.x(), vec.y(), 0, degrees);
    }

    @Override
    public IMatrix3F localRotateXAround(IVec2DC vec, float degrees) {
        return this.localRotateXAround(vec.x(), vec.y(), 0.0D, degrees);
    }

    @Override
    public IMatrix3F localRotateYAround(int x, int y, int z, float degrees) {
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

        return new Matrix3F(
            m2v00, m1v01, m2v02,
            (m2v00 * -x) + (m1v01 * -y) + (m2v02 * -z) + (m2v03),

            m2v10, m1v11, m2v12,
            (m2v10 * -x) + (m1v11 * -y) + (m2v12 * -z) + (m2v13),

            m2v20, m1v21, m2v22,
            (m2v20 * -x) + (m1v21 * -y) + (m2v22 * -z) + (m2v23)
        );
    }

    @Override
    public IMatrix3F localRotateYAround(float x, float y, float z, float degrees) {
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

        return new Matrix3F(
                m2v00, m1v01, m2v02,
                (m2v00 * -x) + (m1v01 * -y) + (m2v02 * -z) + (m2v03),

                m2v10, m1v11, m2v12,
                (m2v10 * -x) + (m1v11 * -y) + (m2v12 * -z) + (m2v13),

                m2v20, m1v21, m2v22,
                (m2v20 * -x) + (m1v21 * -y) + (m2v22 * -z) + (m2v23)
        );
    }

    @Override
    public IMatrix3F localRotateYAround(double x, double y, double z, float degrees) {
        return this.localRotateYAround((float) x, (float) y, (float) z, degrees);
    }

    @Override
    public IMatrix3F localRotateYAround(IVec3IC point, float degrees) {
        return this.localRotateYAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3F localRotateYAround(IVec3DC point, float degrees) {
        return this.localRotateYAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3F localRotateYAround(IVec2IC vec, int z, float degrees) {
        return this.localRotateYAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3F localRotateYAround(IVec2IC vec, double z, float degrees) {
        return this.localRotateYAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3F localRotateYAround(IVec2DC vec, int z, float degrees) {
        return this.localRotateYAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3F localRotateYAround(IVec2DC vec, double z, float degrees) {
        return this.localRotateYAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3F localRotateYAround(IVec2IC vec, float degrees) {
        return this.localRotateYAround(vec.x(), vec.y(), 0, degrees);
    }

    @Override
    public IMatrix3F localRotateYAround(IVec2DC vec, float degrees) {
        return this.localRotateYAround(vec.x(), vec.y(), 0.0D, degrees);
    }

    @Override
    public IMatrix3F localRotateZAround(int x, int y, int z, float degrees) {
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

        return new Matrix3F(
            m2v00, m2v01, m1v02,
            (m2v00 * -x) + (m2v01 * -y) + (m1v02 * -z) + (m2v03),

            m2v10, m2v11, m1v12,
            (m2v10 * -x) + (m2v11 * -y) + (m1v12 * -z) + (m2v13),

            m2v20, m2v21, m1v22,
            (m2v20 * -x) + (m2v21 * -y) + (m1v22 * -z) + (m2v23)
        );
    }

    @Override
    public IMatrix3F localRotateZAround(float x, float y, float z, float degrees) {
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

        return new Matrix3F(
            m2v00, m2v01, m1v02,
            (m2v00 * -x) + (m2v01 * -y) + (m1v02 * -z) + (m2v03),

            m2v10, m2v11, m1v12,
            (m2v10 * -x) + (m2v11 * -y) + (m1v12 * -z) + (m2v13),

            m2v20, m2v21, m1v22,
            (m2v20 * -x) + (m2v21 * -y) + (m1v22 * -z) + (m2v23)
        );
    }

    @Override
    public IMatrix3F localRotateZAround(double x, double y, double z, float degrees) {
        return this.localRotateZAround((float) x, (float) y, (float) z, degrees);
    }

    @Override
    public IMatrix3F localRotateZAround(IVec3IC point, float degrees) {
        return this.localRotateZAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3F localRotateZAround(IVec3DC point, float degrees) {
        return this.localRotateZAround(point.x(), point.y(), point.z(), degrees);
    }

    @Override
    public IMatrix3F localRotateZAround(IVec2IC vec, int z, float degrees) {
        return this.localRotateZAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3F localRotateZAround(IVec2IC vec, double z, float degrees) {
        return this.localRotateZAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3F localRotateZAround(IVec2DC vec, int z, float degrees) {
        return this.localRotateZAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3F localRotateZAround(IVec2DC vec, double z, float degrees) {
        return this.localRotateZAround(vec.x(), vec.y(), z, degrees);
    }

    @Override
    public IMatrix3F localRotateZAround(IVec2IC vec, float degrees) {
        return this.localRotateZAround(vec.x(), vec.y(), 0, degrees);
    }

    @Override
    public IMatrix3F localRotateZAround(IVec2DC vec, float degrees) {
        return this.localRotateZAround(vec.x(), vec.y(), 0.0D, degrees);
    }

    @Override
    public IMatrix3F localRotateAround(IVec3DC point, float degrees, int x, int y, int z) {
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
        return new Matrix3F(
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
    public IMatrix3F localRotateAround(IVec3DC point, float degrees, float x, float y, float z) {
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
        return new Matrix3F(
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
    public IMatrix3F localRotateAround(IVec3DC point, float degrees, double x, double y, double z) {
        return this.localRotateAround(point, degrees, (float) x, (float) y, (float) z);
    }

    @Override
    public IMatrix3F localRotateAround(IVec3DC point, float degrees, IVec3DC vec) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3F localRotateAround(IVec3DC point, float degrees, IVec3IC vec) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3F localRotateAround(IVec3DC point, float degrees, IVec2IC vec, int z) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F localRotateAround(IVec3DC point, float degrees, IVec2IC vec, double z) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F localRotateAround(IVec3DC point, float degrees, IVec2DC vec, int z) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F localRotateAround(IVec3DC point, float degrees, IVec2DC vec, double z) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F localRotateAround(IVec3DC point, float degrees, IVec2IC vec) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3F localRotateAround(IVec3DC point, float degrees, IVec2DC vec) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), 0.0D);
    }

    @Override
    public IMatrix3F localRotateAround(IVec3IC point, float degrees, int x, int y, int z) {
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
        return new Matrix3F(
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
    public IMatrix3F localRotateAround(IVec3IC point, float degrees, float x, float y, float z) {
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
        return new Matrix3F(
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
    public IMatrix3F localRotateAround(IVec3IC point, float degrees, double x, double y, double z) {
        return this.localRotateAround(point, degrees, (float) x, (float) y, (float) z);
    }

    @Override
    public IMatrix3F localRotateAround(IVec3IC point, float degrees, IVec3DC vec) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3F localRotateAround(IVec3IC point, float degrees, IVec3IC vec) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), vec.z());
    }

    @Override
    public IMatrix3F localRotateAround(IVec3IC point, float degrees, IVec2IC vec, int z) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F localRotateAround(IVec3IC point, float degrees, IVec2IC vec, double z) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F localRotateAround(IVec3IC point, float degrees, IVec2DC vec, int z) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F localRotateAround(IVec3IC point, float degrees, IVec2DC vec, double z) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), z);
    }

    @Override
    public IMatrix3F localRotateAround(IVec3IC point, float degrees, IVec2IC vec) {
        return this.localRotateAround(point, degrees, vec.x(), vec.y(), 0);
    }

    @Override
    public IMatrix3F localRotateAround(IVec3IC point, float degrees, IVec2DC vec) {
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
        return null;//new Matrix3DM(this);
    }

    @Override
    public IMatrix3F toImmutable() {
        return this;
    }

    @Override
    public IMatrix3D toDouble() {
        return new Matrix3D(this);
    }

    @Override
    public IMatrix3F copy() {
        return new Matrix3F(this);
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
        return this.hash;
    }
}
