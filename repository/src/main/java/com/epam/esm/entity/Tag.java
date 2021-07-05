package com.epam.esm.entity;

public class Tag {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (id != null ? !id.equals(tag.id) : tag.id != null) return false;
        return name != null ? name.equals(tag.name) : tag.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public static class Builder{
        private Tag tag;

        public Builder() {
            this.tag = new Tag();
        }

        public Builder withId(Long id){
            tag.id = id;
            return this;
        }

        public Builder withName(String name){
            tag.name = name;
            return this;
        }

        public Tag build(){
            return tag;
        }
    }
}
