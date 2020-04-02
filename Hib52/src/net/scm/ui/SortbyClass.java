package net.scm.ui;

import java.util.Comparator;

import net.scm.model.SupplyModel;

public class SortbyClass implements Comparator<SupplyModel> 
{ 
    // Used for sorting in ascending order of 
    // roll name 
    public int compare(SupplyModel a, SupplyModel b) 
    { 
        
        int ClassCmp = a.getvendSupplyClass().compareTo(b.getvendSupplyClass()); 
        int LeadTimeCmp = a.getvendSupplyLeadTime() - b.getvendSupplyLeadTime(); 
        
        if (ClassCmp == 0) { 
            return ((LeadTimeCmp == 0) ? ClassCmp : LeadTimeCmp); 
        } else { 
            return ClassCmp; 
        } 
    } 
}//End of SortbyClass Class 
