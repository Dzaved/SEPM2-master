package sample.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by David on 22-Mar-17.
 */
public class BoxHorseList {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoxHorseList.class);

    private int[] boxList;
    private String[] horseList;

    public BoxHorseList(){}

    public BoxHorseList(int[] boxList, String[] horseList) {
        this.boxList = boxList;
        this.horseList = horseList;
    }

    public int[] getBoxList() {
        return boxList;
    }

    public void setBoxList(int[] boxList) {
        this.boxList = boxList;
    }

    public String[] getHorseList() {
        return horseList;
    }

    public void setHorseList(String[] horseList) {
        this.horseList = horseList;
    }
}
