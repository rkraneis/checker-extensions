package io.github.rkraneis.checker.units.qual;

import java.lang.annotation.Annotation;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.util.Elements;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.units.UnitsRelations;
import static org.checkerframework.checker.units.UnitsRelationsTools.buildAnnoMirrorWithDefaultPrefix;
import static org.checkerframework.checker.units.UnitsRelationsTools.buildAnnoMirrorWithSpecificPrefix;
import static org.checkerframework.checker.units.UnitsRelationsTools.hasNoUnits;
import static org.checkerframework.checker.units.UnitsRelationsTools.hasSpecificUnit;
import static org.checkerframework.checker.units.qual.Prefix.kilo;
import static org.checkerframework.checker.units.qual.Prefix.milli;
import org.checkerframework.checker.units.qual.g;
import org.checkerframework.checker.units.qual.mPERs;
import org.checkerframework.checker.units.qual.mPERs2;
import org.checkerframework.checker.units.qual.s;
import org.checkerframework.framework.type.AnnotatedTypeMirror;
import org.checkerframework.javacutil.AnnotationBuilder;

/** Relations among additional units */
public class UnitsRelationsExtra implements UnitsRelations {

    protected AnnotationMirror Hz, kHz, s, ms;
    protected AnnotationMirror N, Ns, kg, g, mPERs, mPERs2;
    protected Elements elements;

    @Override
    public UnitsRelations init(ProcessingEnvironment env) {

        elements = env.getElementUtils();

        // create Annotation Mirrors, each representing a particular Unit's Annotation
        // frequency
        Hz = buildAnnoMirrorWithDefaultPrefix(env, Hz.class);
        kHz = buildAnnoMirrorWithSpecificPrefix(env, Hz.class, kilo);
        s = buildAnnoMirrorWithDefaultPrefix(env, s.class);
        ms = buildAnnoMirrorWithSpecificPrefix(env, s.class, milli);

        // force N = kg m / s²
        N = buildAnnoMirrorWithNoPrefix(env, N.class);

        // momentum Ns = kg m / s
        Ns = buildAnnoMirrorWithNoPrefix(env, Ns.class);

        g = buildAnnoMirrorWithDefaultPrefix(env, g.class);
        kg = buildAnnoMirrorWithSpecificPrefix(env, g.class, kilo);

        mPERs = buildAnnoMirrorWithDefaultPrefix(env, mPERs.class);
        mPERs2 = buildAnnoMirrorWithDefaultPrefix(env, mPERs2.class);

        return this;
    }

    @Override
    public @Nullable
    AnnotationMirror multiplication(
            AnnotatedTypeMirror lht, AnnotatedTypeMirror rht) {
        // F = m a
        if (havePairOfUnitsIgnoringOrder(lht, kg, rht, mPERs2)) {
            return N; // N = kg m/s² = m/s² kg
        }
        // p = m v
        if (havePairOfUnitsIgnoringOrder(lht, kg, rht, mPERs)) {
            return Ns; // Ns = kg m/s = m/s kg
        }
        // p = F t
        if (havePairOfUnitsIgnoringOrder(lht, N, rht, s)) {
            return Ns; // Ns = N s = s N
        }
        // No multiplications yield Hertz.
        // return null so the default units relations can process multiplcations of other units
        return null;
    }

    @Override
    public @Nullable
    AnnotationMirror division(AnnotatedTypeMirror lht, AnnotatedTypeMirror rht) {
        if (hasSpecificUnit(lht, N)) {
            // a = F / m
            if (hasSpecificUnit(rht, kg)) {
                return mPERs2; // m/s² = N / kg
            }
            // m = F / a
            if (hasSpecificUnit(rht, mPERs2)) {
                return kg; // kg = N / m/s²
            }
            // f = F / p
            if (hasSpecificUnit(rht, Ns)) {
                return Hz; // Hz = N / Ns
            }
        }
        if (hasSpecificUnit(lht, Ns)) {
            // v = p / m
            if (hasSpecificUnit(rht, kg)) {
                return mPERs; // m/s = Ns / kg
            }
            // m = p / v
            if (hasSpecificUnit(rht, mPERs)) {
                return kg; // kg = Ns / m/s
            }
            // t = p / F
            if (hasSpecificUnit(rht, N)) {
                return s; // s = Ns / N
            }
        }
        if (hasNoUnits(lht)) {
            // f = 1 / t
            if (hasSpecificUnit(rht, ms)) {
                return kHz; // kHz = 1 / ms
            } else if (hasSpecificUnit(rht, s)) {
                return Hz; // Hz = 1 / s
            }
        }

        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Copied from UnitRelationsTools, as it is package-private.
    /**
     * Creates an AnnotationMirror representing a unit defined by annoClass, with no prefix.
     *
     * <p>
     * This interface is intended only for subclasses of UnitsRelations; other clients should use
     * {@link #buildAnnoMirrorWithNoPrefix(ProcessingEnvironment, CharSequence)}.
     *
     * @param env       checker Processing Environment, provided as a parameter in init() of a
     *                  UnitsRelations implementation
     * @param annoClass the Class of an Annotation representing a Unit (eg m.class for meters)
     * @return an AnnotationMirror of the Unit with no prefix, or null if it cannot be constructed
     */
    static @Nullable
    AnnotationMirror buildAnnoMirrorWithNoPrefix(
            final ProcessingEnvironment env, final Class<? extends Annotation> annoClass) {
        return AnnotationBuilder.fromClass(env.getElementUtils(), annoClass);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Copied from UnitRelationsDefault, as it is protected and we cannot inherit from it:
    // error: Invalid @UnitsRelations meta-annotation found in interface 
    // io.github.rkraneis.checker.units.qual.Frequency. 
    // @UnitsRelations value @org.checkerframework.checker.units.qual.UnitsRelations(
    // io.github.rkraneis.checker.units.qual.UnitsRelationsExtra.class) is not a subclass of 
    // org.checkerframework.checker.units.UnitsRelations.
    /**
     * Checks to see if lht has the unit ul and if rht has the unit ur all at the same time.
     *
     * @param lht left hand annotated type
     * @param ul  left hand unit
     * @param rht right hand annotated type
     * @param ur  right hand unit
     * @return true if lht has lu and rht has ru, false otherwise
     */
    protected boolean havePairOfUnits(
            AnnotatedTypeMirror lht,
            AnnotationMirror ul,
            AnnotatedTypeMirror rht,
            AnnotationMirror ur) {
        return hasSpecificUnit(lht, ul) && hasSpecificUnit(rht, ur);
    }

    /**
     * Checks to see if lht and rht have the pair of units u1 and u2 regardless of order.
     *
     * @param lht left hand annotated type
     * @param u1  unit 1
     * @param rht right hand annotated type
     * @param u2  unit 2
     * @return true if lht and rht have the pair of units u1 and u2 regardless of order, false
     *         otherwise
     */
    protected boolean havePairOfUnitsIgnoringOrder(
            AnnotatedTypeMirror lht,
            AnnotationMirror u1,
            AnnotatedTypeMirror rht,
            AnnotationMirror u2) {
        return havePairOfUnits(lht, u1, rht, u2) || havePairOfUnits(lht, u2, rht, u1);
    }
}
