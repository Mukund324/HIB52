package net.scm.ui;

import net.scm.core.*;
import java.util.Comparator;

import net.scm.model.SupplyModel;

public class SortbyLeadTime implements Comparator<Order> 
{ 
    // Used for sorting in ascending order of 
    // roll name 
    public int compare(Order a, Order b) 
    { 
        
       // int ClassCmp = a.getvendSupplyClass().compareTo(b.getvendSupplyClass()); 
        return a.getvendSupplyLeadTime() - b.getvendSupplyLeadTime(); 
    } 
}//End of SortbyLeadTime Class 
