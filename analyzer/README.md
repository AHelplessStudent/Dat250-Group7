# Analyzer for Application

## About

This program will listen for incoming RabbitMQ-messages. The messages are assumed to be JSON-objects of finished polls. When received, the program will insert them into a database (MongoDB). It will also print status messages regarding the finished polls.

## Usage

First, start the `rest-service`-application. This application requires you have an active RabbitMQ- and PostgreSQL-connection.

Install dependencies
```python3
pip install -r requirements.txt
```

Run analyzer
```python3
python3 analyzer.py
```
