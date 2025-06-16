CREATE TABLE IF NOT EXISTS portfolio (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  ticker TEXT NOT NULL,
  company_name TEXT,
  amount_owned REAL,
  current_percent REAL,
  target_percent REAL,
  suggestion TEXT
);
