package io.github.rkraneis.checker.units.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/** Relations among units of frequency. */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface UnitsRelationsExtra {

    /**
     * Returns the FrequencyRelations subclass to use.
     *
     * @return the FrequencyRelations subclass to use
     */
    // Dummy class for units-qual
    Class<?> value();
}
