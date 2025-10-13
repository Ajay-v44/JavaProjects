package Bank.util;

import java.util.concurrent.ThreadLocalRandom;

public class UniqueNumberGenerator {
    public static String generateUniqueNumber() {
        long timestamp = System.currentTimeMillis();
        int randomComponent = ThreadLocalRandom.current().nextInt(0, 100000);
        return String.valueOf(timestamp * 100000L + randomComponent);
    }
}
