package com.artur114.bananalib.math.m3d.vec;

import com.artur114.bananalib.math.core.m2d.box.IBox2DC;
import com.artur114.bananalib.math.core.m2d.box.IBox2IC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2DC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2IC;
import com.artur114.bananalib.math.core.m3d.box.IBox3DC;
import com.artur114.bananalib.math.core.m3d.box.IBox3IC;
import com.artur114.bananalib.math.core.m3d.vec.IVec3DC;
import com.artur114.bananalib.math.core.m3d.vec.IVec3IC;
import com.artur114.bananalib.math.m2d.vec.IVec2D;
import com.artur114.bananalib.math.m2d.vec.IVec2DM;
import org.jetbrains.annotations.Contract;

public interface IVec3D extends IVec3DC {
    IVec2D xy();
    IVec2D xz();
    IVec2D yx();
    IVec2D yz();
    IVec2D zx();
    IVec2D zy();
    IVec3D zyx();
    IVec3D zxy();
    IVec3D yzx();
    IVec3D xzy();
    IVec3D yxz();
    @Contract("_ -> _")
    IVec2DM xy(IVec2DM out);
    @Contract("_ -> _")
    IVec2DM xz(IVec2DM out);
    @Contract("_ -> _")
    IVec2DM yx(IVec2DM out);
    @Contract("_ -> _")
    IVec2DM yz(IVec2DM out);
    @Contract("_ -> _")
    IVec2DM zx(IVec2DM out);
    @Contract("_ -> _")
    IVec2DM zy(IVec2DM out);
    @Contract("_ -> _")
    IVec3DM zyx(IVec3DM out);
    @Contract("_ -> _")
    IVec3DM zxy(IVec3DM out);
    @Contract("_ -> _")
    IVec3DM yzx(IVec3DM out);
    @Contract("_ -> _")
    IVec3DM xzy(IVec3DM out);
    @Contract("_ -> _")
    IVec3DM yxz(IVec3DM out);
    double length();
    double lengthSq();
    double dot(IVec3IC vec);
    double dot(IVec3DC vec);
    @Contract("_,_,_ -> new")
    IVec3D cross(int x, int y, int z);
    @Contract("_,_,_ -> new")
    IVec3D cross(double x, double y, double z);
    @Contract("_ -> new")
    IVec3D cross(IVec3IC vec);
    @Contract("_ -> new")
    IVec3D cross(IVec3DC vec);
    double distance(int x, int y, int z);
    double distance(double x, double y, double z);
    double distance(IVec3IC vec);
    double distance(IVec3DC vec);
    double distanceSq(int x, int y, int z);
    double distanceSq(double x, double y, double z);
    double distanceSq(IVec3IC vec);
    double distanceSq(IVec3DC vec);
    IVec3D add(int x, int y, int z);
    IVec3D add(double x, double y, double z);
    IVec3D add(IVec3IC vec);
    IVec3D add(IVec3DC vec);
    IVec3D add(IVec2IC vec, int z);
    IVec3D add(IVec2IC vec, double z);
    IVec3D add(IVec2DC vec, int z);
    IVec3D add(IVec2DC vec, double z);
    IVec3D add(IVec2IC vec);
    IVec3D add(IVec2DC vec);
    IVec3D subtract(int x, int y, int z);
    IVec3D subtract(double x, double y, double z);
    IVec3D subtract(IVec3IC vec);
    IVec3D subtract(IVec3DC vec);
    IVec3D subtract(IVec2IC vec, int z);
    IVec3D subtract(IVec2IC vec, double z);
    IVec3D subtract(IVec2DC vec, int z);
    IVec3D subtract(IVec2DC vec, double z);
    IVec3D subtract(IVec2IC vec);
    IVec3D subtract(IVec2DC vec);
    IVec3D scale(int val);
    IVec3D scale(double val);
    IVec3D scale(int x, int y, int z);
    IVec3D scale(double x, double y, double z);
    IVec3D scale(IVec3IC vec);
    IVec3D scale(IVec3DC vec);
    IVec3D scale(IVec2IC vec, int z);
    IVec3D scale(IVec2IC vec, double z);
    IVec3D scale(IVec2DC vec, int z);
    IVec3D scale(IVec2DC vec, double z);
    IVec3D scale(IVec2IC vec);
    IVec3D scale(IVec2DC vec);
    IVec3D divide(int val);
    IVec3D divide(double val);
    IVec3D divide(int x, int y, int z);
    IVec3D divide(double x, double y, double z);
    IVec3D divide(IVec3IC vec);
    IVec3D divide(IVec3DC vec);
    IVec3D divide(IVec2IC vec, int z);
    IVec3D divide(IVec2IC vec, double z);
    IVec3D divide(IVec2DC vec, int z);
    IVec3D divide(IVec2DC vec, double z);
    IVec3D divide(IVec2IC vec);
    IVec3D divide(IVec2DC vec);
    IVec3D rotateX(double degrees);
    IVec3D rotateY(double degrees);
    IVec3D rotateZ(double degrees);
    IVec3D rotateXAround(int x, int y, int z, double degrees);
    IVec3D rotateXAround(double x, double y, double z, double degrees);
    IVec3D rotateXAround(IVec3IC point, double degrees);
    IVec3D rotateXAround(IVec3DC point, double degrees);
    IVec3D rotateYAround(int x, int y, int z, double degrees);
    IVec3D rotateYAround(double x, double y, double z, double degrees);
    IVec3D rotateYAround(IVec3IC point, double degrees);
    IVec3D rotateYAround(IVec3DC point, double degrees);
    IVec3D rotateZAround(int x, int y, int z, double degrees);
    IVec3D rotateZAround(double x, double y, double z, double degrees);
    IVec3D rotateZAround(IVec3IC point, double degrees);
    IVec3D rotateZAround(IVec3DC point, double degrees);
    IVec3D lerp(int x, int y, int z, double delta);
    IVec3D lerp(double x, double y, double z, double delta);
    IVec3D lerp(IVec3IC vec, double delta);
    IVec3D lerp(IVec3DC vec, double delta);
    IVec3D lerp(IVec2IC vec, int z, double delta);
    IVec3D lerp(IVec2IC vec, double z, double delta);
    IVec3D lerp(IVec2DC vec, int z, double delta);
    IVec3D lerp(IVec2DC vec, double z, double delta);
    IVec3D lerp(IVec2IC vec, double delta);
    IVec3D lerp(IVec2DC vec, double delta);
    IVec3D wrap(IBox3IC box);
    IVec3D wrap(IBox3DC box);
    IVec3D wrap(IBox2IC box, int minZ, int maxZ);
    IVec3D wrap(IBox2IC box, double minZ, double maxZ);
    IVec3D wrap(IBox2DC box, int minZ, int maxZ);
    IVec3D wrap(IBox2DC box, double minZ, double maxZ);
    IVec3D wrap(IBox2IC box);
    IVec3D wrap(IBox2DC box);
    IVec3D wrap(int x, int y, int z);
    IVec3D wrap(double x, double y, double z);
    IVec3D wrap(int minX, int minY, int minZ, int maxX, int maxY, int maxZ);
    IVec3D wrap(double minX, double minY, double minZ, double maxX, double maxY, double maxZ);
    IVec3D clamp(IBox3IC box);
    IVec3D clamp(IBox3DC box);
    IVec3D clamp(IBox2IC box, int minZ, int maxZ);
    IVec3D clamp(IBox2IC box, double minZ, double maxZ);
    IVec3D clamp(IBox2DC box, int minZ, int maxZ);
    IVec3D clamp(IBox2DC box, double minZ, double maxZ);
    IVec3D clamp(IBox2IC box);
    IVec3D clamp(IBox2DC box);
    IVec3D clamp(int x, int y, int z);
    IVec3D clamp(double x, double y, double z);
    IVec3D clamp(int minX, int minY, int minZ, int maxX, int maxY, int maxZ);
    IVec3D clamp(double minX, double minY, double minZ, double maxX, double maxY, double maxZ);
    @Contract("-> new")
    IVec3D normalize();
    @Contract("-> new")
    IVec3DM toMutable();
    @Contract("-> this")
    IVec3D toImmutable();
    @Contract("-> new")
    IVec3I floor();
    @Contract("-> new")
    IVec3I round();
    @Contract("-> new")
    IVec3I ceil();
    @Contract("-> new")
    IVec3D copy();
}
