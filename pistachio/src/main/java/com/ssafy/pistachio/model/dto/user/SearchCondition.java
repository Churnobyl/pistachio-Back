package com.ssafy.pistachio.model.dto.user;

public class SearchCondition {
    private String nickname;
    private String membership;

    public SearchCondition(String nickname, String membership) {
        this.nickname = nickname;
        this.membership = membership;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    @Override
    public String toString() {
        return "SearchCondition{" +
                "nickname='" + nickname + '\'' +
                ", membership='" + membership + '\'' +
                '}';
    }
}
