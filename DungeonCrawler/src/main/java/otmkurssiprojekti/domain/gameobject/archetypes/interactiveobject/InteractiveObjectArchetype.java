/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.archetypes.interactiveobject;

import java.util.function.Consumer;
import otmkurssiprojekti.domain.gameobject.archetypes.Archetype;
import otmkurssiprojekti.domain.gameobject.gameinanimates.InteractiveObject;
import static otmkurssiprojekti.domain.gameobject.archetypes.interactiveobject.ConsumerArchetype.*;

/**
 *
 * @author Juho Gröhn
 */
public enum InteractiveObjectArchetype implements Archetype {

    SWITCH('t', false, ActivationType.ON_PRESSED, SIGNAL_CHILDREN.getInteraction()),
    IRON_GATE('G', true, ActivationType.SIGNAL_ONLY, OPEN_GATE.getInteraction()),
    PRESSURE_PLATE('_', false, ActivationType.ON_TOUCHED, SIGNAL_CHILDREN.getInteraction()),
    TOGGLE_DOOR('D', true, ActivationType.ON_PRESSED, TOGGLE_GATE.getInteraction());

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

    @Override
    public String toString() {
        return Character.toString(getId());
    }

}
