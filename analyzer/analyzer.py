import pika
import json
import time
from os import _exit
import pymongo
from threading import Thread

# Constants
DB_URL = "mongodb://localhost:27017"
DB_NAME = "testdb"
EXCHANGE_NAME = "default_exchange_name"

# Globals
DB = None
COLLECTION_POLL = None

def init_MongoDB():
    global DB, COLLECTION_POLL
    client = pymongo.MongoClient(DB_URL)
    DB = client[DB_NAME]
    COLLECTION_POLL = DB["polls"]

def init_rabbitmq():
    # Built RABBITMQ-part based on this:
    # https://www.rabbitmq.com/tutorials/tutorial-three-python.html
    # read 12.11.2021
    try:
        connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))
    except pika.exceptions.AMQPConnectionError:
        print(f"Could not connect to RabbitMQ! Exiting... ")
        _exit(1)
    channel = connection.channel()

    result = channel.queue_declare(queue='analyzer2', exclusive=True)
    queue_name = result.method.queue

    channel.queue_bind(exchange=EXCHANGE_NAME, queue=queue_name)
    channel.basic_consume(queue=queue_name, on_message_callback=receive_msg, auto_ack=True)
    channel.start_consuming()

def receive_msg(ch, method, properties, body):
    """Callback method"""
    # convert str to json
    json_obj = json.loads(body)

    # change name from id to _id
    json_obj["_id"] = json_obj.pop("id")

    try:
        COLLECTION_POLL.insert_one(json_obj)
        #print(f"<Received> {json_obj}")
    except pymongo.errors.DuplicateKeyError:
        print(f"[ ERROR ] Duplicate key: {json_obj['_id']} => No insert to db")
    
def log_output():
    while True:
        time.sleep(5)
        print("\n=== Status")
        print(f"Num. polls={COLLECTION_POLL.count_documents({})}")
        # TODO: add more 'facts'
        
if __name__ == "__main__":
    init_MongoDB()

    # starting subscriber
    RabbitMQ_thread = Thread(
        target=init_rabbitmq
    )
    RabbitMQ_thread.start()
    
    # starting logger
    logger = Thread(
        target=log_output
    )
    logger.start()

    # wait for termination
    RabbitMQ_thread.join()
    logger.join()