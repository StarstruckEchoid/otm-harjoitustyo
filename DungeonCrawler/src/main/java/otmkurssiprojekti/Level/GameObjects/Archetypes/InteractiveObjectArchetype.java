/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level.GameObjects.Archetypes;

import java.util.function.Consumer;
import otmkurssiprojekti.Level.GameObjects.InteractiveObject;

/**
 *
 * @author Juho Gröhn
 */
public enum InteractiveObjectArchetype implements Archetype{

    SWITCH('T', false, ActivationType.ON_PRESSED, (i) -> i.getChildren().forEach(c -> c.reactToSignal())),
    IRON_GATE('#', true, ActivationType.SIGNAL_ONLY, (i) -> i.setSolid(false)),
    PRESSURE_PLATE('_', false, ActivationType.ON_TOUCHED, (i) -> i.getChildren().forEach(c -> c.reactToSignal())),
    TOGGLE_DOOR('|', true, ActivationType.ON_PRESSED, (i) -> i.setSolid(!i.isSolid()));

    private final char id;
    private final boolean solid;
    private final ActivationType activationType;
    private final Consumer<InteractiveObject> action;

    private InteractiveObjectArchetype(char id, boolean solid, ActivationType activationType, Consumer<InteractiveObject> action) {
        this.id = id;
        this.solid = solid;
        this.activationType = activationType;
        this.action = action;
    }

    @Override
    public char getId() {
        return id;
    }

    public boolean isSolid() {
        return solid;
    }

    public ActivationType getActivationType() {
        return activationType;
    }

    public Consumer<InteractiveObject> getAction() {
        return action;
    }

}
