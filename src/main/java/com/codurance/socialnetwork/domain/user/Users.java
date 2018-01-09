package com.codurance.socialnetwork.domain.user;

import java.util.List;

public interface Users {
    void addFollower(String follower, String followed);

    List<String> getFollowers(String username);
}
