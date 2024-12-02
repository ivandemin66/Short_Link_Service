import java.time.LocalDateTime;
import java.util.UUID;

public class LinkData {
    private final String originalUrl;
    private int maxClicks;
    private final LocalDateTime expiryTime;
    private final UUID userUUID;
    private int clickCount;

    public LinkData(String originalUrl, int maxClicks, LocalDateTime expiryTime, UUID userUUID) {
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

    public boolean isMaxClicksReached () {
        return clickCount >= maxClicks;
    }

    public void incrementClickCount () {
        clickCount++;
    }

    public UUID getUserUUID() {
        return userUUID;
    }

    public void setMaxClicks(int newClicks) {
        this.maxClicks = newClicks;
    }
}
