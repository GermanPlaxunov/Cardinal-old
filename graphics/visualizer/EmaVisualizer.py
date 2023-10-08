from database.QueryProvider import get_ema_query
from database.connection.ConnectionFactory import get_connection_cursor
from database.plot.Plotter import show_ema
import pandas as pd


def visualize():
    query = get_ema_query()
    cursor = get_connection_cursor()
    cursor.execute(query)
    dataframe = pd.DataFrame(data=cursor.fetchall())
    show_ema(dataframe=dataframe)