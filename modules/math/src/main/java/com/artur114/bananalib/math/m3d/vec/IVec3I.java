package com.artur114.bananalib.math.m3d.vec;

import com.artur114.bananalib.math.core.m2d.box.IBox2DC;
import com.artur114.bananalib.math.core.m2d.box.IBox2IC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2DC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2IC;
import com.artur114.bananalib.math.core.m3d.box.IBox3DC;
import com.artur114.bananalib.math.core.m3d.box.IBox3IC;
import com.artur114.bananalib.math.core.m3d.vec.IVec3DC;
import com.artur114.bananalib.math.core.m3d.vec.IVec3IC;
import com.artur114.bananalib.math.m2d.vec.IVec2I;
import com.artur114.bananalib.math.m2d.vec.IVec2IM;
import org.jetbrains.annotations.Contract;

public interface IVec3I extends IVec3IC {
    IVec2I xy();
    IVec2I xz();
    IVec2I yx();
    IVec2I yz();
    IVec2I zx();
    IVec2I zy();
    IVec3I zyx();
    IVec3I zxy();
    IVec3I yzx();
    IVec3I xzy();
    IVec3I yxz();
    @Contract("_ -> _")
    IVec2IM xy(IVec2IM out);
    @Contract("_ -> _")
    IVec2IM xz(IVec2IM out);
    @Contract("_ -> _")
    IVec2IM yx(IVec2IM out);
    @Contract("_ -> _")
    IVec2IM yz(IVec2IM out);
    @Contract("_ -> _")
    IVec2IM zx(IVec2IM out);
    @Contract("_ -> _")
    IVec2IM zy(IVec2IM out);
    @Contract("_ -> _")
    IVec3IM zyx(IVec3IM out);
    @Contract("_ -> _")
    IVec3IM zxy(IVec3IM out);
    @Contract("_ -> _")
    IVec3IM yzx(IVec3IM out);
    @Contract("_ -> _")
    IVec3IM xzy(IVec3IM out);
    @Contract("_ -> _")
    IVec3IM yxz(IVec3IM out);
    float length();
    float lengthSq();
    int dot(IVec3IC vec);
    double dot(IVec3DC vec);
    @Contract("_,_,_ -> new")
    IVec3I cross(int x, int y, int z);
    @Contract("_,_,_ -> new")
    IVec3D cross(double x, double y, double z);
    @Contract("_ -> new")
    IVec3I cross(IVec3IC vec);
    @Contract("_ -> new")
    IVec3D cross(IVec3DC vec);
    float distance(int x, int y, int z);
    double distance(double x, double y, double z);
    float distance(IVec3IC vec);
    double distance(IVec3DC vec);
    float distanceSq(int x, int y, int z);
    double distanceSq(double x, double y, double z);
    float distanceSq(IVec3IC vec);
    double distanceSq(IVec3DC vec);
    IVec3I add(int x, int y, int z);
    IVec3I add(double x, double y, double z);
    IVec3I add(IVec3IC vec);
    IVec3I add(IVec3DC vec);
    IVec3I add(IVec2IC vec, int z);
    IVec3I add(IVec2IC vec, double z);
    IVec3I add(IVec2DC vec, int z);
    IVec3I add(IVec2DC vec, double z);
    IVec3I add(IVec2IC vec);
    IVec3I add(IVec2DC vec);
    IVec3I subtract(int x, int y, int z);
    IVec3I subtract(double x, double y, double z);
    IVec3I subtract(IVec3IC vec);
    IVec3I subtract(IVec3DC vec);
    IVec3I subtract(IVec2IC vec, int z);
    IVec3I subtract(IVec2IC vec, double z);
    IVec3I subtract(IVec2DC vec, int z);
    IVec3I subtract(IVec2DC vec, double z);
    IVec3I subtract(IVec2IC vec);
    IVec3I subtract(IVec2DC vec);
    IVec3I scale(int val);
    IVec3I scale(double val);
    IVec3I scale(int x, int y, int z);
    IVec3I scale(double x, double y, double z);
    IVec3I scale(IVec3IC vec);
    IVec3I scale(IVec3DC vec);
    IVec3I scale(IVec2IC vec, int z);
    IVec3I scale(IVec2IC vec, double z);
    IVec3I scale(IVec2DC vec, int z);
    IVec3I scale(IVec2DC vec, double z);
    IVec3I scale(IVec2IC vec);
    IVec3I scale(IVec2DC vec);
    IVec3I divide(int val);
    IVec3I divide(double val);
    IVec3I divide(int x, int y, int z);
    IVec3I divide(double x, double y, double z);
    IVec3I divide(IVec3IC vec);
    IVec3I divide(IVec3DC vec);
    IVec3I divide(IVec2IC vec, int z);
    IVec3I divide(IVec2IC vec, double z);
    IVec3I divide(IVec2DC vec, int z);
    IVec3I divide(IVec2DC vec, double z);
    IVec3I divide(IVec2IC vec);
    IVec3I divide(IVec2DC vec);
    IVec3I rotateX(double degrees);
    IVec3I rotateY(double degrees);
    IVec3I rotateZ(double degrees);
    IVec3I rotateXAround(int x, int y, int z, double degrees);
    IVec3I rotateXAround(double x, double y, double z, double degrees);
    IVec3I rotateXAround(IVec3IC point, double degrees);
    IVec3I rotateXAround(IVec3DC point, double degrees);
    IVec3I rotateYAround(int x, int y, int z, double degrees);
    IVec3I rotateYAround(double x, double y, double z, double degrees);
    IVec3I rotateYAround(IVec3IC point, double degrees);
    IVec3I rotateYAround(IVec3DC point, double degrees);
    IVec3I rotateZAround(int x, int y, int z, double degrees);
    IVec3I rotateZAround(double x, double y, double z, double degrees);
    IVec3I rotateZAround(IVec3IC point, double degrees);
    IVec3I rotateZAround(IVec3DC point, double degrees);
    IVec3I lerp(int x, int y, int z, double delta);
    IVec3I lerp(double x, double y, double z, double delta);
    IVec3I lerp(IVec3IC vec, double delta);
    IVec3I lerp(IVec3DC vec, double delta);
    IVec3I lerp(IVec2IC vec, int z, double delta);
    IVec3I lerp(IVec2IC vec, double z, double delta);
    IVec3I lerp(IVec2DC vec, int z, double delta);
    IVec3I lerp(IVec2DC vec, double z, double delta);
    IVec3I lerp(IVec2IC vec, double delta);
    IVec3I lerp(IVec2DC vec, double delta);
    IVec3I wrap(IBox3IC box);
    IVec3I wrap(IBox3DC box);
    IVec3I wrap(IBox2IC box, int minZ, int maxZ);
    IVec3I wrap(IBox2IC box, double minZ, double maxZ);
    IVec3I wrap(IBox2DC box, int minZ, int maxZ);
    IVec3I wrap(IBox2DC box, double minZ, double maxZ);
    IVec3I wrap(IBox2IC box);
    IVec3I wrap(IBox2DC box);
    IVec3I wrap(int x, int y, int z);
    IVec3I wrap(double x, double y, double z);
    IVec3I wrap(int minX, int minY, int minZ, int maxX, int maxY, int maxZ);
    IVec3I wrap(double minX, double minY, double minZ, double maxX, double maxY, double maxZ);
    IVec3I clamp(IBox3IC box);
    IVec3I clamp(IBox3DC box);
    IVec3I clamp(IBox2IC box, int minZ, int maxZ);
    IVec3I clamp(IBox2IC box, double minZ, double maxZ);
    IVec3I clamp(IBox2DC box, int minZ, int maxZ);
    IVec3I clamp(IBox2DC box, double minZ, double maxZ);
    IVec3I clamp(IBox2IC box);
    IVec3I clamp(IBox2DC box);
    IVec3I clamp(int x, int y, int z);
    IVec3I clamp(double x, double y, double z);
    IVec3I clamp(int minX, int minY, int minZ, int maxX, int maxY, int maxZ);
    IVec3I clamp(double minX, double minY, double minZ, double maxX, double maxY, double maxZ);
    @Contract("-> new")
    IVec3D normalize();
    @Contract("-> new")
    IVec3IM toMutable();
    @Contract("-> this")
    IVec3I toImmutable();
    @Contract("-> new")
    IVec3D toDouble();
    @Contract("-> new")
    IVec3I copy();
}
