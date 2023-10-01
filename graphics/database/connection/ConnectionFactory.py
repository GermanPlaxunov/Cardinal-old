import psycopg2 as ps


def get_connection_cursor():
    return ps.connect(user="postgres",
                      password="root",
                      host="127.0.0.1",
                      port="5432",
                      database="postgres").cursor()
