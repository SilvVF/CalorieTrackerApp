object Modules {
    const val app = ":app"

    //modules that are in the app module which is the root of the project and
    //contains all of the modules together

    const val core = ":core"
    const val coreUi = ":core-ui"

    const val onboardingDomain = ":onboarding:onboarding_domain"
    const val onboardingPresentation = ":onboarding:onboarding_presentation"

    const val trackerData = ":tracker:tracker_data"
    const val trackerDomain = ":tracker:tracker_domain"
    const val trackerPresentation = ":tracker:tracker_presentation"
}