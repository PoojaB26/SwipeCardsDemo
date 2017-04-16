package poojab26.swipecardsdemo;

/**
 * Created by pblead26 on 15-Apr-17.
 */

public class DataModel {

    private String description;

    private String imagePath;

    public DataModel(String imagePath, String description) {
        this.imagePath = imagePath;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }

}
