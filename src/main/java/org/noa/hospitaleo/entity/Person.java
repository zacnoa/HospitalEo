package org.noa.hospitaleo.entity;


import jakarta.json.bind.annotation.JsonbTransient;
import org.noa.hospitaleo.interfaces.PrintableMenuSelection;
import java.io.Serializable;
import java.util.*;

/**
 * Predstavlja osobu
 * Omogucuje obradu osobnih informacije osobe
 */

public abstract class Person implements Serializable, PrintableMenuSelection {

    private String name;
    private String oib;
    private String id;


    public static SequencedSet<Person> allPersons = new TreeSet<>(
            Comparator.comparing(Person::getName).thenComparing(Person::getOib)
    );

    /**
     * @param name Ime
     * @param OIB OIB
     */
    public Person(String name, String OIB) {
        this.name = name;
        this.oib = OIB;
        this.id = UUID.randomUUID().toString();
        Person.addPerson(this);
    }

    public Person() {}




    /**
     * Ispisuje staticno polje allPersons
     */
    public static void printAllPersons() {
        allPersons.forEach(Person::basicInformation);
    }

    /**
     * @param person Osoba
     */
    static void addPerson(Person person) {
        allPersons.add(person);
    }

    /**
     * @return Ime
     */

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return OIB
     */

    public String getOib() {
        return oib;
    }


    public void setOIB(String oib) {
        this.oib = oib;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    @JsonbTransient
    public String getSelectionLine() {
        return this.name;
    }

    /**
     * Ispisuje ime i OIB
     */
    abstract public void basicInformation();
}