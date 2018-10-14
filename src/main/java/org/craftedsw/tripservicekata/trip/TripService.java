package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		List<Trip> tripList = new ArrayList<Trip>();
		User loggedUser = getUserLoggedIn();
		boolean isFriend = false;
		if (loggedUser != null) {
			for (User friend : user.getFriends()) {
				if (friend.equals(loggedUser)) {
					isFriend = true;
					break;
				}
			}
			if (isFriend) {
				tripList = findTripsForUser(user);
			}
			return tripList;
		} else {
			throw new UserNotLoggedInException();
		}
	}

    protected List<Trip> findTripsForUser(final User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getUserLoggedIn() {
		return UserSession.getInstance().getLoggedUser();
	}

}
