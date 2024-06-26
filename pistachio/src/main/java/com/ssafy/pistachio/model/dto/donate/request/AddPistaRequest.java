package com.ssafy.pistachio.model.dto.donate.request;

public class AddPistaRequest {
    private Long userId;
    private Long pistaAmount;

    public AddPistaRequest(Long userId, Long pistaAmount) {
        this.userId = userId;
        this.pistaAmount = pistaAmount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPistaAmount() {
        return pistaAmount;
    }

    public void setPistaAmount(Long pistaAmount) {
        this.pistaAmount = pistaAmount;
    }

    @Override
    public String toString() {
        return "ChangePistaRequest{" +
                "userId=" + userId +
                ", pistaAmount=" + pistaAmount +
                '}';
    }
}
