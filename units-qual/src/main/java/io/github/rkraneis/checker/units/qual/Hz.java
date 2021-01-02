package io.github.rkraneis.checker.units.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.checker.units.qual.Prefix;
import org.checkerframework.checker.units.qual.UnitsRelations;
import org.checkerframework.framework.qual.SubtypeOf;

/**
 * Hertz (Hz), a unit of frequency.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@SubtypeOf(Frequency.class)
@UnitsRelations(UnitsRelationsExtra.class)
public @interface Hz {
    Prefix value() default Prefix.one;
}
