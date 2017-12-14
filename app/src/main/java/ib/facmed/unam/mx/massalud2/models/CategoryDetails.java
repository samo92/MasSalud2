package ib.facmed.unam.mx.massalud2.models;

/**
 * Created by samo92 on 30/11/2017.
 */

public class CategoryDetails {

    String name;
    String image;
    String id;

    public CategoryDetails(String name, String image, String id) {
        this.name = name;
        this.image = image;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(String id) {
        this.id = id;
    }
}
