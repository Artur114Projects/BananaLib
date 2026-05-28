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

public interface IBox3D extends IBox3DC {
    boolean contains(IBox3IC box);
    boolean contains(IBox3DC box);
    boolean contains(IVec3IC vec);
    boolean contains(IVec3DC vec);
    boolean contains(IVec2IC vec, int z);
    boolean contains(IVec2IC vec, double z);
    boolean contains(IVec2DC vec, int z);
    boolean contains(IVec2DC vec, double z);
    boolean contains(IVec2IC vec);
    boolean contains(IVec2DC vec);
    boolean contains(IBox2IC box, int minZ, int maxZ);
    boolean contains(IBox2IC box, double minZ, double maxZ);
    boolean contains(IBox2DC box, int minZ, int maxZ);
    boolean contains(IBox2DC box, double minZ, double maxZ);
    boolean contains(IBox2IC box);
    boolean contains(IBox2DC box);
    boolean intersects(IVec3DC boxFrom, IVec3DC boxTo);
    boolean intersects(IVec3IC boxFrom, IVec3IC boxTo);
    boolean intersects(IBox3DC box);
    boolean intersects(IBox3IC box);
    boolean intersects(IBox2IC box, int minZ, int maxZ);
    boolean intersects(IBox2IC box, double minZ, double maxZ);
    boolean intersects(IBox2DC box, int minZ, int maxZ);
    boolean intersects(IBox2DC box, double minZ, double maxZ);
    boolean intersects(IBox2IC box);
    boolean intersects(IBox2DC box);
    IBox3D grow(double amount);
    IBox3D grow(double x, double y, double z);
    IBox3D grow(int amount);
    IBox3D grow(int x, int y, int z);
    IBox3D grow(IVec3IC vec);
    IBox3D grow(IVec3DC vec);
    IBox3D grow(IVec2IC vec, int z);
    IBox3D grow(IVec2IC vec, double z);
    IBox3D grow(IVec2DC vec, int z);
    IBox3D grow(IVec2DC vec, double z);
    IBox3D grow(IVec2IC vec);
    IBox3D grow(IVec2DC vec);
    IBox3D offset(int x, int y, int z);
    IBox3D offset(double x, double y, double z);
    IBox3D offset(IVec3IC vec);
    IBox3D offset(IVec3DC vec);
    IBox3D offset(IVec2IC vec, int z);
    IBox3D offset(IVec2IC vec, double z);
    IBox3D offset(IVec2DC vec, int z);
    IBox3D offset(IVec2DC vec, double z);
    IBox3D offset(IVec2IC vec);
    IBox3D offset(IVec2DC vec);
    IBox3D include(int x, int y, int z);
    IBox3D include(double x, double y, double z);
    IBox3D include(IVec3IC vec);
    IBox3D include(IVec3DC vec);
    IBox3D include(IVec2IC vec, int z);
    IBox3D include(IVec2IC vec, double z);
    IBox3D include(IVec2DC vec, int z);
    IBox3D include(IVec2DC vec, double z);
    IBox3D include(IVec2IC vec);
    IBox3D include(IVec2DC vec);
    IBox3D union(int minX, int minY, int minZ, int maxX, int maxY, int maxZ);
    IBox3D union(double minX, double minY, double minZ, double maxX, double maxY, double maxZ);
    IBox3D union(IVec3DC boxFrom, IVec3DC boxTo);
    IBox3D union(IVec3IC boxFrom, IVec3IC boxTo);
    IBox3D union(IBox3DC box);
    IBox3D union(IBox3IC box);
    IBox3D union(IBox2IC box, int minZ, int maxZ);
    IBox3D union(IBox2IC box, double minZ, double maxZ);
    IBox3D union(IBox2DC box, int minZ, int maxZ);
    IBox3D union(IBox2DC box, double minZ, double maxZ);
    IBox3D union(IBox2IC box);
    IBox3D union(IBox2DC box);
    IBox3D intersection(int minX, int minY, int minZ, int maxX, int maxY, int maxZ);
    IBox3D intersection(double minX, double minY, double minZ, double maxX, double maxY, double maxZ);
    IBox3D intersection(IVec3DC boxFrom, IVec3DC boxTo);
    IBox3D intersection(IVec3IC boxFrom, IVec3IC boxTo);
    IBox3D intersection(IBox3DC box);
    IBox3D intersection(IBox3IC box);
    IBox3D intersection(IBox2IC box, int minZ, int maxZ);
    IBox3D intersection(IBox2IC box, double minZ, double maxZ);
    IBox3D intersection(IBox2DC box, int minZ, int maxZ);
    IBox3D intersection(IBox2DC box, double minZ, double maxZ);
    IBox3D intersection(IBox2IC box);
    IBox3D intersection(IBox2DC box);
    @Contract("-> new")
    IBox3DM toMutable();
    @Contract("-> this")
    IBox3D toImmutable();
    @Contract("-> new")
    IBox3I floor();
    @Contract("-> new")
    IBox3I round();
    @Contract("-> new")
    IBox3I ceil();
    @Contract("-> new")
    IBox3D copy();
}
