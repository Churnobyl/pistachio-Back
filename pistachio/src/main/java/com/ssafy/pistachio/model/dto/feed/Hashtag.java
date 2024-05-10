package com.ssafy.pistachio.model.dto.feed;

/**
 * 해시태그
 */
public class Hashtag {
    Long id;
    String name;

    public Hashtag(Long id,
                   String name) {
        this.id = id;
        this.name = name;
    }

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
    public String toString() {
        return "Hashtag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
