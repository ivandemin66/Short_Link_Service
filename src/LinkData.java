import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class LinkData {
    private final String originalUrl;
    private int maxClicks;
    //private long expirationTime;  //new
    private LocalDateTime expiryTime;
    private final UUID userUUID;
    private int clickCount;
    long lifetimeInSeconds = 1;

    public LinkData(String originalUrl, int maxClicks, LocalDateTime expiryTime, UUID userUUID) {
        this.originalUrl = originalUrl;
        this.maxClicks = maxClicks;
        this.expiryTime = LocalDateTime.now().plusSeconds(lifetimeInSeconds);
        this.userUUID = userUUID;
        this.clickCount = 1;
    }

    public void setExpiryTime(LocalDateTime newExpiryTime) {
        this.expiryTime = newExpiryTime;
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
