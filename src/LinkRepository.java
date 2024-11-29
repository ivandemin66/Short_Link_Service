public interface LinkRepository {
    void saveLink(String shortLink, LinkData linkData);

    LinkData findLink(String shortLink);
    void deleteLink(String shortLink);
    void cleanExpiredLinks();
}
