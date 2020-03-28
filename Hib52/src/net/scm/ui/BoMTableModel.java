package net.scm.ui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import net.scm.model.*;


public class BoMTableModel extends AbstractTableModel
{
	
	private static final long serialVersionUID = 1L;
	private final List<BoMModel> bomList;
	private final String[] columnNames = new String[] 
	{
            "Product Name", "Product ID", "Part Name", "Part ID", "Quantity"
    };
	
	private final Class[] columnClass = new Class[] 
	{
	       String.class, String.class, String.class, String.class, Integer.class
	};
	
    public BoMTableModel(List<BoMModel> bomList)
    {
        this.bomList = bomList;
    }
    
    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }
 /*
    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }
*/
 
    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }
 
    @Override
    public int getRowCount()
    {
        return bomList.size();
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        BoMModel row = bomList.get(rowIndex);

        if(0 == columnIndex) {
        	  return row.getbomProdName();           
        }
        else if(1 == columnIndex) {
          
            return row.getbomProdId();
        }
        else if(2 == columnIndex) {
            return row.getbomPartName();
         
        }
        else if(3 == columnIndex) {
        	   return row.getbomPartId();
        }
        else if(4 == columnIndex) {
            return row.getbomPartQty();
        }
        return null;
    }

}
