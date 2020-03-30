package net.scm.ui;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import net.scm.model.*;


public class ProdCycleTableModel extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	private final List<ProdCycleModel> pcList;
	private final String[] columnNames = new String[] 
	{
		"BATCH NAME", "BATCH ID",  "PRODUCT", "PRODUCT ID", "CLASS","START-DATE","CAP-PRICE", "BATCH SIZE"
    };
	private final Class[] columnClass = new Class[] 
	{
	  String.class, String.class, String.class, String.class, String.class,Date.class,Integer.class,Integer.class
	};
	
	 public ProdCycleTableModel(List<ProdCycleModel> pcList)
	    {
	        this.pcList = pcList;
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
	        return pcList.size();
	    }
	    @Override
	    public Object getValueAt(int rowIndex, int columnIndex)
	    {
	    	ProdCycleModel row = pcList.get(rowIndex);
	        if(0 == columnIndex) {
	            return row.getprodcycName();
	        }	    	
	        else if(1 == columnIndex) {
	            return row. getprodcycId();
	        }
	        else if(2 == columnIndex) {
	            return row.getprodcycProd();
	        }
	        else if(3 == columnIndex) {
	            return row.getprodcycProdId();
	        }
	        else if(4 == columnIndex) {
	            return row.getprodcycClass();
	        }
	        else if(5 == columnIndex) {
	            return row.getprodcycStDt();
	        }
	        else if(6 == columnIndex) {
	            return row.getprodcycCapPrice();
	        }
	        else if(7 == columnIndex) {
	            return row.getprodcycBatchSize();
	        }
	        return null;
	    }
}