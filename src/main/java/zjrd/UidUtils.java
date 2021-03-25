package zjrd;

import java.util.UUID;

public class UidUtils {
    public static String generateObjectId() {
        return new ObjectId().toHexString();
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

}
