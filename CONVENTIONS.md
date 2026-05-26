## Conventions
Basic principles that all libraries should follow
### Mutable

- Mutable implementations mutate current object unless otherwise specified.
- Mutable implementations return this object unless otherwise specified
- All mutable objects must have a state stack and a pool

### Rotations

- Must rotate clockwise when looking at the -axis rotation

### Matrix mul

- Unless otherwise specified in the method name, [move * this] should be multiplied
- Matrices must have a mulPost method that multiplies in reverse order, i.e. [this * move]
- Matrices should preferably implement local(method_name) methods that will multiply the matrix in reverse order, i.e. [this * move]

### Bounds

- [wrap] uses inclusive min and exclusive max
- [clamp] uses inclusive min/max
- [contains] uses inclusive min/max
- [intersects] uses inclusive min/max

### Equality

- [equals] performs exact comparison

### Multi Thread

- Mutable objects are not required to be thread-safe.
- Pools must be local to threads