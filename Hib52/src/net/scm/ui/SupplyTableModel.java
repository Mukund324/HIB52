package net.scm.ui;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import net.scm.model.*;

public class SupplyTableModel extends AbstractTableModel
{

	private static final long serialVersionUID = 1L;
	private final List<SupplyModel> supplyList;
	
	private final String[] columnNames = new String[] 
	{
		"PARTNAME",  "PARTID", "VENDORNAME", "VENDORID", "CLASS", "LEAD-TIME", "PRICE"
    };
	private final Class[] columnClass = new Class[] 
	{
	  String.class, String.class, String.class, String.class,String.class,Integer.class,Integer.class
	};
	
	public SupplyTableModel(List<SupplyModel> supplyList)
	{
	    this.supplyList = supplyList;
	}
	@Override
	public String getColumnName(int column)
	{
	   return columnNames[column];
	}
	@Override
	public int getColumnCount()
	{
	    return columnNames.length;
	}
	 
	@Override
	public int getRowCount()
	{
	     return supplyList.size();
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
	    SupplyModel row = supplyList.get(rowIndex);
	    if(0 == columnIndex) {
		    return row.getpartName();
		}
		else if(1 == columnIndex) {
		   return row.getpartId();
		}
		else if(2 == columnIndex) {
	       return row.getvendName();
	    }
	    else if(3 == columnIndex) {
	       return row.getvendId();
	    }
	    else if(4 == columnIndex) {
	       return row.getvendSupplyClass();
	    }
	    else if(5 == columnIndex) {
	       return row.getvendSupplyLeadTime();
	    }
	    else if(6 == columnIndex) {
	       return row.getvendSupplyPrice();
	    }
	    return null;
    }
	    
}
