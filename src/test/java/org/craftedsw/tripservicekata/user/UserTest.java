package org.craftedsw.tripservicekata.user;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    private static final User PAUL = new User();
    private static final User BOB = new User();


    @Test
    public void shouldInformWhenUsersAreNotFriends() {
        final User user = UserBuilder.aUser()
                .withFriends(BOB)
                .build();
        assertThat(user.isFriendsWith(PAUL)).isFalse();
    }

    @Test
    public void shouldInformWhenUsersAreFriends() {
        final User user = UserBuilder.aUser()
                .withFriends(BOB, PAUL)
                .build();
        assertThat(user.isFriendsWith(PAUL)).isTrue();
    }
}
