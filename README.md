# 🎧 DevSoulify – Android Technical Challenge

Welcome! This is the official **Android technical challenge** for developers who want to join **DevSoul**.  
Your mission is to build a production-ready **Android app** using the [Spotify Web API](https://developer.spotify.com/documentation/web-api/).

You’ll be creating a beautiful **music feed UI** with real Spotify data:  
**new releases** and **featured playlists**.

---

## 🚀 First Step

1. **Fork this repository.**  
2. Open it in **Android Studio (latest stable)**.  
3. Start coding your solution using **Kotlin**, **Jetpack Compose**, **Coroutines**, and **Clean Architecture (MVVM)**.

---

## ✨ What We’ll Look For

- ✅ Proper use of **MVVM** with ViewModels  
- ✅ **Coroutines** and/or **Flow** for async data handling  
- ✅ Modular, readable and well-structured code  
- ✅ Proper **state handling** (loading, error, empty)  
- ✅ Meaningful **Git commits**  
- ✅ Smooth **UI with Jetpack Compose**

**Bonus points**:

- ✅ Clean and scalable **architecture** (data / domain / presentation)  
- ✅ **SOLID** principles applied  
- 🧭 Navigation with Jetpack Navigation Compose  

---

## 🧪 Requirements

Your app must include:

### 1. **Spotify Authentication**

Use a mobile-appropriate OAuth method — recommended:

- **Authorization Code with PKCE**

You must create your client on:  
https://developer.spotify.com/dashboard/

Store secrets safely (e.g. local properties).  
No client secrets in the repository.

---

### 2. **Display This Spotify Endpoint**

#### **Released This Week**  
`GET /browse/new-releases`

Your UI must show:

- Album/playlist image  
- Title  
- Subtitle (artists, description…)  
- A card-style, scrollable feed  

*Bonus*: paginate if API allows.

---

### 3. **Compose UI Requirements**

- Use **LazyColumn / LazyVerticalGrid** or similar  
- Elegant card layout  
- Loading state (progress bar or skeletons)  
- Error state with retry  
- Clean spacing, typography and images  

---

## 🧱 Architecture Guidelines

Use a **Clean Architecture** approach with at least these layers:

### **Data layer**
- Spotify API service  
- DTOs  
- Repositories  
- Mappers to domain models  

### **Domain layer**
- Entities (pure Kotlin)  
- Use Cases / Interactors  
- No Android imports here  

### **Presentation layer**
- Jetpack Compose UI  
- ViewModels with state exposed as:  
  - `State`, `StateFlow`, `MutableState`, etc.  
- UI only observes ViewModel state  
- No business logic in the UI layer

Use **Coroutines / Flow** for async work.

Dependency injection (Hilt / Koin / manual) is recommended.

---

## Example Images
![ExampleSpotify](https://github.com/user-attachments/assets/549e0db1-dce2-4b70-a9bb-6885cc921400)

---

## 📦 Project Setup

### 1. Clone the repo

```bash
git clone <your-fork-url>
cd <project-folder>
