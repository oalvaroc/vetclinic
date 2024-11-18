package com.example.vetclinic.view;

import com.example.vetclinic.controller.AnimalController;
import com.example.vetclinic.controller.ClientController;
import com.example.vetclinic.model.Animal;
import com.example.vetclinic.model.Client;
import java.util.List;

public class AnimalTableModel extends GenericTableModel {

    private AnimalController controller;
    private ClientController clientController;

    public AnimalTableModel(AnimalController controller, ClientController clientController, List rows) {
        super(rows, new String[]{"Dono", "Nome", "Idade", "Sexo", "Peso (kg)"});
        this.controller = controller;
        this.clientController = clientController;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Animal animal = (Animal) getItem(rowIndex);
        String[] cols = new String[]{
            animal.getOwner() != null ? animal.getOwner().getName() : "",
            animal.getName(),
            Integer.toString(animal.getAge()),
            animal.getSex().name(),
            Double.toString(animal.getWeight()),};
        return cols[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class<?>[] cls = new Class[]{
            String.class, String.class, String.class, String.class, String.class
        };
        return cls[columnIndex];
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Animal animal = (Animal) getItem(rowIndex);
        Animal newAnimal = new Animal(animal);

        switch (columnIndex) {
            case 0 -> {
                Client owner = clientController.retrieveByName((String) value);
                if (owner != null) {
                    newAnimal.setOwner(owner);
                } else {
                    System.err.println("AnimalTableModel: Owner '" + value + "' not found");
                }
            }
            case 1 ->
                newAnimal.setName((String) value);
            case 2 ->
                newAnimal.setAge(Integer.valueOf((String) value));
            case 3 ->
                newAnimal.setSex(Animal.Sex.valueOf((String) value));
            case 4 ->
                newAnimal.setWeight(Double.valueOf((String) value));
            default ->
                throw new IndexOutOfBoundsException(columnIndex);
        }

        setItem(newAnimal, rowIndex);
        controller.update(newAnimal);
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col > 0;
    }
}
