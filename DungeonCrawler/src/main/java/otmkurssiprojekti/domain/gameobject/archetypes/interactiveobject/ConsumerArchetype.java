/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.archetypes.interactiveobject;

import java.util.function.Consumer;
import otmkurssiprojekti.domain.gameobject.gameinanimates.InteractiveObject;

/**
 *
 * @author Juho Gröhn
 */
public enum ConsumerArchetype {
    CLOSE_GATE("CLOSE", i -> i.setSolid(true)),
    OPEN_GATE("OPEN", i -> i.setSolid(false)),
    TOGGLE_GATE("TOGGLE", i -> i.setSolid(!i.isSolid())),
    SIGNAL_CHILDREN("SIGNAL", i -> i.getChildren().forEach(c -> c.reactToSignal()));

    private final String name;
    private final Consumer<InteractiveObject> interaction;

    private ConsumerArchetype(String name, Consumer<InteractiveObject> interaction) {
        this.name = name;
        this.interaction = interaction;
    }

    public String getName() {
        return name;
    }

    public Consumer<InteractiveObject> getInteraction() {
        return interaction;
    }

}
