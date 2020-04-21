package nl.elec332.util.implementationmanager.api;

/**
 * Created by Elec332 on 29-3-2020
 *
 * Used for extensions with different implementation types
 */
public enum ImplementationType {

    OTHER(false),
    JAVA_DEFAULT(false),
    JAVA(false),
    JAVA_FAST(false),
    NATIVE(true),
    NATIVE_FAST(true),
    GPU(true),
    GPU_FAST(true);

    ImplementationType(boolean isNative) {
        this.isNative = isNative;
    }

    private final boolean isNative;

    public boolean isNative() {
        return this.isNative;
    }

    public int getSpeed(int relativeSpeed) {
        return ordinal() * 1000 + relativeSpeed;
    }

}
