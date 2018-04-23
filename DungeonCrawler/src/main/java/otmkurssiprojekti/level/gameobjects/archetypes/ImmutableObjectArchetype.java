/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.level.gameobjects.archetypes;

/**
 *
 * @author Juho Gröhn
 */
public enum ImmutableObjectArchetype implements Archetype {
    AIR(' ', true, false),
    STONE_PATH('.', false, false),
    GRASS(',', false, false),
    TALL_GRASS('/', false, false),
    STONE_WALL('0', false, true);

    private final char id;
    private final boolean transparent;
    private final boolean solid;

    private ImmutableObjectArchetype(char id, boolean transparent, boolean solid) {
        this.id = id;
        this.transparent = transparent;
        this.solid = solid;
    }

    @Override
    public char getId() {
        return id;
    }

    public boolean isTransparent() {
        return transparent;
    }

    public boolean isSolid() {
        return solid;
    }

    @Override
    public String toString() {
        return Character.toString(id);
    }

}
