# Yummy App

**Yummy** is an Android application built with Java that helps users discover, plan, and manage meals.It supports both guest users and registered users, with offline functionality and cloud sync.

## Features

### For Guest Users
- **Meal of the Day**: Daily inspiration for meals.
- **Categories**: Browse meals by type (Seafood, Beef, Dessert, etc.).
- **Search**: Find meals by country, ingredient, or category.
- **Favorites**: Save favorite meals locally.
- **Meal Planning**: Create simple weekly plans offline.

### For Logged-In Users
- **Authentication**: Login/Signup via Firebase (Email/Password, Google, Facebook).
- **Favorites Management**: Add/remove meals; stored in Room database.
- **Weekly Meal Planner**: Create, save, and sync weekly plans.
- **Backup & Sync**: Plans and favorites backed up to Firebase Firestore.
- **Offline Access**: Favorites and weekly plans available without internet.

## Meal Details Screen
For each meal, the app displays:
- Name & Image
- Country of origin
- Ingredients and measurements
- Step-by-step cooking instructions
- Embedded YouTube video
- Favorite button

## Offline Mode
- Access saved favorites
- View weekly meal plan
- Works without an internet connection

## UI / Design
- Material Design principles
- Splash screen with **Lottie animation**
- Clean, user-friendly interface

## Tech Stack
- Java (Android)
- MVP Architecture
- RX-Java
- Retrofit (API calls)
- Room (Local database)
- Firebase Authentication & Firestore

## Inspiration
The app is inspired by **SideChef**, aiming to provide easy meal planning and discovery for both casual users and meal enthusiasts.
