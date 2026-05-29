# Hippie Hounds Pet Grooming System

Hippie Hounds Pet Grooming is a Java-based terminal application designed to manage and customize appointments for a pet grooming salon. The system supports multi-pet profiling , dynamic sizing options, core services, optional add-ons, and supplemental store extras. It loads pet profiles dynamically via JSON data persistence and serializes final receipts into chronological logs.

## Features

- **Dynamic Catalog Processing:** Supports varied configuration structures based on the specific animal species or demographic:
- **Deep Item Layering:** Build individual sessions selecting from core `ServiceItem` treatments, auxiliary `Addons`, and add-on physical inventory `Extra` product lines.
- **Interactive UI Flows:** Completely command-line interface driven menu navigation with custom colored outputs.
- **Data Persistence Architecture:**
    - Read capabilities utilizing Jackson databinding to parse structured `animals.json` catalog specifications.
    - Automated transactional recording to save generated transaction models down directly into appendable date-time stamped text files.

---

## Architecture Overview

The system architecture employs a structured variation of the Model-View-Controller (MVC) software design pattern:

- **Entities / Models:** Clean structural blueprints (`Catalog`, `ServiceItem`, `AppointmentItem`, `Addons`) representing objects in the database or transactional streams.
- **Repositories (Data Layer):** Interfaces specifying storage adapters decoupling source mechanisms (`AnimalRepository` and `TransactionRepository`) implemented through Jackson JSON streams and BufferedWriter file writes.
- **Services (Business Logic Layer):** Class implementations like `CatalogService` wrapping business rules, exception throwing behaviors (preventing NullPointerExceptions), filtering arrays, and mapping collections securely.
- **Controllers (Flow Orchestration):** Directing user loop pathways between screens (`MainController`, `AppointmentController`, `CombosController`).
- **Frontend / View:** Centralized IO handling (`UI.java`) controlling scanning operations and presentation strings using ANSI escape codes.


## Getting Started

### Prerequisites
- Java Development Kit (JDK) 26 installed.
- Apache Maven installed.
Dexec.mainClass="org.example.PetGroomingApplication"