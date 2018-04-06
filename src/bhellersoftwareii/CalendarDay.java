/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import java.time.LocalDate;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Britt
 */
public class CalendarDay extends AnchorPane {
    
    private LocalDate date;
    private int xVal;
    private int yVal;
    
    /**
     * Create a anchor pane node. Date is not assigned in the constructor.
     * @param children children of the anchor pane
     */
    
    public CalendarDay(Node... children) {
        super(children);
        this.setOnMouseClicked(e -> System.out.println("This pane's data is " + date + " it's xValue is " + xVal + " and y value is " + yVal));
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public int getXVal() {
        return xVal;
    }
    
    public void setXVal(int xVal) {
        this.xVal = xVal;
    }
    public int getYVal() {
        return yVal;
    }
    
    public void setYVal(int yVal) {
        this.yVal = yVal;
    }
}
