package com.udacity.sandwichclub.model;

import java.util.List;

public class Sandwich implements Comparable {

    private Name name;
    private String placeOfOrigin;
    private String description;
    private String image;
    private List<String> ingredients = null;

    /**
     * No args constructor for use in serialization
     */
    public Sandwich() {
        name = new Name();
    }

    public Sandwich(Name name, String placeOfOrigin, String description, String image, List<String> ingredients) {
        this.name = (name == null) ? new Name() : name;
        this.placeOfOrigin = placeOfOrigin;
        this.description = description;
        this.image = image;
        this.ingredients = ingredients;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public int compareTo(Object o) {
        Sandwich secondObj = (Sandwich) o;
        if (this.getName() != null &&
                this.getName().getMainName() != null &&
                secondObj.getName() != null &&
                secondObj.getName().getMainName() != null) {
            return this.getName().getMainName().compareTo(((Sandwich) o).getName().getMainName());
        }
        if ((this.getName() == null ||
                this.getName().getMainName() == null) &&
                secondObj.getName() != null &&
                secondObj.getName().getMainName() != null) {
            return -1;
        }

        if ((this.getName() != null ||
                this.getName().getMainName() != null) &&
                secondObj.getName() == null ||
                secondObj.getName().getMainName() == null) {
            return 1;
        }
        return 0;
    }
}
