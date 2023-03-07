# Spring Boot Blog REST API

This repository contains a REST API for a blog built using Spring Boot. With endpoints for creating, updating, and retrieving blog posts and users, and organized code and clear documentation, it's a useful resource for developers looking to learn about REST API development with Spring Boot.

# Table of Contents

- [Endpoints](#endpoints)
    - [Auth](#auth)
    - [Post](#post)
    - [Comment](#comment)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

# Endpoints

This REST API provides the following endpoints:

## Auth

1. ### Signin

    This endpoint is used to authenticate users by allowing them to sign in using their email and password.
    
    `POST /api/v1/auth/signin`

2. ### Signup

    This endpoint is used to register new users by allowing them to sign up with their email and password.
    
    `/api/v1/auth/signup`

## Post

1. ### Get all Posts

    This endpoint is used to retrieve a list of all posts.
    
    `GET /api/v1/posts`

2. ### Get Post by Id

    This endpoint is used to retrieve a specific post by its ID.
    
    `GET /api/api/posts/{postId}`

3. ### Create a Post

    This endpoint is used to create new post.
    
    `POST /api/v1/posts`

4. ### Update a Post

    This endpoint is used to update a specific post by its ID.
    
    `PUT /api/v1/posts/{postId}`

5. ### Delete a Post

    This endpoint is used to delete a specific post by its ID.
    
    `DELETE /api/v1/posts/{postId}`

## Comment

1. ### Get Comments by PostId

    This endpoint is used to retrieve all comments for a specific post.
    
    `GET /api/v1/posts/{postId}/comments`

2. ### Get Comment by PostId

    This endpoint is used to retrieve a specific comment for a specific post.
    
    `GET /api/v1/posts/{postId}/comments/{commentId}`

3. ### Create a Comment

    This endpoint is used to create a new comment for a specific post.
    
    `POST /api/v1/posts/{postId}/comments`

4. ### Update a Comment

    This endpoint is used to update a specific comment for a specific post.
    
    `PUT /api/v1/posts/{postId}/comments/{commentId}`

5. ### Delete a Comment

    This endpoint is used to delete a specific comment for a specific post.
    
    `DELETE /api/v1/posts/{postId}/comments/{commentId}`

# Installation

1. To run the projects, you'll need to have Java installed on your machine. You can download Java from the [official website](https://www.java.com/en/download/).

2. Install an Integrated Development Environment (IDE) such as IntelliJ  or Visual Studio Code.

   - To install IntelliJ IDEA, follow the instructions on the [official website](https://www.jetbrains.com/idea/download/)

   - To install Visual Studio Code, follow the instructions on the [official website](https://code.visualstudio.com/Download).

# Usage

To run the application, please follow these steps:

1. Clone the repository using the following command:

   `git clone https://github.com/aamirxshaikh/spring-boot-blog-rest-api.git`

2. Navigate into the project directory using the following command:

   `cd spring-boot-blog-rest-api`

3. You can simply run the project in the IntelliJ IDEA by clicking the "run" icon or you can run the application using the following command:

   `mvn spring-boot:run`

4. After the application is running, you can interact with the API by making HTTP requests to the endpoints listed above using tools like curl or Postman.

# Contributing

Feel free to contribute to this repository by improving the existing codebase or adding new features. Simply fork the repository, make your changes, and create a pull request. I'll be happy to review and merge your changes.

# License

This repository is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

