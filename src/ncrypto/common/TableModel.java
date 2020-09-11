package ncrypto.common;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int row, col;
    private Integer[][] data;
    private String[] columnNames;

    public TableModel(int r, int c) {
        this.row = r;
        this.col = c;
        data = new Integer[r][c];
        columnNames = new String[c];
        for (int i = 0; i < c; i++) {
            columnNames[i] = "";
        }
    }

    @Override
    public int getRowCount() {
        return row;
    }

    @Override
    public int getColumnCount() {
        return col;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = (Integer) aValue;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public void fireTableDataChanged() {
        super.fireTableDataChanged();
    }
    
}
