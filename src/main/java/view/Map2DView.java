package view;

import interface_adapter.map2d.Map2DController;
import interface_adapter.map2d.Map2DPresenter;
import interface_adapter.map2d.Map2DViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Map2DView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName;
    private final Map2DViewModel map2DViewModel;
    private Map2DController map2DController;


    public Map2DView (Map2DViewModel map2DViewModel) {
        this.viewName = "Map2D View";
        this.map2DViewModel = map2DViewModel;
        this.map2DViewModel.addPropertyChangeListener(this);


    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public String getViewName() {
        return viewName;
    }

    public void setMap2DController(Map2DController map2DController) {
        this.map2DController = map2DController;
    }

    public Map2DViewModel getMap2DViewModel() {
        return map2DViewModel;
    }

    public Map2DController getMap2DController() {
        return map2DController;
    }
}
