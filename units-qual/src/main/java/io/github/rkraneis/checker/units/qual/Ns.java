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
 * Newton seconds (Ns), a unit of momentum.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@SubtypeOf(Force.class)
@UnitsRelations(UnitsRelationsExtra.class)
public @interface Ns {
    Prefix value() default Prefix.one;
}
