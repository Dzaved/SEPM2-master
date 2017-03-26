package sample.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by David on 15-Mar-17.
 */
public class Box {

    private static final Logger LOGGER = LoggerFactory.getLogger(Box.class);

    private int id;
    private Long price;
    private Long boxSize;
    private Long food_quantity;
    private Boolean has_windows;
    private Boolean is_outside;
    private String image;
    private Boolean deleted;

    public Box() {
    }

    public Box(Long price, Long boxSize, Long food_quantity, Boolean has_windows, Boolean is_outside, String image, Boolean deleted) {
        //this.id = id;
        this.price = price;
        this.boxSize = boxSize;
        this.food_quantity = food_quantity;
        this.has_windows = has_windows;
        this.is_outside = is_outside;
        this.image = image;
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getBoxSize() {
        return boxSize;
    }

    public void setBoxSize(Long boxSize) {
        this.boxSize = boxSize;
    }

    public Long getFood_quantity() {
        return food_quantity;
    }

    public void setFood_quantity(Long food_quantity) {
        this.food_quantity = food_quantity;
    }

    public Boolean isHas_windows() {
        return has_windows;
    }

    public void setHas_windows(Boolean with_windows) {this.has_windows = with_windows;}

    public Boolean isIs_outside() {
        return is_outside;
    }

    public void setIs_outside(Boolean is_outside) {
        this.is_outside = is_outside;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}