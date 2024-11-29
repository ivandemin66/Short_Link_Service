import java.util.HashMap;
import java.util.Map;

//Сохранение результата работы программы в оперативной памяти

public class InMemoryLinkRepository implements LinkRepository {
    private final Map<String, LinkData> storage = new HashMap<>();
    @Override
    public void saveLink(String shortLink, LinkData linkData) {
        storage.put(shortLink, linkData);
    }

    @Override
    public LinkData findLink(String shortLink) {
        return storage.get(shortLink);
    }

    @Override
    public void deleteLink(String shortLink) {
        storage.remove(shortLink);
    }

    @Override
    public void cleanExpiredLinks() {
        storage.entrySet().removeIf(entry -> entry.getValue().isExpired());
    }
}
