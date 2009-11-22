package an.chopsticks.service;

public enum AuthenticationFlag {
    Required,   // The authenticator is required to succeed. If it succeeds or fails, authentication still continues to
                // proceed down the authenticator list.
    Requisite,  // The authenticator is required to succeed. If it succeeds, authentication continues down the
                // authenticator list.  If it fails, control immediately returns to the application (authentication does
                // not proceed down the authenticator list).
    Sufficient, // The authenticator is not required to succeed.  If it does succeed, control immediately returns to the
                // application (authentication does not proceed down the authenticator list).  If it fails,
                // authentication continues down the authenticator list.
    Optional    // The authenticator is not required to succeed.  If it succeeds or fails, authentication still
                // continues to proceed down the authenticator list.
}