package config;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

    private static final String CONFIG_FOLDER = ".\\src\\test\\java\\config";
    private static final String CONFIG_FILE_EXT = ".properties";

    private static Queue<Properties> envQueue;
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

    public static Properties getProp() {
        if (propTL.get() == null) {
            propTL.set(envQueue.poll());
        }
        return propTL.get();
    }

    public static void returnProp(Properties prop) {
        envQueue.offer(propTL.get());
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("bef");
        loadEnvInfoToQueue();
    }


    @BeforeMethod
    public void beforeMethod() {
    }

    @AfterMethod
    public void afterMethod() {
        returnProp(null);
    }

    @Test
    public void aaa() throws Exception {
        Thread.sleep(5000);
        System.out.println(getProp().getProperty("env"));

    }

    @Test
    public void bbb() throws Exception {
        System.out.println(getProp().getProperty("env"));
    }

    @Test
    public void ccc() throws Exception {
        System.out.println(getProp().getProperty("env"));
    }

    @Test
    public void ddd() throws Exception {
        System.out.println(getProp().getProperty("env"));
    }
}
