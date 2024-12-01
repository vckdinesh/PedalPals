# PedalPals - Bike Rental App

## Project Overview
PedalPals is a mobile-based bike rental application that allows users to search, book, and manage bike rentals seamlessly. It also enables administrators to efficiently manage bike inventory and track transactions. The app offers a scalable and user-friendly interface with real-time booking and payment functionalities.

## Features
- **User Registration & Login**: Secure authentication with support for social logins.  
- **Bike Search & Booking**: Users can search for bikes by location, type, and real-time availability.  
- **Booking Management**: View active bookings, rental history, and manage ongoing rentals.  
- **Admin Dashboard**: Admins can manage the bike inventory, monitor user activity, and generate detailed reports.  
- **Transaction Management**: Track payment history and transaction records for both users and admins.  
- **Vehicle Listing**: Users can list their own bikes for rent on the platform.

## User Flow
1. **Login/Signup**: Users can either log in or create a new account.  
2. **Main Menu**: Search for bikes by applying filters (e.g., location, type).  
3. **Booking**: Choose rental duration, provide payment details, and confirm the booking.  
4. **Payment Confirmation**: Users receive confirmation with booking details after payment.  
5. **Transaction History**: Users can view their past transactions and rental history.

## UI Design
- **Simple, User-Friendly Interface**: The app is designed with a clean and intuitive layout to ensure easy navigation.  
- **Layouts**: The app uses XML layout files such as `activity_login.xml`, `activity_register.xml`, and `activity_booking.xml` for different screens.  
- **Key UI Elements**:  
  - Input fields for user login, registration, and bike search.  
  - ListView to display bikes based on user preferences.  
  - Buttons for actions like "Search Bikes," "Book Bike," and "View Bookings."  
  - Navigation drawer for easy access to user profile, booking history, and settings.

## Architecture
The app follows the **Model-View-Controller (MVC)** architecture:  
- **Model**: Handles data storage and retrieval (e.g., user information, bike details, transaction records).  
- **View**: The user interface components, such as `MainActivity`, `AdminMenu`, and `Signup`.  
- **Controller**: Manages the logic of the app, with files like `AdminLogin`, `GetRide`, and `TransactionDetails` handling user input and interactions between the model and view.

## SQLite Integration
- **Local Storage**: SQLite is used for managing local data (e.g., user details, bike availability, and booking history).  
- **CRUD Operations**: Efficient data manipulation through Create, Read, Update, and Delete operations.  
- **Offline Support**: The app supports offline functionality, storing data locally and syncing it with the cloud once the device is online.

