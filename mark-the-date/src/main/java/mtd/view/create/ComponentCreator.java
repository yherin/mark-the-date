/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.view.create;

/**
 *
 * @author sjack
 */
public abstract class ComponentCreator {

    public final void createAndMapComponents() {
        createComponents();
        mapComponents();
    }

    abstract void createComponents();

    abstract void mapComponents();
}
