package com.quizhub.person.request;

import java.util.UUID;

public class FollowRequest {
    private UUID userId;
    private UUID followerId;

    public FollowRequest() {
    }

    public FollowRequest(UUID userId, UUID followerId) {
        this.userId = userId;
        this.followerId = followerId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getFollowerId() {
        return followerId;
    }

    public void setFollowerId(UUID followerId) {
        this.followerId = followerId;
    }
}
