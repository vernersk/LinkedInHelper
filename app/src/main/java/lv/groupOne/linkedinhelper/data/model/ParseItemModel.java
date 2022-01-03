package lv.groupOne.linkedinhelper.data.model;

public class ParseItemModel {
    String title,role,metadata,imageLink;

    public ParseItemModel() {
    }

    public ParseItemModel(String title, String role, String metadata, String imageLink) {
        this.title = title;
        this.role = role;
        this.metadata = metadata;
        this.imageLink = imageLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
