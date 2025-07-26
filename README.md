# SalsaAssignment

## !!! Important Note (Read before installing/testing)

As discussed, minor UI changes have been made to the app's design

- Accessibility - To accomodate accessibility paramters, minimum touch target is set to `48.dp` for buttons, minimum text size is set to `11.sp` for text readability (Without these changes, playstore will give you a warning to make these changes anyway).
- Coding Practice - Buttons are set to clickable but with the `onclick` set to `TODO()`. So, clicking the buttons will crash the device (this is a coding practice for ensuring we don't miss out on any functionality and is designed this way to crash indicating a pending implementation).
- Features added - Since UI was a priority, I have added both `Light` and `Dark` themes to the app using inverted colors for selective elements (similar to how chromium browsers can enforce selective inversion for forced dark mode).
- Issues - Also, Search screen has stutters due to nested scrolling (LazyRow inside a LazyColumn). This can be optimized to some extent but not much without a backend overhaul. `Pagination` has been added to every screen to keep the app somewhat realistic.
- Backend - Internet is important for this app to function. Backend is stored in a different github folder and is being fetched from the internet using the `raw.githubusercontent.com` Api.

## Dark Mode Demo (The required version)

> 1. `Home` or the `For You` Page
> 2. `Search` Page
> 3. `Profile` Page

## light Mode Demo

> 1. `Home` or the `For You` Page
> 2. `Search` Page
> 3. `Profile` Page
