import psycopg2 as pg


def get_connection():
    connection = pg.connect(user='postgres',
                            password='root',
                            host='localhost',
                            port='5432',
                            database='postgres')
    return connection
