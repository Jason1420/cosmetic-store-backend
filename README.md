# Cosmetic Store Backend

This project serves as the backend for the Cosmetic Store e-commerce platform. It handles user authentication, product management, order processing, and other essential business logic. The backend is built with modern web technologies to ensure efficiency, security, and scalability.

## Features

- **User Authentication**: Secure user registration, login, and authentication using JWT.
- **Product Management**: Create, read, update, and delete (CRUD) operations for cosmetic products.
- **Order Processing**: Handle customer orders, including checkout and payment processing.
- **Cart Management**: Add and remove items from the shopping cart.
- **Admin Panel**: Manage users, products, and orders with admin privileges.
- **Secure API**: Well-structured RESTful API with proper validation and error handling.

## Technologies Used

- **Node.js**: JavaScript runtime for building scalable backend services.
- **Express.js**: Lightweight web framework for creating REST APIs.
- **MongoDB**: NoSQL database for efficient data storage.
- **Mongoose**: ODM (Object Data Modeling) library for MongoDB.
- **JWT (JSON Web Token)**: Secure authentication mechanism.
- **Cloudinary**: Image storage and management.
- **Stripe**: Payment gateway integration.

## Installation

To set up the backend locally:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Jason1420/cosmetic-store-backend.git
   ```

2. **Navigate to the project directory**:
   ```bash
   cd cosmetic-store-backend
   ```

3. **Install dependencies**:
   ```bash
   npm install
   ```

4. **Set up environment variables**:
   Create a `.env` file in the root directory and configure the following:
   ```env
   PORT=5000
   MONGO_URI=your_mongodb_connection_string
   JWT_SECRET=your_jwt_secret
   CLOUDINARY_API_KEY=your_cloudinary_api_key
   STRIPE_SECRET_KEY=your_stripe_secret_key
   ```

5. **Start the server**:
   ```bash
   npm start
   ```
   The backend will run on `http://localhost:5000`.

## API Endpoints

| Method | Endpoint               | Description               |
|--------|------------------------|---------------------------|
| GET    | /api/products          | Get all products         |
| GET    | /api/products/:id      | Get a single product     |
| POST   | /api/products          | Create a new product (Admin) |
| PUT    | /api/products/:id      | Update product (Admin)   |
| DELETE | /api/products/:id      | Delete product (Admin)   |
| POST   | /api/users/register    | User registration        |
| POST   | /api/users/login       | User login               |
| GET    | /api/users/profile     | Get user profile (Auth)  |
| PUT    | /api/users/profile     | Update profile (Auth)    |
| POST   | /api/orders            | Create a new order       |
| GET    | /api/orders/:id        | Get order details        |

## Deployment

This project is deployed and accessible online. The API is hosted at: [cosmetic-store-backend.vercel.app](https://cosmetic-store-backend.vercel.app)

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/YourFeature`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add new feature'`).
5. Push to the branch (`git push origin feature/YourFeature`).
6. Open a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Acknowledgements

- **Express.js**: For building the RESTful API.
- **MongoDB & Mongoose**: For database management.
- **JWT**: For secure authentication.
- **Cloudinary**: For handling image uploads.
- **Stripe**: For payment processing.

For more details, please visit the [repository](https://github.com/Jason1420/cosmetic-store-backend).
