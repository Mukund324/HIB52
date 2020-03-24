package net.scm.ui;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import net.scm.model.*;

public class VendorTableModel extends AbstractTableModel
{

	private static final long serialVersionUID = 1L;
	private final List<VendorModel> vendorList;
	private final String[] columnNames = new String[] 
	{
           "id",  "VENDORID", "NAME", "BLDG#", "STREET", "CITY", "COUNTRY","PIN/ZIP","PROFILE","TIN"
    };
	private final Class[] columnClass = new Class[] 
	{
	  Integer.class, String.class, String.class, String.class, String.class,String.class,String.class,String.class,String.class
	};
	
	 public VendorTableModel(List<VendorModel> vendorList)
	    {
	        this.vendorList = vendorList;
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
	        return vendorList.size();
	    }
	    @Override
	    public Object getValueAt(int rowIndex, int columnIndex)
	    {
	    	VendorModel row = vendorList.get(rowIndex);
	        if(0 == columnIndex) {
	            return row.getId();
	        }
	        else if(1 == columnIndex) {
	            return row. getvendId();
	        }
	        else if(2 == columnIndex) {
	            return row.getvendName();
	        }
	        else if(3 == columnIndex) {
	            return row.getvendAddr1();
	        }
	        else if(4 == columnIndex) {
	            return row.getvendAddr2();
	        }
	        else if(5 == columnIndex) {
	            return row.getvendCity();
	        }
	        else if(6 == columnIndex) {
	            return row.getvendCountry();
	        }
	        else if(7 == columnIndex) {
	            return row.getvendPin();
	        }
	        else if(8 == columnIndex) {
	            return row.getvendProdprof();
	        }
	        else if(9 == columnIndex) {
	            return row.getvendTin();
	        }
	        return null;
	    }
	    
}
