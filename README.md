# 📱 Contact Keeper

A modern, elegant Android **Contact Management App** built with **Kotlin**, **Jetpack Compose**, **Room**, and **MVVM** architecture. Easily create, view, edit, and delete contacts — all stored locally with a beautiful Material 3 UI.

> 🚧 This project is actively under development. More features and improvements coming soon!

---

## ✨ Features

- ✅ Add, edit, and delete contacts (CRUD operations)
- ✅ Display contact details: name, phone number, email
- ✅ Modern UI with **Jetpack Compose** and **Material 3**
- ✅ Splash screen with custom logo
- ✅ Room database for persistent local storage
- ✅ Clean architecture using **MVVM pattern**
- ✅ **Dagger Hilt** for dependency injection
- 🚀 Fast performance and smooth navigation

---

## 🧱 Tech Stack

| Layer               | Technology                    |
|---------------------|-------------------------------|
| Language            | Kotlin                        |
| UI Framework        | Jetpack Compose + Material 3  |
| Architecture        | MVVM                          |
| Local DB            | Room                          |
| Dependency Injection| Dagger Hilt                   |
| Navigation          | Navigation-Compose            |
| IDE                 | Android Studio                |

---

## 📁 Project Structure (Vertical View)
ContactApp1/
presentation/
navigation/ → NavGraph for screen routing
screen/ → Add/Edit & Home screens
utils/ → Custom components (TextField, etc.)
ContactViewModel.kt → ViewModel with state & logic
data/
local/ → Room database, DAO, entities
repository/ → Data repository layer
ui/
theme/ → Theme setup for Compose
BaseApplication.kt → Hilt Application class
MainActivity.kt → Entry point + Splash logic
res/
drawable/ → Icons, splash images, etc.
