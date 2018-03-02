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
    
    /**
     * Create a anchor pane node. Date is not assigned in the constructor.
     * @param children children of the anchor pane
     */
    
    public CalendarDay(Node... children) {
        super(children);
        this.setOnMouseClicked(e -> System.out.println("This pane's data is " + date));
//        this.setOnMouseClicked(e -> HomeController.CalendarDatePicker.setValue(date));
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
}
