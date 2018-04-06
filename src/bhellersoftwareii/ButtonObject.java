/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import java.time.LocalDateTime;

/**
 *
 * @author Britt
 */
public class ButtonObject {
    private int buttonID;
    private String buttonText;
    private LocalDateTime startTime;
    
    ButtonObject(int btnID, String btnText, LocalDateTime start) {
        this.buttonID = btnID;
        this.buttonText = btnText;
        this.startTime = start;
    }
    
    public int getButtonID(){
        return buttonID;
    }
    
    public LocalDateTime getTime() {
        return startTime;
    }
    
}
