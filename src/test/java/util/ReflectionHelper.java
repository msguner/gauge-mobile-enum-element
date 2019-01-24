package util;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ReflectionHelper {

    private static Logger LOGGER = Logger.getLogger(ReflectionHelper.class);

    //    @SuppressWarnings("unchecked")
    public static List<Class<?>> findClassesImpmenenting(final Class<?> interfaceClass, final Package fromPackage) {

        if (interfaceClass == null) {
            LOGGER.info("Unknown subclass.");
            return null;
        }

        if (fromPackage == null) {
            LOGGER.info("Unknown package.");
            return null;
        }

        final List<Class<?>> rVal = new ArrayList<Class<?>>();
        try {
            final Class<?>[] targets = getAllClassesFromPackage(fromPackage.getName());
            if (targets != null) {
                for (Class<?> aTarget : targets) {
                    if (aTarget == null) {
                        continue;
                    } else if (aTarget.equals(interfaceClass)) {
                        LOGGER.info("Found the interface definition.");
                        continue;
                    } else if (!interfaceClass.isAssignableFrom(aTarget)) {
                        LOGGER.info("Class '" + aTarget.getName() + "' is not a " + interfaceClass.getName());
                        continue;
                    } else {
                        rVal.add(aTarget);
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            LOGGER.info("Error reading package name.");
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.info("Error reading classes in package.");
            e.printStackTrace();
        }

        return rVal;
    }

    /**
     * Load all classes from a package.
     *
     * @param packageName
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static Class[] getAllClassesFromPackage(final String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }

    /**
     * Find file in package.
     *
     * @param directory
     * @param packageName
     * @return
     * @throws ClassNotFoundException
     */
    public static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}