package com.artur114.bananalib.math.m3d.box;

import com.artur114.bananalib.math.core.m2d.box.IBox2DC;
import com.artur114.bananalib.math.core.m2d.box.IBox2IC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2DC;
import com.artur114.bananalib.math.core.m2d.vec.IVec2IC;
import com.artur114.bananalib.math.core.m3d.box.IBox3DC;
import com.artur114.bananalib.math.core.m3d.box.IBox3IC;
import com.artur114.bananalib.math.core.m3d.vec.IVec3DC;
import com.artur114.bananalib.math.core.m3d.vec.IVec3IC;
import org.jetbrains.annotations.Contract;

public interface IBox3DM extends IBox3D {
    IBox3DM set(double[] box);
    IBox3DM set(int minX, int minY, int minZ, int maxX, int maxY, int maxZ);
    IBox3DM set(double minX, double minY, double minZ, double maxX, double maxY, double maxZ);
    IBox3DM set(IVec3DC boxFrom, IVec3DC boxTo);
    IBox3DM set(IVec3IC boxFrom, IVec3IC boxTo);
    IBox3DM set(IBox3DC box);
    IBox3DM set(IBox3IC box);
    IBox3DM set(IBox2IC box, int minZ, int maxZ);
    IBox3DM set(IBox2IC box, double minZ, double maxZ);
    IBox3DM set(IBox2DC box, int minZ, int maxZ);
    IBox3DM set(IBox2DC box, double minZ, double maxZ);
    IBox3DM set(IBox2IC box);
    IBox3DM set(IBox2DC box);
    IBox3DM setZero();
    IBox3DM collapseStack();
    IBox3DM resetStack();
    IBox3DM pushBox();
    IBox3DM popBox();
    IBox3DM grow(double amount);
    IBox3DM grow(double x, double y, double z);
    IBox3DM grow(int amount);
    IBox3DM grow(int x, int y, int z);
    IBox3DM grow(IVec3IC vec);
    IBox3DM grow(IVec3DC vec);
    IBox3DM grow(IVec2IC vec, int z);
    IBox3DM grow(IVec2IC vec, double z);
    IBox3DM grow(IVec2DC vec, int z);
    IBox3DM grow(IVec2DC vec, double z);
    IBox3DM grow(IVec2IC vec);
    IBox3DM grow(IVec2DC vec);
    IBox3DM offset(int x, int y, int z);
    IBox3DM offset(double x, double y, double z);
    IBox3DM offset(IVec3IC vec);
    IBox3DM offset(IVec3DC vec);
    IBox3DM offset(IVec2IC vec, int z);
    IBox3DM offset(IVec2IC vec, double z);
    IBox3DM offset(IVec2DC vec, int z);
    IBox3DM offset(IVec2DC vec, double z);
    IBox3DM offset(IVec2IC vec);
    IBox3DM offset(IVec2DC vec);
    IBox3DM include(int x, int y, int z);
    IBox3DM include(double x, double y, double z);
    IBox3DM include(IVec3IC vec);
    IBox3DM include(IVec3DC vec);
    IBox3DM include(IVec2IC vec, int z);
    IBox3DM include(IVec2IC vec, double z);
    IBox3DM include(IVec2DC vec, int z);
    IBox3DM include(IVec2DC vec, double z);
    IBox3DM include(IVec2IC vec);
    IBox3DM include(IVec2DC vec);
    IBox3DM union(int minX, int minY, int minZ, int maxX, int maxY, int maxZ);
    IBox3DM union(double minX, double minY, double minZ, double maxX, double maxY, double maxZ);
    IBox3DM union(IVec3DC boxFrom, IVec3DC boxTo);
    IBox3DM union(IVec3IC boxFrom, IVec3IC boxTo);
    IBox3DM union(IBox3DC box);
    IBox3DM union(IBox3IC box);
    IBox3DM union(IBox2IC box, int minZ, int maxZ);
    IBox3DM union(IBox2IC box, double minZ, double maxZ);
    IBox3DM union(IBox2DC box, int minZ, int maxZ);
    IBox3DM union(IBox2DC box, double minZ, double maxZ);
    IBox3DM union(IBox2IC box);
    IBox3DM union(IBox2DC box);
    IBox3DM intersection(int minX, int minY, int minZ, int maxX, int maxY, int maxZ);
    IBox3DM intersection(double minX, double minY, double minZ, double maxX, double maxY, double maxZ);
    IBox3DM intersection(IVec3DC boxFrom, IVec3DC boxTo);
    IBox3DM intersection(IVec3IC boxFrom, IVec3IC boxTo);
    IBox3DM intersection(IBox3DC box);
    IBox3DM intersection(IBox3IC box);
    IBox3DM intersection(IBox2IC box, int minZ, int maxZ);
    IBox3DM intersection(IBox2IC box, double minZ, double maxZ);
    IBox3DM intersection(IBox2DC box, int minZ, int maxZ);
    IBox3DM intersection(IBox2DC box, double minZ, double maxZ);
    IBox3DM intersection(IBox2IC box);
    IBox3DM intersection(IBox2DC box);
    @Contract("-> this")
    IBox3DM toMutable();
    @Contract("-> new")
    IBox3D toImmutable();
    @Contract("-> new")
    IBox3IM floor();
    @Contract("-> new")
    IBox3IM round();
    @Contract("-> new")
    IBox3IM ceil();
    @Contract("-> new")
    IBox3DM copy();
}
