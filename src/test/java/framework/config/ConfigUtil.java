package framework.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class ConfigUtil {

    private static final String CONFIG_FOLDER = ".\\src\\test\\java\\framework\\config";
    private static final String CONFIG_FILE_EXT = ".properties";

    public static Queue<Properties> envQueue;
    private static ThreadLocal<Properties> propTL = new ThreadLocal<>();


    public static void loadEnvInfoToQueue() {
        envQueue = new ConcurrentLinkedQueue<>();
        List<File> files = getConfigFiles();
        files.stream().forEach(e -> {
            Properties p = new Properties();
            try {
                p.load(new FileInputStream(e));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            envQueue.add(p);
        });
    }

    private static List<File> getConfigFiles() {
        File folder = new File(CONFIG_FOLDER);
        File[] listOfFiles = folder.listFiles();
        return Arrays.stream(listOfFiles).filter(e -> e.getName().contains(CONFIG_FILE_EXT)).collect(Collectors.toList());
    }

    public static String getProp(String propKey) {
        if (propTL.get() == null) {
            propTL.set(envQueue.poll());
        }
        return propTL.get().getProperty(propKey);
    }

    public static void returnProp() {
        if (propTL != null) {
            System.out.println("return " + propTL.get().getProperty("env"));
            envQueue.offer(propTL.get());
            propTL.remove();
        }
    }
}
