import java.util.UUID;

public class LinkResponce {
private final String shortLink;
private final UUID userUUID;

public LinkResponce(String shortLink, UUID userUUID) {
    this.shortLink = shortLink;
    this.userUUID = userUUID;
}


    public String getShortLink() {
    return shortLink;
    }

    public UUID getUserUUID() {
    return userUUID;
    }
}
