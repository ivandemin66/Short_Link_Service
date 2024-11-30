import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

class ShortLinkService {
    private final LinkRepository repository;

    public ShortLinkService(LinkRepository repository) {
        this.repository = repository;
    }

    public String createShortLink(String userId, String originalUrl, int maxClicks) {
        String shortLink = generateShortLink();
        UUID userUUID = UUID.fromString(userId);
        LinkData linkData = new LinkData(originalUrl, maxClicks, LocalDateTime.now().plusDays(1), userUUID);
        repository.saveLink(shortLink, linkData);
        return shortLink;
    }

    public String redirect(String shortLink) throws Exception {
        LinkData linkData = repository.findLink(shortLink);
        if (linkData == null || linkData.isExpired() || linkData.isMaxClicksReached()) {
            throw new Exception("Ссылка недоступна");
        }
        linkData.incrementClickCount();
        return linkData.getOriginalUrl();
    }

    public boolean deleteLink(String shortLink, String userId) {
        LinkData linkData = repository.findLink(shortLink);
        if (linkData != null && linkData.getUserUUID().toString().equals(userId)) {
            repository.deleteLink(shortLink);
            return true;
        }
        return false;
    }

    public void cleanExpiredLinks() {
        repository.cleanExpiredLinks();
    }

    private String generateShortLink() {
        return "clck.ru/" + UUID.randomUUID().toString().substring(0, 6);
    }
}