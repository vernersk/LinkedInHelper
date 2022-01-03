package lv.groupOne.linkedinhelper.data.model;

public class ParseItemModel {
    String title,role,location,work,imageLink;

    public ParseItemModel() {
    }

    public ParseItemModel(String title, String role, String location, String work, String imageLink) {
        this.title = title;
        this.role = role;
        this.location = location;
        this.work = work;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
