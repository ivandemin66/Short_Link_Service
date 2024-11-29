import java.time.LocalDateTime;
import java.util.UUID;

public class LinkData {
    private final String originalUrl;
    private final int maxClicks;
    private final LocalDateTime expiryTime;
    private final UUID userUUID;
    private int clickCount;

    public LinkData(String originalUrl, int maxClicks, LocalDateTime expiryTime, UUID UserUUID, UUID userUUID, int clickCount) {
        this.originalUrl = originalUrl;
        this.maxClicks = maxClicks;
        this.expiryTime = expiryTime;
        this.userUUID = userUUID;
        this.clickCount = 0;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public  boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryTime);
    }

    public boolean IsMaxClicksReached () {
        return clickCount >= maxClicks;
    }

    public void incrementClickCount () {
        clickCount++;
    }

    public UUID getUserUUID() {
        return userUUID;
    }
}
