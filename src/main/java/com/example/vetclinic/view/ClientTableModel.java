package com.example.vetclinic.view;

import com.example.vetclinic.controller.ClientController;
import com.example.vetclinic.model.Client;
import java.util.List;

public class ClientTableModel extends GenericTableModel {

    private ClientController controller;

    public ClientTableModel(ClientController controller, List rows) {
        super(rows, new String[]{"Nome", "Endereço", "CEP", "Telefone", "Email"});
        this.controller = controller;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Client client = (Client) getItem(rowIndex);
        String[] cols = new String[]{
            client.getName(),
            client.getAddress(),
            client.getCep(),
            client.getTel(),
            client.getEmail()
        };
        return cols[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex > 4) {
            throw new IndexOutOfBoundsException(columnIndex);
        }
        return String.class;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Client client = (Client) getItem(rowIndex);
        Client newCli = new Client(client);

        switch (columnIndex) {
            case 0 ->
                newCli.setName((String) value);
            case 1 ->
                newCli.setAddress((String) value);
            case 2 ->
                newCli.setCep((String) value);
            case 3 ->
                newCli.setTel((String) value);
            case 4 ->
                newCli.setEmail((String) value);
            default ->
                throw new IndexOutOfBoundsException(columnIndex);
        }

        setItem(newCli, rowIndex);
        controller.update(newCli);
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return true;
    }
}
