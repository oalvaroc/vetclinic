package com.example.vetclinic.view;

import javax.swing.ComboBoxModel;
import com.example.vetclinic.model.Treatment;
import java.util.ArrayList;
import javax.swing.event.ListDataListener;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.event.ListDataEvent;

public final class TreatmentComboBoxModel extends AbstractListModel<String> implements ComboBoxModel<String> {

    private final List<ListDataListener> listeners = new ArrayList<>();
    private List<Treatment> items;
    private Treatment selectedItem;

    public TreatmentComboBoxModel(List<Treatment> items) {
        this.items = new ArrayList<>(items);
        this.items.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        if (!this.items.isEmpty()) {
            setSelectedItem(this.items.get(0).getName());
        }
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if (anItem != null) {
            String name = (String) anItem;

            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getName().equals(name)) {
                    selectedItem = items.get(i);
                    break;
                }
            }
        }
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem == null ? null : selectedItem.getName();
    }

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public String getElementAt(int index) {
        Treatment item = (Treatment) items.get(index);
        return item.getName();
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        listeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        listeners.remove(l);
    }

    public void setItems(List<Treatment> items) {
        this.items = items;
        items.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        fireIntervalAdded(this, 0, this.items.size());
    }

}
