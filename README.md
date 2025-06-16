# 📊 Stock Portfolio Rebalance Tool

A full-stack web application that allows users to track, filter, and receive investment suggestions for stocks based on real-time data pulled from Nasdaq.

---

## 📈 Project Overview

This Java + Python-based project tracks a simulated stock portfolio and provides rebalancing recommendations based on target allocations.
It combines:

* A Java core engine for logic and rebalancing
* A Python backend to serve live Nasdaq data
* A SQLite database for persistent storage
* A modern frontend for user interaction

---

## 💻 Features

* Track popular Wall Street-tracked stocks (AAPL, GOOGL, TSLA, AMZN, MSFT, NVDA, etc.)
* Set custom target asset allocations
* Calculate current portfolio weights vs target
* Suggest Buy/Sell actions to rebalance
* Export rebalance summary to Excel `.csv`
* Clean object-oriented structure (5 classes, 2 interfaces)
* Live ticker import from Nasdaq Screener
* Interactive search-based frontend

---

## 🗂️ Project Structure

```
your-project/
├── db/
│   └── portfolio.db             # SQLite database (auto-generated)
├── scripts/
│   └── populate_portfolio.py   # Pulls real Nasdaq data and populates DB
├── frontend/
│   └── index.html              # Frontend UI
├── backend/
│   └── backend_api.py          # Flask API to serve data
├── java/
│   ├── MainApp.java            # Java core logic
│   └── tracker/                # Java classes and interfaces
├── README.md
```

---

## ⚙️ Setup Instructions

### 1. 🔁 Clone the Repository

```bash
git clone https://github.com/your-username/your-project.git
cd your-project
```

### 2. 🐍 Install Python Dependencies

```bash
pip install flask flask-cors pandas requests yfinance
```

### 3. 🗃️ Generate the SQLite Database with Nasdaq Data

```bash
python scripts/populate_portfolio.py
```

This will:

* Call Nasdaq's API
* Extract thousands of stock tickers
* Assign dummy values
* Save into `db/portfolio.db`

### 4. 🔌 Start the Backend API

```bash
python backend/backend_api.py
```

Open: [http://localhost:5000/api/portfolio](http://localhost:5000/api/portfolio)

### 5. 🌐 Launch the Frontend

Simply open the `frontend/index.html` file in your browser.

> Make sure the backend is running so the table can fetch data from `/api/portfolio`.

### 6. ⚙️ Run Java Rebalance Logic (optional)

Compile and run the Java project to:

* Read mock prices
* Analyze portfolio structure
* Export CSV of rebalancing actions

---

## 🧠 Technologies Used

* **Java**: Core logic, data structures (HashMap, TreeMap, List), File I/O, CSV export
* **Python**: Flask API, Nasdaq data ingestion, SQLite insertion
* **JavaScript**: Table rendering, search filtering
* **HTML/CSS**: Responsive frontend
* **SQLite**: Portfolio database


