/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oct.soft.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

/**
 *
 * @author osantau
 */
public class Office {
    private Integer idoffice;
    @NotEmpty(message = "Campul Denumire este obligatoriu.")
    @Min(value = 3, message = "Campul Denumire nu poate fi mai mic de 3 caractere.")
    @Max(value = 45, message = "Campul Denumire nu poate avea mai mult de 45 caractere.")
    private String name;
    private int isbranch;
    private int parent;

    public Office() {
        this.idoffice=null;
    }

    public Office( String name, int isbranch, int parent) { 
        this.idoffice = null;
        this.name = name;
        this.isbranch = isbranch;
        this.parent = parent;
    }

    public Integer getIdoffice() {
        return idoffice;
    }

    public void setIdoffice(Integer idoffice) {
        this.idoffice = idoffice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsbranch() {
        return isbranch;
    }

    public void setIsbranch(int isbranch) {
        this.isbranch = isbranch;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }
    public boolean isNew() {
        return this.idoffice == null ? true : false;
    }
    @Override
    public String toString() {
        return "Office{" + "idoffice=" + idoffice + ", name=" + name + ", isbranch=" + isbranch + ", parent=" + parent + '}';
    }
    
    
}
