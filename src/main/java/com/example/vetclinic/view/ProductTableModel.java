package com.example.vetclinic.view;

import com.example.vetclinic.controller.Controller;
import com.example.vetclinic.model.Product;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

public class ProductTableModel extends GenericTableModel {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private Controller controller;

    public ProductTableModel(Controller controller, List rows) {
        super(rows, new String[]{"Nome", "Data Adicionado", "Data Validade", "Qtd."});
        this.controller = controller;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product product = (Product) getItem(rowIndex);
        String[] cols = new String[]{
            product.getName(),
            product.getDateEntry() == null ? "" : dateFormat.format(product.getDateEntry()),
            product.getDateExpiration() == null ? "" : dateFormat.format(product.getDateExpiration()),
            Integer.toString(product.getCount())
        };
        return cols[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return String.class;
            }
            case 1 -> {
                return String.class;
            }
            case 2 -> {
                return String.class;
            }
            case 3 -> {
                return Integer.class;
            }
            default ->
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Product product = (Product) getItem(rowIndex);
        Product newProd = null;

        try {
            switch (columnIndex) {
                case 0:
                    newProd = new Product(
                            UUID.fromString(product.getId()),
                            (String) value,
                            product.getDateEntry(),
                            product.getDateExpiration(),
                            product.getCount()
                    );
                case 1:
                    newProd = new Product(
                            UUID.fromString(product.getId()),
                            product.getName(),
                            dateFormat.parse((String) value),
                            product.getDateExpiration(),
                            product.getCount()
                    );
                case 2:
                    newProd = new Product(
                            UUID.fromString(product.getId()),
                            product.getName(),
                            product.getDateEntry(),
                            dateFormat.parse((String) value),
                            product.getCount()
                    );
                case 3:
                    newProd = new Product(
                            UUID.fromString(product.getId()),
                            product.getName(),
                            product.getDateEntry(),
                            product.getDateExpiration(),
                            (Integer) value
                    );
            }
        } catch (Exception e) {
        }

        if (newProd != null) {
            setItem(newProd, rowIndex);
            controller.updateProduct(newProd);
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return true;
    }
}
