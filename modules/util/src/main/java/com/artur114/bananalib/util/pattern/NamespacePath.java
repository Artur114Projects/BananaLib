package com.artur114.bananalib.util.pattern;


import java.util.*;

public class NamespacePath {
    private static final Part SHAPELESS_L = new Part("*");
    private static final Part EMPTY_L = new Part("");

    public static final NamespacePath EMPTY = of("", "");
    public static final NamespacePath ABSOLUTE = of("*:*");

    public static NamespacePath of(String space) {
        String[] strings = space.split(":", 2);
        if (strings.length < 2) {
            throw new IllegalArgumentException("Illegal space string: " + space);
        }
        return of(strings[0], strings[1]);
    }

    public static List<NamespacePath> of(String[] spaces) {
        return of(Arrays.asList(spaces));
    }

    public static List<NamespacePath> of(Iterable<String> spaces) {
        List<NamespacePath> list = new ArrayList<>();
        for (String loc : spaces) {
            if (loc == null || loc.isEmpty()) {
                continue;
            }
            NamespacePath space = NamespacePath.of(loc);

            if (!space.isEmpty()) {
                list.add(space);
            }
        }
        return list;
    }

    public static NamespacePath of(String domain, String path) {
        return new NamespacePath(domain, path);
    }

    private static Part createLocation(String location) {
        switch (location) {
            case "":
                return EMPTY_L;
            case "*":
                return SHAPELESS_L;
        }

        return new Part(location);
    }

    private final Part domain;
    private final Part path;

    public NamespacePath(String space) {
        String[] strings = space.split(":", 2);
        if (strings.length < 2) {
            throw new IllegalArgumentException("Illegal space string: " + space);
        }
        this.domain = createLocation(strings[0]);
        this.path = createLocation(strings[1]);
    }

    public NamespacePath(String domain, String path) {
        this.domain = createLocation(domain);
        this.path = createLocation(path);
    }

    public boolean isAbsoluteShapeless() {
        return this.domain.isShapeless() && this.path.isShapeless();
    }

    public boolean isShapeless() {
        return this.domain.isShapeless() || this.path.isShapeless();
    }

    public boolean isEmpty() {
        return this.domain.isEmpty() || this.path.isEmpty();
    }

    public Part pathLocation() {
        return this.path;
    }

    public Part domainLocation() {
        return this.domain;
    }

    public String path() {
        return this.path.location;
    }

    public String domain() {
        return this.domain.location;
    }

    public boolean matches(NamespacePath location) {
        return this.domain.matches(location.domain) && this.path.matches(location.path);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NamespacePath) {
            return ((NamespacePath) obj).domain.equals(this.domain) && ((NamespacePath) obj).path.equals(this.path);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * this.domain.hash + this.path.hash;
    }

    @Override
    public String toString() {
        return this.domain + ":" + this.path;
    }

    public static class Part {
        protected final String location;
        private final int hash;

        private Part(String location) {
            Objects.requireNonNull(location);

            location = location.toLowerCase();

            this.location = location;
            this.hash = location.hashCode();
        }

        public boolean isShapeless() {
            return this == SHAPELESS_L;
        }

        public boolean isEmpty() {
            return this == EMPTY_L;
        }

        public boolean matches(Part part) {
            if (this.isShapeless() || part.isShapeless()) {
                return true;
            } else {
                return this.location.equals(part.location);
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Part) {
                return this.location.equals(((Part) obj).location);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return this.hash;
        }

        @Override
        public String toString() {
            return this.location;
        }
    }
}
