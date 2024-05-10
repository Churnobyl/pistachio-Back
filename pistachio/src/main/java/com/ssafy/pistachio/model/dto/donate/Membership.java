package com.ssafy.pistachio.model.dto.donate;

/**
 * 소속
 */
public class Membership {
    private Long id;
    private String name;
    private String agencyProfileUrl;

    public Membership(Long id,
                      String name,
                      String agencyProfileUrl) {
        this.id = id;
        this.name = name;
        this.agencyProfileUrl = agencyProfileUrl;
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

    public String getAgencyProfileUrl() {
        return agencyProfileUrl;
    }

    public void setAgencyProfileUrl(String agencyProfileUrl) {
        this.agencyProfileUrl = agencyProfileUrl;
    }

    @Override
    public String toString() {
        return "Membership{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", agencyProfileUrl='" + agencyProfileUrl + '\'' +
                '}';
    }
}
