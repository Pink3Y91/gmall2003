package com.pink.gmall.bean;

import java.io.Serializable;
import java.util.Objects;

public class PmsBaseCatalog3 implements Serializable {
    private String id;
    private String name;
    private String catalog2Id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatalog2Id() {
        return catalog2Id;
    }

    public void setCatalog2Id(String catalog2Id) {
        this.catalog2Id = catalog2Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PmsBaseCatalog3 that = (PmsBaseCatalog3) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PmsBaseCatalog3{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", catalog2Id='" + catalog2Id + '\'' +
                '}';
    }
}
