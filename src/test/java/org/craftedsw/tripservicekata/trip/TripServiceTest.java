package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class TripServiceTest {

    private static final User GUEST = null;
    private static final User UNKNOWN_USER = null;
    private static final User REGISTERED_USER = new User();
    private static final User ANOTHER_USER = new User();
    private static final Trip TO_PARIS = new Trip();
    private static final Trip TO_OHIO = new Trip();
    private User loggedInUser;
    private TripService tripService;


    @Before
    public void setUp() {
        tripService = new TestableTripService();
        loggedInUser = REGISTERED_USER;
    }

    @Test
    public void shouldThrowExceptionIfUserNotLoggedIn() {
        loggedInUser = GUEST;
        assertThatExceptionOfType(UserNotLoggedInException.class)
                .isThrownBy(() -> tripService.getTripsByUser(UNKNOWN_USER));
    }

    @Test
    public void shouldReturnEmptyListIfSearchedUserIsNotFriend() {
        User friend = UserBuilder.aUser()
                .withFriends(ANOTHER_USER)
                .withTrips(TO_PARIS)
                .build();

        assertThat(tripService.getTripsByUser(friend))
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void shouldReturnListOfTripsIfSearchedUserIsFriend() {
        User friend = UserBuilder.aUser()
                .withFriends(ANOTHER_USER, REGISTERED_USER)
                .withTrips(TO_PARIS, TO_OHIO)
                .build();

        assertThat(tripService.getTripsByUser(friend))
                .isNotNull()
                .hasSize(2);
    }

    private class TestableTripService extends TripService{
        @Override
        protected User getUserLoggedIn() {
            return loggedInUser;
        }

        @Override
        protected List<Trip> findTripsForUser(final User user) {
            return user.trips();
        }
    }

}
