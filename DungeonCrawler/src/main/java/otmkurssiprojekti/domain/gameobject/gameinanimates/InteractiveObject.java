/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.gameinanimates;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import otmkurssiprojekti.domain.gameobject.archetypes.interactiveobject.ActivationType;
import static otmkurssiprojekti.domain.gameobject.archetypes.interactiveobject.ActivationType.ON_PRESSED;
import static otmkurssiprojekti.domain.gameobject.archetypes.interactiveobject.ActivationType.ON_TOUCHED;
import otmkurssiprojekti.domain.gameobject.archetypes.interactiveobject.InteractiveObjectArchetype;
import otmkurssiprojekti.domain.gameobject.interfaces.GameObject;
import otmkurssiprojekti.domain.gameobject.interfaces.Mobile;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;

/**
 *
 * @author Juho Gröhn
 */
public class InteractiveObject extends BasicMobileObject implements GameObject, Mobile {

    private final char id;
    public static final boolean TRANSPARENT = true;
    private boolean solid;
    public static final Direction DIRECTION = Direction.DOWN;
    private final ActivationType activationType;
    private final List<InteractiveObject> children;
    private final Consumer<InteractiveObject> action;

    public InteractiveObject(char id, boolean solid, Coords coords, ActivationType activationType, List<InteractiveObject> children, Consumer<InteractiveObject> action) {
        super(coords, DIRECTION);
        this.id = id;
        this.solid = solid;
        this.activationType = activationType;
        this.children = children;
        this.action = action;
    }

    public InteractiveObject(InteractiveObjectArchetype interoa, Coords coords, List<InteractiveObject> children) {
        super(coords, DIRECTION);
        this.id = interoa.getId();
        this.solid = interoa.isSolid();
        this.activationType = interoa.getActivationType();
        this.children = children;
        this.action = interoa.getAction();
    }

    //Getters
    @Override
    public char getId() {
        return id;
    }

    @Override
    public boolean isTransparent() {
        return TRANSPARENT;
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

    public Consumer<InteractiveObject> getAction() {
        return action;
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
    public void addChild(InteractiveObject interactiveObject) {
        this.children.add(interactiveObject);
    }

    public void reactToTouch() {
        if (activationType.equals(ON_TOUCHED)) {
            action.accept(this);
        }
    }

    public void reactToPress() {
        if (activationType.equals(ON_PRESSED)) {
            action.accept(this);
        }
    }

    //Interactive objects always respond to remote signals.
    public void reactToSignal() {
        action.accept(this);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        hash = 17 * hash + Objects.hashCode(this.children);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final InteractiveObject other = (InteractiveObject) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.children, other.children);
    }

}
