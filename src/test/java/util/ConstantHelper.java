package util;

import constants.Constant;
import org.junit.Assert;

import java.util.List;

public class ConstantHelper {

    public static List<Class<?>> constantsClasses = null;

    /**
     * Get Constant (Enum type) with string enum name from constants classes
     *
     * @param enumName
     * @return Constant
     */
    public static Constant getEnumWithName(String enumName) {

        for (Class c : constantsClasses) {
            if (isExistEnum(c, enumName))
                return (Constant) Enum.valueOf(c, enumName);
        }

        Assert.fail(enumName + " enum not found in constants.");

        return null;
    }

    /**
     * Search enum in enumClass with string enum name
     *
     * @param <T>      enumClass
     * @param enumName
     * @return
     */
    public static <T extends Enum<T>> boolean isExistEnum(Class<T> enumClass, String enumName) {
        for (T c : enumClass.getEnumConstants()) {
            if (c.toString().equals(enumName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get constant classes from constants package with reflection and set variable
     */
    public static void getAndSetConstantsClasses(){
        constantsClasses = ReflectionHelper.findClassesImpmenenting(Constant.class, Package.getPackage("constants"));
        Assert.assertNotNull("Constants classes are not found.", ConstantHelper.constantsClasses);
//         constantsClasses.forEach(t -> System.out.println("* " + t.toString()));
    }
}
