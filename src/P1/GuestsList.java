package P1;

import java.util.ArrayList;
import java.util.List;

class GuestsList {
    private int availablePlaces;
    private ArrayList<Guest> participantsList;

    public GuestsList(int guestsCapacity) {
        this.availablePlaces = guestsCapacity;
        this.participantsList = new ArrayList<Guest>(this.availablePlaces);
    }

    /**
     * Add a new, unique guest to the list.
     *
     * @param g the guest to be added
     * @return '-1' if the guest is already present, '0' if is a guest, or the
     *         number on the waiting list
     */
    public int add(Guest g) {
        System.out.println("A new person is being added... ");
        if (isOnTheListAlready(g)){
            return -1;
        }

        this.participantsList.add(g);
        if (this.participantsList.size() <= this.availablePlaces) {
            g.notifyGuest(-1);
            return 0;
        }

        int waitlistPosition = this.participantsList.size() - this.availablePlaces;
        g.notifyGuest(waitlistPosition);
        return waitlistPosition;
    }

    /**
     * Check if someone is already registered ( as a guest, or on the waiting
     * list).
     *
     * @param g the guest we are searching for
     * @return true if present, false if not
     */
    private boolean isOnTheListAlready(Guest g) {
        for (Guest guest : this.participantsList) {
            if (guest.equals(g)) {
                System.out.println("The person is already registered.");
                return true;
            }

            if (guest.getPhoneNumber().equals(g.getPhoneNumber())) {
                System.out.println("The phone number is already registered.");
                return true;
            }

            if (guest.getEmail().equals(g.getEmail())) {
                System.out.println("The email address is already registered.");
                return true;
            }
        }
        return false;
    }

    /**
     * Search for a guest based on first and last name. Return the first result.
     *
     * @param firstName first name of the guest
     * @param lastName  last name of the guest
     * @return the guest if found, null if not
     */
    public Guest search(String firstName, String lastName) {
        String fullNameToSearch = firstName + " " + lastName;
        for (Guest g : this.participantsList) {
            if (g.fullName().equalsIgnoreCase(fullNameToSearch)) {
                return g;
            }
        }
        return null;
    }
    private Guest searchByEmail(String email) {
        for (Guest g : this.participantsList) {
            if (g.getEmail().equalsIgnoreCase(email)) {
                return g;
            }
        }
        return null;
    }

    private Guest searchByPhoneNumber(String phoneNumber) {
        for (Guest g : this.participantsList) {
            if (g.getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
                return g;
            }
        }
        return null;
    }

    /**
     * Search for a guest based on email or phone number. Return the first result.
     *
     * @param opt option to use for searching: 2 for email, 3 for phone number
     * @param match what is searched for
     * @return the guest if found, null if not
     */
    public Guest search(int opt, String match) {
        Guest searchResult = null;

        switch (opt) {
            case 2:
                searchResult = searchByEmail(match);
                break;
            case 3:
                searchResult = searchByPhoneNumber(match);
                break;
            default:
                break;
        }

        return searchResult;
    }

    /**
     * Remove a guest based on first and last name. Remove the first result.
     *
     * @param firstName first name of the guest
     * @param lastName  last name of the guest
     * @return true if removed, false if not
     */
    public boolean remove(String firstName, String lastName) {
        Guest g = search(firstName, lastName);

        if ( g != null) {
            int gIndex = this.participantsList.indexOf(g);
            this.participantsList.remove(g);
            if (gIndex < this.availablePlaces && this.availablePlaces <= this.participantsList.size()) {
                Guest g1 = this.participantsList.get(this.availablePlaces - 1);
                System.out.println( "["+ g1.fullName() + "]" + " Congratulations! Your attendance at the event has been confirmed. We look forward to welcoming you!");
            }
        }
        System.out.println("The person was removed successfully.");
        return false;
    }

    /**
     * Remove a guest based on email or phone number. Remove the first result.
     *
     * @param opt   option to use for searching: 2 for email, 3 for phone number
     * @param match the match we are searching for
     * @return true if removed, false if not
     */
    public boolean remove(int opt, String match) {
        Guest g = search(opt, match);

        if ( g != null) {
            int gIndex = this.participantsList.indexOf(g);
            this.participantsList.remove(g);
            if (gIndex < this.availablePlaces && this.availablePlaces <= this.participantsList.size()) {
                Guest g1 = this.participantsList.get(this.availablePlaces - 1);
                System.out.println( "["+ g1.fullName() + "]" + " Congratulations! Your attendance at the event has been confirmed. We look forward to welcoming you!");
            }
            System.out.println("The person was removed successfully.");
        }
        System.out.println("Error: The person is not registered for the event.");
        return false;
    }

    // Show the list of guests.
    public void showGuestsList() {
        if ( this.participantsList.isEmpty()) {
            System.out.println("No participants have registered...");
            return;
        }

        int limit = this.numberOfGuests();

        for (int i = 0; i < limit; i++) {
            System.out.println((i + 1) + ". " + this.participantsList.get(i));
        }
    }

    // Show the people on the waiting list.
    public void showWaitingList() {
        if (this.numberOfPeopleWaiting() == 0) {
            System.out.println("The waiting list is currently empty...");
            return;
        }

        for (int i = this.availablePlaces, j = 1; i < this.participantsList.size(); i++, j++)
            System.out.println(j + ". " + this.participantsList.get(i));
    }

    /**
     * Show how many free spots are left.
     *
     * @return the number of spots left for guests
     */
    public int numberOfAvailableSpots() {
        int available = this.availablePlaces - this.participantsList.size();
        return Math.max(available, 0);
    }

    /**
     * Show how many guests there are.
     *
     * @return the number of guests
     */
    public int numberOfGuests() {
        return Math.min(this.participantsList.size(), this.availablePlaces);
    }

    /**
     * Show how many people are on the waiting list.
     *
     * @return number of people on the waiting list
     */
    public int numberOfPeopleWaiting() {
        int waitlistSize = this.participantsList.size() - this.availablePlaces;
        return Math.max(waitlistSize, 0);
    }

    /**
     * Show how many people there are in total, including guests.
     *
     * @return how many people there are in total
     */
    public int numberOfPeopleTotal() {
        // TO DO:
        return this.participantsList.size();
    }

    /**
     * Find all people based on a partial value search.
     *
     * @param match the match we are looking for
     * @return a list of people matching the criteria
     */
    public List<Guest> partialSearch(String match) {
        List<Guest> searchResults = new ArrayList<Guest>();

        for (Guest guest : this.participantsList) {
            if (guest.getFirstName().toLowerCase().contains(match.toLowerCase())
                    || guest.getLastName().toLowerCase().contains(match.toLowerCase())
                    || guest.getEmail().toLowerCase().contains(match.toLowerCase())
                    || guest.getPhoneNumber().contains(match))
                searchResults.add(guest);
        }

        return searchResults;
    }

    @Override
    public String toString() {
        return this.participantsList.toString();
    }
}