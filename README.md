# SalsaAssignment

## !!! Important Note (Read before installing/testing)

As discussed, minor UI changes have been made to the app's design

- Accessibility - To accomodate accessibility paramters, minimum touch target is set to `48.dp` for buttons, minimum text size is set to `11.sp` for text readability (Without these changes, playstore will give you a warning to make these changes anyway).
- Coding Practice - Buttons are set to clickable but with the `onclick` set to `TODO()`. So, clicking the buttons will crash the device (this is a coding practice for ensuring we don't miss out on any functionality and is designed this way to crash indicating a pending implementation).
- Features added - Since UI was a priority, I have added both `Light` and `Dark` themes to the app using inverted colors for selective elements (similar to how chromium browsers can enforce selective inversion for forced dark mode).
- Issues - Also, Search screen has stutters due to nested scrolling (LazyRow inside a LazyColumn). This can be optimized to some extent but not much without a backend overhaul. `Pagination` has been added to every screen to keep the app somewhat realistic.
- Backend - Internet is important for this app to function. Backend is stored in a different github folder and is being fetched from the internet using the `raw.githubusercontent.com` Api.


![dark_profile](https://github.com/user-attachments/assets/d7f08b09-b7c5-4cd3-b411-fda35a5edae3)
![dark_search](https://github.com/user-attachments/assets/ab4bfa28-d53d-4a08-9f76-a809152aadf7)
![dark_home](https://github.com/user-attachments/assets/c9b2e4ae-42f0-477c-85eb-4b8323a9977c)
![light_home](https://github.com/user-attachments/assets/1576691a-47a4-45a4-8b45-d333524b2c58)
![light_search](https://github.com/user-attachments/assets/19d69930-5f54-41f1-8c36-c4cf8ffe996b)
![light_profile](https://github.com/user-attachments/assets/bacbe3d3-9082-4c5c-843b-4cdfc2ce50ea)











## Dark Mode Demo (The required version)

<img src="https://github.com/user-attachments/assets/925f3d4c-acd0-4f8d-998a-4f42fe5a41d6" alt="home dark" width="273">
<img src="https://github.com/user-attachments/assets/4361e1fc-840d-49bd-a8c6-4a611f7db46a" alt="search dark" width="273">
<img src="https://github.com/user-attachments/assets/90939d88-61ac-4e4a-af9b-e3bedd61de0c" alt="profile dark" width="273">

> 1. `Home` or the `For You` Page
> 2. `Search` Page
> 3. `Profile` Page

## light Mode Demo

<img src="https://github.com/user-attachments/assets/27eab72c-0e1e-4dc2-b5d6-904228be8f63" alt="profile light" width="273">
<img src="https://github.com/user-attachments/assets/9fa30ac2-c761-44fb-84d9-b5b975b3a06c" alt="search light" width="273">
<img src="https://github.com/user-attachments/assets/6a6192d1-808e-48da-a62c-38e2e46843e8" alt="home light" width="273">

> 1. `Home` or the `For You` Page
> 2. `Search` Page
> 3. `Profile` Page

## Project setup and module structure

Project setup is as normal, file structure -

- data (has api service file)
- model (has data models of the responses with serialization added)
- ui (has UI and related files)
    - sections (home, search and profile screen files along with paging sources and a single viewmodel to use data caching)
    - theme (the default folder created as usual)
- util (utilities)
    - annotations (has custom preview annotation)
    - resources (has different files for colors, fonts and values that are shared)
    - ScreenState (sealed class for screen state monitoring)
- MainActivity
