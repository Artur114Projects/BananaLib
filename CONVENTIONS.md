## Conventions
Basic principles that all libraries should follow
### Mutable

- Mutable implementations mutate current object unless otherwise specified.
- Mutable implementations return this object unless otherwise specified
- All mutable objects must have a state stack and a pool
- Immutable zero/identity constants may be singleton instances
- Immutable objects must preserve their runtime type. Methods that conceptually modify an immutable object must return a new instance of the same concrete runtime type as the receiver, unless explicitly documented otherwise.

### Boxes (ABB)

- all Box implementations must monitor the coordinate order themselves (minVec must be smaller than maxVec in all dimensionality coordinates)
- [intersection] if a box is passed that does not intersect with the current one, it returns an empty box (for immutable), becomes an empty box (for mutable)
- rotating AABB boxes through matrices at angles not divisible by 90 degrees may produce non-minimal or invalid bounds.

### Rotations

### Rotations

- Positive rotations follow the right-hand rule.
- Rotation matrices, vector rotations and quaternions must use the same convention.

### Matrix mul

- Unless otherwise specified in the method name, [move * this] should be multiplied
- Matrices must have a mulPost method that multiplies in reverse order, i.e. [this * move]
- Matrices should preferably implement local(method_name) methods that will multiply the matrix in reverse order, i.e. [this * move]

### Matrix

- [invert] Throws an ArithmeticException if the matrix is not invertible

### Bounds

- [wrap] uses inclusive min and exclusive max
- [clamp] uses inclusive min/max
- [contains] uses inclusive min/max
- [intersects] uses inclusive min/max

### Equality

- [equals] performs exact comparison
- [equalsEps] performs eps comparison
- [equals] must be multi-type, i.e. IVecI[n].equals(IVecD[n]) == true if IVecI.xy...() == IVecD.xy...()
- [hashCode] must be multi-type, i.e. IVecI[n].hashCode() == IVecD[n].hashCode() if IVecI.xy...() == IVecD.xy...()

### Multi Thread

- Mutable objects are not required to be thread-safe.
- Pools must be local to threads

### Other

- [NaN] in math objects is undefined behavior
- [-0.0] in math objects may cause undefined behavior
- Methods do not validate arguments unless explicitly specified