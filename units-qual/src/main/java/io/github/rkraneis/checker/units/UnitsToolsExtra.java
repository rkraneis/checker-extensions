
package io.github.rkraneis.checker.units;

import io.github.rkraneis.checker.units.qual.Hz;
import io.github.rkraneis.checker.units.qual.N;
import io.github.rkraneis.checker.units.qual.Ns;
import io.github.rkraneis.checker.units.qual.kHz;
import org.checkerframework.framework.qual.AnnotatedFor;

/** Utility methods to generate annotated types and to convert between them. */
@SuppressWarnings({"units", "checkstyle:constantname"})
@AnnotatedFor("nullness")
public class UnitsToolsExtra {
    // Frequency
    public static final @Hz int Hz = 1;
    public static final @kHz int kHz = 1;
    
    public static @Hz int fromKiloHertzToHertz(@kHz int kHz) {
        return kHz * 1000;
    }
    public static @kHz int fromHertzToKiloHertz(@Hz int Hz) {
        return Hz / 1000;
    }
    
    // Force
    public static final @N int N = 1;
    
    // Momentum
    public static final @Ns int Ns = 1;
    
}
