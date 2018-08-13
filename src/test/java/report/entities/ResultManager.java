package report.entities;

public class ResultManager {
    private static ThreadLocal<TestResult> resultThreadLocal = new ThreadLocal<>();

    public static TestResult getResult() {
        return resultThreadLocal.get();
    }

    public static void setResult(final TestResult result) {
        resultThreadLocal.set(result);
    }
}
