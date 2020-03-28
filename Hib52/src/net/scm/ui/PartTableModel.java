package net.scm.ui;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import net.scm.model.*;


public class PartTableModel extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	private final List<PartModel> partList;
	private final String[] columnNames = new String[] 
	{
		"NAME", "PARTID",  "PROFILE", "MATERIAL", "SPEC","TYP-PRICE","TYP-DLY-SCH","QLTY-STD"
    };
	private final Class[] columnClass = new Class[] 
	{
	  Integer.class, String.class, String.class, String.class, String.class,String.class,Integer.class,Integer.class,String.class
	};
	
	 public PartTableModel(List<PartModel> partList)
	    {
	        this.partList = partList;
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
	        return partList.size();
	    }
	    @Override
	    public Object getValueAt(int rowIndex, int columnIndex)
	    {
	    	PartModel row = partList.get(rowIndex);
	        if(0 == columnIndex) {
	            return row.getpartName();
	        }	    	
	        else if(1 == columnIndex) {
	            return row. getpartId();
	        }
	        else if(2 == columnIndex) {
	            return row.getpartProf();
	        }
	        else if(3 == columnIndex) {
	            return row.getpartMaterial();
	        }
	        else if(4 == columnIndex) {
	            return row.getpartSpec();
	        }
	        else if(5 == columnIndex) {
	            return row.getpartTypPrice();
	        }
	        else if(6 == columnIndex) {
	            return row.getpartTypSuppSch();
	        }
	        else if(7 == columnIndex) {
	            return row.getpartQlyStd();
	        }
	        return null;
	    }
}
