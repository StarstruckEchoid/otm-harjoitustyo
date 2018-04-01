/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level.GameObjects;

import java.util.List;
import java.util.function.Consumer;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Coords;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Direction;
import otmkurssiprojekti.Level.GameObjects.Archetypes.ActivationType;

/**
 *
 * @author Juho Gröhn
 */
public class InteractiveObject implements GameObject {

    private final char id;
    private final boolean transparent;
    private boolean solid;
    private final Coords coords;
    private Direction direction;

    private final ActivationType activationType;
    private final List<InteractiveObject> children;
    private final Consumer<InteractiveObject> action;

    public InteractiveObject(char id, boolean transparent, boolean solid, Coords coords, Direction direction, ActivationType activationType, List<InteractiveObject> children, Consumer<InteractiveObject> action) {
        this.id = id;
        this.transparent = transparent;
        this.solid = solid;
        this.coords = coords;
        this.direction = direction;
        this.activationType = activationType;
        this.children = children;
        this.action = action;
    }

    //Getters
    @Override
    public char getId() {
        return id;
    }

    @Override
    public boolean isTransparent() {
        return transparent;
    }

    @Override
    public boolean isSolid() {
        return solid;
    }

    @Override
    public Coords getCoords() {
        return coords;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    public List<InteractiveObject> getChildren() {
        return children;
    }

    //Setters
    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    //Others
    @Override
    public void move(Direction dir) {
        this.coords.add(dir.getCoords());
    }

    @Override
    public void turn(Direction direction) {
        this.direction = direction;
    }

    //Specials
    public void reactToTouch() {
        if (activationType.equals(ActivationType.ON_TOUCHED)) {
            action.accept(this);
        }
    }

    public void reactToPress() {
        if (activationType.equals(ActivationType.ON_PRESSED)) {
            action.accept(this);
        }
    }

    //Interactive objects always respond to remote signals.
    public void reactToSignal() {
        action.accept(this);
    }

}
