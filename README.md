# Job Applications Tracker

A full-stack web application to help users manage and track their job applications efficiently.  

## 🚀 Features
- Add, update, and delete job applications
- Track title,  application date, and status
- View all applications in a structured list
- Backend REST API built with Spring Boot
- Frontend built with React
- Containerized with Docker for easy deployment

## 🛠️ Tech Stack
- **Backend:** Spring Boot (Java), REST API, H2 Database (in-memory)
- **Frontend:** React, Axios
- **Database:** H2 (development) – can be swapped for PostgreSQL
- **Deployment:** Docker, hosted on Render

## 🏗️ Architecture
- **Spring Boot API** serves job data
- **React frontend** interacts with the API
- **Docker Compose** ties services together for local development & deployment
- Hosted live on **Render**


## Known Limitations

- **No per-user memory**: The app does not yet store data or context individually per user or device.  
- **Daily database reset**: The backend database is automatically reset every night at midnight, so any data entered during the day will not persist beyond that.

**Next steps**: Implementing user accounts and persistent per-user memory.

![Example Screenshot](jobappex.png)


**Note**
This project was inspired by a basic tutorial for learning Spring Boot. I refactored the app to track job applications, redesigned both frontend and backend code, switched to a new database, containerized it with Docker, and deployed it live—effectively making it a full, functional, independent project.

