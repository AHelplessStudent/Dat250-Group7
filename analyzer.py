import pika
import json
import pymongo

def start(collection):
    # Built RABBITMQ-part based on this:
    # https://www.rabbitmq.com/tutorials/tutorial-three-python.html
    # read 12.11.2021
    connection = pika.BlockingConnection(
        pika.ConnectionParameters(host='localhost'))
    channel = connection.channel()

    #channel.exchange_declare(exchange='default_exchange_name', exchange_type='fanout')

    result = channel.queue_declare(queue='analyzer2', exclusive=True)
    queue_name = result.method.queue

    channel.queue_bind(exchange='default_exchange_name', queue=queue_name)

    print(' [*] Waiting for logs. To exit press CTRL+C')

    def callback(ch, method, properties, body):
        json_obj = json.loads(body)
        collection.insert_one(json_obj)
        print(json_obj)
        #print(f" [x] {body}")

    channel.basic_consume(
        queue=queue_name, on_message_callback=callback, auto_ack=True)

    channel.start_consuming()

def create_db(name):
    client = pymongo.MongoClient("mongodb://localhost:9000")
    return client[name]

if __name__ == "__main__":
    db = create_db("testdb")
    polls = db["polls"]

    start(polls)