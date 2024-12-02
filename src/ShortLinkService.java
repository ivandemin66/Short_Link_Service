import java.time.LocalDateTime;
import java.util.UUID;

class ShortLinkService {
    private final LinkRepository repository;

    public ShortLinkService(LinkRepository repository) {
        this.repository = repository;
    }

    public LinkResponce createShortLink(String userId, String originalUrl, int maxClicks) {

        UUID userUUID = UUID.fromString(userId);
        String shortLink = generateShortLink();

        LinkData linkData = new LinkData(originalUrl, maxClicks, LocalDateTime.now().plusDays(1), userUUID);
        repository.saveLink(shortLink, linkData);
        return new LinkResponce(shortLink, userUUID);
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

    public void changeUser(String newUserId) {

    }

    public boolean updateLinkParams(String shortLink, String userId, int newClicks) {
        LinkData linkData = repository.findLink(shortLink);
        if (linkData!= null && linkData.getUserUUID().toString().equals(userId)) {
            linkData.setMaxClicks(newClicks);
            return true;
        }
        return false;
    }
}