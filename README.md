# PedalPals
A Mobile based bike rental application
Bike Rental App
Project Overview
The Bike Rental App provides a platform for users to search, book, and manage bike rentals, while allowing administrators to manage bike inventory and track transactions. It offers a scalable, user-friendly interface with real-time booking and payment functionalities.

Features
User Registration & Login: Secure authentication, including social login options.
Bike Search & Booking: Search bikes by location/type, with real-time availability.
Booking Management: View active bookings, rental history, and manage bookings.
Admin Dashboard: Manage bike inventory, monitor user activity, and generate reports.
Transaction Management: Track payment history and transactions.
Vehicle Listing: Users can list their own bikes for rent.
User Flow
Login/Signup: Users authenticate or create an account.
Main Menu: Search for bikes based on filters.
Booking: Choose rental duration, enter payment details, and confirm.
Payment Confirmation: Confirm booking details after payment.
Transaction History: View past transactions and bookings.
UI Design
Simple, User-Friendly Interface: Clean design with easy navigation.
Layouts: XML files like activity_login.xml, activity_register.xml, activity_booking.xml.
Key Elements: Input fields, ListView, buttons for actions like "Search Bikes," "Book Bike," and "View Bookings."
Architecture
MVC Architecture:
Model: Data storage and retrieval (user, bike, transaction info).
View: UI components (MainActivity, AdminMenu).
Controller: Manages logic (AdminLogin, GetRide, TransactionDetails).
SQLite Integration
Local storage for user, bike, and booking data with CRUD operations.
Supports offline use and syncs with the cloud when online.
