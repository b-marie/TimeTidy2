/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.scene.Node;
import javafx.scene.layout.Region;

/**
 *
 * @author Britt
 */
public class TimeSlot {
    private LocalDateTime start;
    private Duration duration;
    private Region view;
    private PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("Selected");
    
    private BooleanProperty selected = new SimpleBooleanProperty();
    
    public BooleanProperty selectedProperty() {
        return selected;
    }
    
    public boolean isSelected() {
        return selectedProperty().get();
    }
    
    public void setSelected(boolean selected) {
        selectedProperty().set(selected);
    }
    
    public TimeSlot(LocalDateTime start, Duration duration) {
        this.start = start;
        this.duration = duration;
        
        view = new Region();
        view.setMinSize(80, 20);
        
        selectedProperty().addListener((obs, wasSelected, isSelected) -> 
            view.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, isSelected));
    }
    
    public LocalDateTime getStart() {
        return start;
    }
    
    public LocalTime getTime() {
        return start.toLocalTime();
    }
    
    public DayOfWeek getDayOfWeek() {
        return start.getDayOfWeek();
    }
    
    public Duration getDuration() {
        return duration;
    }
    
    public Node getView() {
        return view;
    }
    
}
