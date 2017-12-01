/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yarregion.redminehelperconcentrator.jsp;

import java.util.Calendar;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author istorozhev
 */
@Entity
public class Worker {
    @Id 
    @Getter @Setter
    String login;
    
    @Getter @Setter
    String fio;
    
    @Getter @Setter
    int lock_status;
    
    
    @Getter @Setter
    Calendar last_ping_tm;
    
    @Getter @Setter
    @OneToMany(mappedBy = "worker", fetch = FetchType.LAZY)
    private List<Issue> issues;
    
    @Getter @Setter
    @Transient
    public List<Issue> last_issues;
    
    
    
    
}
