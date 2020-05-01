package net.scm.ui;

import net.scm.core.*;
import java.util.Comparator;

public class SortbyPrice implements Comparator<Order> 
{ 
    // Used for sorting in ascending order of 
    // roll name 
    public int compare(Order a, Order b) 
    { 
        
       // int ClassCmp = a.getvendSupplyClass().compareTo(b.getvendSupplyClass()); 
        return a.getvendSupplyPrice() - b.getvendSupplyPrice(); 
    } 
}//End of SortbyPrice Class 