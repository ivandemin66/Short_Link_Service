import java.time.LocalDateTime;

public class Config {
    private static final int MAX_CLICKS = 100;
    private static final int MAX_EXPIRY_MINUTES = 10000;

    public static int getMaxClicks() {
        return MAX_CLICKS;
    }

    public static LocalDateTime getMaxExpiryTime() {
        return LocalDateTime.now().plusMinutes(MAX_EXPIRY_MINUTES);
    }
}
