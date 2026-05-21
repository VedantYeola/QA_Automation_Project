# QA Automation Framework Architecture & Documentation

This repository houses a production-ready, highly structured **UI Test Automation Framework** engineered using **Java**, **Selenium WebDriver**, and **TestNG**. Adhering strictly to software engineering best practices, the framework implements the **Page Object Model (POM)** architectural design pattern to maximize script reusability, isolation of concerns, and ease of maintenance.

---

## 🗺️ Architectural Framework Diagram

Below is the comprehensive technical overview of how data, execution flows, page elements, and external reporting modules interact within this framework:

*Figure 1: Conceptual layer breakdown showcasing External Interfaces, Execution Layer, Page Object Layers, and Report Data Flows.*



---

## 🏗️ Detailed Project Structural Breakdowns

### 1. File & Directory Layout
The structure below outlines the clean separation between business logic, locator repositories, test runner engines, and resource management configurations:

```text
QA_Automation_Project/
│
├── src/
│   ├── main/java/               # Core Framework & Design Patterns
│   │   └── pages/               # Page Object Classes (UI Locators & Methods)
│   │       ├── BasePage.java            # Driver synchronization, explicit waits, wrapper actions
│   │       ├── HomePage.java            # Global elements, category menu hover actions
│   │       ├── ProductListPage.java     # Filters, product grids, sorting logic
│   │       └── SearchResultsPage.java   # Dynamic text matching, item scrape utilities
│   │
│   └── test/java/               # Testing Stack Layer
│       └── tests/               # TestNG Test Suites containing Assertions
│           ├── BaseTest.java            # Test Hooks (@BeforeSuite, @AfterMethod, Driver setups)
│           ├── ProductSearchTest.java   # Validation scripts for e-commerce search pipelines
│           └── CategoryValidationTest.java # Validation scripts for UI index count matching
│
├── src/test/resources/          # Test Orchestration & Configurations
│   ├── testng.xml               # Suite runner configurations, grouping, parameters
│   └── config.properties        # Environment URLs, global timeout rules, browser switches
│
├── target/                      # Auto-generated compilation artifacts
│   └── surefire-reports/        # TestNG Native Reports (HTML/XML execution summaries)
│
├── pom.xml                      # Maven Build Tool Automation & Dependencies Configuration
└── README.md                    # Project Technical Documentation
