package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.trip.Trip;

public class UserBuilder {

    private User[] friends = new User[]{};
    private Trip[] trips = new Trip[]{};

    public static UserBuilder aUser() {
        return new UserBuilder();
    }

    public UserBuilder withFriends(final User... friends) {
        this.friends = friends;
        return this;
    }

    public UserBuilder withTrips(final Trip... trips) {
        this.trips = trips;
        return this;
    }

    public User build() {
        User user = new User();
        addTripsTo(user);
        addFriendsTo(user);
        return user;
    }

    private void addFriendsTo(final User user) {
        for (final User friend : friends) {
            user.addFriend(friend);
        }
    }

    private void addTripsTo(final User user) {
        for (final Trip trip : trips) {
            user.addTrip(trip);
        }
    }
}
