import java.time.LocalDateTime;
import java.util.UUID;
import java.util.*;

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

    public void changeUser(UUID newUserId) {
    }


    public boolean updateLinkParams(String shortLink, String userId, int newClicks, int expiryMinutes) {
        LinkData linkData = repository.findLink(shortLink);
        if (linkData != null && linkData.getUserUUID().toString().equals(userId)) {
            // Устанавливаем максимальное количество кликов
            linkData.setMaxClicks(newClicks);

            // Рассчитываем новое время жизни
            LocalDateTime requestedExpiryTime = LocalDateTime.now().plusMinutes(expiryMinutes);

            // Устанавливаем минимальное значение времени жизни между конфигурацией и пользовательским вводом
            LocalDateTime finalExpiryTime = requestedExpiryTime.isBefore(Config.getMaxExpiryTime())
                    ? requestedExpiryTime
                    : Config.getMaxExpiryTime();
            linkData.setExpiryTime(finalExpiryTime);

            return true;
        }
        return false;
    }



}