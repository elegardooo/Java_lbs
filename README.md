# QR Code API application

This repository contains a simple REST API application that converts text into qr-codes.

## Table of Contents

- [Introduction](#introduction)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Configuration](#configuration)

## Introduction

This is a basic REST API application built using [Spring Boot](https://spring.io/projects/spring-boot) framework and [Maven](https://maven.apache.org). The application allows users to get qr-code converted from text by making HTTP requests to predefined endpoints.

## Technologies Used

- [Spring Boot](https://spring.io/projects/spring-boot): Web framework for building the REST API.
- [QR Code API]( https://api.qrserver.com/v1/create-qr-code/): External API for convertion text into QR Code.

## Getting Started

### Prerequisites

Make sure you have the following installed:

- Java (version 17 or higher)
- Maven

### Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/elegardooo/Java_lbs.git
    ```

2. Build the project:

    ```bash
    mvn clean install
    ```

3. Run the application:

    ```bash
    java -jar target/Java_lbs-0.0.1-SNAPSHOT.jar
    ```

The application will start on `http://localhost:8080`.

## Usage

### Endpoints

- **Get sunrise and sunset time by latitude, longitude and date:**
  
  ```http
  GET /api/v1/qr-code?text=YOUR_TEXT
  ```

  Retrieves image of qr-code that contains written text.

  Example:
  ```http
  GET /api/v1/qr-code?text=hello
  ```

### Configuration

The application uses the [QR Code API](https://goqr.me/) to fetch data. You need to obtain an API URL (https://goqr.me/api/) and configure it in the `application.properties` file.

```properties
# application.properties

# Sunrise Sunset API URL
external.api.urlQrCode=http://api.qrserver.com/v1/create-qr-code/
```
