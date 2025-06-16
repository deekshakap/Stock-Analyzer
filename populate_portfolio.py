import sqlite3
import requests
import pandas as pd

API_URL = "https://api.nasdaq.com/api/screener/stocks?tableonly=true&limit=10000&offset=0&download=true"
HEADERS = {
    "Accept-Language": "en-US,en;q=0.9",
    "Accept-Encoding": "gzip, deflate, br",
    "User-Agent": "Mozilla/5.0"
}

# 1. Fetch tickers
resp = requests.get(API_URL, headers=HEADERS)
resp.raise_for_status()
rows = resp.json()['data']['rows']
df = pd.DataFrame(rows)

# 2. Optionally filter columns
df = df[['symbol', 'name', 'marketCap']]
df = df[df['marketCap'].notna()]

# 3. Simulated allocations & holdings (you can customize)
df['amount_owned'] = 1000.0
total = df['amount_owned'].sum()
df['current_percent'] = df['amount_owned'] / total * 100
df['target_percent'] = df['current_percent']
df['suggestion'] = 'Hold'

# 4. Wipe/create DB and insert
conn = sqlite3.connect("portfolio.db")
cur = conn.cursor()
cur.execute("""
  CREATE TABLE IF NOT EXISTS portfolio (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    ticker TEXT NOT NULL,
    company_name TEXT,
    amount_owned REAL,
    current_percent REAL,
    target_percent REAL,
    suggestion TEXT
  )
""")
cur.execute("DELETE FROM portfolio")

for _, r in df.iterrows():
    cur.execute("""
      INSERT INTO portfolio (ticker, company_name, amount_owned, current_percent, target_percent, suggestion)
      VALUES (?, ?, ?, ?, ?, ?)
    """, (r.symbol, r.name, r.amount_owned, r.current_percent, r.target_percent, r.suggestion))

conn.commit()
conn.close()
print(f"✅ Imported {len(df)} tickers into portfolio.db")
