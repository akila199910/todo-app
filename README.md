
# 📝 TODO App – Full Stack Assignment

React (Vite + TypeScript + Tailwind) • Spring Boot 4 (Java 21) • MySQL 8 • Docker

This project is a full-stack Task Management application.
All components (frontend, backend, and database) run using **Docker**, and the entire app can be started using a single command:

```
docker-compose up --build
```

---

## 🚀 Technologies Used

### **Frontend**

* React 18
* TypeScript
* Vite
* Tailwind CSS
* Axios

### **Backend**

* Spring Boot 4.0.0
* Java 21
* Spring Web MVC
* Spring Data JPA
* Spring Validation
* MySQL Connector

### **Database**

* MySQL 8.0

### **Containerization**

* Docker
* Docker Compose

---

## 📁 Project Structure

```
TODO-APP/
│
├── backend/
│   ├── src/
│   ├── target/
│   ├── Dockerfile
│   ├── pom.xml
│   └── mvnw / mvnw.cmd
│
├── frontend/
│   ├── src/
│   ├── public/
│   ├── Dockerfile
│   ├── package.json
│   ├── vite.config.ts
│   └── tailwind.config.js
│
└── docker-compose.yml
```

---

## 🗄️ Database Details

| Property      | Value  |
| ------------- | ------ |
| Database Name | `task` |
| Username      | `root` |
| Password      | `root` |
| Port (Host)   | `3306` |

The MySQL database is automatically created and managed by Docker.
No manual setup is required.

---

## 🐳 Running the Application with Docker

### **1. Clone the repository**

```
git clone https://github.com/akila199910/todo-app
cd TODO-APP
```

### **2. Start all containers**

```
docker-compose up --build
```

Docker will start the following services:

| Service      | URL / Port                                     | Description                |
| ------------ | ---------------------------------------------- | -------------------------- |
| **Frontend** | [http://localhost:5173](http://localhost:5173) | React app served via Nginx |
| **Backend**  | [http://localhost:8080](http://localhost:8080) | Spring Boot REST API       |
| **MySQL**    | localhost:3306                                 | MySQL database (root/root) |

---

## 🔧 API Endpoints (Backend)

### **Base URL**

```
http://localhost:8080/api
```

---

### **1. Create Task**

`POST /tasks`

**Request Body:**

```json
{
  "title": "Buy books",
  "description": "Buy books for next school year"
}
```

---

### **2. Get Recent Tasks (latest 5 incomplete tasks)**

`GET /tasks`

**Response Example:**

```json
[
  {
    "id": 1,
    "title": "Buy books",
    "description": "Buy books for the next school year",
    "completed": false
  }
]
```

---

### **3. Mark Task as Completed**

`POST /tasks/{id}/done`

---

## 🎨 Frontend Features

* Add a new task
* View the latest 5 tasks
* Mark tasks as completed
* Client-side and server-side validation
* Fully responsive Tailwind UI

---

## ⚙️ Environment Variables

### **Frontend (.env)**

```
VITE_API_URL=http://localhost:8080/api
```

### **Backend**

Environment variables are configured automatically inside `docker-compose.yml`.

---

## 🐘 Docker Compose Overview

The `docker-compose.yml` file includes:

* **mysql** → MySQL 8.0 database with persistent volume
* **backend** → Spring Boot (Java 21) packaged as a Docker image
* **frontend** → React app built with Vite and served via Nginx

All services run inside Docker and communicate through a shared network.

---
