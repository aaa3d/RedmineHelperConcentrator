/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yarregion.redminehelperconcentrator.jsp;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author istorozhev
 */
@Entity
public class Issue {
    @Id 
            @Getter @Setter
            int id;
    
            @Getter @Setter
            @ManyToOne(optional = false, cascade = CascadeType.ALL)
            @JoinColumn(name = "worker_login")
            Worker worker;
            
            @Getter @Setter    
            String subject;
            
            @Getter @Setter
            double timer_value;
            
            @Getter @Setter
            Calendar last_timer_tm;
            
            public String timer_value_readable(){
                //timer_value - hours... need hours minutes seconds
                int hours = (int) timer_value;
                int minutes = (int) (timer_value*60 - hours*60);
                int seconds = (int) (timer_value*60*60 - minutes*60 - hours*60);
                
                String result = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                return result;
                
            }

    
}
