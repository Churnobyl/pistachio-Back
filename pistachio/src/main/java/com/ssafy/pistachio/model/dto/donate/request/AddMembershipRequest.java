package com.ssafy.pistachio.model.dto.donate.request;

public class AddMembershipRequest {
    private String name;
    private String agencyProfileUrl;

    public AddMembershipRequest(String name,
                                String agencyProfileUrl) {
        this.name = name;
        this.agencyProfileUrl = agencyProfileUrl;
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
        return "AddMembershipRequest{" +
                "name='" + name + '\'' +
                ", agencyProfileUrl='" + agencyProfileUrl + '\'' +
                '}';
    }
}
