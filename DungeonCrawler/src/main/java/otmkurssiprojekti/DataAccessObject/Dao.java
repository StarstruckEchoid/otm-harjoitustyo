/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.DataAccessObject;

/**
 *
 * @author Juho Gröhn
 */
public interface Dao<T> {
    public T load(String fileName);
    public void save(T t);
}
