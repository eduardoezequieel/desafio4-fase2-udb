package models;

public class Material {

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getSongNumber() {
        return songNumber;
    }

    public void setSongNumber(int songNumber) {
        this.songNumber = songNumber;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public int getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(int materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public int getEditorialId() {
        return editorialId;
    }

    public void setEditorialId(int editorialId) {
        this.editorialId = editorialId;
    }
    
    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }
    
    private String code;
    private String title;
    private String description;

    @Override
    public String toString() {
        return "Material{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", duration='" + duration + '\'' +
                ", songNumber=" + songNumber +
                ", publishDate='" + publishDate + '\'' +
                ", materialTypeId=" + materialTypeId +
                ", creatorId=" + creatorId +
                ", editorialId=" + editorialId +
                ", materialTypeName='" + materialTypeName + '\'' +
                '}';
    }

    private String duration;
    private int songNumber;
    private String publishDate;
    private int materialTypeId;
    private int creatorId;
    private int editorialId;
    private String materialTypeName;
}
