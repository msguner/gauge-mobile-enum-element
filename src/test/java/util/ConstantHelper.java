package util;

import constants.Constant;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

public class ConstantHelper {

    public static List<Class<?>> constantsClasses = null;
    private static Logger log = Logger.getLogger(ConstantHelper.class);

    /**
     * Get constant classes from constants package with reflection and set variable
     */
    public static void getAndSetConstantsClasses() {
        constantsClasses = ReflectionHelper.findClassesImpmenenting(Constant.class, Package.getPackage("constants"));
        Assert.assertNotNull("Constants classes are not found.", ConstantHelper.constantsClasses);

//       log.info("Get and set constants classes : ");
//        constantsClasses.forEach(t -> log.info("* " + t.toString()));
    }

    /**
     * Get Constant (Enum type) with string enum name from constants classes
     *
     * @param enumName
     * @return Constant
     */
    public static Constant getEnumWithName(String enumName) {
//        constantsClasses.forEach(c -> {
//            if (isExistEnum((Class) c, enumName))
//                return (Constant) Enum.valueOf(c, enumName);
//        });

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
     * @param <T>       enumClass
     * @param enumClass
     * @param enumName
     * @return
     */
    private static <T extends Enum<T>> boolean isExistEnum(Class<T> enumClass, String enumName) {
        Object[] enums = getEnumsFromEnumClass(enumClass);
        return Arrays.stream(enums).anyMatch(e -> e.toString().equals(enumName));
    }

    /**
     * Get all enums in enumClass
     *
     * @param <T>       enumClass
     * @param enumClass
     * @return
     */
    private static <T extends Enum<T>> Object[] getEnumsFromEnumClass(Class<T> enumClass) {
        return enumClass.getEnumConstants();
    }

    //---- TEST
    public static void main(String[] args) {
        //constant klasoru ıcerısındekı tum enum classları alınır
        getAndSetConstantsClasses();

//        Object[] enums = getEnumsFromEnumClass(constantsClasses.get(1));
//        Arrays.stream(enums).forEach(e -> log.info("* " + e));

        System.out.println("isexist : " + isExistEnum((Class) constantsClasses.get(1), "USERNAME"));
    }
}
