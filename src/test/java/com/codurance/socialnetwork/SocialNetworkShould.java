package com.codurance.socialnetwork;

import com.codurance.socialnetwork.domain.Post;
import com.codurance.socialnetwork.domain.Posts;
import com.codurance.socialnetwork.infrastructure.Clock;
import com.codurance.socialnetwork.infrastructure.Console;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SocialNetworkShould {
    @Mock
    private Console console;

    @Mock
    private Clock clock;

    @Mock
    private Posts postRepository;
    private Post ALICE_POST = new Post("I love the weather today", "Alice", LocalDateTime.of(2018, 1, 1, 0, 0));;

    @Test
    public void
    save_a_post_when_receive_post_command() {
        given(console.readLine())
                .willReturn("Alice -> I love the weather today")
                .willReturn("exit");

        given(clock.now())
                .willReturn(LocalDateTime.of(2018, 1, 1, 0, 5));

        SocialNetwork socialNetwork = new SocialNetwork(console, clock, postRepository);
        socialNetwork.run();

        verify(postRepository).save(ALICE_POST);
    }

    @Test
    public void
    list_a_user_timeline_when_receive_the_username() {
        given(console.readLine())
                .willReturn("Alice -> I love the weather today")
                .willReturn("Alice")
                .willReturn("exit");

        given(clock.now())
                .willReturn(LocalDateTime.of(2018, 1, 1, 0, 5));

        SocialNetwork socialNetwork = new SocialNetwork(console, clock, postRepository);
        socialNetwork.run();

        verify(console).printLine("I love the weather today (5 minutes ago)");
    }
}