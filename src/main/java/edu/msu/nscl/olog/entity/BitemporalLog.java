/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.msu.nscl.olog.entity;

import edu.msu.nscl.olog.entity.bitemporal.Bitemporal;
import edu.msu.nscl.olog.entity.bitemporal.BitemporalWrapper;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.joda.time.Interval;

/**
 *
 * @author berryman
 */
@Entity
@Table(name = "bitemporal_log")
public class BitemporalLog extends BitemporalWrapper<Log> {
    
    @Id
    private Long id;
    
    @ManyToOne(targetEntity = Log.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Log log;
    
    protected BitemporalLog(){        
    }
    
    protected BitemporalLog(Log log, Interval validityInterval){
        super(log,validityInterval);
    }

    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }
    
    @Override
    protected void setValue(Log log) {
        this.log = log;
    }

    @Override
    public Log getValue() {
        return log;
    }

    @Override
    public Bitemporal copyWith(Interval validityInterval) {
        return new BitemporalLog(log,validityInterval);
    }
    
}
